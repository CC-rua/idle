<script setup>
import CollectChipView from './CollectChipView.vue';

</script>

<template>
    <v-row style="width: 1200px;">
        <v-col v-for="n in 3" :key="n" cols="12" sm="4">
            <v-sheet class="ma-2 pa-2">
                <CollectChipView :name="123" :desc="123"/>
            </v-sheet>
        </v-col>
    </v-row>
</template>
