/*
Description: Populate the database with demo data.
Created by: Wesley Van Malcot & Shenno Willaert
*/

/*
Remove all data
*/
SET SQL_SAFE_UPDATES = 0;
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE realdolmen.pricingrule;
TRUNCATE TABLE realdolmen.booking;
TRUNCATE TABLE realdolmen.user;
TRUNCATE TABLE realdolmen.partner;
TRUNCATE TABLE realdolmen.flighttravelcategory;
TRUNCATE TABLE realdolmen.flight;
TRUNCATE TABLE realdolmen.Airport;
SET SQL_SAFE_UPDATES = 1;
SET FOREIGN_KEY_CHECKS = 1;
	
/*
Populate Airport
*/
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1000,'UNHEA','United Kingdom','Heathrow Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1001,'FRCHA','France','Charles de Gaulle Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1002,'TUIST','Turkey','Istanbul Atatürk Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1003,'GEFRA','Germany','Frankfurt Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1004,'NEAMS','Netherlands','Amsterdam Airport Schiphol','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1005,'SPADO','Spain','Adolfo Suárez Madrid–Barajas Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1006,'GEMUN','Germany','Munich Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1007,'ITLEO','Italy','Leonardo da Vinci–Fiumicino Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1008,'UNGAT','United Kingdom','Gatwick Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1009,'SPBAR','Spain','Barcelona El Prat Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1010,'RUSHE','Russia','Sheremetyevo International Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1011,'RUDOM','Russia','Domodedovo International Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1012,'FRPAR','France','Paris-Orly Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1013,'TUSAB','Turkey','Sabiha Gökçen Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1014,'TUANT','Turkey','Antalya Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1015,'DECOP','Denmark','Copenhagen Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1016,'SWZÜR','Switzerland','Zürich Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1017,'IRDUB','Ireland','Dublin Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1018,'NOOSL','Norway','Oslo Airport, Gardermoen','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1019,'SPPAL','Spain','Palma de Mallorca Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1020,'BEBRU','Belgium','Brussels Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1021,'SWSTO','Sweden','Stockholm-Arlanda Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1022,'UNMAN','United Kingdom','Manchester Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1023,'AUVIE','Austria','Vienna International Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1024,'UNSTA','United Kingdom','Stansted Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1025,'GEDÜS','Germany','Düsseldorf Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1026,'GETEG','Germany','Tegel Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1027,'POLIS','Portugal','Lisbon Portela Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1028,'ITMAL','Italy','Malpensa Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1029,'GRATH','Greece','Athens International Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1030,'FIHEL','Finland','Helsinki Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1031,'RUVNU','Russia','Vnukovo International Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1032,'SWGEN','Switzerland','Geneva International Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1033,'GEHAM','Germany','Hamburg Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1034,'SPMÁL','Spain','Málaga Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1035,'RUPUL','Russia','Pulkovo Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1036,'TUESE','Turkey','Esenboğa International Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1037,'UNLUT','United Kingdom','Luton Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1038,'TUADN','Turkey','Adnan Menderes Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1039,'CZVÁC','Czech Republic','Václav Havel Airport Prague','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1040,'POFRE','Poland','Frederic Chopin Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1041,'UNEDI','United Kingdom','Edinburgh Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1042,'SPGRA','Spain','Gran Canaria Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1043,'SPALI','Spain','Alicante Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1044,'GESTU','Germany','Stuttgart Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1045,'ITORI','Italy','Orio al Serio Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1046,'GECOL','Germany','Cologne Bonn Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1047,'HUBUD','Hungary','Budapest Liszt Ferenc International Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1048,'UNBIR','United Kingdom','Birmingham Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1049,'ITLIN','Italy','Linate Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1050,'ROHEN','Romania','Henri Coandă International Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1051,'SPTEN','Spain','Tenerife South Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1052,'ITVEN','Italy','Venice Marco Polo Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1053,'UNGLA','United Kingdom','Glasgow International Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1054,'FRLYO','France','Lyon-Saint Exupéry Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1055,'GESCH','Germany','Schönefeld Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1056,'FRMAR','France','Marseille Provence Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1057,'POFRA','Portugal','Francisco de Sá Carneiro Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1058,'FRBLA','France','Blagnac Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1059,'UKBOR','Ukraine','Boryspil International Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1060,'ITCAT','Italy','Catania-Fontanarossa Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1061,'FREUR','France','EuroAirport Basel-Mulhouse-Freiburg','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1062,'BECHA','Belgium','Charleroi Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1063,'ITBOL','Italy','Bologna Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1064,'UNBRI','United Kingdom','Bristol Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1065,'SPIBI','Spain','Ibiza Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1066,'POFAR','Portugal','Faro Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1067,'ITNAP','Italy','Naples Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1068,'SWGOT','Sweden','Gothenburg-Landvetter Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1069,'SPLAN','Spain','Lanzarote Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1070,'GRHER','Greece','Heraklion International Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1071,'NOBER','Norway','Bergen Airport, Flesland','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1072,'ITROM','Italy','Rome Ciampino Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1073,'GELAN','Germany','Langenhagen Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1074,'CYLAR','Cyprus','Larnaca International Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1075,'TUADA','Turkey','Adana Şakirpaşa Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1076,'GRTHE','Greece','Thessaloniki International Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1077,'FRBOR','France','Bordeaux - Mérignac Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1078,'LARIG','Latvia','Riga International Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1079,'SPVAL','Spain','Valencia Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1080,'SPFUE','Spain','Fuerteventura Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1081,'CRSIM','Crimea','Simferopol International Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1082,'ITFAL','Italy','Falcone–Borsellino Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1083,'ICKEF','Iceland','Keflavík International Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1084,'ITGAL','Italy','Galileo Galilei Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1085,'SEBEL','Serbia','Belgrade Nikola Tesla Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1086,'MAMAL','Malta','Malta International Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1087,'GRRHO','Greece','Rhodes International Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1088,'UNNEW','United Kingdom','Newcastle Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1089,'NOSTA','Norway','Stavanger Airport, Sola','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1090,'UNEAS','United Kingdom','East Midlands Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1091,'FRNAN','France','Nantes Atlantique Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1092,'UNBEL','United Kingdom','Belfast International Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1093,'TUDAL','Turkey','Dalaman Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1094,'NOTRO','Norway','Trondheim Airport, Værnes','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1095,'NEEIN','Netherlands','Eindhoven Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1096,'FRBEA','France','Beauvais-Tillé Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1097,'UNLON','United Kingdom','London City Airport','EUROPE');
INSERT INTO realdolmen.Airport (id,airportCode,country,name,region) VALUES (1098,'SPSAN','Spain','San Pablo Airport','EUROPE');

