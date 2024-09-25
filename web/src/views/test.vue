<template>
  <ContentField>
    <button
      type="button"
      hover-class="button-hover"
      class="btn btn-secondary btn-sm"
      @click="testAddSignature"
    >测试增加签名</button>

    <button
      type="button"
      hover-class="button-hover"
      class="btn btn-secondary btn-sm"
      @click="testgetSignature"
    >测试查询签名</button>
    </ContentField>
</template>
      
      <script>
import ContentField from "../components/ContentField";
import $ from "jquery";
import { useStore } from "vuex";
import URL from "@/store/constants.js"

export default {
  name: "UserProfileView",
  components: {
    ContentField,
  },

  setup() {
    const store = useStore();
    const testAddSignature = () => {
      $.ajax({
      url: URL + "/api/user/signature/update/",
      data: {
        signature: "test",
      },
      type: "post",

      headers: {
        Authorization: "Bearer " + store.state.user.token,
      },
      success(resp) {
        console.log(resp);
      },
    });
    }
    const testgetSignature = () => {
      $.ajax({
      url: URL + "/api/user/signature/get/",
      type: "get",

      headers: {
        Authorization: "Bearer " + store.state.user.token,
      },
      success(resp) {
        console.log(resp);
      },
    });
    }

    return {
      testAddSignature,
      testgetSignature
    }
  },
};
</script>
      
  <style scoped>
</style>