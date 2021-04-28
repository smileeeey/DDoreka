
pipeline {
	agent none
	options { skipDefaultCheckout(true) }
	stages {
		stage('Build and Test') {
			agent any
			steps {
				sh 'docker build --tag review-server:1.0 . https://lab.ssafy.com/s04-final/s04p31d104/tree/develop/backend/Review'
			}
		}
	}
}
