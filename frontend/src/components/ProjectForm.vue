<template>
    <ProjectFormCommune v-if="loaded && isStepCommune" v-loading="loading" @proceed="proceedToStep2"></ProjectFormCommune>
    <ProjectFormInfo v-else-if="loaded && isStepProjectInfo" v-loading="loading" @proceed="save"></ProjectFormInfo>
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
                form: {
                    municipality:'',
                    ags: null,
                    district:'',
                    region:'',
                    contactPerson:'',

                    fttc: {
                        accessEmptyPipes: false,
                        debundledAccess: false,
                        bitstreamAccess: false,
                    },

                    fttb_ftth: {
                        accessEmptyPipes: false,
                        accessDarkFiber: false,
                        debundledAccess: false,
                        bitstreamAccess: false,
                    },

                    cable: {
                        accessEmptyPipes: false,
                        bitstreamAccess: false,
                    },

                    passiveNetInfrastructure: {
                        accessEmptyPipes: false,
                        accessDarkFiber: false,
                        debundledAccess: false,
                    },

                    mobileNetworks: {
                        bitstreamAccess: false,
                        sharedUse: false,
                        backHaulNetworkAccess: false,
                    },

                    satellitePlatform: {
                        bitstreamAccess: false,
                    },
                }
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

            save(project) {
                const endpoint = project.id ? this.api.project.updateProject : this.api.project.createProject
                endpoint(project).then(() => {
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

