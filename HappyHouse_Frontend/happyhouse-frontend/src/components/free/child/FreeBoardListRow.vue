<template>
  <tr>
    <td scope="col">{{ no }}</td>
    <td scope="col" style="text-align: left">
      <router-link class="notice" :to="{ name: 'FreeBoardView', params: { no: no } }">{{ title }} [{{ hit }}]</router-link>
    </td>
    <td scope="col">{{ writer }}</td>
    <td scope="col">{{ regTime }}</td>
  </tr>
</template>

<script>
export default {
  name: "FreeBoardListRow",
  props: {
    no: String,
    title: String,
    writer: String,
    hit: String,
    regTime: String,
  },
};
</script>

<style></style>
