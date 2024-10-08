import router from "@/router";

export const toTeamCenter = (id) => {
    router.push({
        params: { teamId: id },
        name: "teaminfo",
    });
};
export const toUserCenter = (id) => {
    router.push({
        params: { userId: id },
        name: "usercenter",
    });
};