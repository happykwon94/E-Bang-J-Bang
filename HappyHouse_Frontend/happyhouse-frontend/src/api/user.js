import { apiInstance } from "./index.js";

const api = apiInstance();

async function login(user, success, fail) {
  await api.post(`/user/login`, JSON.stringify(user)).then(success).catch(fail);
}

async function findById(userid, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/user/info/${userid}`).then(success).catch(fail);
}

async function signUp(user, success, fail) {
  await api.post(`/user/signup`, JSON.stringify(user)).then(success).catch(fail);
}

async function kakaoLogin(data, success, fail) {
  await api
    .get(`/user/auth/kakao`, { params: { code: data } })
    .then(success)
    .catch(fail);
}

async function validate(type, value, success, fail) {
  await api.get(`/user/validate/${type}/${value}`).then(success).catch(fail);
}

export { login, findById, signUp, kakaoLogin, validate };
