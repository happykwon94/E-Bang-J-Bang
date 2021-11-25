<template>
  <!-- 주택 정보 -->
  <ul class="house-list-ul" v-if="houses && houses.length != 0">
    <li
      class="row listOne"
      v-for="house in getterAptList"
      :key="house.aptCode"
      v-show="!isGetData"
      @click="goMarker(house)"
    >
      <div class="house-list-row">
        <div class="house-list-img">
          <div class="img">
            <img src="@/assets/test00.png" alt="" style="width: 140px" />
          </div>
        </div>
        <div class="house-list-desc">
          <div>
            <h3 style="paddig-bottom: 5px">
              {{ house.recentPrice | test}}
            </h3>
            <div>{{ house.aptName }}</div>
            <p>{{ house.dongName }} {{ house.jibun }}</p>
          </div>
        </div>
      </div>
    </li>
    <li
      class="row listOne"
      v-for="house in getterAptList"
      :key="house.no"
      v-show="isGetData"
      @click="goMarker(house)"
    >
      <div class="house-list-row">
        <div class="house-list-img">
          <div class="img">
            <img src="@/assets/test00.png" alt="" style="width: 140px" />
          </div>
        </div>
        <div class="house-list-desc">
          <div>
            <h3 style="paddig-bottom: 5px">{{ house.거래금액 | test }}</h3>
            <div>{{ house.아파트 }}</div>
            <p>{{ house.도로명 }} {{ house.지번 }}</p>
          </div>
        </div>
      </div>
    </li>
  </ul>
  <ul v-else>
    <li>
      <div class="col">아파트 목록이 없습니다.</div>
    </li>
  </ul>
</template>

<script>
import { mapGetters, mapMutations, mapState } from "vuex";
const houseStore = "houseStore";

export default {
  name: "HouseList",
  components: {},
  data() {
    return {
      pageNum: 0,
      isClick: false,
    };
  },
  props: {
    pageSize: {
      type: Number,
      required: false,
      default: 14,
    },
  },
  methods: {
    ...mapMutations(houseStore, ["SET_LAT_LNG"]),
    nextPage() {
      this.pageNum += 1;
    },
    prevPage() {
      this.pageNum -= 1;
    },
    goMarker(house) {
      // getMove(house)
      this.SET_LAT_LNG({ lat: house.lat, lng: house.lng });
    },
  },
  computed: {
    ...mapGetters(houseStore, ["getterAptList"]),
    ...mapState(houseStore, ["houses", "isGetData"]),
    pageCount() {
      let listLeng = this.getterAptList.length,
        listSize = this.pageSize,
        page = Math.floor(listLeng / listSize);
      if (listLeng % listSize > 0) page += 1;
      return page;
    },
    paginatedData() {
      const start = this.pageNum * this.pageSize,
        end = start + this.pageSize;
      return this.getterAptList.slice(start, end);
    },
  },
  filters: {
    test: function (value) {
      
      // value = value.replace(/,/gi ,"");
      // return value.slice(0, value.length-4)+"."+value.toString().slice(-4).replace(/0/g, "")+"억";
      // return String(value).trim().replace(/,/gi,"").slice(0, String(value).trim().length-4)+"."+String(value).trim().slice(-4).replace(/0/gi, "")+"억";
      let v = String(value).trim();
      v = v.replace(/,/g, "");
      return v.slice(0, v.length-4)+"."+v.slice(-4).replace(/0/g,"")+"억";
    },
  },
};
</script>

<style>
.house-list-row {
  flex-grow: 1;
  display: flex;
  align-items: flex-start;
  padding: 15px 15px;
  user-select: none;
}

.house-list-img {
  flex: 0 0 auto;
  width: 140px;
  height: 140px;
  min-height: 140px;
  position: relative;
}

.house-list-desc {
  margin-left: 20px;
}

.image {
  border-radius: 2px;
  position: relative;
  overflow: hidden;
}

#pre,
#page #next {
  float: none;
}

.listOne > *:hover {
  background-color: rgb(220, 239, 255);
}

.listOne {
  margin: 0px;
  padding: 0px;
}

.house-list-ul {
  margin: 0px;
  padding: 0px;
}

house-list-row > ul {
  flex-grow: 1;
  width: 100%;
  height: 170px;
  padding: 1px;
  margin: 0px;
}

house-list-row > li {
  display: flex;
  border-bottom: 1px solid rgb(184, 184, 184);
  width: 100%;
  height: 100%;
}
</style>
