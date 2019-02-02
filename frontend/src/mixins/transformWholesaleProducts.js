import * as _ from 'lodash'
import {mapGetters} from 'vuex'

export default {

    data() {
        return {
            wholesaleProducts: {
                fttc: {
                    accessEmptyPipes: false,
                    debundledAccess: false,
                    bitstreamAccess: false,
                },

                fttb_ftth: {
                    accessEmptyPipes: false,
                    accessDarkFiber: false,
                    debundledAccess: false,
                    bitstreamAccess: false,
                },

                cable: {
                    accessEmptyPipes: false,
                    bitstreamAccess: false,
                },

                passiveNetInfrastructure: {
                    accessEmptyPipes: false,
                    accessDarkFiber: false,
                    debundledAccess: false,
                },

                mobileNetworks: {
                    bitstreamAccess: false,
                    sharedUse: false,
                    backHaulNetworkAccess: false,
                },

                satellitePlatform: {
                    bitstreamAccess: false,
                },
            }
        }
    },

    computed: {
        ...mapGetters([
            'project'
        ])
    },

    methods: {
        wholesaleProductsToClientFormat() {
            const pricingDetails = this.project.pricingMasterData.pricingDetails

            const fttc = _.find(pricingDetails, category => category.pricingDetailType === 'FTTC') || {pricingItems: []}
            const fttb_ftth = _.find(pricingDetails, category => category.pricingDetailType === 'FTTB_FTTH') || {pricingItems: []}
            const cableNetwork = _.find(pricingDetails, category => category.pricingDetailType === 'CABLENETWORK') || {pricingItems: []}
            const passiveNetworkInfrastructure = _.find(pricingDetails, category => category.pricingDetailType === 'PASSIVE_NETWORK_INFRASTRUCTURE') || {pricingItems: []}
            const mobileNetwork = _.find(pricingDetails, category => category.pricingDetailType === 'MOBILE_NETWORK') || {pricingItems: []}

            this.wholesaleProducts.fttc.accessEmptyPipes = _.find(fttc.pricingItems, item => item.pricingItemType === 'ACCESS_EMPTY_PIPES') !== undefined
            this.wholesaleProducts.fttc.debundledAccess = _.find(fttc.pricingItems, item => item.pricingItemType === 'DEBUNDLED_ACCESS') !== undefined
            this.wholesaleProducts.fttc.bitstreamAccess = _.find(fttc.pricingItems, item => item.pricingItemType === 'BITSTREAM_ACCESS') !== undefined

            this.wholesaleProducts.fttb_ftth.accessEmptyPipes = _.find(fttb_ftth.pricingItems, item => item.pricingItemType === 'ACCESS_EMPTY_PIPES') !== undefined
            this.wholesaleProducts.fttb_ftth.accessDarkFiber = _.find(fttb_ftth.pricingItems, item => item.pricingItemType === 'ACCESSDARKFIBER') !== undefined
            this.wholesaleProducts.fttb_ftth.debundledAccess = _.find(fttb_ftth.pricingItems, item => item.pricingItemType === 'DEBUNDLED_ACCESS') !== undefined
            this.wholesaleProducts.fttb_ftth.bitstreamAccess = _.find(fttb_ftth.pricingItems, item => item.pricingItemType === 'BITSTREAM_ACCESS') !== undefined

            this.wholesaleProducts.cable.accessEmptyPipes = _.find(cableNetwork.pricingItems, item => item.pricingItemType === 'ACCESS_EMPTY_PIPES') !== undefined
            this.wholesaleProducts.cable.bitstreamAccess = _.find(cableNetwork.pricingItems, item => item.pricingItemType === 'BITSTREAM_ACCESS') !== undefined

            this.wholesaleProducts.passiveNetInfrastructure.accessEmptyPipes = _.find(passiveNetworkInfrastructure.pricingItems, item => item.pricingItemType === 'ACCESS_EMPTY_PIPES') !== undefined
            this.wholesaleProducts.passiveNetInfrastructure.accessDarkFiber = _.find(passiveNetworkInfrastructure.pricingItems, item => item.pricingItemType === 'BITSTREAM_ACCESS') !== undefined
            this.wholesaleProducts.passiveNetInfrastructure.debundledAccess = _.find(passiveNetworkInfrastructure.pricingItems, item => item.pricingItemType === 'BITSTREAM_ACCESS') !== undefined

            this.wholesaleProducts.mobileNetworks.bitstreamAccess = _.find(mobileNetwork.pricingItems, item => item.pricingItemType === 'BITSTREAM_ACCESS') !== undefined
            this.wholesaleProducts.mobileNetworks.sharedUse = _.find(mobileNetwork.pricingItems, item => item.pricingItemType === 'SHARED_USE') !== undefined
            this.wholesaleProducts.mobileNetworks.backHaulNetworkAccess = _.find(mobileNetwork.pricingItems, item => item.pricingItemType === 'BACKHAULNET_WORKACCESS') !== undefined
        },


        wholesaleProductsToServerFormat() {
            const categoryMapping = [
                {internal: 'fttc', external: 'FTTC'},
                {internal: 'fttb_ftth', external: 'FTTB_FTTH'},
                {internal: 'cable', external: 'CABLENETWORK'},
                {internal: 'passiveNetInfrastructure', external: 'PASSIVE_NETWORK_INFRASTRUCTURE'},
                {internal: 'mobileNetworks', external: 'MOBILE_NETWORK'},
            ]

            const productMapping = [
                {internal: 'accessEmptyPipes', external: 'ACCESS_EMPTY_PIPES'},
                {internal: 'debundledAccess', external: 'DEBUNDLED_ACCESS'},
                {internal: 'bitstreamAccess', external: 'BITSTREAM_ACCESS'},
                {internal: 'accessDarkFiber', external: 'ACCESSDARKFIBER'},
                {internal: 'backHaulNetworkAccess', external: 'BACKHAULNET_WORKACCESS'},
            ]

            let categories = []

            _.each(categoryMapping, category => {
                const hasSelectedOptionInCategory = _.some(this.wholesaleProducts[category.internal])
                if(hasSelectedOptionInCategory) {
                    let products = []
                    _.each(productMapping, product => {
                        if(this.wholesaleProducts[category.internal][product.internal] === true) {
                            products.push({
                                pricingItemType: product.external
                            })
                        }
                    })

                    categories.push({
                        pricingDetailType: category.external,
                        pricingItems: products,
                    })
                }
            })

            return categories
        },

        resetWholesaleProducts() {
            this.wholesaleProducts.fttc.accessEmptyPipes = false
            this.wholesaleProducts.fttc.debundledAccess = false
            this.wholesaleProducts.fttc.bitstreamAccess = false
            this.wholesaleProducts.fttb_ftth.accessEmptyPipes = false
            this.wholesaleProducts.fttb_ftth.accessDarkFiber = false
            this.wholesaleProducts.fttb_ftth.debundledAccess = false
            this.wholesaleProducts.fttb_ftth.bitstreamAccess = false
            this.wholesaleProducts.cable.accessEmptyPipes = false
            this.wholesaleProducts.cable.bitstreamAccess = false
            this.wholesaleProducts.passiveNetInfrastructure.accessEmptyPipes = false
            this.wholesaleProducts.passiveNetInfrastructure.accessDarkFiber = false
            this.wholesaleProducts.passiveNetInfrastructure.debundledAccess = false
            this.wholesaleProducts.mobileNetworks.bitstreamAccess = false
            this.wholesaleProducts.mobileNetworks.sharedUse = false
            this.wholesaleProducts.mobileNetworks.backHaulNetworkAccess = false
            this.wholesaleProducts.satellitePlatform.bitstreamAccess = false

        },
    }
}