/*
Populate Partner
*/
INSERT INTO realdolmen.Partner (id, description,logo,name) VALUES (1, 'TUI is de grootste touroperator van België en onderdeel van de TUI Group, de grootste toerismegroep ter wereld. Tot de vennootschap Jetair NV behoren ook de touroperatormerken VIP Selection, VTB Reizen, en Sunjets.be.',null,'TUI');
INSERT INTO realdolmen.Partner (id, description,logo,name) VALUES (2, 'Ryanair is de grootste lagekostenluchtvaartmaatschappij voor continentale vluchten in Europa. Daarnaast is het een van de weinige prijsvechters die daadwerkelijk winstgevend is.',null,'Ryanair');

/*
Populate User
*/
INSERT INTO realdolmen.User (id, city,firstName,houseNumber,lastName, password, phoneNumber, role, street, userName, zip, partnerId) VALUES (1, 'Mechelen','Wesley','141','Van Malcot','123', '0497828701', 'REGULAR', null, 'wesley.vanmalcot@realdolmen.com', '2800', null);
INSERT INTO realdolmen.User (id, city,firstName,houseNumber,lastName, password, phoneNumber, role, street, userName, zip, partnerId) VALUES (2, null, null, null, null, '123', null, 'EMPLOYEE', null, 'shenno.willaert@realdolmen.com', null, null);
INSERT INTO realdolmen.User (id, city,firstName,houseNumber,lastName, password, phoneNumber, role, street, userName, zip, partnerId) VALUES (3, null, null, null, null, '123', null, 'PARTNER', null, 'tui@rair.com', null, 1);

