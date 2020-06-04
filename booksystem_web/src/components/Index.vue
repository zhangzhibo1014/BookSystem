<template>
  <el-container>
    <el-header>
      <el-row :gutter="20" >
        <el-col :offset="11" :span="1">
          <el-menu
        :default-active="activeIndex"
        class="el-menu-demo"
        mode="horizontal"
        @select="handleSelect"
        router
      >
        <el-menu-item index="/book">图书管理</el-menu-item>
      </el-menu>
        </el-col>
      </el-row>
    </el-header>
    <el-main>
      <router-view></router-view>
    </el-main>
  </el-container>
</template>

<script>
export default {
  data() {
    return {
      activeIndex: "/book"
    };
  },
  methods: {
    handleSelect(key, keyPath) {
      // this.activeIndex = keyPath
      // console.log(key, keyPath)
    }
  },
  computed: {
    
  }
};
</script>

<style>

</style>
