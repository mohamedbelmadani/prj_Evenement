Documentation du Projet "Gestion des Événements"

Introduction

Le projet "Gestion des Événements" est une application développée en Java avec le framework Spring Boot. Cette application permet de créer, gérer et consulter des événements.
Le projet inclut des tests unitaires pour assurer la fiabilité, l'utilisation de Docker pour la conteneurisation, et Jenkins pour l'intégration et le déploiement continu.


Fonctionnalités Principales

Gestion des Événements :

        Création d'événements avec des informations comme le nom, la description, la date, le lieu, le nombre maximal de participants, etc.

        Consultation et modification des événements.

API REST :

        Les opérations CRUD sont exposées via des endpoints REST.

Sécurité :

        Implémentation de l’authentification et de la validation des données.


Architecture du Projet

Backend : Spring Boot, avec une structure en couches (Controller, Service, Repository).

Base de données : Utilisation de SQLServer.

Tests : JUnit 5 et Mockito pour les tests unitaires.

Outils de Conteneurisation : Docker.

CI/CD : Jenkins pour le déploiement automatisé.


Tests Unitaires:

        Les tests unitaires vérifient la fonctionnalité des contrôleurs et services.

Exemple de Test Unitaire :

@SpringBootTest(classes =  PrjEventsApplication.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class) 
class EventControllerTest {
    @Mock
    private EventService eventService;
    @InjectMocks
    private EventController eventController;
    @Autowired
    private MockMvc mockMvc;
    @Test
    void shouldSaveAndRetrieveEvent() throws Exception {
        Event newEvent = new Event(null, "Event4", "Description4", new Date(), "Location4", 200, 20);
        mockMvc.perform(post("/api/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Event4\", \"description\":\"Description4\", \"date\":\"2024-01-01T00:00:00\", \"location\":\"Location4\", \"maxParticipants\":200, \"currentParticipants\":20}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Event4"))
                .andExpect(jsonPath("$.description").value("Description4"))
                .andExpect(jsonPath("$.location").value("Location4"));
    }
}

*******************************************************************
Dockerfile :

Le fichier Dockerfile permet de créer une image pour l'application Spring Boot.


FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]

***********************************************************

Docker Compose :

Un fichier docker-compose.yml est utilisé pour orchestrer l'application et la base de données.
version: '3.8'
services:
  jenkins:
    image: jenkins/jenkins:lts
    container_name: jenkins
    ports:
      - "8000:8080"
      - "50000:50000"
    volumes:
      - jenkins_home:/var/jenkins_home
      - /var/run/docker.sock:/var/run/docker.sock
    environment:
      - JAVA_OPTS=-Djenkins.install.runSetupWizard=false
volumes:
  jenkins_home:


![dockerfile_project](https://github.com/user-attachments/assets/e89e3c63-7f0d-4d22-803f-bc5666781340)


  

*****************************************************************


Intégration et Déploiement Continu avec Jenkins

Pipeline Jenkins :

    Le pipeline exécute les étapes suivantes :

    Compilation du projet avec Maven.

    Exécution des tests unitaires.

    Construction de l'image Docker.

    Déploiement de l'application.

Exemple de Jenkinsfile :

  pipeline {
    agent any

    environment {
        // Define any required environment variables here
        GIT_URL = 'https://github.com/mohamedbelmadani/prj_Evenement.git'
        GIT_BRANCH = 'main'
    }

    stages {
        stage('Checkout') {
            steps {
                git url: "${GIT_URL}", branch: "${GIT_BRANCH}"
            }
        }

        stage('Test') {
            steps {
                // Replace this with your test commands
                bat 'echo "Running tests..."'
            }
        }

        stage('Build') {
            steps {
                // Replace this with your build commands
                bat 'echo "Building project..."'
            }
        }

        stage('Deploy') {
            steps {
                // Replace this with your deploy commands
                bat 'echo "Deploying project..."'
            }
        }
    }

    post {
        always {
            // Cleanup or notifications
            echo 'Pipeline completed.'
        }
        success {
            echo 'Pipeline succeeded.'
        }
        failure {
            echo 'Pipeline failed.'
        }
    }
}

![jenkins_verify](https://github.com/user-attachments/assets/42b7d3a8-a3b6-41b5-b016-ba76378193b6)

![jenkins_verify2](https://github.com/user-attachments/assets/2a61da6b-45c1-45c7-9391-568225e29b5b)



***********************************************************************
Déploiement

Le déploiement est effectué en utilisant Docker Compose pour exécuter les services de l'application (API et base de données). Jenkins assure que chaque nouvelle version est testée et déployée automatiquement.
