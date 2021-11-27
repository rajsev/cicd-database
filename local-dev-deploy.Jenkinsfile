pipeline{
    agent{
        label "liquibase"
        // docker { 
        //     image 'liquibase/liquibase:4.4.2'
        // }
    }
    stages{
        stage('pulling application code from git') {
            steps {
                script {
                    dir("$WORKSPACE") {
                        checkout scm: [$class: 'GitSCM', branches: [[name: "*/${APPLICATION_GIT_BRANCH}"]], extensions: [], userRemoteConfigs: [[credentialsId: 'rajsev', url: "https://github.com/rajsev/cicd-database.git"]]]
                    }      
                } 
            }
        }
        stage("dev deployment status check"){
            steps{
                steps{
		            withCredentials([usernamePassword(credentialsId: '', passwordVariable: '', usernameVariable: '')]) {
                    sh'''
                      def externalMethod = load("file1.groovy")
                    '''    
            }
            post{
                always{
                    echo "========always========"
                }
                success{
                    echo "========A executed successfully========"
                }
                failure{
                    echo "========A execution failed========"
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