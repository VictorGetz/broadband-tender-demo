const modules = {};
const context = require.context('./resources', false, /.*\.js$/);
import * as _ from "lodash"
import axios from 'axios'

axios.defaults.baseURL = window.config.provider
axios.defaults.auth = window.config.auth

context.keys().forEach(file => {
    const name = _.camelCase(file.replace(/(^.\/)|(\.js$)/g, ''));
    modules[name] = context(file).default;
});

export default modules;
