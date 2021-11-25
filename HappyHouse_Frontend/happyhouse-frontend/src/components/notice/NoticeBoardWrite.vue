<template>
  <div class="container mt-4">
    <notice-board-write-form type="regist"></notice-board-write-form>
  </div>
</template>

<script>
import NoticeBoardWriteForm from "@/components/notice/child/NoticeBoardWriteForm.vue";

export default {
  name: "NoticeBoardWrite",
  components: {
    NoticeBoardWriteForm,
  },
};
</script>

<style></style>
