import axios from 'axios'

async function getPricingMasterData() {
    const {data} = await axios.get('/masterdata/pricing')

    return data
}

export default {
    getPricingMasterData,
}
