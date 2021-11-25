<template>
  <div class="container mt-2 notice-body">
    <div class="row">
      <div class="col-xs-8 col-md-12">
        <p>&nbsp;</p>
        <div class="table table-responsive">
          <table class="table">
            <tr>
              <th class="success">제목</th>
              <td>{{ notice.title }}</td>
              <th class="success">조회수</th>
              <td>{{ notice.hit }}</td>
            </tr>

            <tr>
              <th class="success">작성자</th>
              <td>{{ notice.writer }}</td>
              <th class="success">작성일</th>
              <td colspan="3">{{ notice.regTime }}</td>
            </tr>

            <tr>
              <td colspan="6">
                <viewer v-if="notice != null" :initialValue="notice.content" height="500px" />
              </td>
            </tr>

            <tr>
              <td colspan="6" class="text-center">
                <input type="button" class="btn btn-outline-danger" @click="deleteNoticeMethod" value="삭제하기" />
                <input type="button" class="btn btn-outline-warning" @click="moveModifyForm" value="수정하기" />
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
import { getNotice, deleteNotice } from "@/api/notice.js";
import "@toast-ui/editor/dist/toastui-editor-viewer.css";

import { Viewer } from "@toast-ui/vue-editor";
export default {
  name: "NoticeBoardView",
  data() {
    return {
      no: this.$route.params.no,
      notice: null,
    };
  },
  components: {
    viewer: Viewer,
  },
  created() {
    getNotice(
      this.no,
      (response) => {
        this.notice = response.data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    moveList() {
      this.$router.push({ name: "Notice" });
    },
    moveModifyForm() {
      this.$router.push({ name: "NoticeBoardUpdate", params: { no: this.no } });
    },
    deleteNoticeMethod() {
      deleteNotice(
        this.no,
        (response) => {
          if (response.data == "success") {
            alert("공지사항을 삭제했습니다.");
            this.$router.push({ name: "Notice" });
          } else {
            alert("삭제 중 문제가 발생했습니다.");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};
</script>

<style></style>
