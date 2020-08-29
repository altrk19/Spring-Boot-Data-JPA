1. Create Employee
POST- localhost:8080/employees
{
    "name":"ali",
    "dob":"10/10/2020",
    "dept":"developer",
    "salary":"10"
}

Response:
{
    "id": 1,
    "name": "ali",
    "dob": "10/10/2020",
    "dept": "developer",
    "salary": 10.0,
    "createDate": "29/08/2020",
    "lastModifiedDate": "29/08/2020",
    "createdBy": "Admin",
    "modifiedBy": "Admin"
}


2. Update Employee
UPDATE- localhost:8080/employee/1
{
    "name":"umut",
    "dob":"02/02/2020",
    "dept":"architecture",
    "salary":"15"
}

Response:
{
    "id": 1,
    "name": "umut",
    "dob": "02/02/2020",
    "dept": "architecture",
    "salary": 15.0,
    "createDate": "29/08/2020",
    "lastModifiedDate": "29/08/2020",
    "createdBy": "Admin",
    "modifiedBy": "Admin"
}


3.Get All Employees
GET- localhost:8080/employees

Response:
{
    "employees": [
        {
            "id": 1,
            "name": "umut",
            "dob": "02/02/2020",
            "dept": "architecture",
            "salary": 15.0,
            "createDate": null,
            "lastModifiedDate": null,
            "createdBy": null,
            "modifiedBy": null
        }
    ]
}