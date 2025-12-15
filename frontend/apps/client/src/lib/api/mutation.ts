import $api from "./client";
import { useQueryClient } from "@tanstack/react-query";
import { toast } from "sonner";

export const useLogout = () => {
    const queryClient = useQueryClient();
    const logoutMutation = $api.useMutation("post", "/security/logout", {
        onSettled(_, error) {
            if (error) {
                toast.error("Failed to logout");
            } else {
                toast.success("Logged out successfully");
            }
            queryClient.resetQueries($api.queryOptions("get", "/users/current"));
        },
    });

    return {
        logout: () => logoutMutation.mutateAsync({}),
    };
}