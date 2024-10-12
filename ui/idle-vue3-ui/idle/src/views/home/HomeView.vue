<script setup>
import { onMounted } from 'vue';
import { ref } from 'vue'
import  api  from '@/api/api';

const sayHello = ref("123")
const sayHelloApi = function () {
 api.testApi.sayhello().then((response) => {
    sayHello.value = response.data.data
  })
}
onMounted(() => {
  sayHelloApi();
})
</script>

<template>
  <v-card style="text-align: left;">
    Home
    <v-btn value="recent">
      <v-icon>mdi-history</v-icon>
      <span v-text="sayHello"></span>
    </v-btn>
  </v-card>
</template>
