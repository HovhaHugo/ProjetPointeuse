# ProjetPointeuse
- **Projet réaliser dans le cadre de la 3éme année d'école d'ingénieur à Polytech Tours**
- **Auteurs : Hugo Hovhannessian, Julien Mathia, Clément Dufau, Aimad Abdelouhab**
- **Date de début du projet : 5 Mai 2022**
- **Date de fin du projet : 12 juin 2022**

## Organisation du projet : 
Le projet est découpé en 3 parties : 
- application principale : Gestion de la company, des pointages, des employees, des paramètres, avec gestion des plannings en heures et jours
- Simulateur de pointeuse : Model et vue permettant l'affichage des employees pour un pointage sans risque, et envoie des pointages à l'application principale
- Parti commun : Model correspondant aux heures, a une liste simplifiée des employees et des pointages.


## Utilisation du projet : 
Pour lancer le projet il suffit de lancer le Main dans le **package (default package)**, il lancera automatiquement les 2 applications. 
Pour relancer le programme et éviter les doublons, commenter la **ligne 14 du fichier Main.java comportant le code suivant : "createCompany();"**.
Comme l'application simule une entreprise, les employees ne travaillant pas le week-end, l'application ne fonctionnera pas si le test est effectuer le week-end. Pour remédier à cela il est possible de modifier l'heure de votre ordinateur pour tester les fonctionnalités.

## Ce qui ne marche pas : 
- Le calcule des heure ce fait avec des heures entiére au lieu des heures arrondies
- La recherche de scores ou d'employer ne fonctionne pas

## Diagramme de classe du projet : 
![Diagramme_de_classe_Turbo](https://user-images.githubusercontent.com/92187603/173245821-da484f0f-d1eb-4e2e-b1b0-18657fb599a4.jpeg)

## Lien de la vidéo youtube :
https://www.youtube.com/watch?v=QJpxa5UXSn4
