import { defineStore } from "pinia";
import type { IThemes, IThemeDTD } from "@/stores/game/dtd/ThemesDTD";
import { ref, computed } from "vue";

export const useThemeStore = defineStore("theme", () => {
const themes = ref <IThemes>({
    christmas: {
        ground: "/src/assets/game/christmas/ChristmasGround.png",
        wall: "/src/assets/game/christmas/ChristmasWall.png",
        character: {
          snackman: "/src/assets/game/christmas/snackman/scene.gltf",
          ghost: "/src/assets/game/christmas/ghost/scene.gltf",
        },
      },
      realistic: {
        ground: "/src/assets/game/realistic/ground.png",
        wall: "/src/assets/game/realistic/wall.png",
        character: {
          snackman: "/src/assets/game/realistic/snackman/snackman.gltf",
          ghost: "/src/assets/game/realistic/ghost/ghost.gltf",
        },
      },
      retro: {
        ground: "/src/assets/game/retro/cobblestone_2.png",
        wall: "/src/assets/game/retro/dirty_1.png",
        character: {
          snackman: "/src/assets/game/retro/scene.gltf",
          ghost: "/src/assets/game/retro/scene.gltf",
        },
      },
      sweetWorld: {
        ground: "/src/assets/game/sweetWorld/sweetWorldFloor.jpg",
        wall: "/src/assets/game/sweetWorld/sweetWorldWall.png",
        character: {
          snackman: "/src/assets/game/sweetWorld/scene.gltf",
          ghost: "/src/assets/game/sweetWorld/scene.gltf",
        },
      },
    });

    const selectedTheme= ref<keyof IThemes>("realistic"); 

    function setSelectedTheme (themeName : keyof IThemes){
        if(themes.value[themeName]){
            selectedTheme.value=themeName;
            console.log(`Theme: ${themeName} set successfully.`);
        }else{
            console.log("Fehlgeschlagen, ungültiger Theme:", themeName);
        }
    }
  

    //Getter zum aktuellen Theme
    const currentTheme= computed(()=>{
        if (selectedTheme.value) {
            return themes.value[selectedTheme.value];
          }
    });

    return{
        themes,
        selectedTheme,
        setSelectedTheme,
        currentTheme, 
    };

});