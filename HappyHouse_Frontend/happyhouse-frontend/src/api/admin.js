import { apiInstance } from "./index.js";

const api = apiInstance();

async function getUserList(success, fail) {
  await api.get(`/admin/user/list`).then(success).catch(fail);
}

async function kickOutUser(id, success, fail) {
  await api.delete(`/admin/user/delete/${id}`).then(success).catch(fail);
}

export { getUserList, kickOutUser };
