<template>

  <Modal v-if="modal.isModalOpen">
    <template #titel>
      <h2 class="ueberschrift-small">Titel</h2>
    </template>
    <template #content>
      <input v-model="newPlayer.name" type="text" name="name" id="name"
        class="input-form" placeholder="Username eingeben" />
      <div v-if="inputErrorMessage" class="input-error-message">{{ inputErrorMessage }}</div>
       <div class="flex space-x-4">
        <button class="button-small" @click="newGame()">Weiter</button>
        <button class="button-small" @click="modal.closeModal()">Schließen</button>
       </div>
    </template>
  </Modal>


  <div class="bg-gray-500 min-h-screen flex flex-col items-center justify-center" 
              :style="{ backgroundImage: `url('/src/assets/TestBackground1.jpg')` }">
    <h1 class="ueberschrift-big">Snackman</h1>
    <div class="flex flex-col gap-4 lg:gap-[15px]">
      <button class="button-big" @click="modal.openModal()">New Game</button>

      <div class="input-with-button">
        <input type="text" v-model="gameId" placeholder="Game Id eingeben" class="flex-1 h-[44px] rounded-l-lg text-center border border-gray-300">
        <!-- @click="game.joinGame()"-->
        <button class="bg-bisque hover:bg-hover-bisgue rounded-r-lg p-2">Join</button>
      </div>

      <!-- @click="game.findLobbies()"-->
      <button class="button-big">Find Lobbies/Games</button>
    </div>
  </div>

</template>




<script setup lang="ts">
import Modal from '@/components/Modal.vue';
import { reactive, ref } from 'vue';
import { useModalStore } from '@/stores/modalstore';
import { useGameStore } from '@/stores/game/gamestore';
import type { IPlayerDTD } from '@/stores/game/dtd/IPlayerDTD';
import { PlayerType } from '@/stores/game/dtd/PlayerType';
import router from '@/router';


const modal = useModalStore()
const game = useGameStore()

const gameId = ref('');

const inputErrorMessage = ref('');


const newPlayer: IPlayerDTD = reactive({
  name: "",
  email: "",
  password: "",
  playerrole: "",
  playertype: PlayerType.GUEST

})

async function newGame() {

  if (!newPlayer.name) {
    inputErrorMessage.value = "Bitte einen Usernamen eingeben";
  } else {
    await game.createGame(newPlayer)

    const id = useGameStore().gameState.gamedata?.id

    router.push({ 
      name: 'lobbyWithId', 
      params: { id } 
    })
  }


}



</script>




<style>

</style>
