import createFetchClient from "openapi-fetch";
import createClient from "openapi-react-query";
import type { paths } from "@/lib/api/v1.d.ts";

const fetchClient = createFetchClient<paths>({
    baseUrl: "https://myapi.dev/v1/",
});
const $api = createClient(fetchClient);

export default $api;
