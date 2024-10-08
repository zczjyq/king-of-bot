<template>
  <div class="container content-filed">
    <div class="card-body"></div>
    <div class="row">
      <div class="col-3" style="margin-top: 40px; border: none">
        <userReviseNavbar @item-selected="updateContent" />
      </div>
      <div class="col-9">
        <ContentField><component :is="currentComponent" /></ContentField>
      </div>
    </div>
  </div>
</template>

<script>
import ContentField from "@/components/ContentField";
import userReviseNavbar from "@/components/userRevise/UserReviseNavbar";
import AccountSettings from "@/components/userRevise/AccountSettings"; // 假设组件
import AccountBinding from "@/components/userRevise/AccountBinding"; // 假设组件
import TaskCenter from "@/components/userRevise/TaskCenter.vue";
import MessageCenter from "@/components/userRevise/MessageCenter.vue";
import MyOrder from "@/components/userRevise/MyOrder.vue";
import RechargeCenter from "@/components/userRevise/RechargeCenter.vue";
import PasswordRevise from "@/components/userRevise/PasswordRevise.vue";
import TeamCenter from "@/components/userRevise/TeamCenter.vue";

export default {
  name: "UserReviseView",
  components: {
    ContentField,
    userReviseNavbar,
    AccountSettings,
    AccountBinding,
    TaskCenter,
    MessageCenter,
    MyOrder,
    RechargeCenter,
    PasswordRevise,
    TeamCenter,
  },
  data() {
    return {
      currentComponent: "AccountSettings", // 默认显示的组件
    };
  },
  methods: {
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
