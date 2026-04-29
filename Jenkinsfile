pipeline {
    agent any

environment {
        DOCKER_HUB_USER = 'snucci'
        APP_NAME        = 'myapi'
        DOCKER_CREDS    = 'docker_hub_credential'
    }



    stages {


        stage('Initialize Docker') {
            steps {
                script {
                    // Assicurati che 'docker-stable2' sia una versione RECENTE nei Tools di Jenkins (es. 24.0.x)
                    def dockerHome = tool name: 'docker-stable2', type: 'org.jenkinsci.plugins.docker.commons.tools.DockerTool'
                    env.DOCKER_BIN = "${dockerHome}/bin/docker"

                    // Forza i permessi di esecuzione sul binario scaricato
                    sh "chmod +x ${env.DOCKER_BIN}"

                    // Specifichiamo l'host per evitare errori di connessione
                    env.DOCKER_HOST = "unix:///var/run/docker.sock"

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
                script {
                    // Usiamo il binario specifico che abbiamo appena inizializzato
                    withCredentials([usernamePassword(credentialsId: env.DOCKER_CREDS, usernameVariable: 'U', passwordVariable: 'P')]) {
                        sh "echo \$P | ${env.DOCKER_BIN} login -u \$U --password-stdin"
                        sh "${env.DOCKER_BIN} build -t ${DOCKER_HUB_USER}/${APP_NAME}:${env.BUILD_ID} ."
                        sh "${env.DOCKER_BIN} push ${DOCKER_HUB_USER}/${APP_NAME}:${env.BUILD_ID}"
                        sh "${env.DOCKER_BIN} push ${DOCKER_HUB_USER}/${APP_NAME}:latest"
                    }
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