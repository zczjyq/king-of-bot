package com.kob.backend.consumer;

import com.alibaba.fastjson2.JSONObject;
import com.kob.backend.consumer.utils.Game;
import com.kob.backend.consumer.utils.JwtAuthentication;
import com.kob.backend.mapper.RecordMapper;
import com.kob.backend.mapper.UserMapper;
import com.kob.backend.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@ServerEndpoint("/websocket/{token}")  // 注意不要以'/'结尾
public class WebSocketServer {

    final public static ConcurrentHashMap<Integer, WebSocketServer>
            users = new ConcurrentHashMap<>();
    final private static CopyOnWriteArraySet<User> matchpool = new CopyOnWriteArraySet<>();
    private User user;
    private Session session = null;
    private Game game = null;

    private static UserMapper userMapper;
    public static RecordMapper recordMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        WebSocketServer.userMapper = userMapper;
    }

   @Autowired
   public void setRecordMapper(RecordMapper recordMapper) {
        WebSocketServer.recordMapper = recordMapper;
   }

    // 当有新连接建立时调用此方法
    @OnOpen
    public void onOpen(Session session, @PathParam("token") String token) throws IOException {
        // 建立连接
        this.session = session;
        System.out.println("connect!");
        Integer userId = JwtAuthentication.getUserId(token);
        this.user = userMapper.selectById(userId);
        if (this.user != null) {
            users.put(userId, this);
        } else {
            this.session.close();
        }
//        System.out.println(users);
    }

    // 当连接关闭时调用此方法
    @OnClose
    public void onClose() {
        // 关闭链接
        System.out.println("disconnect");
        if (this.user != null) {
            users.remove(this.user.getId());
            matchpool.remove(this.user);
        }
    }
    /**
     * 开始匹配对战玩家，并创建游戏实例进行对战
     */
    private void startMatching() {
        System.out.println("start-matching");
        matchpool.add(this.user);

        // 当匹配池中有足够的玩家时，开始匹配对战
        while (matchpool.size() >= 2) {
            // 从匹配池中取出两个玩家进行对战
            Iterator<User> it = matchpool.iterator();
            User a = it.next(), b = it.next();
            matchpool.remove(a);
            matchpool.remove(b);

            // 创建游戏实例并生成地图
            Game game = new Game(15, 16, 40, a.getId(), b.getId());
            game.createMap();

            // 将玩家与游戏实例关联
            users.get(a.getId()).game = game;
            users.get(b.getId()).game = game;
            game.start();

            // 准备发送给玩家的游戏信息
            JSONObject respGame = new JSONObject();
            respGame.put("a_id", game.getPlayerA().getId());
            respGame.put("a_sx", game.getPlayerA().getSx());
            respGame.put("a_sy", game.getPlayerA().getSy());
            respGame.put("b_id", game.getPlayerB().getId());
            respGame.put("b_sx", game.getPlayerB().getSx());
            respGame.put("b_sy", game.getPlayerB().getSy());
            respGame.put("map", game.getG());

            // 准备发送给玩家A的消息
            JSONObject respA = new JSONObject();
            respA.put("event", "start-matching");
            respA.put("opponent_username",b.getUsername());
            respA.put("opponent_photo", b.getPhoto());
            respA.put("game", respGame);
            users.get(a.getId()).sendMessage(respA.toJSONString());

            // 准备发送给玩家B的消息
            JSONObject respB = new JSONObject();
            respB.put("event", "start-matching");
            respB.put("opponent_username",a.getUsername());
            respB.put("opponent_photo", a.getPhoto());
            respB.put("game", respGame);
            users.get(b.getId()).sendMessage(respB.toJSONString());
        }
    }

    private void stopMatching() {
        System.out.println("stop-matching");
        matchpool.remove(this.user);
    }

    private void move(int direction) {
        if (game.getPlayerA().getId().equals(user.getId())) {
            game.setNextstepA(direction);
        } else if (game.getPlayerB().getId().equals(user.getId())) {
            game.setNextstepB(direction);
        }
    }

    // 当接收到客户端消息时调用此方法
    @OnMessage
    public void onMessage(String message, Session session) {
        // 从Client接收消息
        System.out.println("receive message");
        JSONObject data = JSONObject.parseObject(message);
        String event = data.getString("event");
        if ("start-matching".equals(event)) {
            startMatching();
        } else if ("stop-matching".equals(event)) {
            stopMatching();
        } else if ("move".equals(event)) {
            move(data.getInteger("direction"));
        }
    }

    // 当发生错误时调用此方法
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    /**
     * 向 WebSocket 会话发送消息
     * @param message 要发送的消息内容
     */
    public void sendMessage(String message) {
        synchronized (this.session) {
            try {
                this.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}