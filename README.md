# Projeto de Programação Orientada a Objetos 2

Projeto desenvolvido para o aperfeiçoamento do conhecimento na disciplina de Programação Orientada a Objetos 2.

## Desenvolvedores
<p>Erick Ap. Escagion | RA: 190776
<p>Michell Algarra Barros | RA: 190100

## Heroku
Link: https://poo2-events.herokuapp.com/

> Usar o /events apos o link como rota padrão

## Entidades
#### ADMIN
##### Rotas
```
GET:
 Endpoint: /admins
 Optional Params:
  page (int),
  linesPerPage (int),
  direction (String),
  orderBy (String),
  name (String),
  email (String),
  phoneNumber (String)

GET (by Id): /admins/{id}
POST: /admins
PUT: /admins/{id}
DELETE: /admins/{id}
```

##### JSON
```json
{
  "name": "Admin",
  "email": "admin@gmail.com",
  "phoneNumber": "15998181242"
}
```
 
#### ATTEND
##### Rotas
```
GET:
 Endpoint: /attendees
 Optional Params:
  page (int),
  linesPerPage (int),
  direction (String),
  orderBy (String),
  name (String),
  email (String),
  balance (double)

GET (by Id): /attendees/{id}
POST: /attendees
PUT: /attendees/{id}
DELETE: /attendees/{id}
```

##### JSON
```json
{
  "name": "Attend",
  "email": "attend@gmail.com",
  "balance": "1000"
}
```
 
#### PLACE
##### Rotas
```
GET:
 Endpoint: /places
 Optional Params:
  page (int),
  linesPerPage (int),
  direction (String),
  orderBy (String),
  name (String),
  address (String)

GET (by Id): /places/{id}
POST: /places
PUT: /places/{id}
DELETE: /places/{id}
```

##### JSON
```json
{ 
  "name": "Nome",
  "address": "Lugar"
}
```

#### EVENT
##### Rotas
```
GET:
 Endpoint: /events
 Optional Params:
  page (int),
  linesPerPage (int),
  direction (String),
  orderBy (String),
  name (String),
  description (String),
  place (String),
  startDate (String) // 1970-01-01

GET (by Id): /events/{id}
POST: /events
PUT: /events/{id}
DELETE: /events/{id}
```

##### JSON
 
> quantidade de tickets vendidos são inicializados com o valor 0

> o placeId é necessário pois o evento deve possuir ao menos um lugar
 
```json
{
  "name": "Evento",
  "description": "Descricao do Evento",
  "startDate": "2021-03-09",
  "endDate": "2021-03-10",
  "startTime": "18:00:50",
  "endTime": "19:00:50",
  "emailContact": "email@contato.com",
  "amountFreeTickets": 50,
  "amountPaidTickets": 250,
  "priceTicket": 50.0,
  "adminId": 1,
  "placeId": 1
}
```
 
#### EVENT Tickets
##### Rotas
```
GET:
 Endpoint: /events/{id}/tickets
 Optional Params:
  page (int),
  linesPerPage (int),
  direction (String),
  orderBy (String),
  type (String) - "free" or "paid"

POST: /events/{id}/tickets
DELETE: /events/{id}/tickets/{ticketId}
```

##### JSON
 
> 'type' must be 0 (FREE) or 1 (PAID)
 
```json
{
  "type": 0,
  "attendId": 1
}
```
 
#### EVENT Places
##### Rotas
```
GET:
 Endpoint: /events/{id}/places
 Optional Params:
  page (int),
  linesPerPage (int),
  direction (String),
  orderBy (String),
  name (String),
  address (String)

POST | DELETE: /events/{id}/places/{placeId}
```

## Modelo Conceitual
<img src="https://user-images.githubusercontent.com/55297869/119226076-e526dc00-badd-11eb-9832-50f42054251d.png" alt="Modelo"/>
