<template>
  <nav class="navbar navbar-expand-lg navbar-light" id="nav">
    <!-- <a target="_blank" href="https://icons8.com/icon/wqYVbHtvm7Rp/building">Building</a> icon by <a target="_blank" href="https://icons8.com">Icons8</a> -->
    <a class="navbar-brand" style="font-weight: bolder; cursor: pointer" @click="moveHome"><img src="@/assets/ebjb_logo.png" alt="Logo" style="width: 30px; height: 30px; margin-right: 10px" />이 방? 저 방!</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav mr-auto">
        <li class="nav-item active">
          <router-link class="nav-link" :to="{ name: 'Home' }">Home <span class="sr-only">(current)</span></router-link>
        </li>
        <li class="nav-item">
          <router-link class="nav-link" :to="{ name: 'NoticeBoardList' }">공지사항</router-link>
        </li>
        <li class="nav-item">
          <router-link class="nav-link" :to="{ name: 'FreeBoardList' }">자유게시판</router-link>
        </li>
        <li class="nav-item">
          <router-link class="nav-link" :to="{ name: 'HouseView' }">주택</router-link>
        </li>
        <li class="nav-item">
          <router-link :to="{ name: 'BookMarkList' }" class="nav-link" v-if="userInfo">관심목록</router-link>
        </li>
      </ul>
      <ul class="navbar-nav ml-auto" v-if="!userInfo">
        <li class="nav-item">
          <router-link class="nav-link" :to="{ name: 'SignIn' }">로그인</router-link>
        </li>
        <li class="nav-item">
          <router-link class="nav-link" :to="{ name: 'SignUp' }">회원가입</router-link>
        </li>
      </ul>

      <ul class="navbar-nav ml-auto" v-else>
        <li class="nav-item" v-if="userInfo.auth == 20">
          <router-link class="nav-link" :to="{ name: 'Admin' }">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-gear-wide" viewBox="0 0 16 16">
              <path d="M8.932.727c-.243-.97-1.62-.97-1.864 0l-.071.286a.96.96 0 0 1-1.622.434l-.205-.211c-.695-.719-1.888-.03-1.613.931l.08.284a.96.96 0 0 1-1.186 1.187l-.284-.081c-.96-.275-1.65.918-.931 1.613l.211.205a.96.96 0 0 1-.434 1.622l-.286.071c-.97.243-.97 1.62 0 1.864l.286.071a.96.96 0 0 1 .434 1.622l-.211.205c-.719.695-.03 1.888.931 1.613l.284-.08a.96.96 0 0 1 1.187 1.187l-.081.283c-.275.96.918 1.65 1.613.931l.205-.211a.96.96 0 0 1 1.622.434l.071.286c.243.97 1.62.97 1.864 0l.071-.286a.96.96 0 0 1 1.622-.434l.205.211c.695.719 1.888.03 1.613-.931l-.08-.284a.96.96 0 0 1 1.187-1.187l.283.081c.96.275 1.65-.918.931-1.613l-.211-.205a.96.96 0 0 1 .434-1.622l.286-.071c.97-.243.97-1.62 0-1.864l-.286-.071a.96.96 0 0 1-.434-1.622l.211-.205c.719-.695.03-1.888-.931-1.613l-.284.08a.96.96 0 0 1-1.187-1.186l.081-.284c.275-.96-.918-1.65-1.613-.931l-.205.211a.96.96 0 0 1-1.622-.434L8.932.727zM8 12.997a4.998 4.998 0 1 1 0-9.995 4.998 4.998 0 0 1 0 9.996z" />
            </svg>
            관리자 메뉴</router-link
          >
        </li>
        <li class="nav-item">
          <router-link class="nav-link" :to="{ name: 'ModifyUser' }">{{ userInfo.nickName }}({{ userInfo.id }})</router-link>
        </li>
        <li class="nav-item">
          <span class="nav-link" @click="onClickLogout">로그아웃</span>
        </li>
      </ul>
    </div>
  </nav>
</template>

<script>
import { mapState, mapMutations } from "vuex";

const userStore = "userStore";
export default {
  name: "NaviBar",
  computed: {
    ...mapState(userStore, ["isLogin", "userInfo"]),
  },
  methods: {
    ...mapMutations(userStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    onClickLogout() {
      if (confirm("정말 로그아웃 하시겠습니까?")) {
        this.SET_IS_LOGIN(false);
        this.SET_USER_INFO(null);
        sessionStorage.removeItem("access-token");
        if (this.$route.path != "/") {
          this.$router.push({ name: "Home" });
          alert("로그아웃 되었습니다.");
        }
      }
    },
    moveHome() {
      this.$router.push({ name: "Home" });
    },
  },
};
</script>

<style>
.navbar-brand,
.nav-link {
  font-family: "jua";
}

.nav-link {
  cursor: pointer;
}

#nav {
  height: 80px;
  background-image: "url(${require('@/assets/test00.png')})";
  box-shadow: 0 1px 3px 0 rgba(0, 0, 0, 0.5);
}

a.router-link-exact-active {
  color: #42b983 !important;
}
</style>
