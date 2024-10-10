  <template>
  <div id="app">
    <!-- <MouseTrail /> -->
    <Particles
      id="tsparticles"
      :particlesInit="particlesInit"
      :particlesLoaded="particlesLoaded"
      :options="options"
    />
    <NavBar></NavBar>
    <router-view></router-view>
    <button class="feedback-button ui-btn" @click="goToFeedback">
      <span> 问题反馈 </span>
    </button>

    <div
      ref="draggable"
      @mousedown="startDragging"
      style="
        position: fixed; /* 固定定位 */
        bottom: 50px; /* 距离底部 50px */
        left: 50px; /* 距离左侧 50px */
        padding: 10px; /* 内边距 */
      "
    >
      <SmallNav></SmallNav>
    </div>
  </div>
</template>

<script setup>
import NavBar from "@/components/NavBar.vue";
import { loadSlim } from "tsparticles-slim"; // if you are going to use `loadSlim`, install the "tsparticles-slim" package too.
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap";
import { useRouter } from "vue-router";
import SmallNav from "./components/other/SmallNav.vue";
import { ref } from "vue";

// import MouseTrail from './components/MouseTrail.vue';
// 粒子库初始化
const particlesInit = async (engine) => {
  //await loadFull(engine);
  await loadSlim(engine);
};

const particlesLoaded = (container) => {
  console.log(container);
};

const router = useRouter(); // 使用 useRouter 获取路由实例

const goToFeedback = () => {
  router.push("/feedback"); // 使用 router.push 进行导航
};

// 粒子库 options 配置
const options = {
  background: {
    color: {
      // 背景颜色设为透明，以便使用 CSS 控制背景
      value: "transparent",
    },
  },
  // 设置最大帧率为120，确保性能
  fpsLimit: 120,

  // 定义鼠标与粒子之间的交互方式
  interactivity: {
    events: {
      // 鼠标点击事件
      onClick: {
        enable: true, // 启用点击事件
        mode: "push", // 点击时新增粒子
      },
      // 鼠标悬停事件
      onHover: {
        enable: true, // 启用悬停事件
        // 同时启用“吸引”和“抓取”效果
        mode: ["attract", "grab"], // 鼠标悬停时粒子被吸引并连线
      },
      // 允许在窗口大小改变时重新调整粒子
      resize: true,
    },
    // 定义交互模式的具体配置
    modes: {
      // 气泡模式，当鼠标接近时粒子会变大
      bubble: {
        distance: 250, // 水波纹效果的影响范围，点击处250px范围内的粒子会受影响
        size: 10, // 点击时粒子扩散后的最大尺寸
        duration: 2, // 水波纹效果持续2秒
        opacity: 0.8, // 水波纹效果下粒子的透明度变化
      },
      // 点击时新增粒子的数量
      push: {
        quantity: 2, // 每次点击增加5个粒子
      },
      // 鼠标排斥模式，粒子远离鼠标
      repulse: {
        distance: 200, // 粒子排斥距离为200px
        duration: 0.4, // 排斥效果持续时间为0.4秒
      },
      // 鼠标吸引模式，粒子靠近鼠标
      attract: {
        distance: 200, // 鼠标吸引粒子的范围为200px
        duration: 0.4, // 吸引效果持续时间为0.4秒
      },
      // 鼠标抓取模式，粒子之间会产生连线
      grab: {
        distance: 200, // 鼠标悬停时粒子之间连线的最大距离为200px
        line_linked: {
          opacity: 0.5, // 连线的透明度为0.5
        },
      },
    },
  },

  // 定义粒子的行为和外观
  particles: {
    // 粒子的颜色
    color: {
      value: "rgb(187, 170, 223)", // 粒子颜色为浅紫色
    },
    size: {
      value: { min: 2, max: 3 }, // 粒子的大小将在3px到7px之间随机
    },

    // 粒子之间的连线配置
    links: {
      color: "rgb(150, 149, 246)", // 连线颜色为蓝色
      distance: 150, // 粒子间连线的最大距离为150px
      enable: true, // 启用连线效果
      opacity: 0.8, // 连线透明度为0.8
      width: 1.5, // 连线宽度为1.5px
    },
    // 启用粒子之间的碰撞检测
    collisions: {
      enable: true,
    },
    // 粒子的移动配置
    move: {
      direction: "none", // 粒子移动无特定方向
      enable: true, // 启用粒子移动
      outModes: {
        default: "bounce", // 当粒子触碰边界时反弹
      },
      random: true, // 粒子的移动速度不随机
      speed: 5, // 粒子移动速度为1
      straight: false, // 粒子不会沿直线移动
    },
    // 粒子数量的配置
    number: {
      density: {
        enable: true, // 启用粒子密度调节，粒子数量会根据屏幕面积动态调整
        area: 800, // 每800px²区域内的粒子数量
      },
      value: 40, // 初始粒子数量为40
    },
    // 粒子的透明度配置
    opacity: {
      value: 1, // 粒子的透明度为0.5
    },
    // 粒子的形状配置
    shape: {
      type: "circle", // 粒子的形状为圆形
    },
  },

  // 启用高分辨率显示支持
  detectRetina: true,
};

// 拖动操作
const draggable = ref(null);
let offsetX = 0;
let offsetY = 0;

const startDragging = (e) => {
  offsetX = e.clientX - draggable.value.getBoundingClientRect().left;
  offsetY = e.clientY - draggable.value.getBoundingClientRect().top;

  document.addEventListener("mousemove", mouseMoveHandler);
  document.addEventListener("mouseup", mouseUpHandler);
  draggable.value.style.cursor = "grabbing"; // 更新鼠标样式
};

