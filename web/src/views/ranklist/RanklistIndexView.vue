<template>
  <ContentField>
    <h2 style="text-align: center; margin-bottom: 20px">个人排名表</h2>
    <!-- 添加标题 -->
    <table class="table table-striped table-hover" style="text-align: left">
      <thead>
        <tr>
          <th style="width: 8%">排名</th>

          <th style="width: 16%">玩家</th>
          <th style="width: 23%">签名</th>

          <th style="width: 20%">天梯分</th>
          <th style="width: 20%">最后一次游玩时间</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(user, index) in users" :key="user.user.id">
          <td style="text-align: center">
            {{ index + 1 + (current_page - 1) * 10 }}
          </td>
          <!-- 这里添加排名 -->

          <td class="user-info" @click="getId(user.user.id)" style="cursor: pointer">
            <img :src="user.user.photo" alt="" class="record-user-photo" />
            <span class="record-user-username">{{ user.user.username }}</span>
          </td>
          <td>{{ user.user.signature }}</td>
          <!-- 这里添加排名 -->
          <td>
            {{ user.user.rating }}
          </td>
          <td>最后一次游玩时间</td>
        </tr>
      </tbody>
    </table>
    <nav aria-label="...">
      <ul class="pagination" style="float: right">
        <li class="page-item" @click="click_page(-2)">
          <a class="page-link" href="#">前一页</a>
        </li>
        <li
          :class="'page-item ' + page.is_active"
          v-for="page in pages"
          :key="page.number"
          @click="click_page(page.number)"
        >
          <a class="page-link" href="#">{{ page.number }}</a>
        </li>
        <li class="page-item" @click="click_page(-1)">
          <a class="page-link" href="#">后一页</a>
        </li>
      </ul>
    </nav>
  </ContentField>
</template>



<script>
import ContentField from "../../components/ContentField.vue";
import { useStore } from "vuex";
import { ref } from "vue";
import $ from "jquery";
import { URL } from "@/utils/constants.js";
import router from "../../router/index";

export default {
  components: {
    ContentField,
  },
  setup() {
    const store = useStore();
    let users = ref([]);
    let current_page = ref(1);
    let total_users = 0;
    let pages = ref([]);

    const click_page = (page) => {
      if (page === -2) page = current_page.value - 1;
      else if (page === -1) page = current_page.value + 1;
      let max_pages = parseInt(Math.ceil(total_users / 10));
      if (page >= 1 && max_pages >= page) {
        pull_page(page);
      }
    };

    const update_pages = () => {
      let max_pages = parseInt(Math.ceil(total_users / 10));
      let new_pages = [];
      for (let i = current_page.value - 2; i <= current_page.value + 2; i++) {
        if (i >= 1 && i <= max_pages) {
          new_pages.push({
            number: i,
            is_active: i === current_page.value ? "active" : "",
          });
        }
      }
      pages.value = new_pages;
    };

    const pull_page = (page) => {
      current_page.value = page;

      $.ajax({
        url: URL + "/api/ranklist/getlist/",
        data: {
          page,
        },
        type: "get",
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          console.log(resp);
          users.value = resp.users;
          total_users = resp.users_count;

          update_pages();
        },
      });
    };

    const getId = (id) => {
      router.push({
        params: { userId: id },
        name: "usercenter",
      });
    };

    pull_page(current_page.value);

    return {
      users,
      pages,
      click_page,
      current_page,
      getId,
    };
  },
};
</script>

<style scoped>
/* 让图片靠左，文字紧随其后 */
.user-info {
  text-align: left; /* 确保整个单元格内容靠左对齐 */
}

/* 将图片设置为固定大小并靠左浮动 */
img.record-user-photo {
  width: 30px;
  height: 30px;
  border-radius: 50%;
  margin-right: 10px; /* 给图片和用户名之间添加一些间距 */
}

/* 确保用户名和图片在同一行，且紧随图片显示 */
.record-user-username {
  display: inline-block;
  vertical-align: middle;
  color: rgb(0, 129, 255);
}
.word {
  border-top: 50px;
  border-bottom: 50px;
  padding: 10px;
}
</style>