<template>
    <ProjectFormCommune v-if="isStepCommune" :form="form" @proceed="proceedToStep2"></ProjectFormCommune>
    <ProjectFormInfo v-else-if="isStepProjectInfo" :form="form" @proceed="proceedToStep2" @change-type="resetWholesaleProducts"></ProjectFormInfo>
</template>

<script>
    import ProjectFormCommune from "./ProjectFormCommune";
    import ProjectFormInfo from "./ProjectFormInfo";

    export default {
        components: {
            ProjectFormCommune,
            ProjectFormInfo,
        },

        computed: {
            isStepCommune() {
                return this.step === 1
            },
            isStepProjectInfo() {
                return this.step === 2
            },

        },

        data() {
            return {
                step: 1,
                form: {
                    municipality:'',
                    ags: null,
                    district:'',
                    region:'',
                    contactPerson:'',


                    fttc: {
                        accessEmptyPipes: false,
                        dubundledAccess: false,
                        bitstreamAccess: false,
                    },

                    fttb_ftth: {
                        accessEmptyPipes: false,
                        accessDarkFiber: false,
                        dubundledAccess: false,
                        bitstreamAccess: false,
                    },

                    cable: {
                        accessEmptyPipes: false,
                        bitstreamAccess: false,
                    },

                    passiveNetInfrastructure: {
                        accessEmptyPipes: false,
                        accessDarkFiber: false,
                        dubundledAccess: false,
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
            proceedToStep2() {
                this.step = 2
            },

            resetWholesaleProducts() {
                this.form.fttc.accessEmptyPipes = false
                this.form.fttc.dubundledAccess = false
                this.form.fttc.bitstreamAccess = false

                this.form.fttb_ftth.accessEmptyPipes = false
                this.form.fttb_ftth.accessDarkFiber = false
                this.form.fttb_ftth.dubundledAccess = false
                this.form.fttb_ftth.bitstreamAccess = false

                this.form.cable.accessEmptyPipes = false
                this.form.cable.bitstreamAccess = false

                this.form.passiveNetInfrastructure.accessEmptyPipes = false
                this.form.passiveNetInfrastructure.accessDarkFiber = false
                this.form.passiveNetInfrastructure.dubundledAccess = false

                this.form.mobileNetworks.bitstreamAccess = false
                this.form.mobileNetworks.sharedUse = false
                this.form.mobileNetworks.backHaulNetworkAccess = false

                this.form.satellitePlatform.bitstreamAccess = false
            }
        },

        created() {
            this.resetWholesaleProducts()
        }
    }
</script>

