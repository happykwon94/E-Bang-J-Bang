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
            />
            <div class="input-group-append" style="margin-right: 30px">
              <button
                type="button"
                class="btn btn-outline-dark"
                id="btnMaxPrice"
              >
                가격 검색
              </button>
            </div>
            <input type="month" class="form-control searchbar" id="month" />
            <div class="input-group-append">
              <button type="button" class="btn btn-outline-dark" id="btnMonth">
                연,월 검색
              </button>
            </div>
          </div>
        </div>
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
</template>

<script>
import HouseList from "../components/house/HouseList.vue";
import HouseMap from "../components/house/HouseMap.vue";
import SearchBar from "../components/layout/SearchBar.vue";

export default {
  name: "House",
  components: {
    SearchBar,
    HouseMap,
    HouseList,
  },
};
</script>

<style>
.col-8, .col{
    padding: 0px;
    margin: 0px;
}
</style>
