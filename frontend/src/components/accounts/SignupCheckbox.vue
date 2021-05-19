<template>
  <div style="width: 100%">
    <v-col>
      <strong>서비스 약관에 동의해주세요</strong>
      <v-checkbox v-model="boxAll" @click="checkedAll">
        <template v-slot:label>
          <div style="font-size: 1.1rem; margin-left: 1rem">
            <strong>모두 동의합니다.</strong>
          </div>
        </template>
      </v-checkbox>
      <v-card class="mx-auto" max-width="100%" outlined>
        <v-list-item>
          <v-list-item-content style="padding: 0">
            <v-list-item-title class="headline mb-1">
              <v-container fluid style="padding: 0">
                <v-checkbox
                  v-for="(box, idx) in boxes"
                  :key="idx"
                  v-model="box.checkbox"
                  @click="selected"
                >
                  <template v-slot:label>
                    <div style="font-size: 1.1rem; margin-left: 1rem">
                      {{ box.text }}
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
      <v-btn
        style="
          background-color: #0275d8;
          color: white;
          font-size: 1.3rem;
          font-weight: bold;
          width: 100%;
          height: 3rem;
        "
        :disabled="!boxAll || !enable"
        @click="signup"
      >
        동의하고 가입하기
      </v-btn>
    </v-col>
  </div>
</template>

<script>
import { mapActions } from "vuex";

export default {
  name: "SignupCheckbox",
  data: () => ({
    boxAll: false,
    boxes: [
      {
        checkbox: false,
        text: "[필수] 만 14세 이상입니다",
      },
      {
        checkbox: false,
        text: "[필수] 유레카 이용약관 동의",
      },
      {
        checkbox: false,
        text: "[필수] 전자금융거래 이용약관 동의",
      },
      {
        checkbox: false,
        text: "[필수] 개인정보 수집 및 이용 동의",
      },
      {
        checkbox: false,
        text: "[필수] 개인정보 제공 동의",
      },
    ],
  }),
  props: {
    enable: Boolean,
    form: Object,
  },
  methods: {
    ...mapActions("accountStore", ["SELLER_SIGNUP", "USER_SIGNUP"]),
    checkedAll() {
      for (let i in this.boxes) {
        this.boxes[i].checkbox = this.boxAll;
      }
    },
    selected() {
      for (let i in this.boxes) {
        if (!this.boxes[i].checkbox) {
          this.boxAll = false;
          return;
        } else {
          this.boxAll = true;
        }
      }
    },
    signup() {
      if (this.$route.fullPath.indexOf("sell") == 1) {
        // console.log('i am seller')
        this.SELLER_SIGNUP({
          name: this.form.name,
          pw: this.form.password,
          email: this.form.id,
          phone: this.form.phone,
          bank_company: this.form.bank_company,
          bank_account: this.form.bank_account,
        })
        .then(() => {
          this.$router.push({ name: "SellerLogin" });
        });
      }
      else{
        this.USER_SIGNUP({ email : this.form.email, pw : this.form.pw, name : this.form.name})
        .then(()=>{
          this.$router.push( {name: 'Main'});
        })
      }
    },
  },
};
</script>
