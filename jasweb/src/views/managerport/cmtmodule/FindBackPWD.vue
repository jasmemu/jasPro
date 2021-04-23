<template>
   <div style="background-color: #E9EEF3">
       <div style="margin-left: 10%;margin-top: 10px;">
           <h2>找回密码</h2>
       </div>
       <router-view :key="$route.fullPath"></router-view>
   </div>
</template>

<script>
    export default {
        name: "",
        data(){
            return{

            }
        },
        // methods:{
        //     go(){
        //         this.$router.push('/FindBackPWD/FillEmail')
        //     }
        // }
        // create的时候使用this.$router.push('/FindBackPWD/FillEmail')会导致后面的this.$router.push无效
        // created(){
        //     this.$router.push('/FindBackPWD/FillEmail')
        // }
    }
</script>

<style scoped>

</style>