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
        <td
          v-if="
            (role === '队长' || role === '副队长') && member.role !== '队长'
          "
        >
          <!-- Example single danger button -->
          <div class="btn-group">
            <button
              type="button"
              class="btn btn-sm dropdown-toggle"
              data-bs-toggle="dropdown"
              aria-expanded="false"
            ></button>
            <ul class="dropdown-menu custom-dropdown-menu">
              <!-- 添加自定义类 -->
              <li>
                <a class="dropdown-item" href="#">
                  <div
                    data-bs-toggle="modal"
                    data-bs-target="#roleModal"
                    @click="openRoleModal(member.id, member.role)"
                  >
                    编辑
                  </div></a
                >
              </li>
              <li><a class="dropdown-item" href="#">转让</a></li>
              <li><hr class="dropdown-divider" /></li>
              <li>
                <a class="dropdown-item" href="#" style="color: #ea5455"
                  >移除</a
                >
              </li>
            </ul>
          </div>
        </td>
      </tr>
    </tbody>
  </table>
  <!-- 模态框 -->
  <div
    class="modal fade"
    id="roleModal"
    tabindex="-1"
    aria-labelledby="exampleModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-dialog-centered modal-sm">
      <!-- 添加 modal-sm 类 -->
      <div class="modal-content">
        <div class="modal-header">
          <h1 class="modal-title fs-5 model-title" id="exampleModalLabel">
            角色设置
          </h1>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>
        <div class="modal-body">
          <select id="role-select" v-model="selectedRole" class="form-select">
            <option value="副队长" class="model-select">副队长</option>
            <option value="成员" class="model-select">成员</option>
          </select>
        </div>
        <div class="modal-footer">
          <button
            type="button"
            class="btn btn-secondary"
            data-bs-dismiss="modal"
          >
            关闭
          </button>
          <button
            type="button"
            class="btn btn-primary"
            data-bs-dismiss="modal"
            @click="saveRole"
          >
            保存
          </button>
        </div>
      </div>
    </div>
  </div>
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
    // 新增状态来存储选择的角色和用户 ID
    const selectedRole = ref("副队长");
    const selectedMemberId = ref(null); // 用于存储选中的用户 ID

    onMounted(() => {
      refreshData();
    });

    const refreshData = () => {
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
    };

    const openRoleModal = (memberId, currentRole) => {
      selectedMemberId.value = memberId; // 存储用户 ID
      selectedRole.value = currentRole; // 设置当前角色
    };

    const saveRole = () => {
      $.ajax({
        url: URL + "/api/user/updateRole/", // 假设这是你的后端接口
        type: "post",
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        data: {
          userId: selectedMemberId.value,
          newRole: selectedRole.value,
        },
        success(response) {
          // 处理成功逻辑，比如刷新成员列表
          refreshData();
          console.log("成功修改");

          console.log(response);
        },
        error() {
          refreshData();
        }
      });
    };
    return {
      team_members,
      role,
      openRoleModal,
      saveRole,
      selectedRole, // 返回 selectedRole 以便在模态框中使用
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
.model-title {
  font-size: 17px;
  font-weight: bold;
  color: #505050;
}

.model-select {
  color: #495057;
  font-size: 14px;
}

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

/* 进一步缩小行间距 */
.custom-dropdown-menu .dropdown-item {
  padding: 2px 8px; /* 进一步减小上下和左右的 padding */
  font-size: 14px; /* 如果需要更紧凑的字体大小 */
  line-height: 2.2; /* 调整行高，减少行间距 */
}

/* 缩小分隔线的上下间距 */
.custom-dropdown-menu .dropdown-divider {
  margin: 2px 0; /* 减小分隔线的上下 margin */
}
button {
  position: relative;
  padding: 10px 20px;
  border-radius: 7px;
  border: 1px solid rgb(61, 106, 255);
  font-size: 14px;
  text-transform: uppercase;
  font-weight: 600;
  letter-spacing: 2px;
  background: transparent;
  color: #fff;
  overflow: hidden;
  box-shadow: 0 0 0 0 transparent;
  -webkit-transition: all 0.2s ease-in;
  -moz-transition: all 0.2s ease-in;
  transition: all 0.2s ease-in;
}

button:hover {
  background: rgb(61, 106, 255);
  box-shadow: 0 0 30px 5px rgba(0, 142, 236, 0.815);
  -webkit-transition: all 0.2s ease-out;
  -moz-transition: all 0.2s ease-out;
  transition: all 0.2s ease-out;
}

button:hover::before {
  -webkit-animation: sh02 0.5s 0s linear;
  -moz-animation: sh02 0.5s 0s linear;
  animation: sh02 0.5s 0s linear;
}

button::before {
  content: "";
  display: block;
  width: 0px;
  height: 86%;
  position: absolute;
  top: 7%;
  left: 0%;
  opacity: 0;
  background: #fff;
  box-shadow: 0 0 50px 30px #fff;
  -webkit-transform: skewX(-20deg);
  -moz-transform: skewX(-20deg);
  -ms-transform: skewX(-20deg);
  -o-transform: skewX(-20deg);
  transform: skewX(-20deg);
}

@keyframes sh02 {
  from {
    opacity: 0;
    left: 0%;
  }

  50% {
    opacity: 1;
  }

  to {
    opacity: 0;
    left: 100%;
  }
}

button:active {
  box-shadow: 0 0 0 0 transparent;
  -webkit-transition: box-shadow 0.2s ease-in;
  -moz-transition: box-shadow 0.2s ease-in;
  transition: box-shadow 0.2s ease-in;
}
</style>
