<template>
  <v-row justify="center" align="center">
    <v-col cols="12" sm="8" md="4">
      <v-card class="elevation-12">
        <v-toolbar color="primary" dark flat>
          <v-toolbar-title>登录</v-toolbar-title>
        </v-toolbar>
        <v-card-text>
          <v-form ref="form">
            <v-text-field
                v-model="username"
                :rules="usernameRules"
                label="用户名"
                required
            ></v-text-field>
            <v-text-field
                v-model="password"
                :rules="passwordRules"
                label="密码"
                type="password"
                required
            ></v-text-field>
          </v-form>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="primary" @click="login" :disabled="!valid">登录</v-btn>
          <v-btn color="primary" @click="registerRole" :disabled="!valid">注册</v-btn>
        </v-card-actions>
      </v-card>
    </v-col>
  </v-row>
</template>

<script setup>
import {computed, getCurrentInstance, ref} from 'vue';
import api from "@/api/api.js";
import {idle} from "@/api/model/const.js";
// 获取当前实例
const {proxy} = getCurrentInstance();
// 表单数据
const username = ref('');
const password = ref('');

// 表单验证规则
const usernameRules = [
  v => !!v || '用户名是必填项',
  v => (v && v.length >= 3) || '用户名至少需要3个字符',
];
const passwordRules = [
  v => !!v || '密码是必填项',
  v => (v && v.length >= 6) || '密码至少需要6个字符',
];

// 表单有效性计算属性
const valid = computed(() => {
  return username.value && password.value && usernameRules.every(rule => rule(username.value)) && passwordRules.every(rule => rule(password.value));
});

// 登录方法
const form = ref(null);
const login = () => {
  form.value.validate();
  if (valid.value) {
    // 在这里处理登录逻辑
    api.userApi.login({
      roleName: form.value.username,
    }).then((response) => {
      if (response.data.code === idle.successCode) {
        // proxy.$showGlobalDialog({title: '登录成功', message: '欢迎回来，管理员！'});
      } else {
        // proxy.$showGlobalDialog({title: '登录失败', message: response.data.msg})
        proxy.$ftConfirm({
          message: response.data.msg,
          title: "hh",
          show: true,
          onConfirm: () => {
            console.log("confirm")
          },
          onCancel: () => {
            console.log("cancel")
          }
        })
      }
    })
  }
};
const registerRole = function () {
  api.userApi.registerRole({
    roleName: form.value.username,
  }).then((response) => {
    console.log(response)
  })
};
</script>

<style scoped>
/* 你可以在这里添加一些自定义样式 */
.v-row {
  width: 1200px;
}
</style>
