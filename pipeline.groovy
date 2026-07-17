node {

    stage('Clone') {
        checkout scm
    }

    stage('Install Dependencies') {
        sh 'npm install'
    }

    stage('Build React App') {
        sh 'npm run build'
    }

    stage('Deploy to EC2') {

        sshagent(['aws-server']) {

            sh '''
            scp -o StrictHostKeyChecking=no -r build/* ec2-user@43.204.232.222:/usr/share/nginx/html/
            '''

        }
    }
}