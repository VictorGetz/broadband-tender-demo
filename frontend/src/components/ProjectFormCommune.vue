<template>
    <div>
        <el-form ref="form" :model="projectLocal" label-position="left" label-width="220px">
            <el-form-item label="Kommune">
                <el-col :span="11">
                    <el-input v-model="projectLocal.localAuthorityDistrict.name" placeholder="Name der Kommune"></el-input>
                </el-col>
                <el-col class="line" :span="2">&nbsp;</el-col>
                <el-col :span="11">
                    <el-input v-model="projectLocal.localAuthorityDistrict.districtKey" placeholder="Amtlicher Gemeindeschlüssel (AGS)"></el-input>
                </el-col>
            </el-form-item>

            <el-form-item label="Landkreis">
                <el-input v-model="projectLocal.localAuthorityDistrict.district" placeholder=""></el-input>
            </el-form-item>
            <el-form-item label="Regierungsbezirk">
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

            <el-form-item label="Name Erschließungsgebiet">
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
                projectLocal: {}
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
                this.$emit('proceed', this.projectLocal)
            }
        },

        created() {
            this.projectLocal = this.project
        },
    }
</script>