/*
Populate Flight
*/
INSERT INTO realdolmen.Flight (id, arrivalDateTime, departureDateTime, version, arrAirportId, depAirportId, partnerId) VALUES (1, '2016-11-17 06:00:00', '2016-11-17 05:00:00', 0, 1075, 1060, 1);
INSERT INTO realdolmen.Flight (id, arrivalDateTime, departureDateTime, version, arrAirportId, depAirportId, partnerId) VALUES (2, '2016-11-17 06:00:00', '2016-11-17 05:00:00', 0, 1022, 1063, 1);
INSERT INTO realdolmen.Flight (id, arrivalDateTime, departureDateTime, version, arrAirportId, depAirportId, partnerId) VALUES (3, '2016-11-17 06:00:00', '2016-11-17 05:00:00', 0, 1011, 1010, 1);
INSERT INTO realdolmen.Flight (id, arrivalDateTime, departureDateTime, version, arrAirportId, depAirportId, partnerId) VALUES (4, '2016-11-17 06:00:00', '2016-11-17 05:00:00', 0, 1097, 1006, 1);
INSERT INTO realdolmen.Flight (id, arrivalDateTime, departureDateTime, version, arrAirportId, depAirportId, partnerId) VALUES (5, '2016-11-17 06:00:00', '2016-11-17 05:00:00', 0, 1024, 1048, 1);
INSERT INTO realdolmen.Flight (id, arrivalDateTime, departureDateTime, version, arrAirportId, depAirportId, partnerId) VALUES (6, '2016-11-17 06:00:00', '2016-11-17 05:00:00', 0, 1007, 1012, 1);
INSERT INTO realdolmen.Flight (id, arrivalDateTime, departureDateTime, version, arrAirportId, depAirportId, partnerId) VALUES (7, '2016-11-17 06:00:00', '2016-11-17 05:00:00', 0, 1075, 1012, 1);
INSERT INTO realdolmen.Flight (id, arrivalDateTime, departureDateTime, version, arrAirportId, depAirportId, partnerId) VALUES (8, '2016-11-18 06:00:00', '2016-11-18 05:00:00', 0, 1075, 1060, 1);
INSERT INTO realdolmen.Flight (id, arrivalDateTime, departureDateTime, version, arrAirportId, depAirportId, partnerId) VALUES (9, '2016-11-17 06:00:00', '2016-11-17 05:00:00', 0, 1082, 1020, 1);
INSERT INTO realdolmen.Flight (id, arrivalDateTime, departureDateTime, version, arrAirportId, depAirportId, partnerId) VALUES (10, '2016-11-17 06:00:00', '2016-11-17 05:00:00', 0, 1022, 1033, 1);
INSERT INTO realdolmen.Flight (id, arrivalDateTime, departureDateTime, version, arrAirportId, depAirportId, partnerId) VALUES (11, '2016-11-17 09:00:00', '2016-11-17 08:00:00', 0, 1075, 1060, 2);
INSERT INTO realdolmen.Flight (id, arrivalDateTime, departureDateTime, version, arrAirportId, depAirportId, partnerId) VALUES (12, '2016-11-17 06:00:00', '2016-11-17 05:00:00', 0, 1033, 1045, 2);
INSERT INTO realdolmen.Flight (id, arrivalDateTime, departureDateTime, version, arrAirportId, depAirportId, partnerId) VALUES (13, '2016-11-17 06:00:00', '2016-11-17 05:00:00', 0, 1002, 1094, 2);
INSERT INTO realdolmen.Flight (id, arrivalDateTime, departureDateTime, version, arrAirportId, depAirportId, partnerId) VALUES (14, '2016-11-17 06:00:00', '2016-11-17 05:00:00', 0, 1056, 1065, 2);
INSERT INTO realdolmen.Flight (id, arrivalDateTime, departureDateTime, version, arrAirportId, depAirportId, partnerId) VALUES (15, '2016-11-14 06:00:00', '2016-11-14 05:00:00', 0, 1010, 1024, 2);
INSERT INTO realdolmen.Flight (id, arrivalDateTime, departureDateTime, version, arrAirportId, depAirportId, partnerId) VALUES (16, '2016-11-17 06:00:00', '2016-11-17 05:00:00', 0, 1018, 1095, 2);
INSERT INTO realdolmen.Flight (id, arrivalDateTime, departureDateTime, version, arrAirportId, depAirportId, partnerId) VALUES (17, '2016-11-17 06:00:00', '2016-11-17 05:00:00', 0, 1042, 1017, 2);
INSERT INTO realdolmen.Flight (id, arrivalDateTime, departureDateTime, version, arrAirportId, depAirportId, partnerId) VALUES (18, '2016-11-17 06:00:00', '2016-11-17 05:00:00', 0, 1016, 1051, 2);
INSERT INTO realdolmen.Flight (id, arrivalDateTime, departureDateTime, version, arrAirportId, depAirportId, partnerId) VALUES (19, '2016-11-17 06:00:00', '2016-11-17 05:00:00', 0, 1010, 1003, 2);
INSERT INTO realdolmen.Flight (id, arrivalDateTime, departureDateTime, version, arrAirportId, depAirportId, partnerId) VALUES (20, '2016-11-17 06:00:00', '2016-11-17 05:00:00', 0, 1053, 1050, 2);

