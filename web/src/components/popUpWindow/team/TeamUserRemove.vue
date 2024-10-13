<template>
    <div>
      <!-- 模态框 -->
      <div
        class="modal fade"
        id="teamUserRemoveModal"
        tabindex="-1"
        aria-labelledby="disbandModalLabel"
        aria-hidden="true"
      >
        <div class="modal-dialog modal-sm modal-dialog-centered">
          <div class="modal-content text-center">
            <div class="card">
              <div class="header">
                <div class="image">
                  <svg
                    aria-hidden="true"
                    stroke="currentColor"
                    stroke-width="1.5"
                    viewBox="0 0 24 24"
                    fill="none"
                  >
                    <path
                      d="M12 9v3.75m-9.303 3.376c-.866 1.5.217 3.374 1.948 3.374h14.71c1.73 0 2.813-1.874 1.948-3.374L13.949 3.378c-.866-1.5-3.032-1.5-3.898 0L2.697 16.126zM12 15.75h.007v.008H12v-.008z"
                      stroke-linejoin="round"
                      stroke-linecap="round"
                    ></path>
                  </svg>
                </div>
                <div class="content">
                  <span class="title">提示</span>
                  <p class="message">确定要移除该成员吗？该操作不可恢复哦</p>
                </div>
                <div class="actions">
                  <button
                    class="desactivate"
                    type="button"
                    data-bs-dismiss="modal"
                    data-bs-toggle="modal"
                    data-bs-target="#teamDissloveComfirm"
                    @click="dissloveTeam"
                  >
                    确定
                  </button>
                  <button class="cancel" type="button" data-bs-dismiss="modal">
                    取消
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </template>
    
    <script >
  import $ from "jquery";
  import { URL } from "@/utils/constants";
  // import { ref } from "vue";
  import { useStore } from "vuex";
  export default {
    name: "teamDisslove",
    setup() {
      const store = useStore();
      const dissloveTeam = () => {
        console.log(store.state.user.teamId);
  
        $.ajax({
          url: URL + "/api/team/disslove/",
          type: "post",
          headers: {
            Authorization: "Bearer " + store.state.user.token,
          },
          data: {
            teamId: store.state.user.teamId,
          },
          success(resp) {
            console.log(resp);
            window.location.reload();
          },
        });
      };
      return {
        dissloveTeam,
      };
    },
  };
  </script>
    
    <style scoped>
  /* From Uiverse.io by Yaya12085 */
  .card {
    overflow: hidden;
    position: relative;
    background-color: #ffffff;
    text-align: left;
    border-radius: 0.5rem;
    /* max-width: 290px; */
    box-shadow: 0 20px 25px -5px rgba(0, 0, 0, 0.1),
      0 10px 10px -5px rgba(0, 0, 0, 0.04);
  }
  
  .header {
    padding: 1.25rem 1rem 1rem 1rem;
    background-color: #ffffff;
  }
  
  .image {
    display: flex;
    margin-left: auto;
    margin-right: auto;
    background-color: #fee2e2;
    flex-shrink: 0;
    justify-content: center;
    align-items: center;
    width: 3rem;
    height: 3rem;
    border-radius: 9999px;
  }
  
  .image svg {
    color: #dc2626;
    width: 1.5rem;
    height: 1.5rem;
  }
  
  .content {
    margin-top: 0.75rem;
    text-align: center;
  }
  
  .title {
    color: #111827;
    font-size: 1rem;
    font-weight: 600;
    line-height: 1.5rem;
  }
  
  .message {
    margin-top: 0.5rem;
    color: #6b7280;
    font-size: 16px;
    line-height: 1.25rem;
  }
  
  .actions {
    margin: 0.75rem 1rem;
    background-color: #f9fafb;
    display: flex;
    justify-content: space-between;
  }
  
  .desactivate {
    display: inline-flex;
    padding: 0.5rem 1rem;
    background-color: #dc2626;
    color: #ffffff;
    font-size: 14px;
    line-height: 1.5rem;
    font-weight: 500;
    justify-content: center;
    width: 48%; /* 调整按钮宽度 */
    border-radius: 0.375rem;
    border-width: 1px;
    border-color: transparent;
    box-shadow: 0 1px 2px 0 rgba(0, 0, 0, 0.05);
  }
  
  .cancel {
    display: inline-flex;
    padding: 0.5rem 1rem;
    background-color: #ffffff;
    color: #374151;
    font-size: 14px;
    line-height: 1.5rem;
    font-weight: 500;
    justify-content: center;
    width: 48%; /* 调整按钮宽度 */
    border-radius: 0.375rem;
    border: 1px solid #d1d5db;
    box-shadow: 0 1px 2px 0 rgba(0, 0, 0, 0.05);
  }
  </style>
    