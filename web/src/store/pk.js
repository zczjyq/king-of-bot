export default {
  state: {
    status: 0,
    socket: null,
    opponent_username: "",
    opponent_photo: "",
    gamemap: null,
  },
  getters: {
  },
  mutations: {
    updateSocket(state, socket) {
      state.socket = socket;
    },
    updateOpponent(state, oppponent) {
      state.opponent_username = oppponent.username;
      state.opponent_photo = oppponent.photo;
    },

    updateStatus(state, status) {
      state.status = status;
    },

    updateGameMap(state, gamemap) {
      state.gamemap = gamemap;
    }
  },
  actions: {
  },
  modules: {
  }
}