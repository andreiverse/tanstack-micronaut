import { http, HttpResponse } from 'msw'

export const handlers = [
    http.get('http://localhost:8080/users/current', () => {
        return HttpResponse.json({
            email: 'test@example.com',
            roles: [{ name: 'USER' }]
        })
    }),
    http.get('*/details/current', () => {
        return HttpResponse.json({
            description: 'Test User Description'
        })
    })
]
