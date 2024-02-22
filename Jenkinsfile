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
	        	withCredentials([usernamePassword(credentialsId: 'docker-hub-credentials', passwordVariable: 'Juanda9770794', usernameVariable: 'juanda2984')]) {
	            	script {
	            		docker.withRegistry('https://index.docker.io/v1/', 'docker-hub-credentials') {
		                    dockerImage.push('latest') // Sube la imagen Docker a Docker Hub
		                    //sh 'docker push inatlas_imagen:tag'
		                    //docker.image('inatlas_imagen:tag').push("Push de la imagen a Docker Hub")
		                }
	               	}
	            }
            }
        }
    }
}