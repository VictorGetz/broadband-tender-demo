<template>
  <div class="home">

    <el-button type="primary" @click="$router.push({name: 'project.new'})">Neues Ausbauprojekt</el-button>
    <el-table
            :data="tableData"
            style="width: 100%">

      <el-table-column
              prop="municipality"
              label="Kommune"
              width="210">
      </el-table-column>
      <el-table-column
              fixed
              prop="date"
              label="Datum"
              width="150">
      </el-table-column>
      <el-table-column
              prop="developmentArea"
              label="Erschließungsgebiet"
              width="200">
      </el-table-column>
      <el-table-column
              prop="provider"
              label="Ausbauender Netzbetreiber"
              width="210">
      </el-table-column>

      <el-table-column
              fixed="right"
              label="Aktionen"
              width="120">sdfg
        <el-button @click="handleClick" type="text" size="small">Detail</el-button>
        <el-button type="text" size="small">Edit</el-button>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
    export default {
        methods: {
            handleClick() {
                console.log('click');
            }
        },

        data() {
            return {
                tableData: [{
                    date: '2019-01-01',
                    developmentArea: 'Gewerbegebiet Pessenbach',
                    provider: 'Telekom Deutschland GmbH',
                    municipality: 'Kochel a. See',
                }]
            }
        },

        created() {
            this.api.project.getProjectsList().then(response => console.log(response))
        }
    }
</script>
