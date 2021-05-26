#  또레카

어떤 상품이 마음에 드는지 헷갈리는 사용자들을 위한

***'감정분석 + 상품추천 쇼핑몰 : Eureka'*** **리펙토링 프로젝트** 

#### 🛠개발기간 : 2021.04.12~2021.05.21

 [Eureka ](https://github.com/sumin416/Eureka)
 
 <br>
 <br>
 
## Our Team
### Backend
* 팀장 이수민([@sumin416](https://https://github.com/sumin416 "github link"))

* 팀원 심재혁([@JaeHyukSim](https://github.com/JaeHyukSim "github link"))

### Frontend
* 팀원 강구원([@KangGooWeon](https://github.com/KangGooWeon "github link"))

* 팀원 정준영([@dkwjdi](https://github.com/dkwjdi "github link"))
 <br><br>



 
### 🛠개발기간 
### 2021.04.12~2021.05.21


<br><br>

## 서비스 구성도

![서비스 구성도](https://user-images.githubusercontent.com/45270204/119702368-570d7580-be90-11eb-9248-d1f0705d0672.png)


<br><br>

## 💡유레카 기능

#### ◾메인페이지
![메인페이지](https://user-images.githubusercontent.com/42925284/119686684-8b2d6a00-be81-11eb-9059-c50ac6598ae2.gif)

#### ◾카테고리 조회
![카테고리 조회](https://user-images.githubusercontent.com/42925284/119686807-aa2bfc00-be81-11eb-8fd4-d898f32b47e7.gif)

#### ◾상품조회, 감정, 장바구니 등록
![디테일 페이지](https://user-images.githubusercontent.com/42925284/119686865-b31ccd80-be81-11eb-9f4b-5ba8133b7588.gif)

#### ◾장바구니 조회 및 결제
![결제](https://user-images.githubusercontent.com/42925284/119687287-098a0c00-be82-11eb-9d1f-831f293c89e7.gif)

#### ◾내가 본 상품 감정 통계
![마이유레카](https://user-images.githubusercontent.com/42925284/119687449-30e0d900-be82-11eb-962b-b43ac15c4d31.gif)

#### ◾마이페이지
![마이페이지](https://user-images.githubusercontent.com/42925284/119687491-38a07d80-be82-11eb-9711-e7025ff0ddd7.gif)

### ◾판매자페이지

#### ◾상품등록
![상품등록](https://user-images.githubusercontent.com/42925284/119688034-9d5bd800-be82-11eb-83d0-35c7de04b6a6.gif)

#### ◾판매제품관리
![판매제품관리](https://user-images.githubusercontent.com/42925284/119688075-a3ea4f80-be82-11eb-95dd-d12164c1f49c.gif)

#### ◾주문알림
![주문알림](https://user-images.githubusercontent.com/42925284/119688099-a77dd680-be82-11eb-9af8-6a8aeafcb720.gif)

#### ◾배송관리
![배송관리](https://user-images.githubusercontent.com/42925284/119688119-ac428a80-be82-11eb-9cae-c5f08385a52d.gif)




<br><br>


## 기술 스택

 ***WEB***

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
|Library|Vuetify Axios chart.js|

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


## Frontend 실행방법 및 파일구조
[Frontend](/frontend/README.md)
## Backend 실행방법 및 파일구조
[Backend](/backend/README.md)


## Frontend Refactoring

#### 1.서버에 가해지는 디도스 공격 해결 (for문을 통한 Axios호출) 
<br><br>
기존의 코드는 for문을 통해 Axios통신을 여러번 한 뒤, response값에 대해 다시 for문을 돌려 Axios를 호출하는 구조
Backend에서의 Database통신은 저렴하지만, 프론트에서 백으로의 통신은 비용이 비쌈 => 백엔드에서 한 번에 필요한 데이터를 받는 구조로 변경
![디도스1](https://user-images.githubusercontent.com/57394523/119693915-b87d1680-be87-11eb-9684-c4e1d876cac5.png)


<br><br>
메인 화면에서 오늘의 상품, 요즘 뜨는 상품, 스테디셀러 상푸미 카테고리별 추천상품별로 총 4개의 컴포넌트에서 각각 사진에 대해 Axios통신을 하는 구조
한번의 Axios를 통해 4개의 컴포넌트에 필요한 데이터를 받은 후 Vuex를 통해 상태관리하는 구조로 변경
![디도스2](https://user-images.githubusercontent.com/57394523/119694276-0c87fb00-be88-11eb-852e-f7dc675d41b9.png)
![디도스2해결](https://user-images.githubusercontent.com/57394523/119694283-0eea5500-be88-11eb-9d5e-b7a4dad73eef.png)




#### 2.유지보수가 어려운 코드를 해결
<br><br>
기존의 코드는 각각의 컴포넌트에서 Axios를 직접호출 하였기 때문에 하나의 API를 수정하기 위해서는 여러 컴포넌트에서의 코드수정이 필요
이를 위해 API를 모듈화해 유지보수를 쉽게 하였고 중복으로 일어나는 Axios호출 코드를 request객체에 저장해 코드의 중복을 제거
<br>
![문제1](https://user-images.githubusercontent.com/57394523/119694562-48bb5b80-be88-11eb-9629-941139816ad7.png)
![해결](https://user-images.githubusercontent.com/57394523/119694658-62f53980-be88-11eb-941b-4d6928cfe70a.png)


## Backend Refactoring

#### 1.서버에 가해지는 디도스 공격 해결 (for문을 통한 Axios호출) 
<br><br>
RestTemplete입니다 이 기술을 이용해 프론트에서 삼중 및 사중 axios 문제를 해결할 수 있었습니다. 실제로 기존 코드는 잘못된 백엔드의 API 제공으로 인해 지나치게 많은 API를 호출하는 문제가 있습니다. Rest Templete를 활용해 클라이언트와 서버간 API를 줄이고, 데이터는 서버 내에서 집약하는 과정을 거쳐 문제를 해결할 수 있습니다.
![image](https://user-images.githubusercontent.com/55395239/119699335-047e8a00-be8d-11eb-8c9e-50613da43f0e.png)
<br><br>
비즈니스 로직별로 API를 재구축했습니다. 메서드와 리소스를 명확히 구분해서 기존의 뒤죽박죽인 API 호출 문제를 해결했습니다. 
![image](https://user-images.githubusercontent.com/55395239/119699650-59220500-be8d-11eb-81b5-dd9d13e8298b.png)


<br><br>
서버간 통신을 하기 위한 RestTemplate 적용시 모든 HTTPMethod는 exchange 함수로 처리가 가능했습니다. 그래서 get/post 등 httpMethod의 공통로직을 만들어 모듈화했습니다.
또한, 만든 클래스를 다양한 경우에서 사용하기 위해 자바 제네릭을 사용했습니다.
![image](https://user-images.githubusercontent.com/55395239/119699713-66d78a80-be8d-11eb-929b-2da82f91aa78.png)




#### 2.JUnit5
<br><br>
JUnit5를 활용하기도 했습니다. 웹 개발하면서 db샘플을 만들어 전 과정을 테스트하는 것이 불편했습니다. 이를 해결하기 위해 Mockito를 활용해, 의존관계가 있는 모듈을 독립적으로 테스트하였습니다. 
![image](https://user-images.githubusercontent.com/55395239/119699848-8a9ad080-be8d-11eb-8e41-051d59de504a.png)

#### 3. Swagger & Java Doc
Java Docs와 스웨거를 활용해 문서화를 수행했습니다. 유레카를 유지보수하면서 제대로 문서화되어 있지 않아 코드를 해석하는 것이 너무 힘들었습니다. 다른 유지보수를 하는 팀이 같은 과정을 겪지 않도록 하기 위해 메서드 단위로 하나하나 문서화하고 스웨거로 API를 문서화 하였습니다.
![image](https://user-images.githubusercontent.com/55395239/119699950-a69e7200-be8d-11eb-8eaa-88f111da1773.png)
