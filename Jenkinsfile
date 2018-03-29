pipeline {
    agent {
        docker {
            image 'jdk-8'
        }
    }
    stages {
        stage('Build') { 
            steps {
                sh './demo/mvnw -DskipTests clean package' 
            }
        }
    }
}
