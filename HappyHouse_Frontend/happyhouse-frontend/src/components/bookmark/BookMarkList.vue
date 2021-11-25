<template>
  <div class="book-mark-list mb-5">
    <div v-if="showCompare">
      <book-mark-compare v-bind:homes="this.homes" />
    </div>
    <div v-if="this.bookMarkList">
      <!-- {{ bookMarkList }} -->
      <book-mark-list-row
        v-on:compare-house="compareHouse"
        v-bind="{ bookmark: bookmark, userNo: userInfo.no }"
        v-for="(bookmark, index) in getterBookMarkList"
        :key="index"
      />
      <!-- <div class="mt-5 paging mb-5">
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
      </div> -->
    </div>
    <div v-else>
      <div class="bookmarkBox">비어있습니다.</div>
    </div>
  </div>
</template>

<script>
import { mapActions, mapState, mapGetters } from "vuex";
import BookMarkCompare from "./BookMarkCompare.vue";
import BookMarkListRow from "./BookMarkListRow.vue";
const houseStore = "houseStore";
const userStore = "userStore";
export default {
  components: { BookMarkListRow, BookMarkCompare },
  name: "BookMarkList",
  data() {
    return {
      pageNum: 0,
      homes: [],
      showCompare: false,
      re: false,
    };
  },
  props: {
    pageSize: {
      type: Number,
      required: false,
      default: 6,
    },
  },
  created() {
    this.getBookMarkList(this.userInfo.no);
  },
  computed: {
    ...mapState(houseStore, ["bookMarkList"]),
    ...mapState(userStore, ["userInfo"]),
    ...mapGetters(houseStore, ["getterBookMarkList"]),
    pageCount() {
      let listLeng = this.getterBookMarkList.length,
        listSize = this.pageSize,
        page = Math.floor(listLeng / listSize);
      if (listLeng % listSize > 0) page += 1;
      return page;
    },
    paginatedData() {
      const start = this.pageNum * this.pageSize,
        end = start + this.pageSize;
      return this.getterBookMarkList.slice(start, end);
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
    compareHouse(bookmark) {
      this.re = false;
      if (this.homes != []) {
        //이미 배열이 차있으면 검사
        this.homes.forEach((home) => {
          if (home.aptCode == bookmark.aptCode) {
            // 삭제
            this.editHomes(bookmark);
            this.re = true;
          }
        });
        if (!this.re) {
          this.homes.push(bookmark);
        }
      } else {
        this.homes.push(bookmark);
      }
      if (this.homes.length == 2) {
        this.compare(true);
      } else if (this.homes.length < 2) {
        this.compare(false);
      } else {
        alert("비교하기는 두개 선택해주세요!");
        this.compare(false);
      }
    },
    editHomes(bookmark) {
      this.homes = this.homes.filter((home) => {
        return home.aptCode != bookmark.aptCode;
      });
    },
    compare(res) {
      this.showCompare = res;
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

.paging {
  float: right;
}

.page-btn {
  margin-right: 20px;
}

</style>
