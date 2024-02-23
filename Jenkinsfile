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
                    app = docker.build('juanda2984/inatlas_imagen:tag', '.')
                }
            }
        }
        stage('Push to Docker Hub') {
            steps {
                script {
                    withDockerRegistry([ credentialsId: "docker-hub-credentials", url: "https://index.docker.io/v1/" ]) {
                        app.push() // Sube la imagen Docker a Docker Hub
                    }
                }
            }
        }
    }
}