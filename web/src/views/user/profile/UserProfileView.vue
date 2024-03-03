<template>
  <ContentField>
    <div class="row">
      <div class="col-3">
        <!-- @follow="follow" @unfollow="unfollow" :user="user"  -->
        <UserProfileInfo />
        <!--  v-if="is_me" @post_a_post="post_a_post" -->
        <UserProfileWrite />
      </div>
      <div class="col-9">
        <UserProfilePosts :posts="posts" />
      </div>
    </div>
  </ContentField>
</template>
      
      <script>
import ContentField from "@/components/ContentField";
import UserProfileInfo from "@/components/personal_center/UserProfileInfo";
import UserProfilePosts from "@/components/personal_center/UserProfilePosts";
import UserProfileWrite from "@/components/personal_center/UserProfileWrite";
import { reactive } from "vue";

export default {
  name: "UserProfileView",
  components: {
    ContentField,
    UserProfileInfo,
    UserProfilePosts,
    UserProfileWrite,
  },
  setup() {
    const posts = reactive({});

    return { posts };
  },
};
</script>
      
  <style scoped>
</style>