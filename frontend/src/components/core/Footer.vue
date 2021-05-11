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
      <v-btn
        v-for="(link, idx) in links"
        :key="idx"
        :to="link.to"
        text
        rounded
        class="my-2 mx-5"
      >
        {{ link.text }}
      </v-btn>

      <v-col cols="12">
        <v-divider></v-divider>
      </v-col>
      
      <v-row justify="center" class="my-3">
        <v-col cols="3">
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
        <v-col cols="4">
          <div style="font-size: 15px;">
            
            EUREKA 유레카 | TEAM 중대장은 만족했다
            <br>
            경상북도 구미시 진평4길 21
            <br>
            등록번호 : 000-00-00000
            <br>
            전화번호 : 000-0000-0000
          </div>
        </v-col>
        <v-col cols="2">
          <div style="word-break: break-all; font-size: 15px;">
            Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
          </div>
        </v-col>
      </v-row>

      <v-col
        class="text-center"
        cols="12"
      >
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
    links: [
      {
        to: '#',
        text: '공지사항'
      },
      {
        to: '#',
        text: '이용약관'
      },
      {
        to: '#',
        text: '개인정보 처리방침'
      },
      {
        to: '/sell',
        text: '판매자 페이지'
      },
      
    ]
  })
}
</script>

<style>

</style>