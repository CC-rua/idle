<template>
  <v-app>

    <v-app-bar color="primary">
      <v-app-bar-nav-icon variant="text" @click.stop="drawer = !drawer"/>
      <v-toolbar-title>挂机游戏</v-toolbar-title>
    </v-app-bar>

    <v-navigation-drawer v-model="drawer">
      <v-list-item v-for="item in items" :key="item.title" :to="item.to" :title="item.title"/>
    </v-navigation-drawer>


    <v-main class="d-flex align-center justify-center" style="min-height: 300px;">
      <v-card style="margin-top: 1px; margin-left: 1px;">
        <v-container class="custom-container" fluid>

          <router-view/>

        </v-container>
      </v-card>
    </v-main>
  </v-app>
</template>


<style scoped>
.custom-container {
  height: 80vh;
  padding: 0;
  margin: 0;
}
</style>


<script>
export default {
  data: () => ({
    drawer: true,
    group: null,
    items: [
      {
        title: '家园',
        to: '/',
      },
      {
        title: '采集',
        to: '/collect',
      },

    ],
  }),

  watch: {
    group() {
      this.drawer = false
    },
  },
}
</script>
