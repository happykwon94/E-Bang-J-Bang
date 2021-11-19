import { apiInstance } from "./index.js";

const ebjbAPI = apiInstance();

function listNotice(success, fail) {
  ebjbAPI.get(`/notice`).then(success).catch(fail);
}

function getNotice(no, success, fail) {
  ebjbAPI.get(`/notice/${no}`).then(success).catch(fail);
}

export { listNotice, getNotice };
