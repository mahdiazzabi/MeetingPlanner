Exemple de requête curl

Voici un exemple de requête curl pour réserver une salle pour 4 personnes le 20 mars 2025 de 9h00 à 10h00 :

curl -X POST "http://localhost:8080/api/reservations/reserver?date=2025-03-20&startHour=9&endHour=10&nombreDePersonnes=4"

L'API, dans son état actuel, renvoie une salle disponible dans le planning en fonction du nombre de personnes approprié, le planning et renvoi une salle avec l'équipement nécessaire au type de réuinion.

Les contraintes sur les types de réunion et l’équipement de la salle sont à améliorer (Vérifier, tester et compléter) (ceci est un exmple d'implémentation)

NB : La couche de persistance utilise, pour l’instant, une liste statique à des fins d’exemple.
=> Il serait plus approprié d’utiliser une base H2 ou d’implémenter Hibernate.
=> TODO : Ajouter des tests d’intégration.
=> TODO Refactor architecture : Séparer les objets des layer...
=> TODO Définir les requests et les controles de saisie

Au plaisir d'échanger sur la solution.
