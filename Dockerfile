# Utiliser une image Maven avec Java 17
FROM maven:3.9.6-eclipse-temurin-17

# Définir le répertoire de travail dans le conteneur
WORKDIR /app

# Copier les fichiers du projet dans le conteneur
COPY . /app

# Télécharger les dépendances Maven
RUN mvn clean install -DskipTests

# Lancer les tests Cucumber avec Maven
CMD ["mvn", "clean", "test"]
