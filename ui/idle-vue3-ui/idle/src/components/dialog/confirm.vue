<template>
  <div class="text-center pa-4">
    <v-dialog
        v-model="dialog"
        width="auto"
    >
      <v-card
          max-width="400"
          :prepend-icon="option.icon"
          :text="option.text"
          :title="option.title"
      >
        <template v-slot:actions>
          <v-btn
              class="ms-auto"
              text="取消"
              @click="cancel"
          ></v-btn>
          <v-btn
              class="ms-auto"
              text="确认"
              @click="confirm"
          ></v-btn>
        </template>
      </v-card>
    </v-dialog>
  </div>
</template>

<script setup>

import {onMounted, ref} from "vue";


const option = ref({
  title: 'ops!',
  text: 'none',
  icon: 'mdi-update',
  actions: [
    {
      text: 'Ok',
      method: 'cancel'
    }
  ],
  confirmFunc: () => {
  },
  cancelFunc: () => {
  },
})
const dialog = ref(false)

const openDialog = (opt) => {
  dialog.value = true;
  if (opt.title !== undefined) {
    option.value.title = opt.title;
  }
  if (opt.text !== undefined) {
    option.value.text = opt.text;
  }
  if (opt.icon !== undefined) {
    option.value.icon = opt.icon;
  }
  if (opt.actions !== undefined) {
    option.value.actions = opt.actions;
  }
  if (opt.confirmFunc !== undefined) {
    option.value.confirmFunc = opt.confirmFunc;
  }
  if (opt.cancelFunc !== undefined) {
    option.value.cancelFunc = opt.cancelFunc;
  }
}

function confirm() {
  dialog.value = false;
  option.value.confirmFunc();
}

function cancel() {
  dialog.value = false;
  option.value.cancelFunc();
}

onMounted(() => {
  dialog.value = false;
})
defineExpose({openDialog})

</script>