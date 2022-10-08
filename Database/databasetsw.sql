CREATE DATABASE  IF NOT EXISTS `databasetsw` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `databasetsw`;
-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: databasetsw
-- ------------------------------------------------------
-- Server version	8.0.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `appartenenza`
--

DROP TABLE IF EXISTS `appartenenza`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `appartenenza` (
  `idarticolo` int NOT NULL,
  `idcategoria` int NOT NULL,
  PRIMARY KEY (`idarticolo`,`idcategoria`),
  KEY `idcategoria_idx` (`idcategoria`),
  CONSTRAINT `idarticolo` FOREIGN KEY (`idarticolo`) REFERENCES `articolo` (`idarticolo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `idcategoria` FOREIGN KEY (`idcategoria`) REFERENCES `categoria` (`idcategoria`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appartenenza`
--

LOCK TABLES `appartenenza` WRITE;
/*!40000 ALTER TABLE `appartenenza` DISABLE KEYS */;
INSERT INTO `appartenenza` VALUES (7,1),(15,1),(1,2),(2,2),(3,2),(4,2),(5,2),(6,2),(7,2),(8,2),(9,2),(10,2),(11,2),(12,2),(13,2),(14,2),(15,2),(16,2),(17,2),(18,2),(19,2),(20,2),(21,2),(22,2),(23,2),(27,2),(28,2),(29,2),(30,2),(37,4),(38,4),(43,4),(44,4),(45,4),(46,4),(47,4),(48,4),(49,4),(50,4),(31,5),(32,5),(33,5),(34,5),(35,5),(36,5),(37,5),(38,5),(39,5),(40,5),(41,5),(42,5),(49,5),(50,5),(51,6),(52,6),(53,6),(54,6),(55,6),(56,6),(57,6),(58,6),(6,7),(7,7),(17,7),(18,7),(40,7),(42,7),(43,7),(44,7),(45,7),(46,7),(47,7),(48,7),(2,8),(3,8),(5,8),(11,8),(12,8),(13,8),(14,8),(19,8),(20,8),(22,8),(1,9),(3,9),(4,9),(8,9),(9,9),(10,9),(21,9),(23,12),(27,12),(28,12),(29,12),(30,12),(20,13),(27,13),(28,13),(29,13),(6,14),(7,14),(15,14),(16,14),(17,14),(18,14),(1,15),(37,15),(38,15),(49,15),(50,15),(55,15),(31,16),(32,16),(33,16),(34,16),(35,16),(37,16),(39,16),(40,16),(41,16),(54,16),(55,16),(56,16),(57,16),(58,16);
/*!40000 ALTER TABLE `appartenenza` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `articolo`
--

DROP TABLE IF EXISTS `articolo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `articolo` (
  `idarticolo` int NOT NULL AUTO_INCREMENT,
  `Nome` varchar(300) NOT NULL,
  `Prezzo` decimal(10,2) NOT NULL,
  `Descrizione` varchar(800) NOT NULL,
  `Marca` varchar(45) NOT NULL,
  `Quantita` int NOT NULL,
  `Materiale` varchar(45) NOT NULL,
  `Iva` int NOT NULL,
  `Unita` int DEFAULT NULL,
  PRIMARY KEY (`idarticolo`),
  KEY `unita2_idx` (`Unita`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articolo`
--

LOCK TABLES `articolo` WRITE;
/*!40000 ALTER TABLE `articolo` DISABLE KEYS */;
INSERT INTO `articolo` VALUES (1,'Area di caduta Salto con l\'Asta completa di materassina anti chiodi',8902.00,'Area di caduta salto in con l\'asta, composta da materassi di ammortizzazione in schiuma poliuretanica espansa(Pie dello spessore di 70 cm (+10 cm di materassina anti-chiodi), e rivestiti in PVC antistrappo, con peso 650gr/m','Athletix',8000,'PVC 650gr/m',22,1),(2,'Area di caduta Salto in Alto completa di materassina anti chiodi',4900.00,'Area di caduta salto in alto completa di materassina anti-chiodi.','Athletix',100,'PVC 650gr/m',22,1),(3,'Asticella segnalimite anti trauma per Salto in Alto e Salto con l\'Asta',10.90,'Asticella segnalimite anti trauma per ritti salto in alto e salto con l\'asta.','Athletix',40,'Poliuretano',22,1),(4,'Asticella segnalimite per Salto con l\'Asta da MT 4,5 Omologata IAAF',149.00,'Asticella segnalimite in fibra di vetro per ritti salto con l\'asta omologata IAAF da metri 4,5','Athletix',80,'Fibra di Vetro',22,1),(5,'Asticella segnalimite per Salto in Alto da MT 4 Omologata IAAF',149.00,'Asticella segnalimite in fibra di vetro per ritti salto in alto omologata IAAF da metri 4.','Athletix',90,'Fibra di Vetro',22,1),(6,'Blocco di partenza Olimpico modello Mark I approvato IAAF',149.00,'Blocco di partenza olimpionico modello Mark I approvato IAAF','Vinex',120,'Alluminio',22,1),(7,'Blocco di partenza Olimpico modello PRO-new approvato IAAF',199.00,'Certificato IAAF questo blocco di partenza professionale ha un binario centrale da 102 mm in alluminio pesante con scanalature e visualizzazione al centimetro della posizione dei due blocchi.','Vinex',140,'Alluminio',22,1),(8,'Cassetta per Imbucata per Area di caduta Salto con l\'asta Omologata IAAF',219.00,'Cassetta per imbucata, per area di caduta salto con l\'asta, omologata IAAF.','Vinex',25,'Acciaio inox',22,1),(9,'Coppia Ritti Salto con l\'asta modello Competion Omologati IAAF',2490.00,'Coppia di Ritti per Salto con l\'asta modello Competition Omologati IAAF','Vinex',63,'Alluminio',22,1),(10,'Coppia Ritti Salto con l\'asta modello Olympic',3500.00,'Coppia di ritti per area di caduta salto con l\'asta modello Olimpionico.','Athletix',84,'Acciaio',22,1),(11,'Coppia Ritti Salto in Alto modello Club',219.00,'Coppia Ritti Salto in Alto modello Club','Vinex',80,'Alluminio',22,1),(12,'Coppia Ritti Salto in Alto modello Competition Omologati IAAF',299.00,'Coppia Ritti Salto in Alto modello Competition, Omologati IAAF.','Vinex',350,'Acciaio',22,1),(13,'Coppia Ritti Salto in Alto modello Olympic Omologati IAAF',849.00,'Coppia di Ritti per Salto in alto in alluminio, modello Olympic, omologati IAAF.','Vinex',877,'Alluminio',22,1),(14,'Coppia Ritti Salto in Alto modello Supreme Omologati IAAF',350.00,'Coppia Ritti Salto in Alto modello Supreme, Omologati IAAF.','Vinex',120,'Alluminio',22,1),(15,'Ostacolo graduabile cm 76,2 - 84 - 91,4',95.00,'Ostacolo Graduabile da allenamento cm 76,2- 84- 91,4 con base in acciaio zincato e perno di blocco altezza verniciato.','Gana Sport',84,'Acciaio Zincato',22,1),(16,'Ostacolo olimpionico da gara graduabile su cinque altezze certificato IAAF',199.00,'Ostacolo Olimpionico da gara graduabile su cinque altezze.','Vinex',182,'Acciaio Zincato',22,1),(17,'Ostacolo over per allenamento altezza cm 10',18.90,'Ostacolo over per allenamento altezza cm. 10 e larghezza cm 82, realizzato in acciaio tubolare.','Gana Sport',324,'Acciaio Spessore 2mm',22,1),(18,'Ostacolo Training in alluminio regolabile su 5 altezze',64.90,'Ostacolo regolamentare per atletica leggera, in alluminio regolabile su 5 altezze da cm 76.2 a cm 106.7','Vinex',751,'Alluminio',22,1),(19,'Pedana di sostegno per Materassi ed Aree di Caduta in acciaio verniciato',45.00,'Pedana di sostegno per Materassi ed aree di caduta salto, realizzata in acciaio verniciato a polveri','Gana Sport',450,'Acciaio',22,1),(20,'Pedana di sostegno per Materassi ed Aree di Caduta in acciaio zincato',60.00,'Pedana di sostegno per Materassi ed aree di caduta salto, realizzata in acciaio zincato.','Gana Sport',120,'Acciaio Zincato',22,1),(21,'Telo di copertura Antipioggia per Area di Caduta Salto con l\'Asta',1100.00,'Telo di copertura antipioggia, per Area di Caduta Salto con l\'asta.','Gana Sport',520,'PVC',22,1),(22,'Telo di copertura Antipioggia per Area di Caduta Salto in Alto',590.00,'Telo di copertura antipioggia, per Area di Caduta Salto in alto. ','Gana Sport',78,'PVC',22,1),(23,'Vortex per allenamento al lancio del giavellotto',15.90,'Tra il gioco e l\'attrezzo sportivo il Vortex utilizzato nelle gare di atletica come propedeutico al lancio del giavellotto, fino all\'etaÂ di 14 anni','World Athletics',90,'Foam',22,1),(27,'Gabbia di protezione per lancio del disco (e martello) di acciaio zincato, altezza mt 7',10185.00,'Gabbia per lancio del disco e del martello, regolamentare secondo norme FIDAL. Struttura di acciaio zincata a caldo, da fissare a terra mediante bussole da interrare, montanti in tubolare a grossa sezione muniti di controventature rigide. Altezza mt 7, completa di portelloni mobili altezza mt 9 e rete di nylon, diametro mm 4, maglia 4x4. ','DiNa Forniture',5,'Acciaio Zincato',22,1),(28,'Gabbia Lancio del Disco DiMa Sport',9015.00,'Gabbia di protezione per il lancio del disco realizzata in acciaio zincato; composta da 3 montanti a forca di sezione mm. 70x70 di altezza fuori terra di mt. 4,50; e 4 montanti a forca di sezione mm.70x70 di altezza fuori terra mt.6,40;  7 bussole da cementare in acciaio zincato; 1 rete in polipropilene di colore nero con filo da mm.4 ad alta tenacitÃƒÆ’Ã†â€™Ãƒâ€ Ã¢â‚¬â„¢ÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â  a maglia quadra da mm. 40x40 certificata EN1263-1 di dimensioni mt. 26 x 5 agganciata alla struttura in modo da non essere tesa per la sicurezza degli atleti ; 7 cavi in nylon di lunghezza mt. 10 per l\'issaggio della rete da terra. Distanza della rete dai pali di sostegno  cm.40. Omologata W.A. con certificazione n. E-19-0996.','DiMa Sport',10,'Nylon',22,1),(29,'Gabbia Lancio del Disco Polanik',16041.00,'Gabbia di protezione per il lancio del disco realizzata in alluminio, con sistema di montaggio mediante contropiastre da cementare e innalzamento dei pali mediante sistema a cerniera e quindi senza l\'ausilio di trabbattelli, gru o ponteggi. L\'altezza della gabbia e\' di mt. 5 e monta una rete di protezione di colore verde antiriflesso di alta qualita\' sistema di innalzamento della rete mediante argani meccanici e cavi in acciaio con movimento a manovella. ','Polanik',5,'Acciaio',22,1),(30,'Gabbia Lancio del Disco MT.5/7 WA 2020 Polanik',16980.00,'Gabbia di protezione per il lancio del disco realizzata in alluminio, con sistema di montaggio mediante contropiastre da cementare ( CONTROPIASTRE ESCLUSE) e innalzamento dei pali mediante sistema a cerniera e quindi senza l\'ausilio di trabbattelli, gru o ponteggi. La gabbia e\' composta da n. 5 pali alti mt. 5 e da 4 pali alti mt.7 secondo quanto previsto dalla IAAF a partire dal 2020; rete di protezione di colore verde antiriflesso di alta qualita\' sistema di innalzamento della rete mediante argani meccanici e cavi in acciaio con movimento a manovella. Omologata IAAF con certificazione n. E-17-0943.','Polanik',3,'Acciaio Zincato',22,1),(31,'Leggings donna fitness Adidas LINEAR cotone leggero vita alta neri',20.00,'Questi leggings vita alta molto confortevoli sono l\'ideale per tutte le tue sedute di fitness!','Adidas',600,'Cotone',22,1),(32,'Leggings donna fitness 100 cotone leggero blu',6.00,'I leggings basic per eccellenza. Questo prodotto disegnato dai nostri stilisti avra un posto d\'onore nel tuo abbigliamento sportivo... o per la vita quotidiana.','Domyos',600,'Cotone',22,1),(33,'Pantaloni donna fitness FIT+ 500 regular cotone leggero grigi',10.00,'Disegnati dai nostri stilisti per lo sport e per la vita quotidiana, questi leggings confortevoli hanno linee in movimento grazie ad un super taglio diritto!','Domyos',600,'Cotone',22,1),(34,'Softshell montagna donna MT500 WINDWARM nero',45.00,'Appassionati di trekking in montagna, i nostri ideatori hanno pensato a questo softshell per permetterti di far fronte alle mutevoli condizioni meteo.','Forclaz',600,'Cotone sintetico',22,1),(35,'T-shirt donna fitness 500 regular misto cotone nera',5.00,'I nostri stilisti hanno disegnato questo capo basic, IL protagonista di una tenuta sia per lo sport sia per la vita quotidiana: la t-shirt, ovviamente!','Domyos',600,'Cotone',22,1),(36,'Calze running RUN100 bianche x3',4.00,'Le nostre equipe hanno sviluppato queste calze per fare in modo che il piede sia protetto dalle vesciche durante le uscite di running.','Kiprun',500,'Cotone',22,1),(37,'Cappellino basket adulto NEW ERA 9FORTY BOSTON CELTICS verde',22.00,'New Era ha ideato questo cappellino per tutti i fan dei Celtics di Boston. La linguetta posteriore permette una regolazione sempre perfetta!','New Era',600,'Cotone',22,1),(38,'Cappellino basket adulto NBA NEW ERA 9FORTY LOS ANGELES LAKERS nero-bianco',22.00,'New Era ha ideato questo cappellino per tutti i fan dei Lakers di Los Angeles. La linguetta posteriore permette una regolazione sempre perfetta!','New Era',600,'Cotone',22,1),(39,'Calze montagna adulto Hike 50 High | 2 paia | grigie',5.00,'I nostri ideatori hanno progettato queste calze ecologiche per le tue escursioni e la vita di tutti i giorni.','Quechua',500,'Cotone',22,1),(40,'Scarpe camminata sportiva donna SKECHERS FLEX APPEAL blu-rosa',35.00,'Questa scarpa indicata per la camminata sportiva.','Sketchers',1100,'Nylon',22,1),(41,'Scarpe camminata donna ADIDAS GD COURT BASE bianco-argento',37.00,'Questa scarpa indicata per la camminata, per un uso regolare.','Adidas',1100,'Cotone',22,1),(42,'Scarpe running donna Asics GEL WINDHAWK 4 grigio-rosa',40.00,'L\'ideale per le runner con falcata neutra, preferibilmente per un uso occasionale.','Asics',1100,'Cotone',22,1),(43,'Gilet antivento montagna uomo TREK100 WIND nero',25.00,'I nostri ideatori, appassionati di montagna e trekking, hanno sviluppato questo gilet per permetterti di camminare in montagna, quando tira vento.','Quechua',600,'Poliestere',22,1),(44,'Pantaloni uomo fitness 120 regular cotone jersey neri',8.00,'Pantaloni trendy e leggeri da portare per lo sport e la vita quotidiana? Sfida accettata dai nostri stilisti che hanno disegnato questi pantaloni taglio diritto','Domyos',600,'Cotone',22,1),(45,'T-shirt nera uomo fitness SPORTEE 100 regular cotone',2.50,'I nostri stilisti non potevano farne a meno: la t-shirt girocollo 100% cotone maniche corte. Un capo basic per lo sport e per la vita quotidiana!','Domyos',600,'Cotone',22,1),(46,'Short calcio adulto PARMA neri',10.00,'Short da calcio leggeri che tengono all\'asciutto in campo.','Adidas',600,'Cotone',22,1),(47,'Scarpe running uomo Asics GEL WINDHAWK blu-bianco',40.00,'L\'ideale per i runner con falcata neutra, preferibilmente per un uso occasionale.','Adidas',1100,'Cotone',22,1),(48,'Scarpe running uomo RUN 100 grigie',13.00,'Le nostre equipe hanno sviluppato queste scarpe da running leggere e ammortizzanti per le tue uscite fino a 10 km alla settimana.','Kalenji',1100,'Cotone rivestito',22,1),(49,'Borsa ESSENTIAL 20L blu',5.00,'Abbiamo ideato questa borsa a tracolla per trasportare il materiale degli sportivi. Facilmente ripiegabile nella sua tasca!','Kipsta',250,'Cotone',22,1),(50,'Borsa Puma nera',25.00,'Questa borsa pratica e confortevole sara\'Â  il tuo partner ideale per gli allenamenti grazie alla sua capienza e alla sua modularita\'','Puma',250,'Cotone',22,1),(51,'T-shirt atletica bambino AT 300 COMFORT azzurra',12.00,'I nostri ideatori appassionati di atletica hanno sviluppato questa t-shirt per i giovani sportivi. L\'ideale per correre, in estate e in inverno!','Kiprun',600,'Cotone',22,1),(52,'T-shirt bambino ginnastica AT 100 traspirante bianca',3.00,'I nostri ideatori appassionati di atletica hanno sviluppato questa t-shirt per i bambini. Per correre,saltare,lanciare,con tempo caldo,in pista o a scuola.','Basic',600,'Cotone',22,1),(53,'T-shirt atletica bambino personalizzabile giallo fluo',5.00,'I nostri ideatori hanno sviluppato questa t-shirt personalizzabile per i bambini che praticano atletica in un club e che vogliono una t-shirt che somigli loro.','Kalenji',600,'Cotone',22,1),(54,'Maglia manica lunga atletica bambina AT 500 SKINCARE verde-rosa',15.00,'Trovi che fare atletica in inverno sia terribile? Grazie alla lavorazione senza cuciture, al taglio ed al materiale di questa t-shirt il tuo bambino potra\' fare sport senza temere il freddo!','Kiprun',600,'Cotone',22,1),(55,'Sacca porta-scarpe LIGHT 15 litri giallo-blu',2.00,'Abbiamo sviluppato la sacca Light per facilitare il trasporto delle scarpe o del pallone. Prodotto ultracompatto, una volta chiuso non occupa spazio.','Kipsta',250,'Plastica',22,1),(56,'Calze corte atletica bambino AT100 MID bianche x3',2.00,'Per iniziare a praticare atletica, in pista o a scuola, queste calze sottili ed aerate principalmente in cotone sono l\'ideale per i giovani atleti che vogliono correre, saltare o lanciare','Kiprun',200,'Cotone',22,1),(57,'Scarpe ginnastica bambino AT EASY rosso-grigio',16.00,'Le scarpe AT EASY sono ideate per accompagnare i bambini e le bambine nelle loro attivita\' sportive quotidiane.','Basic',700,'Foam e Plastica',22,1),(58,'Scarpe atletica bambina Asics GEL EXCITE rosa',39.00,'Queste scarpe sono state ideate per rispondere alle necessita\' delle giovani atlete in fase di apprendimento, in pista o a scuola.','Asics',700,'Foam e Plastica',22,1);
/*!40000 ALTER TABLE `articolo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carrello`
--

DROP TABLE IF EXISTS `carrello`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carrello` (
  `idcarrello` int NOT NULL AUTO_INCREMENT,
  `idutente` int NOT NULL,
  `idarticolo` int NOT NULL,
  `Quantita_carrello` int NOT NULL,
  `Taglia` int DEFAULT NULL,
  PRIMARY KEY (`idcarrello`,`idutente`,`idarticolo`),
  KEY `idarticolocar_idx` (`idarticolo`),
  KEY `idutentecar_idx` (`idutente`),
  KEY `itagliacar_idx` (`Taglia`),
  CONSTRAINT `idarticolocar` FOREIGN KEY (`idarticolo`) REFERENCES `articolo` (`idarticolo`),
  CONSTRAINT `idutentecar` FOREIGN KEY (`idutente`) REFERENCES `utente` (`idutente`),
  CONSTRAINT `itagliacar` FOREIGN KEY (`Taglia`) REFERENCES `taglia` (`idtaglia`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carrello`
--

LOCK TABLES `carrello` WRITE;
/*!40000 ALTER TABLE `carrello` DISABLE KEYS */;
INSERT INTO `carrello` VALUES (6,15,1,4,NULL),(7,15,3,1,NULL),(11,15,2,1,NULL);
/*!40000 ALTER TABLE `carrello` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoria` (
  `idcategoria` int NOT NULL AUTO_INCREMENT,
  `Nome` varchar(45) NOT NULL,
  PRIMARY KEY (`idcategoria`),
  UNIQUE KEY `Nome_UNIQUE` (`Nome`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (16,'Abbigliamento'),(15,'Accessori'),(2,'Attrezzatura'),(6,'Bambino'),(7,'Corsa'),(14,'Corsa ad ostacoli'),(5,'Donna'),(10,'Getto del peso'),(11,'Lancio del disco'),(12,'Lancio del giavellotto'),(13,'Lancio del martello'),(1,'Pista'),(9,'Salto con l\'asta'),(8,'Salto in alto'),(4,'Uomo');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operatore`
--

DROP TABLE IF EXISTS `operatore`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `operatore` (
  `idoperatore` int NOT NULL AUTO_INCREMENT,
  `Nome` varchar(45) NOT NULL,
  `Cognome` varchar(45) NOT NULL,
  `Email` varchar(45) NOT NULL,
  `Password` varchar(200) NOT NULL,
  PRIMARY KEY (`idoperatore`),
  UNIQUE KEY `Email_UNIQUE` (`Email`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operatore`
--

LOCK TABLES `operatore` WRITE;
/*!40000 ALTER TABLE `operatore` DISABLE KEYS */;
INSERT INTO `operatore` VALUES (1,'Marisa','La Sorda','m.lasorda@studenti.unisa.it','751d6a38c5b77ec35697305602c20d1915e14b709a30d18a195d7df00b968d68'),(2,'Francesco','Adinolfi','f.adinolfi26@studenti.unisa.it','e7adad60d6716eeb4f7637bf34785ec25ff25d22dce75b1681852d260fa0e1c4'),(3,'Gerardo','Napolitano','g.napolitano73@studenti.unisa.it','328e165e4ba2b1ee2828121f9b02b838549e5bd419cc92fcf0274fb1a550777e'),(4,'Luigi','Bacco','l.bacco2@studenti.unisa.it','b6aba01aea976b72d100138d12663e5238bbe1b0c0658529e2518da10e71b778');
/*!40000 ALTER TABLE `operatore` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordine_rigo`
--

DROP TABLE IF EXISTS `ordine_rigo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordine_rigo` (
  `idordine_rigo` int NOT NULL AUTO_INCREMENT,
  `Iva` int NOT NULL,
  `Quantita` int NOT NULL,
  `Prezzo` float NOT NULL,
  `Articolo` int NOT NULL,
  `Testata` int NOT NULL,
  PRIMARY KEY (`idordine_rigo`),
  KEY `Articolo_idx` (`Articolo`),
  KEY `Testata_idx` (`Testata`),
  CONSTRAINT `Articolo` FOREIGN KEY (`Articolo`) REFERENCES `articolo` (`idarticolo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Testata` FOREIGN KEY (`Testata`) REFERENCES `ordine_testata` (`idordine_testata`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordine_rigo`
--

LOCK TABLES `ordine_rigo` WRITE;
/*!40000 ALTER TABLE `ordine_rigo` DISABLE KEYS */;
INSERT INTO `ordine_rigo` VALUES (1,22,13,23,2,1),(2,34,43,54,5,1);
/*!40000 ALTER TABLE `ordine_rigo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordine_testata`
--

DROP TABLE IF EXISTS `ordine_testata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordine_testata` (
  `idordine_testata` int NOT NULL AUTO_INCREMENT,
  `Data` date NOT NULL,
  `Indirizzo_fatturazione` varchar(300) NOT NULL,
  `Ora` time NOT NULL,
  `Pagamento` varchar(16) NOT NULL,
  `Indirizzo_spedizione` varchar(300) NOT NULL,
  `Utente` int NOT NULL,
  PRIMARY KEY (`idordine_testata`),
  KEY `Utente_idx` (`Utente`),
  CONSTRAINT `Utente` FOREIGN KEY (`Utente`) REFERENCES `utente` (`idutente`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordine_testata`
--

LOCK TABLES `ordine_testata` WRITE;
/*!40000 ALTER TABLE `ordine_testata` DISABLE KEYS */;
INSERT INTO `ordine_testata` VALUES (1,'2020-11-01','Via delle Cicogne 3','18:50:00','534598878890987','Via delle formiche 1',3),(2,'2019-12-12','Via dalle Scatole 34','18:21:00','543563674536345','Via dei pinguini 81',4);
/*!40000 ALTER TABLE `ordine_testata` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pagamento`
--

DROP TABLE IF EXISTS `pagamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pagamento` (
  `idpagamento` int NOT NULL AUTO_INCREMENT,
  `Numero` varchar(16) NOT NULL,
  `Nome` varchar(50) NOT NULL,
  `Scadenza` char(5) NOT NULL,
  `Codice` decimal(3,0) NOT NULL,
  `Utente` int NOT NULL,
  PRIMARY KEY (`idpagamento`),
  UNIQUE KEY `Numero_UNIQUE` (`Numero`),
  KEY `Utente_idx` (`Utente`),
  CONSTRAINT `Utente4` FOREIGN KEY (`Utente`) REFERENCES `utente` (`idutente`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pagamento`
--

LOCK TABLES `pagamento` WRITE;
/*!40000 ALTER TABLE `pagamento` DISABLE KEYS */;
INSERT INTO `pagamento` VALUES (1,'4333323232323','Pino Pinco','07/25',345,15);
/*!40000 ALTER TABLE `pagamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recapito`
--

DROP TABLE IF EXISTS `recapito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recapito` (
  `idrecapito` int NOT NULL AUTO_INCREMENT,
  `Indirizzo` varchar(100) NOT NULL,
  `Citta` varchar(45) NOT NULL,
  `CAP` decimal(5,0) NOT NULL,
  `Utente` int NOT NULL,
  PRIMARY KEY (`idrecapito`),
  KEY `Utente5_idx` (`Utente`),
  CONSTRAINT `Utente5` FOREIGN KEY (`Utente`) REFERENCES `utente` (`idutente`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recapito`
--

LOCK TABLES `recapito` WRITE;
/*!40000 ALTER TABLE `recapito` DISABLE KEYS */;
INSERT INTO `recapito` VALUES (2,'Via Bella 50','Torino',88099,15),(3,'Vicolo dell\'Allegria 90','Battipaglia',85090,15),(7,'Vicolo Pila','Giffoni',84096,15),(9,'Via di Qua 65','Salerno',54334,15),(10,'Vicolo Pila','Pugliano',84096,15),(11,'Vicolo delle monache 34','Filetta',34534,15),(12,'Via degli ALBURNI 54','Napoli',54364,15),(13,'Via di Giove 45','Napoli',87678,15),(14,'Via delle Vie 32','Milano',76454,15),(15,'Via via 32','Barletta',43678,15),(16,'Vicolo piano 43','Matera',84096,15),(17,'Vicolo Tasso 23','Tasso',84096,15),(18,'Vicolo vicolo','Manfredonia',23545,15);
/*!40000 ALTER TABLE `recapito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recensione`
--

DROP TABLE IF EXISTS `recensione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recensione` (
  `idrecensione` int NOT NULL AUTO_INCREMENT,
  `idutente` int NOT NULL,
  `idarticolo` int NOT NULL,
  `Descrizione` varchar(100) NOT NULL,
  `Data` date NOT NULL,
  PRIMARY KEY (`idrecensione`,`idutente`,`idarticolo`),
  KEY `idutente2_idx` (`idutente`),
  KEY `idarticolo_idx` (`idarticolo`),
  CONSTRAINT `idarticolo3` FOREIGN KEY (`idarticolo`) REFERENCES `articolo` (`idarticolo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `idutente2` FOREIGN KEY (`idutente`) REFERENCES `utente` (`idutente`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recensione`
--

LOCK TABLES `recensione` WRITE;
/*!40000 ALTER TABLE `recensione` DISABLE KEYS */;
INSERT INTO `recensione` VALUES (1,3,1,'belle','2020-11-21'),(2,15,2,'Bello','2022-07-02'),(3,15,1,'Bellissimo','2022-07-02'),(4,15,1,'Bellissimissimo','2022-07-02'),(5,15,3,'Ciao','2022-07-02');
/*!40000 ALTER TABLE `recensione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `taglia`
--

DROP TABLE IF EXISTS `taglia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `taglia` (
  `idtaglia` int NOT NULL AUTO_INCREMENT,
  `Taglia` varchar(10) NOT NULL,
  PRIMARY KEY (`idtaglia`),
  UNIQUE KEY `Taglia_UNIQUE` (`Taglia`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taglia`
--

LOCK TABLES `taglia` WRITE;
/*!40000 ALTER TABLE `taglia` DISABLE KEYS */;
INSERT INTO `taglia` VALUES (7,'29'),(11,'29-32'),(8,'30'),(9,'31'),(10,'32'),(16,'32-36'),(12,'33'),(13,'34'),(14,'35'),(15,'36'),(19,'36-38'),(17,'37'),(18,'38'),(22,'38-40'),(20,'39'),(21,'40'),(25,'40-42'),(23,'41'),(24,'42'),(28,'42-44'),(26,'43'),(27,'44'),(31,'44-46'),(29,'45'),(30,'46'),(4,'L'),(3,'M'),(2,'S'),(5,'XL'),(1,'XS'),(6,'XXL');
/*!40000 ALTER TABLE `taglia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `taglia_articolo`
--

DROP TABLE IF EXISTS `taglia_articolo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `taglia_articolo` (
  `idarticolo` int NOT NULL,
  `idtaglia` int NOT NULL,
  `Quantita` int NOT NULL,
  PRIMARY KEY (`idtaglia`,`idarticolo`),
  KEY `idarticolo4_idx` (`idarticolo`),
  CONSTRAINT `idarticolo5` FOREIGN KEY (`idarticolo`) REFERENCES `articolo` (`idarticolo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `idtaglia` FOREIGN KEY (`idtaglia`) REFERENCES `taglia` (`idtaglia`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taglia_articolo`
--

LOCK TABLES `taglia_articolo` WRITE;
/*!40000 ALTER TABLE `taglia_articolo` DISABLE KEYS */;
INSERT INTO `taglia_articolo` VALUES (31,1,100),(32,1,100),(33,1,100),(34,1,100),(35,1,100),(37,1,100),(38,1,100),(43,1,100),(44,1,100),(45,1,100),(46,1,100),(51,1,100),(52,1,100),(53,1,100),(54,1,100),(31,2,100),(32,2,100),(33,2,100),(34,2,100),(35,2,100),(37,2,100),(38,2,100),(43,2,100),(44,2,100),(45,2,100),(46,2,100),(51,2,100),(52,2,100),(53,2,100),(54,2,100),(31,3,100),(32,3,100),(33,3,100),(34,3,100),(35,3,100),(37,3,100),(38,3,100),(43,3,100),(44,3,100),(45,3,100),(46,3,100),(49,3,250),(50,3,250),(51,3,100),(52,3,100),(53,3,100),(54,3,100),(31,4,100),(32,4,100),(33,4,100),(34,4,100),(35,4,100),(37,4,100),(38,4,100),(43,4,100),(44,4,100),(45,4,100),(46,4,100),(51,4,100),(52,4,100),(53,4,100),(54,4,100),(55,4,250),(31,5,100),(32,5,100),(33,5,100),(34,5,100),(35,5,100),(37,5,100),(38,5,100),(43,5,100),(44,5,100),(45,5,100),(46,5,100),(51,5,100),(52,5,100),(53,5,100),(54,5,100),(31,6,100),(32,6,100),(33,6,100),(34,6,100),(35,6,100),(37,6,100),(38,6,100),(43,6,100),(44,6,100),(45,6,100),(46,6,100),(51,6,100),(52,6,100),(53,6,100),(54,6,100),(57,7,100),(58,7,100),(57,8,100),(58,8,100),(57,9,100),(58,9,100),(57,10,100),(58,10,100),(56,11,100),(57,12,100),(58,12,100),(57,13,100),(58,13,100),(57,14,100),(58,14,100),(40,15,100),(41,15,100),(42,15,100),(47,15,100),(48,15,100),(56,16,100),(40,17,100),(41,17,100),(42,17,100),(47,17,100),(48,17,100),(40,18,100),(41,18,100),(42,18,100),(47,18,100),(48,18,100),(36,19,100),(39,19,100),(40,20,100),(41,20,100),(42,20,100),(47,20,100),(48,20,100),(40,21,100),(41,21,100),(42,21,100),(47,21,100),(48,21,100),(36,22,100),(39,22,100),(40,23,100),(41,23,100),(42,23,100),(47,23,100),(48,23,100),(40,24,100),(41,24,100),(42,24,100),(47,24,100),(48,24,100),(36,25,100),(39,25,100),(40,26,100),(41,26,100),(42,26,100),(47,26,100),(48,26,100),(40,27,100),(41,27,100),(42,27,100),(47,27,100),(48,27,100),(36,28,100),(39,28,100),(40,29,100),(41,29,100),(42,29,100),(47,29,100),(48,29,100),(40,30,100),(41,30,100),(42,30,100),(47,30,100),(48,30,100),(36,31,100),(39,31,100);
/*!40000 ALTER TABLE `taglia_articolo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unita`
--

DROP TABLE IF EXISTS `unita`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `unita` (
  `idunita` int NOT NULL AUTO_INCREMENT,
  `Nome` varchar(45) NOT NULL,
  `Quantita` int NOT NULL,
  PRIMARY KEY (`idunita`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unita`
--

LOCK TABLES `unita` WRITE;
/*!40000 ALTER TABLE `unita` DISABLE KEYS */;
INSERT INTO `unita` VALUES (1,'Pz',1),(2,'Kg',1),(3,'M',5);
/*!40000 ALTER TABLE `unita` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utente`
--

DROP TABLE IF EXISTS `utente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utente` (
  `idutente` int NOT NULL AUTO_INCREMENT,
  `Nome` varchar(45) NOT NULL,
  `Cognome` varchar(45) NOT NULL,
  `Genere` varchar(10) NOT NULL,
  `Email` varchar(45) NOT NULL,
  `Password` varchar(200) NOT NULL,
  `Stato_Account` varchar(45) NOT NULL,
  PRIMARY KEY (`idutente`),
  UNIQUE KEY `Email_UNIQUE` (`Email`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utente`
--

LOCK TABLES `utente` WRITE;
/*!40000 ALTER TABLE `utente` DISABLE KEYS */;
INSERT INTO `utente` VALUES (3,'Alessia','Orlando','Femmina','ale.orl@gmail.com','def413bb3198e35ae4c60c6158ada59168cd5e75f4ac96a779e94820a7c80090','Attivo'),(4,'Gerardo','Napolitano','Maschio','renatojuventino@hotmail.com','Nap123!','Attivo'),(5,'Francesco','Adinolfi','Maschio','francesco.adinolfi02@gmail.com','Adino123!','Attivo'),(7,'Vincenzo','Cerciello','Maschio','vince.cerci@gmail.com','Cerci123!','Attivo'),(8,'Francesco','Garofalo','Maschio','girolafo@gmail.com','2dba4f48a0528a78f1ef54a1bd0db3c93e06546b7b774e0367e8f780e6a0bdfe','Attivo'),(9,'Luigi','Bacco','Maschio','l.bacco2@studenti.unisa.it','0932b5bd44b316ed074239251a40d0ff9242e55376e99784649ee04e40ecd832','Attivo'),(15,'Luigi','Bacco','undefined','luigibacco01@gmail.com','0932b5bd44b316ed074239251a40d0ff9242e55376e99784649ee04e40ecd832','Attivo'),(30,'Luigi','Bacco','undefined','luigibacco0@gmail.com','c71a5a19874fa2e55a03ab56d66133cea77a31a5b89e4423629543fdee6519e7','Attivo');
/*!40000 ALTER TABLE `utente` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-07-03 23:38:11
