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
        
        stage('Run Container') {
            steps {
                script {
                   def existingContainer = sh(script: 'docker ps -aqf "name=inatlas_container"', returnStdout: true).trim()
		            if (existingContainer) {
		                sh "docker stop ${existingContainer}"
		                sh "docker rm ${existingContainer}"
		            }
                    docker.image('juanda2984/inatlas_imagen:tag').run('-p 8081:8080 --name inatlas_container')
                }
            }
        }
        
        stage('Check Container Status') {
		    steps {
		        script {
		            def containerStatus = sh(script: 'docker ps --filter "name=inatlas_container" --format "{{.Status}}"', returnStdout: true).trim()
		            echo "Container Status: ${containerStatus}"
		        }
		    }
		}
    }
}