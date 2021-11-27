properties([pipelineTriggers([githubPush()])])
pipeline{
    agent any
    options{
        timestamps()
        buildDiscarder(logRotator(numToKeepStr: '15'))
    }
    stages{
        stage('pulling cicd code from git') {
            steps {
                script {
                        checkout(scm: [$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[credentialsId: 'rajsev', url: "https://github.com/rajsev/cicd-database.git"]]])   
                } 
            }
        }
        stage('pulling application code from git') {
            steps {
                script {
                        checkout(scm: [$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[credentialsId: 'rajsevugan', url: "https://rajsevugan@bitbucket.org/rajsevugan/dbrelease_codebase.git"]]])   
                } 
            }
        }
        stage("executing local-dev pipeline"){
            // when {
            //     expression { env.GIT_BRANCH == 'dev' || env.GIT_BRANCH == 'feature'}
            // }
            steps{
                withCredentials([usernamePassword(credentialsId: '', passwordVariable: '', usernameVariable: '')]) {
                    script{
                     def externalMethod = load("file_list.groovy")
                    }
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
            }
    
        }
    
  }
}
