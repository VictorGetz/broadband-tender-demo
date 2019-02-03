<template>
    <ProjectFormCommune v-if="loaded && isStepCommune" v-loading="loading" @proceed="proceedToStep2"></ProjectFormCommune>
    <ProjectFormInfo v-else-if="loaded && isStepProjectInfo" v-loading="loading" @save="save"></ProjectFormInfo>
</template>

<script>
    import {mapActions, mapGetters} from 'vuex'
    import ProjectFormCommune from "./ProjectFormCommune";
    import ProjectFormInfo from "./ProjectFormInfo";

    export default {
        props: {
            id: {
                required: false
            },
        },

        components: {
            ProjectFormCommune,
            ProjectFormInfo,
        },

        computed: {
            ...mapGetters([
                'project'
            ]),
            isStepCommune() {
                return this.step === 1
            },

            isStepProjectInfo() {
                return this.step === 2
            },

            loaded() {
                return this.loading === false
            }
        },

        data() {
            return {
                step: 1,
                loading: true,
            }
        },

        methods: {

            ...mapActions([
                'getProject',
                'storeProject',
            ]),

            proceedToStep2(project) {
                this.storeProject(project)
                this.step = 2
            },

            save() {
                const endpoint = this.project.id ? this.api.project.updateProject : this.api.project.createProject
                endpoint(this.project).then(() => {
                    this.$router.push({name: 'home'})
                })
            }
        },


        async created() {
            if(this.id) {
                await this.getProject(this.id)
            }
            this.loading = false
            //this.resetWholesaleProducts()
        }
    }
</script>

