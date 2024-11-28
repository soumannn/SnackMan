<template>

  <Modal v-if="modal.isModalOpen">
    <template #titel>
      <h2 class="ueberschrift-small">Titel</h2>
    </template>
    <template #content>
      <input v-model="newPlayer.name" type="text" name="name" id="name"
        class="standart-input-form" placeholder="Username eingeben" />
      <div v-if="inputErrorMessage" class="input-error-message">{{ inputErrorMessage }}</div>
       <div class="horizontal-button-stack">
        <button class="button-small" @click="newGame()">Weiter</button>
        <button class="button-small" @click="modal.closeModal()">Schließen</button>
       </div>
    </template>
  </Modal>


  <div class="fullscreen-layout">
    <h1 class="ueberschrift-big">Snackman</h1>

    <div class="vertikal-stack">
      <button class="button-big" @click="modal.openModal()">New Game</button>

      <div class="input-with-button-container">
        <input type="text" v-model="gameId" placeholder="Game Id eingeben" class="input-form-with-button">
        <!-- @click="game.joinGame()"-->
        <button class="button-next-to-form">Join</button>
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
