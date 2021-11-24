<template>
  <div class="search-bar-sm-2-body">
    <div class="input-group">
      <div class="search-bar-price">
        <input
          type="number"
          id="maxPrice"
          class="form-control"
          placeholder="최대 매매가(만원)"
          v-model="price"
          style="width: 200px"
        />
        <button type="button" class="btn btn-outline" @click="searchPrice">
          가격 검색
        </button>
      </div>
      <div class="search-bar-day">
        <input
          type="month"
          class="form-control"
          id="month"
          v-model="yearMonth"
          style="width: 200px"
        />
        <button
          type="button"
          class="btn btn-outline"
          id="btnMonth"
          @click="searchYearMonth"
        >
          연,월 검색
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions, mapMutations, mapState } from "vuex";

const houseStore = "houseStore";

export default {
  name: "SearchBar",
  data() {
    return {
      yearMonth: "",
      price: "",
    };
  },
  created() {
    this.price = "";
  },
  computed: {
    ...mapState(houseStore, ["isGetData", "gugunCode"]),
  },
  methods: {
    ...mapActions(houseStore, ["getAptListPrice", "getAptListYearMonth"]),
    ...mapMutations(houseStore, [
      "SET_APT_LIST",
      "SET_APT_INFO",
      "SET_APT_DEAL",
      "SET_PRICE",
    ]),
    searchPrice() {
      this.SET_PRICE(this.price);

      // this.getAptListPrice({
      //   dongCode: this.houses[0].dongCode,
      //   price: this.price,
      // });
    },
    searchYearMonth() {
      // console.log(this.houses);
      this.getAptListYearMonth({
        gugunCode: this.gugunCode,
        date: this.yearMonth,
      });
    },
  },
};
</script>

<style>
.search-bar-price,
.search-bar-day {
  display: flex;
  padding-bottom: 5px;
}

button {
  width: 100px;
}
</style>
