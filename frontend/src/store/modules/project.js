//import api from '../../api'
import * as _ from "lodash"
import api from '../../api'

const state = {
    projects: [],
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

    storeProjects(state, {projects}) {
        state.projects = projects
    },
}

const actions = {
    storeProject({commit}, project) {
        commit('storeProject', {project})
    },

    getProject({commit}, id) {
        return new Promise(async (resolve, reject) => {
            if(_.get(state.project, 'id')) {
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
            if(state.projects.length) {
                resolve(state.projects);
                return;
            }

            const result = await api.project.getProjectsList();
            if(!result) {
                return reject();
            }

            commit('storeProjects', {projects:result});
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
