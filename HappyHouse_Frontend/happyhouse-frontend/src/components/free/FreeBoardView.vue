<template>
  <div class="container mt-5 notice-body">
    <div class="form-notice">
      <hr />
      <table id="detail-table">
        <input type="hidden" id="notice-no" value="${board.no}" />
        <tr>
          <td style="width: 10%">제목</td>
          <td style="text-align: left" colspan="3">
            <span>{{ free.title }}&nbsp;[{{ free.hit }}]</span>
          </td>
        </tr>
        <tr>
          <td style="width: 10%">작성자</td>
          <td style="text-align: left">
            <span>{{ free.writer }}</span>
          </td>
          <td style="width: 10%">like [{{ free.likeCnt }}]</td>
          <td style="width: 10%">dislike [{{ free.dislikeCnt }}]</td>
        </tr>
        <tr>
          <td colspan="4" style="border-top: 1px solid black; padding-top: 10px; text-align: left">
            <viewer v-if="free != null" :initialValue="free.content" height="500px" />
          </td>
        </tr>
      </table>
    </div>
  </div>
</template>

<script>
import { getFree } from "@/api/free.js";
import "@toast-ui/editor/dist/toastui-editor-viewer.css";

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
};
</script>

<style></style>
