<template>
  <PlayGround v-if="$store.state.pk.status === 1" />
  <MatchGround v-if="$store.state.pk.status === 0"> </MatchGround>
  <ResultBoard v-if="$store.state.pk.loser != 'none'" />
  <div
    class="user-color"
    v-if="
      $store.state.pk.status === 1 &&
      parseInt($store.state.user.id) === parseInt($store.state.pk.a_id)
    "
  >
    左下角
  </div>
  <div
    class="user-color"
    v-if="
      $store.state.pk.status === 1 &&
      parseInt($store.state.user.id) === parseInt($store.state.pk.b_id)
    "
  >
    右上角
  </div>
</template>

<script>
import PlayGround from "../../components/PlayGround.vue";
import MatchGround from "../../components/MatchGround.vue";
import ResultBoard from "../../components/ResultBoard.vue";
import { onMounted, onUnmounted } from "vue";
import { useStore } from "vuex";

export default {
  components: {
    PlayGround,
    MatchGround,
    ResultBoard,
  },
  setup() {

    const store = useStore();
    const socketUrl = `wss://app6142.acapp.acwing.com.cn/websocket/${store.state.user.token}`;

    store.commit("updateLoser", "none");
    store.commit("updateIsRecord", false);

    let socket = null;
    onMounted(() => {
      store.commit("updateOpponent", {
        username: "我的对手",
        photo:
          "https://cdn.acwing.com/media/article/image/2022/08/09/1_1db2488f17-anonymous.png",
      });
      console.log("socketUrl" + socketUrl);

      socket = new WebSocket(socketUrl);

      socket.onopen = () => {
        console.log("WebSocket连接已打开");
        store.commit("updateSocket", socket);
      };
      socket.onmessage = (msg) => {
        const data = JSON.parse(msg.data);

        // start-matching是后端定义的
        // 表示服务器端已经分配到对手，可以开始游戏
        if (data.event === "start-matching") {
          store.commit("updateOpponent", {
            username: data.opponent_username,
            photo: data.opponent_photo,
          });
          
          store.commit("updateGame", data.game);
          setTimeout(() => {
            store.commit("updateStatus", 1);
          }, 200);
          const game = store.state.pk.gameObject;
          const [snake0, snake1] = game.snakes;
          console.log(store.state.user.id, " + a_id: " + store.state.pk.a_id , " + b_id", store.state.pk.b_id, " + status", parseInt(store.state.user.id) === parseInt(store.state.pk.a_id));
          snake0.set_whoai(parseInt(store.state.user.id) === parseInt(store.state.pk.a_id));
          snake1.set_whoai(parseInt(store.state.user.id) === parseInt(store.state.pk.b_id));
        } else if (data.event === "move") {
          const game = store.state.pk.gameObject;
          const [snake0, snake1] = game.snakes;
          snake0.set_direction(data.a_direction);
          
          snake1.set_direction(data.b_direction);
          
        } else if (data.event === "result") {
          const game = store.state.pk.gameObject;
          const [snake0, snake1] = game.snakes;

          if (data.loser === "all" || data.loser === "A") snake0.status = "die";
          if (data.loser === "all" || data.loser === "B") snake1.status = "die";
          store.commit("updateLoser", data.loser);
        }
        console.log("收到消息:", data.event, data.a_direction);
      };
      socket.onclose = () => {
        console.log("WebSocket连接已关闭");
      };
    });

    onUnmounted(() => {
      socket.close();
      store.commit("updateStatus", 0);
    });
  },
};
</script>

<style scoped>
div.user-color {
  text-align: center;
  color: white;
  font-size: 30px;
  font-weight: 600;
}
</style>