/*
Populate PricingRule
*/
INSERT INTO realdolmen.PricingRule(id, discountValue, volume, flightId) VALUES (1, 3, 4, 1);
INSERT INTO realdolmen.PricingRule(id, discountValue, volume, flightId) VALUES (2, 5, 6, 1);
INSERT INTO realdolmen.PricingRule(id, discountValue, volume, flightId) VALUES (3, 7, 8, 1);
INSERT INTO realdolmen.PricingRule(id, discountValue, volume, flightId) VALUES (4, 3, 4, 2);
INSERT INTO realdolmen.PricingRule(id, discountValue, volume, flightId) VALUES (5, 5, 6, 2);
INSERT INTO realdolmen.PricingRule(id, discountValue, volume, flightId) VALUES (6, 7, 8, 2);
INSERT INTO realdolmen.PricingRule(id, discountValue, volume, flightId) VALUES (7, 3, 4, 3);
INSERT INTO realdolmen.PricingRule(id, discountValue, volume, flightId) VALUES (8, 5, 6, 3);
INSERT INTO realdolmen.PricingRule(id, discountValue, volume, flightId) VALUES (9, 7, 8, 3);
INSERT INTO realdolmen.PricingRule(id, discountValue, volume, flightId) VALUES (10, 3, 4, 4);
INSERT INTO realdolmen.PricingRule(id, discountValue, volume, flightId) VALUES (11, 5, 6, 4);
INSERT INTO realdolmen.PricingRule(id, discountValue, volume, flightId) VALUES (12, 7, 8, 4);
INSERT INTO realdolmen.PricingRule(id, discountValue, volume, flightId) VALUES (13, 3, 4, 5);
INSERT INTO realdolmen.PricingRule(id, discountValue, volume, flightId) VALUES (14, 5, 6, 5);
INSERT INTO realdolmen.PricingRule(id, discountValue, volume, flightId) VALUES (15, 7, 8, 5);
INSERT INTO realdolmen.PricingRule(id, discountValue, volume, flightId) VALUES (16, 3, 4, 6);
INSERT INTO realdolmen.PricingRule(id, discountValue, volume, flightId) VALUES (17, 5, 6, 6);
INSERT INTO realdolmen.PricingRule(id, discountValue, volume, flightId) VALUES (18, 7, 8, 6);
INSERT INTO realdolmen.PricingRule(id, discountValue, volume, flightId) VALUES (19, 3, 4, 7);
INSERT INTO realdolmen.PricingRule(id, discountValue, volume, flightId) VALUES (20, 5, 6, 7);
INSERT INTO realdolmen.PricingRule(id, discountValue, volume, flightId) VALUES (21, 7, 8, 7);
INSERT INTO realdolmen.PricingRule(id, discountValue, volume, flightId) VALUES (22, 3, 4, 8);
INSERT INTO realdolmen.PricingRule(id, discountValue, volume, flightId) VALUES (23, 5, 6, 8);
INSERT INTO realdolmen.PricingRule(id, discountValue, volume, flightId) VALUES (24, 7, 8, 8);
INSERT INTO realdolmen.PricingRule(id, discountValue, volume, flightId) VALUES (25, 3, 4, 9);
INSERT INTO realdolmen.PricingRule(id, discountValue, volume, flightId) VALUES (26, 5, 6, 9);
INSERT INTO realdolmen.PricingRule(id, discountValue, volume, flightId) VALUES (27, 7, 8, 9);
INSERT INTO realdolmen.PricingRule(id, discountValue, volume, flightId) VALUES (28, 3, 4, 10);
INSERT INTO realdolmen.PricingRule(id, discountValue, volume, flightId) VALUES (29, 5, 6, 10);
INSERT INTO realdolmen.PricingRule(id, discountValue, volume, flightId) VALUES (30, 7, 8, 10);
INSERT INTO realdolmen.PricingRule(id, discountValue, volume, flightId) VALUES (31, 3, 4, 11);
INSERT INTO realdolmen.PricingRule(id, discountValue, volume, flightId) VALUES (32, 5, 6, 11);
INSERT INTO realdolmen.PricingRule(id, discountValue, volume, flightId) VALUES (33, 7, 8, 11);
INSERT INTO realdolmen.PricingRule(id, discountValue, volume, flightId) VALUES (34, 3, 4, 12);
INSERT INTO realdolmen.PricingRule(id, discountValue, volume, flightId) VALUES (35, 5, 6, 12);
INSERT INTO realdolmen.PricingRule(id, discountValue, volume, flightId) VALUES (36, 7, 8, 12);
INSERT INTO realdolmen.PricingRule(id, discountValue, volume, flightId) VALUES (37, 3, 4, 13);
INSERT INTO realdolmen.PricingRule(id, discountValue, volume, flightId) VALUES (38, 5, 6, 13);
INSERT INTO realdolmen.PricingRule(id, discountValue, volume, flightId) VALUES (39, 7, 8, 13);
INSERT INTO realdolmen.PricingRule(id, discountValue, volume, flightId) VALUES (40, 3, 4, 14);
INSERT INTO realdolmen.PricingRule(id, discountValue, volume, flightId) VALUES (41, 5, 6, 14);
INSERT INTO realdolmen.PricingRule(id, discountValue, volume, flightId) VALUES (42, 7, 8, 14);
INSERT INTO realdolmen.PricingRule(id, discountValue, volume, flightId) VALUES (43, 3, 4, 15);
INSERT INTO realdolmen.PricingRule(id, discountValue, volume, flightId) VALUES (44, 5, 6, 15);
INSERT INTO realdolmen.PricingRule(id, discountValue, volume, flightId) VALUES (45, 7, 8, 15);
INSERT INTO realdolmen.PricingRule(id, discountValue, volume, flightId) VALUES (46, 3, 4, 16);
INSERT INTO realdolmen.PricingRule(id, discountValue, volume, flightId) VALUES (47, 5, 6, 16);
INSERT INTO realdolmen.PricingRule(id, discountValue, volume, flightId) VALUES (48, 7, 8, 16);
INSERT INTO realdolmen.PricingRule(id, discountValue, volume, flightId) VALUES (49, 3, 4, 17);
INSERT INTO realdolmen.PricingRule(id, discountValue, volume, flightId) VALUES (50, 5, 6, 17);
INSERT INTO realdolmen.PricingRule(id, discountValue, volume, flightId) VALUES (51, 7, 8, 17);
INSERT INTO realdolmen.PricingRule(id, discountValue, volume, flightId) VALUES (52, 3, 4, 18);
INSERT INTO realdolmen.PricingRule(id, discountValue, volume, flightId) VALUES (53, 5, 6, 18);
INSERT INTO realdolmen.PricingRule(id, discountValue, volume, flightId) VALUES (54, 7, 8, 18);
INSERT INTO realdolmen.PricingRule(id, discountValue, volume, flightId) VALUES (55, 3, 4, 19);
INSERT INTO realdolmen.PricingRule(id, discountValue, volume, flightId) VALUES (56, 5, 6, 19);
INSERT INTO realdolmen.PricingRule(id, discountValue, volume, flightId) VALUES (57, 7, 8, 19);
INSERT INTO realdolmen.PricingRule(id, discountValue, volume, flightId) VALUES (58, 3, 4, 20);
INSERT INTO realdolmen.PricingRule(id, discountValue, volume, flightId) VALUES (59, 5, 6, 20);
INSERT INTO realdolmen.PricingRule(id, discountValue, volume, flightId) VALUES (60, 7, 8, 20);

