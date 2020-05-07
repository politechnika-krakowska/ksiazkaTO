"use strict";
import axios from 'axios';
const URI = "http://localhost:8066/rest/";

export default {
    getAllCategories() {
        return axios.get(URI + "category/");
    },
}