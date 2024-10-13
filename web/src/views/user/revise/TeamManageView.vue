<template>
  <div class="container content-filed">
    <!-- <div class="card-body"></div> -->
    <div class="row">
      <div class="col-3" style="margin-top: 40px">
        <userReviseNavbar @item-selected="updateContent" />
      </div>
      <div class="col-9" style="margin-top: 40px">
        <div class="card p-4 mb-3">
          <div class="d-flex justify-content-between">
            <h5>我的战队</h5>
            <button
              v-if="role == '队长'"
              class="btn btn-danger"
              data-bs-toggle="modal"
              data-bs-target="#teamDisslove"
            >
              解散战队
            </button>
          </div>
          <ul class="nav nav-tabs mt-3 justify-content-center">
            <li class="nav-item">
              <a
                class="nav-link"
                :class="{ active: activeTab === 'teamInfo' }"
                href="#"
                @click.prevent="setActiveTab('teamInfo')"
                >战队信息</a
              >
            </li>
            <li class="nav-item">
              <a
                class="nav-link"
                :class="{ active: activeTab === 'teamMembers' }"
                href="#"
                @click.prevent="setActiveTab('teamMembers')"
                >战队成员</a
              >
            </li>
            <li v-if="role == '队长' || role == '副队长'" class="nav-item">
              <a
                class="nav-link"
                :class="{ active: activeTab === 'reviewRequests' }"
                href="#"
                @click.prevent="setActiveTab('reviewRequests')"
                >申请审核</a
              >
            </li>
          </ul>

          <div class="mt-4">
            <component :is="currentTabComponent"></component>
          </div>
        </div>
      </div>
    </div>
  </div>
  <TeamDelete></TeamDelete>
  <TeamConfirm></TeamConfirm>
</template>
  
  <script>
import ContentField from "@/components/ContentField";
import userReviseNavbar from "@/components/userRevise/UserReviseNavbar";
import TeamInfo from "@/components/Teammanage/TeamInfo.vue";
import TeamMembers from "@/components/Teammanage/TeamMembers.vue";
import ReviewRequests from "@/components/Teammanage/ReviewRequests.vue";
import { useStore } from "vuex";
import TeamDelete from "@/components/popUpWindow/team/TeamDelete.vue";
import TeamConfirm from "@/components/popUpWindow/team/TeamConfirm.vue";

export default {
  setup() {
    const store = useStore();
    const role = store.state.user.teamRole;

    return {
      role,
    };
  },
  name: "UserReviseView",
  components: {
    ContentField,
    userReviseNavbar,
    TeamInfo,
    TeamMembers,
    ReviewRequests,
    TeamDelete,
    TeamConfirm,
  },
  data() {
    return {
      activeTab: "teamInfo", // 默认显示的标签页
      currentComponent: "AccountSettings", // 默认显示的组件
    };
  },
  computed: {
    currentTabComponent() {
      // 根据 activeTab 返回相应的组件
      if (this.activeTab === "teamInfo") return "TeamInfo";
      if (this.activeTab === "teamMembers") return "TeamMembers";
      if (this.activeTab === "reviewRequests") return "ReviewRequests";
      return null;
    },
  },
  methods: {
    setActiveTab(tab) {
      this.activeTab = tab;
    },
    updateContent(index) {
      const components = [
        "AccountSettings",
        "AccountBinding",
        "TaskCenter",
        "MessageCenter",
        "MyOrder",
        "RechargeCenter",
        "PasswordRevise",
        "TeamCenter",
      ];
      this.currentComponent = components[index]; // 根据选中的索引更新当前组件
    },
  },
};
</script>
<style scoped>
.nav-tabs .nav-link {
  color: #6c757d; /* 未选中的浅色 */
  padding: 10px 20px;
  border-radius: 10px;
}

.nav-tabs .nav-link.active {
  background-color: #007bff; /* 选中的背景颜色 */
  color: white;
  border-radius: 10px;
}
</style>