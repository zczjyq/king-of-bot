<template>
  <div class="">
    <div class="d-flex align-items-center">
      <img
        :src="team.file_src"
        class="rounded"
        alt="team-logo"
        width="48"
        height="48"
      />
      <div class="ms-3">
        <h6 style="margin-top: 20px">战队: {{ team.teamName }}</h6>
        <p class="my-description">宣言: {{ team.teamSignature }}</p>
      </div>
    </div>

    <div class="mt-3" style="font-size: 14px">
      <p class="my-font">
        战队介绍:&nbsp;&nbsp;&nbsp;{{ team.teamDescription || "无" }}
      </p>
      <p class="my-font">
        队&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;长:&nbsp;&nbsp;&nbsp;<span
          class="text-primary"
          style="cursor: pointer"
          >{{ leaderName }}</span
        >
      </p>
      <p class="my-font">
        我的职位:&nbsp;&nbsp;&nbsp;
        <span class="my-red-back">{{ role }}</span>
      </p>
      <p class="my-font">
        积&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;分:&nbsp;&nbsp;&nbsp;{{
          team.teamRating
        }}
      </p>
      <p class="my-font">
        资&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;产:&nbsp;&nbsp;&nbsp;{{
          team.assets
        }}金币
      </p>
      <p class="my-font">创建时间:&nbsp;&nbsp;&nbsp;{{ team.createdAt }}</p>
      <p class="my-font">
        战队状态:&nbsp;&nbsp;&nbsp;
        <span :class="getStatusClass()">{{ team.teamStatus }}</span>
      </p>
    </div>
    <div class="d-flex justify-content-center">
      <button
        v-if="role == '队长' || role == '副队长'"
        type="button"
        class="btn btn-primary"
        data-bs-toggle="modal"
        data-bs-target="#exampleModal"
        style="font-weight: bold; font-size: 14px"
      >
        编辑 
      </button>
    </div>

    <TeamUpdate></TeamUpdate>
  </div>
</template>

<script>
import TeamUpdate from "@/components/popUpWindow/TeamUpdate.vue";
import { onMounted, ref } from "vue";
import $ from "jquery";
import { OSS, URL } from "@/utils/constants";
import { useStore } from "vuex";

export default {
  components: {
    TeamUpdate,
  },
  name: "TeamInfo",
  setup() {
    const store = useStore();
    const team = ref({});
    const leaderName = ref("");
    const role = store.state.user.teamRole;
    console.log(role);
    
    onMounted(() => {
      $.ajax({
        url: URL + "/api/team/getreviseinfo/",
        type: "post",
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          console.log(resp);

          team.value = resp.teamInfo;

          leaderName.value = resp.leaderName;
          team.value.file_src = OSS + resp.teamInfo.teamHead;
          if (team.value.teamStatus === 1) {
            team.value.teamStatus = "正常";
          } else {
            team.value.teamStatus = "解散";
          }

          // 格式化日期
          const date = new Date(team.value.createdAt);
          const year = date.getFullYear();
          const month = String(date.getMonth() + 1).padStart(2, "0"); // 月份从0开始
          const day = String(date.getDate()).padStart(2, "0");
          team.value.createdAt = `${year}-${month}-${day}`; // 格式化为"YYYY-MM"
        },
      });
    });
    return {
      team,
      role,
      leaderName,
    };
  },

  methods: {
    editTeam() {
      // 处理编辑按钮的逻辑
      console.log("Edit button clicked!");
    },
    getStatusClass() {
      return this.team.teamStatus === "正常"
        ? "my-green-back" // 正常状态的类
        : "my-red-back"; // 非正常状态的类
    },
  },
};
</script>

<style scoped>
.badge {
  font-size: 12px;
}

.my-font {
  font-size: 15px;
  color: #505050;
}
.my-description {
  font-size: 12px;
  color: #a7abc3;
}

.my-red-back {
  background-color: #f8d7da; /* 浅红色背景 */
  color: #e74c3c; /* 深红色文字 */
  /* border: 1px solid #e74c3c; 红色边框 */
  border-radius: 5px; /* 圆角 */
  padding: 2px 10px; /* 内边距 */
  font-size: 14px; /* 字体大小 */
  text-align: center; /* 居中 */
  display: inline-block; /* 使其成为内联块元素 */
  font-size: 11px;
}

.my-green-back {
  background-color: rgb(190, 238, 212); /* 浅红色背景 */
  color: #28c76f; /* 深红色文字 */
  /* border: 1px solid #e74c3c; 红色边框 */
  border-radius: 5px; /* 圆角 */
  padding: 2px 10px; /* 内边距 */
  font-size: 14px; /* 字体大小 */
  text-align: center; /* 居中 */
  display: inline-block; /* 使其成为内联块元素 */
  font-size: 11px;
}
</style>

