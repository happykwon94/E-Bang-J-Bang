<template>
  <nav class="navbar navbar-expand-lg navbar-light" style="background-color: #e3f2fd; box-shadow: 0 1px 3px 0 rgba(0, 0, 0, 0.5)">
    <router-link class="navbar-brand" :to="{ name: 'Home' }" style="font-weight: bolder"><img src="@/assets/ebangjbangLogo.png" alt="Logo" style="width: 30px; height: 30px; margin-right: 10px" />이 방? 저 방!</router-link>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav mr-auto">
        <li class="nav-item active">
          <router-link class="nav-link" :to="{ name: 'Home' }">Home <span class="sr-only">(current)</span></router-link>
        </li>
        <li class="nav-item">
          <router-link class="nav-link" :to="{ name: 'Notice' }">공지사항</router-link>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">QnA</a>
        </li>
        <li class="nav-item">
          <router-link :to="{ name: 'House' }" class="nav-link">주택</router-link>
        </li>
      </ul>
      <ul class="navbar-nav ml-auto">
        <li class="nav-item">
          <router-link class="nav-link" :to="{ name: 'SignIn' }">로그인</router-link>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">회원가입</a>
        </li>
      </ul>
    </div>
  </nav>
</template>

<script>
export default {
  name: "NaviBar",
};
</script>

<style></style>
