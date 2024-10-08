<template>
  <table class="table align-middle custom-table">
    <thead>
      <tr>
        <th scope="col" class="table-name">ID</th>
        <th scope="col" class="table-name">成员</th>
        <th scope="col" class="table-name">类型</th>
        <th scope="col" class="table-name">积分</th>
        <th scope="col" class="table-name">金币</th>
        <th
          v-if="role === '队长' || role === '副队长'"
          scope="col"
          class="table-name"
        >
          操作
        </th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="(member, index) in team_members" :key="index + 1">
        <td scope="row" class="table-font">{{ index + 1 }}</td>
        <td>
          <div class="d-flex align-items-center">
            <img
              :src="member.photo"
              class="rounded-circle me-2"
              width="40"
              height="40"
            />
            <div href="#" class="table-font" style="color: #0081ff">
              {{ member.username }}
            </div>
          </div>
        </td>
        <td class="table-font">
          <span :class="getBadgeClass(member.role)" class="badge">{{
            member.role
          }}</span>
        </td>
        <td class="table-font">{{ member.rating }}</td>
        <td class="table-font">{{ member.coins }}</td>
        <td v-if="role === '队长' || role === '副队长'">
          <button class="btn btn-light btn-sm">
            <i class="bi bi-three-dots"></i>
          </button>
        </td>
      </tr>
    </tbody>
  </table>
</template>

<script>
import { onMounted } from "vue";
import { URL } from "@/utils/constants";
import $ from "jquery";
import { useStore } from "vuex";
import { ref } from "vue";
export default {
  setup() {
    const store = useStore();
    const teamId = ref(0); // 获取传递的用户ID
    let teamInfo = ref({});
    let leaderName = ref("");
    let team_members = ref({});
    const role = store.state.user.teamRole;
    console.log("teammember");
    
    console.log(role);
    
    onMounted(() => {

      $.ajax({
        url: URL + "/api/user/getTeamId/",
        type: "get",
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          teamId.value = resp;
          console.log(teamId.value);

          // 在第一个请求成功后发起第二个请求
          $.ajax({
            url: URL + "/api/team/info/",
            type: "get",
            data: {
              teamId: teamId.value,
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
        },
      });
    });
    return {
      team_members,role
    };
  },
  methods: {
    getBadgeClass(type) {
      if (type === "队长") {
        return "my-red-back";
      } else if (type === "成员") {
        return "my-green-back";
      }
      return "bg-secondary";
    },
  },
};
</script>

<style>
.table th,
.table td {
  vertical-align: middle;
}

.table-name {
  font-size: 11px;
  color: #212529;
  font-weight: 300;
}

.table-font {
  font-size: 15px;
  color: #212529;
}

.custom-table tbody tr {
  line-height: 4; /* 调整行高 */
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
  font-weight: 300;
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
  font-weight: 300;
}

.table {
  border-collapse: separate;
  border-spacing: 0; /* 让表格单元格之间的边距为0 */
}

.table th,
.table td {
  border-bottom: 1px solid #dee2e6; /* 设置全局的表格底部边框颜色 */
}

.custom-table tbody tr:last-child td {
  border-bottom: none; /* 隐藏最后一行的底部边框 */
}
</style>
