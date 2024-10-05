package com.kob.backend.DTO;

import com.kob.backend.pojo.Team;
import com.kob.backend.pojo.User;

public class UserTeamDTO {
    private User user;
    private Team team;

    // Constructor
    public UserTeamDTO(User user, Team team) {
        this.user = user;
        this.team = team;
    }

    // Getters and Setters
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
