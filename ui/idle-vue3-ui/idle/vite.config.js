import {fileURLToPath, URL} from 'node:url'

import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'
import vueJsx from '@vitejs/plugin-vue-jsx'
import vueDevTools from 'vite-plugin-vue-devtools'

// https://vitejs.dev/config/
export default defineConfig({
    plugins: [
        vue(),
        vueJsx(),
        vueDevTools(),
    ],
    resolve: {
        alias: {
            '@': fileURLToPath(new URL('./src', import.meta.url))
        }
    },
    server: {
        // 代理配置
        proxy: {
            // 如果请求地址以/打头,就出触发代理机制
            '/api': {
                target: 'http://localhost:10800', // 要代理的真实接口地址
                changeOrigin: true,
                rewrite: path => path.replace(/^\/api/, ''),
                disableHostCheck: true,
                secure: false,
            },
        }
    },
})
