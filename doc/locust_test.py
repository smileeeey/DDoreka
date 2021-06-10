import time
from locust import HttpUser, task, between

class QuickstartUser(HttpUser):
    wait_time = between(4, 4.5)
    # @task
    # def hello_world(self):
    #     self.client.get("/client")
    #     self.client.get("/in")

    # @task(2)
    # def view_items(self):
    #     print("최신 진입")
    #     for cnt in range(1, 3):
    #         self.client.get(f"/api/v1/board/newest?page_count={cnt}", name="read_by_new")
    #
    # @task(2)
    # def view_items_detail(self):
    #     print("세부 진입")
    #     arr = [3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 20, 22, 23, 24, 25,
    #            26, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41,
    #            42, 43, 44, 45, 46, 47, 48, 49, 50, 53, 54, 55]
    #     for item_id in arr:
    #         self.client.get(f"/api/v1/board/detail/{item_id}", name="detail")

    def _get_image_part(self, file_path, file_content_type='image/jpg'):
        import os
        file_name = os.path.basename(file_path)
        file_content = open(file_path, 'rb')
        return file_name, file_content, file_content_type

    def _get_video_part(self, file_path, file_content_type='video/mp4'):
        import os
        file_name = os.path.basename(file_path)
        file_content = open(file_path, 'rb')
        return file_name, file_content, file_content_type

    # @task(3)
    # def deepfake(self):
    #     print("딥페이크 진입")
    #     files = {
    #         "origin": self._get_image_part("C:/SSAFY/Python_workspace/loucst_test/image/준영2.jpg"),
    #         "target": self._get_image_part("C:/SSAFY/Python_workspace/loucst_test/image/정환.jpg")
    #     }
    #
    #     self.client.post("/api/v1/deepfake", files=files)

    # @task(3)
    # def damedame(self):
    #     print("다메다메 진입")
    #     files = {
    #         "image": self._get_image_part("C:/SSAFY/Python_workspace/loucst_test/image/준영2.jpg"),
    #     }
    #
    #     res = self.client.post("/api/v1/damedame", files=files)
    #     print(res)

    @task(3)
    def removeBg(self):
        print("배경제거 진입")
        files = {
            "video": self._get_video_part("C:/Users/YSM/Desktop/test/dance.mp4"),
            "image": self._get_image_part("C:/Users/YSM/Desktop/test/unnamed.jpg")
        }

        res = self.client.post("/api/v1/removeBg", files=files)
        print(res)
    #
    # def on_start(self):
    #     print("user 진입. 게시물 목록 최신순 조회")
    #     self.client.get("/api/v1/board/newest?page_count=1", name="first_read_by_new")