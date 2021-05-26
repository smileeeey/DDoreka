# Backend 설명!!!!!


## 폴더 구성도
### Backend
각 폴더는 모두 SpringBoot 프로젝트이다.

#### 1. Auth Server
[Auth Server 설명이 여기 쑉쇽](Auth/README.md)
#### 2. Face Server
[Face Server 설명이 여기 쑉쇽](Face/README.md)
#### 3. File Server
[File Server 설명이 여기 쑉쇽](File/README.md)
#### 4. Order Server
[Order Server 설명이 여기 쑉쇽](Order/README.md)
#### 5. Product Server
[Product Server 설명이 여기 쑉쇽](Product/README.md)
#### 6. Review Server
[Review Server 설명이 여기 쑉쇽](Review/README.md)
#### 7. Seller Server
[Seller Server 설명이 여기 쑉쇽](Seller/README.md)
#### 8. User Server
[User Server 설명이 여기 쑉쇽](User/README.md)
#### 9. Chat Server
[Chat Server 설명이 여기 쑉쇽](Chat/README.md)
#### 10. cloud-gateway
[cloud-gateway Server 설명이 여기 쑉쇽](cloud-gateway/README.md)
#### 11. service-registry
[service-registry Server 설명이 여기 쑉쇽](service-registry/README.md)


<br>
<br>

## Backend 어플리케이션 인스톨 가이드

### 잠깐! 먼저 database를 다운받아야 합니다
MySQL 5.4.27.Final 버전을 다운로드합니다
계정은 sumin / sumin
```
CREATE USER 'sumin'@localhost identified by 'sumin';
```
스키마들을 만듭니다.
```
CREATE DATABASE auth;
CREATE DATABASE face;
CREATE DATABASE file;
CREATE DATABASE `order`;
CREATE DATABASE product;
CREATE DATABASE review;
CREATE DATABASE seller;
CREATE DATABASE user;
```

User 스키마의 테이블을 만들어줍니다.
[create문](User/src/main/resources/user_create.sql)



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
./execute.sh
```
2. cloud gateway 실행
    폴더 경로 : C: ... /s04p31d104/backend/cloud-gateway
    이곳에서 git bash를 열고 다음 명령어를 수행합니다.
```
./execute.sh
```
3. 나머지 서버도 다 실행시킵니다.
```
./execute.sh
```
