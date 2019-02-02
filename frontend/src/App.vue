<template>
  <div id="app">
    <el-container>
      <el-header></el-header>
      <el-container>
        <!--<el-aside width="200px">Aside</el-aside>-->
        <el-main>
          <router-view/>
        </el-main>
      </el-container>
    </el-container>


  </div>
</template>

<style lang="scss">
  .el-header {background-color: rgb(226, 0, 116);}
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}
#nav {
  padding: 30px;
  a {
    font-weight: bold;
    color: #2c3e50;
    &.router-link-exact-active {
      color: #42b983;
    }
  }
}
</style>
