pipeline {
  agent any
  stages {
    stage('Log Tool Version') {
      parallel {
        stage('Log Tool Version') {
          steps {
            sh '''mvn --version


'''
          }
        }

        stage('Check for POM') {
          steps {
            fileExists 'pom.xml'
          }
        }

      }
    }

    stage('Validate Compile') {
      steps {
        sh 'mvn validate compile'
      }
    }

    stage('Smoke tests') {
      steps {
        sh 'mvn test'
      }
    }

    stage('Generate Test Report') {
      steps {
        sh 'mvn surefire-report:report'
      }
    }

  }
}