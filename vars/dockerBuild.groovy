def call(String dockerHubUsername, String imageName) {
    sh 'pwd' // Check current directory
    sh 'ls -l' // Verify Dockerfile and other files exist
    
    // Build Docker image
    sh "docker build --build-arg REACT_APP_RAPID_API_KEY=5f428cbba5msh53a1a25add324f7p1a7b6ejsnffcd9bc28d0c -t ${imageName} ."
    
    // Tag Docker image
    sh "docker tag ${imageName} ${dockerHubUsername}/${imageName}:latest"
    
    // Push Docker image
    withDockerRegistry([url: 'https://index.docker.io/v1/', credentialsId: 'docker']) {
        sh "docker push ${dockerHubUsername}/${imageName}:latest"
    }
}
