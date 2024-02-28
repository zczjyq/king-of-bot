export default {
  state: {
    status: 0,
    socket: null,
    opponent_username: "",
    opponent_photo: "",
    gamemap: null,
    a_id: 0,
    a_sx: 0,
    a_sy: 0,
    b_id: 0,
    b_sx: 0,
    b_sy: 0,
    gameObject: null,
    loser: "none",// all, A, B
  },
  getters: {
  },
  mutations: {
    updateSocket(state, socket) {
      state.socket = socket;
    },

    emptyOpponent(state) {
      state.opponent_username = "我的对手";
      state.opponent_photo = "https://cdn.acwing.com/media/article/image/2022/08/09/1_1db2488f17-anonymous.png";
    },
    updateOpponent(state, oppponent) {
      state.opponent_username = oppponent.username;
      state.opponent_photo = oppponent.photo;
    },

    updateStatus(state, status) {
      state.status = status;
    },

    updateGame(state, game) {
      state.gamemap = game.map;
      state.a_id = game.a_id;
      state.a_sx = game.a_sx;
      state.a_sy = game.a_sy;
      state.b_id = game.b_id;
      state.b_sx = game.b_sx;
      state.b_sy = game.b_sy;
    },

    updateGameObject(state, gameObject) {
      state.gameObject = gameObject;
    },

    updateLoser(state, loser) {
      state.loser = loser;
    }
  },
  actions: {
  },
  modules: {
  }
}