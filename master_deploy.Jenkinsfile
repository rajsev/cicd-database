pipeline{
    agent{
        label "node"
    }
    parameters{

    }
    options{
        timestamps()
        buildDiscarder(logRotator(numToKeepStr: '15'))
        ansiColor('xterm')
    }
    environment {
        env = ${ENVIRONMENT}
    }
    stages{
        stage("executing local-dev pipeline"){
            when {
                environment name: 'env', value: 'local-dev'
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