<template>
  <div class="container mt-5">
    <!-- 第一部分：战队信息 -->
    <div class="row">
      <div class="col-md-8">
        <div class="card p-4 mb-3">
          <!-- 右上角按钮 -->

          <div class="d-flex align-items-center">
            <img
              :src="OSS + teamInfo.teamHead"
              class="rounded"
              alt="team-logo"
              width="48"
              height="48"
            />
            <div class="ms-3" style="margin-top: 10px">
              <div class="my-gray team-name" style="margin-bottom: 8px">
                战队: {{ teamInfo.teamName }}
              </div>
              <p class="text-muted my-lightgray" style="font-size: 12px">
                宣言：{{ teamInfo.teamSignature }}
              </p>
            </div>
            <button
              class="btn btn-outline-primary ms-auto"
              @click="handleClick"
            >
              加入
            </button>
          </div>

          <div class="mt-4 my-gray" style="font-size: 15px">
            <p>战队介绍:&nbsp;&nbsp;{{ teamInfo.teamDescription }}</p>
            <p>
              队&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;长:&nbsp;&nbsp;<span
                class="my-blue"
                style="cursor: pointer"
                @click="getId(teamInfo.teamLeaderId)"
                >{{ leaderName }}</span
              >
            </p>

            <p>
              积&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;分:&nbsp;&nbsp;{{
                teamInfo.teamRating
              }}
            </p>
            <p>排位分数:&nbsp;&nbsp;{{ teamInfo.ranking }}</p>
            <p>创建时间:&nbsp;&nbsp;{{ teamInfo.createdAt }}</p>
            <p>
              战队状态:&nbsp;&nbsp;
              <span v-if="teamInfo.teamStatus == 1" class="badge bg-success"
                >正常</span
              >
            </p>
          </div>
          <div style="border-bottom: 1px solid gray; width: 100%"></div>

          <!-- 战队成就 -->
          <!-- <div class="mt-4 d-flex justify-content-between">
            <div
              v-for="achievement in teamInfo.achievements"
              :key="achievement.name"
              class="text-center"
            >
              <img :src="achievement.icon" alt="achievement-icon" width="50" />
              <p>{{ achievement.name }}</p>
            </div>
          </div> -->
        </div>
      </div>

      <!-- 第二部分：战队成员列表 -->
      <div class="col-md-4">
        <div class="card p-4 mb-3">
          <div
            class="my-gray"
            style="font-size: 15px; margin: 0 auto; font-weight: bold"
          >
            战队成员
          </div>
          <ul class="list-group">
            <li
              v-for="member in team_members"
              :key="member.id"
              class="list-group-item d-flex justify-content-between align-items-center"
              style="border: none"
            >
              <div class="d-flex align-items-center">
                <img
                  :src="member.photo"
                  class="rounded-circle me-2"
                  width="50"
                  height="50"
                />
                <div>
                  <strong>{{ member.name }}</strong>
                  <p class="mb-0">
                    <span class="badge bg-info">{{ member.role }}</span>
                  </p>
                </div>
              </div>
              <span class="badge bg-warning text-dark">{{ member.score }}</span>
            </li>
          </ul>
        </div>
      </div>
    </div>

    <!-- 第三部分：解题动态 -->
    <!-- <div class="card p-4 mt-3">
      <h5>解题动态</h5>
      <ul class="list-group">
        <li
          v-for="activity in activities"
          :key="activity.id"
          class="list-group-item"
        >
          {{ activity.name }} 破译了 {{ activity.task }} -
          {{ activity.daysAgo }}天前
        </li>
      </ul>
    </div> -->
  </div>
</template>
  
  <script>
import { onMounted } from "vue";
import router from "../../router/index";
import { useStore } from "vuex";
import { ref } from "vue";
import $ from "jquery";
import { URL, OSS } from "@/utils/constants.js";
import { useRoute } from "vue-router";

