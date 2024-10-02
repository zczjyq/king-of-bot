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
          <div class="alert alert-warning" role="alert">
            创建战队将扣除10金币!
          </div>

          <!-- 圆形头像图片，支持上传 -->
          <div class="image-upload-container text-center my-3 d-flex justify-content-center align-items-center">
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
                
              />
            </div>

            <div class="mb-3">
              <label for="teamSlogan" class="form-label">战队宣言</label>
              <input
                type="text"
                class="form-control"
                id="teamSlogan"
                placeholder="请输入战队宣言"
              />
            </div>

            <div class="mb-3">
              <label for="teamDescription" class="form-label">战队介绍</label>
              <textarea
                class="form-control"
                id="teamDescription"
                rows="3"
                placeholder="请输入战队介绍"
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

export default {
  setup() {
    const selectedFile = ref(null); // 存储选中的文件
    const store = useStore();

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
      try {
        await uploadImage(selectedFile.value, store);
        alert("图片上传成功");
      } catch (error) {
        alert("图片上传失败");
      }
    };

    return {
      previewImage,
      uploadImage: handleUploadImage,
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