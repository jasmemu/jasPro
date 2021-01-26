<template>
  <div id="app">
    <router-view :key="$route.fullPath"></router-view>
  </div>
</template>

<script>
 export default {
   data(){
    return{
      butShow : true
    }
   },
   methods:{
   },
   mounted() {
   }
 }
</script>
<style lang="scss" scoped>

</style>
