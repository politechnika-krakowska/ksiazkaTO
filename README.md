**Database**

* Test Docker:
```
docker run --name cookbook-postgres -e POSTGRES_PASSWORD=cookbook -e POSTGRES_USER=cookbook -e POSTGRES_DB=cookbook -p 5432:5432 -d postgres
```


**Endpoints**

Swagger: http://localhost:8066/swagger-ui.html

Base: http://localhost:8066/

* `POST /rest/category` - create new category

```
curl --request POST \
     --url http://localhost:8066/rest/category \
     --header 'content-type: application/json' \
     --data '{ "name": "test" }'
```
     
* `GET /rest/category` - list all categories

* `GET /rest/category/{id}/recipes` - get all recipes in category_id = {id}

* `GET /rest/recipe` - get all recipes

* `POST /rest/recipe` - create new recipe

```
curl --request POST \
     --url http://localhost:8066/rest/recipe \
     --header 'content-type: application/json' \
     --cookie PHPSESSID=49uk1icqs2r7t0mckqctjetvv0 \
     --data '{ 
   	"name": "test",
   	"description": "bla bla ba",
   	"preparationTime": "1 godzina",
   	"difficulty": "EASY",
   	"timeOfDay": "DINNER",
   	"wayOfPreparation": "COOKING"
   }'
```


 


