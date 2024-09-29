<template>
  <div class="card">
    <div class="card-body">
      <div class="list-group">
        <a
          v-for="(item, index) in items"
          :key="index"
          href="#"
          class="list-group-item list-group-item-action"
          :class="{ active: activeIndex === index }"
          @click.prevent="selectItem(index)"
        >
          {{ item }}
        </a>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "userReviseNavbar",
  data() {
    return {
      activeIndex: 0,
      items: ["账号设置", "账号绑定", "任务中心", "消息中心", "我的订单", "充值中心", "修改密码"]
    };
  },
  methods: {
    selectItem(index) {
      this.activeIndex = index;
      this.$emit('item-selected', index); // 触发事件，将当前项的索引传递给父组件
    }
  }
};
</script>

<style scoped>
.card {
  width: 285px;
  height: 500px;
  background-color: white;
}
</style>
