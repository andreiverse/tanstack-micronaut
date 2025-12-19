import { createFileRoute, redirect } from '@tanstack/react-router'
import { createColumnHelper, SortingState } from '@tanstack/react-table';
import { components } from '@/lib/api/v1';
import { useState, useMemo } from 'react';
import { usePageState } from '@/lib/paging';
import { Button } from '@/components/ui/button';
import { ArrowUpDown } from 'lucide-react';
import $api from '@/lib/api/client';
import { DataTable } from '@/components/data-table';

type UserEntity = components["schemas"]["UserEntity"];

export const Route = createFileRoute('/admin/users')({
  beforeLoad: ({ context }) => {
    if (!context.isAdmin) {
      throw redirect({
        to: '/',
      })
    }
  },
  component: RouteComponent,
})

const columnHelper = createColumnHelper<UserEntity>();

function RouteComponent() {
  const [pagination, setPagination] = usePageState();
  const [sorting, setSorting] = useState<SortingState>([]);

  // Build sort parameter from sorting state
  const sortParam = useMemo(() => {
    if (sorting.length === 0) return undefined;
    const sort = sorting[0];
    return `${sort.id},${sort.desc ? 'desc' : 'asc'}`;
  }, [sorting]);

  const usersQuery = $api.useQuery("get", "/users", {
    params: {
      query: {
        page: pagination.page,
        size: pagination.size,
        sort: sortParam,
      }
    }
  });

  const columns = useMemo(() => [
    columnHelper.accessor('id', {
      header: 'ID',
      cell: info => info.getValue(),
    }),
    columnHelper.accessor('email', {
      header: ({ column }) => {
        return (
          <Button
            variant="ghost"
            onClick={() => column.toggleSorting(column.getIsSorted() === "asc")}
          >
            Email
            <ArrowUpDown className="ml-2 h-4 w-4" />
          </Button>
        )
      },
      cell: info => info.getValue(),
    }),
    columnHelper.accessor('roles', {
      header: 'Roles',
      cell: info => info.getValue()?.map(role => role.name).join(", ") || '-',
      enableSorting: false,
    }),
    columnHelper.accessor('createdAt', {
      header: ({ column }) => {
        return (
          <Button
            disabled
            variant="ghost"
          >
            Created At
            {/* <ArrowUpDown className="ml-2 h-4 w-4" /> */}
          </Button>
        )
      },
      cell: info => new Date(info.getValue()).toLocaleDateString(),
    }),
  ], []);

  return (
    <DataTable
      columns={columns}
      data={usersQuery.data?.content || []}
      isLoading={usersQuery.isLoading}
      pagination={{
        pageIndex: pagination.page,
        pageSize: pagination.size,
      }}
      onPaginationChange={(updater) => {
        const newPagination = typeof updater === 'function'
          ? updater({ pageIndex: pagination.page, pageSize: pagination.size })
          : updater;
        setPagination({ page: newPagination.pageIndex, size: newPagination.pageSize });
      }}
      pageCount={usersQuery.data?.totalPages || 0}
      totalElements={usersQuery.data?.totalSize || 0}
      sorting={sorting}
      onSortingChange={setSorting}
    />
  );
}
