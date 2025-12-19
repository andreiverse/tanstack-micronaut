import { Button } from "@/components/ui/button";
import {
    Select,
    SelectContent,
    SelectItem,
    SelectTrigger,
    SelectValue,
} from "@/components/ui/select";

interface PaginationControlsProps {
    currentPage: number;
    totalPages: number;
    pageSize: number;
    totalElements: number;
    onPageChange: (page: number) => void;
    onPageSizeChange: (size: number) => void;
}

export function PaginationControls({
    currentPage,
    totalPages,
    pageSize,
    totalElements,
    onPageChange,
    onPageSizeChange,
}: PaginationControlsProps) {
    const startItem = currentPage * pageSize + 1;
    const endItem = Math.min((currentPage + 1) * pageSize, totalElements);

    return (
        <div className="flex items-center justify-between px-2 py-4">
            <div className="flex items-center space-x-2">
                <p className="text-sm text-muted-foreground">
                    Showing {startItem} to {endItem} of {totalElements} results
                </p>
            </div>

            <div className="flex items-center space-x-6">
                <div className="flex items-center space-x-2">
                    <p className="text-sm font-medium">Rows per page</p>
                    <Select
                        value={pageSize.toString()}
                        onValueChange={(value) => onPageSizeChange(Number(value))}
                    >
                        <SelectTrigger className="h-8 w-[70px]">
                            <SelectValue placeholder={pageSize} />
                        </SelectTrigger>
                        <SelectContent side="top">
                            {[10, 20, 30, 40, 50].map((size) => (
                                <SelectItem key={size} value={size.toString()}>
                                    {size}
                                </SelectItem>
                            ))}
                        </SelectContent>
                    </Select>
                </div>

                <div className="flex items-center space-x-2">
                    <p className="text-sm font-medium">
                        Page {currentPage + 1} of {totalPages}
                    </p>
                    <div className="flex items-center space-x-1">
                        <Button
                            variant="outline"
                            size="sm"
                            onClick={() => onPageChange(0)}
                            disabled={currentPage === 0}
                        >
                            First
                        </Button>
                        <Button
                            variant="outline"
                            size="sm"
                            onClick={() => onPageChange(currentPage - 1)}
                            disabled={currentPage === 0}
                        >
                            Previous
                        </Button>
                        <Button
                            variant="outline"
                            size="sm"
                            onClick={() => onPageChange(currentPage + 1)}
                            disabled={currentPage >= totalPages - 1}
                        >
                            Next
                        </Button>
                        <Button
                            variant="outline"
                            size="sm"
                            onClick={() => onPageChange(totalPages - 1)}
                            disabled={currentPage >= totalPages - 1}
                        >
                            Last
                        </Button>
                    </div>
                </div>
            </div>
        </div>
    );
}
