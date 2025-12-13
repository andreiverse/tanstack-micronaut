import LoginForm from '@/components/LoginForm';
import RegisterForm from '@/components/RegisterForm';
import { Button } from '@/components/ui/button';
import { Tabs, TabsContent, TabsList, TabsTrigger } from '@/components/ui/tabs';
import $api from '@/lib/api/client';
import { createFileRoute } from '@tanstack/react-router'
import { useQueryClient } from '@tanstack/react-query';
import { useState } from 'react';

export const Route = createFileRoute('/login')({
    component: RouteComponent,
})

function RouteComponent() {
    const queryClient = useQueryClient();
    const currentUser = $api.useQuery("get", "/users/current");
    const logoutMutation = $api.useMutation("post", "/security/logout");

    const [tab, setTab] = useState<"login" | "register">("login");

    if (currentUser.isSuccess) {
        return <>
            <p>Logged in as {currentUser.data?.email}</p>
            <Button onClick={async () => {
                await logoutMutation.mutateAsync({});
                // Clear the current user query data to prevent stale data after 401
                queryClient.resetQueries(
                    $api.queryOptions("get", "/users/current")
                );
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
