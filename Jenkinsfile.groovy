pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "M3"
    }

    stages {
        stage('Build') {
            steps {
                /**
                 * Here we need to handle the interaction with the git:
                 * - providing proper credentials
                 * - also checkout policy
                 * */
                git 'https://github.com/EgorPopelyaev/coding-challenge-release-engineer.git'

                /**
                 * on this step we build our app and run the unit tests
                 */
                sh "mvn clean package"
            }

            post {
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
        stage('Build and run docker image') {
            steps {
                //adding this command to copy war to the docker directory to build an image. There should be the better way how to do it but for now we go along with it.
                sh "cp target/ebayk-0.1.0.war src/main/docker/"
                sh "docker build -t ebayk -f src/main/docker/Dockerfile ."
            }
        }
    }
}