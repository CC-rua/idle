<script setup>
import {onMounted, ref} from 'vue';
import api from '@/api/api';
import loginView from './LoginView.vue';

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
  <login-view/>
</template>
