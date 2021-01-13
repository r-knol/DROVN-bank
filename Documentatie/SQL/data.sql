INSERT INTO `Customer`
VALUES (1, 'loo', 'loo', 'NATURAL'),
       (2, 'rknol', 'rknol', 'NATURAL'),
       (3, 'hva', 'hva', 'LEGAL');

INSERT INTO `NaturalPerson`
VALUES (1, 'N.', 'Nina', 'van', 'Loo', '1987-02-07', '159398289', 'ninavanloo@gmail.com', '0610087058', '1056 AC',
        '2-H', 'James Rosskade', 'Amsterdam'),
       (2, 'R.W.', 'Richard', NULL, 'Knol', '1990-05-11', '243535345', 'rknol@gmail.com', '0612345678', '1234 AQ',
        '345',
        'Oudenoord', 'Utrecht');

INSERT INTO `Employee`
VALUES (1, 'Guus1', 'Guus1', 'Guus', NULL, 'Ouderkerk', 'ACCOUNTMANAGER'),
       (2, 'jantje1', 'jan202', 'Jan', 'de', 'Hond', 'HEAD_LEGAL'),
       (3, 'pietje1', 'piet5', 'Piet', NULL, 'Joosten', 'HEAD_PRIVATE'),
       (4, 'Anne', 'antje1', 'Annemarie', 'van', 'Hoven', 'ACCOUNTMANAGER'),
       (5, 'Jet', 'jetje1', 'Jet', NULL, 'Rover', 'ACCOUNTMANAGER'),
       (6, 'Bob1990', 'bobje', 'Robert', 'de', 'Wit', 'ACCOUNTMANAGER'),
       (7, 'Sopje', 'sopje26', 'Sophie', NULL, 'Koper', 'ACCOUNTMANAGER');

INSERT INTO `LegalPerson`
VALUES (3, 'HogeSchool van Amsterdam', 129831236, 'EDUCATION', '1234567', '1000 AB', '1', 'Wibaaut', 'Amsterdam', 1);

INSERT INTO `Employee`
VALUES (1, 'Guus1', 'Guus1', 'Guus', NULL, 'Ouderkerk', 'ACCOUNTMANAGER'),
       (2, 'jantje1', 'jan202', 'Jan', 'de', 'Hond', 'HEAD_LEGAL'),
       (3, 'pietje1', 'piet5', 'Piet', NULL, 'Joosten', 'HEAD_PRIVATE'),
       (4, 'Anne', 'antje1', 'Annemarie', 'van', 'Hoven', 'ACCOUNTMANAGER'),
       (5, 'Jet', 'jetje1', 'Jet', NULL, 'Rover', 'ACCOUNTMANAGER'),
       (6, 'Bob1990', 'bobje', 'Robert', 'de', 'Wit', 'ACCOUNTMANAGER'),
       (7, 'Sopje', 'sopje26', 'Sophie', NULL, 'Koper', 'ACCOUNTMANAGER');

INSERT INTO `Account`
VALUES (1, 'NL14DRVN0478712478', 0),
       (2, 'NL19DRVN0478579866', 0),
       (3, 'NL25DRVN0354785412', 0),
       (4, 'NL31DRVN0124574999', 0),
       (5, 'NL36DRVN0214744521', 0),
       (6, 'NL52DRVN0214785124', 0),
       (7, 'NL65DRVN0514125121', 0),
       (8, 'NL71DRVN0541263213', 0),
       (9, 'NL77DRVN0457812141', 0),
       (10, 'NL78DRVN0254778965', 0),
       (11, 'NL87DRVN0981245744', 0);

INSERT INTO `customer_has_account`
VALUES (4, 1),
       (4, 2),
       (6, 3);

INSERT INTO `transaction`
VALUES (1, 45.00, 'transactie', '2020-12-30 12:25:59', 'NL87DRVN0981245744', 'NL14DRVN0478712478'),
       (2, 1252.50, 'transactie', '2020-12-29 08:15:00', 'NL25DRVN0354785412', 'NL14DRVN0478712478'),
       (3, 2514.78, 'transactie', '2020-12-27 15:32:14', 'NL14DRVN0478712478', 'NL31DRVN0124574999'),
       (4, 99.99, 'transactie', '2020-12-16 14:47:18', 'NL77DRVN0457812141', 'NL14DRVN0478712478'),
       (5, 78.56, 'transactie', '2020-12-15 16:47:33', 'NL19DRVN0478579866', 'NL14DRVN0478712478'),
       (6, 24.99, 'pinbetaling', '2020-12-14 10:03:15', 'NL78DRVN0254778965', 'NL14DRVN0478712478'),
       (7, 39.25, 'pinbetaling', '2020-12-12 08:58:56', 'NL36DRVN0214744521', 'NL14DRVN0478712478'),
       (8, 8.00, 'pinbetaling', '2020-12-11 17:12:35', 'NL65DRVN0514125121', 'NL14DRVN0478712478'),
       (9, 79.99, 'iDeal betaling', '2020-12-11 11:33:26', 'NL52DRVN0214785124', 'NL14DRVN0478712478'),
       (10, 19.99, 'iDeal betaling', '2020-12-10 23:14:59', 'NL52DRVN0214785124', 'NL14DRVN0478712478'),
       (11, 185.23, 'Incasso', '2020-12-06 00:00:00', 'NL71DRVN0541263213', 'NL14DRVN0478712478');