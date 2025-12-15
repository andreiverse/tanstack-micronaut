import { useState } from "react";
import $api from "../lib/api/client";
import { Input } from "./ui/input";
import { Label } from "./ui/label";
import { Button } from "./ui/button";
import { toast } from "sonner";

export default function RegisterForm({ onRegister }: { onRegister: () => void }) {
    const registerMutation = $api.useMutation("post", "/users");

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    return <div className="flex flex-col gap-2">
        <Label>Email</Label>
        <Input type="email" value={email} onChange={(e) => setEmail(e.target.value)} />
        <Label>Password</Label>
        <Input type="password" value={password} onChange={(e) => setPassword(e.target.value)} />
        <Button onClick={() => {
            registerMutation.mutateAsync({
                body: {
                    email, password
                }
            }).then(() => {
                toast.success("Registered successfully");
                onRegister();
            }).catch((error) => {
                toast.error("Failed to register: " + error.message);
            });
        }}>Register</Button>
    </div>
}