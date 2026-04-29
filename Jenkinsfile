pipeline {
    agent any

environment {
        DOCKER_HUB_USER = 'snucci'
        APP_NAME        = 'myapi'
        DOCKER_CREDS    = 'docker_hub_credential'
    }



    stages {


        stage('Initialize Tools') {
            steps {
                script {
                    def dockerHome = tool name: 'docker-stable2', type: 'org.jenkinsci.plugins.docker.commons.tools.DockerTool'
                    env.DOCKER_BIN = "${dockerHome}/bin/docker"

                    // Definiamo l'host per tutti i comandi successivi
                    env.DOCKER_HOST = "unix:///var/run/docker.sock"

                    echo "Controllo Connessione al Daemon..."
                    sh "${env.DOCKER_BIN} version"
                }
            }
        }

        stage('Build') {
            steps {
                echo 'Building..'
                sh './mvnw clean package -DskipTests'
            }
        }





stage('Docker Build & Push') {
            steps {
                // Usiamo withCredentials per gestire il login in sicurezza senza il plugin Docker
                withCredentials([usernamePassword(credentialsId: env.DOCKER_CREDS, usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    sh """
                        # Login manuale
                        echo \$DOCKER_PASS | ${env.DOCKER_BIN} login -u \$DOCKER_USER --password-stdin

                        # Build
                        ${env.DOCKER_BIN} build -t ${env.DOCKER_HUB_USER}/${env.APP_NAME}:${env.BUILD_ID} .
                        ${env.DOCKER_BIN} tag ${env.DOCKER_HUB_USER}/${env.APP_NAME}:${env.BUILD_ID} ${env.DOCKER_HUB_USER}/${env.APP_NAME}:latest

                        # Push
                        ${env.DOCKER_BIN} push ${env.DOCKER_HUB_USER}/${env.APP_NAME}:${env.BUILD_ID}
                        ${env.DOCKER_BIN} push ${env.DOCKER_HUB_USER}/${env.APP_NAME}:latest

                        # Logout per sicurezza
                        ${env.DOCKER_BIN} logout
                    """
                }
            }
        }


        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}