/*
Populate FlightTravelCategory
*/
INSERT INTO realdolmen.FlightTravelCategory(id, commission, maximumSeats, openSeats, overruledPrice, seatPrice, travelCategory, version, flightId) VALUES (1, 10, 60, 60, 10, 50, 'REGULAR', 0, 1);
INSERT INTO realdolmen.FlightTravelCategory(id, commission, maximumSeats, openSeats, overruledPrice, seatPrice, travelCategory, version, flightId) VALUES (2, 10, 60, 60, 0, 100, 'PREMIUM', 0, 1);
INSERT INTO realdolmen.FlightTravelCategory(id, commission, maximumSeats, openSeats, overruledPrice, seatPrice, travelCategory, version, flightId) VALUES (3, 10, 60, 60, 10, 50, 'REGULAR', 0, 2);
INSERT INTO realdolmen.FlightTravelCategory(id, commission, maximumSeats, openSeats, overruledPrice, seatPrice, travelCategory, version, flightId) VALUES (4, 10, 60, 60, 0, 100, 'PREMIUM', 0, 2);  
INSERT INTO realdolmen.FlightTravelCategory(id, commission, maximumSeats, openSeats, overruledPrice, seatPrice, travelCategory, version, flightId) VALUES (5, 10, 60, 60, 10, 50, 'REGULAR', 0, 3);
INSERT INTO realdolmen.FlightTravelCategory(id, commission, maximumSeats, openSeats, overruledPrice, seatPrice, travelCategory, version, flightId) VALUES (6, 10, 60, 60, 0, 100, 'PREMIUM', 0, 3);  
INSERT INTO realdolmen.FlightTravelCategory(id, commission, maximumSeats, openSeats, overruledPrice, seatPrice, travelCategory, version, flightId) VALUES (7, 10, 60, 60, 10, 50, 'REGULAR', 0, 4);
INSERT INTO realdolmen.FlightTravelCategory(id, commission, maximumSeats, openSeats, overruledPrice, seatPrice, travelCategory, version, flightId) VALUES (8, 10, 60, 60, 0, 100, 'PREMIUM', 0, 4);  
INSERT INTO realdolmen.FlightTravelCategory(id, commission, maximumSeats, openSeats, overruledPrice, seatPrice, travelCategory, version, flightId) VALUES (9, 10, 60, 60, 10, 50, 'REGULAR', 0, 5);
INSERT INTO realdolmen.FlightTravelCategory(id, commission, maximumSeats, openSeats, overruledPrice, seatPrice, travelCategory, version, flightId) VALUES (10, 10, 60, 60, 0, 100, 'PREMIUM', 0, 5);  
INSERT INTO realdolmen.FlightTravelCategory(id, commission, maximumSeats, openSeats, overruledPrice, seatPrice, travelCategory, version, flightId) VALUES (11, 10, 60, 60, 10, 50, 'REGULAR', 0, 6);
INSERT INTO realdolmen.FlightTravelCategory(id, commission, maximumSeats, openSeats, overruledPrice, seatPrice, travelCategory, version, flightId) VALUES (12, 10, 60, 60, 0, 100, 'PREMIUM', 0, 6);  
INSERT INTO realdolmen.FlightTravelCategory(id, commission, maximumSeats, openSeats, overruledPrice, seatPrice, travelCategory, version, flightId) VALUES (13, 10, 60, 60, 10, 50, 'REGULAR', 0, 7);
INSERT INTO realdolmen.FlightTravelCategory(id, commission, maximumSeats, openSeats, overruledPrice, seatPrice, travelCategory, version, flightId) VALUES (14, 10, 60, 60, 0, 100, 'PREMIUM', 0, 7);  
INSERT INTO realdolmen.FlightTravelCategory(id, commission, maximumSeats, openSeats, overruledPrice, seatPrice, travelCategory, version, flightId) VALUES (15, 10, 60, 60, 10, 50, 'REGULAR', 0, 8);
INSERT INTO realdolmen.FlightTravelCategory(id, commission, maximumSeats, openSeats, overruledPrice, seatPrice, travelCategory, version, flightId) VALUES (16, 10, 60, 60, 0, 100, 'PREMIUM', 0, 8);  
INSERT INTO realdolmen.FlightTravelCategory(id, commission, maximumSeats, openSeats, overruledPrice, seatPrice, travelCategory, version, flightId) VALUES (17, 10, 60, 60, 10, 50, 'REGULAR', 0, 9);
INSERT INTO realdolmen.FlightTravelCategory(id, commission, maximumSeats, openSeats, overruledPrice, seatPrice, travelCategory, version, flightId) VALUES (18, 10, 60, 60, 0, 100, 'PREMIUM', 0, 9);  
INSERT INTO realdolmen.FlightTravelCategory(id, commission, maximumSeats, openSeats, overruledPrice, seatPrice, travelCategory, version, flightId) VALUES (19, 10, 60, 60, 10, 50, 'REGULAR', 0, 10);
INSERT INTO realdolmen.FlightTravelCategory(id, commission, maximumSeats, openSeats, overruledPrice, seatPrice, travelCategory, version, flightId) VALUES (20, 10, 60, 60, 0, 100, 'PREMIUM', 0, 10);  
INSERT INTO realdolmen.FlightTravelCategory(id, commission, maximumSeats, openSeats, overruledPrice, seatPrice, travelCategory, version, flightId) VALUES (21, 10, 60, 60, 10, 50, 'REGULAR', 0, 11);
INSERT INTO realdolmen.FlightTravelCategory(id, commission, maximumSeats, openSeats, overruledPrice, seatPrice, travelCategory, version, flightId) VALUES (22, 10, 60, 60, 0, 100, 'PREMIUM', 0, 11);  
INSERT INTO realdolmen.FlightTravelCategory(id, commission, maximumSeats, openSeats, overruledPrice, seatPrice, travelCategory, version, flightId) VALUES (23, 10, 60, 60, 10, 50, 'REGULAR', 0, 12);
INSERT INTO realdolmen.FlightTravelCategory(id, commission, maximumSeats, openSeats, overruledPrice, seatPrice, travelCategory, version, flightId) VALUES (24, 10, 60, 60, 0, 100, 'PREMIUM', 0, 12);  
INSERT INTO realdolmen.FlightTravelCategory(id, commission, maximumSeats, openSeats, overruledPrice, seatPrice, travelCategory, version, flightId) VALUES (25, 10, 60, 60, 10, 50, 'REGULAR', 0, 13);
INSERT INTO realdolmen.FlightTravelCategory(id, commission, maximumSeats, openSeats, overruledPrice, seatPrice, travelCategory, version, flightId) VALUES (26, 10, 60, 60, 0, 100, 'PREMIUM', 0, 13);  
INSERT INTO realdolmen.FlightTravelCategory(id, commission, maximumSeats, openSeats, overruledPrice, seatPrice, travelCategory, version, flightId) VALUES (27, 10, 60, 60, 10, 50, 'REGULAR', 0, 14);
INSERT INTO realdolmen.FlightTravelCategory(id, commission, maximumSeats, openSeats, overruledPrice, seatPrice, travelCategory, version, flightId) VALUES (28, 10, 60, 60, 0, 100, 'PREMIUM', 0, 14);  
INSERT INTO realdolmen.FlightTravelCategory(id, commission, maximumSeats, openSeats, overruledPrice, seatPrice, travelCategory, version, flightId) VALUES (29, 10, 60, 60, 10, 50, 'REGULAR', 0, 15);
INSERT INTO realdolmen.FlightTravelCategory(id, commission, maximumSeats, openSeats, overruledPrice, seatPrice, travelCategory, version, flightId) VALUES (30, 10, 60, 60, 0, 100, 'PREMIUM', 0, 15);  
INSERT INTO realdolmen.FlightTravelCategory(id, commission, maximumSeats, openSeats, overruledPrice, seatPrice, travelCategory, version, flightId) VALUES (31, 10, 60, 60, 10, 50, 'REGULAR', 0, 16);
INSERT INTO realdolmen.FlightTravelCategory(id, commission, maximumSeats, openSeats, overruledPrice, seatPrice, travelCategory, version, flightId) VALUES (32, 10, 60, 60, 0, 100, 'PREMIUM', 0, 16);  
INSERT INTO realdolmen.FlightTravelCategory(id, commission, maximumSeats, openSeats, overruledPrice, seatPrice, travelCategory, version, flightId) VALUES (33, 10, 60, 60, 10, 50, 'REGULAR', 0, 17);
INSERT INTO realdolmen.FlightTravelCategory(id, commission, maximumSeats, openSeats, overruledPrice, seatPrice, travelCategory, version, flightId) VALUES (34, 10, 60, 60, 0, 100, 'PREMIUM', 0, 17);  
INSERT INTO realdolmen.FlightTravelCategory(id, commission, maximumSeats, openSeats, overruledPrice, seatPrice, travelCategory, version, flightId) VALUES (35, 10, 60, 60, 10, 50, 'REGULAR', 0, 18);
INSERT INTO realdolmen.FlightTravelCategory(id, commission, maximumSeats, openSeats, overruledPrice, seatPrice, travelCategory, version, flightId) VALUES (36, 10, 60, 60, 0, 100, 'PREMIUM', 0, 18);  
INSERT INTO realdolmen.FlightTravelCategory(id, commission, maximumSeats, openSeats, overruledPrice, seatPrice, travelCategory, version, flightId) VALUES (37, 10, 60, 60, 10, 50, 'REGULAR', 0, 19);
INSERT INTO realdolmen.FlightTravelCategory(id, commission, maximumSeats, openSeats, overruledPrice, seatPrice, travelCategory, version, flightId) VALUES (38, 10, 60, 60, 0, 100, 'PREMIUM', 0, 19);  
INSERT INTO realdolmen.FlightTravelCategory(id, commission, maximumSeats, openSeats, overruledPrice, seatPrice, travelCategory, version, flightId) VALUES (39, 10, 60, 60, 10, 50, 'REGULAR', 0, 20);
INSERT INTO realdolmen.FlightTravelCategory(id, commission, maximumSeats, openSeats, overruledPrice, seatPrice, travelCategory, version, flightId) VALUES (40, 10, 60, 60, 0, 100, 'PREMIUM', 0, 20); 

