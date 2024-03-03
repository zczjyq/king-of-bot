<template>
  <div class="card">
    <div class="card-body">
      <div class="row">
        <div class="col-3">
          <!-- :src="user.photo" -->
          <img class="img-fluid" alt="" />
        </div>
        <div class="col-9">
          <div class="username">{{ $store.state.user.username }}</div>
          <div class="fans">
            {{ "粉丝数:" + $store.state.user.followerCount }}
          </div>
          <!-- @click="follow"
            v-if="!user.is_followed" -->
          <button
            type="button"
            @click="follow"
            class="btn btn-secondary btn-sm"
          >
            +关注
          </button>
          <!-- @click="unfollow"
            v-if="user.is_followed" -->
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { useRoute } from "vue-router";
import $ from "jquery";
import { useStore } from "vuex";

export default {
  name: "UserProfileInfo",
  setup() {
    const route = useRoute();
    const userId = route.params.userId;
    const store = useStore();
    console.log(userId);

    $.ajax({
      url: "http://localhost:3000/api/follows/get/",
      data: {
        follower: store.state.user.id,
        following: userId,
      },
      type: "get",

      headers: {
        Authorization: "Bearer " + store.state.user.token,
      },
      success(resp) {
        console.log(resp);
      },
    });
    const follow = () => {
      console.log("follow");
    };
    return { follow };
  },
  //   props: {
  //     user: {
  //       type: Object,
  //       required: true,
  //     },
  //   },

  //   setup(props, context) {
  //     const store = useStore();
  //     const follow = () => {
  //       $.ajax({
  //         url: "https://app165.acapp.acwing.com.cn/myspace/follow/",
  //         type: "POST",
  //         headers: {
  //           Authorization: "Bearer " + store.state.user.access,
  //         },
  //         data: {
  //           target_id: props.user.id,
  //         },
  //         success(resp) {
  //           if (resp.result === "success") {
  //             context.emit("follow");
  //           }
  //         },
  //       });
  //     };

  //     const unfollow = () => {
  //       $.ajax({
  //         url: "https://app165.acapp.acwing.com.cn/myspace/follow/",
  //         type: "POST",
  //         headers: {
  //           Authorization: "Bearer " + store.state.user.access,
  //         },
  //         data: {
  //           target_id: props.user.id,
  //         },
  //         success(resp) {
  //           if (resp.result === "success") {
  //             context.emit("unfollow");
  //           }
  //         },
  //       });
  //     };
  //     return { follow, unfollow, props };
  //   },
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
</style>