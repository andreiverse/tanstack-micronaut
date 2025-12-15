import createFetchClient from "openapi-fetch";
import createClient from "openapi-react-query";
import type { paths } from "@/lib/api/v1.d.ts";
import { SERVER_API_BASE_URL, CLIENT_API_BASE_URL } from "@/lib/constants";

export const serverFetchClient = createFetchClient<paths>({
    baseUrl: SERVER_API_BASE_URL,
    credentials: "include"
});

const fetchClient = createFetchClient<paths>({
    baseUrl: CLIENT_API_BASE_URL,
    credentials: "include"
});

const $api = createClient(fetchClient);

export default $api;

