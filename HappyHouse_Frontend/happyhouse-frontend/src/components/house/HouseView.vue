<template>
  <div class="house">
    <div class="search-bar-sm-container">
      <search-bar-sm />
      <search-bar-sm-2 />
      <div style="overflow: scroll; height: 680px">
        <house-list />
      </div>
    </div>
    <house-map />
  </div>
</template>

<script>
import HouseList from "@/components/house/HouseList.vue";
import HouseMap from "@/components/house/HouseMap.vue";
import SearchBarSm from "@/components/layout/SearchBarSm.vue";
import SearchBarSm2 from "@/components/layout/SearchBarSm2.vue";
import { mapMutations } from "vuex";
const houseStore = "houseStore";
export default {
  name: "HouseView",
  components: {
    HouseMap,
    HouseList,
    SearchBarSm,
    SearchBarSm2,
  },
  computed: {},
  methods: {
    ...mapMutations(houseStore, [
      "SET_APT_LIST",
      "SET_APT_INFO",
      "SET_GET_DATA",
      "SET_LAT_LNG",
    ]),
  },
  destroyed() {
    // this.SET_APT_LIST([]);
    // this.SET_APT_INFO(null);
    // this.SET_GET_DATA(null);
  },
};
</script>

<style>
.search-bar-sm-container {
  width: 400px;
  border-right: 1px solid rgb(184, 184, 184);
  padding: 10px;
}

.house {
  display: flex;
  width: 100%;
  height: 100%;
}
</style>
