<template>
  <div class="container">
    <!-- 搜索框 -->
    <div class="d-flex justify-content-between align-items-center my-4">
      <button
        type="button"
        class="btn btn-primary"
        data-bs-toggle="modal"
        data-bs-target="#exampleModal"
      >
        创建战队
      </button>
      <div class="col-4">
        <button
          v-if="my_team_show === 1"
          class="btn btn-outline-primary ml-2"
          @click="goToTeamCenter(user_id)"
        >
          我的团队
        </button>
      </div>
      <div class="col-4 d-flex align-items-center">
        <input
          type="text"
          v-model="searchQuery"
          class="form-control w-75 me-2"
          placeholder="请输入队伍名称"
        />
        <button class="btn btn-outline-success" type="submit">搜索</button>
      </div>
    </div>

    <!-- 队伍卡片 -->
    <div class="row row-cols-1 row-cols-md-4 g-5">
      <div class="col" v-for="infos in teams" :key="infos.id">
        <div class="card" style="height: 335px">
          <div class="card-body">
            <div class="row">
              <div class="col-2 text-end" style="padding-right: 0">
                <img
                  :src="OSS + infos.team.teamHead"
                  class="img-fluid"
                  style="height: 32px; width: 32px; border-radius: 4px"
                />
              </div>

              <div class="col-8 justify-content-start" style="padding-left: 10">
                <div class="row">
                  <div
                    class="name"
                    @click="getId(infos.team.id)"
                    style="cursor: pointer"
                  >
                    {{ infos.team.teamName }}
                  </div>
                  <div class="raking">排名：{{ infos.team.ranking }}</div>
                </div>
              </div>
            </div>
            <p class="raking" style="margin-top: 30px">
              宣言: {{ infos.team.teamSignature }}
            </p>
            <div
              class="description"
              style="margin-top: 10px; margin-bottom: 15px"
            >
              {{ infos.team.teamDescription }}
            </div>
            <div class="row">
              <div class="col-6">
                <div class="raking">成立</div>
                <div class="description">
                  {{
                    new Date(infos.team.createdAt).toISOString().split("T")[0]
                  }}
                </div>
              </div>
              <div class="col-6">
                <div class="raking">积分</div>
                <div class="description">{{ infos.team.teamRating }}</div>
              </div>
            </div>
          </div>
          <hr style="border: 1px solid #ccc; margin: 0" />
          <div class="card-body">
            <div class="d-flex flex-column">
              <div class="team-member">成员</div>

              <div class="d-flex">
                <div
                  class="member-stack"
                  v-for="member in infos.team_members"
                  :key="member.id"
                >
                  <img
                    class="member-photo"
                    style="cursor: pointer"
                    @click="toUserCenter(member.id)"
                    :src="member.photo"
                  />
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- Modal -->
    <TeamWindow></TeamWindow>
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
  </div>
</template>
  
  <script>
import TeamWindow from "@/components/popUpWindow/TeamWindow.vue";
import { useStore } from "vuex";
import $ from "jquery";
import { URL, OSS } from "@/utils/constants.js";
import { onMounted, ref } from "vue";
import router from "../../router/index";

