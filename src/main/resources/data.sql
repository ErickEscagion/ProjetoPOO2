INSERT INTO TB_BASEUSER(name, email) VALUES('Erick', 'erick@gmail.com');
INSERT INTO TB_BASEUSER(name, email) VALUES('Michell', 'michell@gmail.com');
INSERT INTO TB_BASEUSER(name, email) VALUES('Laura', 'laura@outlook.com');
INSERT INTO TB_BASEUSER(name, email) VALUES('Duda', 'Duda@hotmail.com');

INSERT INTO TB_BASEUSER(name, email) VALUES('Pedro', 'Pedro@gmail.com');
INSERT INTO TB_BASEUSER(name, email) VALUES('Carlos', 'Carlos@gmail.com');
INSERT INTO TB_BASEUSER(name, email) VALUES('Lau', 'Lau@outlook.com');
INSERT INTO TB_BASEUSER(name, email) VALUES('Eduarda', 'Eduarda@hotmail.com');

INSERT INTO TB_ADMIN(user_id, phone_number) VALUES (1, '15998181242');
INSERT INTO TB_ADMIN(user_id, phone_number) VALUES (2, '15999888777');
INSERT INTO TB_ADMIN(user_id, phone_number) VALUES (3, '15912345678');
INSERT INTO TB_ADMIN(user_id, phone_number) VALUES (4, '15998765432');

INSERT INTO TB_ATTEND(user_id, balance) VALUES (5, 4000);
INSERT INTO TB_ATTEND(user_id, balance) VALUES (6, 102);
INSERT INTO TB_ATTEND(user_id, balance) VALUES (7, 336);
INSERT INTO TB_ATTEND(user_id, balance) VALUES (8, 2123);

INSERT INTO TB_PLACE(name,address) VALUES ('Praca','Praca, 123');
INSERT INTO TB_PLACE(name,address) VALUES ('Casa','Rua da casa, 265');
INSERT INTO TB_PLACE(name,address) VALUES ('Apartamento','Rua do Apartamento, 54');
INSERT INTO TB_PLACE(name,address) VALUES ('Shopping','Rua shop, 13');
INSERT INTO TB_PLACE(name,address) VALUES ('Ceu','Nuvens');

INSERT INTO TB_EVENT(name, description, email_Contact,start_Date,end_Date,start_Time,end_Time,amount_free_tickets,amount_paid_tickets,free_tickets_selled,paid_tickets_selled,price_ticket,admin_user_id) VALUES ('Evento do Erick','Descricao do Evento do Erick', 'email@contato.com', '2021-03-10', '2021-03-11', '22:00:50', '17:35:10', 30, 270, 0, 0, 50, 1);
INSERT INTO TB_EVENT(name, description, email_Contact,start_Date,end_Date,start_Time,end_Time,amount_free_tickets,amount_paid_tickets,free_tickets_selled,paid_tickets_selled,price_ticket,admin_user_id) VALUES ('Event from Erick','Description from Event', 'email@contact.com', '2021-03-11', '2021-03-11', '18:55:30', '22:03:12', 50, 50, 0, 0, 30, 1);
INSERT INTO TB_EVENT(name, description, email_Contact,start_Date,end_Date,start_Time,end_Time,amount_free_tickets,amount_paid_tickets,free_tickets_selled,paid_tickets_selled,price_ticket,admin_user_id) VALUES ('Evento da Laura','Descricao do Evento da Laura', 'Laura@contato.com', '2021-03-25', '2021-03-25', '16:00:00', '22:30:10', 5, 255, 0, 0, 90, 2);
INSERT INTO TB_EVENT(name, description, email_Contact,start_Date,end_Date,start_Time,end_Time,amount_free_tickets,amount_paid_tickets,free_tickets_selled,paid_tickets_selled,price_ticket,admin_user_id) VALUES ('Event from Laura','Description from Event Laura', 'Laura@contact.com', '2021-03-25', '2021-03-25', '16:00:00', '22:30:10', 0, 100, 0, 0, 520, 3);

INSERT INTO TB_PLACE_EVENT(place_id, event_id) VALUES (1, 4);
INSERT INTO TB_PLACE_EVENT(place_id, event_id) VALUES (2, 2);
INSERT INTO TB_PLACE_EVENT(place_id, event_id) VALUES (3, 3);
INSERT INTO TB_PLACE_EVENT(place_id, event_id) VALUES (4, 1);

INSERT INTO TB_TICKET(type, date, price, attend_user_id, event_id) VALUES (0, CURRENT_TIMESTAMP, 0, 5, 3);
INSERT INTO TB_TICKET(type, date, price, attend_user_id, event_id) VALUES (0, CURRENT_TIMESTAMP, 0, 5, 3);
INSERT INTO TB_TICKET(type, date, price, attend_user_id, event_id) VALUES (1, CURRENT_TIMESTAMP, 50, 6, 3);
INSERT INTO TB_TICKET(type, date, price, attend_user_id, event_id) VALUES (1, CURRENT_TIMESTAMP, 100, 7, 3);
