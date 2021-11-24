<template>
  <div class="bookmarkBox row">
    <div class="col-3">
      <img
        src="https://cfile181.uf.daum.net/image/250649365602043421936D"
        width="73"
        height="70"
      />
    </div>
    <div class="col-7">
      <div class="row">
        <div class="col-3">아파트명</div>
        <div class="col">
          {{ bookmark.aptName }}
          {{ bookmark.no }}
        </div>
      </div>
      <div class="row">
        <div class="col-3">매매가</div>
        <div class="col">{{ bookmark.dealAmount }}만원</div>
      </div>
      <div class="row">
        <div class="col-3">주소</div>
        <div class="col">
          {{ bookmark.sidoName }} {{ bookmark.gugunName }}
          {{ bookmark.dongName }} {{ bookmark.jibun }}
        </div>
      </div>
      <div class="row">
        <div class="col-3">면적</div>
        <div class="col">
          {{ bookmark.area }}
        </div>
        <div class="col">
          <button
            type="button"
            class="btn btn-sm btn-outline-dark"
            @click="delBookMark({ aptNo: bookmark.no, userNo: userNo })"
          >
            삭제
          </button>
        </div>
      </div>
    </div>
    <div class="col">
      <div class="row" style="padding-bottom: 15px">
        <router-link :to="{ name: 'HouseDetail', params: { no: bookmark.no } }"
          >자세히보기</router-link
        >
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions } from "vuex";
const houseStore = "houseStore";
export default {
  name: "BookMarkListRow",
  props: {
    bookmark: Object,
    userNo: String,
  },
  methods: {
    ...mapActions(houseStore, ["deleteBookMark"]),
    delBookMark(bookMarkInfo) {
      this.deleteBookMark(bookMarkInfo);
    },
  },
};
</script>

<style></style>
