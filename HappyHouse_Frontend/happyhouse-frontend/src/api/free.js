import { apiInstance } from "./index.js";

const ebjbAPI = apiInstance();

function listFree(success, fail) {
  ebjbAPI.get(`/free`).then(success).catch(fail);
}

function getFree(no, success, fail) {
  ebjbAPI.get(`/free/${no}`).then(success).catch(fail);
}

function registFree(free, success, fail) {
  ebjbAPI.post(`/free`, JSON.stringify(free)).then(success).catch(fail);
}

async function deleteFree(no, success, fail) {
  await ebjbAPI.delete(`/free/${no}`).then(success).catch(fail);
}
function modifyFree(free, success, fail) {
  ebjbAPI.put(`/free`, JSON.stringify(free)).then(success).catch(fail);
}

export { listFree, getFree, registFree, deleteFree, modifyFree };
