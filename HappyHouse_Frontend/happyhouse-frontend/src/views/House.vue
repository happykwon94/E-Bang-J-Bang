<template>
  <div>
    <search-bar />
    <div class="mb-2 container">
      <div id="map-container">
        <h3 style="font-weight: bold">검색 결과</h3>
        <div class="search row mb-3">
          <div class="input-group" style="width: 100%">
            <input
              type="number"
              id="maxPrice"
              class="form-control searchbar"
              placeholder="최대 매매가(만원)"
              v-model="price"
            />
            <div class="input-group-append" style="margin-right: 30px">
              <button
                type="button"
                class="btn btn-outline-dark"
                @click="searchPrice"
              >
                가격 검색
              </button>
            </div>
            <input
              type="month"
              class="form-control searchbar"
              id="month"
              v-model="yearMonth"
            />
            <div class="input-group-append">
              <button
                type="button"
                class="btn btn-outline-dark"
                id="btnMonth"
                @click="searchYearMonth"
              >
                연,월 검색
              </button>
            </div>
          </div>
        </div>
        <div class="row-12" v-if="this.houseDeal != null">
          <house-detail />
        </div>
        <div class="row-12" v-else>
          <div class="row">
            <div class="col">
              <house-list />
            </div>
            <div class="col-8">
              <house-map />
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions, mapMutations, mapState } from "vuex";
import HouseDetail from "../components/house/HouseDetail.vue";
import HouseList from "../components/house/HouseList.vue";
import HouseMap from "../components/house/HouseMap.vue";
import SearchBar from "../components/layout/SearchBar.vue";
const houseStore = "houseStore";
export default {
  name: "House",
  data() {
    return {
      price: "",
      yearMonth: "",
    };
  },
  created() {
    this.price = "";
  },
  components: {
    SearchBar,
    HouseMap,
    HouseList,
    HouseDetail,
  },
  computed: {
    ...mapState(houseStore, ["houses", "houseDeal", "gugunCode", "isGetData"]),
  },
  methods: {
    ...mapActions(houseStore, ["getAptListPrice", "getAptListYearMonth"]),
    ...mapMutations(houseStore, [
      "SET_APT_LIST",
      "SET_APT_INFO",
      "SET_APT_DEAL",
    ]),
    searchPrice() {
      alert("searchPrice()");
      alert(this.price);
      this.getAptListPrice({
        dongCode: this.houses[0].dongCode,
        price: this.price,
      });
    },
    searchYearMonth() {
      console.log(this.houses);
      this.getAptListYearMonth({
        gugunCode: this.gugunCode,
        date: this.yearMonth,
      });
    },
  },
};
</script>

<style>
.col-8,
.col {
  padding: 1px;
  margin: 1px;
}
</style>
