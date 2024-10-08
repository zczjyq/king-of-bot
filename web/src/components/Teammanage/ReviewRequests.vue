<template>
    <div>
      <h6>申请审核</h6>
      <ul class="list-group mt-3">
        <li
          v-for="request in requests"
          :key="request.id"
          class="list-group-item d-flex justify-content-between align-items-center"
        >
          <span>{{ request.name }} 请求加入战队</span>
          <button class="btn btn-sm btn-success">通过</button>
        </li>
      </ul>
    </div>
  </template>
  
  <script>
  export default {
    name: "ReviewRequests",
    data() {
      return {
        requests: [
          { id: 1, name: "申请人1" },
          { id: 2, name: "申请人2" },
        ],
      };
    },
  };
  </script>
  