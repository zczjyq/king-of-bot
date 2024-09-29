<template>
  <div>
    <h4
      style="
        color: rgb(60, 60, 60);
        font-weight: bold;
        margin-left: 10px;
        margin-top: 20px;
        margin-bottom: 20px;
      "
    >
      修改密码
    </h4>

    <!-- 警告框 -->
    <div
      v-if="showAlert"
      class="alert custom-alert alert-dismissible fade show"
      role="alert"
    >
      <strong>提示!</strong> {{ alertMessage }}
      <button
        type="button"
        class="btn-close"
        @click="closeAlert"
        aria-label="Close"
      ></button>
    </div>
    <div
      v-if="showSuccessAlert"
      class="alert alert-success alert-dismissible fade show"
      role="alert"
    >
      <strong>提示!</strong> 修改成功
      <button
        type="button"
        class="btn-close"
        @click="closeAlert"
        aria-label="Close"
      ></button>
    </div>

    <div class="input text" id="">旧密码</div>
    <div class="input-group flex-nowrap input">
      <input
        type="password"
        class="form-control"
        placeholder=""
        aria-label="Old Password"
        aria-describedby="addon-wrapping"
        v-model="oldPassword"
        id="oldPassword"
      />
    </div>

    <div class="input text" id="">新密码</div>
    <div class="input-group flex-nowrap input">
      <input
        type="password"
        class="form-control"
        placeholder=""
        aria-label="New Password"
        aria-describedby="addon-wrapping"
        v-model="newPassword"
        id="newPassword"
      />
    </div>

    <div class="input text" id="">确认密码</div>
    <div class="input-group flex-nowrap input">
      <input
        type="password"
        class="form-control"
        placeholder=""
        aria-label="Confirm Password"
        aria-describedby="addon-wrapping"
        v-model="confirmPassword"
        id="confirmPassword"
      />
    </div>

    <button
      type="button"
      class="btn btn-primary input"
      style="font-weight: bold"
      @click="sendPasswordRevise"
    >
      确认
    </button>
  </div>
</template>

<script>
import { ref } from "vue";
import $ from "jquery";
import { useStore } from "vuex";
import URL from "@/store/constants.js";

export default {
  name: "PasswordRevise",
  setup() {
    let oldPassword = ref("");
    let newPassword = ref("");
    let confirmPassword = ref("");
    const store = useStore();

    // 控制警告框的显示
    const showAlert = ref(false);
    const showSuccessAlert = ref(false);
    const alertMessage = ref("");

    const closeAlert = () => {
      showAlert.value = false; // 关闭警告框
      showSuccessAlert.value = false; // 关闭警告框
    };

    const sendPasswordRevise = () => {
      $.ajax({
        url: URL + "/api/user/password/update/",
        data: {
          oldPassword: oldPassword.value,
          newPassword: newPassword.value,
          confirmPassword: confirmPassword.value,
        },
        type: "post",
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          console.log(resp);
          if (resp.error_message === "success") {
            showSuccessAlert.value = true; // 显示警告框
            showAlert.value = false; // 显示警告框
          } else {
            alertMessage.value = resp.error_message;
            showSuccessAlert.value = false; // 显示警告框
            showAlert.value = true; // 显示警告框
          }
        },
      });
    };

    return {
      showAlert,
      showSuccessAlert,
      alertMessage,
      sendPasswordRevise,
      closeAlert,
      oldPassword,
      newPassword,
      confirmPassword,
    };
  },
};
</script>

<style scoped>
.input {
  margin: 10px;
}
.text {
  color: rgb(80, 80, 80);
}
.custom-alert {
  background-color: #f8d7da; /* 自定义背景色 */
  color: #721c24; /* 自定义文本颜色 */
  border: 1px solid #f5c6cb; /* 自定义边框颜色 */
  border-radius: 0.5rem; /* 圆角 */
}
</style>
