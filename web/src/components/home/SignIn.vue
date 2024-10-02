<template>
  <div class="sign-in-card card">
    <div class="sign-in-header">
      <span class="consecutive"
        >连续签到 <strong>{{ consecutiveDays }}</strong> 天</span
      >
      <span class="rules" @click="$emit('show-modal')">签到规则</span>
    </div>
    <div class="sign-in-body">
      <span class="total-days"
        >累计 <strong>{{ cumulativeDays }}</strong> 天</span
      >
      <button
        v-if="!isSigned"
        class="btn btn-primary signed-button"
        type="button"
        @click="SignIn"
      >
        签到
      </button>
      <button
        v-else
        class="btn btn-secondary signed-button"
        type="button"
        disabled
      >
        已签到
      </button>
    </div>
  </div>
</template>
  

<script setup>
import { ref, onMounted } from "vue"; // 确保你引入了正确的 Vue 函数
import $ from "jquery";
import { useStore } from "vuex";
import {URL} from "@/utils/constants.js";

const store = useStore();
let consecutiveDays = ref();
let cumulativeDays = ref();
let isSigned = ref(false);

const SignIn = () => {
  $.ajax({
    url: URL + "/api/user/signin/",
    type: "post",
    headers: {
      Authorization: "Bearer " + store.state.user.token,
    },
    success(resp) {
      console.log(resp);
      consecutiveDays.value = resp.consecutiveDays;
      cumulativeDays.value = resp.cumulativeDays;
      isSigned.value = resp.isSigned;
    },
  });
};
onMounted(() => {
  const GetSignInInfo = () => {
    $.ajax({
      url: URL + "/api/user/signin/get/",
      type: "get",
      headers: {
        Authorization: "Bearer " + store.state.user.token,
      },
      success(resp) {
        console.log(resp);

        consecutiveDays.value = resp.consecutiveDays;
        cumulativeDays.value = resp.cumulativeDays;
        isSigned.value = resp.isSigned;
      },
    });
  };
  GetSignInInfo();
});
</script>

<style scoped>
.sign-in-card {
  margin: 20px 10px;
  background: linear-gradient(to left, #b187ff, #e2a6ff); /* 渐变紫色背景 */
  border-radius: 15px; /* 圆角 */
  border: none; /* 去掉边框 */
  padding: 20px;
  color: white;
  height: 140px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1); /* 添加阴影 */
}

.sign-in-header {
  display: flex;
  justify-content: space-between;
}

.consecutive {
  font-size: 16px;
  font-weight: bold;
}

.consecutive strong {
  font-size: 20px;
}

.rules {
  font-size: 12px;
  opacity: 0.7;
}

.sign-in-body {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.total-days {
  font-size: 18px;
  font-weight: bold;
}

.total-days strong {
  font-size: 24px;
}

.signed-button {
  background-color: rgba(255, 255, 255, 0.3);
  border: none;
  padding: 5px 15px;
  border-radius: 20px;
  color: white;
  cursor: not-allowed;
}

.signed-button {
  background-color: rgba(255, 255, 255, 0.8);
  border: none;
  padding: 5px 15px;
  border-radius: 20px;
  color: #6200ea; /* 改为按钮文本的紫色 */
  cursor: pointer;
}

.signed-button:hover {
  background-color: white; /* 悬停效果 */
}
</style>



