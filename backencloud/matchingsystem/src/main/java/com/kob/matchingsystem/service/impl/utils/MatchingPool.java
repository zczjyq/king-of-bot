package com.kob.matchingsystem.service.impl.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class MatchingPool extends Thread{
    private static List<Player> players = new ArrayList<>();
    private ReentrantLock lock = new ReentrantLock();
    private static RestTemplate restTemplate;
    private static final String startGameUrl = "http://127.0.0.1:3000/pk/start/game/";
    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        MatchingPool.restTemplate = restTemplate;
    }

    public void addPlayer(Integer userId, Integer rating, Integer botId) {
        lock.lock();
        try {
            players.add(new Player(userId, rating,botId, 0));
        }finally {
            lock.unlock();
        }
    }

    public void removePlayer(Integer userId) {
        lock.lock();
        try {
            List<Player> newPlayer = new ArrayList<>();
            for (Player player:players) {
                if (!player.getUserId().equals(userId)) {
                    newPlayer.add(player);
                }
            }
            players = newPlayer;
        } finally {
            lock.unlock();
        }
    }

    /*
    匹配函数
    */
    private void inceaseWaitingTime() { // 将所有玩家的等待时间加1
        for (Player player: players) {
            player.setWaitingTime(player.getWaitingTime() + 1);
        }
    }

    private boolean checkMatched(Player a, Player b) {
        int aRating = a.getRating(), bRating = b.getRating();
        int ratingDetla = Math.abs(aRating - bRating);
        int waitingTime = Math.min(a.getWaitingTime(), b.getWaitingTime());
        return (ratingDetla <= waitingTime * 10);
    }

    private void sendResult(Player a, Player b) { // 返回匹配结果
        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        data.add("a_id", a.getUserId().toString());
        data.add("b_id", b.getUserId().toString());
        data.add("a_bot_id", a.getBotId().toString());
        data.add("b_bot_id", b.getBotId().toString());
        restTemplate.postForObject(startGameUrl, data, String.class);
    }

    private void matchPlayers() { // 尝试匹配所有玩家
        System.out.println("match players: " + players.toString());
        boolean[] used = new boolean[players.size()];
        for (int i = 0; i < players.size(); i  ++ ) {
            if (used[i]) continue;
            for (int j = i + 1; j < players.size(); j ++ ) {
                if (used[j]) continue;
                Player a = players.get(i), b = players.get(j);
                if (checkMatched(a, b)) {
                    used[i] = used[j] = true;
                    sendResult(a, b);
                    break;
                }
            }
        }

        List<Player> newPlayer = new ArrayList<>();
        for (int i = 0; i < players.size(); i ++ ) {
            if (!used[i]) {
                newPlayer.add(players.get(i));
            }
        }
        players = newPlayer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                lock.lock();
                try {
                    inceaseWaitingTime();
                    matchPlayers();
                } finally {
                    lock.unlock();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
