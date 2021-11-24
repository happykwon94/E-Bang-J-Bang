<template>
  <div class="search-bar-sm-body">
    <div>
      <div class="search-bar">
        <div class="input-group mb-3">
          <select
            class="form-control"
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
            class="form-control"
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
            class="form-control"
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

<style>

.search-bar {
  padding-top: 20px;
}
</style>
