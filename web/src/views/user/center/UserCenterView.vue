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
      <h5 class="username">{{ user.username }}</h5>
      <!-- 签名 -->
      <p class="signature">{{ user.signature }}</p>
    </div>
    <ul
      class="list-group list-group-flush"
      style="height: 100px; font-size: large"
    >
      <div class="row text-center" style="margin-top: 20px">
        <div class="col" style="color: rgb(51, 181, 229)">
          <div class="number_bold">{{ user.rating }}</div>
          <div>积分</div>
        </div>
        <div class="col" style="color: rgb(40, 199, 111)">
          <div class="number_bold">{{ ranking }}</div>
          <div>排名</div>
        </div>
        <div class="col" style="color: rgb(256, 159, 67)">
          <div class="number_bold">{{ follows }}</div>
          <div>粉丝数</div>
        </div>
      </div>
    </ul>
  </div>
  <div class="card" style="width: 1000px; margin: 40px auto 40px">
    <ul class="nav nav-tabs" style="font-size: large;">
      <li class="nav-item">
        <a
          class="nav-link"
          :class="{ active: activeTab === 'personalMessages' }"
          href="#"
          @click.prevent="activeTab = 'personalMessages'"
        >
          个人消息
        </a>
      </li>
      <li class="nav-item">
        <a
          class="nav-link"
          :class="{ active: activeTab === 'personalSubmissions' }"
          href="#"
          @click.prevent="activeTab = 'personalSubmissions'"
        >
          个人投稿
        </a>
      </li>
      <li class="nav-item">
        <a
          class="nav-link"
          :class="{ active: activeTab === 'writeUp' }"
          href="#"
          @click.prevent="activeTab = 'writeUp'"
        >
          Write up
        </a>
      </li>
      <li class="nav-item">
        <a
          class="nav-link"
          :class="{ active: activeTab === 'personalHonors' }"
          href="#"
          @click.prevent="activeTab = 'personalHonors'"
        >
          个人荣誉
        </a>
      </li>
    </ul>
    <div style="margin: 20px 40px">
      <!-- 个人消息内容 -->
      <div v-if="activeTab === 'personalMessages'">
        <div class="row">
          <div class="col-6">
            <div
              style="
                display: flex;
                flex-direction: column;
                line-height: 3.5;
                color: rgb(80, 80, 80);
              "
            >
              <div style="text-align: left">
                排&emsp;&emsp;名:&ensp; {{ ranking }}
              </div>
              <div style="text-align: left">
                积&emsp;&emsp;分:&ensp; {{ user.rating }}
              </div>
              <div style="text-align: left">所属战队:&ensp;暂无</div>
              <div style="text-align: left">注册时间:&ensp; data</div>
            </div>
          </div>
          <div class="col-2">123</div>
        </div>

        <h3>个人消息</h3>
        <p>这里是个人消息的内容。</p>
      </div>

      <!-- 个人投稿内容 -->
      <div
        v-if="activeTab === 'personalSubmissions'"
        style="
          display: flex;
          flex-direction: column;
          align-items: center;
          justify-content: center;
          text-align: center;
        "
      >
        <img
          style="width: 150px; padding-top: 30px"
          src="../../../assets/img/usercenter/nodata.png"
        />
        <div style="color: gray; padding-bottom: 30px">暂无数据</div>
      </div>

      <!-- Write Up内容 -->
      <div
        v-if="activeTab === 'writeUp'"
        style="
          display: flex;
          flex-direction: column;
          align-items: center;
          justify-content: center;
          text-align: center;
        "
      >
        <img
          style="width: 150px; padding-top: 30px"
          src="../../../assets/img/usercenter/nodata.png"
        />
        <div style="color: gray; padding-bottom: 30px">暂无数据</div>
      </div>

      <!-- 个人荣誉内容 -->
      <div
        v-if="activeTab === 'personalHonors'"
        style="
          display: flex;
          flex-direction: column;
          align-items: center;
          justify-content: center;
          text-align: center;
        "
      >
        <img
          style="width: 150px; padding-top: 30px"
          src="../../../assets/img/usercenter/nodata.png"
        />
        <div style="color: gray; padding-bottom: 30px">暂无数据</div>
      </div>
    </div>
  </div>
  <div>&emsp;</div>
</template>
        
  <script>
// import ContentField from "@/components/ContentField";
import { reactive, onMounted } from "vue";
import { useStore } from "vuex";
import { ref } from "vue";
import $ from "jquery";
import URL from "@/store/constants.js";

export default {
  name: "UserProfileView",
  components: {
    // ContentField,
  },
  data() {
    return {
      activeTab: "personalMessages", // 默认激活个人消息
    };
  },
  setup() {
    const store = useStore();
    const posts = reactive({});
    let ranking = ref(0);
    let follows = ref(0);

    onMounted(() => {
      store.dispatch("getSignature");
      // 获得用户排名
      console.log("开始获取排名");

      $.ajax({
        url: URL + "/api/user/ranking/get/",
        type: "get",

        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          ranking.value = resp;
          console.log(resp);
        },
      });

      $.ajax({
        url: URL + "/api/user/follow/get/",
        type: "get",
        data: {
          username: store.state.user.username,
        },
        success(resp) {
          follows.value = resp;
        },
      });
    });
    let user = ref(store.state.user);

    return { posts, user, ranking, follows };
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
.signature {
  margin-top: 15px;
  text-align: center;
  color: black;
  font-size: 16px;
}
.number_bold {
  font-weight: bold;
  font-size: 20px;
  /* margin-top: 10px; */
}
</style>