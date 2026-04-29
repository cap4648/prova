pipeline {
    agent any

environment {
        DOCKER_HUB_USER = 'snucci'
        APP_NAME        = 'myapi'
        DOCKER_CREDS    = 'docker_hub_credential'
    }



    stages {


        stage('Check Docker Environment') {
            steps {
                script {
                    // Verifichiamo se il comando docker di sistema funziona
                    sh 'docker version'
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
                    // Usiamo il plugin Docker standard che ora troverà il docker di sistema
                    docker.withRegistry('', DOCKER_CREDS) {
                        def myImage = docker.build("${DOCKER_HUB_USER}/${APP_NAME}:${env.BUILD_ID}")
                        myImage.push()
                        myImage.push('latest')
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