<template>
  <div class="home">

    <el-button type="primary" @click="$router.push({name: 'project.new'})">Neues Ausbauprojekt</el-button>
    <el-table
            v-loading="loading"
            :data="projects">
      <el-table-column
              prop="localAuthorityDistrict.name"
              label="Kommune"
              width="210">
      </el-table-column>

      <el-table-column
              prop="projectMasterData.developmentAreaName"
              label="Erschließungsgebiet"
              width="200">
      </el-table-column>
      <el-table-column
              prop="projectMasterData.networkOperator"
              label="Ausbauender Netzbetreiber"
              width="210">
      </el-table-column>
      <el-table-column
              label="Aktionen">
        <template slot-scope="scope">
          <el-button
                  size="mini"
                  @click="handleEdit(scope.$index, scope.row)">Bearbeiten</el-button>
          <el-button
                  size="mini"
                  type="danger"
                  @click="handleDelete(scope.$index, scope.row)">Löschen</el-button>
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
                console.log(index, row);


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
                console.log(index, row);

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
