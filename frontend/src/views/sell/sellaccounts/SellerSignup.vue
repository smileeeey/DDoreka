<template>
<v-app>

  <v-form>
    <v-container style="width: 500px;">
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
          <strong>회원정보를 입력해주세요</strong>
          
          <v-text-field
            style="margin-top: 1rem;"
            solo
            flat
            dense
            outlined
            prepend-inner-icon="mdi-email-outline"
            :rules="[required_id, emailRules,]"
            label="아이디(이메일)"
            v-model="form.id"
          ></v-text-field> 
          
          <v-text-field
            v-model="form.password"
            solo
            flat
            dense
            outlined
            required
            prepend-inner-icon="mdi-lock-outline"
            :rules="[required, min_pw]"
            type="password"
            label="비밀번호"
          ></v-text-field> 

          <v-text-field
            solo
            flat
            dense
            outlined
            required
            prepend-inner-icon="mdi-lock-check-outline"
            :rules="[passwordConfirmationRule]"
            v-model="rePassword"
            type="password"
            label="비밀번호 확인"
            
          ></v-text-field> 
          <v-text-field
            solo
            flat
            dense
            outlined
            required
            prepend-inner-icon="mdi-account-outline"
            :rules="[required_name,]"
            label="이름"
            v-model="form.name"
          ></v-text-field> 

          <v-text-field
            solo
            flat
            dense
            outlined
            required
            prepend-inner-icon="mdi-phone"
            label="전화번호"
            v-model="form.phone"
          ></v-text-field> 
          
          <v-text-field
            solo
            flat
            dense
            outlined
            required
            prepend-inner-icon="mdi-cash-multiple"
            label="계좌은행"
            v-model="form.bank_company"
          ></v-text-field> 
          
          <v-text-field
            solo
            flat
            dense
            outlined
            required
            prepend-inner-icon="mdi-numeric"
            label="계좌번호"
            v-model="form.bank_account"
          ></v-text-field> 
          
        </v-col>
        <v-col cols="12">
          <v-divider></v-divider>
        </v-col>
          <SignupCheckbox :form="form" :enable="enable" />
        

        <v-col>
          <AccountsFooter />
        </v-col>
      </v-row>
    </v-container>
  </v-form>
</v-app>
</template>

<script>
import SignupCheckbox from '@/components/accounts/SignupCheckbox.vue'
import AccountsFooter from '@/components/accounts/AccountsFooter.vue'
export default {
  components: { AccountsFooter, SignupCheckbox },
  name: 'SellerSignup',
  data: () => ({
    rePassword: '',
    form: {
      id: '',
      password: '',
      name: '',
      phone: '',
      bank_company: '',
      bank_account: '',
    },
    show1: false,
    rules: {

    },
  }),
  methods: {
    action() {
      console.log(this.form)
    }
  },
  computed: {
    required() {
      return () => !!this.form.password || '비밀번호를 입력해주세요.'
    },
    required_id() {
      return () => !!this.form.id || '아이디(이메일)를 입력해주세요.'
    },
    required_name() {
      return () => !!this.form.name || '이름을 정확히 입력하세요.'
    },
    // required_phonenumber() {
    //   return () => !!this.form.phonenumber || '휴대폰 번호를 정확하게 입력하세요.'
    // },
    min_pw() {
      return () => this.form.password.length >= 8 || '비밀번호를 8자 이상 작성해주세요.'
    },
    emailRules() {
      return () => /.+@.+\..+/.test(this.form.id) || '아이디(이메일)는 이메일 형식으로 입력해주세요.'
    },
    passwordConfirmationRule() {
      return () => (this.form.password === this.rePassword) || '비밀번호가 일치하지 않습니다.'
    },
    enable() {
      if (this.required_name() == true && this.emailRules() == true && this.passwordConfirmationRule() == true && this.min_pw() == true) {
        return true
      } else {
        return false
      }
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