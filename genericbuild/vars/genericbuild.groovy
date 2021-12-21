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
       steps{
        echo 'Gathering code from version control'
        git branch : '${branch}', url: 'https://github.com/pbuczma/step3.git'
        sh 'pwd'
        sh 'ls -1'
        sh 'uname -a'
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
       steps{
            //try{
               sh 'cd'
               sh 'dir /B'
               sh 'ver'
               echo 'Building...'
               sh 'cd ' + config.target
               sh 'dotnet build ' + config.target
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
       steps{
        echo  'Testing...'
       }
      }
      stage('Deploy'){
       steps{
        echo 'Deploying...'
       }
      }
     }
    }
}