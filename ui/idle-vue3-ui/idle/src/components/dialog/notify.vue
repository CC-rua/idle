<template>
  <!-- 用瞬移组件挂载到body -->
  <Teleport to="body">
    <transition name="notify-fade">
      <div class="tips" v-if="visible">
        <!-- 弹窗内容 -->
      </div>
    </transition>
  </Teleport>
</template>
<script setup >
// 这里可以根据个人所需写一些配置，title，content，按钮点击的callback函数之类的

import {ref} from "vue";

let visible = ref(false);

const close = function() {
  visible.value = false;
};

const open = (opt) => {
  visible.value = true;
};

defineExpose({
  open,
  close
});
</script>