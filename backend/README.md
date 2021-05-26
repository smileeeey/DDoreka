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
MySQL 5.4.27.Final 버전을 다운로드합니다.


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


java 설치 jdk 11
```
sudo apt-get install openjdk-11-jdk
```
maven 설치
```
sudo apt install maven
```
gradle 설치
```
sudo apt install gradle
```




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

## 백엔드 트러블슈팅

### JUnit 5

1.  스프링 부트에서의 MVC를 빠르게 테스트하기 위해 의존성을 해결해야 한다
    
    1.  @WebMvcTest
    2.  Mock 객체
2.  왜 gradle에서 JUnit 5가 동작하지 않는걸까?
    1.  스프링 부트 버전이 2.2.x이상이면 junit5가 자동으로 지원된다 따라서 junit 5를 위한 engine, api를 dependency하면 에러가 발생한다.
3.  JUnit 4에서 사용하던 @RunWith 왜 사용하지 않을까?
    JUnit5의 어노테이션에서 포함하고 있기 때문(@WebMvcTest)
    1.  @ExtendWith가 @RunWith에 대응된다.
    2.  Test로 끝나는 모든 어노테이션에 ExtendWith가 추가됨
4.  Mock 객체가 뭐까?
    가짜 객체
    1.  의존성을 해결하기 위해 리턴값을 내멋대로 정하는 가짜 객체
    2.  given when을 이용
5.  JPA Page가 무엇일까? PageImpl의 인터페이스이다
    1.  Page 내부에는 List를 넣을 수 있다.
6.  API 통합 테스트로 얻을 수 있는 것은 무엇일까?
    1.  파라미터가 적합한가
    2.  리턴값이 적합한가
    3.  URL이 적합한가를 알 수 있다.

[Junit5(jupiter) Controller 테스트코드 작성법 (WebMvcTest, MockMvc, MockBean을 사용한 테스트)](https://frozenpond.tistory.com/82)

Most developers use the spring-boot-starter-test “Starter”, which imports both Spring Boot test modules as well as JUnit Jupiter, AssertJ, Hamcrest, and a number of other useful libraries.

1.  postman으로 테스트할 수 있는데 왜 굳이 junit으로 webmvctest?
2.  왜 mock을 활용했나?
    
    → 답변 : 독립적으로 model, controller, view를 테스트하기 위해.
    
    코드 수정이 일어나도 이미 작성된 테스트케이스들로 잘 동작하는지 확인하려면
    
    junit을 활용하는 것이 적합하다고 판단.(api뿐 아니라 모듈들까지) (젠킨스 활용 가능)
    
    또한, 의존관계가 있는 클래스들을 용이하게 테스트하기 위해서는 mock을 이용하는 것이 좋겠다는 판단을 했다.
