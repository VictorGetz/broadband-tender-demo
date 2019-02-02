//import api from '../../api'
//import * as _ from "lodash"

const state = {
    project: {
        municipality:'',
        ags:null,
        district:'',
        region:'',
        contactPerson:'',
    },
}

const mutations = {
    storeProject(state, {project}) {
        state.project = project
    },
}

const actions = {
    storeProject({commit}, project) {
        commit('storeProject', {project})
    },
}

const getters = {
    project: state => state.project,
}

export default {
    state,
    mutations,
    actions,
    getters,
}
