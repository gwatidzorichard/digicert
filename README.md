# Hotel Booking API powered by Springboot

## Introduction
This project is a Spingboot application running within a docker container on port 8080 using Java17 and Jersey using an in-memory H2 database

## Problem Statement
Create a Springboot application running within a docker container on port 8080 using Java 17 and Jersey using in-memory H2 database.
Create an Agency Booking REST API.

- The Booking API would be a hotel booking service that allows you to list, create, update and delete a reservation.
- Basic error handing, logging.
- Create a README detailing instructions on how to run and call the API.
- Write appropriate unit and Integration tests
- An agency might have several hundred bookings how would solve this issue through API

## Agency Booking REST API.

The API has the following endpoints in the controller

- `POST` at `/reservations/create` which you can use to:
    - Save a new reservation with passed `{id}`
  

- `POST` at `/reservations/createFromAgency` which you can use to:
    - Save multiple reservations from a particular Agency


- `GET` at `/reservations/{id}` which should:
    - Return a reservation with passed `{id}`


- `PUT` at `/reservations/{id}` which should:
    - Allow the updating of a reservation with the passed `{id}`


- `DEL` at `/reservations/delete/{id}` which should:
    - Allow the deletion of a reservation with the passed `{id}`



## Running the application

1. A postman collection has been added in the resources package.
2. On the APIs it is best to start by running the createFromAgency which creates bulk number of reservations before testing other endpoints since 
   I had limited time to implement all the necessary error handling. This is also because I used the H2 database which is volatile hence it does not store data permanently. 
3. 

