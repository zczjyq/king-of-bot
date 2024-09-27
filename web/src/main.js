import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import Particles from 'particles.vue3'

createApp(App).use(store).use(router).use(Particles).mount('#app')
