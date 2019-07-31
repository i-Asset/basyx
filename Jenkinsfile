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
      stage('Parallel CI') {
          parallel {
              stage('PostgreSQL') {
                  steps {
                      container('postgresql') {
                          sh '''
                              id
                              pwd
                              ./ci/init_postgres.sh postgres
                              '''
                      }
                      container('postgresql') {
                          sh '''postgres'''
                      }
                  }
              }
              stage('Maven') {
                  steps {
                      container('maven') {
                          sh '''
                              echo OK
                              '''
                      }
                  }
              }
          }
      }
  }
}

