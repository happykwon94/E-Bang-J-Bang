<template>
  <div>
    <form class="form-notice">
      <div id="notice">
        <h4 v-if="this.type === 'regist'">공지사항 작성</h4>
        <h4 v-else>공지사항 수정</h4>
        <div class="mt-4" style="width: 100%">
          <div class="mt-3">
            <label for="title">제목</label>
            <input type="text" id="title" style="width: 100%" v-model="notice.title" />
          </div>
          <div class="mt-3">
            <label for="writer">작성자</label>
            <input type="text" id="writer" style="width: 100%" v-model="userInfo.nickName" disabled />
          </div>
          <div class="mt-3">
            <label for="content">내용</label>
            <editor ref="toastuiEditor" v-if="type === 'modify' && notice.content.length != 0" :initialValue="notice.content" height="500px" initialEditType="wysiwyg" previewStyle="vertical"></editor>
            <editor ref="toastuiEditor" v-if="type === 'regist'" height="500px" initialEditType="wysiwyg" previewStyle="vertical"></editor>
          </div>
          <div class="mt-3 mb-3">
            <button type="button" id="regBtn" class="btn btn-outline-primary" @click="moveList">목록으로</button>
            <button type="button" id="regBtn" class="btn btn-outline-primary" v-if="this.type === 'regist'" @click="regist">등&nbsp;록</button>
            <button type="button" id="regBtn" class="btn btn-outline-warning" v-else @click="modify">수&nbsp;정</button>
          </div>
        </div>
      </div>
    </form>
  </div>
</template>

<script>
import { registNotice, getNotice, modifyNotice } from "@/api/notice.js";
import { mapState } from "vuex";

const userStore = "userStore";
import "@toast-ui/editor/dist/toastui-editor.css";

import { Editor } from "@toast-ui/vue-editor";

export default {
  name: "NoticeBoardWriteForm",
  components: {
    editor: Editor,
  },
  props: {
    type: { type: String },
  },
  data() {
    return {
      notice: {
        title: "",
        writer: "",
        content: "",
      },
    };
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  methods: {
    moveList() {
      this.$router.push({ name: "Notice" });
    },
    regist() {
      this.notice.content = this.$refs.toastuiEditor.invoke("getMarkdown");
      this.notice.writer = this.userInfo.nickName;
      registNotice(
        this.notice,
        (response) => {
          if (response.data === "success") {
            alert("게시글이 등록되었습니다.");
            this.$router.push({ name: "Notice" });
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    modify() {
      this.notice.content = this.$refs.toastuiEditor.invoke("getMarkdown");
      this.notice.writer = this.userInfo.nickName;
      modifyNotice(
        this.notice,
        (response) => {
          if (response.data === "success") {
            alert("게시글이 수정되었습니다.");
            this.$router.push({ name: "NoticeBoardView", params: { no: this.$route.params.no } });
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
  created() {
    if (this.type === "modify") {
      getNotice(
        this.$route.params.no,
        (response) => {
          this.notice = response.data;
        },
        (error) => {
          console.log(error);
        }
      );
    }
  },
};
</script>

<style scoped>
form {
  border: 1px solid rgb(194, 189, 189);
  padding: 50px;
  margin: auto;
}
input {
  height: 40px;
}
</style>
