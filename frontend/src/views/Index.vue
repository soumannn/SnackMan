<template>

  <Modal v-if="modal.isModalOpen">
    <template #titel>
      <h2 class="font-bold text-3xl text-center">{{ modal.modalType === ModalType.JOIN_GAME ? "Join Game" : "New Game" }}</h2>
    </template>
    <template #content>
      <input v-model="newPlayer.name" type="text" name="name" id="name"
        class="block w-full rounded-md border-0 py-1.5 pl-7 pr-20 text-gray-900 ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm/6"
        placeholder="Username eingeben" />
      <div v-if="inputErrorMessage" class="input-error-message">{{ inputErrorMessage }}</div>

      <div>
        <label class="mt-4 mb-4 mflex itmes-center space-x-2">
          <input type="checkbox" v-model="requirePassword" id="requirePassword"
            class="form-checkbox rounded shadow-sm hover:shadow-md transition-shadow duration-300" />
          <span>Privates Spiel</span>
        </label>
        <input v-if="requirePassword" v-model="password" type="text" id="password"
          class="block w-full rounded-md mt-2 border-0 py-1.5 pl-7 pr-20 text-gray-900 ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm/6"
          placeholder="Passwort eingeben" />
      </div>

      <!-- TODO: überprüfen ob name eingeben worden ist -->
       <div class="flex space-x-4">
        <button class="rounded-lg bg-gray-300 hover:bg-gray-400 p-3" @click="handleModalAction"> Weiter </button>
        <button class="rounded-lg bg-gray-300 hover:bg-gray-400 p-3" @click="modal.closeModal()">Schließen</button>
       </div>

    </template>


  </Modal>


  <div class="homeMenue" :style="{ backgroundImage: `url('/src/assets/TestBackground1.jpg')` }">
    <h1>Snackman</h1>
    <div class="form-container">
      <button class="buttons-top-bottom" @click="modal.openModal(ModalType.NEW_GAME,'')">New Game</button>
      <div>
        <input type="text" v-model="gameId" placeholder="Game Id eingeben" class="gameid-input-field">
        <!-- @click="game.joinGame()"-->
        <button class="button-middle" @click="modal.openModal(ModalType.JOIN_GAME,gameId)">Join</button>
      </div>
      <!-- @click="game.findLobbies()"-->
      <button class="buttons-top-bottom" @click="router.push('/lobby')">Find Lobbies/Games</button>
      <button class="buttons-top-bottom" v-on:click="toMapCreator">Map Creator</button>
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
import { ModalType } from '@/stores/game/dtd/EModalType';
import { Playerrole } from '@/stores/game/dtd/EPlayerrole';


const modal = useModalStore()
const game = useGameStore()

const gameId = ref('');

const inputErrorMessage = ref('');

const requirePassword = ref(false);
const password = ref('');


const newPlayer: IPlayerDTD = reactive({
  name: "",
  email: "",
  password: "",
  playertype: PlayerType.GUEST,
  playerrole: Playerrole.GHOST,
})

function toMapCreator() {
  router.push({ name: 'createmap' });
}

function handleModalAction() {
  //Fehlersuche
  console.log("Debug:");
  console.log("modalType:", modal.modalType);
  console.log("newPlayer:", newPlayer);
  console.log("requirePassword:", requirePassword.value);
  console.log("password:", password.value);

  //Tut tatsächlich was
  if (modal.modalType === ModalType.NEW_GAME) {
    modal.newGame(newPlayer, requirePassword.value, password.value);
  } else {
    modal.joinGame(newPlayer);
  }
}           
</script>

<style>
@media (min-width: 1024px) {
  h1 {
    font-size: 50px;
    margin-bottom: 80px;
  }

  .homeMenue {
    background-color: grey;
    min-height: 100vh;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
  }

  .form-container {
    display: flex;
    flex-direction: column;
    gap: 15px;
  }

  .buttons-top-bottom {
    background-color: bisque;
    border-radius: 10px;
    padding: 10px;
  }

  .button-middle {
    background-color: bisque;
    border-radius: 0 10px 10px 0;
    padding: 10px;
  }

  .button-middle:hover,
  .buttons-top-bottom:hover {
    background-color: rgb(247, 194, 130);
  }

  .gameid-input-field {
    height: 44px;
    border-radius: 10px 0 0 10px;
    text-align: center;
  }




}
</style>
