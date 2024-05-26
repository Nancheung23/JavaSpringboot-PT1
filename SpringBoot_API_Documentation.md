# SpringBoot
[[https://documenter.getpostman.com/view/31561523/2sA3QqhDJ7#0e0f4252-7711-4136-8680-2d7edf6c12ea](url)](https://documenter.getpostman.com/view/31561523/2sA3QqhDJ7) 
All routers for Food recipe Apis, except Http.Menthod.GET all routers need authentication. which generate by \`/auth/authenticate\`

## User Controller

Management of User (CRUD), avatar upload

### GetUsers

**Method:** GET

**URL:** {{users}}/

**Description:** Get all users

**Response:**

**Status:** OK (200)

**Body:**

```json
[
    {
        "id": 1,
        "name": "Yannan_Zhang",
        "password": "63c03a75e3d350dfe558c655adbd67f4bdb9971989c6cb2b7237fee97f05cce8",
        "nickName": "nan",
        "avatarUrl": "http://localhost:8080/uploads/test.png",
        "email": "yannan.zhang@tuni.fi",
        "birthDate": "20.04.1997",
        "salt": "f6c82cd4af0877bd8dafee763743dc86",
        "recipes": [
            {
                "id": 4,
                "title": "Test Recipe4",
                "content": "Test Recipe4",
                "rating": 0,
                "views": 0,
                "picture": null,
                "comments": []
            },
            {
                "id": 1,
                "title": "Test Recipe1",
                "content": "Test Recipe1",
                "rating": 0,
                "views": 0,
                "picture": null,
                "comments": [
                    {
                        "id": 4,
                        "content": "Test Comment4",
                        "ldt": "2024-09-07T18:02:47",
                        "picture": null
                    },
                    {
                        "id": 2,
                        "content": "Test Comment2",
                        "ldt": "2023-06-20T07:34:55",
                        "picture": "C:\\Users\\zhang\\IdeaProjects\\JavaSpringboot-PT1\\uploads\\favicon.png"
                    },
                    {
                        "id": 1,
                        "content": "Test Comment1",
                        "ldt": "2024-05-15T14:45:11",
                        "picture": null
                    },
                    {
                        "id": 6,
                        "content": "Test Comment6",
                        "ldt": "2022-11-03T13:51:29",
                        "picture": null
                    }
                ]
            }
        ],
        "comments": [
            {
                "id": 1,
                "content": "Test Comment1",
                "ldt": "2024-05-15T14:45:11",
                "picture": null
            },
            {
                "id": 5,
                "content": "Test Comment5",
                "ldt": "2023-04-21T17:15:20",
                "picture": null
            }
        ]
    },
    {
        "id": 2,
        "name": "TestUser",
        "password": "0f30c32f557dd0c18426126375d9d3d6da6c7664a5b0fde2bc52e2d1720e45f9",
        "nickName": "test1",
        "avatarUrl": "https://chatgpt.com",
        "email": "testUser@test.fi",
        "birthDate": "16.09.1950",
        "salt": "b4584a625e52b018b653784bff079e79",
        "recipes": [
            {
                "id": 2,
                "title": "Test Recipe2",
                "content": "Test Recipe2",
                "rating": 0,
                "views": 0,
                "picture": null,
                "comments": [
                    {
                        "id": 5,
                        "content": "Test Comment5",
                        "ldt": "2023-04-21T17:15:20",
                        "picture": null
                    }
                ]
            }
        ],
        "comments": [
            {
                "id": 3,
                "content": "Test Comment3",
                "ldt": "2022-08-11T22:16:03",
                "picture": null
            },
            {
                "id": 4,
                "content": "Test Comment4",
                "ldt": "2024-09-07T18:02:47",
                "picture": null
            },
            {
                "id": 2,
                "content": "Test Comment2",
                "ldt": "2023-06-20T07:34:55",
                "picture": "C:\\Users\\zhang\\IdeaProjects\\JavaSpringboot-PT1\\uploads\\favicon.png"
            }
        ]
    },
    {
        "id": 3,
        "name": "TestUser2",
        "password": "a3c339fb2863a6688e183b84cdeea2adb320d80bb2a5b9a86341d0d3cc208855",
        "nickName": "test2",
        "avatarUrl": "http://www.youtube.com",
        "email": "test@test.fi",
        "birthDate": "23.04.2000",
        "salt": "62ca4b8c7ecdfc4d4049ebb5b5dffc8b",
        "recipes": [
            {
                "id": 3,
                "title": "Test Recipe3",
                "content": "Test Recipe3",
                "rating": 0,
                "views": 0,
                "picture": null,
                "comments": [
                    {
                        "id": 3,
                        "content": "Test Comment3",
                        "ldt": "2022-08-11T22:16:03",
                        "picture": null
                    }
                ]
            }
        ],
        "comments": [
            {
                "id": 6,
                "content": "Test Comment6",
                "ldt": "2022-11-03T13:51:29",
                "picture": null
            }
        ]
    },
    {
        "id": 4,
        "name": "TestUser3",
        "password": "f3a40c7dbd3f9cf6c044f5946f6360fd491e2d8f6161cf0ad8769469277f5eec",
        "nickName": "test3",
        "avatarUrl": "https://www.tuni.fi/en",
        "email": "test_testing@test.fi",
        "birthDate": "01.01.1980",
        "salt": "b4be836e08f07b674d43d3fcbcf9743d",
        "recipes": [],
        "comments": []
    }
]
```

### GetUserById

**Method:** GET

**URL:** {{users}}/1

**Description:** Get user by id

**Response:**

**Status:** OK (200)

**Body:**

```json
{
    "id": 1,
    "name": "Yannan_Zhang",
    "password": "63c03a75e3d350dfe558c655adbd67f4bdb9971989c6cb2b7237fee97f05cce8",
    "nickName": "nan",
    "avatarUrl": "http://localhost:8080/uploads/test.png",
    "email": "yannan.zhang@tuni.fi",
    "birthDate": "20.04.1997",
    "salt": "f6c82cd4af0877bd8dafee763743dc86",
    "recipes": [
        {
            "id": 4,
            "title": "Test Recipe4",
            "content": "Test Recipe4",
            "rating": 0,
            "views": 0,
            "picture": null,
            "comments": []
        },
        {
            "id": 1,
            "title": "Test Recipe1",
            "content": "Test Recipe1",
            "rating": 0,
            "views": 0,
            "picture": null,
            "comments": [
                {
                    "id": 4,
                    "content": "Test Comment4",
                    "ldt": "2024-09-07T18:02:47",
                    "picture": null
                },
                {
                    "id": 2,
                    "content": "Test Comment2",
                    "ldt": "2023-06-20T07:34:55",
                    "picture": "C:\\Users\\zhang\\IdeaProjects\\JavaSpringboot-PT1\\uploads\\favicon.png"
                },
                {
                    "id": 1,
                    "content": "Test Comment1",
                    "ldt": "2024-05-15T14:45:11",
                    "picture": null
                },
                {
                    "id": 6,
                    "content": "Test Comment6",
                    "ldt": "2022-11-03T13:51:29",
                    "picture": null
                }
            ]
        }
    ],
    "comments": [
        {
            "id": 1,
            "content": "Test Comment1",
            "ldt": "2024-05-15T14:45:11",
            "picture": null
        },
        {
            "id": 5,
            "content": "Test Comment5",
            "ldt": "2023-04-21T17:15:20",
            "picture": null
        }
    ]
}
```

### GetRecipesFromUsers

**Method:** GET

**URL:** {{users}}/1/recipes

**Description:** Get all recipes from user

**Response:**

**Status:** OK (200)

**Body:**

```json
[
    {
        "id": 4,
        "title": "Test Recipe4",
        "content": "Test Recipe4",
        "rating": 0,
        "views": 0,
        "picture": null,
        "comments": []
    },
    {
        "id": 1,
        "title": "Test Recipe1",
        "content": "Test Recipe1",
        "rating": 0,
        "views": 0,
        "picture": null,
        "comments": [
            {
                "id": 4,
                "content": "Test Comment4",
                "ldt": "2024-09-07T18:02:47",
                "picture": null
            },
            {
                "id": 2,
                "content": "Test Comment2",
                "ldt": "2023-06-20T07:34:55",
                "picture": "C:\\Users\\zhang\\IdeaProjects\\JavaSpringboot-PT1\\uploads\\favicon.png"
            },
            {
                "id": 1,
                "content": "Test Comment1",
                "ldt": "2024-05-15T14:45:11",
                "picture": null
            },
            {
                "id": 6,
                "content": "Test Comment6",
                "ldt": "2022-11-03T13:51:29",
                "picture": null
            }
        ]
    }
]
```

### GetCommentsFromUsers

**Method:** GET

**URL:** {{users}}/1/comments

**Description:** Get all comments from user

**Response:**

**Status:** OK (200)

**Body:**

```json
[
    {
        "id": 1,
        "content": "Test Comment1",
        "ldt": "2024-05-15T14:45:11",
        "picture": null
    },
    {
        "id": 5,
        "content": "Test Comment5",
        "ldt": "2023-04-21T17:15:20",
        "picture": null
    }
]
```

### PostUser

**Method:** POST

**URL:** {{users}}/

**Description:** Create one user

**Body:**

```json
{
    "name": "PostmanTest",
    "password": "postman-test",
    "nickName": "postmantest",
    "avatarUrl": "https://www.postman.com",
    "email": "postman-test@postman.com",
    "age": 20,
    "birthDate" : "20.04.1997"
}
```

**Response:**

**Status:** OK (200)

**Body:**

```json
{
    "name": "PostmanTest",
    "nickName": "postmantest",
    "password": "e3c3bc05b54053c3fadc4ee6e822ab4d12055723aa8d2b4d619b96f877eb2a24",
    "avatarUrl": "https://www.postman.com",
    "email": "postman-test@postman.com",
    "salt": null,
    "birthDate": "20.04.1997"
}
```

### CreateCommentFromUserForRecipe

**Method:** POST

**URL:** {{users}}/2/comments/1/post

**Description:** Create comment from user for a recipe

**Body:**

```json
{
    "content" : "Post man test",
    "date" : "2024-05-26 10:24:00",
    "userId" : 1,
    "recipeId": 1
}
```

**Response:**

**Status:** OK (200)

**Body:**

```json
{
    "content": "Post man test",
    "date": "2024-05-26T10:24",
    "picture": null,
    "userId": 1,
    "recipeId": 2
}
```

### createRatingFromUserForRecipe

**Method:** POST

**URL:** {{users}}/2/comments/1/rating

**Description:** Create rating from user for recipe

**Body:**

```json
5
```

**Response:**

**Status:** OK (200)

**Body:**

```json
{
    "title": "Test Recipe1",
    "content": "Test Recipe1",
    "rating": 5,
    "views": 0,
    "picture": null,
    "userId": 1
}
```

### PutUser

**Method:** PUT

**URL:** {{users}}/2

**Description:** Modify user info

**Body:**

```json
{
  "name": "JohnDoe",
  "password": "examplePassword",
  "nickName": "Johnny",
  "avatarUrl": "https://example.com/avatar.jpg",
  "email": "john.doe@example.com",
  "age": 30,
  "birthDate" : "20.04.1997"
}

```

**Response:**

**Status:** OK (200)

**Body:**

```json
{
    "name": "JohnDoe",
    "nickName": "Johnny",
    "password": "fbe9a45a89a6c9920968c3a7e7c21d4939bc45ea19d0116758a50bf86053a89e",
    "avatarUrl": "https://example.com/avatar.jpg",
    "email": "john.doe@example.com",
    "salt": null,
    "birthDate": "20.04.1997"
}
```

### UploadAvatar

**Method:** PATCH

**URL:** {{users}}/2/avatar

**Description:** Upload avatar

**Response:**

**Status:** OK (200)

**Body:**

```json
{
    "name": "TestUser",
    "nickName": "test1",
    "password": "3ee9e37b9c5bc425e5ab3f1bbeb5def167bba7824914399c2fd78145e21aebd3",
    "avatarUrl": "C:\\Users\\zhang\\IdeaProjects\\JavaSpringboot-PT1\\uploads\\test.png",
    "email": "testUser@test.fi",
    "salt": null,
    "birthDate": "16.09.1950"
}
```

### DeleteUser

**Method:** DELETE

**URL:** {{users}}/2

**Description:** Delete user

**Response:**

**Status:** OK (200)

**Body:**

```json
{
    "name": "TestUser",
    "nickName": "test1",
    "password": "3ee9e37b9c5bc425e5ab3f1bbeb5def167bba7824914399c2fd78145e21aebd3",
    "avatarUrl": "C:\\Users\\zhang\\IdeaProjects\\JavaSpringboot-PT1\\uploads\\test.png",
    "email": "testUser@test.fi",
    "salt": null,
    "birthDate": "16.09.1950"
}
```

## Recipe Controller

Management of Recipes

### GetRecipes

**Method:** GET

**URL:** {{recipes}}/

**Description:** Get all recipes

**Response:**

**Status:** OK (200)

**Body:**

```json
[
    {
        "id": 1,
        "title": "Test Recipe1",
        "content": "Test Recipe1",
        "rating": 0,
        "views": 0,
        "picture": null,
        "comments": [
            {
                "id": 4,
                "content": "Test Comment4",
                "ldt": "2024-09-07T18:02:47",
                "picture": null
            },
            {
                "id": 2,
                "content": "Test Comment2",
                "ldt": "2023-06-20T07:34:55",
                "picture": "C:\\Users\\zhang\\IdeaProjects\\JavaSpringboot-PT1\\uploads\\favicon.png"
            },
            {
                "id": 1,
                "content": "Test Comment1",
                "ldt": "2024-05-15T14:45:11",
                "picture": null
            },
            {
                "id": 6,
                "content": "Test Comment6",
                "ldt": "2022-11-03T13:51:29",
                "picture": null
            }
        ]
    },
    {
        "id": 2,
        "title": "Test Recipe2",
        "content": "Test Recipe2",
        "rating": 0,
        "views": 0,
        "picture": null,
        "comments": [
            {
                "id": 5,
                "content": "Test Comment5",
                "ldt": "2023-04-21T17:15:20",
                "picture": null
            }
        ]
    },
    {
        "id": 3,
        "title": "Test Recipe3",
        "content": "Test Recipe3",
        "rating": 0,
        "views": 0,
        "picture": null,
        "comments": [
            {
                "id": 3,
                "content": "Test Comment3",
                "ldt": "2022-08-11T22:16:03",
                "picture": null
            }
        ]
    },
    {
        "id": 4,
        "title": "Test Recipe4",
        "content": "Test Recipe4",
        "rating": 0,
        "views": 0,
        "picture": null,
        "comments": []
    }
]
```

### GetRecipeById

**Method:** GET

**URL:** {{recipes}}/2

**Description:** Get recipe by id

**Response:**

**Status:** OK (200)

**Body:**

```json
{
    "id": 2,
    "title": "Test Recipe2",
    "content": "Test Recipe2",
    "rating": 0,
    "views": 0,
    "picture": null,
    "comments": [
        {
            "id": 5,
            "content": "Test Comment5",
            "ldt": "2023-04-21T17:15:20",
            "picture": null
        }
    ]
}
```

### GetCommentsOfRecipe

**Method:** GET

**URL:** {{recipes}}/2/comments

**Description:** Get all comments of a recipe

**Response:**

**Status:** OK (200)

**Body:**

```json
[
    {
        "id": 5,
        "content": "Test Comment5",
        "ldt": "2023-04-21T17:15:20",
        "picture": null
    }
]
```

### PostRecipe

**Method:** POST

**URL:** {{recipes}}/

**Description:** Create a recipe

**Body:**

```json
{
   "title" : "Test Recipe5",
   "content" : "Test Recipe5",
   "rating" : 0,
   "views" : 0,
   "userId" : 2 
}
```

**Response:**

**Status:** OK (200)

**Body:**

```json
{
    "title": "Test Recipe5",
    "content": "Test Recipe5",
    "rating": 0,
    "views": 0,
    "picture": null,
    "userId": 2
}
```

### PutRecipe

**Method:** PUT

**URL:** {{recipes}}/2

**Description:** Modify a recipe

**Body:**

```json
{
    "title" : "Test Recipe2 -- postman",
   "content" : "Test Recipe2 -- postman",
   "rating" : 0,
   "views" : 0,
   "userId" : 2 
}
```

**Response:**

**Status:** OK (200)

**Body:**

```json
{
    "title": "Test Recipe2 -- postman",
    "content": "Test Recipe2 -- postman",
    "rating": 0,
    "views": 0,
    "picture": null,
    "userId": 2
}
```

### ViewRecipe

**Method:** PUT

**URL:** {{recipes}}/2/view

**Description:** Add a view increment for a recipe

**Body:**

```json
{
    "id": 2,
    "name": "TestUser",
    "password": "f2116a1d5bb8e897a0668296dc5eab74c96dfcd5cfc86e789e7f84144f5a4fc0",
    "nickName": "test1",
    "avatarUrl": "https://chatgpt.com",
    "email": "testUser@test.fi",
    "birthDate": "16.09.1950",
    "salt": "eb4487245f57f9c7eb361d349d5e6b26"
}
```

**Response:**

**Status:** OK (200)

**Body:**

```json
{
    "title": "Test Recipe2 -- postman",
    "content": "Test Recipe2 -- postman",
    "rating": 0,
    "views": 1,
    "picture": null,
    "userId": 2
}
```

### PostImage

**Method:** PATCH

**URL:** {{recipes}}/2/image

**Description:** Upload image for recipe

**Response:**

**Status:** OK (200)

**Body:**

```json
{
    "title": "Test Recipe2 -- postman",
    "content": "Test Recipe2 -- postman",
    "rating": 0,
    "views": 1,
    "picture": "C:\\Users\\zhang\\IdeaProjects\\JavaSpringboot-PT1\\uploads\\test.png",
    "userId": 2
}
```

### DeleteRecipe

**Method:** DELETE

**URL:** {{recipes}}/2

**Description:** Delete a recipe by id

**Response:**

**Status:** OK (200)

**Body:**

```json
{
    "title": "Test Recipe2 -- postman",
    "content": "Test Recipe2 -- postman",
    "rating": 0,
    "views": 1,
    "picture": "C:\\Users\\zhang\\IdeaProjects\\JavaSpringboot-PT1\\uploads\\test.png",
    "userId": 2
}
```

## Comment Controller

Management of Comments

### GetComments

**Method:** GET

**URL:** {{comment}}/

**Description:** Get all comments

**Response:**

**Status:** OK (200)

**Body:**

```json
[
    {
        "id": 1,
        "content": "Test Comment1",
        "ldt": "2024-05-15T14:45:11",
        "picture": null
    },
    {
        "id": 3,
        "content": "Test Comment3",
        "ldt": "2022-08-11T22:16:03",
        "picture": null
    },
    {
        "id": 4,
        "content": "Test Comment4",
        "ldt": "2024-09-07T18:02:47",
        "picture": null
    },
    {
        "id": 5,
        "content": "Test Comment5",
        "ldt": "2023-04-21T17:15:20",
        "picture": null
    },
    {
        "id": 6,
        "content": "Test Comment6",
        "ldt": "2022-11-03T13:51:29",
        "picture": null
    },
    {
        "id": 2,
        "content": "Test Comment2",
        "ldt": "2023-06-20T07:34:55",
        "picture": "C:\\Users\\zhang\\IdeaProjects\\JavaSpringboot-PT1\\uploads\\favicon.png"
    }
]
```

### GetCommentById

**Method:** GET

**URL:** {{comment}}/2

**Description:** Get a comment by id

**Response:**

**Status:** OK (200)

**Body:**

```json
{
    "id": 2,
    "content": "Test Comment2",
    "ldt": "2023-06-20T07:34:55",
    "picture": null
}
```

### PostComment

**Method:** POST

**URL:** {{comment}}/

**Description:** Create a comment

**Body:**

```json
{
    "content" : "Post man test",
    "date" : "2024-05-26 13:14:00",
    "userId" : 2,
    "recipeId": 2
}
```

**Response:**

**Status:** OK (200)

**Body:**

```json
{
    "content": "Post man test",
    "date": "2024-05-26T13:14",
    "picture": null,
    "userId": 2,
    "recipeId": 2
}
```

### PutComment

**Method:** PUT

**URL:** {{comment}}/2

**Description:** Modify a Comment

**Body:**

```json
{
    "content" : "Post man test",
    "date" : "2024-05-26 13:19:00",
    "userId" : 2,
    "recipeId": 1
}
```

**Response:**

**Status:** OK (200)

**Body:**

```json
{
    "content": "Post man test",
    "date": "2024-05-26T13:19",
    "picture": null,
    "userId": 1,
    "recipeId": 2
}
```

### PostImage

**Method:** PATCH

**URL:** {{comment}}/2/image

**Description:** Upload image for comment

**Response:**

**Status:** OK (200)

**Body:**

```json
{
    "content": "Post man test",
    "date": "2024-05-26T13:19",
    "picture": "C:\\Users\\zhang\\IdeaProjects\\JavaSpringboot-PT1\\uploads\\favicon.png",
    "userId": 1,
    "recipeId": 2
}
```

### DeleteComment

**Method:** DELETE

**URL:** {{comment}}/2

**Description:** Delete a Comment by id

**Response:**

**Status:** OK (200)

**Body:**

```json
{
    "content": "Post man test",
    "date": "2024-05-26T13:19",
    "picture": "C:\\Users\\zhang\\IdeaProjects\\JavaSpringboot-PT1\\uploads\\favicon.png",
    "userId": 1,
    "recipeId": 2
}
```

## Authentication

Routers for getting jwt token

### AuthRequest

**Method:** POST

**URL:** http://localhost:8080/auth/authenticate

**Description:** generate token with correct username and pwd

**Body:**

```json
{
    "username": "TestUser",
    "password": "test1_password"
}

```

**Response:**

**Status:** OK (200)

**Body:**

```json
{
    "jwt": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJUZXN0VXNlciIsImlhdCI6MTcxNjcyMTQyMCwiZXhwIjoxNzE2NzU3NDIwfQ.aPGYIDL-M1SAsOcfY5o9tP3k8PLHHpnYtIUCqt621Do"
}
```

## Default

Test pages with default html5 templates

### Greeting

**Method:** GET

**URL:** http://localhost:8080/

**Description:** Occupy root url

**Response:**

**Status:** OK (200)

**Body:**

```json
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Food Recipes Website</title>
    </head>
    <body>
        <h1>Food Recipes Website</h1>
        <p>Java SpringBoot project for practical training 1, A Food Website For Recipes, User Can Browse Recipes Or Upload.</p>
        <p>Welcome to the Food Recipes Website!</p>
    </body>
</html>
```

### Test

**Method:** GET

**URL:** http://localhost:8080/test

**Description:** Test page

**Response:**

**Status:** OK (200)

**Body:**

```json
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Test</title>
    </head>
    <body>
        <h1>Test page for PT1: SpringBoot</h1>
    </body>
</html>
```

