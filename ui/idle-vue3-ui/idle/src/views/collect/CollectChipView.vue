<template>
    <v-sheet class="mx-auto" elevation="10" max-width="300" rounded="xl">
        <v-sheet class="pa-3 bg-primary text-right" rounded="t-xl">
            <div class="text-left">{{ name }}</div>
            <v-btn icon="mdi-content-save-cog-outline"></v-btn>

            <!-- <v-btn class="ms-2" icon="mdi-check-bold" ></v-btn> -->
        </v-sheet>

        <div class="pa-4 text-left">
            {{ desc }}
        </div>
    </v-sheet>
</template>
<script>
export default {
    data: () => ({
    
    }),
    props:{
        name:"名称",
        desc:"描述"
    }
}
</script>