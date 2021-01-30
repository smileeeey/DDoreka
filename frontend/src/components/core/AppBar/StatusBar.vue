<template>
  <div style="width: 100%; background-color: #ECECEC; display: flex; justify-content: center;">
    <v-card-text
      style="text-align: right; color: black; width: 50%;"
      class="pa-2"
    >
      <div>
        <a href="/login" class="mx-1" style="color: black; text-decoration:none; word-break: keep-all">로그인</a>
        <a href="/signup" class="mx-1" style="color: black; text-decoration:none; word-break: keep-all">회원가입</a>
        <a href="/mypage" class="mx-1" style="color: black; text-decoration:none; word-break: keep-all">고객센터</a>
      </div>
    </v-card-text>
  </div>
</template>

<script>
export default {
  name: 'StatusBar',
}
</script>

<style>

</style>