import LoginForm from '@/components/LoginForm';
import RegisterForm from '@/components/RegisterForm';
import { Button } from '@/components/ui/button';
import $api from '@/lib/api/client';
import { useLogout } from '@/lib/api/mutation';
import { Tabs, TabsList, TabsTrigger, TabsContent } from '@/components/ui/tabs';
import { createFileRoute } from '@tanstack/react-router'
import { useState } from 'react';

export const Route = createFileRoute('/profile')({
  component: RouteComponent,
})

function RouteComponent() {
  const { logout } = useLogout();
  const currentUser = $api.useQuery("get", "/users/current");
  const currentUserDetails = $api.useQuery("get", "/details/current")

  const [tab, setTab] = useState<"login" | "register">("login");

  if (currentUser.isSuccess) {
    return <>
      <p>Logged in as {currentUser.data.email}</p>
      <p>Roles: {currentUser.data.roles?.map(role => role.name).join(", ")}</p>
      <Button onClick={async () => {
        await logout();
      }}>Logout</Button>
      <p>Details: {currentUserDetails.data?.description}</p>
    </>
  }

  return <>
    <Tabs value={tab} onValueChange={(v) => setTab(v as any)} >
      <TabsList>
        <TabsTrigger value="login">Login</TabsTrigger>
        <TabsTrigger value="register">Register</TabsTrigger>
      </TabsList>
      <TabsContent value="login">
        <LoginForm />
      </TabsContent>
      <TabsContent value="register">
        <RegisterForm onRegister={() => setTab("login")} />
      </TabsContent>
    </Tabs>
  </>

}