import Vue from 'vue'
import ElementUI from 'element-ui';
//import 'element-ui/lib/theme-chalk/index.css';
import '@/scss/element.scss'
import App from './App.vue'
import router from './router'
import store from './store'
import api from './mixins/api'

Vue.config.productionTip = false

Vue.mixin(api)

Vue.use(ElementUI);

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')