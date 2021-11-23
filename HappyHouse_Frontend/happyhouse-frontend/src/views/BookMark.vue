<template>
  <div id="body-div" class="container mt-5 notice-body">
    <form class="form-bookmark">
      <div id="bookmark">
        <h1>관심목록</h1>
        <router-view/>
      </div>
    </form>
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
