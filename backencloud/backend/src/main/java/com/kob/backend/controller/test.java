package com.kob.backend.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/")
public class test {
    @RequestMapping("yuanshen/")
    public boolean[][] ground(){
        int rows = 17, cols = 17, inner_walls_count = 80;
        boolean[][] g = new boolean[rows][cols];

        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++) {
                g[r][c] = false;
            }
        for (int r = 0; r < rows; r++) {
            g[r][0] = g[r][cols - 1] = true;
        }

// 第二个循环
        for (int r = 0; r < cols; r++) {
            g[0][r] = g[rows - 1][r] = true;
        }

// 创建随机障碍物
        for (int i = 0; i < inner_walls_count / 2; i++) {
            for (int j = 0; j < 1000; j++) {
                int r = (int)(Math.random() * rows);
                int c = (int)(Math.random() * cols);
                if (g[r][c] || g[c][r]) continue;
                if ((r == rows - 2 && c == 1) || (r == 1 && c == cols - 2)) continue;

                g[r][c] = g[c][r] = true;
                break;
            }
        }
        return g;
    }
}
