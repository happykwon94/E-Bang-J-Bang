import { apiInstance } from "./index.js";

const ebjbAPI = apiInstance();

function listNotice(success, fail) {
  ebjbAPI.get(`/notice`).then(success).catch(fail);
}

function getNotice(no, success, fail) {
  ebjbAPI.get(`/notice/${no}`).then(success).catch(fail);
}

function registNotice(notice, success, fail) {
  ebjbAPI.post(`/notice`, JSON.stringify(notice)).then(success).catch(fail);
}

function deleteNotice(no, success, fail) {
  ebjbAPI.delete(`/notice/${no}`).then(success).catch(fail);
}
function modifyNotice(notice, success, fail) {
  ebjbAPI.put(`/notice`, JSON.stringify(notice)).then(success).catch(fail);
}

export { listNotice, getNotice, registNotice, deleteNotice, modifyNotice };
