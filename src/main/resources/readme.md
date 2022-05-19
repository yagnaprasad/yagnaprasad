Introduction to AllRecipes:
It gives web based functionality to maintain the Recipe details.

Functionality:
Service provides the functionality to save, update, select and delete the recipes from records.

Implementation:
Functionality implemented using Rest API service which covers all CRUD operations.
Service is developed using layered architecture i.e. Controller, services

Framework used:
Spring Boot framework: it provides feature to develop standalone production ready applications
with embedded tomcat server and H2 DB.
Spring security: provides basic authentications.
Spring Data JPA: to persist recipes entities in db.

Tools:
IDE: Intellij
DB: H2
Server: tomcat
Repository: GitHub

Credentials:
GitHub Repository: https://github.com/mastanjava88/ABN_Sample_Assignment.git

Rest API:
Post : http://localhost:8082/recipe/create

Request:

{  
"name": "Mutton",
"price": 250.22,
"type": "NonVeg",
"ingridientEntities":[  
{  
"name":"mutton",
"quantity":"200g"
},
{  
"name":"chicken",
"quantity":"500g"
}
]
}

{  
"name": "South Indian Veg Meals",
"price": 100.00,
"type": "Veg",
"ingridientEntities":[  
{  
"name":"Dal",
"quantity":"200g"
},
{  
"name":"Fry",
"quantity":"100g"
},
{  
"name":"Rice",
"quantity":"500g"
}
]
}

{  
"name": "South Indian Non-Veg Meals",
"price": 300.00,
"type": "Non-Veg",
"ingridientEntities":[  
{  
"name":"chicken",
"quantity":"200g"
},
{  
"name":"Fry",
"quantity":"600g"
},
{  
"name":"Rice",
"quantity":"300g"
}
]
}

Response:

{
"id": 2,
"name": "Mutton",
"price": 250.22,
"type": "NonVeg",
"ingridientEntities": [
{
"id": 3,
"name": "chicken",
"quantity": "500g",
"recipe_id": 2
},
{
"id": 4,
"name": "mutton",
"quantity": "200g",
"recipe_id": 2
}
]
}

PUT URL:

Request :
{
"id": 2,
"name": "Mutton",
"price": 250.22,
"type": "NonVeg",
"ingridientEntities": [
{
"id": 3,
"name": "Mutton",
"quantity": "500g",
"recipe_id": 2
},
{
"id": 4,
"name": "Oil",
"quantity": "200,
"recipe_id": 2
}
]
}


{
"id": 2,
"name": "Mutton",
"price": 250.22,
"type": "NonVeg",
"ingridientEntities": [
{
"id": 3,
"name": "Mutton",
"quantity": "500g",
"recipe_id": 2
},
{
"id": 4,
"name": "Oil",
"quantity": "200,
"recipe_id": 2
}
]
}



http://localhost:8082/recipe/getID/10

{
"id": 10,
"name": "Mutton",
"price": 250.22,
"type": "NonVeg",
"ingridientEntities": [
{
"id": 19,
"name": "chicken",
"quantity": "500g",
"recipe_id": 10
},
{
"id": 20,
"name": "mutton",
"quantity": "200g",
"recipe_id": 10
}
]
}

http://localhost:8082/recipe/getAll
[
{
"id": 2,
"name": "Mutton",
"price": 250.22,
"type": "NonVeg",
"ingridientEntities": [
{
"id": 3,
"name": "chicken",
"quantity": "500g",
"recipe_id": 2
},
{
"id": 4,
"name": "mutton",
"quantity": "200g",
"recipe_id": 2
}
]
},
]
http://localhost:8082/recipe/delete/10
Record deleted
