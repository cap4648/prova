pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
                sh './mvnw clean package -DskipTests'
            }
        }

stage('Docker Build & Push') {
    steps {
        script {

            // usa docker interno
            dockerTool 'docker-stable'

            // docker.withRegistry(URL, CREDENTIALS_ID)
            // Se lasci l'URL vuoto '', Jenkins usa di default Docker Hub
            docker.withRegistry('', 'docker_hub_credential') {

                // 1. Build: crea l'immagine leggendo il Dockerfile nel path corrente (.)
                def myImage = docker.build("snucci/myapi:${env.BUILD_ID}")

                // 2. Push: invia l'immagine con il tag specifico dell'ID build
                myImage.push()

                // 3. Tag aggiuntivo: è buona norma aggiornare anche il tag 'latest'
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