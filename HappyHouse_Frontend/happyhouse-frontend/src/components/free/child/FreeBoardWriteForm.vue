<template>
  <div>
    <form class="form-notice">
      <div id="notice">
        <h4 v-if="type === 'regist'">게시글 작성</h4>
        <h4 v-else>게시글 수정</h4>
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
            <editor ref="toastuiEditor" v-if="type === 'modify' && free.content.length != 0" :initialValue="free.content" height="500px" initialEditType="wysiwyg" previewStyle="vertical"></editor>
            <editor ref="toastuiEditor" v-if="type === 'regist'" height="500px" initialEditType="wysiwyg" previewStyle="vertical"></editor>
          </div>
          <div class="mt-3 mb-3">
            <button type="button" id="regBtn" class="btn btn-outline-primary" @click="moveList">목록으로</button>
            <button type="button" id="regBtn" v-if="type == 'regist'" class="btn btn-outline-primary" @click="regist">등&nbsp;록</button>
            <button type="button" id="regBtn" v-else class="btn btn-outline-primary" @click="modify">수&nbsp;정</button>
          </div>
        </div>
      </div>
    </form>
  </div>
</template>

<script>
import { registFree, getFree, modifyFree } from "@/api/free.js";
import { mapState } from "vuex";

const userStore = "userStore";
import "@toast-ui/editor/dist/toastui-editor.css";

import { Editor } from "@toast-ui/vue-editor";

export default {
  name: "FreeBoardWriteForm",
  props: {
    type: String,
  },
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
  created() {
    alert(this.$route.params.no);
    if (this.type === "modify") {
      getFree(
        this.$route.params.no,
        (response) => {
          this.free = response.data;
        },
        (error) => {
          console.log(error);
        }
      );
    }
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
    modify() {
      alert("modify");
      this.free.content = this.$refs.toastuiEditor.invoke("getMarkdown");
      this.free.writer = this.userInfo.nickName;
      modifyFree(
        this.free,
        (response) => {
          if (response.data === "success") {
            alert("게시글이 수정되었습니다.");
            this.$router.push({ name: "FreeBoardView", params: { no: this.$route.params.no } });
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
