pipeline {
    agent any
    
    
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/juanda2984/inAtlas.git'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    docker.build('inatlas_imagen:tag', '.')
                }
            }
        }
        stage('Push to Docker Hub') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'docker-hub-credentialss', passwordVariable: 'Juanda9770794', usernameVariable: 'juanda2984')]) {
                        docker.withRegistry('https://registry.hub.docker.com', 'docker-hub-credentialss') {
                            docker.image('inatlas_imagen:tag').push('latest') // Sube la imagen Docker a Docker Hub
                        }
                    }
                }
            }
        }
    }
}