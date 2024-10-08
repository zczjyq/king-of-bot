package com.kob.backend.service.impl.team;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;

import com.kob.backend.constant.TeamRole;
import com.kob.backend.enumClass.OrderByColumn;
import com.kob.backend.mapper.TeamMapper;
import com.kob.backend.mapper.TeamMemberMapper;
import com.kob.backend.mapper.UserMapper;
import com.kob.backend.pojo.Team;
import com.kob.backend.pojo.TeamMember;
import com.kob.backend.pojo.User;
import com.kob.backend.service.impl.utils.UserDetailsImpl;
import com.kob.backend.service.team.TeamMemberService;
import com.kob.backend.service.team.TeamService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.kob.backend.constant.TeamConstant.*;
import static com.kob.backend.constant.UtilMessage.STATICRETURNMESSAGE;


@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    private TeamMapper teamMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TeamMemberService teamMemberService;

    @Autowired
    private TeamMemberMapper teamMemberMapper;

    // todo
    @Override
    public Map<String, String> check(Map<String, String> data) {
        String teamName = data.get("teamName");
        System.out.println(teamName);
        Map<String, String> map = new HashMap<>();
        if (teamName.isEmpty()) {
            map.put(STATICRETURNMESSAGE, "战队名称不能为空");
            return map;
        }
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();
        if (Objects.equals(teamMemberService.getTeamMemberStatus(user.getId()), TEAM_MEMBER_HAS_TEAM)) {
            map.put(STATICRETURNMESSAGE, "您已经有战队了,不能再次创建");
            return map;
        }

        List<String> teamNames = teamMapper.selectTeamNames();
        System.out.println(teamNames.toString());
        for (String name : teamNames) {
            if (name.equals(teamName)) {
                map.put(STATICRETURNMESSAGE, "战队名称已被占用,请换个名字");
                return map;
            }
        }
        map.put(STATICRETURNMESSAGE, "success");
        return map;
    }

    @Override
    public Map<String, String> createNewTeam(Map<String, String> data) {
        Map<String, String> map = new HashMap<>();
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();
        Team team = new Team(null,
                data.get("teamName"),
                data.get("teamDescription"),
                new Date(),
                new Date(),
                user.getId(),
                TEAM_STATUS_NORMAL,
                "",
                "",
                data.get("src"),
                TEAM_START_RATING,
                String.valueOf(user.getId()),
                (int) (teamMapper.selectCount(null) + 1)
        );
        teamMapper.insert(team);
        Long teamId = team.getId();
        data.put("team_id", String.valueOf(teamId));
        data.put("user_id", String.valueOf(user.getId()));
        data.put("role", String.valueOf(TEAM_LEADER));
        // 在teamMember中也加入一条创建者的信息
        teamMemberService.addTeamMember(data);

        map.put("error_message", "success");
        return map;
    }


    @Override
    public JSONObject getAllTeams(Map<String, String> data) {
        // 获取分页参数
        int page = Integer.parseInt(data.get("page"));
        int pageSize = Integer.parseInt(data.get("pageSize"));

        // 默认值处理
        if (page < 1) {
            page = 1;  // 默认当前页为第 1 页
        }
        if (pageSize < 1) {
            pageSize = 10;  // 默认每页显示 10 条数据
        }

        JSONObject result = new JSONObject();

        // 获取排序字段和排序方向
        String orderByColumn = data.get("orderByColumn");
        String orderDirection = data.get("orderDirection");

        // 校验排序字段
        if (!OrderByColumn.isValid(orderByColumn)) {
            result.put("error", orderByColumn);
            return result;  // 如果字段不合法，返回错误信息
        }

        // 校验排序方向
        if (!("ASC".equalsIgnoreCase(orderDirection) || "DESC".equalsIgnoreCase(orderDirection))) {
            result.put("error", orderDirection);
            return result;  // 如果排序方向不合法，返回错误信息
        }

        // 使用分页查询
        IPage<Team> teamPage = new Page<>(page, pageSize);
        QueryWrapper<Team> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderBy(true, "ASC".equalsIgnoreCase(orderDirection), orderByColumn);

        // 查询数据
        IPage<Team> paginatedTeams = teamMapper.selectPage(teamPage, queryWrapper);
        List<Team> teams = paginatedTeams.getRecords();

        JSONArray teamArray = new JSONArray();
        for (Team team : teams) {
            JSONObject teamJson  = new JSONObject();
            // 使用 BeanUtils 直接复制对象属性
            teamJson.put("team", team);
            // 获取 team_member 列表
            String teamMembers = null;

            teamMembers = team.getTeamMember();
            String[] userIds = teamMembers.split(",");

            // 查询用户 ID 和头像保存位置
            JSONArray membersArray = new JSONArray();
            if (!teamMembers.isEmpty()) {
                for (String userID : userIds) {
                    User user = userMapper.selectById(Integer.parseInt(userID));
                    JSONObject userJson = new JSONObject();
                    userJson.put("id", user.getId());
                    userJson.put("photo", user.getPhoto());
                    membersArray.add(userJson);
                }
            }
            teamJson.put("team_members", membersArray);  // 添加用户信息
            teamArray.add(teamJson);
        }

        // 返回分页结果
        result.put("teams", teamArray);  // 查询的团队列表
        result.put("currentPage", paginatedTeams.getCurrent());  // 当前页
        result.put("totalPages", paginatedTeams.getPages());  // 总页数
        result.put("totalCount", paginatedTeams.getTotal());  // 总记录数
        result.put("pageSize", pageSize);  // 每页的记录数

        return result;
    }

    @Override
    public JSONObject getTeamsInfo(Map<String, String> data) {
        // 返回结果
        JSONObject result = new JSONObject();
        Integer teamId = Integer.valueOf(data.get("teamId"));
        Team team = teamMapper.selectById(teamId);

        String leaderName = userMapper.selectById(team.getTeamLeaderId()).getUsername();
        result.put("team", team);
        result.put("leaderName", leaderName);


        // 获取 team_member 列表
        String teamMembers = null;

        teamMembers = team.getTeamMember();
        String[] userIds = teamMembers.split(",");

        // 查询用户 ID 和头像保存位置
        JSONArray membersArray = new JSONArray();
        if (!teamMembers.isEmpty()) {
            for (String userID : userIds) {
                User user = userMapper.selectById(Integer.parseInt(userID));
                JSONObject userJson = new JSONObject();
                userJson.put("id", user.getId());
                userJson.put("username", user.getUsername());
                userJson.put("photo", user.getPhoto());
                userJson.put("rating", user.getRating());
                userJson.put("role", getrole(user.getId()));
                membersArray.add(userJson);
            }
        }
        result.put("team_members", membersArray);  // 添加用户信息
        return result;
    }


    private String getrole(Integer id) {
        TeamMember teamMember = teamMemberMapper.selectByUserId(id);
        if (teamMember == null) {
            return "";
        }
        return TeamRole.fromCode(teamMember.getRole()).getRoleName();
    }
}
