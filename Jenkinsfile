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
    resources:
      requests:
        memory: "2Gi"
        cpu: "1"
      limits:
        memory: "2Gi"
        cpu: "1"
    command: 
    - cat
    tty: true
    env:    
    - name: POSTGRES_PASSWORD              
      value: admin
    - name: PGDATA
      value: /run/postgresql/data
  - name: cmake
    image: rikorose/gcc-cmake:latest
    resources:
      requests:
        memory: "2Gi"
        cpu: "1"
      limits:
        memory: "2Gi"
        cpu: "1"
    command:
    - cat
    tty: true
  - name: maven
    image: maven:latest
    resources:
      requests:
        memory: "2Gi"
        cpu: "1"
      limits:
        memory: "2Gi"
        cpu: "1"
    command:
    - cat
    tty: true
"""
    }
  }
  stages {
      stage('Setup PostgreSQL') {
          steps {
              container('postgresql') {
                  sh '''
                      chmod +x ./ci/init_postgres.sh
                      ./ci/init_postgres.sh postgres
                      pg_ctl start
                      '''
              }
          }
      }
      stage('Java SDK Tests') {
          steps {
              container('maven') {
                  sh '''
GIT_DIFF=$(/usr/bin/git diff --name-only origin/CI_Test)

JAVA_SDK_CHANGED=$(echo $GIT_DIFF | grep ".*/sdks/java/.*" | wc -l)
JAVA_COMPONENTS_CHANGED=$(echo $GIT_DIFF | grep "components/.*" | wc -l)
JAVA_EXAMPLES_CHANGED=$(echo $GIT_DIFF | grep "examples/.*" | wc -l)

if [ $((JAVA_SDK_CHANGED > 0)) ];
then
    mvn -f ./sdks/java/basys.sdk/pom.xml clean verify
elif [ $((JAVA_COMPONENTS_CHANGED > 0)) ];
then
    mvn -f ./components/basys.components/pom.xml clean verify
elif [ $((JAVA_EXAMPLES_CHANGED > 0)) ];
then
    mvn -f ./examples/basys.examples/pom.xml clean verify
fi
                      '''
              }
          }
      }
  }
}
