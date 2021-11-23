<template>
  <!-- 주택 정보 -->
  <div id="searchInfo" v-if="houses && houses.length != 0">
    <div class="row aptlist">
      <div class="col text-left">일련번호</div>
      <div class="col text-left">아파트 명</div>
      <div class="col text-left">가격</div>
    </div>
    <div
      class="row listOne"
      v-for="house in paginatedData"
      :key="house.aptCode"
      v-show="!isGetData"
      @click="goMarker(house)"
    >
      <div class="col text-left">{{ house.aptCode }}</div>
      <div class="col text-left">{{ house.aptName }}</div>
      <div class="col text-left">{{ house.recentPrice }}</div>
    </div>
    <div
      class="row listOne"
      v-for="(house, index) in paginatedData"
      :key="index"
      v-show="isGetData"
      @click="goMarker(house)"
    >
      <div class="col text-left">{{ house.일련번호 }}</div>
      <div class="col text-left">{{ house.아파트 }}</div>
      <div class="col text-left">{{ house.거래금액 }}</div>
    </div>
    <div class="btn-cover mt-3">
      <button
        id="pre"
        :disabled="pageNum === 0"
        @click="prevPage"
        class="page-btn btn btn-sm btn-outline-dark m"
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
  <div id="searchInfo" v-else>
    <div class="row">
      <div class="col">아파트 목록이 없습니다.</div>
    </div>
  </div>
</template>

<script>
import { mapMutations, mapState } from "vuex";
const houseStore = "houseStore";
export default {
  name: "HouseList",
  components: {},
  data() {
    return {
      pageNum: 0,
      isClick: false,
      color: "rgb(194, 216, 255)",
    };
  },
  props: {
    pageSize: {
      type: Number,
      required: false,
      default: 14,
    },
  },
  methods: {
    ...mapMutations(houseStore, ["SET_LAT_LNG"]),
    nextPage() {
      this.pageNum += 1;
    },
    prevPage() {
      this.pageNum -= 1;
    },
    goMarker(house) {
      // getMove(house)
      console.log(house);
      this.SET_LAT_LNG({ lat: house.lat, lng: house.lng });
    },
  },
  computed: {
    ...mapState(houseStore, ["houses", "isGetData"]),
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
  padding: 3px;
}

#searchInfo {
  font-size: 14px;
}

#pre,
#page #next {
  float: none;
}

#page {
  padding-left: 75px;
}

.listOne > *:hover {
  background-color: rgb(220, 239, 255);
}
</style>
