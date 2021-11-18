<template>
  <div class="home">
    <search-bar></search-bar>
  </div>
</template>

<script>
import SearchBar from "@/components/layout/SearchBar.vue";
export default {
  name: "Home",
  components: {
    SearchBar,
  },
};
</script>
