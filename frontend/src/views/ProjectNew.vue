<template>
  <div>
    <h1>Neues Projekt</h1>
    <project-form></project-form>
  </div>
</template>
<script>
  import ProjectForm from '../components/ProjectForm'

  export default {
      components: {
          ProjectForm
      },

      created() {
        console.log(this.$route)
      }
  }
</script>
