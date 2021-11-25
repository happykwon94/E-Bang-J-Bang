!
<template>
  <div class="main-body" style="margin-bottom: 50px">
    <div
      class="container-fluid"
      :style="{
        backgroundImage: `url(${require('@/assets/test00.png')})`,
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
        <h2><strong>지역 매물 검색</strong></h2>
        <div class="input-group mb-3">
          <select
            class="form-control searchbar"
            id="sido"
            v-model="sidoCode"
            @change="gugunList"
          >
            <option value="" disabled selected hidden>시/도</option>
            <option
              v-for="(sido, index) in sidos"
              :key="index"
              :value="sido.sidoCode"
            >
              {{ sido.sidoName }}
            </option>
          </select>
          <select
            class="form-control searchbar"
            id="gugun"
            v-model="gugunCode"
            @change="dongList"
          >
            <option value="" disabled selected hidden>구/군</option>
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
            id="dong"
            v-model="dongCode"
            @change="aptList"
          >
            <option value="" disabled selected hidden>동</option>
            <option
              v-for="(dong, index) in dongs"
              :key="index"
              :value="dong.dongCode"
            >
              {{ dong.dongName }}
            </option>
          </select>
        </div>
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
    ...mapMutations(houseStore, ["SET_HOUSE", "SET_PRICE"]),
    sidoList() {
      this.getSido();
    },
    gugunList() {
      this.getGugun(this.sidoCode);
    },
    dongList() {
      this.getDong(this.gugunCode);
    },
    aptList() {
      this.getAptList(this.dongCode);
      this.SET_PRICE(null);
      if (this.$route.name == "Home") {
        this.$router.push({ name: "House" });
      }
    },
  },
  computed: {
    ...mapState(houseStore, ["sidos", "guguns", "dongs", "house"]),
  },
};
</script>

<style></style>
