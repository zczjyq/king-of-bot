<template >
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark" style="height: 70px;">
    <div class="container">
      <router-link class="navbar-brand" :to="{ name: 'home' }"
        >King Of Bots</router-link
      >
      <div class="collapse navbar-collapse" id="navbarText">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <router-link
              :class="route_name == 'home' ? 'nav-link active' : 'nav-link'"
              :to="{ name: 'home_index' }"
              >首页</router-link
            >
          </li>
          <li class="nav-item">
            <router-link
              :class="
                route_name == 'record_index' ? 'nav-link active' : 'nav-link'
              "
              :to="{ name: 'record_index' }"
              >对局列表</router-link
            >
          </li>
          <li class="nav-item">
            <router-link
              :class="
                route_name == 'ranklist_index' ? 'nav-link active' : 'nav-link'
              "
              :to="{ name: 'ranklist_index' }"
              >排行榜</router-link
            >
          </li>
          <li class="nav-item">
            <router-link
              :class="
                route_name == 'team' ? 'nav-link active' : 'nav-link'
              "
              :to="{ name: 'team' }"
              >战队</router-link
            >
          </li>
          <li class="nav-item">
            <router-link
              :class="route_name == 'test' ? 'nav-link active' : 'nav-link'"
              :to="{ name: 'test' }"
              >test</router-link
            >
          </li>
        </ul>
        <ul
          class="navbar-nav"
          v-if="$store.state.user.is_login || !$store.state.user.show_content"
        >
          <li class="nav-item dropdown">
            <a
              class="nav-link dropdown-toggle"
              href="#"
              id="navbarDropdown"
              role="button"
              data-bs-toggle="dropdown"
              aria-expanded="false"
            >
              {{ $store.state.user.username }}
            </a>
            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
              <li>
                <router-link
                  class="dropdown-item"
                  :to="{
                    name: 'userprofile',
                    params: { userId: parseInt($store.state.user.id) },
                  }"
                  >发帖</router-link
                >
              </li>
              <li>
                <router-link
                  class="dropdown-item"
                  :to="{
                    name: 'usercenter',
                    params: { userId: parseInt($store.state.user.id) },
                  }"
                  >个人中心</router-link
                >
              </li>
              <li>
                <router-link
                  class="dropdown-item"
                  :to="{
                    name: 'userrevise',
                    params: { userId: parseInt($store.state.user.id) },
                  }"
                  >用户设置</router-link
                >
              </li>
              <li>
                <router-link
                  class="dropdown-item"
                  :to="{ name: 'user_bot_index' }"
                  >我的Bot</router-link
                >
              </li>
              <li><hr class="dropdown-divider" /></li>
              <li>
                <a class="dropdown-item" href="#" @click="logout">退出</a>
              </li>
            </ul>
          </li>
        </ul>
        <ul class="navbar-nav" v-else>
          <li class="nav-item">
            <router-link
              class="nav-link"
              :to="{ name: 'user_account_login' }"
              role="button"
            >
              登录
            </router-link>
          </li>
          <li class="nav-item">
            <router-link
              class="nav-link"
              :to="{ name: 'user_account_register' }"
              role="button"
            >
              注册
            </router-link>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<script>
import { useRoute } from "vue-router";
import { computed } from "vue";
import { useStore } from "vuex";

export default {
  setup() {
    const store = useStore();
    const route = useRoute();
    const show_navbar = store.state.user.show_content;

    // const jwt_token = localStorage.getItem("jwt_token");
    let route_name = computed(() => route.name);
    const logout = () => {
      store.dispatch("logout");
    };
    return {
      route_name,
      logout,
      show_navbar,
    };
  },
};
</script>

<style>
</style>