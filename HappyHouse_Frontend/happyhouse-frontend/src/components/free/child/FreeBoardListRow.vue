<template>
  <tr>
    <td style="vertical-align: middle" scope="col">{{ no }}</td>
    <td scope="col" style="text-align: left; vertical-align: middle">
      <router-link class="notice" :to="{ name: 'FreeBoardView', params: { no: no } }">{{ title }} [{{ hit }}]</router-link>
    </td>
    <td style="vertical-align: middle" scope="col">{{ writer }}</td>
    <td style="vertical-align: middle" scope="col">{{ regTime }}</td>
  </tr>
</template>

<script>
import { mapState } from "vuex";

const userStore = "userStore";
export default {
  name: "FreeBoardListRow",
  props: {
    no: String,
    title: String,
    writer: String,
    hit: String,
    regTime: String,
  },
  computed: {
    ...mapState(userStore, ["isLogin", "userInfo"]),
  },
};
</script>

<style scoped></style>
