<template>
    <div>
        <b-button variant="success"
                  class="addRecipe"
                  @click="modalShow = !modalShow"
        >
            Dodaj nowy przepis
        </b-button>
        <b-button
                variant="success"
                class="addCategory"
                @click="modalCategoryShow = !modalCategoryShow"
        >
            Dodaj nową kategorię
        </b-button>
        <b-modal id="modal-category"
                 size="lg"
                 v-model="modalCategoryShow"
        >
            <template v-slot:modal-header class="formHeader">
                <h5>Dodawanie noweej kategorii</h5>
            </template>
            <b-row class="my-1" type="text">
                <b-col sm="3">
                    <label>Nazwa nowej kategorii:</label>
                </b-col>
                <b-col sm="9">
                    <b-form-input type="text"
                                  id="name"
                                  v-model="newCategory.name"
                                  pattern=".{5,15}"
                                  required
                                  title="5 characters minimum">
                    </b-form-input>
                </b-col>
            </b-row>
            <template v-slot:modal-footer="{cancel}">
                <b-button size="sm" variant="danger" @click="cancel()">
                    Anuluj
                </b-button>
                <b-button size="sm" variant="success" type="submit" @click="addCategory">
                    OK
                </b-button>
            </template>
        </b-modal>
        <b-modal id="modal-lg" size="lg" v-model="modalShow">
            <template v-slot:modal-header class="formHeader">
                <h5>Dodawanie nowego przepisu</h5>
            </template>
            <template v-slot:default>
                <b-container name="recipeData">
                    <b-row class="my-1" type="text">
                        <b-col sm="3">
                            <label>Nazwa: </label>
                        </b-col>
                        <b-col sm="9">
                            <b-form-input type="text"
                                          id="name"
                                          v-model="newRecipe.name"
                                          pattern=".{5,15}"
                                          required
                                          title="5 characters minimum">
                            </b-form-input>
                        </b-col>
                    </b-row>
                    <b-row class="my-1" type="text">
                        <b-col sm="3">
                            <label>Kategoria: </label>
                        </b-col>
                        <b-col sm="9">
                            <div class="input-group mb-3">
                                <select class="custom-select"
                                        id="category"
                                        v-model="newRecipe.category"
                                        :categories="categories"
                                >
                                    <option v-for="category in categories"
                                            :key="category.id"
                                            :value="category"
                                    >
                                        {{category.name}}
                                    </option>
                                </select>
                            </div>
                        </b-col>
                    </b-row>
                    <b-row class="my-1" type="text">
                        <b-col sm="3">
                            <label>Pora dnia: </label>
                        </b-col>
                        <b-col sm="9">
                            <div class="input-group mb-3">
                                <select class="custom-select" id="time"
                                        v-model="newRecipe.timeOfDay">
                                    <option v-for="time in times" :key="time.name" :value="time.value">{{time.name}}
                                    </option>
                                </select>
                            </div>
                        </b-col>
                    </b-row>
                    <b-row class="my-1" type="text">
                        <b-col sm="3">
                            <label>Trudność: </label>
                        </b-col>
                        <b-col sm="9">
                            <div class="input-group mb-3">
                                <select class="custom-select"
                                        v-model="newRecipe.difficulty">
                                    <option v-for="difs in difficulties"
                                            :key="difs.value"
                                            :value="difs.value"
                                    >
                                        {{difs.name}}
                                    </option>
                                </select>
                            </div>
                        </b-col>
                    </b-row>
                    <b-row class="my-1" type="number">
                        <b-col sm="3">
                            <label>Czas przygotowania w minutach: </label>
                        </b-col>
                        <b-col sm="9">
                            <b-form-input type="number" id="timeToPrepare"
                                          v-model="newRecipe.preparationTime">
                            </b-form-input>
                        </b-col>
                    </b-row>
                    <b-row class="my-1" type="text">
                        <b-col sm="3">
                            <label>Składniki: </label>
                        </b-col>
                        <b-col sm="9">
                            <b-form-textarea
                                    id="ingredients"
                                    v-model="newRecipe.description"
                                    placeholder="Wpisz wymagane składniki"
                                    rows="3"
                                    max-rows="6"
                                    pattern=".{5,15}"
                            ></b-form-textarea>
                        </b-col>
                    </b-row>
                    <b-row class="my-1" type="text">
                        <b-col sm="3">
                            <label>Sposób przygotowania: </label>
                        </b-col>
                        <b-col sm="9">
                            <select class="custom-select" v-model="newRecipe.wayOfPreparation">
                                <option v-for="metoda in making" :value="metoda.value" :key="metoda.value">{{metoda.name}}
                                </option>
                            </select>
                            <!-- <b-form-textarea
                                     id="procedure"
                                     v-model="newRecipe.wayOfPreparation"
                                     placeholder="Opisz przygotowanie dania"
                                     rows="3"
                                     max-rows="6"
                                     pattern=".{5,15}"
                             ></b-form-textarea>-->
                        </b-col>
                    </b-row>
                </b-container>
            </template>

            <template v-slot:modal-footer="{cancel}">
                <b-button size="sm" variant="danger" @click="cancel()">
                    Anuluj
                </b-button>
                <b-button size="sm" variant="success" type="submit" @click="addRecipe">
                    OK
                </b-button>
            </template>
        </b-modal>
    </div>
