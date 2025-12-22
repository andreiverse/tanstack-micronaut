import { screen } from '@testing-library/react'
import { describe, it, expect } from 'vitest'
import { renderWithClient } from './test/utils'
import $api from './lib/api/client'

function TestComponent() {
    const { data, isSuccess } = $api.useQuery('get', '/users/current')

    if (isSuccess && data) {
        return <div>User: {data.email}</div>
    }
    return <div>Loading...</div>
}

describe('API Integration Test', () => {
    it('should fetch and display user data', async () => {
        renderWithClient(<TestComponent />)

        expect(await screen.findByText('User: test@example.com')).toBeInTheDocument()
    })
})
