import axios from 'axios'

async function getProjectsList() {
    const {data} = await axios.get('/projects')

    return data
}

export default {
    getProjectsList,
}
