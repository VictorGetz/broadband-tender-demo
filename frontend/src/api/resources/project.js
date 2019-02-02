import axios from 'axios'

async function getProjectsList() {
    const {data} = await axios.get('/projects')

    return data
}

async function createProject(project) {
    const {data} = await axios.post('/projects', project)

    return data
}

async function getProject(id) {
    const {data} = await axios.get('/projects/' + id)

    return data
}

async function updateProject(project) {
    const {data} = await axios.put('/projects/' + project.id, project)

    return data
}

async function deleteProject(id) {
    const {data} = await axios.delete('/projects/' + id)

    return data
}

export default {
    getProjectsList,
    createProject,
    getProject,
    updateProject,
    deleteProject,
}
