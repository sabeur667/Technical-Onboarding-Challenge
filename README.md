# Technical-Onboarding-Challenge

# Gestion des Transactions de Carte de Crédit

Ce projet vise à fournir une API RESTful pour gérer les transactions de carte de crédit. L'API permet de lister, filtrer et trier les transactions, ainsi que de mettre en place une pagination pour faciliter la gestion des données.

## Fonctionnalités Principales

- Liste des Transactions: Endpoint pour récupérer une liste de transactions de carte de crédit.
- Possibilité de filtrer les transactions par montant, marchand et statut (approuvé, refusé, en attente).
- Possibilité de trier les transactions par montant, marchand et statut.
- Mise en place de la pagination avec les paramètres de requête page et pageSize.

## Installation

1. Cloner le repository:

   ```bash
   git clone https://github.com/sabeur667/Technical-Onboarding-Challenge.git

2. Accéder au répertoire du projet:

   ```bash
   cd Technical-Onboarding-Challenge
   
3. Construire et exécuter l'application:

   ```bash
   ./mvnw spring-boot:run
  L'application sera accessible à l'URL http://localhost:7080.

  ## Utilisation de l'API
  ### Liste des Transactions
#### Endpoint: GET /listTransactions
#### Paramètres de Requête:
  -  amount : Montant de la transaction (optionnel)
  -  merchant : Nom du marchand (optionnel)
  -  status : Statut de la transaction (approuvé, refusé, en attente) (optionnel)
  -  page : Numéro de la page pour la pagination (par défaut: 1)
  -  pageSize : Taille de la page pour la pagination (par défaut: 5)
  -  sortBy : Champ de tri (amount, merchant, status) (optionnel)
  -  sortD : Direction de tri (asc, desc) (optionnel)

 Example URL: http://localhost:7080/Transactions?merchant=Amazon&status=approved&page=1&pageSize=10&sortBy=amount&sortD=asc.
 
 Documentation API: http://localhost:7080/swagger-ui/index.html.

   ## Technologies Used
   -  Spring Boot: Java framework for web application development.
   -  Swagger: Documentation and testing tool for REST APIs.
   -  Gson: Library for JSON data manipulation.
