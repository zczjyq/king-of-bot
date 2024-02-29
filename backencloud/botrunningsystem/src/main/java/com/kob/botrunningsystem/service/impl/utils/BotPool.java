package com.kob.botrunningsystem.service.impl.utils;

import com.kob.botrunningsystem.service.impl.utils.Bot;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * BotPool 类是一个线程池，用于管理和执行 Bot 对象。
 */
public class BotPool extends Thread{
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    private final Queue<Bot> bots = new LinkedList<>();

    /**
     * 将新的 Bot 添加到线程池中。
     * @param userId 用户ID
     * @param botCode Bot 代码
     * @param input 输入信息
     */
    public void addBot(Integer userId, String botCode, String input) {
        lock.lock();
        try {
            bots.add(new Bot(userId, botCode, input));
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    // 如果要改成其他方法执行代码，改这边就ok了
    private void consume(Bot bot) {
        Consumer consumer = new Consumer();
        consumer.startTimeout(2000, bot);
    }

    /**
     * 线程池的执行逻辑，在空闲时循环等待并执行任务。
     */
    @Override
    public void run() {
        while (true) {
            lock.lock();
            if (bots.isEmpty()) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    lock.unlock();
                    break;
                }
            } else {
                Bot bot = bots.remove();
                lock.unlock();

                consume(bot); // 比较耗时， 可能会执行几秒
            }
        }
    }
}
