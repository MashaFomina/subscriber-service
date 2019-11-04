## Description
The project allows subscribers make calls, send sms, check balance.

## Requirements
- JDK 8
- PostgresSQL 10+

**Create database and schema:**
```
# connect to database
psql -p 5432 -U postgres -d postgres

# create database and schema
$ CREATE DATABASE subscribers;
$ \c subscribers;
$ CREATE schema subscribers;
```

- Fill database username and password in `application.yml`: `username`, `password`.
- See **REST API Swagger Documentation** by using url `http://localhost:8080` after running app.

## TODO:
- round balance to 2 decimal places (2 signs precision)
- authentication and authorization 
- several roles (subscriber, operator)
- only operator can add subscribers, edit information and add money to balance
 
