SET MODE MYSQL;
/* Private Customers */
INSERT INTO `customer` (`customerID`, `userName`, `password`, `customerType`)
VALUES (1, 'loo', 'loo', 'NATURAL');
INSERT INTO `customer` (`customerID`, `userName`, `password`, `customerType`)
VALUES (2, 'rknol', 'rknol', 'NATURAL');
INSERT INTO `customer` (`customerID`, `userName`, `password`, `customerType`)
VALUES (3, 'daan', 'daan', 'NATURAL');
INSERT INTO `customer` (`customerID`, `userName`, `password`, `customerType`)
VALUES (4, 'olaf', 'olaf', 'NATURAL');
/* Business Customers */
INSERT INTO `customer` (`customerID`, `userName`, `password`, `customerType`)
VALUES (4001, 'Kim2', 'Clsg.:L,6', 'LEGAL');
INSERT INTO `customer` (`customerID`, `userName`, `password`, `customerType`)
VALUES (4002, 'Max3', 'vDnt#iUR1', 'LEGAL');
INSERT INTO `customer` (`customerID`, `userName`, `password`, `customerType`)
VALUES (4003, 'Guus7', 'zQFIF "up', 'LEGAL');
INSERT INTO `customer` (`customerID`, `userName`, `password`, `customerType`)
VALUES (4004, 'Teddy471', 'AS1@@&T2G', 'LEGAL');
/* Employees */
INSERT INTO `employee` (`employeeID`, `userName`, `password`, `firstName`, `surName`, `role`)
VALUES (1, 'Lu2', '0gG#Wm=bh', 'Ed', 'Hopper', 'ACCOUNTMANAGER');
INSERT INTO `employee` (`employeeID`, `userName`, `password`, `firstName`, `surName`, `role`)
VALUES (2, 'Patricia037', 'ea#KU%sds', 'Brad', 'Brown', 'ACCOUNTMANAGER');
INSERT INTO `employee` (`employeeID`, `userName`, `password`, `firstName`, `surName`, `role`)
VALUES (3, 'Andrew', 'mc#TeOGK6', 'Guo', 'Jiminez', 'ACCOUNTMANAGER');
INSERT INTO `employee` (`employeeID`, `userName`, `password`, `firstName`, `surName`, `role`)
VALUES (4, 'Rob', '''yFLrncC3', 'Christine', 'Blount', 'ACCOUNTMANAGER');
INSERT INTO `employee` (`employeeID`, `userName`, `password`, `firstName`, `surName`, `role`)
VALUES (5, 'Luis360', '%D$xY''nBf', 'Juri', 'Meterson', 'ACCOUNTMANAGER');
INSERT INTO `employee` (`employeeID`, `userName`, `password`, `firstName`, `surName`, `role`)
VALUES (6, 'Mads3', 'zP35uJ17.', 'Hens', 'Dittrich', 'HEAD_PRIVATE');
INSERT INTO `employee` (`employeeID`, `userName`, `password`, `firstName`, `surName`, `role`)
VALUES (7, 'Cathy', '34d:3&"A7', 'John', 'Chwatal', 'HEAD_LEGAL');
/* Private Customer Details */
INSERT INTO `naturalperson` (`customerID`, `initials`, `firstName`, `preposition`, `surName`, `dateOfBirth`,
                             `socialSecurityNumber`, `email`, `phone`, `postalCode`, `homeNumber`, `street`,
                             `residence`)
VALUES (1, 'B.L', 'Shermie', NULL, 'Imhoff', '1963-06-13', 03911573, 'Nadine.Friedman2@dolfijn.be', '0601572904',
        '3244IA', '52', 'Moerslaan', 'Oldebroek');
INSERT INTO `naturalperson` (`customerID`, `initials`, `firstName`, `preposition`, `surName`, `dateOfBirth`,
                             `socialSecurityNumber`, `email`, `phone`, `postalCode`, `homeNumber`, `street`,
                             `residence`)
VALUES (2, 'N.C', 'Saskia', 'van', 'Clarke', '1907-05-41', 97455752, 'FredIonescu3@mail.es', '0628683349', '1469OH',
        '04', 'Sint-corneliuslaan', 'Reusel-De Mierden ');
INSERT INTO `naturalperson` (`customerID`, `initials`, `firstName`, `preposition`, `surName`, `dateOfBirth`,
                             `socialSecurityNumber`, `email`, `phone`, `postalCode`, `homeNumber`, `street`,
                             `residence`)
VALUES (3, 'B.G', 'Maximilian', NULL, 'van der Laar', '2020-04-21', 71530304, 'RichardAllison1@msn.co.uk', '0620538640',
        '9370AQ', '68', 'Ledeganckstraat', 'Muiden');
INSERT INTO `naturalperson` (`customerID`, `initials`, `firstName`, `preposition`, `surName`, `dateOfBirth`,
                             `socialSecurityNumber`, `email`, `phone`, `postalCode`, `homeNumber`, `street`,
                             `residence`)
VALUES (4, 'Q.O', 'Willy', NULL, 'Bryant', '1953-02-13', 06252102, 'RMartin2@mymail.nl', '0656975859', '9051MZ', '35',
        'Frederik van edenweg', 'Delfzijl ');
/* Business Customer Details */
INSERT INTO `legalperson` (`companyID`, `companyName`, `kvkNumber`, `sector`, `vatNumber`, `postalCode`, `homeNumber`,
                           `street`, `residence`, `accountmanagerID`)
VALUES (4001, 'VOF De Vlinder', 29850235, 'IT', 'NL079622118B22', '7421GA', '65', 'Wanbeeklaan', 'Wervershoof ', 3);
INSERT INTO `legalperson` (`companyID`, `companyName`, `kvkNumber`, `sector`, `vatNumber`, `postalCode`, `homeNumber`,
                           `street`, `residence`, `accountmanagerID`)
VALUES (4002, 'Secure Computing', 93137626, 'CARE', 'NL339580048B44', '1394UG', '75', 'Herdersgracht', 'Arnhem', 1);
INSERT INTO `legalperson` (`companyID`, `companyName`, `kvkNumber`, `sector`, `vatNumber`, `postalCode`, `homeNumber`,
                           `street`, `residence`, `accountmanagerID`)
VALUES (4003, 'Getronics', 69879640, 'CONSULTANCY', 'NL921726226B12', '2351FP', '16', 'Prins bernhardsingel', 'Uden ',
        1);
INSERT INTO `legalperson` (`companyID`, `companyName`, `kvkNumber`, `sector`, `vatNumber`, `postalCode`, `homeNumber`,
                           `street`, `residence`, `accountmanagerID`)
VALUES (4004, 'Hessersmitt', 15638233, 'IT', 'NL880447260B56', '0098QK', '29', 'Ananasweg', 'Vaals ', 3);