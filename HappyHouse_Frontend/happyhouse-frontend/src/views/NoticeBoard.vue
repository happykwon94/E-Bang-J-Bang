<template>
  <div id="body-div" class="container mt-5 notice-body">
    <form class="form-notice">
      <div id="notice">
        <h1>공지사항</h1>
        <router-view></router-view>
      </div>
    </form>
  </div>
</template>

<script>
export default {
  name: "NoticeBoard",
};
</script>

<style>
table tr {
  text-align: center;
}

table tr:first-child th:nth-child(1) {
  width: 10%;
}
table tr:first-child th:nth-child(2) {
  width: 60%;
}
table tr:first-child th:nth-child(3) {
  width: 15%;
}
table tr:first-child th:nth-child(4) {
  width: 15%;
}

.btn {
  float: right;
  margin-left: 10px;
}

#detail-table {
  width: 100%;
  margin-top: 30px;
  border-collapse: separate;
  border-spacing: 0 20px;
}
</style>
