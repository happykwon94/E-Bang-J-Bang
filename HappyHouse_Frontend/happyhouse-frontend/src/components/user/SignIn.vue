<template>
  <div class="login-body container mt-5">
    <form class="form-login" method="POST" action="/user/login">
      <div><h2>로그인</h2></div>
      <hr />
      <div class="login-d">
        <label for="">아이디</label>
        <input type="text" class="form-control" id="id" name="id" placeholder="ID" required v-model="user.id" />
      </div>
      <div class="login-d">
        <label for="">비밀번호</label>
        <input type="password" class="form-control" id="password" name="password" placeholder="PASSWORD" required v-model="user.password" />
      </div>
      <div>
        <div class="checkbox login-d">
          <input type="checkbox" id="saveId" name="saveId" />아이디 저장
          <span class="nav-find">
            <router-link to="/user/idfind">아이디 찾기</router-link>
            &nbsp;
            <router-link to="/user/passwordfind">비밀번호 찾기</router-link>
          </span>
        </div>
        <div>
          <button type="button" class="btn btn-primary" id="loginBtn" @click="confirm">로그인</button>
        </div>
        <div>
          <img src="@/assets/kakao_login_medium_wide.png" id="kakaoLoginBtn" @click="kakaoLogin()" />
        </div>
      </div>
    </form>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";

// userStore.js에 네임 스페이스에 설정이 되어있음
const userStore = "userStore";

export default {
  name: "SignIn",
  data() {
    return {
      user: {
        id: null,
        password: null,
      },
    };
  },
  computed: {
    // memberStore를 사용하겠다.
    ...mapState(userStore, ["isLogin", "isLoginError"]),
  },
  methods: {
    ...mapActions(userStore, ["userConfirm", "getUserInfo", "kakaoSocial"]),
    async confirm() {
      await this.userConfirm(this.user);

      let token = sessionStorage.getItem("access-token");

      console.log(this.isLogin);

      if (this.isLogin) {
        console.log("1");
        await this.getUserInfo(token);
        console.log("2");
        this.$router.push({ name: "Home" });
      } else {
        alert("등록되지 않은 회원입니다.");
        this.user.id = null;
        this.user.password = null;
      }
    },
    movePage() {
      this.$router.push({ name: "SignUp" });
    },
    kakaoLogin() {
      // 코드 발급
      this.kakaoSocial();
    },
  },
};
</script>

<style>
* {
  font-family: "Noto Sans KR", sans-serif;
}

#loginBtn {
  width: 100%;
  height: 45px;
  margin-bottom: 10px;
}

#kakaoLoginBtn {
  cursor: pointer;
}

.login-body {
  width: 500px;
  padding-top: 50px;
}

.form-login {
  border: 1px solid rgb(194, 189, 189);
  width: 400px;
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
