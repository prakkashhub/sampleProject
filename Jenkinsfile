pipeline {
    agent none
    
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
        
        
        }

    }

  }   