import router from "@/router";

export const toUserCenter = (id) => {
    router.push({
        params: { userId: id },
        name: "usercenter",
    });
};

export const toTeamCenter = (id) => {
    router.push({
        params: { teamId: id },
        name: "teaminfo",
    });
};