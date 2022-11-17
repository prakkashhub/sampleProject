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
               sh "/usr/local/Cellar/maven/3.8.6/libexec/bin/mvn -version"
               sh "/usr/local/Cellar/maven/3.8.6/libexec/bin/mvn compile"
            }
        }
        
		stage('Unit Tests'){
        	agent any
        	steps {
        		sh '/usr/local/Cellar/maven/3.8.6/libexec/bin/mvn test'
        	}
        }
        
        
       

        stage('Build Package') {
            agent any
            steps {
                sh "/usr/local/Cellar/maven/3.8.6/libexec/bin/mvn -U clean install -Dmaven.test.skip=true"
                sh "ls"
                sh "/usr/local/Cellar/maven/3.8.6/libexec/bin/mvn -version"
            }
        }
        
        stage('Docker Build') {
            agent any
            steps {
                sh "/usr/local/bin/docker build -t prakkash/sampleProject . "
                
            }
        }
        
        }

    }

  