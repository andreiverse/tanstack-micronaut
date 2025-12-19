# DataTable Component Usage

The `DataTable` component is a generic, reusable table component built on top of TanStack Table with built-in support for pagination, sorting, and loading states.

## Features

- **Type-safe**: Fully typed with TypeScript generics
- **Pagination**: Built-in pagination controls with customizable page size
- **Sorting**: Support for manual and automatic sorting
- **Loading states**: Displays loading and empty state messages
- **Flexible**: Customizable columns, messages, and behavior

## Basic Usage

```tsx
import { DataTable } from '@/components/data-table';
import { createColumnHelper } from '@tanstack/react-table';

type MyData = {
  id: number;
  name: string;
  email: string;
};

const columnHelper = createColumnHelper<MyData>();

function MyComponent() {
  const columns = [
    columnHelper.accessor('name', {
      header: 'Name',
      cell: info => info.getValue(),
    }),
    columnHelper.accessor('email', {
      header: 'Email',
      cell: info => info.getValue(),
    }),
  ];

  const data: MyData[] = [
    { id: 1, name: 'John', email: 'john@example.com' },
    { id: 2, name: 'Jane', email: 'jane@example.com' },
  ];

  return (
    <DataTable
      columns={columns}
      data={data}
    />
  );
}
```

## With Pagination

```tsx
import { useState } from 'react';
import { DataTable } from '@/components/data-table';

function MyComponent() {
  const [pagination, setPagination] = useState({
    pageIndex: 0,
    pageSize: 10,
  });

  // ... columns and data

  return (
    <DataTable
      columns={columns}
      data={data}
      pagination={pagination}
      onPaginationChange={setPagination}
      pageCount={totalPages}
      totalElements={totalElements}
    />
  );
}
```

## With Sorting

```tsx
import { useState } from 'react';
import { DataTable } from '@/components/data-table';
import { SortingState } from '@tanstack/react-table';
import { Button } from '@/components/ui/button';
import { ArrowUpDown } from 'lucide-react';

function MyComponent() {
  const [sorting, setSorting] = useState<SortingState>([]);

  const columns = [
    columnHelper.accessor('name', {
      header: ({ column }) => (
        <Button
          variant="ghost"
          onClick={() => column.toggleSorting(column.getIsSorted() === "asc")}
        >
          Name
          <ArrowUpDown className="ml-2 h-4 w-4" />
        </Button>
      ),
      cell: info => info.getValue(),
    }),
  ];

  return (
    <DataTable
      columns={columns}
      data={data}
      sorting={sorting}
      onSortingChange={setSorting}
    />
  );
}
```

## With Loading State

```tsx
function MyComponent() {
  const { data, isLoading } = useQuery(...);

  return (
    <DataTable
      columns={columns}
      data={data || []}
      isLoading={isLoading}
      loadingMessage="Fetching data..."
      emptyMessage="No items found."
    />
  );
}
```

## Props

| Prop | Type | Required | Description |
|------|------|----------|-------------|
| `columns` | `ColumnDef<TData, any>[]` | Yes | Column definitions using TanStack Table's column helper |
| `data` | `TData[]` | Yes | Array of data to display |
| `isLoading` | `boolean` | No | Whether data is currently loading |
| `pagination` | `PaginationState` | No | Current pagination state `{ pageIndex, pageSize }` |
| `onPaginationChange` | `OnChangeFn<PaginationState>` | No | Callback when pagination changes |
| `pageCount` | `number` | No | Total number of pages |
| `totalElements` | `number` | No | Total number of elements across all pages |
| `sorting` | `SortingState` | No | Current sorting state |
| `onSortingChange` | `OnChangeFn<SortingState>` | No | Callback when sorting changes |
| `emptyMessage` | `string` | No | Message to display when no data (default: "No results.") |
| `loadingMessage` | `string` | No | Message to display while loading (default: "Loading...") |

## See Also

- [TanStack Table Documentation](https://tanstack.com/table/latest)
- [users.tsx](/home/d4rck/tanstack-micronaut/frontend/apps/client/src/routes/admin/users.tsx) - Real-world example
