import { serverFetchClient } from '@/lib/api/client';
import { createMiddleware } from '@tanstack/react-start'

export const authMiddleware = createMiddleware().server(async ({ next, request }) => {
    const { data, error } = await serverFetchClient.GET("/users/current", {
        headers: request.headers
    })

    const user = (!error && data) ? data : null;
    const roles = user?.roles?.map(role => role.name) || [];
    const isAdmin = roles.includes('ADMIN');

    return next({
        context: {
            user,
            roles,
            isAdmin,
        },
    })
})