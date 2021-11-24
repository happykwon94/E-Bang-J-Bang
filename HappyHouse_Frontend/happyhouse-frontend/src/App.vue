<template>
  <div id="app" class="">
    <navi-bar></navi-bar>
    <router-view />
  </div>
</template>

<script>
import NaviBar from "@/components/layout/NaviBar.vue";

export default {
  name: "App",
  components: {
    NaviBar,
  },
};
</script>

<style scope>

@font-face {
  font-family: "jua";
  src: url(./fonts/Jua-Regular.ttf);
}

@font-face {
  font-family: "BMHANNAAir";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_four@1.0/BMHANNAAir.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}

@import url('https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap');


body {
    font-family: 'Nanum Gothic', sans-serif;
}

#app {
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}

#nav {
  padding: 30px;
}

nav {
  height: 50px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}

</style>
