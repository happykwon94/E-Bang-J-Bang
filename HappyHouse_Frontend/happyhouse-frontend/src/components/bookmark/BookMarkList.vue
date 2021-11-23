<template>
  <div>
    <div v-if="this.bookMarkList">
      <book-mark-list-row v-bind="bookmark" v-for="(bookmark, index) in bookMarkList" :key="index"/>
    </div>
    <div v-else>
      <div class="bookmarkBox">비어있습니다.</div>
    </div>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
import BookMarkListRow from './BookMarkListRow.vue';
const houseStore = "houseStore";
const userStore = "userStore";
export default {
  components: { BookMarkListRow },
  name: "BookMarkList",
  data() {
    return {};
  },
  created() {
    this.getBookMarkList(this.userInfo.no);
    // alert(this.userInfo.no);
  },
  computed: {
    ...mapState(houseStore, ["bookMarkList"]),
    ...mapState(userStore, ["userInfo"]),
  },
  methods: {
    ...mapActions(houseStore, ["getBookMarkList"]),
  },
};
</script>

<style>
.bookmarkBox {
  border: 1px solid rgb(213, 213, 213);
  padding: 15px;
  font-size: 13px;
}
</style>
