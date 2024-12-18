import { defineStore } from 'pinia';
import { ref } from 'vue'
import { ModalType } from './game/dtd/EModalType';
import type { IPlayerDTD } from './game/dtd/IPlayerDTD';
import { useGameStore } from './game/gamestore';
import { useRouter } from 'vue-router';

export const useModalStore = defineStore('modal', () => {

  const isModalOpen = ref(false);
  const modalType = ref<ModalType>();
  const gameId = ref();
  const inputErrorMessage = ref('');
  const game = useGameStore();
  const router = useRouter();
  const isGamePrivate = ref(false);
  const requirePassword = ref(false);

  function openModal(type: ModalType, gameId_: String, isPrivate: boolean) {
    isModalOpen.value = true;
    modalType.value = type;
    gameId.value = gameId_;
    isGamePrivate.value = isPrivate;
  }

  function closeModal() {
    isModalOpen.value = false;
    isGamePrivate.value = false;
    gameId.value = null;
  }

  async function newGame(newPlayer: IPlayerDTD, privateLobby: boolean, password: string | null) {

    if (!newPlayer.name) {
      inputErrorMessage.value = "Bitte einen Usernamen eingeben";
      return;
    }

    if (privateLobby && !password) {
      inputErrorMessage.value = "Passwort wird für Private Lobby benoetigt:";
      return;
    }

    await game.createGame(newPlayer, privateLobby, password);
    router.push(`/lobby/${game.gameState.gamedata.id}`);
  }

  async function joinGame(player: IPlayerDTD, password: string | null): Promise<boolean> {
    if (!player.name) {
      inputErrorMessage.value = "Bitte einen Username eingeben.";
      return false;
    }
  
    try {
      const isJoined = await game.joinLobby(gameId.value as string, player, password);
  
      if (isJoined) {
        const id = game.gameState.gamedata.id;
        router.push(`/lobby/${gameId.value}`);

        return true;
      } else {
        inputErrorMessage.value = "Lobby konnte nicht betreten werden. Überprüfe das Passwort.";
        return false;
      }
    } catch (error) {
      console.error("Fehler beim Lobby-Beitritt:", error);
      inputErrorMessage.value = "Ein unerwarteter Fehler ist aufgetreten.";
      return false;
    }
  }

  async function checkPrivateGame(gameId_: string) {
    try {
      const isPrivate = await game.isGamePrivate(gameId_);
      isGamePrivate.value = isPrivate;
      openModal(ModalType.JOIN_GAME, gameId_, isPrivate);
    } catch (error) {
      console.error("Fehler beim Überprüfen des privaten Status:", error);
      inputErrorMessage.value = "Lobby konnte nicht überprüft werden.";
    }
  }

  function setErrorMessage(error: string) {
    inputErrorMessage.value = error;
  }

  async function handleModalAction(newPlayer: IPlayerDTD, gameId: string) {
    if (modalType.value === ModalType.NEW_GAME) {
      newGame(newPlayer, requirePassword.value, newPlayer.password);
    } else if (modalType.value === ModalType.JOIN_GAME) {
      try {
        const result:any = await game.isGamePrivate(gameId);
  
        console.log(result);
  
        if (result && !newPlayer.password) {
          inputErrorMessage.value = "Passwort wird benötigt!";
          return;
        }
  
        const joinResult = await joinGame(newPlayer, newPlayer.password);
  
        if (!joinResult) {
          inputErrorMessage.value = "Lobby konnte nicht betreten werden. Überprüfe das Passwort.";
        }
      } catch (error) {
        console.error("Fehler beim Beitritt zur Lobby:", error);
      };
    }
  }

  return { isModalOpen, openModal, closeModal, modalType, newGame, joinGame, setErrorMessage, inputErrorMessage, checkPrivateGame,isGamePrivate,requirePassword,handleModalAction }

})