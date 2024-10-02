<template>
  <ContentField>
    <button
      type="button"
      hover-class="button-hover"
      class="btn btn-secondary btn-sm"
      @click="testAddSignature"
    >
      测试增加签名
    </button>
    <input
      v-model="signature"
      placeholder=""
      placeholder-class="input-placeholder"
    />
    <br />
    <button
      type="button"
      hover-class="button-hover"
      class="btn btn-secondary btn-sm"
      @click="testgetSignature"
    >
      测试查询签名
    </button>
    <svg
      xmlns="http://www.w3.org/2000/svg"
      width="16"
      height="16"
      fill="currentColor"
      class="bi bi-0-circle"
      viewBox="0 0 16 16"
    >
      <path
        d="M7.988 12.158c-1.851 0-2.941-1.57-2.941-3.99V7.84c0-2.408 1.101-3.996 2.965-3.996 1.857 0 2.935 1.57 2.935 3.996v.328c0 2.408-1.101 3.99-2.959 3.99ZM8 4.951c-1.008 0-1.629 1.09-1.629 2.895v.31c0 1.81.627 2.895 1.629 2.895s1.623-1.09 1.623-2.895v-.31c0-1.8-.621-2.895-1.623-2.895Z"
      />
      <path
        d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0ZM1 8a7 7 0 1 0 14 0A7 7 0 0 0 1 8Z"
      />
    </svg>
    <br />
    <button
      type="button"
      hover-class="button-hover"
      class="btn btn-secondary btn-sm"
      @click="testGetRaking"
    >
      测试获取排名
    </button>
    <br />
    <button
      type="button"
      hover-class="button-hover"
      class="btn btn-secondary btn-sm"
      @click="testGetOtherUser"
    >
      测试获取其他用户信息
    </button>
    <br />
    <button
      type="button"
      hover-class="button-hover"
      class="btn btn-secondary btn-sm"
      @click="testSendMail"
    >
      发送邮件
    </button>
    <br />
    <button
      type="button"
      hover-class="button-hover"
      class="btn btn-secondary btn-sm"
      @click="testGetSignInInfo"
    >
      获取签到信息
    </button>
    <button
      type="button"
      hover-class="button-hover"
      class="btn btn-secondary btn-sm"
      @click="testPostSignIn"
    >
      获取签到
    </button>
  </ContentField>
</template>
      
      <script>
import ContentField from "../components/ContentField";
import $ from "jquery";
import { useStore } from "vuex";
import {URL} from "@/utils/constants.js";
import { ref } from "vue";

export default {
  name: "UserProfileView",
  components: {
    ContentField,
  },

  setup() {
    const store = useStore();
    let signature = ref("");
    const testAddSignature = () => {
      $.ajax({
        url: URL + "/api/user/signature/update/",
        data: {
          signature: signature.value,
        },
        type: "post",

        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          console.log(resp);
        },
      });
    };
    const testgetSignature = () => {
      $.ajax({
        url: URL + "/api/user/signature/get/",
        type: "get",

        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          console.log(resp);
        },
      });
    };
    const testGetRaking = () => {
      $.ajax({
        url: URL + "/api/user/ranking/get/",
        type: "get",

        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          console.log(resp);
        },
      });
    };
    const testGetOtherUser = () => {
      $.ajax({
        url: URL + "/api/otheruserinfo/",
        type: "get",
        data: {
          id: "3",
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          console.log(resp);
        },
      });
    };
    const testSendMail = () => {
      $.ajax({
        url: URL + "/api/feedback/",
        type: "get",
        data: {
          username: "123",
          email: "123@qq.com",
          content: "123",
          description: "123",
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },

        success(resp) {
          console.log(resp);
        },
      });
    };
    const testGetSignInInfo = () => {
      $.ajax({
        url: URL + "/api/user/signin/get/",
        type: "get",
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          console.log(resp);
        },
      });
    };

    const testPostSignIn = () => {
      $.ajax({
        url: URL + "/api/user/signin/",
        type: "post",
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          console.log(resp);
        },
      });
    };

    return {
      testPostSignIn,
      testGetSignInInfo,
      testAddSignature,
      testgetSignature,
      testGetRaking,
      testGetOtherUser,
      testSendMail,
      signature,
    };
  },
};
</script>
      
  <style scoped>
</style>

<!-- <template>
  <div>
    <input type="file" @change="onFileChange" />
    <button @click="uploadImage">上传图片</button>
  </div>
</template>

<script>
import { ref } from "vue";
import $ from "jquery"; // 如果还没有安装 jQuery，请运行 npm install jquery
import { useStore } from "vuex";

export default {
  setup() {
    const selectedFile = ref(null); // 存储选中的文件
    const ossData = ref(null); // 存储从后端获取的 OSS 上传凭证
    const store = useStore();

    // 当用户选择文件时，触发这个方法
    const onFileChange = (event) => {
      selectedFile.value = event.target.files[0];
    };

    // 获取 OSS 上传凭证
    const getOssPolicy = () => {
      return new Promise((resolve, reject) => {
        $.ajax({
          url: "http://localhost:3001/oss/policy", // 替换为您后端的 OSS 签名接口
          type: "GET",
          headers: {
            Authorization: "Bearer " + store.state.user.token, // 若后端需要 token 验证
          },
          success: function (response) {
            resolve(response); // 返回 OSS 上传凭证
          },
          error: function (error) {
            console.error("无法获取 OSS 签名", error);
            reject(error);
          },
        });
      });
    };

    // 上传图片到阿里云 OSS
    const uploadImage = async () => {
      if (!selectedFile.value) {
        alert("请先选择文件");
        return;
      }

      // 获取 OSS 上传凭证
      try {
        ossData.value = await getOssPolicy();
        console.log("OSS 上传凭证:", ossData.value);
      } catch (error) {
        alert("无法获取 OSS 签名");
        return;
      }

      if (!ossData.value) {
        alert("无法获取 OSS 签名");
        return;
      }

      // 构建 FormData，携带文件和 OSS 签名信息
      const formData = new FormData();
      formData.append("key", ossData.value.dir + selectedFile.value.name); // 上传路径
      formData.append("OSSAccessKeyId", ossData.value.accessid); // Access Key ID
      formData.append("policy", ossData.value.policy); // 上传策略
      formData.append("Signature", ossData.value.signature); // 签名
      formData.append("success_action_status", "200"); // 返回状态码
      formData.append("file", selectedFile.value); // 上传的文件
      console.log("开始上传图片");

      // 使用 $.ajax 将图片上传到阿里云 OSS
      $.ajax({
        url: ossData.value.host, // 阿里云 OSS 的上传地址
        type: "POST",
        data: formData,
        processData: false, // 告诉 jQuery 不要处理数据
        contentType: false, // 告诉 jQuery 不要设置内容类型
        success(resp) {
          console.log("图片上传成功:", resp);
          alert("图片上传成功");
        },
        error(resp) {
console.log(resp);

        },
      });
    };

    return {
      onFileChange,
      uploadImage,
    };
  },
};
</script> -->
