node{
 
  stage('SCM'){
    echo 'Gathering code from version control'
    git branch : '${branch}', url: 'https://github.com/pbuczma/step3.git'
  }
  stage('Build'){
   agent { 
       docker { 
           image 'mcr.microsoft.com/dotnet/sdk'
           // label 'maven-label'
       } 
   } 
   
    try{
      echo 'Building...'
      sh 'dotnet --version'
      sh "dotnet build ConsoleApp1"
      echo 'Building New Feature'
      
    }catch(ex){
      echo  'Something went wrong'
      echo  ex.toString();
      currentBuild.result = 'FAILURE'
    }
    finally{
      //cleanup
    }
    
  }
  stage('Test'){
    echo  'Testing...'
  }
  stage('Deploy'){
    echo 'Deploying...'
  }
}
