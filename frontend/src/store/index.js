import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

const modules = {};
const context = require.context('./modules', false, /.*\.js$/);

context.keys().forEach(file => {
    const name = file.replace(/(^.\/)|(\.js$)/g, '');

    modules[name] = context(file).default;
});

export default new Vuex.Store({modules});