export default {
  setup() {
    const store = useStore();
    let teamInfo = ref({});
    let leaderName = ref("");
    let team_members = ref({});
    const route = useRoute();
    const teamId = route.params.teamId; // 获取传递的用户ID
    onMounted(() => {
      $.ajax({
        url: URL + "/api/team/info/",
        type: "get",
        data: {
          teamId: teamId,
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          teamInfo.value = resp.team;
          leaderName.value = resp.leaderName;
          team_members.value = resp.team_members;
          console.log(resp);

          
        },
      });
    });
    // 用于跳转页面
    const getId = (id) => {
      router.push({
        params: { userId: id },
        name: "usercenter",
      });
    };
    return {
      getId,
      teamInfo,
      leaderName,
      team_members,
      OSS,
    };
  },

  data() {
    // return {
    //   teamInfo: {
    //     photo: "https://via.placeholder.com/100",
    //     name: "WhiteHat",
    //     slogan: "渗透人，万为上人",
    //     website: "https://www.whlhc.top/",
    //     leader: "dotast",
    //     score: 7645,
    //     ranking: "0.00",
    //     createdAt: "2020-12-08 22:36:59",
    //     status: "正常",
    //     leader_id: 1,
    //     achievements: [
    //       { name: "AWD公测赛优秀奖", icon: "https://via.placeholder.com/50" },
    //       { name: "AWD-S1耀辉钻石", icon: "https://via.placeholder.com/50" },
    //       { name: "AWD-S2荣耀黄金", icon: "https://via.placeholder.com/50" },
    //     ],
    //   },
    //   teamMembers: [
    //     {
    //       id: 1,
    //       name: "xwhat",
    //       role: "成员",
    //       score: 305,
    //       photo: "https://via.placeholder.com/50",
    //     },
    //     {
    //       id: 2,
    //       name: "dotast",
    //       role: "队长",
    //       score: 5000,
    //       photo: "https://via.placeholder.com/50",
    //     },
    //     {
    //       id: 3,
    //       name: "cheyenne",
    //       role: "成员",
    //       score: 5035,
    //       photo: "https://via.placeholder.com/50",
    //     },
    //     {
    //       id: 4,
    //       name: "wjhwjhn",
    //       role: "副队长",
    //       score: 3225,
    //       photo: "https://via.placeholder.com/50",
    //     },
    //     {
    //       id: 5,
    //       name: "Y4tacker",
    //       role: "副队长",
    //       score: 640,
    //       photo: "https://via.placeholder.com/50",
    //     },
    //     {
    //       id: 6,
    //       name: "atao",
    //       role: "成员",
    //       score: 0,
    //       photo: "https://via.placeholder.com/50",
    //     },
    //     {
    //       id: 7,
    //       name: "spaceman",
    //       role: "成员",
    //       score: 360,
    //       photo: "https://via.placeholder.com/50",
    //     },
    //     {
    //       id: 8,
    //       name: "bit",
    //       role: "成员",
    //       score: 2255,
    //       photo: "https://via.placeholder.com/50",
    //     },
    //     {
    //       id: 9,
    //       name: "NN来了",
    //       role: "成员",
    //       score: 25,
    //       photo: "https://via.placeholder.com/50",
    //     },
    //   ],
    //   activities: [
    //     { id: 1, name: "cheyenne", task: "奇奇怪怪的语言", daysAgo: 4 },
    //     { id: 2, name: "cheyenne", task: "Find_password", daysAgo: 4 },
    //   ],
    // };
  },
  methods: {
    formatDate(dateString) {
      return dateString.split(" ")[0]; // 只显示日期部分
    },
  },
};
</script>
  
  <style scoped>
img {
  object-fit: cover;
}
.my-lightgray {
  color: #a7abc3;
}
.my-gray {
  color: #505050;
}
.team-name {
  font-size: 17px;
}
.my-blue {
  color: #0081ff;
}
.my-gray p {
  margin-bottom: 20px; /* 或者根据你需要调整的具体数值 */
}
</style>
  