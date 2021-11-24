<template>
  <div class="bookmarkBox row">
    <div class="col-3">
      <img
        src="https://cfile181.uf.daum.net/image/250649365602043421936D"
        width="73"
        height="70"
      />
    </div>
    <div class="col">
      <div class="row">
        <div class="col-3">아파트명</div>
        <div class="col">
          {{ aptName }}
        </div>
      </div>
      <div class="row">
        <div class="col-3">매매가</div>
        <div class="col">{{ dealAmount }}만원</div>
      </div>
      <div class="row">
        <div class="col-3">주소</div>
        <div class="col">
          {{ sidoName }} {{ gugunName }} {{ dongName }} {{ jibun }}
        </div>
      </div>
      <div class="row">
        <div class="col-3">면적</div>
        <div class="col">
          {{ area }}
        </div>
      </div>
    </div>
    <router-link :to="{name: 'HouseDetail', params: {no: no}}" >자세히보기</router-link>
  </div>
</template>

<script>
export default {
  name: "BookMarkListRow",
  props: {
    no: String,
    aptName: String,
    dealAmount: String,
    sidoName: String,
    gugunName: String,
    dongName: String,
    jibun: String,
    area: String,
  },
};
</script>

<style></style>
