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
   steps{
    //try{
      echo 'Building...'
      sh 'dotnet --version'
      sh 'dir'
      sh "dotnet build ConsoleApp1"
      echo 'Building New Feature'
      
   // }catch(ex){
      //echo  'Something went wrong'
      //echo  ex.toString();
      //currentBuild.result = 'FAILURE'
   // }
   // finally{
      //cleanup
   // }
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
