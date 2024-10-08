import { createRouter, createWebHistory } from 'vue-router'
import NotFound from '../views/error/NotFound.vue'
import PkIndexView from '../views/pk/PkIndexView.vue'
import RanklistIndexView from '../views/ranklist/RanklistIndexView.vue'
import RecordContentView from '../views/record/RecordContentView.vue';
import RecordIndexView from '../views/record/RecordIndexView.vue'
import UserBotIndexView from '../views/user/bot/UserBotIndexView.vue'
import UserAccountLoginView from '../views/user/account/UserAccountLoginView.vue'
import UserAccountRegisterView from '../views/user/account/UserAccountRegisterView.vue'
import store from '../store/index'
import UserProfileView from '../views/user/profile/UserProfileView.vue';
import test from '../views/TestView.vue';
import showImage from '../views/showImage.vue'
import UserReviseView from '../views/user/revise/UserReviseView.vue'
import UserCenterView from '@/views/user/center/UserCenterView.vue';
import HomeView from '../views/HomeView.vue'
import TeamListView from '@/views/team/TeamListView.vue';
import TeamView from '@/views/team/TeamView.vue';
import TeamManageView from '@/views/user/revise/TeamManageView.vue';
const routes = [
  // PK界面
  {
    path: '/',
    name: 'home',
    redirect: "/team/",
    meta: {
      requestAuth: true,
    }
  },
  {
    path: "/home/",
    name: "home_index",
    component: HomeView,
    meta: {
      requestAuth: true,
    }
  },

  // 
  {
    path: "/pk/",
    name: "pk_index",
    component: PkIndexView,
    meta: {
      requestAuth: true,
    }
  },

  {
    path: "/record/",
    name: "record_index",
    component: RecordIndexView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: "/record/:recordId/",
    name: "record_content",
    component: RecordContentView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: "/teaminfo/:teamId/",
    name: "teaminfo",
    component: TeamView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: "/team/",
    name: "team",
    component: TeamListView,
    meta: {
      requestAuth: true,
    }
  },
  // 排行榜界面
  {
    path: "/ranklist/",
    name: "ranklist_index",
    component: RanklistIndexView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: "/user/bot/",
    name: "user_bot_index",
    component: UserBotIndexView,
    meta: {
      requestAuth: true,
    }
  },

  {
    path: "/recharge/",
    name: "recharge",
    component: () => import('@/views/recharge/rechargeView.vue'),
  },

  // 404界面
  {
    path: "/404/",
    name: "not_found_index",
    component: NotFound,
    meta: {
      requestAuth: false,
    }
  },

  // 登录界面 
  {
    path: "/user/account/login/",
    name: "user_account_login",
    component: UserAccountLoginView,
    meta: {
      requestAuth: false,
    }
  },

  // 注册界面 
  {
    path: "/user/account/register/",
    name: "user_account_register",
    component: UserAccountRegisterView,
    meta: {
      requestAuth: false,
    }
  },

  // 发帖
  {
    path: '/userprofile/:userId/',
    name: 'userprofile',
    component: UserProfileView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: '/usercenter/:userId/',
    name: 'usercenter',
    component: UserCenterView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: '/userrevise/:userId/',
    name: 'userrevise',
    component: UserReviseView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: '/teammanage/:userId/',
    name: 'teammanage',
    component: TeamManageView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: '/feedback/',
    name: 'feedback',
    component: () => import('@/views/feedback/feedBackView.vue'),
  },
  // 测试界面
  {
    path: '/test/',
    name: 'test',
    component: test,
    meta: {
      requestAuth: false,
    }
  },


  // MYSQL课设演示
  {
    path: '/showImage/',
    name: 'showImage',
    component: showImage,
    meta: {
      requestAuth: false,
    }
  },
  {
    path: "/:catchAll(.*)*",
    redirect: "/404/"
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

router.beforeEach((to, from, next) => {
  if (to.meta.requestAuth && !store.state.user.is_login) {
    next({ name: 'user_account_login' });
  } else {
    next();
  }
})

export default router