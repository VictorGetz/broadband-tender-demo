<template>
    <pre  v-if="true">{{form}}</pre>
    <el-form v-else ref="form" :model="form" label-position="left" label-width="170px">

        <el-form-item label="Kommune">
            <el-col :span="11">
                <el-input v-model="form.localAuthorityDistrict.name" placeholder="Name der Kommune"></el-input>
            </el-col>
            <el-col class="line" :span="2">&nbsp;</el-col>
            <el-col :span="11">
                <el-input v-model="form.ags" placeholder="Amtlicher Gemeindeschlüssel (AGS)"></el-input>
            </el-col>
        </el-form-item>

        <el-form-item label="Landkreis">
            <el-input v-model="form.district" placeholder=""></el-input>
        </el-form-item>
        <el-form-item label="Regierungsbezirk">
            <el-select v-model="form.region" placeholder="Regierungsbezirk">
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
            <el-input v-model="form.contactPerson" placeholder="Frau Mustermann"></el-input>
        </el-form-item>

        <el-form-item>
            <el-button type="primary" @click="proceed">Weiter</el-button>
            <el-button @click="$router.push({name: 'home'})">Abbrechen</el-button>
        </el-form-item>
    </el-form>
</template>
<script>
    export default {
        name: 'ProjectFormCommune',

        props: {
            project: {},
        },

        data() {
            return {
                form: {},
            }
        },

        methods: {
            proceed() {
                this.$emit('proceed')
            }
        },

        created() {
            this.form = this.project
        },
    }
</script>
