# README


## update date
2021.02.05
2021.02.09

## 기술 스택

  

 WEB 

  

**DevOps**
||기술 스택|
|--|--|
|SCM|GitLab,Jira|
|Cloud Service|AWS EC2|

<br>

**Front-end**
||기술 스택|
|--|--|
|Language|HTML CSS JavaScript|
|Framework|Vue.js|
|Library|Ajax VuetifyAxios|

<br>

**Back-end**
||기술 스택|
|--|--|
|Server Architecture|MSA|
|Language|Java8|
|Framework|SpringBoot SpringCloud SpringSecurity SpringDataJPA|
|BuildTool|Gradle,Maven|
|DBMS|MySQL|
|Testing | JUnit Locust|

<br>


## Backend 어플리케이션 인스톨 가이드

MSA 구조에 따라서 각 서비스들을 독립적으로 실행해야 합니다.

먼저 프로젝트를 로컬에 다운로드 받습니다.

```
git clone https://lab.ssafy.com/s04-final/s04p31d104.git
```

다음과 같은 순서로 서버를 동작시킵니다.

1. service registry 실행
폴더 경로 : C: ... /s04p31d104/backend/service-registry
이곳에서 git bash를 열고 다음 명령어를 수행합니다.
```
./mvnw package
java -jar target/libs/service-registry-0.0.2-SNAPSHOT.jar
```
2. cloud gateway 실행
폴더 경로 : C: ... /s04p31d104/backend/cloud-gateway
이곳에서 git bash를 열고 다음 명령어를 수행합니다.
```
./mvnw package
 java -jar target/libs/cloud-gateway-0.0.2-SNAPSHOT.jar
```
3. Auth 실행
폴더 경로 : C: ... /s04p31d104/backend/Auth
이곳에서 git bash를 열고 다음 명령어를 수행합니다.
```
./mvnw package
java -jar target/auth-0.0.2-SNAPSHOT.jar
```
4. Face 실행
폴더 경로 : C: ... /s04p31d104/backend/Face
이곳에서 git bash를 열고 다음 명령어를 수행합니다.
```
./gradlew bootjar
java -jar build/libs/face-0.0.3-SNAPSHOT.jar
```
5. File 실행
폴더 경로 : C: ... /s04p31d104/backend/File
이곳에서 git bash를 열고 다음 명령어를 수행합니다.
```
./gradlew bootjar
java -jar build/libs/file-v0.0.19.jar
```
6. Order 실행
폴더 경로 : C: ... /s04p31d104/backend/Order 
이곳에서 git bash를 열고 다음 명령어를 수행합니다.
```
./gradlew bootjar
java -jar build/libs/order-order-20210217.4.jar
```
7. Product 실행
폴더 경로 : C: ... /s04p31d104/backend/Product 
이곳에서 git bash를 열고 다음 명령어를 수행합니다.
```
./gradlew bootjar
java -jar build/libs/product-v0.0.26.jar
```
8. Review 실행
폴더 경로 : C: ... /s04p31d104/backend/Review  
이곳에서 git bash를 열고 다음 명령어를 수행합니다.
```
./gradlew bootjar
java -jar build/libs/review-v0.0.10.jar
```
9. Seller 실행
폴더 경로 : C: ... /s04p31d104/backend/Seller
이곳에서 git bash를 열고 다음 명령어를 수행합니다.
```
./gradlew bootjar
java -jar build/libs/seller-0.0.2-SNAPSHOT.jar
```
10. User 실행
폴더 경로 : C: ... /s04p31d104/backend/User 
이곳에서 git bash를 열고 다음 명령어를 수행합니다.
```
./gradlew bootjar
java -jar build/libs/user-v0.1.0.jar
```
