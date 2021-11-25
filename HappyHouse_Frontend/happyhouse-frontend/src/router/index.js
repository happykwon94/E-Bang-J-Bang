import Vue from "vue";
import VueRouter from "vue-router";
import store from "@/store/index.js";

import Home from "../views/Home.vue";

import House from "@/views/House.vue";
import HouseMap from "@/components/house/HouseMap.vue";
import HouseList from "@/components/house/HouseList.vue";
import HouseDetail from "@/components/house/HouseDetail.vue";

import NoticeBoard from "@/views/NoticeBoard.vue";
import NoticeBoardList from "@/components/notice/NoticeBoardList.vue";
import NoticeBoardView from "@/components/notice/NoticeBoardView.vue";
import NoticeBoardWrite from "@/components/notice/NoticeBoardWrite.vue";
import NoticeBoardUpdate from "@/components/notice/NoticeBoardUpdate.vue";

import User from "@/views/User.vue";
import SignIn from "@/components/user/SignIn.vue";
import SignUp from "@/components/user/SignUp.vue";
import KakaoLogin from "@/components/social/KakaoLogin.vue";

import BookMark from "@/views/BookMark.vue";
import BookMarkList from "../components/bookmark/BookMarkList.vue";

import FreeBoard from "@/views/FreeBoard.vue";
import FreeBoardList from "@/components/free/FreeBoardList.vue";
import FreeBoardView from "@/components/free/FreeBoardView.vue";
import FreeBoardWrite from "@/components/free/FreeBoardWrite.vue";
import FreeBoardUpdate from "@/components/free/FreeBoardUpdate.vue";

import Admin from "@/views/Admin.vue";
import UserList from "@/components/admin/UserList.vue";
import QuestionList from "@/components/admin/QuestionList.vue";

Vue.use(VueRouter);

const onlyAuthAdmin = async (to, from, next) => {
  const checkUserInfo = store.getters["userStore/checkUserInfo"];
  const getUserInfo = store._actions["userStore/getUserInfo"];
  let token = sessionStorage.getItem("access-token");

  if (checkUserInfo == null && token) {
    await getUserInfo(token);
  }

  if (checkUserInfo === null) {
    alert("로그인이 필요한 페이지입니다.");
    router.push({ name: "SignIn" });
  } else if (checkUserInfo === 10) {
    alert("관리자 권한이 없는 사용자입니다.");
    router.push({ name: "Home" });
  } else {
    next();
  }
};

const onlyAuthUser = async (to, from, next) => {
  // store의 모듈을 활용할 수 있도록 설정
  const checkUserInfo = store.getters["userStore/checkUserInfo"];
  const getUserInfo = store._actions["userStore/getUserInfo"];
  let token = sessionStorage.getItem("access-token");

  if (checkUserInfo == null && token) {
    await getUserInfo(token);
  }

  if (checkUserInfo === null) {
    alert("로그인이 필요한 페이지입니다.");
    router.push({ name: "SignIn" });
  } else {
    console.log("허가된 사용자");
    next();
  }
};

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/house",
    name: "House",
    component: House,
    children: [
      {
        path: "/houseMap",
        name: "HouseMap",
        component: HouseMap,
      },
      {
        path: "/houseList",
        name: "HouseList",
        component: HouseList,
      },
      {
        path: "/houseDetail",
        name: "HouseDetail",
        component: HouseDetail,
      },
    ],
  },
  {
    path: "/notice",
    name: "Notice",
    component: NoticeBoard,
    redirect: "/notice/list",
    children: [
      {
        path: "list",
        name: "NoticeBoardList",
        component: NoticeBoardList,
      },
      {
        path: "list/:no",
        name: "NoticeBoardView",
        beforeEnter: onlyAuthUser,
        component: NoticeBoardView,
      },
      {
        path: "write",
        name: "NoticeBoardWrite",
        beforeEnter: onlyAuthAdmin,
        component: NoticeBoardWrite,
      },
      {
        path: "update",
        name: "NoticeBoardUpdate",
        beforeEnter: onlyAuthAdmin,
        component: NoticeBoardUpdate,
      },
    ],
  },
  {
    path: "/free",
    name: "Free",
    component: FreeBoard,
    redirect: "/free/list",
    children: [
      {
        path: "list",
        name: "FreeBoardList",
        component: FreeBoardList,
      },
      {
        path: "list/:no",
        name: "FreeBoardView",
        beforeEnter: onlyAuthUser,
        component: FreeBoardView,
      },
      {
        path: "write",
        name: "FreeBoardWrite",
        beforeEnter: onlyAuthUser,
        component: FreeBoardWrite,
      },
      {
        path: "update",
        name: "FreeBoardUpdate",
        beforeEnter: onlyAuthUser,
        component: FreeBoardUpdate,
      },
    ],
  },
  {
    path: "/user",
    name: "User",
    component: User,
    redirect: "/user/signin",
    children: [
      {
        path: "/signin",
        name: "SignIn",
        component: SignIn,
      },
      {
        path: "/signup",
        name: "SignUp",
        component: SignUp,
      },
    ],
  },
  {
    path: "/admin",
    name: "Admin",
    beforeEnter: onlyAuthAdmin,
    component: Admin,
    redirect: "/admin/user/list",
    children: [
      {
        path: "user/list",
        name: "UserList",
        component: UserList,
      },
      {
        path: "question/list",
        name: "QuestionList",
        component: QuestionList,
      },
    ],
  },
  {
    path: "/social/kakao",
    name: "KakaoLogin",
    component: KakaoLogin,
  },
  {
    path: "/bookmark",
    name: "BookMark",
    component: BookMark,
    redirect: "/bookmark/list",
    children: [
      {
        path: "list",
        name: "BookMarkList",
        component: BookMarkList,
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
