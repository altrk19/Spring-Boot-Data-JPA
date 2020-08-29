1-Create User1
POST localhost:8080/users
{
    "name": "ali",
    "surname": "turk",
    "address": [
        {
            "addressLine": "addressLine1",
            "city": "Istanbul",
            "country": "TURKEY",
            "addressType": "WORK",
            "active": true
        },
        {
            "addressLine": "addressLine2",
            "city": "Ankara",
            "country": "TURKEY",
            "addressType": "OTHER",
            "active": true
        }
    ]
}

2-Create User2
POST localhost:8080/users
{
    "name": "umut",
    "surname": "turk",
    "address": [
        {
            "addressLine": "addressLine3",
            "city": "Kocaeli",
            "country": "TURKEY",
            "addressType": "HOME",
            "active": true
        }
    ]
}


3-Create User3
POST localhost:8080/users
{
    "name": "ali",
    "surname": "can",
    "address": [
        {
            "addressLine": "addressLine3",
            "city": "Kocaeli",
            "country": "TURKEY",
            "addressType": "HOME",
            "active": true
        }
    ]
}


4-Get All Users
GET localhost:8080/users

5-Get User By Id
GET localhost:8080/user/1

6-Delete User By Id
DELETE localhost:8080/user/1

7-Get Users By Surname
localhost:8080/users/surname/turk

8-Get Users By Name And Surname
localhost:8080/users/name/ali/surname/turk

9-Get Users By Surname (using Named Query)
localhost:8080/users/surname/turk/named_query

10-Get Users By Surname (using Named Native Query)
localhost:8080/users/surname/turk/named_native_query

11-Get Users By Surname (using Jpql Query)
localhost:8080/users/surname/turk/jpql_query

12-Get Users By Surname (using Native Sql Query)
localhost:8080/users/surname/turk/native_sql_query

13-Get Users By Name (using Async)
localhost:8080/user/ali/async