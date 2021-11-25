<template>
  <div class="container mt-4">
    <free-board-write-form type="modify"></free-board-write-form>
  </div>
</template>

<script>
import FreeBoardWriteForm from "@/components/free/child/FreeBoardWriteForm.vue";

export default {
  name: "FreeBoardUpdate",
  components: {
    FreeBoardWriteForm,
  },
};
</script>

<style></style>
