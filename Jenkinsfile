properties([pipelineTriggers([githubPush()])])
pipeline{
    agent any
    options{
        timestamps()
        buildDiscarder(logRotator(numToKeepStr: '15'))
    }
    stages{
        stage('pulling application code from git') {
            steps {
                script {
                        checkout(scm: [$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[credentialsId: 'rajsevugan', url: "https://rajsevugan@bitbucket.org/rajsevugan/dbrelease_codebase.git"]]])   
                } 
            }
        }
        stage("executing local-dev pipeline"){
            when {
                expression { env.GIT_BRANCH == 'dev' || env.GIT_BRANCH == 'feature'}
            }
            steps{
                withCredentials([usernamePassword(credentialsId: '', passwordVariable: '', usernameVariable: '')]) {
                    sh'''
                      git diff-tree --no-commit-id --name-only -r `git rev-parse --short HEAD`
                      liquibase status --url="jdbc:postgres://192.168.32.11:3306/dev" --changeLogFile=my_app-wrapper.xml --username=$POSTGRESDB_CREDS_USR --password=$POSTGRESDB_CREDS_PSW'
                    '''  
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
    
        }
    }
  }
}
