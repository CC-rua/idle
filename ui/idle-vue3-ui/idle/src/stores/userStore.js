import {ref} from 'vue'
import {defineStore} from 'pinia'

export const useUserStore = defineStore('counter', () => {
    const roleId = ref(0)
    const roleName = ref('')


    return {roleId, roleName}
})