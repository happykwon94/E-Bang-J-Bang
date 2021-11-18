!
<template>
  <div class="main-body container-full" style="margin-bottom: 50px">
    <div
      class="container-fluid"
      :style="{
        backgroundImage: `url(${require('@/assets/main2.png')})`,
        width: '100%',
        backgroundSize: 'cover',
        height: '300px',
        backgroundPosition: '50%',
      }"
    >
      <div class="container searchbar fade-in-box" style="padding-top: 150px; text-align: center">
        <h2 style="color: white"><strong>지역 매물 검색</strong></h2>
        <div class="input-group mb-3">
          <select class="form-control searchbar" name="" id="sido">
            <option value="">시/도</option>
          </select>
          <select class="form-control searchbar" name="" id="gugun">
            <option value="">구/군</option>
          </select>
          <select class="form-control searchbar" name="" id="dong">
            <option value="">읍/면/동</option>
          </select>
          <div class="input-group-append">
            <button type="button" id="lookMap" class="btn btn-secondary btn-outline-light">지도보기</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style></style>
