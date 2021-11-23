<template>
  <div>
    <form class="form-notice">
      <div id="notice">
        <h4>게시글 작성</h4>
        <div class="mt-4" style="width: 100%">
          <div class="mt-3">
            <label for="title">제목</label>
            <input type="text" id="title" style="width: 100%" v-model="free.title" />
          </div>
          <div class="mt-3">
            <label for="writer">작성자</label>
            <input type="text" id="writer" style="width: 100%" v-model="userInfo.nickName" disabled />
          </div>
          <div class="mt-3">
            <label for="content">내용</label>
            <editor ref="toastuiEditor" height="500px" initialEditType="wysiwyg" previewStyle="vertical"></editor>
          </div>
          <div class="mt-3 mb-3">
            <button type="button" id="regBtn" class="btn btn-primary" @click="moveList">목록으로...</button>
            <button type="button" id="regBtn" class="btn btn-primary" @click="regist">등&nbsp;록</button>
          </div>
        </div>
      </div>
    </form>
  </div>
</template>

<script>
import { registFree } from "@/api/free.js";
import { mapState } from "vuex";

const userStore = "userStore";
import "@toast-ui/editor/dist/toastui-editor.css";

import { Editor } from "@toast-ui/vue-editor";

export default {
  name: "FreeBoardWriteForm",
  components: {
    editor: Editor,
  },
  data() {
    return {
      free: {
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
      this.$router.push({ name: "Free" });
    },
    regist() {
      this.free.content = this.$refs.toastuiEditor.invoke("getMarkdown");
      this.free.writer = this.userInfo.nickName;
      registFree(
        this.free,
        (response) => {
          if (response.data === "success") {
            alert("게시글이 등록되었습니다.");
            this.$router.push({ name: "Free" });
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
