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
        v-for="link in links"
        :key="link"
        
        text
        rounded
        class="my-2 mx-5"
      >
        {{ link }}
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
        <v-col cols="3">Team 중대장은 만족했다</v-col>
        <v-col cols="3">^-^</v-col>
      </v-row>

      <v-col
        class="text-center"
        cols="12"
      >
        <v-card-text class="py-2 text-center" style="color: white; width=100%; background-color: #333333;">
          Copyright © <strong>EUREKA</strong> Corp. 2021-{{ new Date().getFullYear() }} All Rights Reserved.
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
      '공지사항',
      '이용약관',
      '개인정보 처리방침',
    ]
  })
}
</script>

<style>

</style>