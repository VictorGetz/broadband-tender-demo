<template>
    <div>
        <el-form ref="form" :model="form" label-position="left" label-width="170px">
            <el-form-item label="Art des Ausbaus">
                <el-select v-model="form.type" placeholder="Bitte wählen" @change="onChangeType">
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
                    <el-checkbox v-model="form.fttc.accessEmptyPipes">Zugang zu Leerrohren</el-checkbox><br>
                    <el-checkbox v-model="form.fttc.dubundledAccess">entbündelter Zugang zum KVz</el-checkbox><br>
                    <el-checkbox v-model="form.fttc.bitstreamAccess">Bitstromzugang</el-checkbox><br>
                </template>

                <template label="FTTB/FTTH" v-if="isFttb_ftth">
                    <label class="el-form-item__label--secondary">FTTB/FTTH</label>
                    <el-checkbox v-model="form.fttb_ftth.accessEmptyPipes">Zugang zu Leerrohren</el-checkbox><br>
                    <el-checkbox v-model="form.fttb_ftth.accessDarkFiber">Zugang zu unbeschalteten Glasfaserleitung</el-checkbox><br>
                    <el-checkbox v-model="form.fttb_ftth.dubundledAccess">entbündelter Zugang zum Teilnehmeranschluss</el-checkbox><br>
                    <el-checkbox v-model="form.fttb_ftth.bitstreamAccess">Bitstromzugang</el-checkbox><br>
                </template>

                <template label="Kabelnetz" v-if="isCable">
                    <label class="el-form-item__label--secondary">Kabelnetz</label>
                    <el-checkbox v-model="form.cable.accessEmptyPipes">Zugang zu Leerrohren</el-checkbox><br>
                    <el-checkbox v-model="form.cable.bitstreamAccess">Bitstromzugang</el-checkbox><br>
                </template>

                <div label="Passive Netzinfrastruktur" class="wrapper--passive-net-infrastructure" v-if="isFttc || isFttb_ftth || isCable">
                    <label class="el-form-item__label--secondary">Passive Netzinfrastruktur</label>
                    <el-checkbox v-model="form.passiveNetInfrastructure.accessEmptyPipes">Zugang zu Leerrohren</el-checkbox><br>
                    <el-checkbox v-model="form.passiveNetInfrastructure.accessDarkFiber">Zugang zu unbeschalteten Glasfaserleitung</el-checkbox><br>
                    <el-checkbox v-model="form.passiveNetInfrastructure.dubundledAccess">entbündelter Zugang zum Teilnehmeranschluss</el-checkbox><br>
                </div>

                <template label="Mobile/Drahtlose Netze" v-if="isMobileNetworks">
                    <label class="el-form-item__label--secondary">Mobile/Drahtlose Netze</label>
                    <el-checkbox v-model="form.mobileNetworks.bitstreamAccess">Bitstromzugang</el-checkbox><br>
                    <el-checkbox v-model="form.mobileNetworks.sharedUse">Gemeinsame Nutzung der physischen Masten</el-checkbox><br>
                    <el-checkbox v-model="form.mobileNetworks.backHaulNetworkAccess">Zugang zu Backhaulnetzen</el-checkbox><br>
                </template>

                <template label="Satellitenplattform" v-if="isSatellitePlatform">
                    <label class="el-form-item__label--secondary">Satellitenplattform</label>
                    <el-checkbox v-model="form.satellitePlatform.bitstreamAccess">Bitstromzugang</el-checkbox>
                </template>
            </el-form-item>

            <!--

                    mobileNetworks: {
                        bitstreamAccess: false,
                        sharedUse: false,
                        backHaulNetworkAccess: false,
                    },

                    satellitePlatform: {
                        bitstreamAccess: false,
                    },
            -->

            <el-form-item>
                <el-button type="primary" @click="proceed">Weiter</el-button>
                <el-button @click="$router.push({name: 'home'})">Abbrechen</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>
<script>
    export default {

        props: {
            form: {},
        },

        data() {
            return {
                typeOptions: [
                    {value: 'fttc', label: 'FTTC'},
                    {value: 'fttb_ftth', label: 'FTTB/FTTH'},
                    {value: 'cable', label: 'Kabelnetz'},
                    {value: 'mobileNetworks', label: 'Mobile/Drahtlose Netzwerke'},
                    {value: 'satellitePlatform', label: 'Satellitenplattform'},
                ],
            }
        },

        methods: {
            proceed() {
                this.$emit('proceed')
            },

            onChangeType() {
                console.log('change-type')
                this.$emit('change-type')
            }
        },

        computed: {
            isFttc() {
                return this.form.type === 'fttc'
            },
            isFttb_ftth() {
                return this.form.type === 'fttb_ftth'
            },
            isCable() {
                return this.form.type === 'cable'
            },
            isPassiveNetInfrastructure() {
                return this.form.type === 'passiveNetInfrastructure'
            },
            isMobileNetworks() {
                return this.form.type === 'mobileNetworks'
            },
            isSatellitePlatform() {
                return this.form.type === 'satellitePlatform'
            },
        }
    }
</script>

<style>
    .wrapper--passive-net-infrastructure {
        margin-top:20px;
    }
</style>
