<script setup lang="ts">
import router from '@/router';
import { RouterLink, RouterView, useRoute } from 'vue-router'

const route = useRoute()
const id = route.params.id
console.log(id)

async function start(): Promise<void> {

    try {
        const response = await fetch("../api/game/start", {
            method: "POST"
        });

        if(response.ok){
            console.log("Gut gemacht");
        }else{
            console.error("Failed");
        }
    } catch (error) {
        console.error('Fetch error:', error)
    }
}

</script>

<template>
    <p>Das ist die Lobby mit der id : {{ id }}</p>

    <p>Hallo</p>
    
    <RouterLink class="font-bold" to="/">Homepage</RouterLink>
    <br>
    <button @click="start()">Starten</button>
</template>
