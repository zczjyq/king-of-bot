<template>
  <div class="card">
    <div class="card-body">
      <div class="row">
        <div class="col-3">
          <!-- :src="user.photo" -->
          <img class="img-fluid" alt="" :src="photo" />
        </div>
        <div class="col-9">
          <div class="username">{{ username }}</div>
          <div class="fans">
            {{ "粉丝数:" + followerCount }}
          </div>
          <div class="rating">
            {{ "天梯分:" + rating }}
          </div>

          <!-- @click="follow"
            v-if="!user.is_followed" 
          
          -->
          <!-- <div>{{ status }}</div> -->
          <div v-if="status !== 'self'">
            <button
              v-if="status === 'unfollow'"
              type="button"
              @click="follow"
              class="btn btn-secondary btn-sm"
            >
              +关注
            </button>
            <button
              v-if="status === 'follow'"
              type="button"
              @click="unfollow"
              class="btn btn-secondary btn-sm"
            >
              取消关注
            </button>
            <!-- @click="unfollow"
            v-if="user.is_followed" -->
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { useRoute } from "vue-router";
import $ from "jquery";
import { useStore } from "vuex";
import { ref } from "vue";
import URL from "@/store/constants.js"

export default {
  name: "UserProfileInfo",
  setup() {
    const route = useRoute();
    const userId = route.params.userId;
    const store = useStore();

    // 关注状态
    let status = ref("self");

    // 用户信息
    let username = ref("");
    let followerCount = ref(0);
    let photo = ref("");
    let rating = ref("");
    console.log(URL);

    // 获取个人信息
    $.ajax({
      
      url: URL + "/api/user/getInfo/",
      data: {
        id: userId,
      },
      type: "post",

      headers: {
        Authorization: "Bearer " + store.state.user.token,
      },
      success(resp) {
        username.value = resp.username;
        photo.value = resp.photo;
        rating.value = resp.rating;
        followerCount.value = resp.follows;
      },
    });

    // 获取粉丝数量
    // $.ajax({
    //   url: "http://localhost:3000/api/follows/get/",
    //   data: {
    //     follower: store.state.user.id,
    //     following: userId,
    //   },
    //   type: "get",

    //   headers: {
    //     Authorization: "Bearer " + store.state.user.token,
    //   },
    //   success(resp) {
    //     status.value = resp.error_message;
    //   },
    // });
    const follow = () => {
      $.ajax({
        url: URL + "/api/follows/change/",
        data: {
          follower: store.state.user.id,
          following: userId,
          status: "follow",
        },
        type: "get",

        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          if (resp.error_message === "success") {
            status.value = "follow";
            // 更新粉丝数量
            $.ajax({
              url: URL + "/api/follows/add/",
              data: {
                user_id: userId,
                change: 1,
              },
              type: "get",

              headers: {
                Authorization: "Bearer " + store.state.user.token,
              },
              success() {
                followerCount.value += 1;
              },
            });
          }
        },
      });
    };

    const unfollow = () => {
      $.ajax({
        url: "/api/follows/change/",
        data: {
          follower: store.state.user.id,
          following: userId,
          status: "unfollow",
        },
        type: "get",

        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          if (resp.error_message === "success") {
            status.value = "unfollow";
            // 更新粉丝数量
            $.ajax({
              url: "http://localhost:3000/api/follows/add/",
              data: {
                user_id: userId,
                change: -1,
              },
              type: "get",

              headers: {
                Authorization: "Bearer " + store.state.user.token,
              },
              success() {
                followerCount.value -= 1;
              },
            });
          }
        },
        error() {},
      });
    };

    return { follow, unfollow, status, followerCount, username, photo, rating };
  },
};
</script>

<style scoped>
img {
  border-radius: 50%;
}
.username {
  font-weight: bold;
}
.fans {
  font-size: 12px;
}
.rating {
  font-size: 12px;
}
</style>