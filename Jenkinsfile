
pipeline {

	agent none
	options { skipDefaultCheckout(true) }
	stages {
		stage('Build and Test') {
			agent any
			steps {
				sh 'docker build -t review-server:1.0 ./backend/Review'
				sh 'docker build -t face-server:1.0 ./backend/Face'
			}
		}
		stage('Docker Container rm') {
			agent any
			steps {
				sh 'docker ps -f name=review-server -q | xargs --no-run-if-empty docker container stop'
				sh 'docker container ls -a name=review-server -q | xargs -r docker container rm'

				sh 'docker ps -a -f "status=created" -f "status=exited" -q | xargs -r docker container rm'
				sh 'docker images -f "dangling=true" -q | xargs -r docker rmi'
			}
		}
		stage('Docker run!') {
			agent any
			steps {
				sh 'docker run -d --name review-server -v /volumes/back_home/profile:/volumes/profile review-server:1.0 -p 8083:8083'
			}
		}
	}
}
