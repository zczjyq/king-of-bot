<template>
  <div class="container">
    <div class="row">
      <div class="col-9">
        <CarouselComp
          class="card_zczjyq card"
          style="background-color: lightblue; height: 270px"
        />
        <AnnouncementComp
          class="card_zczjyq card"
          style="background-color: white; height: 40px"
        >
          公告
        </AnnouncementComp>
        <div
          class="row"
          style="display: flex; justify-content: center; /* 水平居中 */"
        >
          <div class="col-6" style="padding: 10px 20px">
            <PersonalRanklist> 个人榜 </PersonalRanklist>
          </div>
          <div class="col-6" style="padding: 10px 20px">
            <TeamRanklist />
          </div>
        </div>
        <div
          class="card_zczjyq card"
          style="background-color: white; height: 600px"
        >
          游戏列表
        </div>
      </div>
      <div class="col-3">
        <SignIn @show-modal="openSignInRulesModal"> 签到 </SignIn>
        <router-link to="/recharge" class="card_zczjyq card pay">
          金币充值
        </router-link>
        <div
          class="card_zczjyq card"
          style="
            background-color: white;
            height: 100px;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
          "
        >
          <!-- "投稿入口"文字居中 -->
          <div
            style="font-weight: bold; font-size: 18px; color: rgb(80, 80, 80)"
          >
            发帖入口
          </div>
          <!-- "投稿"按钮 -->
          <router-link
            :to="{
              name: 'userprofile',
              params: { userId: parseInt($store.state.user.id) },
            }"
            class="btn btn-primary mt-2"
            style="
              width: 80%;
              text-align: center;
              font-weight: bold;
              font-size: 15px;
            "
          >
            去发帖
          </router-link>
        </div>

        <div
          class="card_zczjyq card"
          style="background-color: white; height: 500px"
        >
          比赛通知
        </div>
        <div
          class="card_zczjyq card"
          style="background-color: white; height: 500px"
        >
          对战动态
        </div>
      </div>
    </div>
    <div class="row">&emsp;</div>

    <!-- 签到规则的模态框 -->
    <div
      class="modal fade"
      id="signInRulesModal"
      tabindex="-1"
      aria-labelledby="signInRulesLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
          <div class="modal-header">
            <h6
              class="modal-title"
              id="signInRulesLabel"
              style="
                font-weight: bold;
                color: rgb(80, 80, 80);
                cursor: pointer !important;
              "
            >
              签到规则
            </h6>

            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
              aria-label="Close"
            ></button>
          </div>
          <div
            class="modal-body"
            style="font-size: 15px; color: rgb(80, 80, 80)"
          >
            连续签到1天，奖励1金币。<br />
            没有签到中断，连续签到2天，奖励2金币。<br />
            以此类推，最多7金币。<br />
            若连续签到中断，则重新计数。
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-primary"
              data-bs-dismiss="modal"
            >
              关闭
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
// import { Modal } from "bootstrap";
import SignIn from "@/components/home/SignIn.vue";
import CarouselComp from "@/components/home/CarouselComp.vue";
import AnnouncementComp from "@/components/home/AnnouncementComp.vue";
import PersonalRanklist from "@/components/home/PersonalRanklist.vue";
import TeamRanklist from "@/components/home/TeamRanklist.vue";
import { ref, onMounted } from "vue";
import $ from "jquery";
import { useStore } from "vuex";
import { URL } from "@/utils/constants";
const store = useStore();
const role = ref("");
onMounted(() => {
  $.ajax({
    url: URL + "/api/team/getreviseinfo/",
    type: "post",
    headers: {
      Authorization: "Bearer " + store.state.user.token,
    },
    success(resp) {
      role.value = resp.role;

      store.commit("updateTeamInfo", role.value);
      console.log("更新成功");
      
    },
  });
});

// 通过 Bootstrap API 显示模态框
// const openSignInRulesModal = () => {
//   const modalElement = document.getElementById("signInRulesModal");
//   const modal = new Modal(modalElement);
//   modal.show();
// };
</script>

<style scoped>
.test {
  background-color: red;
  height: 400px;
}

.background {
  margin: 20px auto;
}

.card_zczjyq {
  margin: 20px 10px;
  border-radius: 10px;
  background-color: white;
  border: none; /* 去掉边框 */
}

.card_zczjyq_inner {
  padding: 20px 20px;
  border-radius: 10px;
  background-color: white;
}

.pay {
  display: flex;
  justify-content: center; /* 水平居中 */
  align-items: center; /* 垂直居中 */
  height: 40px;
  background-color: rgb(234, 85, 85);
  font-weight: bold; /* 字体加粗 */
  text-align: center;
  color: white; /* 让文字颜色更明显 */
  text-decoration: none; /* 去掉链接下划线 */
  cursor: pointer;
  border: none; /* 去掉边框 */
}
</style>


