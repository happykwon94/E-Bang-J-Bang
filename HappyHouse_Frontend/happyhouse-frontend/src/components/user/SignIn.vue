<template>
  <div class="login-body container mt-5">
    <form class="form-login" method="POST" action="/user/login">
      <div><h2>로그인</h2></div>
      <hr />
      <div class="login-d">
        <label for="">아이디</label>
        <input type="text" class="form-control" id="id" name="id" placeholder="ID" required />
      </div>
      <div class="login-d">
        <label for="">비밀번호</label>
        <input type="password" class="form-control" id="password" name="password" placeholder="PASSWORD" required />
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
          <button type="button" class="" style="width: 100%; height: 45px">로그인</button>
        </div>
        <div>
          <img src="@/assets/kakao_login_medium_wide.png" alt="" />
        </div>
      </div>
    </form>
  </div>
</template>

<script>
export default {};
</script>

<style>
* {
  font-family: "Noto Sans KR", sans-serif;
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
