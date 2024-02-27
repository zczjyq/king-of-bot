<template>
  <ContentField>我的Bot</ContentField>
</template>

<script>
import ContentField from "../../../components/ContentField.vue";
import $ from "jquery";
import { useStore } from "vuex";

export default {
  components: {
    ContentField,
  },
  setup() {
    const store = useStore();
    // $.ajax({
    //   url: "http://localhost:3000/user/bot/add/",
    //   type: "post",
    //   data: {
    //     title: "test3",
    //     description: "afd",
    //     content: "afd",
    //   },
    //   headers: {
    //     Authorization: "Bearer " + store.state.user.token,
    //   },
    //   success(resp) {
    //     console.log(resp);
    //   },
    //   error(resp) {
    //     console.log("fuck");
    //     console.log(resp);
    //   },
    // });
    // $.ajax({
    //   url: "http://localhost:3000/user/bot/remove/",
    //   type: "post",
    //   data: {
    //     bot_id: "3",
    //   },
    //   headers: {
    //     Authorization: "Bearer " + store.state.user.token,
    //   },
    //   success(resp) {
    //     console.log("remove");
    //     console.log(resp);
    //   },
    //   error(resp) {
    //     console.log("fuck");
    //     console.log(resp);
    //   },
    // });
    // $.ajax({
    //   url: "http://localhost:3000/user/bot/update/",
    //   type: "post",
    //   data: {
    //     bot_id: "4",
    //     title: "updata",
    //     description: "updata",
    //     content: "",
    //   },
    //   headers: {
    //     Authorization: "Bearer " + store.state.user.token,
    //   },
    //   success(resp) {
    //     console.log(resp);
    //   },
    //   error(resp) {
    //     console.log("fuck");
    //     console.log(resp);
    //   },
    // });
    $.ajax({
      url: "http://localhost:3000/user/bot/getlist/",
      type: "get",
      headers: {
        Authorization: "Bearer " + store.state.user.token,
      },
      success(resp) {
        console.log(resp);
      },
      error(resp) {
        console.log("fuck");
        console.log(resp);
      },
    });
  },
};
</script>

<style scoped>
</style>