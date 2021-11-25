<template>
  <div class="home">
    <search-bar></search-bar>
    <div class="container">
      <div class="row">
        <news-bar></news-bar>
        <notice-bar></notice-bar>
      </div>
    </div>
  </div>
</template>

<script>
import SearchBar from "@/components/layout/SearchBar.vue";
import NewsBar from "@/components/layout/NewsBar.vue";
import NoticeBar from "@/components/layout/NoticeBar.vue";

export default {
  name: "Home",
  components: {
    SearchBar,
    NewsBar,
    NoticeBar,
  },
};
</script>

<style scoped>
.container {
  margin-bottom: 40px;
}
</style>
