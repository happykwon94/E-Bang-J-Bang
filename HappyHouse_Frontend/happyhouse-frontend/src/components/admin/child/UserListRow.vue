<template>
  <div class="col-lg-3 mb-5 user-data-form">
    <div><span>ID</span> : {{ this.id }}</div>
    <div><span>닉네임</span> : {{ this.nickName }}</div>
    <div><span>이메일</span> : {{ this.email }}</div>
    <div><span>연락처</span> : {{ this.phone }}</div>
    <div><span>등&nbsp;급</span> : {{ this.auth }}</div>
    <div><span>가입일</span> : {{ this.regTime }}</div>
    <div class="mt-4">
      <button type="button" class="btn btn-outline-danger" @click="kickOut">추 방</button>
      <button type="button" class="btn btn-outline-warning">정 지</button>
    </div>
  </div>
</template>

<script>
import { mapActions } from "vuex";

const adminStore = "adminStore";

export default {
  name: "UserListRow",
  props: {
    id: String,
    nickName: String,
    email: String,
    phone: String,
    regTime: String,
    auth: String,
  },
  methods: {
    ...mapActions(adminStore, ["kickOutUserAction", "getUserListAction"]),
    async kickOut() {
      await this.kickOutUserAction(this.id);
      this.getUserListAction();
    },
  },
};
</script>

<style scoped>
.user-data-form {
  border: 1px solid #f6f6f6;
  margin-right: 3%;
  margin-left: 2%;
  border-radius: 15px;
  padding: 15px;
}

div {
  margin-bottom: 8px;
}

span {
  font-weight: bold;
}
</style>
