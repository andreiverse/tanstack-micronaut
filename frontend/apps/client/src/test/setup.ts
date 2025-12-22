import '@testing-library/jest-dom/vitest'
import { cleanup } from '@testing-library/react'
import { afterAll, afterEach, beforeAll } from 'vitest'
import { server } from './mocks/server'

const isMockingEnabled = !import.meta.env.VITE_REAL_BACKEND

// Start server before all tests if mocking is enabled
beforeAll(() => {
    if (isMockingEnabled) {
        server.listen({ onUnhandledRequest: 'error' })
    }
})

//  Close server after all tests
afterAll(() => {
    if (isMockingEnabled) {
        server.close()
    }
})

// Reset handlers after each test `important for test isolation`
afterEach(() => {
    if (isMockingEnabled) {
        server.resetHandlers()
    }
    cleanup()
})
