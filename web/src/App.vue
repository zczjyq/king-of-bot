<template>
  <NavBar></NavBar>
  <router-view></router-view>
</template>

<script>
import NavBar from "@/components/NavBar.vue";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap";
// import $ from "jquery";

export default {
  components: {
    NavBar,
  },
  setup() {
    // $.ajax({
    //   url: "http://localhost:3000/user/account/token/",
    //   type: "post",
    //   data: {
    //     username: "cjq",
    //     password: "123",
    //   },
    //   success(resp) {
    //     console.log(resp);
    //   },
    // });
    // $.ajax({
    //   url: "http://localhost:3000/user/account/info/",
    //   type: "get",
    //   headers: {
    //     Authorization:
    //       "Bearer " +
    //       "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIzMjEzZTEzYTlmM2Y0ZjU0ODU5NGIwMzM2ZDBlZDY3ZSIsInN1YiI6IjEiLCJpc3MiOiJzZyIsImlhdCI6MTcwOTAyMDg1OSwiZXhwIjoxNzEwMjMwNDU5fQ._nDORF7ikd9PZeg2TSElrzrsa5ephV5nUvOPoN12FDI",
    //   },
    //   success(resp) {
    //     console.log(resp);
    //   },
    // });
    // $.ajax({
    //   url: "http://localhost:3000/user/account/register/",
    //   type: "post",
    //   data: {
    //     username: "cjq1",
    //     password: "123",
    //     comfirmedPassword: "123",
    //   },
    //   success(resp) {
    //     console.log(resp);
    //   },
    // });
  },
};
</script>
  
<style>
body {
  background-image: url("@/assets/img/background.png");
  background-size: cover;
}
</style>
 