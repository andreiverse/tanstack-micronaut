import { serverFetchClient } from '@/lib/api/client';
import { createMiddleware } from '@tanstack/react-start'
import { components } from "@/lib/api/v1";

type User = components["schemas"]["UserEntity"];

export const authMiddleware = createMiddleware().server(async ({ next, request }) => {
    const { data, error } = await serverFetchClient.GET("/users/current", {
        headers: request.headers
    })

    const user: User | null = (!error && data) ? data : null;

    return next({
        context: {
            user,
        },
    })
})