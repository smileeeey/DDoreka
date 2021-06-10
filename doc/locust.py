from locust import HttpUser, task, between 

# 한명의 유저가 수행하는 일들!
class WebsiteUser(HttpUser): 

    wait_time = between(5, 8); 

######## 구매자 ########


    # 유레카 페이지 띄우기
    @task(2) 
    def index(self): 
        self.client.get(":8080/") 
        
    # category1Ids = ["001","002","003","004","005"]
    # for i in category1Ids:

    # 메인 화면 띄울 때 API call
    @task(1)
    def category_get(self): 
        self.client.get(":8081/category/mainCategory")
        self.client.get(":8084/order/recommend/hotproduct")
        self.client.get(":8084/order/recommend/steadyseller")
        self.client.get(":8084/order/recommend/todayhot")
        self.client.get(":8081/product/recommend/latestproduct")
        self.client.get(":8081/product/recommend/realtimesearchword")

    # 회원가입
    @task
    def user_login(self):
        self.client.post(":8088/login/add")

    # 로그인

    # 상품 검색

    # 상품 상세보기

    # 장바구니 담기

    # 결제

    # 로그아웃




######## 판매자 ########

    # 회원가입

    # 로그인
    
    # 대시보드

    # 판매자 정보 업데이트

    # 판매 상품 등록

    # 판매 상품 리스트 보기

    # 새로 들어온 주문 보기

    # 주문 status 이동

    # 
