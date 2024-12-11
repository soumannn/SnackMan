<script setup lang="ts">
import router from "@/router";
import { ModalType } from "@/stores/game/dtd/EModalType";
import type { IGameDTD } from "@/stores/game/dtd/IGameDTD";
import { useGameStore } from "@/stores/game/gamestore";

const gameStore = useGameStore();


defineProps<{
    game: IGameDTD,
}>();
</script>

<template>
    <td class="px-6 py-3 text-gray-600 text-center">
        <span class="font-medium py-3"></span> {{ game.id }}
    </td>
    <td class="px-6 py-3 text-gray-600">
        <span class="font-medium py-3"></span> {{ game.gamemaster?.name || 'Unknown' }}
    </td>
    <td class="px-6 py-3">
        <button
            @click="$emit('open-modal',ModalType.JOIN_GAME, game.id)"
            class="button-small-neumorphism"
        >
        Join
        </button>
    </td>
  </template>