const mouseMoveHandler = (e) => {
  draggable.value.style.left = e.clientX - offsetX + "px";
  draggable.value.style.top = e.clientY - offsetY + "px";
};

const mouseUpHandler = () => {
  document.removeEventListener("mousemove", mouseMoveHandler);
  document.removeEventListener("mouseup", mouseUpHandler);
  draggable.value.style.cursor = "grab"; // 重置鼠标样式
};
</script>
<style>
/* 设置背景为渐变 */
/* 设置从上到下的渐变背景 */
#app {
  min-height: 100vh;
  width: 100vw;
  background: linear-gradient(
    to bottom,
    rgb(174, 190, 233),
    rgb(240, 197, 237)
  );
  /* background:radial-gradient(at 63.897428656118336% 2.1621973151393914%, hsla(198.00000000000003, 100%, 92.15686274509804%, 1) 0%, hsla(198.00000000000003, 100%, 92.15686274509804%, 0) 100%), radial-gradient(at 67.20125495169921% 75.66595958458151%, hsla(35.62499999999998, 20%, 68.62745098039215%, 1) 0%, hsla(35.62499999999998, 20%, 68.62745098039215%, 0) 100%), radial-gradient(at 3.9269843966381335% 36.283352471281695%, hsla(201.81818181818184, 59.139784946236574%, 81.76470588235294%, 1) 0%, hsla(201.81818181818184, 59.139784946236574%, 81.76470588235294%, 0) 100%); */
  background-attachment: fixed; /* 背景固定在视口，不随页面滚动 */
  background-size: cover;
  position: relative;
}

/* 确保粒子效果和内容在背景上正常显示 */
Particles {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
}

NavBar,
router-view {
  position: relative;
  z-index: 10; /* 让内容覆盖在粒子效果上 */
}

.feedback-button {
  position: fixed; /* 固定位置 */
  bottom: 20px; /* 距离底部20px */
  right: 20px; /* 距离右边20px */
  padding: 10px 15px; /* 按钮内边距 */
  background-color: #007bff; /* 按钮背景色 */
  color: white; /* 按钮文字颜色 */
  border: none; /* 去掉边框 */
  border-radius: 5px; /* 圆角 */
  cursor: pointer; /* 鼠标悬停时显示手型 */
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2); /* 添加阴影 */
}

.feedback-button:hover {
  background-color: #0056b3; /* 悬停时的背景色 */
}

router-view {
  min-height: 100%;
  display: flex;
  flex-direction: column;
}
/* From Uiverse.io by Galahhad */
.ui-btn {
  --btn-default-bg: rgb(41, 41, 41);
  --btn-padding: 15px 20px;
  --btn-hover-bg: rgb(51, 51, 51);
  --btn-transition: 0.3s;
  --btn-letter-spacing: 0.1rem;
  --btn-animation-duration: 1.2s;
  --btn-shadow-color: rgba(0, 0, 0, 0.137);
  --btn-shadow: 0 2px 10px 0 var(--btn-shadow-color);
  --hover-btn-color: #fac921;
  --default-btn-color: #fff;
  --font-size: 16px;
  /* 👆 this field should not be empty */
  --font-weight: 600;
  --font-family: Menlo, Roboto Mono, monospace;
  /* 👆 this field should not be empty */
}

/* button settings 👆 */

.ui-btn {
  box-sizing: border-box;
  padding: var(--btn-padding);
  display: flex;
  align-items: center;
  justify-content: center;
  color: var(--default-btn-color);
  font: var(--font-weight) var(--font-size) var(--font-family);
  background: var(--btn-default-bg);
  border: none;
  cursor: pointer;
  transition: var(--btn-transition);
  overflow: hidden;
  box-shadow: var(--btn-shadow);
}

.ui-btn span {
  letter-spacing: var(--btn-letter-spacing);
  transition: var(--btn-transition);
  box-sizing: border-box;
  position: relative;
  background: inherit;
}

.ui-btn span::before {
  box-sizing: border-box;
  position: absolute;
  content: "";
  background: inherit;
}

.ui-btn:hover,
.ui-btn:focus {
  background: var(--btn-hover-bg);
}

.ui-btn:hover span,
.ui-btn:focus span {
  color: var(--hover-btn-color);
}

.ui-btn:hover span::before,
.ui-btn:focus span::before {
  animation: chitchat linear both var(--btn-animation-duration);
}

@keyframes chitchat {
  0% {
    content: "#";
  }

  5% {
    content: ".";
  }

  10% {
    content: "^{";
  }

  15% {
    content: "-!";
  }

  20% {
    content: "#$_";
  }

  25% {
    content: "№:0";
  }

  30% {
    content: "#{+.";}35%{content: "@}-?";
  }

  40% {
    content: "?{4@%";
  }

  45% {
    content: "=.,^!";
  }

  50% {
    content: "?2@%";
  }

  55% {
    content: "\;1}]";
  }

  60% {
    content: "?{%:%";
    right: 0;
  }

  65% {
    content: "|{f[4";
    right: 0;
  }

  70% {
    content: "{4%0%";
    right: 0;
  }

  75% {
    content: "'1_0<";
    right: 0;
  }

  80% {
    content: "{0%";
    right: 0;
  }

  85% {
    content: "]>'";
    right: 0;
  }

  90% {
    content: "4";
    right: 0;
  }

  95% {
    content: "2";
    right: 0;
  }

  100% {
    content: "";
    right: 0;
  }
}
</style>
