<template >
  <div class="card edit-field">
    <div class="card-body">
      <label for="edit-post" class="form-label">编辑帖子</label>
      <textarea
        v-model="content"
        class="form-control"
        id="edit-post"
        rows="3"
      ></textarea>
      <button @click="post_a_post" type="button" class="btn btn-info">
        发帖
      </button>
    </div>
  </div>
</template>
      

<script>
import { ref } from "vue";
import $ from "jquery";
import { useStore } from "vuex";

export default {
  name: "UserProfileWrite",
  setup(props, context) {
    let content = ref("");
    const store = useStore();

    const post_a_post = () => {
      $.ajax({
        url: "https://app165.acapp.racwing.com.cn/myspace/post/",
        type: "POST",
        data: {
          content: content.value,
        },
        headers: {
          Authorization: "Bearer " + store.state.user.access,
        },
        success(resp) {
          if (resp.result === "success") {
            context.emit("post_a_post", content.value);
            content.value = "";
          }
        },
      });
    };

    return { content, post_a_post };
  },
};
</script>
  
<style scoped>
.edit-field {
  margin-top: 20px;
}
</style>