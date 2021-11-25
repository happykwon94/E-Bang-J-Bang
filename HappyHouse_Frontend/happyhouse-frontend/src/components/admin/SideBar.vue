<template>
  <div class="col-lg-2">
    <div class="icon-bar">
      <router-link :to="{ name: 'UserList' }">회원관리</router-link>
      <router-link :to="{ name: 'QuestionList' }">문의사항 관리</router-link>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
.icon-bar {
  height: 100%;
  /*z-index: 1;*/ /* Stay on top */
  top: 0; /* Stay at the top */
  left: 0;
  overflow-x: hidden; /* Disable horizontal scroll */
  padding-top: 0;
}

.icon-bar a {
  display: block;
  text-align: center;
  padding: 15px;
  transition: all 0.3s ease;
  color: black;
  font-size: 20px;
  font-family: "jua";
}

.icon-bar a:hover {
  background-color: #e3e3e3;
}

a.router-link-exact-active {
  color: #42b983;
}
</style>
