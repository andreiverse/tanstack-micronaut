import { useState } from "react";
import $api from "../lib/api/client";
import { Input } from "./ui/input";
import { Label } from "./ui/label";
import { Button } from "./ui/button";
import { toast } from "sonner";

export default function LoginForm() {
    const currentUser = $api.useQuery("get", "/users/current");
    const loginMutation = $api.useMutation("post", "/login");

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    return <div className="flex flex-col gap-2">
        <Label>Email</Label>
        <Input type="email" value={email} onChange={(e) => setEmail(e.target.value)} />
        <Label>Password</Label>
        <Input type="password" value={password} onChange={(e) => setPassword(e.target.value)} />
        <Button onClick={() => {
            loginMutation.mutateAsync({
                body: {
                    username: email, password
                }
            }).then(() => {
                toast.success("Logged in successfully");
                currentUser.refetch();
            }).catch(() => {
                toast.error("Wrong credentials");
            });
        }}>Login</Button>
    </div>
}