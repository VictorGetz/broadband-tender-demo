import api from '../../api'

const state = {
    pricingMasterData: {},
    pricingOptions: {},
}

const mutations = {
    storePricingMasterData(state, pricingMasterData) {
        state.pricingMasterData = pricingMasterData
    },

    storePricingOptions(state, pricingOptions) {
        state.pricingOptions = pricingOptions
    },

}

const actions = {
    storePricingOptions({commit}, pricingOptions) {
        commit('storePricingOptions', pricingOptions)
    },
    storePricingMasterData({commit}, storePricingMasterData) {
        commit('storePricingMasterData', storePricingMasterData)
    },

    getPricingMasterData({commit}) {
        return new Promise(async (resolve, reject) => {
            if(state.pricingMasterData.length) {
                resolve(state.pricingMasterData);
                return;
            }

            const result = await api.pricing.getPricingMasterData();
            if(!result) {
                return reject();
            }

            commit('storePricingMasterData', result);
            resolve(result);
        });
    },
}

const getters = {
    pricingMasterData: state => state.pricingMasterData,
    pricingOptions: state => state.pricingOptions,
}

export default {
    state,
    mutations,
    actions,
    getters,
}
