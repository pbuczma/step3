pipeline{
 agent none
 stages{
  stage('SCM'){
   agent { 
                docker { 
                    image 'alpine'
                } 
            }
   steps{
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
   steps{
     echo 'Building...'
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
