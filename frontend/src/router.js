import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'
import ProjectNew from './views/ProjectNew.vue'
import ProjectEdit from './views/ProjectEdit.vue'

Vue.use(Router)

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/project/new',
      name: 'project.new',
      component: ProjectNew
    },
    {
      path: '/project/:id',
      name: 'project.edit',
      component: ProjectEdit
    }
  ]
})
