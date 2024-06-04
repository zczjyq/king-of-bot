import { AcGameObjects } from './AcGameObject';
import { Wall } from './Wall';
import { Snake } from './Snake';
// import $ from 'jquery';

export class GameMap extends AcGameObjects {
    constructor(ctx, parent, store) {
        super();

        this.store = store;
        this.ctx = ctx;
        this.parent = parent;
        this.L = 0;

        this.rows = 15;
        this.cols = 16;

        this.walls = [];
        this.inner_walls_count = 40;

        this.snakes = [
            new Snake({ id: 0, color: "#4876EC", r: this.rows - 2, c: 1 }, this),
            new Snake({ id: 1, color: "#F94848", r: 1, c: this.cols - 2 }, this),
        ]
    }


    create_walls() {
        const g = this.store.state.pk.gamemap;

        for (let i = 0; i < this.rows; i++) {
            for (let j = 0; j < this.cols; j++) {
                if (g[i][j] === 1) {
                    this.walls.push(new Wall(i, j, this));
                }
            }
        }
    }


    add_listening_events() {
        console.log(this.store.state.record.is_record);
        if (this.store.state.record.is_record) {
            let k = 0;

            const a_steps = this.store.state.record.a_steps;
            const b_steps = this.store.state.record.b_steps;
            const loser = this.store.state.record.record_loser;
            const [snake0, snake1] = this.snakes;
            const interval_id = setInterval(() => {
                if (k >= a_steps.length - 1) {
                    if (loser === "all" || loser === "A") {
                        snake0.status = "die";
                    }
                    if (loser === "all" || loser === "B") {
                        snake1.status = "die";
                    }
                    clearInterval(interval_id);
                } else {
                    snake0.set_direction(parseInt(a_steps[k]));
                    snake1.set_direction(parseInt(b_steps[k]));
                }
                k++;
            }, 300);

        } else {
            this.ctx.canvas.focus();

            this.ctx.canvas.addEventListener("keydown", e => {
                let d = -1;
                if (e.key === 'w') d = 0;
                else if (e.key === 'd') d = 1;
                else if (e.key === 's') d = 2;
                else if (e.key === 'a') d = 3;

                // 向后端发送请求
                if (d >= 0) {
                    this.store.state.pk.socket.send(JSON.stringify({
                        event: "move",
                        direction: d
                    }))
                }
            });
        }
    }

    start() {
        this.create_walls();
        this.add_listening_events();
    }

    update_size() {
        this.L = parseInt(Math.min(this.parent.clientWidth / this.cols, this.parent.clientHeight / this.rows));
        this.ctx.canvas.width = this.cols * this.L;
        this.ctx.canvas.height = this.rows * this.L;
    }

    check_ready() { // 判断两条蛇是否准备好下一回合了
        for (const snake of this.snakes) {
            if (snake.status !== "idle") return false;
            if (snake.direction === -1) return false;
        }
        return true;
    }

    check_tail_increasing() { // 检查是否增加蛇的长度
        if (this.step <= 10) return true;
        if (this.step % 3 === 1) return true;
        return false;
    }

    next_step() {
        for (const snake of this.snakes) {
            snake.next_step();
        }
    }

    check_valid(cell) {  // 检测目标位置是否合法：没有撞到两条蛇的身体和障碍物
        for (const wall of this.walls) {
            if (wall.r === cell.r && wall.c === cell.c)
                return false;
        }

        for (const snake of this.snakes) {
            let k = snake.cells.length;
            if (!snake.check_tail_increasing()) {  // 当蛇尾会前进的时候，蛇尾不要判断
                k--;
            }
            for (let i = 0; i < k; i++) {
                if (snake.cells[i].r === cell.r && snake.cells[i].c === cell.c)
                    return false;
            }
        }

        return true;
    }

    update() {
        this.update_size();
        if (this.check_ready()) {
            this.next_step();
        }
        this.render();
    }
    // 绘制地图
    render() {
        const color_even = "#AAD751", color_odd = "#A2D149";
        for (let r = 0; r < this.rows; r++) {
            for (let c = 0; c < this.cols; c++) {
                if ((r + c) % 2 == 0) {
                    this.ctx.fillStyle = color_even;
                } else {
                    this.ctx.fillStyle = color_odd;
                }
                this.ctx.fillRect(c * this.L, r * this.L, this.L, this.L);
            }
        }
    }
}