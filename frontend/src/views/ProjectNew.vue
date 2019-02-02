<template>
  <div>
    <h1>Neues Projekt</h1>
    <project-form></project-form>
  </div>
</template>
<script>
  import ProjectForm from '../components/ProjectForm'

  import {mapActions} from 'vuex'

  export default {
      components: {
          ProjectForm
      },

      methods: {
          ...mapActions([
              'resetProject'
          ]),
      },

      created() {
          this.resetProject()
      }
  }
</script>
