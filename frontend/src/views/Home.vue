<template>
  <div class="home">

    <el-button type="primary" @click="$router.push({name: 'project.new'})">Neues Ausbauprojekt</el-button>
    <el-table
            v-loading="loading"
            :data="projects"
            empty-text="Keine Projekte vorhanden">
      <el-table-column
              prop="localAuthorityDistrict.name"
              label="Kommune"
              width="250">
      </el-table-column>

      <el-table-column
              prop="projectMasterData.developmentAreaName"
              label="Erschließungsgebiet"
              width="250">
      </el-table-column>
      <el-table-column
              prop="projectMasterData.networkOperator"
              label="Ausbauender Netzbetreiber"
              width="250">
      </el-table-column>
      <el-table-column
              prop="pricingMasterData.sumValue"
              label="Preis"
              width="80">
      </el-table-column>
      <el-table-column
              label="Aktionen"
      width="120">
        <template slot-scope="scope">
            <el-button-group>
                <el-button type="primary" size="mini" icon="el-icon-edit" @click="handleEdit(scope.$index, scope.row)"></el-button>
                <el-button type="primary" size="mini" icon="el-icon-delete" @click="handleDelete(scope.$index, scope.row)"></el-button>
            </el-button-group>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
  import {mapGetters, mapActions} from 'vuex'

    export default {
        methods: {
            ...mapActions([
                'getProjectList'
            ]),

            handleEdit(index, row) {
                this.$router.push({
                    name: 'project.edit',
                    params: {
                        id: row.id
                    }
                })
            },

            load() {
                this.loading = true
                this.getProjectList()
                    .then(() => {
                        this.loading = false
                    })
            },

            handleDelete(index, row) {
                this.loading = true
                this.api.project.deleteProject(row.id)
                    .then(() => {
                        this.load()
                    })

            },
        },

        data() {
            return {
                loading: true,
                tableData: [{
                    date: '2019-01-01',
                    developmentArea: 'Gewerbegebiet Pessenbach',
                    provider: 'Telekom Deutschland GmbH',
                    municipality: 'Kochel a. See',
                }]
            }
        },

        computed: {
            ...mapGetters([
                'projects'
            ]),
        },

        created() {
            this.load()
        }
    }
</script>
