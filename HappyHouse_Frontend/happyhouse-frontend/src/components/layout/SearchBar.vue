!
<template>
  <div class="main-body container-full" style="margin-bottom: 50px">
    <div
      class="container-fluid"
      :style="{
        backgroundImage: `url(${require('@/assets/main2.png')})`,
        width: '100%',
        backgroundSize: 'cover',
        height: '300px',
        backgroundPosition: '50%',
      }"
    >
      <div
        class="container searchbar fade-in-box"
        style="padding-top: 150px; text-align: center"
      >
        <h2 style="color: white"><strong>지역 매물 검색</strong></h2>
        <div class="input-group mb-3">
          <b-form-select
            class="form-control searchbar"
            id="sido"
            v-model="sidoCode"
            @change="gugunList"
          >
            <option
              v-for="(sido, index) in sidos"
              :key="index"
              :value="sido.sidoCode"
              selected
            >
              {{ sido.sidoName }}
            </option>
          </b-form-select>
          <select
            class="form-control searchbar"
            name=""
            id="gugun"
            v-model="gugunCode"
            @change="dongList"
          >
            <option
              v-for="(gugun, index) in guguns"
              :key="index"
              :value="gugun.gugunCode"
            >
              {{ gugun.gugunName }}
            </option>
          </select>
          <select
            class="form-control searchbar"
            name=""
            id="dong"
            v-model="dongCode"
            @change="aptList"
          >
            <option
              v-for="(dong, index) in dongs"
              :key="index"
              :value="dong.dongCode"
            >
              {{ dong.dongName }}
            </option>
          </select>
          <div class="input-group-append">
            <button
              type="button"
              id="lookMap"
              class="btn btn-secondary btn-outline-light"
            >
              지도보기
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";

const houseStore = "houseStore";

export default {
  name: "SearchBar",
  data() {
    return {
      sidoCode: "",
      gugunCode: "",
      dongCode: "",
    };
  },
  created() {
    this.sidoList();
  },
  methods: {
    ...mapActions(houseStore, ["getSido", "getGugun", "getDong", "getAptList"]),
    sidoList() {
      // console.log("sidoList")
      this.getSido();
    },
    gugunList() {
      // console.log(this.sidoCode);
      this.getGugun(this.sidoCode);
    },
    dongList() {
      this.getDong(this.gugunCode);
    },
    aptList() {
      this.getAptList(this.dongCode);
    },
  },
  computed: {
    ...mapState(houseStore, ["sidos", "guguns", "dongs"]),
  },
};
</script>

<style></style>
