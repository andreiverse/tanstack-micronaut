import LoginForm from '@/components/LoginForm';
import RegisterForm from '@/components/RegisterForm';
import { Button } from '@/components/ui/button';
import { Tabs, TabsContent, TabsList, TabsTrigger } from '@/components/ui/tabs';
import $api from '@/lib/api/client';
import { createFileRoute } from '@tanstack/react-router'
import { useState } from 'react';
import { useLogout } from '@/lib/api/mutation';

export const Route = createFileRoute('/login')({
    component: RouteComponent,
})

function RouteComponent() {
    const { logout } = useLogout();
    const currentUser = $api.useQuery("get", "/users/current");

    const [tab, setTab] = useState<"login" | "register">("login");

    if (currentUser.isSuccess) {
        return <>
            <p>Logged in as {currentUser.data.email}</p>
            <Button onClick={async () => {
                await logout();
            }}>Logout</Button>
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
