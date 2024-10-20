<script setup>
import AppBarView from './views/index/AppBarView.vue';
import Confirm from "@/components/dialog/confirm.vue";
import {onMounted, onUnmounted, ref} from "vue";
import eventBus from "@/plugins/eventBus.js";

const confirmRef = ref(null);
const OpenDialog = (options) => {
  confirmRef.value.openDialog(options)
}
onMounted(() => {
  eventBus.on("openDialog", OpenDialog);
})
onUnmounted(() => {
  eventBus.off("openDialog", OpenDialog);
})
</script>

<template>
  <AppBarView/>
  <Confirm ref="confirmRef" :dialog="true"/>
</template>
<style>
#app {
  width: 100%;
  margin: 0 auto;
  padding: 0 0 0 0;
  text-align: center;
  max-width: 1920px;
}
</style>