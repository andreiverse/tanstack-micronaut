import { serverFetchClient } from '@/lib/api/client';
import { createMiddleware } from '@tanstack/react-start'

export const authMiddleware = createMiddleware().server(async ({ next, request }) => {
    const { data, error } = await serverFetchClient.GET("/users/current", {
        headers: request.headers
    })

    return next({
        context: {
            user: (!error && data) ? data : null,
        },
    })
})