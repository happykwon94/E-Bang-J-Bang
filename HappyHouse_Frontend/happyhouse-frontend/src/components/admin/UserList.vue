<template>
  <div class="col-lg-10 mt-5">
    <h2>유저 관리 목록</h2>
    <div class="row mt-4">
      <user-list-row v-for="(user, idx) in getUsetListGetter" :key="idx" v-bind="user"></user-list-row>
    </div>
  </div>
</template>

<script>
import UserListRow from "./child/UserListRow.vue";
import { mapGetters, mapActions } from "vuex";

// userStore.js에 네임 스페이스에 설정이 되어있음
const adminStore = "adminStore";

export default {
  name: "UserList",
  components: {
    UserListRow,
  },
  computed: {
    ...mapGetters(adminStore, ["getUsetListGetter"]),
  },
  methods: {
    ...mapActions(adminStore, ["getUserListAction"]),
  },
  mounted() {
    this.getUserListAction();
  },
};
</script>

<style scoped></style>
