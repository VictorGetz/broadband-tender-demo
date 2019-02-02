<template>
    <!--
    <ProjectFormCommune v-if="loaded && isStepCommune" v-loading="loading" :form="form" :project="project" @proceed="proceedToStep2"></ProjectFormCommune>
    <ProjectFormInfo v-else-if="loaded && isStepProjectInfo" v-loading="loading" :form="form" :project="project" @proceed="proceedToStep2" @change-type="resetWholesaleProducts"></ProjectFormInfo>
    -->
    <pre>{{project}}</pre>
</template>

<script>
    import {mapActions, mapGetters} from 'vuex'
    import ProjectFormCommune from "./ProjectFormCommune";
    import ProjectFormInfo from "./ProjectFormInfo";

    export default {
        props: {
            id: {
                type: String
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
                'getProject'
            ]),
            proceedToStep2() {
                this.step = 2
            },

            resetWholesaleProducts() {
                return;
                this.form.fttc.accessEmptyPipes = false
                this.form.fttc.debundledAccess = false
                this.form.fttc.bitstreamAccess = false

                this.form.fttb_ftth.accessEmptyPipes = false
                this.form.fttb_ftth.accessDarkFiber = false
                this.form.fttb_ftth.debundledAccess = false
                this.form.fttb_ftth.bitstreamAccess = false

                this.form.cable.accessEmptyPipes = false
                this.form.cable.bitstreamAccess = false

                this.form.passiveNetInfrastructure.accessEmptyPipes = false
                this.form.passiveNetInfrastructure.accessDarkFiber = false
                this.form.passiveNetInfrastructure.debundledAccess = false

                this.form.mobileNetworks.bitstreamAccess = false
                this.form.mobileNetworks.sharedUse = false
                this.form.mobileNetworks.backHaulNetworkAccess = false

                this.form.satellitePlatform.bitstreamAccess = false
            }
        },


        async created() {
            this.form = this.project
            if(this.id) {
                const project = await this.getProject(this.id)
                this.form = project
            }
            this.loading = false
            //this.resetWholesaleProducts()
        }
    }
</script>

