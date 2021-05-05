
pipeline {

	agent none
	options { skipDefaultCheckout(false) }
	stages {
		stage('Init config - make jar') {
			agent any
			steps {
				sh 'cd ./backend/Review
				sh './shell_script.sh'
			}
		}
		stage('Build and Test') {
			agent any
			steps {
				sh 'docker build -t review-server:1.0 ./backend/Review'
				sh 'docker build -t face-server:1.0 ./backend/Face'
				//sh 'docker build -t auth-server:1.0  ./backend/Auth'
				sh 'docker build -t user-server:1.0  ./backend/User'
				sh 'docker build -t product-server:1.0  ./backend/Product'
				sh 'docker build -t order-server:1.0  ./backend/Order'
				sh 'docker build -t seller-server:1.0  ./backend/Seller' 
				

			}
		}
		stage('Docker Container rm') {
			agent any
			steps {
				sh 'docker ps -f name=review-server -q | xargs --no-run-if-empty docker container stop'
				sh 'docker ps -f name=face-server -q | xargs --no-run-if-empty docker container stop'
				sh 'docker ps -f name=auth-server -q | xargs --no-run-if-empty docker container stop'
				sh 'docker ps -f name=user-server -q | xargs --no-run-if-empty docker container stop'
				sh 'docker ps -f name=product-server -q | xargs --no-run-if-empty docker container stop'
				sh 'docker ps -f name=order-server -q | xargs --no-run-if-empty docker container stop'
				sh 'docker ps -f name=seller-server -q | xargs --no-run-if-empty docker container stop'

				sh 'docker container ls -a name=review-server -q | xargs -r docker container rm'
				sh 'docker container ls -a name=face-server -q | xargs -r docker container rm'
				sh 'docker container ls -a name=auth-server -q | xargs -r docker container rm'
				sh 'docker container ls -a name=user-server -q | xargs -r docker container rm'
				sh 'docker container ls -a name=product-server -q | xargs -r docker container rm'
				sh 'docker container ls -a name=order-server -q | xargs -r docker container rm'
				sh 'docker container ls -a name=seller-server -q | xargs -r docker container rm'

				sh 'docker ps -a -f "status=created" -f "status=exited" -q | xargs -r docker container rm'
				sh 'docker images -f "dangling=true" -q | xargs -r docker rmi'
			}
		}
		stage('Docker run!') {
			agent any
			steps {
				sh 'docker run -d --name review-server -v /volumes/back_home/profile:/volumes/profile review-server:1.0 -p 8083:8083'
				sh 'docker run -d --name face-server -v /volumes/back_home/profile:/volumes/profile face-server:1.0 -p 8087:8087'
				sh 'docker run -d --name auth-server -v /volumes/back_home/profile:/volumes/profile auth-server:1.0 -p 9999:9999'
				sh 'docker run -d --name user-server -v /volumes/back_home/profile:/volumes/profile user-server:1.0 -p 8085:8085'
				sh 'docker run -d --name product-server -v /volumes/back_home/profile:/volumes/profile product-server:1.0 -p 8081:8081'
				sh 'docker run -d --name order-server -v /volumes/back_home/profile:/volumes/profile order-server:1.0 -p 8084:8084'
				sh 'docker run -d --name seller-server -v /volumes/back_home/profile:/volumes/profile seller-server:1.0 -p 8086:8086'
			}
		}
	}
}
