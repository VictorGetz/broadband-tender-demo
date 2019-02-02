<template>
    <div>
        <el-form ref="form" :model="form" v-loading="loading" label-position="left" label-width="170px">
            <el-form-item label="Art des Ausbaus">
                <el-select v-model="projectLocal.projectMasterData.projectType" placeholder="Bitte wählen" @change="onChangeType">
                    <el-option
                            v-for="item in typeOptions"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                    </el-option>
                </el-select>
            </el-form-item>

            <el-form-item label="Vorleistungsprodukte nach Ziffer 9 BdR. i.V.m 5.2 BdR nach ABI C 2013 25/1">

                <template label="FTTC" v-if="isFttc">
                    <label class="el-form-item__label--secondary">FTTC</label>
                    <el-checkbox v-model="wholesaleProducts.fttc.accessEmptyPipes">Zugang zu Leerrohren</el-checkbox><br>
                    <el-checkbox v-model="wholesaleProducts.fttc.debundledAccess">entbündelter Zugang zum KVz</el-checkbox><br>
                    <el-checkbox v-model="wholesaleProducts.fttc.bitstreamAccess">Bitstromzugang</el-checkbox><br>
                </template>

                <template label="FTTB/FTTH" v-if="isFttb_ftth">
                    <label class="el-form-item__label--secondary">FTTB/FTTH</label>
                    <el-checkbox v-model="wholesaleProducts.fttb_ftth.accessEmptyPipes">Zugang zu Leerrohren</el-checkbox><br>
                    <el-checkbox v-model="wholesaleProducts.fttb_ftth.accessDarkFiber">Zugang zu unbeschalteten Glasfaserleitung</el-checkbox><br>
                    <el-checkbox v-model="wholesaleProducts.fttb_ftth.debundledAccess">entbündelter Zugang zum Teilnehmeranschluss</el-checkbox><br>
                    <el-checkbox v-model="wholesaleProducts.fttb_ftth.bitstreamAccess">Bitstromzugang</el-checkbox><br>
                </template>

                <template label="Kabelnetz" v-if="isCable">
                    <label class="el-form-item__label--secondary">Kabelnetz</label>
                    <el-checkbox v-model="wholesaleProducts.cable.accessEmptyPipes">Zugang zu Leerrohren</el-checkbox><br>
                    <el-checkbox v-model="wholesaleProducts.cable.bitstreamAccess">Bitstromzugang</el-checkbox><br>
                </template>

                <div label="Passive Netzinfrastruktur" class="wrapper--passive-net-infrastructure" v-if="isFttc || isFttb_ftth || isCable">
                    <label class="el-form-item__label--secondary">Passive Netzinfrastruktur</label>
                    <el-checkbox v-model="wholesaleProducts.passiveNetInfrastructure.accessEmptyPipes">Zugang zu Leerrohren</el-checkbox><br>
                    <el-checkbox v-model="wholesaleProducts.passiveNetInfrastructure.accessDarkFiber">Zugang zu unbeschalteten Glasfaserleitung</el-checkbox><br>
                    <el-checkbox v-model="wholesaleProducts.passiveNetInfrastructure.debundledAccess">entbündelter Zugang zum Teilnehmeranschluss</el-checkbox><br>
                </div>

                <template label="Mobile/Drahtlose Netze" v-if="isMobileNetworks">
                    <label class="el-form-item__label--secondary">Mobile/Drahtlose Netze</label>
                    <el-checkbox v-model="wholesaleProducts.mobileNetworks.bitstreamAccess">Bitstromzugang</el-checkbox><br>
                    <el-checkbox v-model="wholesaleProducts.mobileNetworks.sharedUse">Gemeinsame Nutzung der physischen Masten</el-checkbox><br>
                    <el-checkbox v-model="wholesaleProducts.mobileNetworks.backHaulNetworkAccess">Zugang zu Backhaulnetzen</el-checkbox><br>
                </template>

            </el-form-item>

            <el-form-item>
                <el-button type="primary" @click="proceed">Weiter</el-button>
                <el-button @click="$router.push({name: 'home'})">Abbrechen</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>
<script>
    import {mapActions, mapGetters} from 'vuex'

    import transformWholesaleProducts from '../mixins/transformWholesaleProducts'

    export default {

        props: {
            form: {},
        },

        mixins: [
            transformWholesaleProducts
        ],

        data() {
            return {
                loading: false,
                projectLocal: {},
                typeOptions: [
                    {value: 'FTTC', label: 'FTTC'},
                    {value: 'FTTB_FTTH', label: 'FTTB/FTTH'},
                    {value: 'CABLENETWORK', label: 'Kabelnetz'},
                    {value: 'MOBILE_NETWORK', label: 'Mobile/Drahtlose Netzwerke'},
                ],
            }
        },

        methods: {
            ...mapActions([
                'storeProject'
            ]),

            proceed() {
                this.loading = true
                this.$emit('proceed', this.prepareProject())
            },

            prepareProject() {
                const project = {
                    localAuthorityDistrict: this.projectLocal.localAuthorityDistrict,
                    pricingMasterData: {
                        id: this.projectLocal.pricingMasterData.id,
                        pricingDetails: this.wholesaleProductsToServerFormat(),
                    },
                    projectMasterData: this.projectLocal.projectMasterData,
                }

                if(this.projectLocal.id) {
                    project.id = this.projectLocal.id
                }

                return project
            },

            onChangeType() {
                this.resetWholesaleProducts()
            },
        },

        computed: {
            ...mapGetters([
                'project'
            ]),


            isFttc() {
                return this.projectLocal.projectMasterData.projectType === 'FTTC'
            },
            isFttb_ftth() {
                return this.projectLocal.projectMasterData.projectType === 'FTTB_FTTH'
            },
            isCable() {
                return this.projectLocal.projectMasterData.projectType === 'CABLENETWORK'
            },
            isPassiveNetInfrastructure() {
                return this.projectLocal.projectMasterData.projectType === 'PASSIVE_NETWORK_INFRASTRUCTURE'
            },
            isMobileNetworks() {
                return this.projectLocal.projectMasterData.projectType === 'MOBILE_NETWORK'
            },
        },

        created() {
            this.projectLocal = this.project

            this.wholesaleProductsToClientFormat()
        },
    }
</script>

<style>
    .wrapper--passive-net-infrastructure {
        margin-top:20px;
    }
</style>
