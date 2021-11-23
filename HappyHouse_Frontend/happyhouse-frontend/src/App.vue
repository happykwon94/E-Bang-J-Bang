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

<style>
@font-face {
  font-family: "jua";
  src: url(./fonts/Jua-Regular.ttf);
}

#app {
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  margin-bottom: 50px;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
