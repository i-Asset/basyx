pipeline {
  agent {
    kubernetes {
      label 'basyx-' + env.BRANCH_NAME + '-' + env.BUILD_NUMBER
      yaml """
apiVersion: v1
kind: Pod
spec:
  containers:
  - name: postgresql
    image: postgres:latest
    command: [ "/usr/local/bin/uid_entrypoint", cat ]
	args: [ "cat" ]
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
           initdb -D "postgres" -U postgres
           pg_ctl -D "postgres" start 
           psql -U postgres -f "ci/create_postgres.txt"
        '''
        }
        container('maven') {
          sh '''
GIT_DIFF=$(/usr/bin/git diff --name-only origin/CI_Test)
JAVA_SDK_CHANGED=$(echo $GIT_DIFF | grep ".*/sdks/java/.*" | wc -l)
JAVA_COMPONENTS_CHANGED=$(echo $GIT_DIFF | grep "components/.*" | wc -l)
if [ $((JAVA_SDK_CHANGED > 0)) ];
then
    mvn -f ./sdks/java/basys.sdk/pom.xml clean verify
    mvn -f ./components/basys.components/pom.xml clean verify
elif [ $((JAVA_COMPONENTS_CHANGED > 0)) ];
then
    mvn -f ./components/basys.components/pom.xml clean verify
fi
        '''
        }
      }
    }
  }
}

