import $ from 'jquery';
import { URL } from "@/utils/constants.js"

export default {
    state: {
        id: "",
        username: "",
        photo: "",
        token: "",
        is_login: false,
        show_content: false,
        followerCount: "",
        signature: "",
        rating: "",
        teamId: "",
        teamRole: "",
    },
    getters: {
    },
    mutations: {
        updateTeamInfo(state, teamRole) {
            state.teamRole = teamRole;
        }, 

        // 更新关注者数量
        updatefollowerCount(state, followerCount) {
            state.followerCount = followerCount;
        },

        // 更新用户信息
        updateUser(state, user) {
            state.id = user.id;
            state.username = user.username;
            state.photo = user.photo;
            state.is_login = user.is_login;
            state.signature = user.signature;
            state.rating = user.rating;
        },

        updateSignature(state, signature) {
            state.signature = signature;
        },

        // 更新用户令牌
        updateToken(state, token) {
            state.token = token;
        },

        // 登出用户
        logout(state) {
            state.id = "";
            state.username = "";
            state.photo = "";
            state.token = "";
            state.is_login = false;
        }
    },
    actions: {
        login(context, data) {
            $.ajax({
                url: URL + "/api/user/account/token/",
                type: "post",
                data: {
                    username: data.username,
                    password: data.password,
                },
                success(resp) {
                    if (resp.error_message === "success") {
                        localStorage.setItem("jwt_token", resp.token)
                        context.commit("updateToken", resp.token);
                        data.success(resp);
                    } else {
                        data.error(resp);
                    }
                },

            });
        },
        getSignature(context) {
            $.ajax({
                url: URL + "/api/user/signature/get/",
                type: "get",
                headers: {
                    Authorization:
                        "Bearer " + context.state.token,
                },
                success(resp) {
                    console.log(resp.error_message);
                    context.commit("updateSignature", resp.error_message);
                }

            })
        },
        getinfo(context, data) {


            $.ajax({
                url: URL + "/api/user/account/info/",
                type: "get",
                headers: {
                    Authorization:
                        "Bearer " + context.state.token,
                },
                success(resp) {
                    if (resp.error_message === "success") {
                        context.commit("updateUser", {
                            ...resp,
                            is_login: true,
                        });
                        data.success(resp);
                    } else {
                        data.error(resp)
                    }
                },
                error(resp) {
                    data.error(resp);
                }
            });
        },
        logout(context) {
            localStorage.removeItem("jwt_token");
            context.commit("logout");
        },
    },
    modules: {
    }
}