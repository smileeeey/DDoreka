<template>
  <div style="width: 100%" @mouseleave="CScenter = false">
    <div
      style="
        width: 100%;
        background-color: #ececec;
        display: flex;
        justify-content: center;
      "
    >
      <v-card-text
        style="text-align: right; color: black; width: 75%"
        class="pa-2"
      >
        <div v-if="!isLogin">
          <a
            @click="goLogin"
            class="mx-1"
            style="color: black; text-decoration: none; word-break: keep-all"
            >로그인</a
          >
          <a
            @click="$router.push({ name: 'Signup' })"
            class="mx-1"
            style="color: black; text-decoration: none; word-break: keep-all"
            >회원가입</a
          >
        </div>
        <div v-else>
          <a
            @click="$router.push({ name: 'UserModify' })"
            class="mx-1"
            style="
              color: black;
              text-decoration: none;
              word-break: keep-all;
              font-weight: bold;
            "
            >{{ userData.name }}님</a
          >
          <a
            @click="logout"
            class="mx-1"
            style="color: black; text-decoration: none; word-break: keep-all"
            >로그아웃</a
          >
          <a
            @click="$router.push({ name: 'Mypage' })"
            class="mx-1"
            style="color: black; text-decoration: none; word-break: keep-all"
            >마이페이지</a
          >
        </div>
      </v-card-text>
    </div>
    <div></div>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";

export default {
  name: "StatusBar",
  data: () => ({
    items: [
      "자주 묻는 질문",
      "1:1 채팅문의",
      "고객의 소리",
      "취소 / 반품 안내",
    ],
    CScenter: false,
  }),
  computed: {
    ...mapState('accountStore',["isLogin", "userData"]),
  },
  methods: {
    ...mapMutations("accountStore", ["SET_LOGOUT"]),
    ...mapActions("accountStore", ["LOGOUT"]),
    logout() {
      this.SET_LOGOUT();
      localStorage.removeItem("seller-eureka-authorization");
      if(this.$route.path !='/') this.$router.push({name : "Main"});
    },
    goLogin() {
      this.$router.push({ name: "Login", query: { next: "Main" } });
    },
  },
};
</script>

<style scoped>
</style>