</template>

<script>
    import axios from 'axios';

    const newRecipeModelTemplate = {
        name: '',
        category: {},
        timeOfDay: '',
        difficulty: '',
        preparationTime: '',
        description: '',
        wayOfPreparation: ''
    };
    const newCategoryModelTemplate = {
        name: '',
    };
    export default {
        props: ['categories', 'items'],
        data() {
            return {
                modalShow: false,
                modalCategoryShow: false,
                newRecipe: {...newRecipeModelTemplate},
                newCategory: {...newCategoryModelTemplate},
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
                ],
                difficulties: [
                    {
                        value: 'EASY',
                        name: 'Łatwa'
                    },
                    {
                        value: 'FAIR',
                        name: 'Średnia',
                    },
                    {
                        value: 'MEDIUM',
                        name: 'Zaawansowana',
                    },
                    {
                        value: 'HARD',
                        name: 'Trudna',
                    },
                    {
                        value: 'EXTREME',
                        name: 'Bardzo drudna',
                    },
                ],
                times: [{
                    value: 'BREAKFAST',
                    name: 'Śniadanie'
                    },
                    {
                        value: 'DINNER',
                        name: 'Obiad',
                    },
                    {
                        value: 'SUPPER',
                        name: 'Kolacja',
                    },
                ]
            }
        },
        methods: {
            addRecipe() {
                //console.log(this.newRecipe);
                axios.post('http://localhost:8066/rest/recipe', this.newRecipe)
                    .then(res => {
                        console.log(res);
                       // items.push(this.newRecipe);
                    })
                    .catch(err => console.warn(err));
                this.newRecipe = {...newRecipeModelTemplate};
                this.makeToastRecipe();
                this.modalShow = false;
                console.log(this.categories[0].name);
            },
            addCategory() {
                //console.log(this.newRecipe);
                axios.post('http://localhost:8066/rest/category', this.newCategory)
                    .then(res => {
                        console.log(res);
                        //this.categories.push(this.newCategory);
                    })
                    .catch(err => console.warn(err));
                this.makeToastCategory();
                this.modalCategoryShow = false;
            },
            makeToastRecipe() {
                this.$bvToast.toast('Utworzono nowy przepis!', {
                    title: `Powiadomienie`,
                    variant: 'success',
                    solid: true,
                });
            },
            makeToastCategory() {
                this.$bvToast.toast('Utworzono nową kategorię!', {
                    title: `Powiadomienie`,
                    variant: 'success',
                    solid: true,
                });
            },
        }
    }
</script>
<style>
    .modal-backdrop:not(.show):not(.fade) {
        opacity: .5;
    }

    .formHeader {
        text-align: center;
    }

    .modal-header {
        background-color: #28A745;
    }

    .toast {
        opacity: 1 !important;
    }

    .addCategory {
        margin-left: 10px;
    }
</style>