<template>
  <ContentField>
    <h2 style="text-align: center; margin-bottom: 20px;">个人排名表</h2> <!-- 添加标题 -->
    <table class="table table-striped table-hover" style="text-align: center">
      <thead>
        <tr>
          <th style="width: 5%">排名</th>
          <th style="width: 7%">玩家</th>
          <th style="width: 20%">签名</th>
          
          <th style="width: 20%">天梯分</th>
          <th style="width: 20%">最后一次游玩时间</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(user, index) in users" :key="user.id">
          <td>{{ index + 1 + (current_page - 1) * 10}}</td> <!-- 这里添加排名 -->
          
          <td>
            <img :src="user.a_photo" alt="" class="record-user-photo" />
            &nbsp;
            <span class="record-user-username">{{ user.username }}</span>
          </td>
          <td>签名</td> <!-- 这里添加排名 -->
          <td>
            {{ user.rating }}
          </td>
          <td>
            最后一次游玩时间
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
import URL from "@/store/constants.js"

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
      console.log("请求数据中" + current_page.value);
      console.log("开始请求数据");
      
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
          // if (total_users % 10 !== 0) {
          //   total_users = (parseInt(total_users / 10) + 1) * 10;
          // }
          // for (let i = 0; i < total_users; i++) {
          //   store.state.record.id = i;
          // }
          console.log(total_users);
          update_pages();
        },
      });
    };
    pull_page(current_page.value);

    return {
      users,
      pages,
      click_page,
      current_page
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