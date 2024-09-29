<template>
    <div class="line-chart-container">
      <canvas id="lineChart" width="900" height="300"></canvas> <!-- 设置宽度 -->
    </div>
  </template>
  
  
  <script setup>
  import { onMounted } from 'vue';
  import {
    Chart,
    LineElement,
    PointElement,
    LinearScale,
    CategoryScale,
    Title,
    Tooltip,
    Legend,
    LineController
  } from 'chart.js';
  
  // 注册 Chart.js 组件
  Chart.register(LineElement, PointElement, LinearScale, CategoryScale, Title, Tooltip, Legend, LineController);
  
  onMounted(() => {
    const ctx = document.getElementById('lineChart').getContext('2d');
    new Chart(ctx, {
      type: 'line', // 设置图表类型为折线图
      data: {
        labels: ['Week 1', 'Week 2', 'Week 3', 'Week 4', 'Week 5', 'Week 6'], // X轴标签
        datasets: [{
          label: '天梯分',
          data: [1200, 1250, 1300, 1350, 1400, 1000], // Y轴数据
          fill: false,
          borderColor: 'rgba(75, 192, 192, 1)',
          tension: 0.4,
          pointRadius: 5, // 数据点半径
          pointBackgroundColor: 'rgba(75, 192, 192, 1)', // 数据点背景色
        }]
      },
      options: {
        responsive: true, // 响应式设计
        plugins: {
          legend: {
            display: true,
            position: 'top',
          },
          tooltip: {
            enabled: true,
          }
        },
        scales: {
          x: {
            type: 'category', // 指定 x 轴的类型为 category
          },
          y: {
            beginAtZero: true, // Y轴从0开始
            min: 900, // Y轴的最小值
            max: 1500, // Y轴的最大值
          }
        },
        animation: {
            duration: 1000, // 动画持续时间
            easing: "easeInOutBounce", // 动画的效果
          },
      }
    });
  });
  </script>
  
  <style scoped>
  .line-chart-container {
    width: 1000px; /* 容器宽度 */
    height: 300px; /* 设置高度 */
  }
  </style>
  