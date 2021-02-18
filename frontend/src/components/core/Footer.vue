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
        <v-col cols="7">
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

  })
}
</script>

<style>

</style>