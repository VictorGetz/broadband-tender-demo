<template>
    <div>
        <h1>Projekt bearbeiten</h1>
        <project-form :id="this.$route.params.id"></project-form>
    </div>
</template>
<script>
    import ProjectForm from '../components/ProjectForm'

    export default {
        components: {
            ProjectForm
        },
    }
</script>
