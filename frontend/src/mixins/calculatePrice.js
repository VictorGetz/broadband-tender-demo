import {productMapping} from './transformWholesaleProducts'
import {mapGetters} from 'vuex'
import * as _ from "lodash";

export default {
    computed: {
        ...mapGetters([
            'pricingMasterData',
            'pricingOptions',
        ]),

        totalPrice() {
            let price = 0

            _.each(this.pricingOptions, pricingOption => {
                _.each(productMapping, product => {
                    if(pricingOption.hasOwnProperty(product.internal) && pricingOption[product.internal] === true) {
                        if(this.pricingMasterData.hasOwnProperty(product.external)) {
                            price += this.pricingMasterData[product.external]
                        }
                    }
                })
            })

            console.log('calculatePrice', price, this.pricingOptions)

            return price
        }
    }
}