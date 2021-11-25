<template>
  <div class="book-mark-body container mt-5">
    <div id="bookmark">
      <h1>관심목록</h1>
      <router-view />
    </div>
  </div>
</template>

<script>
export default {
  name: "BookMark",
  // data(){
  //   return{

  //   }
  // },
  // create(){
  //   this.getBookMarkList(this.userInfo.no);
  // },
  // computed:{
  //   ...mapState(houseStore, ["bookMarkList"]),
  //   ...mapState(userStore, ["userInfo"]),

  // },
  // methods:{
  //   ...mapGetters(houseStore, ["getBookMarkList"])
  // }
};
</script>

<style></style>
