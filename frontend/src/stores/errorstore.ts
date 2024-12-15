import { defineStore } from 'pinia';
import { useToast } from 'vue-toastification';


const toast = useToast();

export const useErrorStore = defineStore("errorstore", () => {


    async function fetchError() {
        try {
            const response = await fetch('/api/....');
            if(!response.ok){
                throw new Error('Fehler beim empfangen der Error Daten')
            }

            const data = await response.json();
            console.log('Error Daten erfolgreich aus dem Backend empfangen:', data);
        } catch (error: any) {
            toast.error('Empfangsfehler: ${error.message}' )
        }
    }
    
})