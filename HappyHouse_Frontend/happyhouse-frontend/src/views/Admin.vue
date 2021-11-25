<template>
  <div class="container-fluid">
    <div class="row admin-form">
      <side-bar></side-bar>
      <router-view />
    </div>
  </div>
</template>

<script>
import SideBar from "@/components/admin/SideBar.vue";
export default {
  name: "Admin",
  components: {
    SideBar,
  },
};
</script>

<style scoped>
.admin-form {
  height: 640px;
}
</style>
