<template>
  <div class="container mt-2 notice-body">
    <div class="row">
      <div class="col-xs-8 col-md-12">
        <p>&nbsp;</p>
        <div class="table table-responsive">
          <table class="table">
            <tr>
              <th class="success">제목</th>
              <td>{{ free.title }}</td>
              <th class="success">조회수</th>
              <td>{{ free.hit }}</td>
              <th class="success">추천 수</th>
              <td>{{ free.hit }}</td>
            </tr>

            <tr>
              <th class="success">작성자</th>
              <td>{{ free.writer }}</td>
              <th class="success">작성일</th>
              <td colspan="3">{{ free.regTime }}</td>
            </tr>

            <tr>
              <td colspan="6">
                <viewer v-if="free != null" :initialValue="free.content" height="500px" />
              </td>
            </tr>

            <tr>
              <td colspan="6" class="text-center">
                <button type="button" class="btn btn-outline-success">답글 쓰기</button>
                <button type="button" class="btn btn-outline-warning" v-if="userInfo.nickName === free.writer" @click="moveFreeModifyForm">수정하기</button>
                <!-- 닉네임 말고 id랑 비교가 되어야함 -->
                <button type="button" class="btn btn-outline-danger" v-if="userInfo.auth >= 20 || userInfo.nickName === free.writer" @click="deleteFreeMethod">삭제하기</button>
                <button type="button" class="btn btn-outline-primary" @click="moveList">목록으로</button>
              </td>
            </tr>
          </table>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { getFree, deleteFree } from "@/api/free.js";
import "@toast-ui/editor/dist/toastui-editor-viewer.css";
import { mapState } from "vuex";
const userStore = "userStore";

import { Viewer } from "@toast-ui/vue-editor";

export default {
  name: "FreeBoardView",
  data() {
    return {
      no: this.$route.params.no,
      free: null,
    };
  },
  components: {
    viewer: Viewer,
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  created() {
    getFree(
      this.no,
      (response) => {
        this.free = response.data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    moveList() {
      this.$router.push({ name: "Free" });
    },
    deleteFreeMethod() {
      deleteFree(
        this.no,
        (response) => {
          if (response.data == "success") {
            alert("게시글이 삭제되었습니다.");
            this.moveList();
          } else {
            alert("게시글 삭제 시 문제가 발생했습니다.");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    moveFreeModifyForm() {
      this.$router.push({ name: "FreeBoardUpdate", params: { no: this.no, type: "modify" } });
    },
  },
};
</script>

<style></style>
