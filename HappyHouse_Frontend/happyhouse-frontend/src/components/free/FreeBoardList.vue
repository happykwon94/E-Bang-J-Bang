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
        <free-board-list-row v-for="(freeBoard, idx) in freeBoardList" :key="idx" v-bind="freeBoard"></free-board-list-row>
      </tbody>
    </table>
    <button type="button" class="btn btn-primary" @click="moveWriteForm">글 쓰기</button>
  </div>
</template>

<script>
import FreeBoardListRow from "@/components/free/child/FreeBoardListRow.vue";
import { listFree } from "@/api/free.js";

export default {
  name: "FreeBoardList",
  data() {
    return {
      freeBoardList: [],
    };
  },
  components: {
    FreeBoardListRow,
  },
  methods: {
    moveWriteForm() {
      this.$router.push({ name: "FreeBoardWrite" });
    },
  },
  created() {
    listFree(
      (response) => {
        this.freeBoardList = response.data;
      },
      (error) => {
        console.log(error);
      }
    );
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

button {
  float: right;
}
</style>
