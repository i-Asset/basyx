pipeline {
  agent {
    kubernetes {
      label 'my-agent-pod'
      yaml """
apiVersion: v1
kind: Pod
spec:
  containers:
  - name: postgresql
    image: postgres:latest
    tty: true
    environment:
       POSTGRES_PASSWORD: admin
  - name: cmake
    image: rikorose/gcc-cmake:latest
    command:
    - cat
    tty: true
  - name: maven
    image: maven:latest
    command:
    - cat
    tty: true
"""
    }
  }
  stages {
    stage('Run maven') {
      steps {
        container('postgresql') {
        sh '''
           psql -U postgres -f "ci/create_postgres.txt"
        '''
        }
        container('maven') {
          sh '''
JAVA_FILES_CHANGED=$(/usr/bin/git diff --name-only origin/development | grep ".*/sdks/java/.*" | wc -l)
if [ $((JAVA_FILES_CHANGED > 0)) ];
then
    mvn -f ./sdks/java/basys.sdk/pom.xml clean verify
	mvn -f ./components/basys.components/pom.xml clean verify
fi
        '''
        }
      }
    }
  }
}

