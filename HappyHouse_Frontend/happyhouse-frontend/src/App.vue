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

* {
  font-family: "jua";
}

#app {
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  height: 100%;
  width: 100%;
}

#nav {
  padding: 30px;
}

nav {
  height: 50px;
}

#nav a {
  font-weight: bold;
}
</style>
