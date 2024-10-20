import './assets/main.css'
// Vuetify
import 'vuetify/styles'
import {createVuetify} from 'vuetify'
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'
import '@mdi/font/css/materialdesignicons.css'
import {md3} from 'vuetify/blueprints'

import axios from 'axios';
import VueAxios from 'vue-axios'


import {createApp} from 'vue'
import {createPinia} from 'pinia'

import App from './App.vue'
import router from './router'

const app = createApp(App)
const vuetify = createVuetify({
    components,
    directives,
    icons: {
        defaultSet: 'mdi',
    },
    theme: {
        defaultTheme: 'dark'
    },
    blueprint: md3,
})

app.use(createPinia())
app.use(router)
app.use(vuetify)
app.use(VueAxios, axios);

app.mount('#app')

axios.defaults.baseURL = "/api";