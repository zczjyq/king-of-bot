<template>
  <ContentField>
    <table class="table table-striped table-hover" style="text-align: center">
      <thead>
        <tr>
          <th>玩家</th>
          <th>天梯分</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="user in users" :key="user.id" @click="getId(user.username)">
          <td>
            <img :src="user.photo" alt="" class="record-user-photo" />
            &nbsp;
            <span class="record-user-username">{{ user.username }}</span>
          </td>
          <td>
            {{ user.rating }}
          </td>
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
import router from "@/router/index";

export default {
  components: {
    ContentField,
  },
  setup() {
    const store = useStore();
    let id = ref(0);
    let users = ref([]);
    let current_page = 1;
    let total_users = 0;
    let pages = ref([]);

    const getId = (username) => {
      console.log(username);
      console.log(store.state.user.token);
      $.ajax({
        url: "http://localhost:3000/api/user/getid/",
        type: "post",
        data: {
          username: username,
        },

        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          id.value = resp.id;

          // 在 AJAX 请求成功后执行后续代码
          router.push({
            params: { userId: id.value },
            name: "userprofile",
          });
        },
        error(resp) {
          console.log(resp);
        },
      });
    };

    const click_page = (page) => {
      if (page === -2) page = current_page - 1;
      else if (page === -1) page = current_page + 1;
      let max_pages = parseInt(Math.ceil(total_users / 10));
      if (page >= 1 && max_pages >= page) {
        pull_page(page);
      }
    };

    const update_pages = () => {
      let max_pages = parseInt(Math.ceil(total_users / 10));
      let new_pages = [];
      for (let i = current_page - 2; i <= current_page + 2; i++) {
        if (i >= 1 && i <= max_pages) {
          new_pages.push({
            number: i,
            is_active: i === current_page ? "active" : "",
          });
        }
      }
      pages.value = new_pages;
    };

    const pull_page = (page) => {
      current_page = page;
      $.ajax({
        url: "http://localhost:3000/api/ranklist/getlist/",
        data: {
          page,
        },
        type: "get",
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          users.value = resp.users;
          total_users = resp.users_count;

          update_pages();
        },
      });
    };
    pull_page(current_page);

    return {
      users,
      pages,
      click_page,
      getId,
      id,
    };
  },
};
</script>

<style scoped>
img.record-user-photo {
  width: 5vh;
  border-radius: 50%;
}

.word {
  border-top: 50px;
  border-bottom: 50px;
  padding: 10px;
}
</style>