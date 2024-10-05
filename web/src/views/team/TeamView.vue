<template>
    <div class="container mt-5">
      <!-- 第一部分：战队信息 -->
      <div class="row">
        <div class="col-md-8">
          <div class="card p-4 mb-3">
            <div class="d-flex align-items-center">
              <img :src="teamInfo.photo" class="rounded-circle" alt="team-logo" width="100" height="100" />
              <div class="ms-3">
                <h3>战队: {{ teamInfo.name }}</h3>
                <p class="text-muted">{{ teamInfo.slogan }}</p>
                <a :href="teamInfo.website" target="_blank">{{ teamInfo.website }}</a>
              </div>
            </div>
  
            <div class="mt-4">
              <p>队长: <strong>{{ teamInfo.leader }}</strong></p>
              <p>积分: <strong>{{ teamInfo.score }}</strong></p>
              <p>排位分数: <strong>{{ teamInfo.ranking }}</strong></p>
              <p>创建时间: <strong>{{ formatDate(teamInfo.createdAt) }}</strong></p>
              <p>战队状态: <span class="badge bg-success">{{ teamInfo.status }}</span></p>
            </div>
  
            <!-- 战队成就 -->
            <div class="mt-4 d-flex justify-content-between">
              <div v-for="achievement in teamInfo.achievements" :key="achievement.name" class="text-center">
                <img :src="achievement.icon" alt="achievement-icon" width="50" />
                <p>{{ achievement.name }}</p>
              </div>
            </div>
          </div>
        </div>
  
        <!-- 第二部分：战队成员列表 -->
        <div class="col-md-4">
          <div class="card p-4 mb-3">
            <h5>战队成员</h5>
            <ul class="list-group">
              <li v-for="member in teamMembers" :key="member.id" class="list-group-item d-flex justify-content-between align-items-center">
                <div class="d-flex align-items-center">
                  <img :src="member.photo" class="rounded-circle me-2" width="50" height="50" />
                  <div>
                    <strong>{{ member.name }}</strong>
                    <p class="mb-0"><span class="badge bg-info">{{ member.role }}</span></p>
                  </div>
                </div>
                <span class="badge bg-warning text-dark">{{ member.score }}</span>
              </li>
            </ul>
          </div>
        </div>
      </div>
  
      <!-- 第三部分：解题动态 -->
      <div class="card p-4 mt-3">
        <h5>解题动态</h5>
        <ul class="list-group">
          <li v-for="activity in activities" :key="activity.id" class="list-group-item">
            {{ activity.name }} 破译了 {{ activity.task }} - {{ activity.daysAgo }}天前
          </li>
        </ul>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        teamInfo: {
          photo: 'https://via.placeholder.com/100',
          name: 'WhiteHat',
          slogan: '渗透人，万为上人',
          website: 'https://www.whlhc.top/',
          leader: 'dotast',
          score: 7645,
          ranking: '0.00',
          createdAt: '2020-12-08 22:36:59',
          status: '正常',
          achievements: [
            { name: 'AWD公测赛优秀奖', icon: 'https://via.placeholder.com/50' },
            { name: 'AWD-S1耀辉钻石', icon: 'https://via.placeholder.com/50' },
            { name: 'AWD-S2荣耀黄金', icon: 'https://via.placeholder.com/50' },
          ]
        },
        teamMembers: [
          { id: 1, name: 'xwhat', role: '成员', score: 305, photo: 'https://via.placeholder.com/50' },
          { id: 2, name: 'dotast', role: '队长', score: 5000, photo: 'https://via.placeholder.com/50' },
          { id: 3, name: 'cheyenne', role: '成员', score: 5035, photo: 'https://via.placeholder.com/50' },
          { id: 4, name: 'wjhwjhn', role: '副队长', score: 3225, photo: 'https://via.placeholder.com/50' },
          { id: 5, name: 'Y4tacker', role: '副队长', score: 640, photo: 'https://via.placeholder.com/50' },
          { id: 6, name: 'atao', role: '成员', score: 0, photo: 'https://via.placeholder.com/50' },
          { id: 7, name: 'spaceman', role: '成员', score: 360, photo: 'https://via.placeholder.com/50' },
          { id: 8, name: 'bit', role: '成员', score: 2255, photo: 'https://via.placeholder.com/50' },
          { id: 9, name: 'NN来了', role: '成员', score: 25, photo: 'https://via.placeholder.com/50' },
        ],
        activities: [
          { id: 1, name: 'cheyenne', task: '奇奇怪怪的语言', daysAgo: 4 },
          { id: 2, name: 'cheyenne', task: 'Find_password', daysAgo: 4 },
        ]
      };
    },
    methods: {
      formatDate(dateString) {
        return dateString.split(' ')[0]; // 只显示日期部分
      }
    }
  }
  </script>
  
  <style scoped>
  img {
    object-fit: cover;
  }
  </style>
  