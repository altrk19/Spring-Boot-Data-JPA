POST localhost:8080/users
{
    "name": "ALI",
    "surname": "TURK",
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


POST localhost:8080/users
{
    "name": "UMUT",
    "surname": "CAN",
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




GET localhost:8080/users
GET localhost:8080/user/1

DELETE localhost:8080/user/1