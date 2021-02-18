<template>
<v-app>

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
            label="아이디(이메일)"
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
        
        <!-- <v-col style="padding-top: 0;">
          <p style="text-align: right; cursor: pointer; color: #0275d8;">비밀번호 찾기 ></p>
        </v-col> -->
        
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
            @click="$router.push({ name: 'Signup' })"
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

import AccountsFooter from '../../components/accounts/AccountsFooter.vue'
export default {
  components: { AccountsFooter },
  name: 'Login',
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
      axios.post('https://i4d106.p.ssafy.io:8088/login', {
        username: this.form.email,
        password: this.form.pw
      })
        .then(res => {
          localStorage.setItem('eureka-authorization', res.headers['eureka-authorization']);
          const token = localStorage.getItem('eureka-authorization')
          axios.post('https://i4d106.p.ssafy.io:8080/user/login', this.form, {
            headers: {
              'eureka-authorization': token,
            }
          })
            .then(response => {
              this.$store.dispatch("LOGIN", response.data.data)
              axios.get(`https://i4d106.p.ssafy.io:8080/user/cart/${this.form.email}`, {
                headers: {
                  'eureka-authorization': token,
                }
              })
                .then(newres=> {
                  this.$store.dispatch("SETWISHLIST", newres.data.data)
                  if (this.$route.query.next) {
                    this.$router.push({ name: this.$route.query.next })
                  } else {
                    this.$router.push({ name: 'Main' })
                  }
                })
            })
          // jwt token setting required
          
        })
        .catch(err => {
          alert('로그인에 실패하였습니다. 비밀번호를 확인해주세요.')
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