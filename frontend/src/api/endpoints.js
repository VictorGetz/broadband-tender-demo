import axios from 'axios'

export const PROVIDER = window.config.provider
export const AUTH = window.config.auth
axios.defaults.baseURL = PROVIDER
axios.defaults.auth = AUTH
