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
                    sh './mvn package -Dmaven.test.skip=true '
                }
            }
        }
        stage('Test') {
            steps {
                dir('personalFinanceManagement') {
                    echo 'Testing...'
                    sh './mvn test'
                    junit 'target/surefire-reports/*.txt'
                }
            }
        }
        /*
        stage ('Javadoc'){
            steps {
                dir('personalFinanceManagement') {
                    echo 'Generating javadocs...'
                    sh './mvnw javadoc:javadoc'
                    publishHTML ([
                                reportName: 'Javadoc',
                                reportDir: 'target/site/apidocs/',
                                reportFiles: 'index.html',
                                keepAll: false,
                                alwaysLinkToLastBuild: false,
                                allowMissing: false
                                ])
                }
            }
        }
        */
        stage('Archiving') {
            steps {
                dir('personalFinanceManagement') {
                    sh 'dir'
                    echo 'Archiving...'
                    archiveArtifacts 'target/'
                }
            }
        }
        /*
        stage('Docker Image') {
             steps {
                script {
                    dockerimage = docker.build("gabriel1191765/ca5part2:${env.BUILD_ID}")
                }
            }
        }
        stage('Publish Image') {
            steps {
                script{
                    sh 'docker login -u="gabriel1191765" -p="gabriel2636"'
                    dockerimage.push()
                }
            }
        }
        */
        /* stage ('Ansible') {

        }*/
    }
}