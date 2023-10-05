INSERT INTO users VALUES(NULL, 'admin', 'admin', 'admin@email.com', '$2a$12$bi8aUj1ExR1xAjER832FW.0pA.f.kXUJZrsnsRg6iNxwW2WSIpZz6', 1);

INSERT INTO roles VALUES(NULL, 'admin');
INSERT INTO roles VALUES(NULL, 'user');

INSERT INTO user_roles VALUES(NULL, 1, 1);