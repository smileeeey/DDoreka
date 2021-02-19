<template>
  <v-footer
    color=white
  >
    <v-row
      justify="center"
      no-gutters
    >
      <v-col cols="12">
        <v-divider></v-divider>
      </v-col>

      <v-col cols="12">
        <v-divider></v-divider>
      </v-col>
      
      <v-row justify="center" class="my-3">
        <v-col cols="2">
          <v-img
            @click="$router.push({ name: 'Main' })"
            style="cursor: pointer"
            src="@/assets/eureka_logo(blank).png"
            class="my-auto"
            max-height="120"
            max-width="400"
          >
          </v-img>
        </v-col>
        <v-col cols="6">
          <div style="font-size: 20px;">
            <strong>EUREKA 유레카 | TEAM 중대장은 만족했다</strong>
            <br>
            <div style="word-break: break-all; font-size: 15px;">
              <li>본 사이트에 등록된 제품들은 가상의 제품들로 실제 제공되는 서비스가 아닙니다.</li>
              <li>결제단계에서 진행되는 결제 서비스는 <strong>실제로 결제가 되는 점</strong>을 알려드립니다. 금액은 당일내로 환불처리될 예정입니다.</li>
              <li>본 사이트는 이용자의 표정을 수치화 한 정보만을 저장합니다. 이용자의 화면은 <strong>절대로</strong> 저장되지 않습니다.</li>
            </div>
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

      <v-col
        class="text-center"
        cols="12"
      >
        <v-card-text class="py-2 text-center" style="color: white; width=100%; background-color: #333333;">
          Copyright © <strong>EUREKA</strong> Team. {{ new Date().getFullYear() }} All Rights Reserved.
        </v-card-text>
      </v-col>
    </v-row>
  </v-footer>
</template>

<script>
export default {
  name: 'Footer',
  data: () => ({
    dialog: false,
  })
}
</script>

<style>

</style>