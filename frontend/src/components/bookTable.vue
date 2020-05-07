<template>
    <div>
        <b-card
                border-variant="success"
                header-bg-variant="success"
                header-text-variant="white"
                style="margin-top: 10px"
                :items="items"
                v-for="item in items" :key="item.id"
        >
            <template v-slot:header>
                <h4 class="mb-0" style="text-align:center;">{{item.name}}</h4>
            </template>
            <b-card-body>
                <b-card-sub-title class="mb-2">Kategoria: {{item.category.name}}</b-card-sub-title>
                <b-card-sub-title class="mb-2">Pora dnia: {{item.timeOfDay}}</b-card-sub-title>
                <b-card-sub-title class="mb-2">Poziom trudności: {{item.difficulty}}</b-card-sub-title>
                <b-card-sub-title class="mb-2">Czas przygotowania: {{item.preparationTime}}</b-card-sub-title>
                <b-card-sub-title class="mb-2">Sposób przygotowania: {{item.wayOfPreparation}}</b-card-sub-title>
                <b-card-text><b>Składniki: {{item.description}}</b></b-card-text>
                <hr>
            </b-card-body>
            <div class="delete-item">
            <b-button class="delete-recipe" size="sm" variant="danger" @click="makeToast">Usuń przepis</b-button>
            </div>
        </b-card>
    </div>
</template>

<script>

    export default {
        props: ['items'],
        name: "bookTable",
        data: function () {
            return {
                making: [
                    {
                        value: 'NONE',
                        name: 'Bez przetworzenia'
                    },
                    {
                        value: 'COOKING',
                        name: 'Gotowanie',
                    },
                    {
                        value: 'FRYING',
                        name: 'Smażenie',
                    },
                    {
                        value: 'BAKING',
                        name: 'Pieczenie',
                    },
                ]
            }
        },
        methods:{
            deleteRecipe() {

            },
            makeToast() {
                this.$bvToast.toast('Przepis został usunięty', {
                    title: `Powiadomienie`,
                    variant: 'danger',
                    solid: true,
                });
            },
        }

    }
</script>

<style scoped>
    .delete-item{
        display:flex;
    }
    .delete-recipe{
        margin-left: auto;
    }
</style>