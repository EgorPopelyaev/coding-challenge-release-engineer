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
                checkout(
                        [$class: 'GitSCM', branches: [[name: '*/master']],
                         doGenerateSubmoduleConfigurations: false,
                         extensions: [[$class: 'CleanBeforeCheckout', deleteUntrackedNestedRepositories: true]],
                         submoduleCfg: [],
                         userRemoteConfigs: [[credentialsId: '0d7a1aeb-6ab4-4b58-9d3e-5ba775eedfc9',
                                              url: 'https://github.com/EgorPopelyaev/coding-challenge-release-engineer.git'
                                             ]]
                        ])

                /**
                 * on this step we build our app and run the unit tests
                 */
                sh "mvn clean package"
            }

            post {
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.war'
                }
            }
        }
        stage('Build and run docker image') {
            steps {
                //adding this command to copy war to the docker directory to build an image. There should be the better way how to do it but for now we go along with it.
                sh "cp target/ebayk-0.1.0.war src/main/docker/"
                sh "docker build -t ebayk -f src/main/docker/Dockerfile ."
                sh "docker run -d ebayk:latest"
            }
        }
        stage('Run E2E tests') {
            steps {
                sh "mvn verify -Pe2eTests"
            }
            post {
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.war'
                }
            }

        }
    }
    post {
        always {
            sh "docker stop \$(docker ps -a -q)"
        }
    }
}