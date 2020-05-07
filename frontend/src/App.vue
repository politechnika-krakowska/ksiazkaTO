<template>
    <div id="app">
        <div class="overlay"  v-if="loading">
            <b-spinner variant="success" label="Spinning" ></b-spinner>
        </div>
        <div class="container" v-else>
        <page-header></page-header>
        <search :search.sync="search"></search>
        <new-recipe :items="items" :categories="categories"></new-recipe>
        <content-table :items="itemsToDisplay"></content-table>
        </div>
    </div>

</template>

<script>
    import pageHeader from './components/pageHeader.vue';
    import searchBox from './components/searchBox.vue';
    import bookTable from './components/bookTable';
    import addNewRecipe from './components/addNewRecipe.vue';
    import RecipesService from "./services/RecipesService.js";
    import CategoryService from "./services/CategoryService";
    /* eslint-disable no-console */


    export default {
        name: 'App',
        components: {
            'page-header': pageHeader,
            'content-table': bookTable,
            'search': searchBox,
            'new-recipe': addNewRecipe
        },
        data() {
            return {
                search: '',
                items: [],
                categories: [],
                loading: true,
            }
        },
        methods: {
        },
        computed: {
            itemsToDisplay() {
                return this.items.filter( item => item.category.name.toLowerCase().includes(this.search.toLocaleLowerCase()) || item.name.toLocaleLowerCase().includes(this.search.toLocaleLowerCase()) );
            }
        },
       /* created() {
            Promise.all([RecipesService.getAllRecipes(), CategoryService.getAllCategories()])
                .then(res => {
                    this.items = res[0].data;
                    console.log(this.items)
                    this.categories = res[1].data;
                    this.loading = false;
                })
                .catch(err => {
                    console.warn(err);
                });
        },*/
        mounted() {
            Promise.all([RecipesService.getAllRecipes(), CategoryService.getAllCategories()])
                .then(res => {
                    this.items = res[0].data;
                    console.log(this.items)
                    this.categories = res[1].data;
                    this.loading = false;
                })
                .catch(err => {
                    console.warn(err);
                });
        }
    }
</script>
<style>
    html, body {
        display: flex;
        height: 100%;
        justify-content: center;

    }
    #app {
        display: flex;
        flex-direction: column;
        font-family: Avenir, Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        color: #2c3e50;
        width: 800px;
        padding-top: 35px;
        margin: 0 auto;
    }
    .overlay{
        height: 100vh;
        width: 100vw;
        position: fixed;
        z-index: 1000;
        display: flex;
        justify-content: center;
        align-items: center;
        background-color: rgba(0,0,0,.25);
        left:0;
        top:0;
    }
</style>
