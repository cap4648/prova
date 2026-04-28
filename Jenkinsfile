pipeline {
    agent any

    stages {
        stage('Build') {
   steps {
         // "maven3" deve corrispondere al nome dato nei Tools
         withMaven(maven: 'maven3') {
             sh "mvn clean package -DskipTests"
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