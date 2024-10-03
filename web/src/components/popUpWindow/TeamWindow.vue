<template>
  <div
    class="modal fade"
    id="exampleModal"
    tabindex="-1"
    aria-labelledby="exampleModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">创建战队</h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>

        <div class="modal-body">
          <!-- 成功提示框 -->
          <div v-if="successBool" class="alert alert-success" role="alert">
            创建成功！
          </div>
          <div class="alert alert-warning" role="alert">
            创建战队将扣除10金币!
          </div>

          <!-- 圆形头像图片，支持上传 -->
          <div
            class="image-upload-container text-center my-3 d-flex justify-content-center align-items-center"
          >
            <label for="file-input">
              <div class="upload-icon">
                <img
                  id="team-avatar"
                  src="@/assets/img/usercenter/teamAvatar.png"
                  class="rounded-circle"
                  alt="avatar"
                  style="width: 100px; height: 100px; object-fit: cover"
                />
                <i class="camera-icon fas fa-camera"></i>
              </div>
            </label>
            <input
              id="file-input"
              type="file"
              accept="image/*"
              style="display: none"
              @change="previewImage"
            />
          </div>

          <!-- 表单部分 -->
          <form style="margin: 20px 60px">
            <div class="mb-3">
              <label for="teamName" class="form-label">战队名称</label>
              <input
                type="text"
                class="form-control"
                id="teamName"
                placeholder="请输入战队名称"
                v-model="teamName"
              />
            </div>
            <div v-if="errorBool" style="color: red; font-size: 12px">
              {{ errorMessage }}
            </div>

            <div class="mb-3">
              <label for="teamSlogan" class="form-label">战队宣言</label>
              <input
                type="text"
                class="form-control"
                id="teamSlogan"
                placeholder="请输入战队宣言"
                v-model="teamSignature"
              />
            </div>

            <div class="mb-3">
              <label for="teamDescription" class="form-label">战队介绍</label>
              <textarea
                class="form-control"
                id="teamDescription"
                rows="3"
                placeholder="请输入战队介绍"
                v-model="teamDescription"
              ></textarea>
            </div>
          </form>
        </div>

        <div class="modal-footer">
          <button
            type="button"
            class="btn btn-secondary"
            data-bs-dismiss="modal"
          >
            Close
          </button>
          <button type="button" class="btn btn-primary" @click="uploadImage">
            创建
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { ref } from "vue";
import { useStore } from "vuex";
import { uploadImage } from "@/utils/upload.js"; // 导入封装的上传函数
import $ from "jquery";
import { URL } from "@/utils/constants.js";

export default {
  setup() {
    const selectedFile = ref(null); // 存储选中的文件
    const store = useStore();
    const teamName = ref("");
    const teamSignature = ref("");
    const teamDescription = ref("");
    let errorBool = ref(false);
    let errorMessage = ref("");
    let successBool = ref(false);
    let file_src = ref("");

    const previewImage = (event) => {
      const file = event.target.files[0];
      selectedFile.value = file;
      if (file) {
        const reader = new FileReader();
        reader.onload = (e) => {
          document.getElementById("team-avatar").src = e.target.result;
        };
        reader.readAsDataURL(file);
      }
    };

    const handleUploadImage = async () => {
      // 将 $.ajax 包装成 Promise
      const getTeamList = () => {
        return new Promise((resolve, reject) => {
          $.ajax({
            url: URL + "/api/team/check/",
            data: {
              teamName: teamName.value,
            },
            type: "post",
            headers: {
              Authorization: "Bearer " + store.state.user.token,
            },
            success(resp) {
              console.log(resp);

              if (resp.error_message !== "success") {
                errorMessage.value = resp.error_message;
                console.log(errorMessage);

                errorBool.value = true;
                reject(resp.error_message); // 请求失败，返回错误
              } else {
                console.log("没有重复的");

                resolve(resp); // 请求成功，返回响应数据
              }
            },
            error(err) {
              reject(err); // 捕获请求错误
            },
          });
        });
      };

      // 先等待 getTeamList 完成再继续后面的操作
      try {
        const resp = await getTeamList();
        console.log("获取列表成功:", resp);
        if (resp.error_message !== "success") return; // 如果获取列表失败，则不执行后面的逻辑
      } catch (error) {
        console.error("获取列表失败:", error);
        return; // 请求失败时不执行后面的逻辑
      }

      // 继续执行图片上传
      try {
        const resp = await uploadImage(selectedFile.value, store);
        console.log(resp.file);
        file_src.value = resp.file;

        // alert("图片上传成功");
      } catch (error) {
        alert("图片上传失败");
        return; // 如果图片上传失败，则不执行后面的逻辑
      }

      // 创建战队
      $.ajax({
        url: URL + "/api/team/create/",
        data: {
          teamName: teamName.value,
          teamSignature: teamSignature.value,
          teamDescription: teamDescription.value,
          src: file_src.value,
        },
        type: "post",
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          if (resp.error_message === "success") {
            errorBool.value = false;
            successBool.value = true;
            teamDescription.value = "";
            teamName.value = "";
            teamSignature.value = "";
            setTimeout(() => {
              successBool.value = false;
            }, 5000); // 3秒后 successBool 变为 false
          }
        },
      });
    };

    return {
      previewImage,
      uploadImage: handleUploadImage,
      errorBool,
      errorMessage,
      teamName,
      teamSignature,
      teamDescription,
      successBool,
    };
  },
};
</script>

<style scoped>
.image-upload-container {
  position: relative;
  display: inline-block;
}

.upload-icon img {
  cursor: pointer;
  transition: opacity 0.3s ease;
}

.camera-icon {
  position: absolute;
  top: 35%;
  left: 35%;
  font-size: 30px;
  color: rgba(255, 255, 255, 0.7);
  display: none;
  cursor: pointer;
}

.upload-icon:hover img {
  opacity: 0.7;
}

.upload-icon:hover .camera-icon {
  display: block;
}
</style>