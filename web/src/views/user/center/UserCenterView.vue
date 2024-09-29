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
      <div class="userId">UID: {{ String(user.id).padStart(6, "0") }}</div>
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
          <div class="number_bold">{{ user.ranking }}</div>
          <div>排名</div>
        </div>
        <div class="col" style="color: rgb(256, 159, 67)">
          <div class="number_bold">{{ user.follows }}</div>
          <div>粉丝数</div>
        </div>
      </div>
    </ul>
  </div>
  <div class="card" style="width: 1000px; margin: 40px auto 40px">
    <ul class="nav nav-tabs nav-fill">
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
                排&emsp;&emsp;名:&ensp; {{ user.ranking }}
              </div>
              <div style="text-align: left">
                积&emsp;&emsp;分:&ensp; {{ user.rating }}
              </div>
              <div style="text-align: left">
                粉丝数&emsp;:&ensp; {{ user.follows }}
              </div>
              <div style="text-align: left">所属战队:&ensp;暂无</div>
              <div style="text-align: left">
                注册时间:&ensp; {{ user.registerTime }}
              </div>
            </div>
          </div>
          <div class="col-2"></div>
          <!-- 绘制雷达图 -->
          <div class="col-4">
            <canvas id="radarChart" width="300" height="300"></canvas>
          </div>
        </div>
        <!-- 绘制折线图 -->
        <LineChart />
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
import LineChart from "@/components/userInfo/LineChart.vue"; // 引入 LineChart 组件
import { useRoute } from "vue-router";
import {
  Chart,
  RadialLinearScale,
  PointElement,
  LineElement,
  Filler,
  Tooltip,
  // Legend,/
  RadarController,
} from "chart.js";
export default {
  name: "UserProfileView",
  components: {
    // ContentField,
    LineChart,
  },

  data() {
    return {
      activeTab: "personalMessages", // 默认激活个人消息
    };
  },
  setup() {
    const store = useStore();
    const posts = reactive({});
    let user = ref({});

    // 绘制折线图
    const labels = ["Week 1", "Week 2", "Week 3", "Week 4", "Week 5"];
    const data = [1500, 1600, 1580, 1650, 1700]; // 天梯分数据

    const route = useRoute();
    const userId = route.params.userId; // 获取传递的用户ID

    onMounted(() => {
      $.ajax({
        url: URL + "/api/otheruserinfo/",
        type: "get",
        data: {
          id: userId,
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          user.value = resp;
          console.log(resp);
          const date = new Date(user.value.registerTime);
          const year = date.getFullYear();
          const month = String(date.getMonth() + 1).padStart(2, "0"); // 月份从0开始
          const day = String(date.getDate()).padStart(2, "0");
          user.value.registerTime = `${year}-${month}-${day}`; // 格式化为"YYYY-MM"
        },
      });

      // 雷达图
      // 手动注册雷达图相关的组件
      Chart.register(
        RadialLinearScale,
        PointElement,
        LineElement,
        Filler,
        Tooltip,
        // Legend,
        RadarController
      );

      const ctx = document.getElementById("radarChart").getContext("2d");
      new Chart(ctx, {
        type: "radar",
        data: {
          labels: ["Strength", "Speed", "POWE", "TEST", "ID"],
          datasets: [
            {
              label: "Player Stats",
              data: [65, 59, 90, 81, 56],
              backgroundColor: "rgba(54, 162, 235, 0.2)",
              borderColor: "rgba(54, 162, 235, 1)",
              borderWidth: 1,
            },
          ],
        },
        options: {
          maintainAspectRatio: false, // 禁用保持宽高比
          aspectRatio: 2, // 设置宽高比，值越大图表越宽
          scales: {
            r: {
              angleLines: {
                display: false,
              },
              suggestedMin: 0,
              suggestedMax: 100,
            },
          },
          animation: {
            duration: 1000, // 动画持续时间
            easing: "easeInOutBounce", // 动画的效果
          },
        },
      });
    });

    return {
      labels,
      data,
      user,
      posts,
    };
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
.nav-pills .nav-link {
  background-color: #f8f9fa; /* 默认背景色 */
  color: #495057; /* 默认文本颜色 */
  font-size: 16px; /* 修改字体大小 */
  padding: 10px 20px; /* 调整内边距 */
  border-radius: 5px; /* 圆角效果 */
}

.nav-pills .nav-link.active {
  background-color: #007bff; /* 选中状态背景色 */
  color: #ffffff; /* 选中状态文本颜色 */
}

.userId {
  color: gray;
  font-size: 10px;
  /* text-align: center; */
}
</style>