<template>
  <div
    class="modal fade"
    id="exampleModal"
    tabindex="-1"
    aria-labelledby="TeamUpdate"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="TeamUpdate">修改战队</h5>
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
            修改成功！
          </div>

          <!-- 圆形头像图片，支持上传 -->
          <div
            class="image-upload-container text-center my-3 d-flex justify-content-center align-items-center"
          >
            <label for="file-input">
              <div class="upload-icon">
                <img
                  id="team-avatar"
                  :src="file_src"
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
            修改
          </button>
        </div>
      </div>
    </div>
  </div>
</template>
  
  <script>
import { ref, onMounted } from "vue";
import { useStore } from "vuex";
import { uploadImage } from "@/utils/upload.js"; // 导入封装的上传函数
import $ from "jquery";
import { URL, OSS } from "@/utils/constants.js";

export default {
  setup() {
    const selectedFile = ref(null); // 存储选中的文件
    const store = useStore();
    let teamName = ref("");
    let teamSignature = ref("");
    let teamDescription = ref("");
    let errorBool = ref(false);
    let errorMessage = ref("");
    let successBool = ref(false);
    let file_src = ref("");
    let teamInfo = ref("");
    let src = ref("");

    onMounted(() => {
      $.ajax({
        url: URL + "/api/team/getreviseinfo/",
        type: "post",
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          teamName.value = resp.teamInfo.teamName;
          teamSignature.value = resp.teamInfo.teamSignature;
          teamDescription.value = resp.teamInfo.teamDescription;
          file_src.value = OSS + resp.teamInfo.teamHead;
          teamInfo.value = resp.teamInfo;

          console.log(resp);
        },
      });
    });

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

    // 发送改照片的相关请求
    const handleUploadImage = async () => {
      // 继续执行图片上传
      try {
        const resp = await uploadImage(selectedFile.value, store);
        console.log(resp.file);
        file_src.value = OSS + resp.file;
        src.value = resp.file;

        // alert("图片上传成功");
      } catch (error) {
        alert("图片上传失败");
        return; // 如果图片上传失败，则不执行后面的逻辑
      }
 
      console.log(teamInfo.value.id);

      // 创建战队
      $.ajax({
        url: URL + "/api/team/revise/",
        data: {
          teamId: teamInfo.value.id,
          teamName: teamName.value,
          teamSignature: teamSignature.value,
          teamDescription: teamDescription.value,
          src: src.value,
        },
        type: "post",
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          console.log(resp);

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
      teamInfo,
      file_src,
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