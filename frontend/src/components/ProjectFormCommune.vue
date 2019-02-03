<template>
    <div>
        <el-form ref="form" :model="projectLocal" :rules="rules" label-position="left" label-width="250px">
            <el-form-item label="Kommune" prop="localAuthorityDistrict.name">
                <el-input v-model="projectLocal.localAuthorityDistrict.name" placeholder="Name der Kommune"></el-input>
            </el-form-item>
            <el-form-item label="Amtlicher Gemeindeschlüssel (AGS)" prop="localAuthorityDistrict.districtKey">

                <el-input v-model="projectLocal.localAuthorityDistrict.districtKey" maxlength="12" placeholder="Amtlicher Gemeindeschlüssel (AGS)"></el-input>
            </el-form-item>

            <el-form-item label="Landkreis" prop="localAuthorityDistrict.district">
                <el-input v-model="projectLocal.localAuthorityDistrict.district" placeholder=""></el-input>
            </el-form-item>
            <el-form-item label="Regierungsbezirk" prop="localAuthorityDistrict.governmentDistrict">
                <el-select v-model="projectLocal.localAuthorityDistrict.governmentDistrict" placeholder="Regierungsbezirk">
                    <el-option label="Oberbayern" value="Oberbayern"></el-option>
                    <el-option label="Niederbayern" value="Niederbayern"></el-option>
                    <el-option label="Oberpfalz" value="Oberpfalz"></el-option>
                    <el-option label="Oberfranken" value="Oberfranken"></el-option>
                    <el-option label="Mittelfranken" value="Mittelfranken"></el-option>
                    <el-option label="Unterfranken" value="Unterfranken"></el-option>
                    <el-option label="Schwaben" value="Schwaben"></el-option>
                </el-select>
            </el-form-item>

            <el-form-item label="Ansprechpartner">
                <el-input v-model="projectLocal.localAuthorityDistrict.contactPerson.name" placeholder="Ansprechpartner"></el-input>
            </el-form-item>

            <el-form-item label="Name Erschließungsgebiet" prop="projectMasterData.developmentAreaName">
                <el-input v-model="projectLocal.projectMasterData.developmentAreaName" placeholder="Name Erschließungsgebiet"></el-input>
            </el-form-item>

            <el-form-item label="Ausbauender Netzbetreiber">
                <el-input v-model="projectLocal.projectMasterData.networkOperator" placeholder="Ausbauender Netzbetreiber"></el-input>
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

    export default {
        data() {
            return {
                projectLocal: {},
                rules: {
                    localAuthorityDistrict: {
                        name: [
                            { required: true, message: 'Bitte geben Sie die Kommune ein.', trigger: 'blur' },
                        ],
                        districtKey: [
                            { required: true, message: 'Bitte geben Sie den AGS ein.', trigger: 'blur' },
                            { min: 12, message: 'Der AGS muss 12 Stellen haben.', trigger: 'blur' },
                        ],
                        district: [
                            { required: true, message: 'Bitte geben Sie den Landkreis ein.', trigger: 'blur' },
                        ],
                        governmentDistrict: [
                            { required: true, message: 'Bitte geben Sie den Regierungsbezirk ein.', trigger: 'input' },
                        ],
                    },
                    projectMasterData: {
                        developmentAreaName: [
                            { required: true, message: 'Bitte geben Sie das Erschließungsgebiet ein.', trigger: 'blur' },
                        ]
                    }
                }

            }
        },

        computed: {
            ...mapGetters([
                'project'
            ]),
        },

        methods: {
            ...mapActions([
                'storeProject'
            ]),

            proceed() {
                this.$refs.form.validate((valid) => {
                    if (valid) {
                        this.$emit('proceed', this.projectLocal)
                    }
                });
            }
        },

        created() {
            this.projectLocal = this.project
        },
    }
</script>
