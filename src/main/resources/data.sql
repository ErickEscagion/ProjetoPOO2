INSERT INTO TB_BASEUSER(name, email) VALUES('erick', 'erick@gmail.com');
INSERT INTO TB_BASEUSER(name, email) VALUES('Michell', 'michell@gmail.com');

INSERT INTO TB_ADMIN(user_id, phone_number) VALUES (1, '15998181242');

INSERT INTO TB_ATTEND(user_id, balance) VALUES (2, 4000);

INSERT INTO TB_PLACE(name,address) VALUES ('nome','lugar');

INSERT INTO TB_EVENT(name, description, email_Contact,start_Date,end_Date,start_Time,end_Time,amount_free_tickets,amount_paid_tickets,free_tickets_selled,paid_tickets_selled,price_ticket,admin_user_id) VALUES ('Evento do Erick','Descricao do Evento do Erick', 'email@contato.com', '2021-03-10', '2021-03-11', '22:00:50', '17:35:10', 50, 250, 0, 0, 50, 1);
INSERT INTO TB_EVENT(name, description, email_Contact,start_Date,end_Date,start_Time,end_Time,amount_free_tickets,amount_paid_tickets,free_tickets_selled,paid_tickets_selled,price_ticket,admin_user_id) VALUES ('Event from Erick','Description from Event', 'email@contact.com', '2021-03-11', '2021-03-11', '18:55:30', '22:03:12', 50, 250, 0, 0, 50, 1);
INSERT INTO TB_EVENT(name, description, email_Contact,start_Date,end_Date,start_Time,end_Time,amount_free_tickets,amount_paid_tickets,free_tickets_selled,paid_tickets_selled,price_ticket,admin_user_id) VALUES ('Evento da Laura','Descricao do Evento da Laura', 'Laura@contato.com', '2021-03-25', '2021-03-25', '16:00:00', '22:30:10', 50, 250, 0, 0, 50, 1);
INSERT INTO TB_EVENT(name, description, email_Contact,start_Date,end_Date,start_Time,end_Time,amount_free_tickets,amount_paid_tickets,free_tickets_selled,paid_tickets_selled,price_ticket,admin_user_id) VALUES ('Event from Laura','Description from Event Laura', 'Laura@contact.com', '2021-03-25', '2021-03-25', '16:00:00', '22:30:10', 50, 250, 0, 0, 50, 1);

INSERT INTO TB_PLACE_EVENT(place_id, event_id) VALUES (1, 3);

INSERT INTO TB_TICKET(type, date, price, attend_user_id, event_id) VALUES (0, CURRENT_TIMESTAMP, 50, 2, 3);
