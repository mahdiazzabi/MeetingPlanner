Exemple de requête curl

Voici un exemple de requête curl pour réserver une salle pour 4 personnes le 20 mars 2025 de 9h00 à 10h00 :

curl -X POST "http://localhost:8080/api/reservations/reserver?date=2025-03-20&startHour=9&endHour=10&nombreDePersonnes=4"
