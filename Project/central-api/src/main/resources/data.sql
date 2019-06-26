-- INSERT INTO ROLE (ID, NAME) VALUES (4, 'AGENT');
--
-- INSERT INTO USER (USER, ID, EMAIL, LAST_NAME, NAME, PASSWORD, SALT, USER_STATUS, USERNAME, ROLE_ID)
-- VALUES ('Agent', 3, 'marjanskid@uns.ac.rs', 'Marjanski', 'Dusan',
-- 		'$2a$10$s6XQqHePCeEI/8Rhu/0rie3Gzf0k5DMZ40rjhC0Tnpl0rnoyf/73.',
-- 		'org.springframework.security.crypto.keygen.SecureRandomBytesKeyGenerator@5b09efcc',
-- 		1, 'marjanskid', 4);
--

insert into role (id,name)  values (1001,'DefaultRole');
insert into role (id,name)  values (1002,'SYSADMIN');
insert into role (id,name)  values (1003,'SECADMIN');
insert into role (id,name)  values (1004,'CLIENT_USER');
insert into role (id,name)  values (1005,'AGENT_USER');

insert into role_privileges (role_id, privileges) VALUES (1005,'WRITE_PRODUCT');
insert into role_privileges (role_id, privileges) VALUES (1005,'READ_PRODUCT');
insert into role_privileges (role_id, privileges) VALUES (1005,'DELETE_PRODUCT');

insert into user (user, id, email, last_name, name, password, salt, user_status, username, role_id) values ('REGISTERED',1001,'mejlic@mail.com','markovic','marko','$2a$10$5SSvvBC/Ed8RpagSdaJGheizPxt9lQemBR02s38E7GMt1jCc8n1v.','org.springframework.security.crypto.keygen.SecureRandomBytesKeyGenerator@4435e0b1',1,'user1',1004);
insert into user (user, id, email, last_name, name, password, salt, user_status, username, role_id) values ('AGENT',1002,'mejl@mail.com','agent','agentName','$2a$10$5SSvvBC/Ed8RpagSdaJGheizPxt9lQemBR02s38E7GMt1jCc8n1v.','org.springframework.security.crypto.keygen.SecureRandomBytesKeyGenerator@4435e0b1',1,'agent',1005);
insert into user (user, id, email, last_name, name, password, salt, user_status, username, role_id) values ('User',1003,'mejli@mail.com','SecAdmin','SecAdminName','$2a$10$5SSvvBC/Ed8RpagSdaJGheizPxt9lQemBR02s38E7GMt1jCc8n1v.','org.springframework.security.crypto.keygen.SecureRandomBytesKeyGenerator@4435e0b1',1,'secadmin',1003);
insert into user (user, id, email, last_name, name, password, salt, user_status, username, role_id) values ('User',1004,'mejlic1@mail.com','SysAmdin','SysAdminName','$2a$10$5SSvvBC/Ed8RpagSdaJGheizPxt9lQemBR02s38E7GMt1jCc8n1v.','org.springframework.security.crypto.keygen.SecureRandomBytesKeyGenerator@4435e0b1',1,'sysadmin',1002);

insert into agent_user (pib, id) VALUES ('12134',1002);

insert into location ( lat, lng, name) values (100,101,'mesto1');
insert into location ( lat, lng, name) values (101,102,'mesto2');
insert into location ( lat, lng, name) values (102,103,'mesto3');

insert into accommodation_category (description,active) values ("1star",1);
insert into accommodation_category (description,active) values ("2star",1);
insert into accommodation_category (description,active) values ("3star",1);
insert into accommodation_category (description,active) values ("4star",1);
insert into accommodation_category (description,active) values ("5star",1);

insert into accommodation_type (description,active) values ("Hotel",1);
insert into accommodation_type (description,active) values ("room",1);
insert into accommodation_type (description,active) values ("tip neki",1);

insert into room_additional_service (description,active) values ("Wifi",1);
insert into room_additional_service (description,active) values ("DogFriendly",1);
insert into room_additional_service (description,active) values ("AirCondition",1);

insert into price_list (month, price) value ('2019-6-14',120);
insert into price_list (month, price) value ('2019-7-04',130);
insert into price_list (month, price) value ('2019-6-24',140);

insert into room (days_for_cancel, description, number_of_beds, accommodation_category_id, accommodation_type_id, location_id) values (10,'soba 1',2,3,1,1);
insert into room (days_for_cancel, description, number_of_beds, accommodation_category_id, accommodation_type_id, location_id) values (15,'soba 2',3,1,2,2);
insert into room (days_for_cancel, description, number_of_beds, accommodation_category_id, accommodation_type_id, location_id) values (12,'soba 3',4,4,3,3);

insert into reservation (check_in, check_out, state) values ('2019-6-14','2019-7-14',0);
insert into reservation (check_in, check_out, state) values ('2019-6-16','2019-6-27',0);
insert into reservation (check_in, check_out, state) values ('2019-7-14','2019-8-14',0);
insert into reservation (check_in, check_out, state) values ('2019-7-15','2019-7-26',2);


insert into agent_user_room (agent_user_id, room_id) VALUES (1002,1);
insert into agent_user_room (agent_user_id, room_id) VALUES (1002,2);
insert into agent_user_room (agent_user_id, room_id) VALUES (1002,3);

insert into room_room_additional_service (room_id, room_additional_service_id) values (1,1);
insert into room_room_additional_service (room_id, room_additional_service_id) values (2,2);
insert into room_room_additional_service (room_id, room_additional_service_id) values (2,1);
insert into room_room_additional_service (room_id, room_additional_service_id) values (2,2);
insert into room_room_additional_service (room_id, room_additional_service_id) values (3,3);

insert into room_reservation (room_id, reservation_id) VALUES (1,1);
insert into room_reservation (room_id, reservation_id) VALUES (2,2);
insert into room_reservation (room_id, reservation_id) VALUES (3,3);

insert into room_price_list (room_id, price_list_id) VALUES (1,1);
insert into room_price_list (room_id, price_list_id) VALUES (2,2);
insert into room_price_list (room_id, price_list_id) VALUES (3,3);