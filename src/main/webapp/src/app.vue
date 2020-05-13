<template>
    <div>
        <v-title title="Vue组件化"></v-title>
        <v-button @click="handleClick">点击按钮</v-button>
        <p>
            <img src="./assets/images/testimage.jpg" style="width: 200px;" alt="">
        </p>
    </div>
</template>

<script>
    import vTitle from './components/title.vue';
    import vButton from './components/button.vue';

    export default {
        components: {
            vTitle: vTitle,
            vButton: vButton
        },
        methods: {
            handleClick(e) {
                console.log(e);
            }
        }
    }
</script>

<style scoped>
    div {
        color: #ff5500;
        font-size: 24px;
    }
</style>
