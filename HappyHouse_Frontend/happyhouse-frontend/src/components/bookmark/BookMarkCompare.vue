<template>
  <div class="book-mark-compare">
    <h2>비교하기</h2>
    <div class="compareResult">
      <div class="row compare-box">
        <div class="col">{{ homes[0].aptName }}</div>
        <div class="col">아파트명</div>
        <div class="col">{{ homes[1].aptName }}</div>
      </div>
      <div class="row compare-box">
        <div class="col">{{ homes[0].dealAmount }}만원</div>
        <div class="col">가격</div>
        <div class="col">{{ homes[1].dealAmount }}만원</div>
      </div>
      <div class="row compare-box">
        <div class="col">
          {{ homes[0].sidoName }} {{ homes[0].gugunName }}
          {{ homes[0].dongName }}
        </div>
        <div class="col">주소</div>
        <div class="col">
          {{ homes[1].sidoName }} {{ homes[1].gugunName }}
          {{ homes[1].dongName }}
        </div>
      </div>
      <div class="row compare-box">
        <div class="col">{{ homes[0].area }}㎡</div>
        <div class="col">면적</div>
        <div class="col">{{ homes[1].area }}㎡</div>
      </div>
      <div class="row compare-box">
        <div class="col">{{ homes[0].floor }}층</div>
        <div class="col">층</div>
        <div class="col">{{ homes[1].floor }}층</div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "BookMarkCompare",
  props: {
    homes: Array,
  },
  created() {
    console.log("homes");
    console.log(this.homes);
  },
};
</script>

<style>
.book-mark-compare {
  margin-top: 50px;
}

.compare-box {
  display: flex;
  text-align: center;
}

.compareResult {
  padding: 20px;
}
</style>
