<template>
  <div class="login-body container mt-5">
    <div class="form-signin">
      <div><h2>회원가입</h2></div>
      <div class="">
        <label for="">아이디</label>
        <input type="text" class="form-control" id="id" name="id" placeholder="아이디 입력" required autofocus v-model="user.id" />
      </div>
      <div id="idResult" class="mt-1"></div>
      <br />
      <div class="">
        <label for="">닉네임</label>
        <input type="text" class="form-control" id="nickName" name="nickName" placeholder="닉네임 입력" required v-model="user.nickName" />
      </div>
      <br />
      <div class="">
        <label for="">비밀번호</label>
        <input type="password" class="form-control form-control1" id="password" name="password" placeholder="비밀번호 입력" required v-model="user.password" />
      </div>
      <br />
      <div class="">
        <label for="">비밀번호 확인</label>
        <input type="password" class="form-control form-control2" id="passwordValidate" placeholder="비밀번호 확인" name="passwordValidate" required />
      </div>
      <div id="passwordResult" class="mt-1"></div>
      <br />
      <div class="">
        <label for="">연락처</label>
        <input type="tel" class="form-control" id="tel" name="tel" placeholder="연락처 입력" required v-model="user.phone" />
      </div>
      <br />
      <div class="">
        <label for="">이메일</label>
        <input type="email" class="form-control" id="email" name="email" placeholder="이메일 입력" required v-model="user.email" />
      </div>
      <br />
      <div>
        <button type="button" class="btn btn-primary submit-userinfo" @click="join">회원가입</button>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
const userStore = "userStore";
export default {
  name: "SignUp",
  data() {
    return {
      user: {
        id: null,
        password: null,
        nickName: null,
        phone: null,
        email: null,
      },
    };
  },
  computed: {
    ...mapState(userStore, ["isSignUp"]),
  },
  methods: {
    ...mapActions(userStore, ["userJoin"]),
    join() {
      this.userJoin(this.user);
      alert(this.isSignUp);
      if (this.isSignUp) {
        this.$router.push({ name: "SignIn" });
      } else {
        alert("회원가입 중 문제가 발생하였습니다.");
      }
    },
  },
};
</script>

<style>
* {
  font-family: "Noto Sans KR", sans-serif;
}

.login-body {
  width: 500px;
  padding-top: 50px;
}

.form-signin {
  border: 1px solid rgb(194, 189, 189);
  width: 500px;
  padding: 50px;
  margin: auto;
}

.div-login {
  margin-bottom: 30px;
}

input.form-control1 {
  margin-bottom: -1px;
  border-bottom-right-radius: 0;
  border-bottom-left-radius: 0;
}

input.form-control2 {
  border-top-right-radius: 0;
  border-top-left-radius: 0;
  margin-bottom: 10px;
}

.login-d {
  margin-top: 25px;
  margin-bottom: 25px;
}

.checkbox {
  font-size: 0.8rem;
}

.checkbox .nav-find {
  float: right;
}
</style>
