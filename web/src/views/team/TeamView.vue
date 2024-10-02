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
      <div class="col-4">我的团队</div>
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
      <div class="col" v-for="team in filteredTeams" :key="team.id">
        <div class="card" style="height: 335px">
          <div class="card-body">
            <div class="row">
              <div class="col-2 text-end" style="padding-right: 0">
                <img
                  :src="team.logo"
                  class="img-fluid"
                  style="height: 32px; width: 32px; border-radius: 4px"
                />
              </div>

              <div class="col-8 justify-content-start" style="padding-left: 10">
                <div class="row">
                  <div class="name">{{ team.name }}</div>
                  <div class="raking">排名：{{ team.raking }}</div>
                </div>
              </div>
            </div>
            <p class="raking" style="margin-top: 30px">
              宣言: {{ team.slogan }}
            </p>
            <div
              class="description"
              style="margin-top: 10px; margin-bottom: 15px"
            >
              {{ team.description }}
            </div>
            <div class="row">
              <div class="col-6">
                <div class="raking">成立</div>
                <div class="description">{{ team.founded }}</div>
              </div>
              <div class="col-6">
                <div class="raking">积分</div>
                <div class="description">{{ team.points }}</div>
              </div>
            </div>
          </div>
          <hr style="border: 1px solid #ccc; margin: 0" />
          <div class="card-body">
            <div class="d-flex flex-column">
              <div class="team-member">成员</div>

              <div class="d-flex">
                <div
                  v-for="member in team.members"
                  :key="member.id"
                  class="card-member"
                >
                  <img :src="member.avatar" alt="member-avatar" />
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- Modal -->
    <TeamWindow></TeamWindow>
  </div>
</template>
  
  <script>
import TeamWindow from "@/components/popUpWindow/TeamWindow.vue";

export default {
  components: {
    TeamWindow,
  },
  data() {
    return {
      searchQuery: "",
      teams: [
        {
          id: 1,
          name: "Team A",
          logo: "https://th.bing.com/th/id/OIP.7kSGRwe_IJg61dyveatBvgHaE8?w=245&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7",
          slogan: "异世相遇，尽享壮丽",
          description: "欢迎加入",
          founded: "2021-11-08",
          points: 8605,
          raking: 1,
          members: [
            { id: 1, avatar: "https://via.placeholder.com/30" },
            { id: 2, avatar: "https://via.placeholder.com/30" },
            { id: 3, avatar: "https://via.placeholder.com/30" },
          ],
        },
        {
          id: 2,
          name: "Team B",
          logo: "https://th.bing.com/th/id/OIP.7kSGRwe_IJg61dyveatBvgHaE8?w=245&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7",
          slogan: "Flag is not easy?",
          description: "欢迎加入",
          founded: "2020-12-10",
          points: 8355,
          raking: 2,
          members: [
            { id: 1, avatar: "https://via.placeholder.com/30" },
            { id: 2, avatar: "https://via.placeholder.com/30" },
          ],
        },
        {
          id: 2,
          name: "Team B",
          logo: "https://th.bing.com/th/id/OIP.7kSGRwe_IJg61dyveatBvgHaE8?w=245&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7",
          slogan: "Flag is not easy?",
          description: "欢迎加入",
          founded: "2020-12-10",
          points: 8355,
          raking: 2,
          members: [
            { id: 1, avatar: "https://via.placeholder.com/30" },
            { id: 2, avatar: "https://via.placeholder.com/30" },
          ],
        },
        {
          id: 2,
          name: "Team B",
          logo: "https://th.bing.com/th/id/OIP.7kSGRwe_IJg61dyveatBvgHaE8?w=245&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7",
          slogan: "Flag is not easy?",
          description: "欢迎加入",
          founded: "2020-12-10",
          points: 8355,
          raking: 2,
          members: [
            { id: 1, avatar: "https://via.placeholder.com/30" },
            { id: 2, avatar: "https://via.placeholder.com/30" },
          ],
        },
        {
          id: 2,
          name: "Team B",
          logo: "https://th.bing.com/th/id/OIP.7kSGRwe_IJg61dyveatBvgHaE8?w=245&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7",
          slogan: "Flag is not easy?",
          description: "欢迎加入",
          founded: "2020-12-10",
          points: 8355,
          raking: 2,
          members: [
            { id: 1, avatar: "https://via.placeholder.com/30" },
            { id: 2, avatar: "https://via.placeholder.com/30" },
          ],
        },
        {
          id: 2,
          name: "Team B",
          logo: "https://th.bing.com/th/id/OIP.7kSGRwe_IJg61dyveatBvgHaE8?w=245&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7",
          slogan: "Flag is not easy?",
          description: "欢迎加入",
          founded: "2020-12-10",
          points: 8355,
          raking: 2,
          members: [
            { id: 1, avatar: "https://via.placeholder.com/30" },
            { id: 2, avatar: "https://via.placeholder.com/30" },
          ],
        },
        // 继续添加更多队伍数据...
      ],
    };
  },
  computed: {
    filteredTeams() {
      return this.teams.filter((team) =>
        team.name.toLowerCase().includes(this.searchQuery.toLowerCase())
      );
    },
  },
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
</style>
  