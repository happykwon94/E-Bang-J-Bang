import { apiInstance } from "./index.js";

const api = apiInstance();

function getHeadlineList(success, fail) {
  api.get(`/news/headline`).then(success).catch(fail);
}

export { getHeadlineList };
