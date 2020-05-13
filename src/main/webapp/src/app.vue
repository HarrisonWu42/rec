<template>
    <div>Hello{{name}}</div>
</template>

<script>
    export default {
        data() {
            return {
                name: 'Vue.js'
            }
        }
    }
</script>

<style scoped>
    div{
        color:#ff5500;
        font-size: 24px;
    }
</style>
