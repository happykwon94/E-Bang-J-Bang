<template>
  <div>
    <div v-if="this.bookMarkList">
      <!-- {{ bookMarkList }} -->
      <book-mark-list-row
        v-bind="{ bookmark: bookmark, userNo: userInfo.no }"
        v-for="(bookmark, index) in bookMarkList"
        :key="index"
      />
      <div class="btn-cover mt-3">
        <button
          id="pre"
          :disabled="pageNum === 0"
          @click="prevPage"
          class="page-btn btn btn-sm btn-outline-dark"
        >
          이전
        </button>
        <span id="page" class="page-count"
          >{{ pageNum + 1 }} / {{ pageCount }} 페이지</span
        >
        <button
          id="next"
          :disabled="pageNum >= pageCount - 1"
          @click="nextPage"
          class="page-btn btn btn-sm btn-outline-dark"
        >
          다음
        </button>
      </div>
    </div>
    <div v-else>
      <div class="bookmarkBox">비어있습니다.</div>
    </div>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
import BookMarkListRow from "./BookMarkListRow.vue";
const houseStore = "houseStore";
const userStore = "userStore";
export default {
  components: { BookMarkListRow },
  name: "BookMarkList",
  data() {
    return {
      pageNum: 0,
    };
  },
  props: {
    pageSize: {
      type: Number,
      required: false,
      default: 9,
    },
  },
  created() {
    this.getBookMarkList(this.userInfo.no);
  },
  computed: {
    ...mapState(houseStore, ["bookMarkList"]),
    ...mapState(userStore, ["userInfo"]),
    pageCount() {
      let listLeng = this.bookMarkList.length,
        listSize = this.pageSize,
        page = Math.floor(listLeng / listSize);
      if (listLeng % listSize > 0) page += 1;
      return page;
    },
    paginatedData() {
      const start = this.pageNum * this.pageSize,
        end = start + this.pageSize;
      return this.bookMarkList.slice(start, end);
    },
  },
  methods: {
    ...mapActions(houseStore, ["getBookMarkList"]),
    nextPage() {
      this.pageNum += 1;
    },
    prevPage() {
      this.pageNum -= 1;
    },
  },
  watch: {
    bookMarkList() {
      console.log("왜 무한반복..?");
      this.getBookMarkList(this.userInfo.no);
    },
  },
};
</script>

<style>
.bookmarkBox {
  border: 1px solid rgb(213, 213, 213);
  padding: 15px;
  font-size: 13px;
}
</style>
