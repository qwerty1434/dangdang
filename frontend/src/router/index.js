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
    children: [
      {
        path: "story",
        name: "story",
        component: () => import("@/components/ProductStory.vue"),
      },
      {
        path: "supporter",
        name: "supporter",
        component: () => import("@/components/ProductSupporter.vue"),
      },
      {
        path: "account",
        name: "account",
        component: () => import("@/components/ProductAccount.vue"),
      },
      {
        path: "purchase",
        name: "purchase",
        component: () => import("@/components/FundingPurchase.vue"),
      },
    ],
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
    children: [
      {
        path: "onfunding",
        name: "onfunding",
        component: () => import("@/components/UserJoinOnFundings.vue"),
      },
      {
        path: "endfunding",
        name: "endfunding",
        component: () => import("@/components/UserJoinEndFundings.vue"),
      },
    ],
  },
  {
    path: "/mypage/maker",
    name: "maker",
    component: () => import("@/views/MakerView.vue"),
    children: [
      {
        path: "prefunding",
        component: () => import("@/components/MakerMypagePreFunding.vue"),
      },
      {
        path: "onfunding",
        component: () => import("@/components/MakerMypageProcessFunding.vue"),
      },
      {
        path: "endfunding",
        component: () => import("@/components/MakerMypageEndFunding.vue"),
      },
    ],
  },
  {
    path: "/funding/submit",
    name: "submit",
    component: () => import("@/views/FundingsubmitView.vue"),
  },
  {
    path: "/funding/cashout",
    name: "cashout",
    component: () => import("@/views/FundingCashout.vue"),
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
  {
    path: "/temp",
    name: "temp",
    component: () => import("@/views/FundingCashout.vue"),
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
