# Meet room


## 1. Tech stack

    Java 8 (Build tool: Apache Maven 3.8.1)
    Spring Boot 2.6.6
    
    PostgreSQL 14
    flyway 8.5.12

## 2. Application launch


1. Clone repo in your PC
2. Open project in any IDE
3. Run application
4. Go to ``localhost:8080`` in browser


## 3. Description


System for booking meeting room

Employee can book a meeting room for a period of 30 minutes to 24 hours.
The booking schedule is divided into sectors of 30 minutes each. 
When creating a booking for more than 30 minutes, the period is divided into sectors of 30 minutes each, 
which can be edited and deleted independently of each other.
Employee can join and exit other employees events.

    In nutshell employee can:
        - join, exit and read description of other employees events
        - create, update and delete own events
