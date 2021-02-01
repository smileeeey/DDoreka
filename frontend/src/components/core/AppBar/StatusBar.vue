<template>
  <div style="width: 100%;" @mouseleave="CScenter=false">
    <div style="width: 100%; background-color: #ECECEC; display: flex; justify-content: center;">
      <v-card-text
        style="text-align: right; color: black; width: 75%;"
        class="pa-2"
      >
        <div>
          <a @click="$router.push({ name: 'Login' })" class="mx-1" style="color: black; text-decoration:none; word-break: keep-all">로그인</a>
          <a @click="$router.push({ name: 'Signup' })" class="mx-1" style="color: black; text-decoration:none; word-break: keep-all">회원가입</a>
          <a @mouseover="CScenter=true" @click="$router.push({ name: 'Mypage' })"
            class="mx-1" style="color: black; text-decoration:none; word-break: keep-all">고객센터
            <div style="position: relative; display: none;">
              <v-card
                class="mx-auto"
                width="150"
                tile
                style="position: absolute; right: 0px; z-index: 3;"
                v-show="CScenter"
              >
                <v-list-item v-for="(item, idx) in items" :key="idx">
                  <v-list-item-content>
                    <v-list-item-title v-text="item"></v-list-item-title>
                  </v-list-item-content>
                </v-list-item>

              </v-card>
            </div>
          </a>
          
        </div>
      </v-card-text>
    </div>
    <div>

      
    </div>
  </div>
</template>

<script>
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
  })
}
</script>

<style scoped>

</style>