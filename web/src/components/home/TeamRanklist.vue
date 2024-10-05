<template>
  <div
    class="card_zczjyq_inner card"
    style="background-color: white; height: 510px"
  >
    <h4 class="text-center title">战队榜</h4>
    <table class="table text-center mt-2">
      <thead>
        <tr class="title-form">
          <th>排名</th>
          <th class="text-start user-field">用户</th>
          <th>分数</th>
        </tr>
      </thead>
      <tbody>
        <tr
          v-for="(user, index) in users"
          :key="index"
          :class="{ 'no-border': index === 9 }"
        >
          <td class="mycolor">{{ index + 1 }}</td>
          <td class="mycolor text-start user-field myblue">
            {{ user.username }}
          </td>
          <td class="mycolor">{{ user.rating }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>
  <script setup>
import { useStore } from "vuex";
import $ from "jquery";
import { URL } from "@/utils/constants";
import { ref } from "vue";
const store = useStore();
let users = ref([]);
$.ajax({
  url: URL + "/api/ranklist/getlistlimit/",

  type: "get",
  headers: {
    Authorization: "Bearer " + store.state.user.token,
  },
  success(resp) {
    //   users.value = resp.users;
    //   total_users = resp.users_count;
    console.log(resp);
    users.value = resp.users;
  },
});

// data() {
//   return {
//     userList: [
//       { rank: 1, name: "Paradiso", score: 8550 },
//       { rank: 2, name: "Pouletrecrue", score: 8425 },
//       { rank: 3, name: "Lazzaro", score: 8355 },
//       { rank: 4, name: "sain", score: 8145 },
//       { rank: 5, name: "pick29", score: 7885 },
//       { rank: 6, name: "死在回忆里", score: 7795 },
//       { rank: 7, name: "blueyst", score: 7750 },
//       { rank: 8, name: "沐沐", score: 7660 },
//       { rank: 9, name: "@Z", score: 7500 },
//       { rank: 10, name: "流", score: 7450 },
//     ],
//   }

//   };
</script>
  
  <style scoped>
.card_zczjyq_inner {
  padding: 20px 20px;
  border-radius: 10px;
  background-color: white;
}

.title-form th {
  font-size: 11px;
  font-weight: normal;
  color: #212529;
}

.title {
  color: #505050;
  font-size: 16px;
  font-weight: bold;
}

.mycolor {
  color: #212529;
  font-size: 15px;
  font-family: sans-serif;
}

.user-field {
  padding-left: 20px;
}

.no-border td {
  border-bottom: none !important; /* 去掉最后一行的底部边框 */
}

.myblue {
  color: #0071ff;
}
</style>
  