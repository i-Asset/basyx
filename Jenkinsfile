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
                     chmod +x ./ci/build_java.sh
                     ./ci/build_java.sh
                     '''
              }
          }
      }
  }
}
