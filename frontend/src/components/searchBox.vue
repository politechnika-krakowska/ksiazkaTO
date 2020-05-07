<template>
    <div class="search-wrapper">
        <nav class="navbar navbar-light bg-light">
            <input class="form-control"
                   type="text"
                   placeholder="Wyszukaj przepis..."
                   aria-label="Search"
                   v-model="search"
                   @input="$emit('update:search', search)"
            >
        </nav>
        <hr>
    </div>
</template>

<script>
    export default {
        props: ['search'],
        data: function () {
            return {
                visible: 1
            }
        }
    }
</script>

<style scoped>
    .search-wrapper {
    }
</style>