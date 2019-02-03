import Vue from 'vue'
import ElementUI from 'element-ui';

import '@/scss/element.scss'
import App from './App.vue'
import router from './router'
import store from './store/'
import api from './mixins/api'
import euros from './filters/euros'

Vue.config.productionTip = false

Vue.mixin(api)

Vue.filter('euros', euros)

Vue.use(ElementUI);

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')