/*
Populate Booking
*/
INSERT INTO realdolmen.Booking (id,bookingDateTime,bookingStatus,finalPrice,paymentType,purchasePrice,travelCategory,version,flightId,userId) VALUES (1,'2016-11-16 11:47:33','PAID',55.29,'CREDIT_CARD',48.5,'REGULAR',0,1,1);
INSERT INTO realdolmen.Booking (id,bookingDateTime,bookingStatus,finalPrice,paymentType,purchasePrice,travelCategory,version,flightId,userId) VALUES (2,'2016-11-16 11:47:33','PAID',55.29,'CREDIT_CARD',48.5,'REGULAR',0,1,null);
INSERT INTO realdolmen.Booking (id,bookingDateTime,bookingStatus,finalPrice,paymentType,purchasePrice,travelCategory,version,flightId,userId) VALUES (3,'2016-11-16 11:47:33','PAID',55.29,'CREDIT_CARD',48.5,'REGULAR',0,1,null);
INSERT INTO realdolmen.Booking (id,bookingDateTime,bookingStatus,finalPrice,paymentType,purchasePrice,travelCategory,version,flightId,userId) VALUES (4,'2016-11-16 11:47:33','PAID',55.29,'CREDIT_CARD',48.5,'REGULAR',0,1,null);
INSERT INTO realdolmen.Booking (id,bookingDateTime,bookingStatus,finalPrice,paymentType,purchasePrice,travelCategory,version,flightId,userId) VALUES (5,'2016-11-16 11:47:33','PAID',55.29,'CREDIT_CARD',48.5,'REGULAR',0,1,null);
INSERT INTO realdolmen.Booking (id,bookingDateTime,bookingStatus,finalPrice,paymentType,purchasePrice,travelCategory,version,flightId,userId) VALUES (6,'2016-11-16 11:47:33','PAID',104.5,'CREDIT_CARD',100,'PREMIUM',0,1,null);
INSERT INTO realdolmen.Booking (id,bookingDateTime,bookingStatus,finalPrice,paymentType,purchasePrice,travelCategory,version,flightId,userId) VALUES (7,'2016-11-16 11:48:11','UNPAID',60,'ENDORSED',50,'REGULAR',0,2,null);
INSERT INTO realdolmen.Booking (id,bookingDateTime,bookingStatus,finalPrice,paymentType,purchasePrice,travelCategory,version,flightId,userId) VALUES (8,'2016-11-16 11:48:11','UNPAID',60,'ENDORSED',50,'REGULAR',0,2,null);
INSERT INTO realdolmen.Booking (id,bookingDateTime,bookingStatus,finalPrice,paymentType,purchasePrice,travelCategory,version,flightId,userId) VALUES (9,'2016-11-16 11:48:11','UNPAID',110,'ENDORSED',100,'PREMIUM',0,2,null);
INSERT INTO realdolmen.Booking (id,bookingDateTime,bookingStatus,finalPrice,paymentType,purchasePrice,travelCategory,version,flightId,userId) VALUES (10,'2016-11-16 11:48:50','PAID',57,'CREDIT_CARD',50,'REGULAR',0,2,null);
INSERT INTO realdolmen.Booking (id,bookingDateTime,bookingStatus,finalPrice,paymentType,purchasePrice,travelCategory,version,flightId,userId) VALUES (11,'2016-11-16 11:49:33','PAID',57,'CREDIT_CARD',50,'REGULAR',0,4,null);
INSERT INTO realdolmen.Booking (id,bookingDateTime,bookingStatus,finalPrice,paymentType,purchasePrice,travelCategory,version,flightId,userId) VALUES (12,'2016-11-16 11:49:33','PAID',57,'CREDIT_CARD',50,'REGULAR',0,4,null);
INSERT INTO realdolmen.Booking (id,bookingDateTime,bookingStatus,finalPrice,paymentType,purchasePrice,travelCategory,version,flightId,userId) VALUES (13,'2016-11-16 11:49:33','PAID',57,'CREDIT_CARD',50,'REGULAR',0,4,null);
INSERT INTO realdolmen.Booking (id,bookingDateTime,bookingStatus,finalPrice,paymentType,purchasePrice,travelCategory,version,flightId,userId) VALUES (14,'2016-11-16 11:49:33','PAID',57,'CREDIT_CARD',50,'REGULAR',0,4,null);
INSERT INTO realdolmen.Booking (id,bookingDateTime,bookingStatus,finalPrice,paymentType,purchasePrice,travelCategory,version,flightId,userId) VALUES (15,'2016-11-16 11:49:33','PAID',104.5,'CREDIT_CARD',100,'PREMIUM',0,4,1);
INSERT INTO realdolmen.Booking (id,bookingDateTime,bookingStatus,finalPrice,paymentType,purchasePrice,travelCategory,version,flightId,userId) VALUES (16,'2016-11-16 11:56:39','PAID',54.15,'CREDIT_CARD',47.5,'REGULAR',0,9,null);
INSERT INTO realdolmen.Booking (id,bookingDateTime,bookingStatus,finalPrice,paymentType,purchasePrice,travelCategory,version,flightId,userId) VALUES (17,'2016-11-16 11:56:39','PAID',54.15,'CREDIT_CARD',47.5,'REGULAR',0,9,null);
INSERT INTO realdolmen.Booking (id,bookingDateTime,bookingStatus,finalPrice,paymentType,purchasePrice,travelCategory,version,flightId,userId) VALUES (18,'2016-11-16 11:56:39','PAID',54.15,'CREDIT_CARD',47.5,'REGULAR',0,9,null);
INSERT INTO realdolmen.Booking (id,bookingDateTime,bookingStatus,finalPrice,paymentType,purchasePrice,travelCategory,version,flightId,userId) VALUES (19,'2016-11-16 11:56:39','PAID',54.15,'CREDIT_CARD',47.5,'REGULAR',0,9,null);
INSERT INTO realdolmen.Booking (id,bookingDateTime,bookingStatus,finalPrice,paymentType,purchasePrice,travelCategory,version,flightId,userId) VALUES (20,'2016-11-16 11:56:39','PAID',54.15,'CREDIT_CARD',47.5,'REGULAR',0,9,null);
INSERT INTO realdolmen.Booking (id,bookingDateTime,bookingStatus,finalPrice,paymentType,purchasePrice,travelCategory,version,flightId,userId) VALUES (21,'2016-11-16 11:56:39','PAID',54.15,'CREDIT_CARD',47.5,'REGULAR',0,9,null);
INSERT INTO realdolmen.Booking (id,bookingDateTime,bookingStatus,finalPrice,paymentType,purchasePrice,travelCategory,version,flightId,userId) VALUES (22,'2016-11-16 11:56:39','PAID',54.15,'CREDIT_CARD',47.5,'REGULAR',0,9,null);
INSERT INTO realdolmen.Booking (id,bookingDateTime,bookingStatus,finalPrice,paymentType,purchasePrice,travelCategory,version,flightId,userId) VALUES (23,'2016-11-16 11:57:15','UNPAID',60,'ENDORSED',50,'REGULAR',0,19,null);
INSERT INTO realdolmen.Booking (id,bookingDateTime,bookingStatus,finalPrice,paymentType,purchasePrice,travelCategory,version,flightId,userId) VALUES (24,'2016-11-16 11:57:15','UNPAID',60,'ENDORSED',50,'REGULAR',0,19,null);
INSERT INTO realdolmen.Booking (id,bookingDateTime,bookingStatus,finalPrice,paymentType,purchasePrice,travelCategory,version,flightId,userId) VALUES (25,'2016-11-16 11:57:15','UNPAID',104.5,'ENDORSED',95,'PREMIUM',0,19,null);
INSERT INTO realdolmen.Booking (id,bookingDateTime,bookingStatus,finalPrice,paymentType,purchasePrice,travelCategory,version,flightId,userId) VALUES (26,'2016-11-16 11:57:15','UNPAID',104.5,'ENDORSED',95,'PREMIUM',0,19,null);
INSERT INTO realdolmen.Booking (id,bookingDateTime,bookingStatus,finalPrice,paymentType,purchasePrice,travelCategory,version,flightId,userId) VALUES (27,'2016-11-16 11:57:15','UNPAID',104.5,'ENDORSED',95,'PREMIUM',0,19,null);
INSERT INTO realdolmen.Booking (id,bookingDateTime,bookingStatus,finalPrice,paymentType,purchasePrice,travelCategory,version,flightId,userId) VALUES (28,'2016-11-16 11:57:15','UNPAID',104.5,'ENDORSED',95,'PREMIUM',0,19,null);
INSERT INTO realdolmen.Booking (id,bookingDateTime,bookingStatus,finalPrice,paymentType,purchasePrice,travelCategory,version,flightId,userId) VALUES (29,'2016-11-16 11:57:15','UNPAID',104.5,'ENDORSED',95,'PREMIUM',0,19,null);
INSERT INTO realdolmen.Booking (id,bookingDateTime,bookingStatus,finalPrice,paymentType,purchasePrice,travelCategory,version,flightId,userId) VALUES (30,'2016-11-16 11:57:15','UNPAID',104.5,'ENDORSED',95,'PREMIUM',0,19,null);
INSERT INTO realdolmen.Booking (id,bookingDateTime,bookingStatus,finalPrice,paymentType,purchasePrice,travelCategory,version,flightId,userId) VALUES (31,'2016-11-16 11:57:15','UNPAID',104.5,'ENDORSED',95,'PREMIUM',0,19,null);