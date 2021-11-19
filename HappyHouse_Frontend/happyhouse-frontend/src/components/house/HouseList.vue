<template>
  <!-- 주택 정보 -->
  <div id="searchInfo" v-if="houses && houses.length != 0">
    <div class="row aptlist">
      <div class="col-4 text-left">일련 번호</div>
      <div class="col text-left">아파트 명</div>
    </div>
    <div class="row" v-for="house in paginatedData" :key="house.aptCode">
      <div class="col-4 text-left">{{ house.aptCode }}</div>
      <div class="col text-left">{{ house.aptName }}</div>
    </div>
    <div class="btn-cover mt-3">
      <button
        :disabled="pageNum === 0"
        @click="prevPage"
        class="page-btn btn btn-outline-dark mr-3"
      >
        이전
      </button>
      <span class="page-count">{{ pageNum + 1 }} / {{ pageCount }} 페이지</span>
      <button
        :disabled="pageNum >= pageCount - 1"
        @click="nextPage"
        class="page-btn btn btn-outline-dark ml-3"
      >
        다음
      </button>
    </div>
  </div>
  <div id="searchInfo" v-else>
    <div class="row">
      <div class="col">아파트 목록이 없습니다.</div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
const houseStore = "houseStore";
export default {
  name: "HouseList",
  components: {},
  data() {
    return {
      pageNum: 0,
    };
  },
  props: {
    pageSize: {
      type: Number,
      required: false,
      default: 18,
    },
  },
  methods: {
    nextPage() {
      this.pageNum += 1;
    },
    prevPage() {
      this.pageNum -= 1;
    },
  },
  computed: {
    ...mapState(houseStore, ["houses"]),
    pageCount() {
      let listLeng = this.houses.length,
        listSize = this.pageSize,
        page = Math.floor(listLeng / listSize);
      if (listLeng % listSize > 0) page += 1;
      return page;
    },
    paginatedData() {
      const start = this.pageNum * this.pageSize,
        end = start + this.pageSize;
      return this.houses.slice(start, end);
    },
  },
};
</script>

<style>
.aptlist {
  border-bottom: 1px solid rgb(224, 224, 224);
}
</style>
