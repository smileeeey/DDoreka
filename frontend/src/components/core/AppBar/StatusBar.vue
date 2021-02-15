<template>
  <div style="width: 100%;" @mouseleave="CScenter=false">
    <div style="width: 100%; background-color: #ECECEC; display: flex; justify-content: center;">
      <v-card-text
        style="text-align: right; color: black; width: 75%;"
        class="pa-2"
      >
        <div v-if="!login">
          <a @click="goLogin" class="mx-1" style="color: black; text-decoration:none; word-break: keep-all">로그인</a>
          <a @click="$router.push({ name: 'Signup' })" class="mx-1" style="color: black; text-decoration:none; word-break: keep-all">회원가입</a>
        </div>
        <div v-else>
          <a @click="$router.push({ name: 'UserModify' })" class="mx-1" style="color: black; text-decoration:none; word-break: keep-all; font-weight: bold;">{{name}}님</a>
          <a @click="logout" class="mx-1" style="color: black; text-decoration:none; word-break: keep-all">로그아웃</a>
          <a @click="$router.push({ name: 'Mypage' })" class="mx-1" style="color: black; text-decoration:none; word-break: keep-all">마이페이지</a>
        </div>
      </v-card-text>
    </div>
    <div>

      
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex'

export default {
  name: 'StatusBar',
  data: () => ({
    items: [
      '자주 묻는 질문',
      '1:1 채팅문의',
      '고객의 소리',
      '취소 / 반품 안내',
    ],
    CScenter: false,
  }),
  computed: {
    ...mapState([
      'login',
      'name',
    ])
  },
  methods: {
    logout() {
      this.$store
        .dispatch("LOGOUT")
        .then(() => {
          if (this.$route.path !== "/") this.$router.replace("/");
        })
        .catch(() => {
          console.error("logout error occured!!");
        });
    },
    goLogin() {
      this.$router.push({ name: 'Login', query: { next: 'Main' } })
    }
  }
}
</script>

<style scoped>

</style>