import { defineStore } from 'pinia'
import type { IThemes } from '@/stores/game/dtd/ThemesDTD'
import { ref, computed } from 'vue'
import { sendMessage, subscribeTo, stompClient } from '@/config/stompWebsocket'


export const useThemeStore = defineStore("theme", () => {
const themes = ref <IThemes>({
    christmas: {
        ground: "/src/assets/game/christmas/ChristmasGround.png",
        wall: "/src/assets/game/christmas/ChristmasWall.png",
      character: {
          snackman: "/src/assets/game/christmas/snackman/snackman.glb",
          ghost: "/src/assets/game/christmas/ghost/ghost.glb",
      },
    },
    realistic: {
        ground: "/src/assets/game/realistic/ground.png",
        wall: "/src/assets/game/realistic/wall.png",
      character: {
          snackman: "/src/assets/game/realistic/snackman/snackman.glb",
          ghost: "/src/assets/game/realistic/ghost/ghost.glb",
      },
    },
    retro: {
        ground: "/src/assets/game/retro/cobblestone_2.png",
        wall: "/src/assets/game/retro/dirty_1.png",
      character: {
          snackman: "/src/assets/game/retro/retroSnackman.glb",
          ghost: "/src/assets/game/retro/retroGhost.glb",
      },
    },
    sweetWorld: {
        ground: "/src/assets/game/sweetWorld/sweetWorldFloor.jpg",
        wall: "/src/assets/game/sweetWorld/sweetWorldWall.png",
      character: {
          snackman: "/src/assets/game/sweetWorld/snackman/sweetWorldPinkSnackman.glb",
          ghost: "/src/assets/game/sweetWorld/ghost/sweetWorldGhost.glb",
      },
    },
    });

    const selectedTheme= ref<keyof IThemes>("realistic"); 

  function setSelectedTheme(themeName: keyof IThemes, lobbyId: string) {
    if(themes.value[themeName]){
      selectedTheme.value=themeName;
      console.log(`Theme: ${themeName} set successfully.`);
      sendThemeUpdateToBackend(themeName, lobbyId)
    }else{
      console.log("Fehlgeschlagen, ungültiger Theme:", themeName);
    }
  }

  const currentTheme = computed(() => themes.value[selectedTheme.value])

  function sendThemeUpdateToBackend(themeName: string, lobbyId: string) {
    if (lobbyId) {
      sendMessage(`/topic/game/${lobbyId}/setTheme`, { themeName })
      console.log(`Theme update sent for lobbyId: ${lobbyId}`)
    } else {
      console.error('Lobby ID not provided.')
    }
  }

  function subscribeToThemeUpdates(lobbyId: string): Promise<boolean> {
    return new Promise((resolve) => {
      if (!stompClient.connected) {
        stompClient.activate();
        stompClient.onConnect = () => {
          subscribeToThemeUpdatesHandler(lobbyId);
          resolve(true)
        };
      } else {
        subscribeToThemeUpdatesHandler(lobbyId);
        resolve(true)
      }
    })
  }
  
  function subscribeToThemeUpdatesHandler(lobbyId: string) {
    subscribeTo(`/game/${lobbyId}`, (message: any) => {
      if (message.type === 'themeUpdate' && message.status === 'ok') {
        const newTheme = message.feedback
        if (themes.value[newTheme]) {
          selectedTheme.value = newTheme
          console.log(`Theme updated to: ${newTheme}`)
        } else {
          console.error('Received invalid theme:', newTheme)
        }
      }
    })
  }
  

  return {
    themes,
    selectedTheme,
    setSelectedTheme,
    currentTheme,
    subscribeToThemeUpdates,
  };

});