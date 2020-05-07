"use strict";
import axios from 'axios';
const URI = "http://localhost:8066/rest/";

export default {
    getAllRecipes() {
        return axios.get(URI + "recipe/");
    },
    /*createRecipe(recipe) {
        return axios.post(URI + "post/", recipe);
    }*/
}