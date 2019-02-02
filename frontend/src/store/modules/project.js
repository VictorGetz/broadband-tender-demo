//import api from '../../api'
import * as _ from "lodash"
import api from '../../api'

const state = {
    projects: [],
    project: {},
}

const mutations = {
    storeProject(state, project) {
        state.project = project
    },

    storeProjects(state, projects) {
        state.projects = projects
    },
}

const actions = {
    storeProject({commit}, project) {
        commit('storeProject', project)
    },

    resetProject({commit}) {
        const projectTemplate = {
            localAuthorityDistrict: {
                name: '',
                districtKey: '',
                district: '',
                governmentDistrict: '',
                contactPerson: {
                    name: ''
                },
            },

            projectMasterData: {
                id: null,
                developmentAreaName: '',
                networkOperator: '',
                projectType: null
            },
            pricingMasterData: {
                pricingDetails: [],
                sumValue: 0,
            },


        }

        commit('storeProject', projectTemplate);
    },

    getProject({commit}, id) {
        return new Promise(async (resolve, reject) => {
            if(_.get(state.project, 'id') === id) {
                resolve(state.project);
                return;
            }

            const project = await api.project.getProject(id)

            if(!project) {
                return reject();
            }

            commit('storeProject', project);

            resolve(project);
        });
    },

    getProjectList({commit}) {
        return new Promise(async (resolve, reject) => {

            const result = await api.project.getProjectsList();
            if(!result) {
                return reject();
            }

            commit('storeProjects', result);
            resolve(result);
        });
    },
}

const getters = {
    project: state => state.project,
    projects: state => state.projects,
}

export default {
    state,
    mutations,
    actions,
    getters,
}
