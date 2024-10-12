<template>
  <v-app>
    <v-app-bar color="primary">
      <v-app-bar-nav-icon variant="text" @click.stop="drawer = !drawer" />
      <v-toolbar-title>挂机游戏</v-toolbar-title>
    </v-app-bar>

    <v-navigation-drawer v-model="drawer">
      <v-list-item v-for="item in items" :key="item.title" :to="item.to" :title="item.title" />
    </v-navigation-drawer>


    <v-main>
      <v-container class="custom-container" fluid>
        <router-view></router-view>
      </v-container>
    </v-main>

  </v-app>
</template>

<style scoped>
.custom-container {
  height: 100vh;
  padding: 0;
  margin: 0;
  min-width: 1920px;
}
</style>




<script>
export default {
  data: () => ({
    drawer: true,
    group: null,
    items: [
      {
        title: '采集',
        to: '/collect',
      },
      {
        title: '家园',
        to: '/',
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
