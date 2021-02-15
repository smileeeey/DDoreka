<template>
<div style="width: 100%;">
  <v-col>
    <strong>서비스 약관에 동의해주세요</strong>
    <v-checkbox v-model="boxAll" @click="checkedAll">
      <template v-slot:label>
        <div style="font-size: 1.1rem; margin-left: 1rem;">
          <strong>모두 동의합니다.</strong>
        </div>
      </template>
    </v-checkbox>
    <v-card
      class="mx-auto"
      max-width="100%"
      outlined
    >
      <v-list-item>
        <v-list-item-content style="padding: 0;">
          <v-list-item-title class="headline mb-1">
              <v-container fluid style="padding: 0;">
                <v-checkbox v-for="(box, idx) in boxes" :key="idx" v-model="box.checkbox" @click="selected">
                  <template v-slot:label>
                    <div style="font-size: 1.1rem; margin-left: 1rem;">
                      {{box.text}}
                    </div>
                  </template>
                </v-checkbox>
              </v-container>
          </v-list-item-title>
        </v-list-item-content>


      </v-list-item>

    </v-card>
  </v-col>

  <v-col cols="12">
    <v-btn style="background-color: #0275d8; color: white; 
      font-size: 1.3rem; font-weight: bold; 
      width: 100%; height: 3rem;"
      :disabled="!boxAll || !enable"
      @click="signup"
    >
      동의하고 가입하기
    </v-btn>
  </v-col>

</div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'SignupCheckbox',
  data: () => ({
    boxAll: false,
    boxes: [
      {
        checkbox: false, text: '[필수] 만 14세 이상입니다'
      },
      {
        checkbox: false, text: '[필수] 유레카 이용약관 동의'
      },
      {
        checkbox: false, text: '[필수] 전자금융거래 이용약관 동의'
      },
      {
        checkbox: false, text: '[필수] 개인정보 수집 및 이용 동의'
      },
      {
        checkbox: false, text: '[필수] 개인정보 제공 동의'
      },
    ]
  }),
  props: {
    enable: Boolean,
    form: Object,
  },
  methods: {
    checkedAll() {
      for (let i in this.boxes) {
        this.boxes[i].checkbox = this.boxAll
      }
    },
    selected() {
      for (let i in this.boxes) {
        if(! this.boxes[i].checkbox) {
          this.boxAll = false;
          return
        } else {
          this.boxAll = true;
        }
      }
    },
    signup() {
      if (document.location.href.split('sell').length > 1) {
        // console.log('i am seller')
        console.log(this.form)
        axios.post(`http://i4d106.p.ssafy.io:8088/seller/add`, {
          name: this.form.name,
          pw: this.form.password,
          email: this.form.id,
          phone: this.form.phone,
          bank_company: this.form.bank_company,
          bank_account: this.form.bank_account
        })
          .then(res => {
            
            if (res.status == 200) {
              axios.post(`http://i4d106.p.ssafy.io:8088/login/add`, {
                username: this.form.id,
                password: this.form.password,
                role: 'SELLER',
              }, {
                headers: {
                  'eureka-authorization': "Seungyun eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzZXVuZ3l1biIsImF1dGhvcml0aWVzIjpbeyJhdXRob3JpdHkiOiJST0xFX0FETUlOIn1dLCJpYXQiOjE2MTM0MDI5MTIsImV4cCI6MTYxNDU1NjgwMH0.bbgvRGYbXv52BzwAFpIPq_WjkhpP2D1dYpByx6awEKZyj1j-WUMHFCor-4yn1VNML73JFieZpCDKtcBErqYgnA",                  
                }
              })
                .then(response => {
                  console.log(response)
                  this.$router.push({ name: 'SellerLogin' })
                })
            }
          })
      } else {
        // console.log('i am buyer')
        axios.post(`http://i4d106.p.ssafy.io:8080/user/signup`, this.form)
          .then(res => {
            if (res.data.response == 'success') {
              axios.post(`http://i4d106.p.ssafy.io:8088/login/add`, {
                username: this.form.email,
                password: this.form.pw,
                role: 'USER',
              }, {
                headers: {
                  'eureka-authorization': "Seungyun eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzZXVuZ3l1biIsImF1dGhvcml0aWVzIjpbeyJhdXRob3JpdHkiOiJST0xFX0FETUlOIn1dLCJpYXQiOjE2MTM0MDI5MTIsImV4cCI6MTYxNDU1NjgwMH0.bbgvRGYbXv52BzwAFpIPq_WjkhpP2D1dYpByx6awEKZyj1j-WUMHFCor-4yn1VNML73JFieZpCDKtcBErqYgnA",
                }
              })
                .then(response => {
                  console.log(response)
                  this.$router.push( {name: 'Main'} )
                })
            } else {
              // alert
              console.log('signup failed')
            }
          })
      }
      
    }
  }
  
}
</script>

<style scoped>
  >>> .v-messages {
    display: none;
  }
</style>