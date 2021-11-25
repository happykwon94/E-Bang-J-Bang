<template>
  <div class="notice-content col-6">
    <h4>공지사항</h4>
    <table class="table table-notice table-hover table-sm">
      <thead>
        <tr>
          <th scope="col">No</th>
          <th scope="col">제목</th>
          <th scope="col">작성자</th>
          <th scope="col">작성일</th>
        </tr>
      </thead>
      <tbody id="tableBody">
        <notice-board-list-row v-for="(notice, idx) in notices" :key="idx" v-bind="notice"></notice-board-list-row>
      </tbody>
    </table>
  </div>
</template>

<script>
import { listNotice } from "@/api/notice.js";
import NoticeBoardListRow from "@/components/notice/child/NoticeBoardListRow.vue";

export default {
  name: "NoticeBar",
  data() {
    return {
      notices: [],
    };
  },
  components: {
    NoticeBoardListRow,
  },
  created() {
    listNotice(
      (response) => {
        this.notices = response.data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
};
</script>

<style>
.notice-content {
  border-left: 1px solid rgba(0, 0, 0, 0.1);
}

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
</style>
