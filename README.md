Exemple de requête curl

Voici un exemple de requête curl pour réserver une salle pour 4 personnes le 20 mars 2025 de 9h00 à 10h00 :

curl -X POST "http://localhost:8080/api/reservations/reserver?date=2025-03-20&startHour=9&endHour=10&nombreDePersonnes=4"

L'API, dans son état actuel, renvoie une salle disponible dans le planning en fonction du nombre de personnes approprié.

Les contraintes sur les types de réunion et l’équipement de la salle ne sont pas encore implémentées.
=>  Une itération supplémentaire de développement et de tests sera nécessaire pour intégrer ces spécifications (temps estimé : minimum 2 heures).

NB : La couche de persistance utilise, pour l’instant, une liste statique à des fins d’exemple.
=>  Il serait plus approprié d’utiliser une base H2 ou d’implémenter Hibernate.
=>  TODO : Ajouter des tests d’intégration.
