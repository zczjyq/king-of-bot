<template>
  <ContentField> </ContentField>
</template>
      
      <script>
import ContentField from "../components/ContentField";
import $ from "jquery";
import { useStore } from "vuex";

export default {
  name: "UserProfileView",
  components: {
    ContentField,
  },

  setup() {
    const store = useStore();
    // $.ajax({
    //   url: "http://localhost:3000/api/follows/change/",
    //   data: {
    //     follower: 1,
    //     following: 2,
    //     status: "unfollow",
    //   },
    //   type: "get",

    //   headers: {
    //     Authorization: "Bearer " + store.state.user.token,
    //   },
    //   success(resp) {
    //     console.log(resp);
    //   },
    // });
    $.ajax({
      url: "http://localhost:3000/api/follows/get/",
      data: {
        follower: 1,
        following: 2,
      },
      type: "get",

      headers: {
        Authorization: "Bearer " + store.state.user.token,
      },
      success(resp) {
        console.log(resp);
      },
    });
  },

  //   return {
  //     ContentFiled
  //   }
};
</script>
      
  <style scoped>
</style>