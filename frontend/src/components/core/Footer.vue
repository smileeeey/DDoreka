<template>
  <v-footer color="white">
    <v-row justify="center" no-gutters>
      <v-col cols="12">
        <v-divider></v-divider>
      </v-col>
      <v-btn v-for="(link, idx) in links" :key="idx" :to="link.to" text rounded class="my-2 mx-5">
        {{ link.text }}
      </v-btn>

      <v-col cols="12">
        <v-divider></v-divider>
      </v-col>

      <v-row justify="center" class="my-3">
        <v-col cols="3">
          <v-img @click="$router.push({ name: 'Main' })" style="cursor: pointer" src="@/assets/eureka_logo(blank).png" class="my-auto" max-height="120" max-width="400"> </v-img>
        </v-col>
        <v-col cols="4">
          <div style="font-size: 15px;">
            EUREKA 유레카 | TEAM 또레카
            <br />
            경상북도 구미시 진평4길 21
            <br />
            등록번호 : 000-00-00000
            <br />
            전화번호 : 000-0000-0000
          </div>
        </v-col>
        <v-col cols="1">
          <div class="text-center mt-5">
            <v-dialog
              v-model="dialog"
              width="1000"
            >
              <template v-slot:activator="{ on, attrs }">
                <v-btn
                  color="blue lighten-1"
                  x-large
                  dark
                  v-bind="attrs"
                  v-on="on"
                >
                  EUREKA사용법
                </v-btn>
              </template>

              <v-card>
                <v-card-title class="headline grey lighten-2">
                  환경설정
                </v-card-title>

                <v-card-text style="font-size: 1.25rem;">
                  <br>
                  STEP 1 : 
                  <strong>chrome://flags/#unsafely-treat-insecure-origin-as-secure</strong> 를 주소창에 복사/붙여넣기
                  <br>
                  <br>
                  STEP 2 :
                  <strong>http://i4d106.p.ssafy.io/</strong> 를 enabled 해 주세요!
                </v-card-text>

                <v-divider></v-divider>
                <v-img src="@/assets/notice.png"></v-img>
                <v-divider></v-divider>

                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn
                    color="primary"
                    text
                    @click="dialog = false"
                  >
                    알겠습니다!
                  </v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
          </div>
        </v-col>

      </v-row>

      <v-col class="text-center" cols="12">
        <v-card-text class="py-2 text-center" style="color: white; width=100%; background-color: #333333;">
          Copyright © <strong>EUREKA</strong> Team. 2021-{{ new Date().getFullYear() }} All Rights Reserved.
        </v-card-text>
      </v-col>
    </v-row>
  </v-footer>
</template>

<script>
export default {
  name: 'Footer',
  data: () => ({
    dialog : false,
    links: [
      {
        to: '#',
        text: '공지사항',
      },
      {
        to: '#',
        text: '이용약관',
      },
      {
        to: '#',
        text: '개인정보 처리방침',
      },
      {
        to: '/sell',
        text: '판매자 페이지',
      },
    ],
  }),
};
</script>

<style></style>
