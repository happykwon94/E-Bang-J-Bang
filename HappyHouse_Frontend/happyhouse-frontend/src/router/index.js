import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";

import House from "@/views/House.vue";
import HouseMap from "@/components/house/HouseMap.vue";
import HouseList from "@/components/house/HouseList.vue";
import HouseDetail from "@/components/house/HouseDetail.vue";

import NoticeBoard from "@/views/NoticeBoard.vue";
import NoticeBoardList from "@/components/notice/NoticeBoardList.vue";
import NoticeBoardView from "@/components/notice/NoticeBoardView.vue";

import User from "@/views/User.vue";
import SignIn from "@/components/user/SignIn.vue";
import SignUp from "@/components/user/SignUp.vue";
import KakaoLogin from "@/components/social/KakaoLogin.vue";

import BookMark from "@/views/BookMark.vue";
import BookMarkList from '../components/bookmark/BookMarkList.vue';

Vue.use(VueRouter);

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
        component: NoticeBoardView,
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
