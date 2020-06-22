pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out...'
                git url: 'https://gabrielPereira12@bitbucket.org/martalribeiro/devops_g2_maven.git/'
            }
        }
        stage('Assemble') { //  qual o objectivo desta fase?
            steps {
                dir('personalFinanceManagement') {
                    echo 'Building...'
                    bat './mvnw package -Dmaven.test.skip=true '
                }
            }
        }
        stage('Test') {
            steps {
                dir('personalFinanceManagement') {
                    echo 'Testing...'
                    bat './mvnw test'
                    junit 'target/surefire-reports/*.txt'
                }
            }
        }
        stage ('Javadoc'){
            steps {
                dir('personalFinanceManagement') {
                    echo 'Generating javadocs...'
                    bat './gradlew javadoc'
                    publishHTML ([
                                reportName: 'Javadoc',
                                reportDir: 'build/docs/javadoc/',
                                reportFiles: 'index.html',
                                keepAll: false,
                                alwaysLinkToLastBuild: false,
                                allowMissing: false
                                ])
                }
            }
        }
        stage('Archiving') {
            steps {
                dir('ca2/ca2part2') {
                    bat 'dir'
                    echo 'Archiving...'
                    archiveArtifacts 'build/libs/'
                }
            }
        }
        stage('Docker Image') {
             steps {
                dir('ca5/ca5part2') {
                    script {
                        dockerimage = docker.build("gabriel1191765/ca5part2:${env.BUILD_ID}")
                    }
                }
            }
        }
        stage('Publish Image') {
            steps {
                dir('ca5/ca5part2') {
                    script{
                        bat 'docker login -u="gabriel1191765" -p="gabriel2636"'
                        dockerimage.push()
                    }
                }
            }
        }
    }
}