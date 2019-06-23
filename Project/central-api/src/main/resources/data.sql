INSERT INTO ROLE (ID, NAME) VALUES (4, 'AGENT');

INSERT INTO USER (USER, ID, EMAIL, LAST_NAME, NAME, PASSWORD, SALT, USER_STATUS, USERNAME, ROLE_ID) 
VALUES ('Agent', 3, 'marjanskid@uns.ac.rs', 'Marjanski', 'Dusan',
		'$2a$10$s6XQqHePCeEI/8Rhu/0rie3Gzf0k5DMZ40rjhC0Tnpl0rnoyf/73.',
		'org.springframework.security.crypto.keygen.SecureRandomBytesKeyGenerator@5b09efcc',
		1, 'marjanskid', 4);