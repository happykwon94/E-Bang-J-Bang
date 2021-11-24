<template>
  <div class="house">
    <div class="search-bar-sm-container">
      <search-bar-sm />
      <search-bar-sm-2 />
      <div style="overflow: scroll; height: 470px">
        <house-list />
      </div>
    </div>
    <house-map />
  </div>
</template>

<script>
import HouseList from "../components/house/HouseList.vue";
import HouseMap from "../components/house/HouseMap.vue";
import SearchBarSm from "../components/layout/SearchBarSm.vue";
import SearchBarSm2 from "../components/layout/SearchBarSm2.vue";
export default {
  name: "House",
  components: {
    HouseMap,
    HouseList,
    SearchBarSm,
    SearchBarSm2,
  },
  computed: {
  },
  methods: {},
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
}
</style>
