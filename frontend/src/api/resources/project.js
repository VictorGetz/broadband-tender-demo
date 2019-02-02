import axios from 'axios'

async function getProjectsList() {
    const {data} = await axios.get('/projects')

    return data
}

async function createProject() {
    const {data} = await axios.post('/projects')

    return data
}

async function getProject(id) {
    const {data} = await axios.get('/projects/' + id)

    return data
}

async function updateProject(id) {
    const {data} = await axios.put('/project/' + id)

    return data
}

async function deleteProject(id) {
    const {data} = await axios.delete('/project/' + id)

    return data
}

export default {
    getProjectsList,
    createProject,
    getProject,
    updateProject,
    deleteProject,
}
