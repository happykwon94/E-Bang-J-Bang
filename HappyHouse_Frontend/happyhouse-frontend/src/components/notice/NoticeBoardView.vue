<template>
  <div class="container mt-5 notice-body">
    <div class="form-notice">
      <hr />
      <table id="detail-table">
        <input type="hidden" id="notice-no" value="${board.no}" />
        <tr>
          <td style="width: 10%">제목</td>
          <td style="text-align: left">
            <span>{{ notice.title }}&nbsp;[{{ notice.hit }}]</span>
          </td>
        </tr>
        <tr>
          <td style="width: 10%">작성자</td>
          <td style="text-align: left">
            <span>{{ notice.writer }}</span>
          </td>
        </tr>
        <tr>
          <td colspan="2" style="border-top: 1px solid black; padding-top: 10px; text-align: left">{{ notice.content }}</td>
        </tr>
      </table>
    </div>
  </div>
</template>

<script>
import { getNotice } from "@/api/notice.js";

export default {
  name: "NoticeBoardView",
  data() {
    return {
      no: this.$route.params.no,
      notice: {},
    };
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
};
</script>

<style></style>
