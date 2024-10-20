<template>
  <v-sheet class="mx-auto" elevation="10" max-width="300" rounded="xl">
    <v-sheet class="pa-3 bg-primary text-right" rounded="t-xl">
      <div class="text-left">{{ name }}</div>
      <v-btn icon="mdi-content-save-cog-outline" @click="start"></v-btn>
      <v-btn class="ms-2" icon="mdi-check-bold" @click="end"></v-btn>
    </v-sheet>

    <div class="pa-4 text-left">
      {{ desc }}
    </div>
  </v-sheet>
</template>
<script setup>
import api from '@/api/api';

function start() {
  console.log("start")
  api.idleApi.idleStart({
    roleId: 100001,
    configId: props.id
  })
}

function end() {
  api.idleApi.idleEnd({
    roleId: 1,
    configId: props.id
  })
}

const props = defineProps({
  id: Number,
  name: String,
  desc: String
})
</script>