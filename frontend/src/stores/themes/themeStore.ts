import { defineStore } from "pinia";
import type { IThemes, IThemeDTD } from "@/stores/game/dtd/ThemesDTD";
import { ref, computed } from "vue";

export const useThemeStore = defineStore("theme", () => {
const themes = ref <IThemes>({
    christmas: {
        ground: "@/assets/game/christmas/ChristmasGround.png",
        wall: "@/assets/game/christmas/ChristmasWall.png",
        character: {
          snackman: "@/assets/game/christmas/snackman/scene.gltf",
          ghost: "@/assets/game/christmas/ghost/scene.gltf",
        },
      },
      realistic: {
        ground: "@/assets/game/realistic/ground.png",
        wall: "@/assets/game/realistic/wall.png",
        character: {
          snackman: "@/assets/game/realistic/snackman/snackman.gltf",
          ghost: "@/assets/game/realistic/ghost/ghost.gltf",
        },
      },
      retro: {
        ground: "@/assets/game/retro/cobblestone_2.png",
        wall: "@/assets/game/retro/dirty_1.png",
        character: {
          snackman: "@/assets/game/retro/scene.gltf",
          ghost: "@/assets/game/retro/scene.gltf",
        },
      },
      sweetWorld: {
        ground: "@/assets/game/sweetWorld/sweetWorldFloor.jpg",
        wall: "@/assets/game/sweetWorld/sweetWorldWall.png",
        character: {
          snackman: "@/assets/game/sweetWorld/scene.gltf",
          ghost: "@/assets/game/sweetWorld/scene.gltf",
        },
      },
    });

    const selectedTheme= ref<keyof IThemes>("realistic"); 

    function setSelectedTheme (themeName : keyof IThemes){
        if(themes.value[themeName]){
            selectedTheme.value=themeName;
            console.log("Theme : ${themeName} gesetzt");
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