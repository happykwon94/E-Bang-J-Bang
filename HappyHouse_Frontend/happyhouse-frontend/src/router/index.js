import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";

import House from "../views/House.vue";
import HouseMap from "../components/house/HouseMap.vue";
import HouseList from "../components/house/HouseList.vue";


Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/about",
    name: "About",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/About.vue"),
  },
  {
    path: "/house",
    name: "House",
    component: House,
    children: [
      {
        path: "/houseMap",
        name: "HouseMap",
        component: HouseMap
      },
      {
        path: "/houseList",
        name: "HouseList",
        component: HouseList
      }
    ]
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
