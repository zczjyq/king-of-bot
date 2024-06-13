import { AcGameObjects } from "./AcGameObject";
import { Cell } from "./Cell";

export class Snake extends AcGameObjects {
    constructor(info, gamemap) {
        super();
        this.check_whoai = false;
        this.a_id;
        this.b_id;
        this.myid;
        this.id = info.id;
        this.color = info.color;
        this.gamemap = gamemap;

        this.cells = [new Cell(info.r, info.c)]; // 存放蛇的身体
        this.next_cell = null;

        this.speed = 5;
        this.direction = -1; // -1 表示没有指令
        this.status = "idle" // idle 表示静止

        this.dr = [-1, 0, 1, 0]; // 4个方向行的偏移量
        this.dc = [0, 1, 0, -1]; // 4个方向列的偏移量

        this.step = 0;
        this.eps = 1e-2;

        this.eye_direction = 0;
        if (this.id === 1) this.eye_direction = 2;

        this.eye_dx = [  // 蛇眼睛不同方向的x的偏移量
            [-1, 1],
            [1, 1],
            [1, -1],
            [-1, -1],
        ];
        this.eye_dy = [  // 蛇眼睛不同方向的y的偏移量
            [-1, -1],
            [-1, 1],
            [1, 1],
            [1, -1],
        ]
    }

    start() {

    }

    set_direction(d) {
        this.direction = d;
    }

    set_whoai(check) {
        this.check_whoai = check;
    }

    set_id(a_id, b_id, myid) {
        this.a_id = a_id;
        this.b_id = b_id;
        this.myid = myid;
    }

    check_tail_increasing() { // 检查是否增加蛇的长度
        if (this.step <= 10) return true;
        if (this.step % 3 === 1) return true;
        return false;
    }

    next_step() { // 将蛇的状态变成走下一步
        const d = this.direction;
        this.next_cell = new Cell(this.cells[0].r + this.dr[d], this.cells[0].c + this.dc[d]);
        this.eye_direction = d; // 更新蛇头方向
        this.direction = -1;
        this.status = 'move';
        this.step++;

        const k = this.cells.length;
        for (let i = k; i > 0; i--) {
            this.cells[i] = JSON.parse(JSON.stringify(this.cells[i - 1]));
        }

        // if (!this.gamemap.check_valid(this.next_cell)) { // 下一步操作非法直接去世
        //     this.status = "die";
        // }
    }

    update_move() {
        const dx = this.next_cell.x - this.cells[0].x;
        const dy = this.next_cell.y - this.cells[0].y;
        const distance = Math.sqrt(dx * dx + dy * dy);

        if (distance < this.eps) {  // 走到目标点了
            this.cells[0] = this.next_cell;  // 添加一个新蛇头
            this.next_cell = null;
            this.status = "idle";  // 走完了，停下来

            if (!this.check_tail_increasing()) {  // 蛇不变长
                this.cells.pop();
            }
        } else { // 没走到目标点了
            const move_distance = this.speed * this.timedelta / 1000;  // 每两帧之间走的距离
            this.cells[0].x += move_distance * dx / distance;
            this.cells[0].y += move_distance * dy / distance;

            // 判断尾部是否要增加
            if (!this.check_tail_increasing()) {
                const k = this.cells.length;
                const tail = this.cells[k - 1], tail_target = this.cells[k - 2];
                const tail_dx = tail_target.x - tail.x;
                const tail_dy = tail_target.y - tail.y;
                tail.x += move_distance * tail_dx / distance;
                tail.y += move_distance * tail_dy / distance;
            }
        }
        // this.cells[0].x += this.speed * this.timedelta / 1000;
    }

    update() {
        if (this.status === "move") {
            this.update_move();
        }

        this.render();
    }

    render() {
        console.log(this.a_id, this.b_id, this.myid);
        // console.log(this.check_whoai);
        const L = this.gamemap.L;
        const ctx = this.gamemap.ctx;
        // console.log(this.check_whoai, this);


        
        ctx.fillStyle = this.color;
        // 如果死了就将颜色变成白色
        if (this.status === "die") {
            this.color = "white";
        }


        for (const cell of this.cells) {
            ctx.beginPath();
            ctx.arc(cell.x * L, cell.y * L, L / 2 * 0.8, 0, Math.PI * 2)
            ctx.fill();
        }

        for (let i = 1; i < this.cells.length; i++) {
            const a = this.cells[i - 1], b = this.cells[i];
            if (Math.abs(a.x - b.x) < this.eps && Math.abs(a.y - b.y) < this.eps)
                continue;
            if (Math.abs(a.x - b.x) < this.eps) {
                ctx.fillRect((a.x - 0.4) * L, Math.min(a.y, b.y) * L, L * 0.8, Math.abs(a.y - b.y) * L);
            } else {
                ctx.fillRect(Math.min(a.x, b.x) * L, (a.y - 0.4) * L, Math.abs(a.x - b.x) * L, L * 0.8);
            }
        }

        ctx.fillStyle = "black";
        for (let i = 0; i < 2; i++) {
            const eye_x = (this.cells[0].x + this.eye_dx[this.eye_direction][i] * 0.15) * L;
            const eye_y = (this.cells[0].y + this.eye_dy[this.eye_direction][i] * 0.15) * L;

            ctx.beginPath();
            ctx.arc(eye_x, eye_y, L * 0.05, 0, Math.PI * 2);
            ctx.fill();
        }

        if (this.check_whoai) {
            const lastCell = this.cells[0]; // 获取最后一个元素的坐标
            ctx.fillStyle = "black"; // 设置文字颜色为黑色
            ctx.font = "bold 12px Arial"; // 设置文字样式
            ctx.fillText("我", lastCell.x * L, (lastCell.y) * L); // 在坐标上方写文字“我”
        } else {
            const lastCell = this.cells[0]; // 获取最后一个元素的坐标
            ctx.fillStyle = "black"; // 设置文字颜色为黑色
            ctx.font = "bold 12px Arial"; // 设置文字样式
            ctx.fillText("对手", lastCell.x * L, (lastCell.y) * L); // 在坐标上方写文字“我”
        }
    }
}