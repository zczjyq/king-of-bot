<template>
  <div class="result-board">
    <div class="result-board-text" v-if="$store.state.pk.loser == 'all'">
      Draw
    </div>
    <div
      class="result-board-text"
      v-else-if="
        $store.state.pk.loser === 'A' &&
        $store.state.pk.a_id == $store.state.user.id
      "
    >
      Lose
    </div>
    <div
      class="result-board-text"
      v-else-if="
        $store.state.pk.loser === 'B' &&
        $store.state.pk.b_id == $store.state.user.id
      "
    >
      Lose
    </div>
    <div class="result-board-text" v-else>Win!</div>
    <div class="result-board-btn">
      <button @click="restart" type="button" class="btn btn-warning btn-lg">
        再来!
      </button>
    </div>
  </div>
</template>

<script>
import { useStore } from "vuex";

export default {
  setup() {
    const store = useStore();
    const restart = () => {
      //   console.log("asdf");
      store.commit("updateStatus", 0);
      store.commit("updateLoser", "none");
      store.commit("emptyOpponent");
    };
    return { restart };
  },
};
</script>

<style scoped>
div.result-board {
  height: 30vh;
  width: 30vw;
  background-color: rgba(50, 50, 50, 0.5);
  position: absolute;
  top: 30vh;
  left: 35vw;
}
div.result-board-text {
  text-align: center;
  color: white;
  font-size: 50px;
  font-weight: 600;
  font-style: italic;
  padding-top: 5vh;
}

div.result-board-btn {
  padding-top: 7vh;
  text-align: center;
}
</style>