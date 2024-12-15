import './assets/style.css'

import { createApp } from 'vue'
import App from './App.vue'
import router from './router'

import Toast from 'vue-toastification';
// Toast Styles
import 'vue-toastification/dist/index.css';



// Pinia importieren
import { createPinia } from 'pinia'

const pinia = createPinia()
const app = createApp(App)

app.use(pinia)
app.use(router)
app.use(Toast)

app.mount('#app')
