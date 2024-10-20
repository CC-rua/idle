import {createRouter, createWebHistory} from 'vue-router'
import CollectView from '@/views/collect/CollectView.vue'
import HomeView from '@/views/home/HomeView.vue'

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: '家园',
            component: HomeView
        },
        {
            path: '/collect',
            name: '采集',
            component: CollectView
        },

    ]
})

export default router
