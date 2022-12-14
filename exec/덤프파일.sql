-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: j7a306.p.ssafy.io    Database: dangdang
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Table structure for table `body_image`
--

DROP TABLE IF EXISTS `body_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `body_image` (
  `id` varchar(255) NOT NULL,
  `img` varchar(255) NOT NULL,
  `sequence` int NOT NULL,
  `funding_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKlphdpeggneeg1agmogrm3d9h8` (`funding_id`),
  CONSTRAINT `FKlphdpeggneeg1agmogrm3d9h8` FOREIGN KEY (`funding_id`) REFERENCES `funding` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `body_image`
--

LOCK TABLES `body_image` WRITE;
/*!40000 ALTER TABLE `body_image` DISABLE KEYS */;
INSERT INTO `body_image` VALUES ('0fb36404-4a2e-4fe3-875c-fff35c9e875f','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/8ee67129-607e-4969-abbc-f320884fd933/contents/1.jpg',1,'dcaea2f7-b37f-48a0-bd13-eb50f887bf13'),('10620b9f-cf39-4f9d-94dc-c223cb0cd175','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/2fce140e-cf0e-41b0-a9d2-96fea68457e9/contents/0.jpg',0,'4e2094d5-37cf-40bd-92a4-5e5432e9b50e'),('1199069c-582f-4223-ba93-471988ff8dae','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/af1d19aa-6e18-4abc-b553-45a0e53f7efa/contents/0.jpg',0,'e2a33a2d-b9dc-420d-ad1f-5568e7d929dc'),('17bdbb8a-c955-41bf-b560-df48becb51cb','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/ded4bf4c-0482-4382-8193-c11b166a85ee/contents/0.jpg',0,'7fe3eb89-8935-48ff-9e0e-d4d0932cf12b'),('1b1d6045-9b8e-443a-884b-1a2538d4106a','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/1d192086-c2e4-4d77-8219-e5c141dafb6a/contents/0.jpg',0,'881089c7-08aa-41b9-bcce-ad26f3a2fbd9'),('252c6f99-d2b8-4b61-af34-36b753f733db','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/200907cd-cbf8-43fb-8caa-d41590fd32ba/contents/0.jpg',0,'a5ad0047-e0c8-4151-8f49-be18d8880e1c'),('34df3b2a-31f1-4dde-a7c9-ed543f765e7e','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/b0638eb8-6db7-42b9-9102-bb9d1bfad194/contents/0.jpg',0,'8ca878de-b0f1-4211-af0b-252a4c0a595d'),('36057624-06a8-4eca-a945-0b2f05818b21','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/27a260b0-9133-490d-b3e5-a040e780c91a/contents/0.jpg',0,'ba6a963b-9f2c-43fe-83e5-1e16ff5bd718'),('40decbe8-d859-4e3a-a8d7-d01e589a4241','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/e6940cb9-61bb-4eb7-8157-ddc40bb5de34/contents/0.jpg',0,'5f17e6b4-3ada-48d8-a127-e0d5ef1eab87'),('448a5627-1742-448f-917c-ed86d168680a','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/3eaea270-9068-4525-895d-3ebc9a260b10/contents/0.jpg',0,'52457548-5fcb-49c3-b6e4-7c95799e08ad'),('45d33b8c-1c56-48d0-89a2-98fa8e2197d5','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/f11d334e-65c2-49c5-83ae-2f22704f0d09/contents/0.jpg',0,'c6407a5c-4115-4750-9a4f-a61fe92cd56d'),('4de2964e-afdf-4a3e-a77b-bd224ca7c9a3','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/57d760ba-09cc-416c-90ca-11203fc3e29e/contents/0.jpg',0,'3bdf321e-d9c5-480a-b707-dbe0e20ed8d1'),('4e63c7c3-0673-401c-b827-4c310be5e38c','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/62b9185a-4d8e-4180-884d-303ca6546daf/contents/0.jpg',0,'825e9b2c-d2af-4cad-ade0-d62f039d3052'),('5547f8c5-583a-4ba0-8fa6-bc556cfc855c','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/bb1a5c59-7a3d-4d06-ad30-17f642ed39bd/contents/0.jpg',0,'dca399d1-8aea-4f56-bc3a-ddfde9789c09'),('583d3eb4-8532-41d1-b2d5-7698bec82ef3','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/e5a65db7-e155-48dc-8146-3169a83d329b/contents/0.jpg',0,'c248d671-0e9e-44a1-bbc9-73425d669dea'),('5f11493a-1b8f-49a7-99f3-9c75f2d9c7f5','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/a2cdc733-44a3-4d2e-8f99-0b7f2661db11/contents/0.jpg',0,'3d91dcd1-f584-4cde-9e7e-70e262e7896b'),('5f644a68-762a-473a-bccc-7df4e6f9e850','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/59f480e1-90ce-4af8-ba71-b32539ab1875/contents/0.jpg',0,'dc8c854e-3cbc-47da-a4ec-8dba21a15048'),('678cfece-24cc-422c-8a07-b4ad2cb109e5','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/36772555-0db1-4a57-a89b-b3c8262e06d9/contents/0.jpg',0,'0cbb1e5f-60f1-4775-a62b-fd616df9df94'),('6b82eb57-6a01-472f-a078-691fc8caf53d','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/cb5cc905-31c7-47ef-980e-79f30eb8a92b/contents/0.jpg',0,'dd5feef7-647b-4c26-b75d-47289d424230'),('70895297-d982-44ef-bc87-467b826620eb','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/b740e354-f30e-4990-9550-1362c5dc8841/contents/0.jpg',0,'f761b5ed-5cde-4079-8b52-65867065fdf0'),('72290d6c-6cfd-4c8f-ada6-22d76e7fba76','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/9ce16817-8ae1-451d-a094-7df7470748e2/contents/0.jpg',0,'ea38fbec-3a56-4bbe-b0ae-934fd9199743'),('8f44ea4c-03af-4f66-bd7f-925f17879fd9','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/ddeb1929-bb2b-4261-a09b-16a6a4ec3bbc/contents/0.jpg',0,'07f1811d-1786-4270-ab6f-a6fae7c1e1cb'),('916edb7f-77ee-45a9-bc6d-8cabc6afc25a','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/dc8df01c-5221-4def-bcff-e30c3ea9ee51/contents/0.jpg',0,'d2b31f79-6f4a-44bc-8ea8-3b792d21228b'),('99ed01d9-00b0-47c3-af1b-0406244df014','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/faa6793e-1c7f-4741-852b-b1864ee3870e/contents/0.jpg',0,'ad7faf8f-3ac9-4f95-b170-f1b5dad26db3'),('99f3618c-c8f3-4823-8af6-07a9295882ee','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/8ee67129-607e-4969-abbc-f320884fd933/contents/0.jpg',0,'dcaea2f7-b37f-48a0-bd13-eb50f887bf13'),('a1745767-73bf-4385-99b6-2bb7ae1e9b00','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/b446eb20-0993-4842-aecc-52860737e81c/contents/1.jpg',1,'5fdc8570-6c26-4108-bc6d-e8f9cd53da78'),('b1acd855-5579-4472-b7f5-4c8b60005b3e','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/ad2ec550-ae42-4333-b252-9640bf6ced1e/contents/0.jpg',0,'0fe1076b-3ce5-42dc-8088-61b8fa3db9cb'),('c05a70f2-9138-48e5-877e-0ea62ebfa216','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/e843017d-a935-44bb-bf47-e8b2d4205e2c/contents/0.jpg',0,'75bfdda5-8743-41d9-9ccc-abc52c57cd22'),('c3d2ca3c-a4d5-4bc7-968a-3c3a31d70fc4','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/c4c9b7cc-e636-4bae-bcc5-2a3316fcce59/contents/0.jpg',0,'9a17c50e-eaab-4009-a5bb-9a6e78134e1d'),('d1c3e784-677a-4aed-b529-6873fb911fa9','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/92065982-c5e1-4cc0-b889-23cc865fdc18/contents/0.jpg',0,'194defde-4717-494b-9b49-d043a6ad5c6d'),('d67fe9a8-31b0-4c78-bcca-aa4bb36337f8','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/f34bb2fc-04c0-4b08-a68a-65fd38990ce0/contents/0.jpg',0,'363e12f4-3950-496a-ab99-99e46d24da60'),('d7578f2e-b994-41fc-9919-0dfd6424ddb1','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/0eb93134-c9ca-4bdd-9829-fad41869b46c/contents/0.jpg',0,'5b8afa9b-6f1d-43c8-8e4a-edc52f1fb135'),('e232dca0-ef90-4fd8-9d7c-2fc27b6d0e47','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/b446eb20-0993-4842-aecc-52860737e81c/contents/0.jpg',0,'5fdc8570-6c26-4108-bc6d-e8f9cd53da78'),('e4cdb347-18bd-4c35-8e7f-afa9e98edbcf','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/60dddb01-157b-4e06-b518-e259cba8d207/contents/0.jpg',0,'bbb79215-fe91-471e-bd85-a760e16f0663'),('e768b114-5bb8-4121-b091-ed46771b7a41','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/63d54974-5a67-4251-8c19-03fd3db6ecd1/contents/0.jpg',0,'8609c8b4-cb19-42ed-8ec0-7a8128bebd3f'),('e8b6c6cd-24ea-49df-82df-a8fc7898279d','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/56c8705a-6f72-4b45-b71c-4d0ee03be95e/contents/0.jpg',0,'d0fc1f16-ee7e-405a-a2e4-3f5f30c0884f'),('f12c6a33-b1de-40d1-adcb-ee60047e1265','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/8fde60e7-7e32-4ef8-bf1c-f2e96b1f26d2/contents/0.jpg',0,'608ab9f0-96ce-42ca-8510-7b1a42034a9f');
/*!40000 ALTER TABLE `body_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` varchar(255) NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_c2491gxyu6bsjw346i1fgojqf` (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES ('a943a4be-daf8-4b86-8035-b65135c906e2','beauty'),('868cd991-5dbc-4d33-941e-1186e046f737','culture'),('2ea02c06-2e6f-40ba-963d-6972f0f84a57','fashion'),('3fbe4649-e6d2-4704-a0d5-6d692f3d8606','food'),('237b9241-c038-416a-a9d1-0a31809ea09f','kid'),('fa40c97b-b42f-4fd9-9df0-54395abede22','pet'),('dabe2fdf-34a8-4c78-b9f4-cc0ae84e8d8b','sports'),('5749f3db-5725-4c64-bbe6-f701ec06a500','tech');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fund_thumbnail`
--

DROP TABLE IF EXISTS `fund_thumbnail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fund_thumbnail` (
  `id` varchar(255) NOT NULL,
  `img` varchar(255) NOT NULL,
  `sequence` int NOT NULL,
  `funding_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK22alhaig97rhg931eoh1wk5nt` (`funding_id`),
  CONSTRAINT `FK22alhaig97rhg931eoh1wk5nt` FOREIGN KEY (`funding_id`) REFERENCES `funding` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fund_thumbnail`
--

LOCK TABLES `fund_thumbnail` WRITE;
/*!40000 ALTER TABLE `fund_thumbnail` DISABLE KEYS */;
INSERT INTO `fund_thumbnail` VALUES ('03123c11-922c-4354-9f1f-efe90527dc3c','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/ddeb1929-bb2b-4261-a09b-16a6a4ec3bbc/thumbnails/0.jpg',0,'07f1811d-1786-4270-ab6f-a6fae7c1e1cb'),('11f3a570-c3fc-44b2-aac2-5b86e2133782','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/1d192086-c2e4-4d77-8219-e5c141dafb6a/thumbnails/0.jpg',0,'881089c7-08aa-41b9-bcce-ad26f3a2fbd9'),('1de4c2d9-88f5-4acc-96ac-f678a2f04f70','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/199d6770-ea02-4e9b-916f-180ee290de71/thumbnails/0.jpg',0,'85f8d18a-294e-4460-b6f8-1ee82ebfec85'),('241fac58-663e-4d97-acea-deef87d5cf46','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/b740e354-f30e-4990-9550-1362c5dc8841/thumbnails/0.jpg',0,'f761b5ed-5cde-4079-8b52-65867065fdf0'),('2d197a90-86d6-4f81-b2f8-8512c4720117','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/f11d334e-65c2-49c5-83ae-2f22704f0d09/thumbnails/0.jpg',0,'c6407a5c-4115-4750-9a4f-a61fe92cd56d'),('2e6ccd5a-548f-4973-90ec-abc141fa7251','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/e5a65db7-e155-48dc-8146-3169a83d329b/thumbnails/0.jpg',0,'c248d671-0e9e-44a1-bbc9-73425d669dea'),('2f6047c7-1140-4463-9403-f73f2516278a','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/970b3ef5-fbea-4120-9c93-62dda4d9508f/thumbnails/0.jpg',0,'fcee6922-b15e-4137-9c6d-b7d56e14197e'),('3084ac72-4a73-45ab-a53d-7868e78ad0a6','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/59f480e1-90ce-4af8-ba71-b32539ab1875/thumbnails/0.jpg',0,'dc8c854e-3cbc-47da-a4ec-8dba21a15048'),('31e913dc-7ba3-4004-94af-029f71660cce','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/b0638eb8-6db7-42b9-9102-bb9d1bfad194/thumbnails/0.jpg',0,'8ca878de-b0f1-4211-af0b-252a4c0a595d'),('35a7c73e-0cbe-47c2-b71e-9823cb5a916d','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/0eb93134-c9ca-4bdd-9829-fad41869b46c/thumbnails/0.jpg',0,'5b8afa9b-6f1d-43c8-8e4a-edc52f1fb135'),('382f190a-ba90-467b-b47a-f72df95e0ad4','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/cb5cc905-31c7-47ef-980e-79f30eb8a92b/thumbnails/0.jpg',0,'dd5feef7-647b-4c26-b75d-47289d424230'),('3b7c0c8c-c95c-433c-9614-8120fd383fbe','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/9ce16817-8ae1-451d-a094-7df7470748e2/thumbnails/0.jpg',0,'ea38fbec-3a56-4bbe-b0ae-934fd9199743'),('3c660523-1da7-4191-8721-f80a0233e2ff','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/63d54974-5a67-4251-8c19-03fd3db6ecd1/thumbnails/0.jpg',0,'8609c8b4-cb19-42ed-8ec0-7a8128bebd3f'),('4cd473b2-cff4-4538-8589-35d8b2fe42dd','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/36772555-0db1-4a57-a89b-b3c8262e06d9/thumbnails/0.jpg',0,'0cbb1e5f-60f1-4775-a62b-fd616df9df94'),('612483be-2aaa-48a6-b858-828a63168840','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/e6940cb9-61bb-4eb7-8157-ddc40bb5de34/thumbnails/0.jpg',0,'5f17e6b4-3ada-48d8-a127-e0d5ef1eab87'),('7d1bf24c-c2e6-4f02-bf62-770eb42e41e5','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/200907cd-cbf8-43fb-8caa-d41590fd32ba/thumbnails/0.jpg',0,'a5ad0047-e0c8-4151-8f49-be18d8880e1c'),('7d90a760-012d-4135-80a8-39eff2173817','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/c4c9b7cc-e636-4bae-bcc5-2a3316fcce59/thumbnails/0.jpg',0,'9a17c50e-eaab-4009-a5bb-9a6e78134e1d'),('819b3c69-f8eb-4ce5-a4d9-f935adf19073','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/8ee67129-607e-4969-abbc-f320884fd933/thumbnails/0.jpg',0,'dcaea2f7-b37f-48a0-bd13-eb50f887bf13'),('8f2c73cc-8912-448c-a9b9-309a3ac9b102','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/ad2ec550-ae42-4333-b252-9640bf6ced1e/thumbnails/0.jpg',0,'0fe1076b-3ce5-42dc-8088-61b8fa3db9cb'),('97188c14-fa48-490d-9931-58bdc582f738','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/8fde60e7-7e32-4ef8-bf1c-f2e96b1f26d2/thumbnails/0.jpg',0,'608ab9f0-96ce-42ca-8510-7b1a42034a9f'),('984c6e0f-def6-4850-88d6-024957254ae7','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/dc8df01c-5221-4def-bcff-e30c3ea9ee51/thumbnails/0.jpg',0,'d2b31f79-6f4a-44bc-8ea8-3b792d21228b'),('a4a69592-2edd-461c-88b1-ca69ed39bfc3','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/faa6793e-1c7f-4741-852b-b1864ee3870e/thumbnails/0.jpg',0,'ad7faf8f-3ac9-4f95-b170-f1b5dad26db3'),('bb7fa568-8376-45df-88cf-8a7a0b04a9b0','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/2fce140e-cf0e-41b0-a9d2-96fea68457e9/thumbnails/0.jpg',0,'4e2094d5-37cf-40bd-92a4-5e5432e9b50e'),('bfdd376e-cbfa-4d6c-b48d-829a3a2eb259','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/ded4bf4c-0482-4382-8193-c11b166a85ee/thumbnails/0.jpg',0,'7fe3eb89-8935-48ff-9e0e-d4d0932cf12b'),('c062b52c-ea38-4616-820f-cbd7c1d7388b','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/27a260b0-9133-490d-b3e5-a040e780c91a/thumbnails/0.jpg',0,'ba6a963b-9f2c-43fe-83e5-1e16ff5bd718'),('c0b68fc8-b668-4712-b859-ce8d9b6004b8','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/62b9185a-4d8e-4180-884d-303ca6546daf/thumbnails/0.jpg',0,'825e9b2c-d2af-4cad-ade0-d62f039d3052'),('c5c8b87d-72e8-418c-89a8-2ee5e51b2012','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/3eaea270-9068-4525-895d-3ebc9a260b10/thumbnails/0.jpg',0,'52457548-5fcb-49c3-b6e4-7c95799e08ad'),('ccb1449b-df8e-4c58-bb56-438f5855ef8d','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/e843017d-a935-44bb-bf47-e8b2d4205e2c/thumbnails/0.jpg',0,'75bfdda5-8743-41d9-9ccc-abc52c57cd22'),('d0fb91b8-94a1-48ed-a486-6cd0f605f290','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/f34bb2fc-04c0-4b08-a68a-65fd38990ce0/thumbnails/0.jpg',0,'363e12f4-3950-496a-ab99-99e46d24da60'),('d47f3f8b-53b2-4bb7-bfe5-d96bc246c86b','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/a2cdc733-44a3-4d2e-8f99-0b7f2661db11/thumbnails/0.jpg',0,'3d91dcd1-f584-4cde-9e7e-70e262e7896b'),('dc0b8d3e-fa3d-4909-9635-932db0437356','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/92065982-c5e1-4cc0-b889-23cc865fdc18/thumbnails/0.jpg',0,'194defde-4717-494b-9b49-d043a6ad5c6d'),('dd6a9dd2-da42-43c6-8fa8-f1e7e964c9d3','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/b446eb20-0993-4842-aecc-52860737e81c/thumbnails/0.jpg',0,'5fdc8570-6c26-4108-bc6d-e8f9cd53da78'),('e1114b70-e58b-4c43-9a12-2c9e70e1b40e','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/57d760ba-09cc-416c-90ca-11203fc3e29e/thumbnails/0.jpg',0,'3bdf321e-d9c5-480a-b707-dbe0e20ed8d1'),('e8b6e1bf-c105-4a13-a65d-6d3f63fa5248','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/60dddb01-157b-4e06-b518-e259cba8d207/thumbnails/0.jpg',0,'bbb79215-fe91-471e-bd85-a760e16f0663'),('f3bbab79-820e-4615-9ae0-7644f13029e0','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/56c8705a-6f72-4b45-b71c-4d0ee03be95e/thumbnails/0.jpg',0,'d0fc1f16-ee7e-405a-a2e4-3f5f30c0884f'),('fa9616c7-560e-4695-9a8f-bb834e7c7d10','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/af1d19aa-6e18-4abc-b553-45a0e53f7efa/thumbnails/0.jpg',0,'e2a33a2d-b9dc-420d-ad1f-5568e7d929dc'),('fb7a7389-c166-4c89-ada5-546fe298d146','https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/funding/bb1a5c59-7a3d-4d06-ad30-17f642ed39bd/thumbnails/0.jpg',0,'dca399d1-8aea-4f56-bc3a-ddfde9789c09');
/*!40000 ALTER TABLE `fund_thumbnail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `funding`
--

DROP TABLE IF EXISTS `funding`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `funding` (
  `id` varchar(255) NOT NULL,
  `business_license_num` varchar(255) NOT NULL,
  `company` varchar(255) NOT NULL,
  `detail_state` varchar(255) NOT NULL,
  `end_date` datetime(6) DEFAULT NULL,
  `now_price` int NOT NULL,
  `project_introduction` varchar(255) DEFAULT NULL,
  `start_date` datetime(6) DEFAULT NULL,
  `state` int NOT NULL DEFAULT '0',
  `target_price` int DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `category_id` varchar(255) DEFAULT NULL,
  `maker_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkbxd35ur7pg4ubyq4j3v3i1fq` (`category_id`),
  KEY `FKcp7tjip50e54sulugnj5c4l7n` (`maker_id`),
  CONSTRAINT `FKcp7tjip50e54sulugnj5c4l7n` FOREIGN KEY (`maker_id`) REFERENCES `maker` (`id`),
  CONSTRAINT `FKkbxd35ur7pg4ubyq4j3v3i1fq` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funding`
--

LOCK TABLES `funding` WRITE;
/*!40000 ALTER TABLE `funding` DISABLE KEYS */;
INSERT INTO `funding` VALUES ('07f1811d-1786-4270-ab6f-a6fae7c1e1cb','7950301132','???????????????????????????','?????? ?????????','2024-01-18 09:23:00.000000',0,'temp','2022-10-07 09:25:00.000000',1,50000,'????????? ????????? ????????????','fa40c97b-b42f-4fd9-9df0-54395abede22','e3139b60-c38f-46f2-a05a-c8420db51330'),('0cbb1e5f-60f1-4775-a62b-fd616df9df94','8808101059','???????????????','?????? ?????????','2022-10-16 15:30:00.000000',60000,'temp','2022-10-06 15:40:00.000000',1,1000000,'???????????? ????????? ????????????','237b9241-c038-416a-a9d1-0a31809ea09f','baa7ea8c-981b-4ef5-b828-a0dc5735ab40'),('0fe1076b-3ce5-42dc-8088-61b8fa3db9cb','8808101059','???????????????','?????? ?????????','2022-10-22 15:45:00.000000',0,'temp','2022-10-06 16:00:00.000000',1,500000,'????????? ??????????????? ????????? 100???','237b9241-c038-416a-a9d1-0a31809ea09f','baa7ea8c-981b-4ef5-b828-a0dc5735ab40'),('194defde-4717-494b-9b49-d043a6ad5c6d','7950301132','???????????????????????????','?????? ??????','2022-10-21 16:31:00.000000',0,'temp','2022-10-07 16:31:00.000000',0,50000000,'????????? ?????? ?????? ?????? ???????????? ??????','a943a4be-daf8-4b86-8035-b65135c906e2','e3139b60-c38f-46f2-a05a-c8420db51330'),('363e12f4-3950-496a-ab99-99e46d24da60','3753300654','??????????????????','?????? ??????','2022-10-09 15:35:00.000000',0,'temp','2022-10-07 15:35:00.000000',0,600000,'??????????????? ????????? ??????','3fbe4649-e6d2-4704-a0d5-6d692f3d8606','d668edd8-3f71-45f4-acf9-983bbd8f29e2'),('3bdf321e-d9c5-480a-b707-dbe0e20ed8d1','1088178306','?????????????????????(???)','?????? ??????','2022-10-30 02:11:00.000000',0,'temp','2022-10-08 02:11:00.000000',0,100000000,'AI ????????? ?????? ?????????','5749f3db-5725-4c64-bbe6-f701ec06a500','3a01dac2-7873-4e9c-b833-b72ee0b73607'),('3d91dcd1-f584-4cde-9e7e-70e262e7896b','1088178306','?????????????????????(???)','?????? ?????????','2022-10-22 14:51:00.000000',56000,'temp','2022-10-06 14:53:00.000000',1,100000,'????????????','3fbe4649-e6d2-4704-a0d5-6d692f3d8606','3a01dac2-7873-4e9c-b833-b72ee0b73607'),('4e2094d5-37cf-40bd-92a4-5e5432e9b50e','1088178306','?????????????????????(???)','?????? ?????????','2022-10-15 15:55:00.000000',12000,'temp','2022-10-06 15:59:00.000000',1,100000,'????????? ??????','fa40c97b-b42f-4fd9-9df0-54395abede22','3a01dac2-7873-4e9c-b833-b72ee0b73607'),('52457548-5fcb-49c3-b6e4-7c95799e08ad','8808101059','???????????????','?????? ??????','2022-10-12 15:52:00.000000',0,'temp','2022-10-09 16:00:00.000000',0,600000,'??????????????? 30??? ?????????, ?????????','fa40c97b-b42f-4fd9-9df0-54395abede22','baa7ea8c-981b-4ef5-b828-a0dc5735ab40'),('5b8afa9b-6f1d-43c8-8e4a-edc52f1fb135','7950301132','???????????????????????????','?????? ?????????','2022-10-31 16:28:00.000000',0,'temp','2022-10-06 17:28:00.000000',1,500000,'????????? 100% ?????? ?????????','fa40c97b-b42f-4fd9-9df0-54395abede22','e3139b60-c38f-46f2-a05a-c8420db51330'),('5f17e6b4-3ada-48d8-a127-e0d5ef1eab87','7950301132','???????????????????????????','?????? ??????','2022-10-31 16:33:00.000000',0,'temp','2022-10-07 16:33:00.000000',0,6800000,'????????????????????????','5749f3db-5725-4c64-bbe6-f701ec06a500','e3139b60-c38f-46f2-a05a-c8420db51330'),('5fdc8570-6c26-4108-bc6d-e8f9cd53da78','7950301132','???????????????????????????','?????? ??????','2022-10-19 09:12:00.000000',0,'temp','2022-10-08 09:12:00.000000',0,50000,'???????????????(???????????????)','5749f3db-5725-4c64-bbe6-f701ec06a500','e3139b60-c38f-46f2-a05a-c8420db51330'),('608ab9f0-96ce-42ca-8510-7b1a42034a9f','1088178306','SSAFY','?????? ??????','2022-10-14 03:22:00.000000',0,'temp','2022-10-07 04:00:00.000000',0,80000,'?????? ????????? ???????????????','5749f3db-5725-4c64-bbe6-f701ec06a500','42f7dd7b-68a1-480f-84cd-cb2757feb55d'),('75bfdda5-8743-41d9-9ccc-abc52c57cd22','7950301132','???????????????????????????','?????? ?????????','2022-10-07 12:42:00.000000',0,'temp','2022-10-07 09:35:00.000000',1,500000,'[????????????] ????????????','868cd991-5dbc-4d33-941e-1186e046f737','e3139b60-c38f-46f2-a05a-c8420db51330'),('7fe3eb89-8935-48ff-9e0e-d4d0932cf12b','8808101059','???????????????','?????? ?????????','2022-10-27 15:35:00.000000',20000,'temp','2022-10-06 18:00:00.000000',1,500000,'?????? ??????????????? ??????','237b9241-c038-416a-a9d1-0a31809ea09f','baa7ea8c-981b-4ef5-b828-a0dc5735ab40'),('825e9b2c-d2af-4cad-ade0-d62f039d3052','7950301132','???????????????????????????','?????? ?????????','2022-10-28 09:18:00.000000',52000,'temp','2022-10-07 09:22:00.000000',1,50000,'[?????????] Take me to Church','868cd991-5dbc-4d33-941e-1186e046f737','e3139b60-c38f-46f2-a05a-c8420db51330'),('85f8d18a-294e-4460-b6f8-1ee82ebfec85','7950301132','???????????????????????????','?????? ?????????','2023-05-17 09:11:00.000000',0,'temp','2022-10-07 10:11:00.000000',1,400000,'[????????????] ????????? ????????????','868cd991-5dbc-4d33-941e-1186e046f737','e3139b60-c38f-46f2-a05a-c8420db51330'),('8609c8b4-cb19-42ed-8ec0-7a8128bebd3f','3753300654','??????????????????','?????? ??????','2022-10-07 15:30:00.000000',0,'temp','2022-10-06 15:32:59.000000',0,500000,'???????????? ??????????????????','dabe2fdf-34a8-4c78-b9f4-cc0ae84e8d8b','d668edd8-3f71-45f4-acf9-983bbd8f29e2'),('881089c7-08aa-41b9-bcce-ad26f3a2fbd9','1088178306','?????????????????????(???)','?????? ?????????','2022-10-22 15:43:00.000000',39000,'temp','2022-10-06 15:47:00.000000',1,100000,'?????? ORM ?????? JPA ??????????????? ???','868cd991-5dbc-4d33-941e-1186e046f737','3a01dac2-7873-4e9c-b833-b72ee0b73607'),('8ca878de-b0f1-4211-af0b-252a4c0a595d','1088178306','?????????????????????(???)','?????? ?????????','2022-10-21 14:44:00.000000',660000,'temp','2022-10-06 15:08:00.000000',1,100000,'??? ?????????','3fbe4649-e6d2-4704-a0d5-6d692f3d8606','3a01dac2-7873-4e9c-b833-b72ee0b73607'),('9a17c50e-eaab-4009-a5bb-9a6e78134e1d','7950301132','???????????????????????????','?????? ??????','2030-06-02 08:34:00.000000',0,'temp','2026-03-13 08:34:00.000000',0,30000,'????????? ???','868cd991-5dbc-4d33-941e-1186e046f737','e3139b60-c38f-46f2-a05a-c8420db51330'),('a5ad0047-e0c8-4151-8f49-be18d8880e1c','7950301132','???????????????????????????','?????? ??????','2022-10-07 09:15:00.000000',870000,'temp','2022-10-07 08:19:00.000000',2,50000000,'????????? ?????????','868cd991-5dbc-4d33-941e-1186e046f737','e3139b60-c38f-46f2-a05a-c8420db51330'),('ad7faf8f-3ac9-4f95-b170-f1b5dad26db3','7950301132','???????????????????????????','?????? ??????','2025-05-23 09:15:00.000000',0,'temp','2023-09-23 09:15:00.000000',0,50000,'[????????????] ????????? ??????','868cd991-5dbc-4d33-941e-1186e046f737','e3139b60-c38f-46f2-a05a-c8420db51330'),('ba6a963b-9f2c-43fe-83e5-1e16ff5bd718','8808101059','???????????????','?????? ?????????','2022-10-13 15:01:00.000000',210000,'temp','2022-10-06 15:10:00.000000',1,3000000,'?????? ?????? 3D ???????????????','237b9241-c038-416a-a9d1-0a31809ea09f','baa7ea8c-981b-4ef5-b828-a0dc5735ab40'),('bbb79215-fe91-471e-bd85-a760e16f0663','8808101059','???????????????','?????? ??????','2022-10-07 10:30:00.000000',73000,'temp','2022-10-07 10:00:00.000000',2,100000,'?????? ??????????????? ????????????','a943a4be-daf8-4b86-8035-b65135c906e2','baa7ea8c-981b-4ef5-b828-a0dc5735ab40'),('c248d671-0e9e-44a1-bbc9-73425d669dea','1088178306','SSAFY','?????? ??????','2022-10-11 02:45:00.000000',0,'temp','2022-10-08 02:45:00.000000',0,100000,'????????? ???????????? ????????????','a943a4be-daf8-4b86-8035-b65135c906e2','42f7dd7b-68a1-480f-84cd-cb2757feb55d'),('c6407a5c-4115-4750-9a4f-a61fe92cd56d','3753300654','??????????????????','?????? ??????','2022-10-08 15:06:00.000000',0,'temp','2022-10-06 15:28:10.000000',0,24000000,'????????? ????????? ????????????','5749f3db-5725-4c64-bbe6-f701ec06a500','d668edd8-3f71-45f4-acf9-983bbd8f29e2'),('d0fc1f16-ee7e-405a-a2e4-3f5f30c0884f','7950301132','???????????????????????????','?????? ?????????','2022-10-08 09:30:00.000000',0,'temp','2022-10-07 09:33:00.000000',1,30000,'[????????????] ?????????????????? ??????','868cd991-5dbc-4d33-941e-1186e046f737','e3139b60-c38f-46f2-a05a-c8420db51330'),('d2b31f79-6f4a-44bc-8ea8-3b792d21228b','3753300654','??????????????????','?????? ??????','2022-10-09 15:19:00.000000',0,'temp','2022-10-06 15:30:30.000000',0,240000,'????????? ????????? ?????????','3fbe4649-e6d2-4704-a0d5-6d692f3d8606','d668edd8-3f71-45f4-acf9-983bbd8f29e2'),('dc8c854e-3cbc-47da-a4ec-8dba21a15048','1088178306','SSAFY','?????? ??????','2022-10-06 16:10:00.000000',350000,'temp','2022-10-06 16:00:00.000000',2,100000,'?????? ????????? / ???????????? ????????? ??????','2ea02c06-2e6f-40ba-963d-6972f0f84a57','42f7dd7b-68a1-480f-84cd-cb2757feb55d'),('dca399d1-8aea-4f56-bc3a-ddfde9789c09','3753300654','??????????????????','?????? ?????????','2022-12-23 15:48:00.000000',40000,'temp','2022-10-06 15:50:00.000000',1,990000,'?????? ????????? ??????','3fbe4649-e6d2-4704-a0d5-6d692f3d8606','d668edd8-3f71-45f4-acf9-983bbd8f29e2'),('dcaea2f7-b37f-48a0-bd13-eb50f887bf13','7950301132','???????????????????????????','?????? ?????????','2022-10-07 15:03:00.000000',0,'temp','2022-10-07 10:10:00.000000',1,500000,'[??????] ????????????','dabe2fdf-34a8-4c78-b9f4-cc0ae84e8d8b','e3139b60-c38f-46f2-a05a-c8420db51330'),('dd5feef7-647b-4c26-b75d-47289d424230','7950301132','???????????????????????????','?????? ?????????','2023-05-17 08:27:00.000000',300000,'temp','2022-10-07 08:31:00.000000',1,3000000,'[??????] ?????? ?????? ??????????????? ??????.','868cd991-5dbc-4d33-941e-1186e046f737','e3139b60-c38f-46f2-a05a-c8420db51330'),('e2a33a2d-b9dc-420d-ad1f-5568e7d929dc','1088178306','?????????????????????(???)','?????? ??????','2022-10-31 14:47:00.000000',0,'temp','2022-10-18 14:47:00.000000',0,50000,'????????? ??????','3fbe4649-e6d2-4704-a0d5-6d692f3d8606','3a01dac2-7873-4e9c-b833-b72ee0b73607'),('ea38fbec-3a56-4bbe-b0ae-934fd9199743','3753300654','??????????????????','?????? ?????????','2022-10-28 15:50:00.000000',0,'temp','2022-10-06 15:55:00.000000',1,500000,'?????????????????????','868cd991-5dbc-4d33-941e-1186e046f737','d668edd8-3f71-45f4-acf9-983bbd8f29e2'),('f761b5ed-5cde-4079-8b52-65867065fdf0','1088178306','SSAFY','?????? ?????????','2022-10-08 15:50:00.000000',250000,'temp','2022-10-06 15:50:00.000000',1,200000,'????????? ???????????? ???????????????','3fbe4649-e6d2-4704-a0d5-6d692f3d8606','42f7dd7b-68a1-480f-84cd-cb2757feb55d'),('fcee6922-b15e-4137-9c6d-b7d56e14197e','3753300654','??????????????????','?????? ?????????','2022-10-31 15:55:00.000000',0,'temp','2022-10-06 15:59:00.000000',1,1000000,'?????? ??????','dabe2fdf-34a8-4c78-b9f4-cc0ae84e8d8b','d668edd8-3f71-45f4-acf9-983bbd8f29e2');
/*!40000 ALTER TABLE `funding` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `funding_tag`
--

DROP TABLE IF EXISTS `funding_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `funding_tag` (
  `id` varchar(255) NOT NULL,
  `funding_id` varchar(255) NOT NULL,
  `tag_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKstsnwd76xf16tdg6q614ymuvr` (`funding_id`),
  KEY `FK76xe0ylcsdtvcrnor23hxxsmy` (`tag_id`),
  CONSTRAINT `FK76xe0ylcsdtvcrnor23hxxsmy` FOREIGN KEY (`tag_id`) REFERENCES `tag` (`id`),
  CONSTRAINT `FKstsnwd76xf16tdg6q614ymuvr` FOREIGN KEY (`funding_id`) REFERENCES `funding` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funding_tag`
--

LOCK TABLES `funding_tag` WRITE;
/*!40000 ALTER TABLE `funding_tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `funding_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `maker`
--

DROP TABLE IF EXISTS `maker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `maker` (
  `id` varchar(255) NOT NULL,
  `company_name` varchar(255) DEFAULT NULL,
  `company_number` varchar(255) DEFAULT NULL,
  `funding_sum` bigint DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK8uekmflg253t3lg5u4wlwb938` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `maker`
--

LOCK TABLES `maker` WRITE;
/*!40000 ALTER TABLE `maker` DISABLE KEYS */;
INSERT INTO `maker` VALUES ('3a01dac2-7873-4e9c-b833-b72ee0b73607','?????????????????????(???)','1088178306',0,'https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/user/qwerty1434%40naver.com/profile/maker/0.jpg'),('42f7dd7b-68a1-480f-84cd-cb2757feb55d','SSAFY','1088178306',350000,'https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/user/leemyo_%40naver.com/profile/maker/0.jpg'),('baa7ea8c-981b-4ef5-b828-a0dc5735ab40','???????????????','8808101059',0,'https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/user/hyungi4608%40gmail.com/profile/maker/0.jpg'),('d668edd8-3f71-45f4-acf9-983bbd8f29e2','??????????????????','3753300654',0,'https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/user/yi_jonghoon%40naver.com/profile/maker/0.jpg'),('e3139b60-c38f-46f2-a05a-c8420db51330','???????????????????????????','7950301132',0,'https://dangdang-bucket.s3.ap-northeast-2.amazonaws.com/user/dlwhd9635%40naver.com/profile/maker/0.jpg');
/*!40000 ALTER TABLE `maker` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_history`
--

DROP TABLE IF EXISTS `order_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_history` (
  `id` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `order_date` datetime(6) NOT NULL,
  `phone_number` varchar(255) NOT NULL,
  `total_price` int DEFAULT NULL,
  `fund_id` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKwp0a5qcbwjwrrsmmt4re8ah6` (`fund_id`),
  KEY `FKp03guo9hm9uf9k0n4a1sam969` (`user_id`),
  CONSTRAINT `FKp03guo9hm9uf9k0n4a1sam969` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKwp0a5qcbwjwrrsmmt4re8ah6` FOREIGN KEY (`fund_id`) REFERENCES `funding` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_history`
--

LOCK TABLES `order_history` WRITE;
/*!40000 ALTER TABLE `order_history` DISABLE KEYS */;
INSERT INTO `order_history` VALUES ('0912de32-d40f-4962-9cf9-7190bcf61da5','?????? ????????? ??????????????? 162 (?????????) ?????? ???','2022-10-06 15:58:02.912000','010-2320-8737',40000,'dca399d1-8aea-4f56-bc3a-ddfde9789c09','13af8b68-a180-4448-945f-1ce7ce6de896'),('17967001-dd07-4f85-9239-d16af937e913','?????? ???????????? ???????????? 6 (????????????, ??????G well estates) s343','2022-10-07 01:08:05.712000','01083437455',20000,'7fe3eb89-8935-48ff-9e0e-d4d0932cf12b','3a01dac2-7873-4e9c-b833-b72ee0b73607'),('2eac60b9-4b78-4764-b201-45620b32a8b3',' ','2022-10-07 09:22:39.062000','',52000,'825e9b2c-d2af-4cad-ade0-d62f039d3052','e3139b60-c38f-46f2-a05a-c8420db51330'),('2f4b5547-d3f0-4313-9d45-17c3892b61e6','?????? ????????? ????????? ?????????106?????? 43 (?????????) ?????????','2022-10-06 15:39:27.153000','01056469635',2000,'3d91dcd1-f584-4cde-9e7e-70e262e7896b','d668edd8-3f71-45f4-acf9-983bbd8f29e2'),('345eea4f-3ed1-45d3-a4f2-005df6fa05ce','?????? ????????? ???????????? 212 (?????????) ???????????????','2022-10-07 04:04:12.067000','010-1234-4321',50000,'f761b5ed-5cde-4079-8b52-65867065fdf0','42f7dd7b-68a1-480f-84cd-cb2757feb55d'),('366db477-e699-41cd-8cde-78aa40851ef4','?????? ????????? ????????? ????????? 262-24 (??????2????????????) 202??? 1302???','2022-10-06 16:01:23.172000','01099348381',30000,'dc8c854e-3cbc-47da-a4ec-8dba21a15048','3a01dac2-7873-4e9c-b833-b72ee0b73607'),('3f3a05ef-bfd6-4baa-b5fe-55d3faeb490c','?????? ????????? ?????????1??? 8 (?????????) ???????????????','2022-10-06 16:01:29.074000','01012345678',140000,'dc8c854e-3cbc-47da-a4ec-8dba21a15048','baa7ea8c-981b-4ef5-b828-a0dc5735ab40'),('46b1df65-0247-4e38-949d-51504fd35033','?????? ????????? ???????????? 101 (?????????) ???????????????','2022-10-06 17:05:49.830000','01012345678',39000,'881089c7-08aa-41b9-bcce-ad26f3a2fbd9','baa7ea8c-981b-4ef5-b828-a0dc5735ab40'),('55cf9a87-de50-4b38-81eb-72b2a014a9e3','?????? ????????? ????????? ??????1??? 11 (??????, ????????????6???????????????) ?????????','2022-10-07 10:07:31.754000','01012345678',70000,'f761b5ed-5cde-4079-8b52-65867065fdf0','baa7ea8c-981b-4ef5-b828-a0dc5735ab40'),('5b35536c-8f71-4402-a7d8-34eab2673583',' ','2022-10-06 15:42:10.999000','',200000,'ba6a963b-9f2c-43fe-83e5-1e16ff5bd718','d668edd8-3f71-45f4-acf9-983bbd8f29e2'),('5b8d39dc-3e8d-4e01-a683-d36df7b81580',' ','2022-10-06 15:40:31.055000','',11000,'3d91dcd1-f584-4cde-9e7e-70e262e7896b','d668edd8-3f71-45f4-acf9-983bbd8f29e2'),('666c46e8-b301-449c-a824-ebe8735dd498','????????????????????? ???????????? ????????? ????????? 632 ????????? ?????????','2022-10-07 10:06:49.680000','01012345678',50000,'f761b5ed-5cde-4079-8b52-65867065fdf0','baa7ea8c-981b-4ef5-b828-a0dc5735ab40'),('79d87016-cca0-4404-8a08-c4e565093730','?????? ????????? ???????????? 5 (????????????) 401???','2022-10-06 15:19:58.875000','010-6847-0223',10000,'ba6a963b-9f2c-43fe-83e5-1e16ff5bd718','13af8b68-a180-4448-945f-1ce7ce6de896'),('84b55f68-c372-4b47-9531-0bd798dadb2e',' ','2022-10-06 15:43:25.473000','',660000,'8ca878de-b0f1-4211-af0b-252a4c0a595d','d668edd8-3f71-45f4-acf9-983bbd8f29e2'),('8879d7d7-d144-43f7-9492-afdb962421a0','?????? ????????? ????????? ???????????? 32 (?????????) 203???','2022-10-06 16:07:16.872000','010-7738-3843',20000,'f761b5ed-5cde-4079-8b52-65867065fdf0','13af8b68-a180-4448-945f-1ce7ce6de896'),('9341e525-4a8d-47af-a2d5-4981bd5ec1f5','?????? ????????? ???????????? 79 (?????????) 501???','2022-10-07 02:22:31.122000','01084379823',12000,'4e2094d5-37cf-40bd-92a4-5e5432e9b50e','3a01dac2-7873-4e9c-b833-b72ee0b73607'),('a7847da3-0799-4b64-9851-d68ccb54ac8a','?????? ????????? ????????? ????????? 12 203???','2022-10-06 15:56:22.401000','010-1232-2293',10000,'f761b5ed-5cde-4079-8b52-65867065fdf0','13af8b68-a180-4448-945f-1ce7ce6de896'),('aa974faa-af91-4294-9591-1e2daea1a4c3','?????? ????????? ????????? 674 (????????????) 302???','2022-10-06 16:00:48.282000','010-8839-0229',180000,'dc8c854e-3cbc-47da-a4ec-8dba21a15048','13af8b68-a180-4448-945f-1ce7ce6de896'),('ae97620b-639e-4ecf-b560-0e1244a1753a',' ','2022-10-07 08:31:44.053000','',480000,'a5ad0047-e0c8-4151-8f49-be18d8880e1c','e3139b60-c38f-46f2-a05a-c8420db51330'),('c19f757d-ba79-417d-ad4d-f88f16f4fbc3',' ','2022-10-07 09:22:58.647000','',300000,'dd5feef7-647b-4c26-b75d-47289d424230','e3139b60-c38f-46f2-a05a-c8420db51330'),('c1d3612d-9740-4561-81ef-1341859f5168','?????? ????????? ???????????? 212 (?????????) ???????????????','2022-10-07 10:01:51.388000','010-1234-4321',48000,'bbb79215-fe91-471e-bd85-a760e16f0663','42f7dd7b-68a1-480f-84cd-cb2757feb55d'),('c20a1c51-0655-4db0-b787-632b9c4c920e',' ','2022-10-07 10:01:10.957000','',25000,'bbb79215-fe91-471e-bd85-a760e16f0663','e3139b60-c38f-46f2-a05a-c8420db51330'),('c297ae5b-2205-48fe-964c-eb8387b45500','?????? ????????? ???????????? 212 (?????????) ???????????????','2022-10-07 01:20:25.402000','010-1234-4321',50000,'f761b5ed-5cde-4079-8b52-65867065fdf0','42f7dd7b-68a1-480f-84cd-cb2757feb55d'),('d02b15a4-d7f5-49bb-8a8e-d4b760df1739',' ','2022-10-06 15:45:45.561000','',60000,'0cbb1e5f-60f1-4775-a62b-fd616df9df94','d668edd8-3f71-45f4-acf9-983bbd8f29e2'),('d7f39b84-90c1-451e-92a2-f050498b894f',' ','2022-10-06 15:41:39.319000','',43000,'3d91dcd1-f584-4cde-9e7e-70e262e7896b','d668edd8-3f71-45f4-acf9-983bbd8f29e2'),('d8c765bd-89c4-4237-a674-09e7d7435d08',' ','2022-10-07 08:31:01.676000','',390000,'a5ad0047-e0c8-4151-8f49-be18d8880e1c','e3139b60-c38f-46f2-a05a-c8420db51330'),('f4812e88-15d7-4dd4-9ff1-ac8fd86215ed',' ','2022-10-06 15:42:11.548000','',200000,'ba6a963b-9f2c-43fe-83e5-1e16ff5bd718','d668edd8-3f71-45f4-acf9-983bbd8f29e2'),('f76908d9-bc3d-4227-baa9-3f40e76307e0',' ','2022-10-06 15:45:46.104000','',60000,'0cbb1e5f-60f1-4775-a62b-fd616df9df94','d668edd8-3f71-45f4-acf9-983bbd8f29e2');
/*!40000 ALTER TABLE `order_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_reward`
--

DROP TABLE IF EXISTS `order_reward`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_reward` (
  `id` varchar(255) NOT NULL,
  `count` int NOT NULL,
  `item_total_price` int NOT NULL,
  `order_history_id` varchar(255) NOT NULL,
  `reward_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3bx1opbtn12j0qxwoqxpnwu3e` (`order_history_id`),
  KEY `FK7nsnbug2fs6k1u6u5r85qmhbe` (`reward_id`),
  CONSTRAINT `FK3bx1opbtn12j0qxwoqxpnwu3e` FOREIGN KEY (`order_history_id`) REFERENCES `order_history` (`id`),
  CONSTRAINT `FK7nsnbug2fs6k1u6u5r85qmhbe` FOREIGN KEY (`reward_id`) REFERENCES `reward` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_reward`
--

LOCK TABLES `order_reward` WRITE;
/*!40000 ALTER TABLE `order_reward` DISABLE KEYS */;
INSERT INTO `order_reward` VALUES ('03061de9-ac16-4d47-9e48-1220e07f55d7',13,390000,'d8c765bd-89c4-4237-a674-09e7d7435d08','77bfce36-7163-46d2-9a77-a6d131fdbe8c'),('0c4f03d8-3c25-4a11-811a-88208715c758',36,360000,'84b55f68-c372-4b47-9531-0bd798dadb2e','7d12e71f-db91-46e7-b986-3ab8387aeeaa'),('0c7e3558-2d29-4a2e-ac90-845e7d94a794',1,20000,'55cf9a87-de50-4b38-81eb-72b2a014a9e3','85ad9131-3ce5-4245-9816-71a8583bd5b6'),('0fe3dbf6-9198-40ba-bd28-3146b7402893',1,39000,'46b1df65-0247-4e38-949d-51504fd35033','3e9a61e5-f7f3-437b-9983-d6302163fd5b'),('1bf27441-62c1-44a5-b1ff-bdd077a627b8',4,20000,'2eac60b9-4b78-4764-b201-45620b32a8b3','304965c3-3f68-4590-bea8-6ca1180de514'),('20bb4b9b-4190-4392-87bc-3cbdabfd3203',5,5000,'5b8d39dc-3e8d-4e01-a683-d36df7b81580','8f3a9cf0-9dd5-4033-83b8-cd844e1ec21c'),('3329c0b1-5c28-4643-97a0-32cc171fd2af',3,15000,'2eac60b9-4b78-4764-b201-45620b32a8b3','78a553c9-1c85-4e1d-87c7-2a4cd947be2e'),('3a51cb06-98fb-40ff-b27c-2c680e569fef',4,40000,'0912de32-d40f-4962-9cf9-7190bcf61da5','5497c62f-cc14-42e9-bbca-69ea4d46e5b3'),('3c93beee-67ef-4c0d-b6cf-65fb052b9bce',1,20000,'17967001-dd07-4f85-9239-d16af937e913','0f65fc72-ce81-46b9-a5a2-2628e7bb1a31'),('48a9ccec-59c3-4abd-9167-19c2ae32fa1a',2,80000,'3f3a05ef-bfd6-4baa-b5fe-55d3faeb490c','b1a805cf-306e-4b60-85c5-7335e3eebe13'),('51b34897-37b1-4d8f-a0d2-6cdaea919408',1,10000,'c20a1c51-0655-4db0-b787-632b9c4c920e','248cafde-53e5-4f0d-9fc6-8b931e71506b'),('53595b43-7f92-426b-9f9d-e8bdeb67c493',2,200000,'5b35536c-8f71-4402-a7d8-34eab2673583','ed482de3-5b7c-4a64-a740-a07720eef5f4'),('53d1b9e0-d754-456d-b26f-ef1e2ef6e685',1,50000,'c297ae5b-2205-48fe-964c-eb8387b45500','6ed8803f-66e0-4cb4-b737-fa350d78c385'),('55e72d39-12ef-4ad0-a804-b99c9df5f065',1,28000,'c1d3612d-9740-4561-81ef-1341859f5168','316a8487-9900-4ba3-958c-049777548725'),('5d4ba24e-94e0-4200-8abd-abce0ab9730b',6,60000,'f76908d9-bc3d-4227-baa9-3f40e76307e0','03acfa86-d361-43de-b732-c5ca69ffab40'),('67d2149c-6f54-4b15-86ba-eb415371cce4',1,12000,'9341e525-4a8d-47af-a2d5-4981bd5ec1f5','5847a652-b9e7-4f60-a353-71d396f4905a'),('6f60a00c-96cc-47ed-875c-2e8063703275',1,1000,'2f4b5547-d3f0-4313-9d45-17c3892b61e6','a0ef225f-9296-465d-8714-2c31b3d478dc'),('725f3c31-31a3-4ef8-b0fc-956b1dd6b088',1,50000,'55cf9a87-de50-4b38-81eb-72b2a014a9e3','6ed8803f-66e0-4cb4-b737-fa350d78c385'),('73f40e44-f1e7-4c26-8261-d3152cd2b5cb',10,300000,'c19f757d-ba79-417d-ad4d-f88f16f4fbc3','4d66c84b-8d83-4601-9601-c8437480decc'),('8ba3d2cb-5acd-46bb-b104-8bb53fc77047',1,50000,'345eea4f-3ed1-45d3-a4f2-005df6fa05ce','6ed8803f-66e0-4cb4-b737-fa350d78c385'),('8d1b1876-abfb-490c-bdf4-2992fd83651d',2,60000,'3f3a05ef-bfd6-4baa-b5fe-55d3faeb490c','ba13cc3a-8cb2-4ea3-aedb-0f17f89cb7e6'),('91eee30f-536e-4db7-88e1-86d5d5d5aa1c',6,60000,'d02b15a4-d7f5-49bb-8a8e-d4b760df1739','03acfa86-d361-43de-b732-c5ca69ffab40'),('934e7e50-9a39-4b75-be8c-f4c0577570a4',2,60000,'aa974faa-af91-4294-9591-1e2daea1a4c3','ba13cc3a-8cb2-4ea3-aedb-0f17f89cb7e6'),('998f0b0c-d7a1-48ae-a372-8f4a6d7fecf3',16,480000,'ae97620b-639e-4ecf-b560-0e1244a1753a','77bfce36-7163-46d2-9a77-a6d131fdbe8c'),('9e828693-de94-4ba0-94d6-4e79ef01cf21',6,6000,'5b8d39dc-3e8d-4e01-a683-d36df7b81580','a0ef225f-9296-465d-8714-2c31b3d478dc'),('a6f0eccd-1085-4519-bb6c-dd343e126698',15,300000,'84b55f68-c372-4b47-9531-0bd798dadb2e','bf9ae7a3-936d-4b46-88e4-442586940439'),('aa782057-dca7-4e0b-8f85-611c10be3e2f',1,10000,'79d87016-cca0-4404-8a08-c4e565093730','fb73989f-ad74-417f-b816-01779211fda4'),('ab577003-af95-4907-b080-cfc87c54bb32',2,20000,'c1d3612d-9740-4561-81ef-1341859f5168','248cafde-53e5-4f0d-9fc6-8b931e71506b'),('afb67b15-aad8-43ed-914d-5df5989f727c',2,200000,'f4812e88-15d7-4dd4-9ff1-ac8fd86215ed','ed482de3-5b7c-4a64-a740-a07720eef5f4'),('b52c2f70-4b98-4eef-952a-e83b9153eabb',1,50000,'666c46e8-b301-449c-a824-ebe8735dd498','6ed8803f-66e0-4cb4-b737-fa350d78c385'),('b8ed9d27-888b-4db2-bbe9-22b0a4c1690c',0,0,'79d87016-cca0-4404-8a08-c4e565093730','48bcf492-b987-4973-b43c-bdc859632a60'),('c34cd8f7-9940-47f6-9e03-e3ee9c67490f',1,1000,'2f4b5547-d3f0-4313-9d45-17c3892b61e6','8f3a9cf0-9dd5-4033-83b8-cd844e1ec21c'),('c765a2a1-242a-49c0-b480-6d4d2085a750',3,9000,'2eac60b9-4b78-4764-b201-45620b32a8b3','c01c92e4-c755-45ee-993b-492fed20f9f7'),('d5c9618a-ec6b-409e-a3cd-34fdfb29f444',3,120000,'aa974faa-af91-4294-9591-1e2daea1a4c3','b1a805cf-306e-4b60-85c5-7335e3eebe13'),('e223eaca-b707-4942-8fbb-5ba42b5914b2',43,43000,'d7f39b84-90c1-451e-92a2-f050498b894f','8f3a9cf0-9dd5-4033-83b8-cd844e1ec21c'),('e6a6f8a3-695f-4e73-91d3-af20e8ef11c8',1,20000,'8879d7d7-d144-43f7-9492-afdb962421a0','85ad9131-3ce5-4245-9816-71a8583bd5b6'),('e77b1d7d-2f3c-4ed0-a670-7281471c6e65',1,10000,'a7847da3-0799-4b64-9851-d68ccb54ac8a','62edad31-386d-4a0f-ac8d-b107eced6bcc'),('ec98fea7-2ce2-4912-87c5-7a385646395e',0,0,'79d87016-cca0-4404-8a08-c4e565093730','ed482de3-5b7c-4a64-a740-a07720eef5f4'),('f00d196c-cbe7-4683-943a-2ca38b444bb5',1,15000,'c20a1c51-0655-4db0-b787-632b9c4c920e','23d66b1e-ebfe-46be-90e1-13d4a08c6971'),('f17b2db1-83fd-4272-93a6-a04869f45eee',1,30000,'366db477-e699-41cd-8cde-78aa40851ef4','ba13cc3a-8cb2-4ea3-aedb-0f17f89cb7e6'),('f91126aa-4e72-4031-86f7-39cb325128c5',4,8000,'2eac60b9-4b78-4764-b201-45620b32a8b3','4a28785c-1fad-4da1-ac23-3e2f4cc72db5');
/*!40000 ALTER TABLE `order_reward` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reward`
--

DROP TABLE IF EXISTS `reward`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reward` (
  `id` varchar(255) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `price` int DEFAULT NULL,
  `sequence` int NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `funding_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmp89o9smbgibhsd047hf818h4` (`funding_id`),
  CONSTRAINT `FKmp89o9smbgibhsd047hf818h4` FOREIGN KEY (`funding_id`) REFERENCES `funding` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reward`
--

LOCK TABLES `reward` WRITE;
/*!40000 ALTER TABLE `reward` DISABLE KEYS */;
INSERT INTO `reward` VALUES ('00c13549-e6f5-439c-b551-aca55dd08ac2','?????? ???????????? ????????? ????????? ????????? ??? ?????????~ ?????? ????????? ?????? ?????? ???????????? ?????????????????? ?????? ????????? ????????? ?????? ???????????? ???????????????!',10000,0,'60ml 5???','c248d671-0e9e-44a1-bbc9-73425d669dea'),('03acfa86-d361-43de-b732-c5ca69ffab40','???????????? ?????? 200ml*3???',10000,0,'?????? ??????','0cbb1e5f-60f1-4775-a62b-fd616df9df94'),('0cb47639-7cec-4eaa-972b-70256e599e83','?????? ?????? ???',10000,0,'?????? 1','e2a33a2d-b9dc-420d-ad1f-5568e7d929dc'),('0f65fc72-ce81-46b9-a5a2-2628e7bb1a31','\'?????? ?????? ????????????????\' * 1???',20000,0,'?????? ?????? ????????????????','7fe3eb89-8935-48ff-9e0e-d4d0932cf12b'),('10f58029-13b4-4fab-9f8f-f95244f04bea','?????????',5000,0,'??????','75bfdda5-8743-41d9-9ccc-abc52c57cd22'),('110c2fa4-e1ce-4722-a400-16eb1b82a148','???????????? ?????? 200ml*3???',10000,0,'?????? ?????? 200ml*3','0cbb1e5f-60f1-4775-a62b-fd616df9df94'),('123c0c92-c171-4c77-84ce-2f16d61a9065','?????????',30000,0,'?????????','07f1811d-1786-4270-ab6f-a6fae7c1e1cb'),('159aa954-4ce6-4ec1-92db-f8c66731401b','??????????????? 30??? * 4??????',95000,0,'??????????????? 30??? 4??????','52457548-5fcb-49c3-b6e4-7c95799e08ad'),('169f99a9-0ad3-48f1-b026-66a9f9f1c55a','?????? ??????',140000,0,'?????? ??????','5f17e6b4-3ada-48d8-a127-e0d5ef1eab87'),('23d66b1e-ebfe-46be-90e1-13d4a08c6971','?????? ??????????????? ???????????? 50ml * 1???',15000,0,'??????????????? 50ml','bbb79215-fe91-471e-bd85-a760e16f0663'),('248cafde-53e5-4f0d-9fc6-8b931e71506b','?????? ??????????????? ???????????? 30ml * 1???',10000,0,'??????????????? 30ml','bbb79215-fe91-471e-bd85-a760e16f0663'),('26d5340a-910f-4da3-908d-7ff4c4ef5761','????????? ????????? ??????',10000,0,'?????? 0','e2a33a2d-b9dc-420d-ad1f-5568e7d929dc'),('304965c3-3f68-4590-bea8-6ca1180de514','cross',5000,0,'a cross','825e9b2c-d2af-4cad-ade0-d62f039d3052'),('316a8487-9900-4ba3-958c-049777548725','?????? ??????????????? ???????????? 100ml * 1???',28000,0,'??????????????? 100ml','bbb79215-fe91-471e-bd85-a760e16f0663'),('357b45ad-cf21-4c5b-b1e5-36bf4f2432c0','5000???????????? ??????',9000,0,'???????????????','363e12f4-3950-496a-ab99-99e46d24da60'),('3a8550f9-5f75-4f8e-8487-9cd968aa46c0','1???',3000000,0,'????????? ?????????','ea38fbec-3a56-4bbe-b0ae-934fd9199743'),('3e9a61e5-f7f3-437b-9983-d6302163fd5b','JPA ??????????????? ???',39000,0,'???(??????)','881089c7-08aa-41b9-bcce-ad26f3a2fbd9'),('42c8a860-641f-4229-902c-b4b3f6e1490a','1???',5000,0,'????????????','dcaea2f7-b37f-48a0-bd13-eb50f887bf13'),('4480a122-2162-47cf-b323-0c2bb53e52e1','??????',10000,0,'????????? 1','5b8afa9b-6f1d-43c8-8e4a-edc52f1fb135'),('48bcf492-b987-4973-b43c-bdc859632a60','?????? ?????????, ????????? 50???',50000,0,'?????? ????????? 50???','ba6a963b-9f2c-43fe-83e5-1e16ff5bd718'),('4a28785c-1fad-4da1-ac23-3e2f4cc72db5','reward',2000,0,'forth','825e9b2c-d2af-4cad-ade0-d62f039d3052'),('4d66c84b-8d83-4601-9601-c8437480decc','?????????',30000,0,'?????????','dd5feef7-647b-4c26-b75d-47289d424230'),('4ecb27dd-e669-4f99-8ca3-18607a857a77','?????????',4000,0,'?????????','d2b31f79-6f4a-44bc-8ea8-3b792d21228b'),('5497c62f-cc14-42e9-bbca-69ea4d46e5b3','??????',10000,0,'??????','dca399d1-8aea-4f56-bc3a-ddfde9789c09'),('5847a652-b9e7-4f60-a353-71d396f4905a','???????????? ????????? ????????? ???',12000,0,'????????????','4e2094d5-37cf-40bd-92a4-5e5432e9b50e'),('5de01738-abf0-4302-a8f9-f1ac2daeacde','??????????????? 30??? * 2???',50000,0,'??????????????? 30??? 2??????','52457548-5fcb-49c3-b6e4-7c95799e08ad'),('5f2864bf-ecef-46cd-bf2a-da985216070e','??????',20000,0,'??????','fcee6922-b15e-4137-9c6d-b7d56e14197e'),('61a9fb51-b57b-448f-b3e0-21ddbdb90ab5','??????',3000,0,'??????????????? ?????????','d0fc1f16-ee7e-405a-a2e4-3f5f30c0884f'),('62edad31-386d-4a0f-ac8d-b107eced6bcc','?????? ????????? ?????? ??????! ???????????? ????????? ????????? GAP ??????, ?????? 1??? ?????? ?????????!',10000,0,'??????????????? 1.5KG','f761b5ed-5cde-4079-8b52-65867065fdf0'),('65981731-3965-4a31-910c-2f4778e19e95','KC ?????? ??? ??????: R-R-MMn-SY5540, ??????: 70ml',40000,0,'????????? ???????????????','608ab9f0-96ce-42ca-8510-7b1a42034a9f'),('67fdf206-19ad-4521-9a7a-f55e89145ca9','??????????????????',19000,0,'??????????????????','8609c8b4-cb19-42ed-8ec0-7a8128bebd3f'),('68ef3ddf-636c-40c7-aa7e-06922ef89276','???',30000,0,'??????','9a17c50e-eaab-4009-a5bb-9a6e78134e1d'),('6b775c27-9160-4baf-b035-c01ed6f6f773','\'?????? ????????? ????????????????\' * 1???',20000,0,'?????? ????????? ????????????????','7fe3eb89-8935-48ff-9e0e-d4d0932cf12b'),('6ed8803f-66e0-4cb4-b737-fa350d78c385','?????? ????????? ?????? ??????! ???????????? ????????? ????????? GAP ??????, ?????? 1??? ?????? ?????????!',50000,0,'??????????????? 10KG','f761b5ed-5cde-4079-8b52-65867065fdf0'),('71372cc4-f931-4e01-9177-16483ca80d4a','9?????? ????????? ???????????? ????????? AI ????????? ??????',1000000,0,'AI ????????? v1.0','3bdf321e-d9c5-480a-b707-dbe0e20ed8d1'),('77bfce36-7163-46d2-9a77-a6d131fdbe8c','?????????',30000,0,'?????????','a5ad0047-e0c8-4151-8f49-be18d8880e1c'),('78a553c9-1c85-4e1d-87c7-2a4cd947be2e','for church',5000,0,'a gown','825e9b2c-d2af-4cad-ade0-d62f039d3052'),('78d42ccb-f6ae-42fc-97dd-87cea33bcc4f','???,???,?????? ??? ??????',55000,0,'??? ?????? ??????','7fe3eb89-8935-48ff-9e0e-d4d0932cf12b'),('7d12e71f-db91-46e7-b986-3ab8387aeeaa','?????? ????????? 10???',10000,0,'10??? ??????','8ca878de-b0f1-4211-af0b-252a4c0a595d'),('8170c5ac-aa8c-463a-90e8-c964f448bcb1','?????? ???????????? ????????? ????????? ????????? ??? ?????????~ ?????? ????????? ?????? ?????? ???????????? ?????????????????? ?????? ????????? ????????? ?????? ???????????? ???????????????!',25000,0,'1000ml 1???','c248d671-0e9e-44a1-bbc9-73425d669dea'),('83d61c17-711c-46ec-8fc4-74e97de1e061','?????????',3000,0,'??????','ad7faf8f-3ac9-4f95-b170-f1b5dad26db3'),('85ad9131-3ce5-4245-9816-71a8583bd5b6','?????? ????????? ?????? ??????! ???????????? ????????? ????????? GAP ??????, ?????? 1??? ?????? ?????????!',20000,0,'??????????????? 3KG','f761b5ed-5cde-4079-8b52-65867065fdf0'),('88e2770c-5924-480b-9169-4a01b9b8d174','?????? ???????????? ????????? ????????? ????????? ??? ?????????~ ?????? ????????? ?????? ?????? ???????????? ?????????????????? ?????? ????????? ????????? ?????? ???????????? ???????????????!',23000,0,'100ml 12???','c248d671-0e9e-44a1-bbc9-73425d669dea'),('8f3a9cf0-9dd5-4033-83b8-cd844e1ec21c','????????? ?????????',1000,0,'?????????','3d91dcd1-f584-4cde-9e7e-70e262e7896b'),('918c3e27-f047-4c9f-a043-015a8fdd1cdd','????????? ??????????????? 100??? * 10???',30000,0,'????????? ??????????????? 10???','0fe1076b-3ce5-42dc-8088-61b8fa3db9cb'),('a0ef225f-9296-465d-8714-2c31b3d478dc','????????? ?????????',1000,0,'?????????','3d91dcd1-f584-4cde-9e7e-70e262e7896b'),('a4f86938-c04c-4409-bbd6-b6b2b654293b','????????? ??????????????? 100??? *  20???',55000,0,'????????? ??????????????? 20???','0fe1076b-3ce5-42dc-8088-61b8fa3db9cb'),('b1a805cf-306e-4b60-85c5-7335e3eebe13','??????, ?????????, ????????? ??? ??? 1 ??????. ?????? ??? ?????? ???????????????.',40000,0,'?????? ????????? ?????????','dc8c854e-3cbc-47da-a4ec-8dba21a15048'),('ba13cc3a-8cb2-4ea3-aedb-0f17f89cb7e6','?????????, ?????????, ??????, ???????????? ??? ?????? ??????. ?????? ??? ?????? ???????????????.',30000,0,'?????? ?????? ????????? ??????','dc8c854e-3cbc-47da-a4ec-8dba21a15048'),('bf9ae7a3-936d-4b46-88e4-442586940439','????????? 20??? + ?????? 2???',20000,0,'20??? ??????','8ca878de-b0f1-4211-af0b-252a4c0a595d'),('c01c92e4-c755-45ee-993b-492fed20f9f7','bestseller',3000,0,'a bible','825e9b2c-d2af-4cad-ade0-d62f039d3052'),('c9128cec-bdd7-40f8-8287-f58044affb8e','\'????????? ??? ????????????????\' * 1???',20000,0,'????????? ??? ????????????????','7fe3eb89-8935-48ff-9e0e-d4d0932cf12b'),('d585ba94-1eb5-43d5-a1c7-3b94fd573f77','?????????',50000,0,'??????????????????','c6407a5c-4115-4750-9a4f-a61fe92cd56d'),('daec83e9-93b1-4248-b019-4c98f3b15761','1???',300000,0,'????????????','85f8d18a-294e-4460-b6f8-1ee82ebfec85'),('db54fe5a-4362-461a-b6a2-6229caec8e7d','?????? ????????? ?????? ??????! ???????????? ????????? ????????? GAP ??????, ?????? 1??? ?????? ?????????!',25000,0,'??????????????? 5KG','f761b5ed-5cde-4079-8b52-65867065fdf0'),('e06f0ac0-3de7-4bcb-b68d-1b7c7b4b5c9b','?????????',3000,0,'??????','194defde-4717-494b-9b49-d043a6ad5c6d'),('e22c3ddb-1f26-47fd-a7db-c44509e9a8a7','?????? ????????? ??????????????? ???????????? ??????',49000,0,'??? + ??????','881089c7-08aa-41b9-bcce-ad26f3a2fbd9'),('ed482de3-5b7c-4a64-a740-a07720eef5f4','?????? ?????????, ????????? 100???',100000,0,'?????? ????????? 100???','ba6a963b-9f2c-43fe-83e5-1e16ff5bd718'),('efdf794d-9efd-43ad-838f-234fc6ca8716','??????????????? ????????? ??????',10000,0,'????????? ??????','4e2094d5-37cf-40bd-92a4-5e5432e9b50e'),('f961a326-7ae4-43b0-90db-20e33a01126f','1???',4000,0,'????????????','5fdc8570-6c26-4108-bc6d-e8f9cd53da78'),('fb73989f-ad74-417f-b816-01779211fda4','?????? ????????? ????????? 10???',10000,0,'?????? ????????? 10???','ba6a963b-9f2c-43fe-83e5-1e16ff5bd718');
/*!40000 ALTER TABLE `reward` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tag` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` varchar(255) NOT NULL,
  `authority` varchar(255) DEFAULT NULL,
  `background` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `is_admin` bit(1) NOT NULL DEFAULT b'0',
  `is_delete` bit(1) NOT NULL DEFAULT b'0',
  `join_date` date DEFAULT NULL,
  `kakao_sid` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `provider` varchar(255) DEFAULT NULL,
  `public_key` varchar(255) DEFAULT NULL,
  `refresh_token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('13af8b68-a180-4448-945f-1ce7ce6de896','ROLE_USER',NULL,'rdt2359@gmail.com',_binary '\0',_binary '\0','2022-10-06',NULL,'lallal','ssafy','DD','2272e13ad757fdd018d565c3ebaf6404452d18b7d973514c0e462dab5cb96e96','eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NjUxMDQ2NTcsImV4cCI6MTY2NTcwOTQ1N30.bKU7h1NY7W9Y1P9E_e3hFNplal7jHJXsDlog4OtPnKg'),('3a01dac2-7873-4e9c-b833-b72ee0b73607','ROLE_USER',NULL,'qwerty1434@naver.com',_binary '\0',_binary '\0','2022-10-06',NULL,'?????????','ssafy','DD','6206d34499b2ccef38fcc06e530bc37b8491b85487514af76eb7abe962aa6d5f','eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NjUwNjcwMTksImV4cCI6MTY2NTY3MTgxOX0.3gmzrRj48sd3lrCgW1NBOSTjOm8wFMUvcJ35kESk97Y'),('42f7dd7b-68a1-480f-84cd-cb2757feb55d','ROLE_USER',NULL,'leemyo_@naver.com',_binary '\0',_binary '\0','2022-10-06',NULL,'2m','ssafy','DD','466a5a841747b548c9f35dd15b570f44a69d0a5da9d64f3f700afc0c22d54308','eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NjUwNzAxNzAsImV4cCI6MTY2NTY3NDk3MH0.BG4LICVSZbxujXwKs9NFwOTrNCKYWUUryjXYKu4m1Vs'),('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa','ROLE_USER',NULL,'consultant@ssafy.com',_binary '\0',_binary '\0','2022-10-10',NULL,'???????????????','consultant','DD','ed499159aa7a15f19b2fb374686fad9d0d94d8c7bde80cecf5ee98c2a1b20513','eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NjQ5NzI2OTcsImV4cCI6MTY2NTU3NzQ5N30.6jU6ugT9-azK9VBonqz58AFCbI89ovc41enwqUhkfA4'),('baa7ea8c-981b-4ef5-b828-a0dc5735ab40','ROLE_USER',NULL,'hyungi4608@gmail.com',_binary '\0',_binary '\0','2022-10-06',NULL,'??????','ssafy','DD','5bab2a8e5e971f43ee79b6ce971f818bc85157ba3c93608b451f4e4a4177e894','eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NjUwMzk4MzMsImV4cCI6MTY2NTY0NDYzM30.1oRXs702--jiXQVmo6qQyYbRK6lY4sVe_R30X_LJFQQ'),('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb','ROLE_USER',NULL,'coach@ssafy.com',_binary '\0',_binary '\0','2022-10-05',NULL,'?????????','coach','DD','99f512d1de76e20d5196dc4bb78578431bd8f6a78c82a2286162f4c8bb7fd34b','eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NjUwNjY5NzAsImV4cCI6MTY2NTY3MTc3MH0.V5UH5ggZW6BA_tgpN7--uNQQa4Tgf4PRF_X8W3Ca7Dw'),('d668edd8-3f71-45f4-acf9-983bbd8f29e2','ROLE_USER',NULL,'yi_jonghoon@naver.com',_binary '\0',_binary '\0','2022-10-06',NULL,'????????????','ssafy','DD','b4ff5b4ba232a776f363621c39cab6c39e052ad68f173d50b27f843c4d1d1a70','eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NjUwMzU5NTAsImV4cCI6MTY2NTY0MDc1MH0.kVM5BySo4aXNrURFTq-tDbLBmg_Rrp3j-IZquVkQBWA'),('e3139b60-c38f-46f2-a05a-c8420db51330','ROLE_USER',NULL,'dlwhd9635@naver.com',_binary '\0',_binary '\0','2022-10-06',NULL,'????????????','ssafy','DD','7d22a3e20ea5bcbb6068919b51f194780fce51c9f68a835a79383c724a7c742b','eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NjUwNDQ2NTEsImV4cCI6MTY2NTY0OTQ1MX0.MSga6Df3iMbEIawVjsWmerzFDixdXK_p1wVjRI1gX-E');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `withdraw_form`
--

DROP TABLE IF EXISTS `withdraw_form`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `withdraw_form` (
  `id` varchar(255) NOT NULL,
  `amount_used` int NOT NULL,
  `date` datetime(6) NOT NULL,
  `is_delete` bit(1) NOT NULL DEFAULT b'0',
  `process` varchar(255) NOT NULL,
  `purpose` varchar(255) NOT NULL,
  `refer_reason` varchar(255) DEFAULT NULL,
  `funding_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKt4dbbwfwajg60qqui7eh4eqdl` (`funding_id`),
  CONSTRAINT `FKt4dbbwfwajg60qqui7eh4eqdl` FOREIGN KEY (`funding_id`) REFERENCES `funding` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `withdraw_form`
--

LOCK TABLES `withdraw_form` WRITE;
/*!40000 ALTER TABLE `withdraw_form` DISABLE KEYS */;
INSERT INTO `withdraw_form` VALUES ('a809a8e8-16a5-4a19-99a2-01c578e325b5',70000,'2022-10-06 16:23:54.654000',_binary '\0','?????????','?????? ??????',NULL,'dc8c854e-3cbc-47da-a4ec-8dba21a15048'),('fdeb3b3a-df3e-4ac2-80da-ead8013a3849',30000,'2022-10-06 16:30:40.031000',_binary '\0','?????????','????????? ?????? ??????',NULL,'dc8c854e-3cbc-47da-a4ec-8dba21a15048');
/*!40000 ALTER TABLE `withdraw_form` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-10-07 11:05:35
