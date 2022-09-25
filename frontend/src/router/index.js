import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";
import NotFound from "../views/NotFound.vue";

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: "/funding",
    name: "funding",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "about" */ "@/views/FundingView.vue"),
  },
  {
    path: "/charity",
    name: "charity",
    component: () => import("@/views/CharityView.vue"),
  },
  {
    path: "/product",
    name: "product",
    component: () => import("@/views/ProductView.vue"),
  },
  {
    path: "/plan",
    name: "plan",
    component: () => import("@/views/PlanView.vue"),
  },
  {
    path: "/login",
    name: "login",
    component: () => import("@/views/LoginView.vue"),
  },
  {
    path: "/signup",
    name: "signup",
    component: () => import("@/views/SignupView.vue"),
  },
  {
    path: "/mypage",
    name: "mypage",
    component: () => import("@/views/MypageView.vue"),
  },
  {
    path: "/mypage/maker",
    name: "maker",
    component: () => import("@/views/MakerView.vue"),
  },
  {
    path: "/funding/submit",
    name: "submit",
    component: () => import("@/views/FundingsubmitView.vue"),
  },
  {
    path: "/404",
    name: "notfound",
    component: () => import("@/views/NotFound.vue"),
  },
  {
    path: "/:pathMatch(.*)*",
    redirect: "/404",
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
