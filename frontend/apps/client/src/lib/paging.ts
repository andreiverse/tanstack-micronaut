import { useState } from "react";

export interface PaginationState {
    page: number;
    size: number;
}

export const usePageState = (initialState?: Partial<PaginationState>) => {
    return useState<PaginationState>({
        page: 0,
        size: 20,
        ...initialState
    });
};