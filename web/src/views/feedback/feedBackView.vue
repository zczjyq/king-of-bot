<template>
  <div
    style="
      background-color: rgb(243, 245, 247);
      padding: 15px;
      border-radius: 10px;
      margin: 40px auto; /* 设置为 auto，自动分配左右边距 */
      max-width: 900px;
      margin-bottom: 40px;
    "
    class="container content-filed"
  >
    <div class="feedback-container-title card">
      <h3 style="font-weight: bold">ZCZJYQ 平台异常反馈</h3>
      <div style="font-size: small">
        本收集表仅收集 zczjyq.com 相关的问题，如有其他问题，请私信QQ群管理员。
      </div>
    </div>
    <br />
    <!-- // 告警 -->
    <div
      v-if="showAlertYellow"
      class="alert alert-warning alert-dismissible fade show"
      role="alert"
    >
      <strong>警告!</strong> {{ alertMessage }}
      <button
        type="button"
        class="btn-close"
        @click="closeAlert"
        aria-label="Close"
      ></button>
    </div>
    <div
      v-if="showAlertRed"
      class="alert alert-danger alert-dismissible fade show"
      role="alert"
    >
      <strong>错误!</strong> {{ alertMessage }}
      <button
        type="button"
        class="btn-close"
        @click="closeAlert"
        aria-label="Close"
      ></button>
    </div>
    <div
      v-if="showAlertGreen"
      class="alert alert-success alert-dismissible fade show"
      role="alert"
    >
      <strong>成功!</strong> {{ alertMessage }}
      <button
        type="button"
        class="btn-close"
        @click="closeAlert"
        aria-label="Close"
      ></button>
    </div>
    <div class="feedback-container card">
      <form @submit.prevent="submitFeedback">
        <div class="mb-3">
          <label for="username" class="form-label">01 平台用户名</label>
          <input
            type="text"
            class="form-control"
            v-model="username"
            id="username"
            required
          />
        </div>
      </form>
    </div>
    <br />
    <div class="feedback-container card">
      <div class="mb-3">
        <label for="teamName" class="form-label">02 出现问题的模块</label>
        <select
          class="form-select"
          v-model="feedbackType"
          id="feedbackType"
          required
        >
          <option value="" disabled selected>请选择问题类型</option>
          <option value="用户功能">用户功能</option>
          <option value="对战问题">对战问题</option>
          <option value="支付相关">支付相关</option>
          <option value="商业合作">商业合作</option>
          <option value="其他">其他</option>
        </select>
      </div>
    </div>
    <br />
    <div class="feedback-container card">
      <div class="mb-3">
        <label for="description" class="form-label">03 问题简述</label>
        <div class="jianshu">
          <div>常见问题：</div>
          <ul>
            <li>
              无法接受短信验证码 =>
              检查是否手机号输入错误，或者手机号为被风控的手机号。
            </li>
            <li>
              无法访问也无法链接SSH =>
              检查白名单，并确保白名单IP不为192.168.x.x或者172.16.x.x或10.x.x.x
            </li>
            <li>
              可以访问网页但无法链接SSH => 我们的SSH端口通常是2222而不是22。
            </li>
          </ul>
        </div>
        <textarea
          class="form-control"
          v-model="description"
          id="description"
          rows="3"
          required
        ></textarea>
        <div class="jianshu">此题涉及隐私信息，请注意核实收集者身份。</div>
      </div>
    </div>
    <br />
    <div class="feedback-container card">
      <div class="mb-3">
        <label for="contact" class="form-label">04 邮箱</label>
        <input
          type="text"
          class="form-control"
          v-model="contact"
          id="contact"
          required
        />
        <small class="form-text text-muted"
          >QQ邮箱，163邮箱，mail邮箱 均可 例如 xxxxx@qq.com</small
        >
      </div>
    </div>
    <br />
    <div class="d-flex justify-content-center">
      <button
        @click="submitFeedback"
        type="submit"
        class="btn btn-primary"
        style="margin: 10px auto"
      >
        &emsp;&emsp;&emsp;&emsp;提交&emsp;&emsp;&emsp;&emsp;
      </button>
    </div>
  </div>
</template>
  
  <script>
import { ref } from "vue";
import $ from "jquery";
import { useStore } from "vuex";
import {URL} from "@/utils/constants.js";

export default {
  setup() {
    let username = ref("");
    let feedbackType = ref("");
    let description = ref("");
    let contact = ref("");
    const store = useStore();
    const showAlertYellow = ref(false);
    const showAlertGreen = ref(false);
    const showAlertRed = ref(false);
    const alertMessage = ref("");

    const submitFeedback = () => {
      if (username.value === "") {
        alertMessage.value = "请输入用户名";
        showAlertRed.value = false; // 隐藏错误框
        showAlertYellow.value = true; // 隐藏警告框
        showAlertGreen.value = false; // 显示成功框
        return;
      }
      if (feedbackType.value === "") {
        alertMessage.value = "请选择问题类型";
        showAlertRed.value = false; // 隐藏错误框
        showAlertYellow.value = true; // 隐藏警告框
        showAlertGreen.value = false; // 显示成功框
        return;
      }
      if (description.value === "") {
        alertMessage.value = "请输入问题简述";
        showAlertRed.value = false; // 隐藏错误框
        showAlertYellow.value = true; // 隐藏警告框
        showAlertGreen.value = false; // 显示成功框
        return;
      }
      if (contact.value === "") {
        alertMessage.value = "请输入邮箱";
        showAlertRed.value = false; // 隐藏错误框
        showAlertYellow.value = true; // 隐藏警告框
        showAlertGreen.value = false; // 显示成功框
        return;
      }
      // 发送请求
      $.ajax({
        url: URL + "/api/feedback/",
        type: "get",
        data: {
          username: username.value,
          feedbackType: feedbackType.value,
          description: description.value,
          contact: contact.value,
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          // 正确返回
          if (resp.error_message === "success") {
            alertMessage.value = "反馈提交成功！";
            showAlertRed.value = false; // 隐藏错误框
            showAlertYellow.value = false; // 隐藏警告框
            showAlertGreen.value = true; // 显示成功框
            username.value = "";
            feedbackType.value = "";
            description.value = "";
            contact.value = "";
          } else { // 邮箱填写错误
            alertMessage.value = resp.error_message;
            showAlertRed.value = false; // 隐藏错误框
            showAlertYellow.value = true; // 隐藏警告框
            showAlertGreen.value = false; // 显示成功框
          }
        },
        error() {
          alertMessage.value = "请求失败，请重试！";
          showAlertRed.value = true; // 隐藏错误框
          showAlertYellow.value = false; // 隐藏警告框
          showAlertGreen.value = false; // 显示成功框
        },
      });
    };

    const closeAlert = () => {
      showAlertRed.value = false; // 隐藏错误框
      showAlertYellow.value = false; // 隐藏警告框
      showAlertGreen.value = false; // 显示成功框
    };

    return {
      username,
      feedbackType,
      description,
      contact,
      submitFeedback,
      showAlertYellow,
      showAlertGreen,
      showAlertRed,
      alertMessage,
      closeAlert,
    };
  },
};
</script>
  
  <style scoped>
.feedback-container-title {
  padding: 25px;
  border: none;
}

.feedback-container {
  padding-top: 10px;
  padding-left: 25px;
  padding-right: 25px;
  border: none;
}

.common-issues {
  margin-top: 20px;
  padding: 10px;
  border: 1px solid #e0e0e0;
  border-radius: 5px;
}

.title {
  font-size: 24px;
}
.jianshu {
  font-size: x-small;
  color: gray;
}
</style>
  