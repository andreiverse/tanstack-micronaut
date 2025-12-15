import { createServerFn } from '@tanstack/react-start'
import { authMiddleware } from '@/middleware/auth'

export const fetchCurrentUserInfo = createServerFn({ method: 'GET' })
    .middleware([authMiddleware])
    .handler(async ({ context }) => {
        return context.user
    })