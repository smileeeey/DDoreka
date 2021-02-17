<template>
<v-app style="background-color: lightgray">

  <v-form>
    <v-container fluid style="width: 500px;">
      <v-row>
        <v-col
          cols="12"
          style="padding-bottom: 0;"
        >
          <v-img
            @click="$router.push({ name: 'Main' })"
            style="cursor: pointer;"
            src="@/assets/eureka_logo(blank).png"
            class="my-auto"
            max-height="150"
            max-width="500"
          >
          </v-img>
          
          <v-text-field
            solo
            flat
            dense
            outlined
            prepend-inner-icon="mdi-email-outline"
            :rules="[rules.required_id, rules.emailRules,]"
            label="판매자아이디(이메일)"
            v-model="form.email"
          ></v-text-field> 
          
          <v-text-field
            solo
            flat
            dense
            outlined
            required
            prepend-inner-icon="mdi-lock-outline"
            :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
            :rules="[rules.required, rules.min]"
            :type="show1 ? 'text' : 'password'"
            label="비밀번호"
            v-model="form.pw"
            @click:append="show1 = !show1"
            @keypress.enter="login"
          ></v-text-field> 
        </v-col>
        
        <v-col style="padding-top: 0;">
          <p style="text-align: right; cursor: pointer; color: #0275d8;">비밀번호 찾기 ></p>
        </v-col>
        
        <v-col cols="12">
          <v-btn style="background-color: #0275d8; color: white; 
            font-size: 1.5rem; font-weight: bold; 
            width: 100%; height: 150%;"
            :disabled="!enable"
            @click="login"
          >
            로그인
          </v-btn>
        </v-col>
        
        <v-col style="margin-top: 1rem;">
          <v-divider></v-divider>
        </v-col>
        <v-col cols="12">
          <v-btn style="background-color: white; color: #0275d8; 
            font-size: 1.5rem; font-weight: bold; 
            width: 100%; height: 150%;"
            @click="$router.push({ name: 'SellerSignup' })"
          >
            회원가입
          </v-btn>
        </v-col>
        <v-col>
          <AccountsFooter />
        </v-col>
      </v-row>
    </v-container>
  </v-form>
</v-app>
</template>

<script>
import axios from 'axios'

import AccountsFooter from '@/components/accounts/AccountsFooter.vue'
export default {
  components: { AccountsFooter },
  name: 'SellerLogin',
  data: () => ({
    form: {
      email: '',
      pw: '',
    },
    show1: false,
    rules: {
      required_id: value => !!value || '아이디(이메일)를 입력해주세요.',
      emailRules: v => /.+@.+\..+/.test(v) || '아이디(이메일)는 이메일 형식으로 입력해주세요.',
      required: value => !!value || '비밀번호를 입력해주세요.',
      min: v => v.length >= 8 || '비밀번호를 8자 이상 작성해주세요.',
    },
  }),
  computed: {
    enable () {
      if (this.rules.emailRules(this.form.email) == true && this.rules.min(this.form.pw) == true) {
        return true
      } else {
        return false
      }
    }
  },
  methods: {
    login: function () {
      axios.post('http://i4d106.p.ssafy.io:8088/login', {
        username: this.form.email,
        password: this.form.pw
      })
        .then(res => {
          console.log('1st')
          console.log(res.data)
          localStorage.setItem('seller-eureka-authorization', res.headers['eureka-authorization']);
          const token = localStorage.getItem('seller-eureka-authorization')
          axios.get(`http://i4d106.p.ssafy.io:8088/seller/getByEmail/${this.form.email}`, {}, {
            headers: {
              'eureka-authorization': token
            }
          })
            .then(response=> {
              console.log('2nd')
              console.log(response.data)
              this.$store.dispatch("SETSELLERINFO", response.data)
              console.log(response.data)
              axios.get(`http://i4d106.p.ssafy.io:8088/store/getBySellerId/${response.data.id}`)
                .then(resp => {
                  console.log('3rd')
                  console.log(resp.data)
                  this.$store.dispatch('SETSELLERSTORE', resp.data)
                  this.$router.push({ name: 'Dashboard' });
                })
                .catch(err => {
                  this.$router.push({ name: 'Dashboard' })
                })
            })

        })
      
    }
  }
}
</script>

<style scoped>
  >>> .v-text-field__slot {
    margin: 10px;;
  }

  >>> .v-messages__message {
    margin-top: 2px;
  }

</style>