<template>
  <div>
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
    <div>
      <button v-if="userInfo != null && userInfo.auth >= 20" type="button" class="btn btn-outline-success col-2" @click="moveWriteForm">공지사항 작성</button>
    </div>
  </div>
</template>

<script>
import NoticeBoardListRow from "@/components/notice/child/NoticeBoardListRow.vue";
import { listNotice } from "@/api/notice.js";
import { mapState } from "vuex";

const userStore = "userStore";

export default {
  name: "NoticeBoardList",
  data() {
    return {
      notices: [],
    };
  },
  components: {
    NoticeBoardListRow,
  },
  computed: {
    ...mapState(userStore, ["isLogin", "userInfo"]),
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
  methods: {
    moveWriteForm() {
      this.$router.push({ name: "NoticeBoardWrite" });
    },
  },
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
</style>
