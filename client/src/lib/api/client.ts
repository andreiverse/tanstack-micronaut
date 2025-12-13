import createFetchClient from "openapi-fetch";
import createClient from "openapi-react-query";
import type { paths } from "@/lib/api/v1.d.ts";
import { API_BASE_URL } from "@/lib/constants";

const fetchClient = createFetchClient<paths>({
    baseUrl: API_BASE_URL,
    credentials: "include"
});

const $api = createClient(fetchClient);

export default $api;

