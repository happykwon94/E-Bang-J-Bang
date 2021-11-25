<template>
  <div class="book-mark-compare">
    <h2 style="color: #42b983">비교하기</h2>
    <div class="compareResult">
      <div class="row compare-box ">
        <div class="col">{{ homes[0].aptName }}</div>
        <div class="col">아파트명</div>
        <div class="col">{{ homes[1].aptName }}</div>
      </div>
      <div class="row compare-box">
        <div class="col">
          {{
            homes[0].dealAmount
              .replace(/,/gi, "")
              .slice(0, homes[0].dealAmount.length - 5)
          }}.{{
            homes[0].dealAmount.replace(/,/gi, "").slice(-4).replace(/0/gi, "")
          }}억
        </div>
        <div class="col">가격</div>
        <div class="col">
          {{
            homes[1].dealAmount
              .replace(/,/gi, "")
              .slice(0, homes[1].dealAmount.length - 5)
          }}.{{
            homes[1].dealAmount.replace(/,/gi, "").slice(-4).replace(/0/gi, "")
          }}억
        </div>
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
};
</script>

<style>
.book-mark-compare {
  margin-top: 50px;
}

.compare-box {
  display: flex;
  text-align: center;
  align-items: center;
}

.compareResult {
  padding: 20px;
}

.compareResult .row {
  height: 50px;
}

.compare-box .col:nth-child(2){
  color: #42b983;
}

.compare-box.row {
  border: 1px solid #e6e6e6 ;
  border-radius: 8px;
}
</style>
