<template>
  <ContentField>
    <table class="table table-striped table-hover" style="text-align: center">
      <thead>
        <tr>
          <th>A</th>
          <th>B</th>
          <th>对战结果</th>
          <th>对战时间</th>
          <th>操作</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="record in records" :key="record.record.id">
          <td @click="getId(record.a_username)" style="cursor: pointer">
            <img :src="record.a_photo" alt="" class="record-user-photo" />
            &nbsp;
            <span class="record-user-username">{{ record.a_username }}</span>
          </td>
          <td @click="getId(record.b_username)" style="cursor: pointer">
            <img :src="record.b_photo" alt="" class="record-user-photo" />
            &nbsp;
            <span class="record-user-username">{{ record.b_username }}</span>
          </td>
          <td>{{ record.result }}</td>
          <td class="word">{{ record.record.createtime }}</td>
          <td>
            <button
              @click="open_record_content(record.record.id)"
              type="button"
              class="btn btn-secondary"
            >
              查看录像
            </button>
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
import router from "../../router/index";
import {URL} from "@/utils/constants.js";

export default {
  components: {
    ContentField,
  },
  setup() {
    const store = useStore();
    let records = ref([]);
    let current_page = 1;
    let total_records = 0;
    let pages = ref([]);
    let id = ref(0);

    // 根据username 去 router页面
    const getId = (username) => {
      console.log(username);
      console.log(store.state.user.token);
      $.ajax({
        url: URL + "/api/user/getid/",
        type: "post",
        data: {
          username: username,
        },

        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          id.value = resp.id;
          console.log(id.value);

          // 在 AJAX 请求成功后执行后续代码
          router.push({
            params: { userId: id.value },
            name: "usercenter",
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
      let max_pages = parseInt(Math.ceil(total_records / 10));
      if (page >= 1 && max_pages >= page) {
        pull_page(page);
      }
    };

    const update_pages = () => {
      let max_pages = parseInt(Math.ceil(total_records / 10));
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
        url: URL + "/api/record/getlist/",
        data: {
          page,
        },
        type: "get",
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          records.value = resp.records;
          total_records = resp.records_count;
          if (total_records % 10 !== 0) {
            total_records = (parseInt(total_records / 10) + 1) * 10;
          }
          for (let i = 0; i < total_records; i++) {
            store.state.record.id = i;
          }
          update_pages();
        },
      });
    };
    pull_page(current_page);

    const stringTo2D = (map) => {
      let g = [];
      for (let i = 0, k = 0; i < 15; i++) {
        let line = [];
        for (let j = 0; j < 16; j++, k++) {
          if (map.charAt(k) == "0") {
            line.push(0);
          } else {
            line.push(1);
          }
        }
        g.push(line);
      }
      return g;
    };

    const open_record_content = (recordId) => {
      for (const record of records.value) {
        if (record.record.id === recordId) {
          store.commit("updateIsRecord", true);
          store.commit("updateGame", {
            map: stringTo2D(record.record.map),
            a_id: record.record.aid,
            a_sx: record.record.asx,
            a_sy: record.record.asy,
            b_id: record.record.bid,
            b_sx: record.record.bsx,
            b_sy: record.record.bsy,
          });
          store.commit("updateSteps", {
            a_steps: record.record.asteps,
            b_steps: record.record.bsteps,
          });
          store.commit("updateRecordLoser", record.record.loser);
          router.push({
            name: "record_content",
            params: {
              recordId,
            },
          });
          break;
        }
      }
    };

    return {
      records,
      open_record_content,
      pages,
      click_page,
      getId,
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