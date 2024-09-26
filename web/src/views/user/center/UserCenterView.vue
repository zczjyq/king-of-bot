<template>
  <div class="card" style="width: 1000px; margin: 40px auto">
    <img
      src="../../../assets/img/usercenter/background.jpg"
      class="card-img-top"
      alt="..."
    />
    <!-- 头像 -->
    <img :src="user.photo" alt="Avatar" class="avatar" />
    
    <div class="card-body">
      <!-- 名字 -->
      <h5 class=" username">{{ user.username }}</h5>
      <!-- 签名 -->
      <p class="signature">{{ user.signature }}</p>
    </div>
    <ul class="list-group list-group-flush">
      <li class="list-group-item">An item</li>
      <li class="list-group-item">A second item</li>
      <li class="list-group-item">A third item</li>
    </ul>
    <div class="card-body">
      <a href="#" class="card-link">Card link</a>
      <a href="#" class="card-link">Another link</a>
    </div>
  </div>
</template>
        
  <script>
// import ContentField from "@/components/ContentField";
import { reactive ,onMounted } from "vue";
import { useStore } from "vuex";
import { ref } from "vue";


export default {
  name: "UserProfileView",
  components: {
    // ContentField,
  },

  setup() {
    
    const store = useStore();
    const posts = reactive({});

    onMounted(() => {
      store.dispatch("getSignature");
      console.log(user.value);
    });
    let user = ref(store.state.user);
    console.log(user.value);
    
    return { posts, user };
  },
};
</script>




<style scoped>
.avatar {
  position: absolute; /* 使用绝对定位 */
  top: 262px; /* 调整头像顶部位置 */
  left: 50%; /* 水平居中 */
  transform: translateX(-50%); /* 进一步确保居中 */
  width: 100px; /* 设置头像宽度 */
  height: 100px; /* 设置头像高度 */
  border-radius: 50%; /* 圆形头像 */
  border: 2px solid white; /* 可选：给头像添加边框 */
}
.username {
  margin-top: 40px;
  text-align: center;
  color: black;
  font-size: 24px;
}
</style>