export default {
  components: {
    TeamWindow,
  },

  setup() {

    const store = useStore();
    let teams = ref([]);
    let current_page = ref(1);
    let total_users = 0;
    let pages = ref([]);
    let user_id = store.state.user.id;
    let my_team_show = ref(0);

    onMounted(() => {
      $.ajax({
        url: URL + "/api/team/checkHasTeam/",
        type: "post",
        data: {
          userId: store.state.user.id,
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          console.log("myteamshow" + resp);
          my_team_show.value = resp;
        }
      });
    });

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
        url: URL + "/api/team/getallteam/",
        type: "get",
        data: {
          page: page,
          pageSize: 20,
          orderByColumn: "team_name",
          orderDirection: "ASC",
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          console.log(resp);

          console.log(resp.teams);
          teams.value = resp.teams;
          update_pages();
        },
      });
    };
    pull_page(current_page.value);
    const getId = (id) => {
      router.push({
        params: { teamId: id },
        name: "teaminfo",
      });
    };
    const toUserCenter = (id) => {
      router.push({
        params: { userId: id },
        name: "usercenter",
      });
    };

    const goToTeamCenter = (id) => {
      // 跳转到 userrevise 页面并传递参数，告诉它要显示 TeamCenter 组件
      router.push({
        params: { userId: id },
        name: "teammanage",
      });
    };
    return {
      teams,
      pages,
      click_page,
      current_page,
      OSS,
      getId,
      toUserCenter,
      goToTeamCenter,
      user_id,
      my_team_show
    };
  },
  // data() {
  //   return {
  //     searchQuery: "",
  //     teams: [
  //       {
  //         id: 1,
  //         name: "Team A",
  //         logo: "https://th.bing.com/th/id/OIP.7kSGRwe_IJg61dyveatBvgHaE8?w=245&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7",
  //         slogan: "异世相遇，尽享壮丽",
  //         description: "欢迎加入",
  //         founded: "2021-11-08",
  //         points: 8605,
  //         raking: 1,
  //         members: [
  //           { id: 1, avatar: "https://via.placeholder.com/30" },
  //           { id: 2, avatar: "https://via.placeholder.com/30" },
  //           { id: 3, avatar: "https://via.placeholder.com/30" },
  //         ],
  //       },
  //       {
  //         id: 2,
  //         name: "Team B",
  //         logo: "https://th.bing.com/th/id/OIP.7kSGRwe_IJg61dyveatBvgHaE8?w=245&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7",
  //         slogan: "Flag is not easy?",
  //         description: "欢迎加入",
  //         founded: "2020-12-10",
  //         points: 8355,
  //         raking: 2,
  //         members: [
  //           { id: 1, avatar: "https://via.placeholder.com/30" },
  //           { id: 2, avatar: "https://via.placeholder.com/30" },
  //         ],
  //       },
  //       {
  //         id: 2,
  //         name: "Team B",
  //         logo: "https://th.bing.com/th/id/OIP.7kSGRwe_IJg61dyveatBvgHaE8?w=245&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7",
  //         slogan: "Flag is not easy?",
  //         description: "欢迎加入",
  //         founded: "2020-12-10",
  //         points: 8355,
  //         raking: 2,
  //         members: [
  //           { id: 1, avatar: "https://via.placeholder.com/30" },
  //           { id: 2, avatar: "https://via.placeholder.com/30" },
  //         ],
  //       },
  //       {
  //         id: 2,
  //         name: "Team B",
  //         logo: "https://th.bing.com/th/id/OIP.7kSGRwe_IJg61dyveatBvgHaE8?w=245&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7",
  //         slogan: "Flag is not easy?",
  //         description: "欢迎加入",
  //         founded: "2020-12-10",
  //         points: 8355,
  //         raking: 2,
  //         members: [
  //           { id: 1, avatar: "https://via.placeholder.com/30" },
  //           { id: 2, avatar: "https://via.placeholder.com/30" },
  //         ],
  //       },
  //       {
  //         id: 2,
  //         name: "Team B",
  //         logo: "https://th.bing.com/th/id/OIP.7kSGRwe_IJg61dyveatBvgHaE8?w=245&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7",
  //         slogan: "Flag is not easy?",
  //         description: "欢迎加入",
  //         founded: "2020-12-10",
  //         points: 8355,
  //         raking: 2,
  //         members: [
  //           { id: 1, avatar: "https://via.placeholder.com/30" },
  //           { id: 2, avatar: "https://via.placeholder.com/30" },
  //         ],
  //       },
  //       {
  //         id: 2,
  //         name: "Team B",
  //         logo: "https://th.bing.com/th/id/OIP.7kSGRwe_IJg61dyveatBvgHaE8?w=245&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7",
  //         slogan: "Flag is not easy?",
  //         description: "欢迎加入",
  //         founded: "2020-12-10",
  //         points: 8355,
  //         raking: 2,
  //         members: [
  //           { id: 1, avatar: "https://via.placeholder.com/30" },
  //           { id: 2, avatar: "https://via.placeholder.com/30" },
  //         ],
  //       },
  //       // 继续添加更多队伍数据...
  //     ],
  //   };
  // },
  // computed: {
  //   filteredTeams() {
  //     return this.teams.filter((team) =>
  //       team.name.toLowerCase().includes(this.searchQuery.toLowerCase())
  //     );
  //   },
  // },
};
</script>
  
  <style>
.card-member img {
  width: 30px;
  height: 30px;
  border-radius: 50%;
  margin-right: 5px;
}

.name {
  color: #0081ff;
  font-size: 15px;
}

.raking {
  color: #a7abc3;
  font-size: 12px;
}

.description {
  color: #505050;
  font-size: 15px;
}

.team-member {
  color: #505050;
  font-size: 12px;
  margin-bottom: 10px;
}

.member-stack {
  display: inline-block; /* 保持容器的水平排列 */
  margin-left: -16px; /* 设置负的外边距使图片重叠，每个图片露出一半 */
}

.member-photo {
  width: 36px;
  height: 36px;
  border-radius: 50%;
  border: 2px solid white; /* 设置白色边框 */
  background-color: white; /* 背景为白色，确保边框清晰 */
}

.member-stack:nth-child(1) .member-photo {
  margin-left: 15px; /* 确保第一个图片不被向左移动 */
}
</style>
  