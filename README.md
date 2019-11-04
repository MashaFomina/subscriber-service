## Description
The project allows subscribers make calls, send sms, check balance.

**Create database and schema:**
```
# connect to database
psql -p 5432 -U postgres -d postgres

# create database and schema
$ CREATE DATABASE subscribers;
$ \c subscribers;
$ CREATE schema subscribers;
```

## TODO:
- authorization 
- several roles (subscriber, operator)
- only operator can add subscribers, edit information ans add money to balance
 
