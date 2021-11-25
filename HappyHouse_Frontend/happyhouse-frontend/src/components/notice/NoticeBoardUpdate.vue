<template>
  <div class="container mt-4">
    <notice-board-write-form type="modify"></notice-board-write-form>
  </div>
</template>

<script>
import NoticeBoardWriteForm from "@/components/notice/child/NoticeBoardWriteForm.vue";

export default {
  name: "NoticeBoardUpdate",
  components: {
    NoticeBoardWriteForm,
  },
};
</script>

<style></style>
