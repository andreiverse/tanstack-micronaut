import $api from '@/lib/api/client';
import { createFileRoute } from '@tanstack/react-router'
export const Route = createFileRoute('/')({ component: App })

function App() {
  const pingQuery = $api.useQuery("get", "/ping");


  return <>
    {pingQuery.isPending && "Checking api status..."}

    {pingQuery.isError && "Error checking api status"}

    {pingQuery.isSuccess && <>{pingQuery.data.ok ? "Api is up and running" : "Api is down"}</>}
  </>;
}
