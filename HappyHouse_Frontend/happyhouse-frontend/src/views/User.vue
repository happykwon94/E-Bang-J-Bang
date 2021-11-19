<template>
  <router-view></router-view>
</template>

<script>
export default {
  name: "User",
};
</script>

<style>
* {
  font-family: "Noto Sans KR", sans-serif;
}

.login-body {
  width: 500px;
  padding-top: 50px;
}

.form-login {
  border: 1px solid rgb(194, 189, 189);
  width: 400px;
  padding: 50px;
  margin: auto;
}

.div-login {
  margin-bottom: 30px;
}

input.form-control1 {
  margin-bottom: -1px;
  border-bottom-right-radius: 0;
  border-bottom-left-radius: 0;
}

input.form-control2 {
  border-top-right-radius: 0;
  border-top-left-radius: 0;
  margin-bottom: 10px;
}

.login-d {
  margin-top: 25px;
  margin-bottom: 25px;
}
</style>
