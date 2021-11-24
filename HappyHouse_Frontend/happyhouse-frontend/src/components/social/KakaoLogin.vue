<template>
  <div style="text-align: center">
    <div class="spinner-border m-5" role="status">
      <span class="visually-hidden"></span>
    </div>
    <h3>Loading...</h3>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
const userStore = "userStore";

export default {
  name: "KakaoLogin",
  created() {
    alert("들어왔다");
    this.login();
  },
  computed: {
    ...mapState(userStore, ["isLogin", "isLoginError"]),
  },
  methods: {
    ...mapActions(userStore, ["userConfirm", "getUserInfo", "kakaoSocialLogin"]),
    async login() {
      await this.kakaoSocialLogin(this.$route.query.code);
      this.confirm();
    },
    async confirm() {
      let token = sessionStorage.getItem("access-token");

      if (this.isLogin) {
        await this.getUserInfo(token);
        this.$router.push({ name: "Home" });
      } else {
        alert("등록되지 않은 회원입니다.");
        this.user.id = null;
        this.user.password = null;
      }
    },
  },
};
</script>

<style></style>
