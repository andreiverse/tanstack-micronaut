import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from '@/components/ui/table';
import {
    useReactTable,
    getCoreRowModel,
    flexRender,
    ColumnDef,
    SortingState,
    PaginationState,
    OnChangeFn,
} from '@tanstack/react-table';
import { PaginationControls } from '@/components/PaginationControls';

export interface DataTableProps<TData> {
    columns: ColumnDef<TData, any>[];
    data: TData[];
    isLoading?: boolean;
    // Pagination props
    pagination?: PaginationState;
    onPaginationChange?: OnChangeFn<PaginationState>;
    pageCount?: number;
    totalElements?: number;
    // Sorting props
    sorting?: SortingState;
    onSortingChange?: OnChangeFn<SortingState>;
    // Optional customization
    emptyMessage?: string;
    loadingMessage?: string;
}

export function DataTable<TData>({
    columns,
    data,
    isLoading = false,
    pagination,
    onPaginationChange,
    pageCount = 0,
    totalElements = 0,
    sorting,
    onSortingChange,
    emptyMessage = "No results.",
    loadingMessage = "Loading...",
}: DataTableProps<TData>) {
    const table = useReactTable({
        data,
        columns,
        getCoreRowModel: getCoreRowModel(),
        // Pagination
        manualPagination: !!pagination,
        pageCount,
        state: {
            ...(pagination && { pagination }),
            ...(sorting && { sorting }),
        },
        ...(onPaginationChange && { onPaginationChange }),
        // Sorting
        manualSorting: !!sorting,
        ...(onSortingChange && { onSortingChange }),
    });

    const showPagination = pagination && onPaginationChange && pageCount > 0;

    return (
        <div className="space-y-4">
            <div className="rounded-md border">
                <Table>
                    <TableHeader>
                        {table.getHeaderGroups().map((headerGroup) => (
                            <TableRow key={headerGroup.id}>
                                {headerGroup.headers.map((header) => (
                                    <TableHead key={header.id}>
                                        {header.isPlaceholder
                                            ? null
                                            : flexRender(
                                                header.column.columnDef.header,
                                                header.getContext()
                                            )}
                                    </TableHead>
                                ))}
                            </TableRow>
                        ))}
                    </TableHeader>
                    <TableBody>
                        {table.getRowModel().rows?.length ? (
                            table.getRowModel().rows.map((row) => (
                                <TableRow key={row.id}>
                                    {row.getVisibleCells().map((cell) => (
                                        <TableCell key={cell.id}>
                                            {flexRender(
                                                cell.column.columnDef.cell,
                                                cell.getContext()
                                            )}
                                        </TableCell>
                                    ))}
                                </TableRow>
                            ))
                        ) : (
                            <TableRow>
                                <TableCell
                                    colSpan={columns.length}
                                    className="h-24 text-center"
                                >
                                    {isLoading ? loadingMessage : emptyMessage}
                                </TableCell>
                            </TableRow>
                        )}
                    </TableBody>
                </Table>
            </div>

            {showPagination && (
                <PaginationControls
                    currentPage={pagination.pageIndex}
                    totalPages={pageCount}
                    pageSize={pagination.pageSize}
                    totalElements={totalElements}
                    onPageChange={(page) => onPaginationChange({ ...pagination, pageIndex: page })}
                    onPageSizeChange={(size) => onPaginationChange({ ...pagination, pageSize: size, pageIndex: 0 })}
                />
            )}
        </div>
    );
}
