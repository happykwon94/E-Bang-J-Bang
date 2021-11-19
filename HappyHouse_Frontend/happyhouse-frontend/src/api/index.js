import axios from "axios";
import { API_EBJB_URL } from "@/config";

// EBJB REST API 서버를 베이스로 하는 Axios 객체 생성
function apiInstance() {
  const instance = axios.create({
    baseURL: API_EBJB_URL,
    headers: {
      "Content-type": "application/json",
    },
  });
  return instance;
}

export { apiInstance };
