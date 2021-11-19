<template>
  <div class="row aptlist">
    <div class="col-1">{{ index }}</div>
    <div class="col text-left">{{ house.aptName }}</div>
  </div>
</template>

<script>
export default {
  name: "HouseListRow",
  props: {
    house: Object,
    index: Number,
  },
};
</script>

<style></style>
