import {
  HeadContent,
  Scripts,
  createRootRouteWithContext,
} from '@tanstack/react-router'
import { TanStackRouterDevtoolsPanel } from '@tanstack/react-router-devtools'
import { TanStackDevtools } from '@tanstack/react-devtools'

import TanStackQueryDevtools from '../integrations/tanstack-query/devtools'

import appCss from '../styles.css?url'

import type { QueryClient } from '@tanstack/react-query'
import { Toaster } from '@/components/ui/sonner'
import { components } from '@/lib/api/v1'
import { fetchCurrentUserInfo } from '@/functions/auth'
import $api from '@/lib/api/client'

interface MyRouterContext {
  queryClient: QueryClient,
  user: components["schemas"]["UserEntity"] | null
}

export const Route = createRootRouteWithContext<MyRouterContext>()({
  beforeLoad: async ({ context }) => {
    const user = await fetchCurrentUserInfo()

    if (user) {
      context.queryClient.setQueryData($api.queryOptions("get", "/users/current").queryKey, user)
    }

    return {
      user
    }
  },
  head: () => ({
    meta: [
      {
        charSet: 'utf-8',
      },
      {
        name: 'viewport',
        content: 'width=device-width, initial-scale=1',
      },
      {
        title: 'TanStack Start Starter',
      },
    ],
    links: [
      {
        rel: 'stylesheet',
        href: appCss,
      },
    ],
  }),

  shellComponent: RootDocument,
})

function RootDocument({ children }: { children: React.ReactNode }) {

  return (
    <html lang="en">
      <head>
        <HeadContent />
      </head>
      <body>
        <main className="max-w-2xl mx-auto mt-5">
          {children}
        </main>
        <Toaster />
        <TanStackDevtools
          config={{
            position: 'bottom-right',
          }}
          plugins={[
            {
              name: 'Tanstack Router',
              render: <TanStackRouterDevtoolsPanel />,
            },
            TanStackQueryDevtools,
          ]}
        />
        <Scripts />
      </body>
    </html>
  )
}
