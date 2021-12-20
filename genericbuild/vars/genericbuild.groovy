def call(Map config=[:]) {

    pipeline {
        agent none
        stages {
            stage('SCM') {
                agent {
                    docker {
                        image 'alpine'
                    }
                }
       step s{
        echo 'Gathering code from version control'
        git branch : '${branch}', url: 'https://github.com/pbuczma/step3.git'

       }
      }
      stage('Build'){
       agent {
           docker {
               image 'mcr.microsoft.com/dotnet/sdk'
               // label 'maven-label'
           }
       }
       environment {
          HOME = '/tmp'
       }
       step s{
            //try{
               echo 'Building...'
               sh 'cd ConsoleApp1'
               sh 'dotnet build  ConsoleApp1'
               echo 'Building New Feature'
               releasenotes(changes: "true")
             //}catch(ex){
             //  echo  'Something went wrong'
             //  echo  ex.toString();
             //  currentBuild.result = 'FAILURE'
             //}
       }
      }
      stage('Test'){
       step s{
        echo  'Testing...'
       }
      }
      stage('Deploy'){
       step s{
        echo 'Deploying...'
       }
      }
     }
    }
}