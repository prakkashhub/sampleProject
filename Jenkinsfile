pipeline {
    agent none
    options {

    buildDiscarder(
        logRotator(
            // number of build logs to keep
            numToKeepStr:'5',
            // history to keep in days
            daysToKeepStr: '15',
            // artifacts are kept for days
            artifactDaysToKeepStr: '15',
            // number of builds have their artifacts kept
            artifactNumToKeepStr: '5'
        )
    )
}

    stages {
        stage('Checkout') {
           agent any
            steps {
                checkout scm
               
            }
        }

        stage('compile') { 
            agent any
            steps {
               sh "mvn -version"
               sh "mvn compile"
            }
        }
        
		stage('Unit Tests'){
        	agent any
        	steps {
        		sh 'mvn test'
        	}
        }
        
        
        stage('Sonarqube quality scan') {
            agent any
            steps {
                withSonarQubeEnv('sonarqube') {
                        sh 'ls -la'
                        sh 'mvn clean package sonar:sonar'
                }
            }
        }

        stage("Quality Gate") {
            steps {
                timeout(time: 1, unit: 'MINUTES') {
                    // Parameter indicates whether to set pipeline to UNSTABLE if Quality Gate fails
                    // true = set pipeline to UNSTABLE, false = don't
                    waitForQualityGate abortPipeline: true
                }
            }
        }       

        stage('Build Package') {
            agent any
            steps {
                sh "mvn -U clean install -Dmaven.test.skip=true"
                sh "ls"
                sh "mvn -version"
            }
        }
        
        stage('Publish Docker image') {
            agent any
            steps{
                script {
                    dockerImage = docker.build "sample-project"
                  //  docker.withRegistry('https://011756743573.dkr.ecr.us-east-1.amazonaws.com', 'ecr:us-east-1:demo-ecr-credentials') {
                    docker.image('sample-project').push('latest')
                 }
                }
            }
        }

    }

  }   

