INSERT INTO CLIENT(id, first_name, last_name) VALUES (1, 'PIERRE_1', 'DUPONT_1');
INSERT INTO CLIENT(id, first_name, last_name) VALUES (2, 'PIERRE_2', 'DUPONT_2');
INSERT INTO CLIENT(id, first_name, last_name) VALUES (3, 'PIERRE_3', 'DUPONT_3');
INSERT INTO ACCOUNT(id, account_number, balance, client_id) VALUES (1, 'ACC1', 120, 1);
INSERT INTO ACCOUNT(id, account_number, balance, client_id) VALUES (2, 'ACC2', 240, 2);
INSERT INTO ACCOUNT(id, account_number, balance, client_id) VALUES (3, 'ACC3', 360, 3);
INSERT INTO OPERATION(id, amount, operation_date, operation_type, account_id) VALUES (1, 1200, null, 'DEPOSIT', 1);
INSERT INTO OPERATION(id, amount, operation_date, operation_type, account_id) VALUES (2, 300, null, 'WITHDRAWAL', 1);
