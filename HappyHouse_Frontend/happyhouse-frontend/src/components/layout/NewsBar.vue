<template>
  <div class="headline-content col-6">
    <h4>오늘의 뉴스</h4>
    <ul class="mt-3">
      <news-list-row v-for="(news, idx) in headlines" :key="idx" v-bind="news"></news-list-row>
    </ul>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
import NewsListRow from "@/components/layout/child/NewsListRow.vue";
const newsStore = "newsStore";

export default {
  name: "NewsBar",
  components: {
    NewsListRow,
  },
  created() {
    this.getHeadline();
  },
  computed: {
    ...mapState(newsStore, ["headlines"]),
  },
  methods: {
    ...mapActions(newsStore, ["getHeadline"]),
  },
};
</script>

<style scoped>
ul {
  padding-left: 10px;
}
</style>
