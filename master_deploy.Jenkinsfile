pipeline{
    agent any
    options{
        timestamps()
        buildDiscarder(logRotator(numToKeepStr: '15'))
    }
    // environment {
    //     env = "${ENVIRONMENT}"
    // }
    stages{
        stage('pulling application code from git') {
            steps {
                script {
                        checkout(scm: [$class: 'GitSCM', branches: [[name: "*/${APPLICATION_GIT_BRANCH}"]], extensions: [], userRemoteConfigs: [[credentialsId: 'rajsevugan', url: "https://github.com/rajsev/cicd-database.git"]]])   
                } 
            }
        }

        stage("executing local-dev pipeline"){
            when {
                expression { env.BRANCH_NAME == 'dev' || env.BRANCH_NAME == 'feature'}
            }
            steps{
                    build job: "local-dev-deploy", parameters: [
                        string(name: 'branch', value: 'DEV')
                    ],
                    wait: true
            }
            post{
                always{
                    echo "========always========"
                }
                success{
                    echo "========executing local-dev pipeline successfully========"
                }
                failure{
                    echo "========executing local-dev pipeline failed========"
                }
            }
        }
        stage("executing dev pipeline"){
            when {
                expression { env.BRANCH_NAME == 'dev' || env.BRANCH_NAME == 'feature'}
            }
            steps{
                    build job: "dev-deploy", parameters: [
                        string(name: 'branch', value: 'DEV')
                    ],
                    wait: true
            }
            post{
                always{
                    echo "========always========"
                }
                success{
                    echo "========executing local-dev pipeline successfully========"
                }
                failure{
                    echo "========executing local-dev pipeline failed========"
                }
            }
        }
    }
    post{
        always{
            echo "========always========"
        }
        success{
            echo "========pipeline executed successfully ========"
        }
        failure{
            echo "========pipeline execution failed========"
        }
    }
}
