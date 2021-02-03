<template>
  <div>
  <v-container fluid>
    <v-row>
      <v-col cols="3">
        <v-subheader>현재 비밀번호</v-subheader>
      </v-col>
      <v-col cols="4">
        <v-text-field
          solo flat dense outlined required validate-on-blur
          type="password"
          v-model="form.password"
        ></v-text-field>
      </v-col>
    </v-row>

    <v-row>
      <v-col cols="3">
        <v-subheader>새 비밀번호</v-subheader>
      </v-col>
      <v-col cols="4">
        <v-text-field
          solo flat dense outlined required
          :rules="[required, min_pw]"
          v-model="form.newpassword"
          type="password"
        ></v-text-field>
      </v-col>
    </v-row>

    <v-row>
      <v-col cols="3">
        <v-subheader>비밀번호 다시 입력</v-subheader>
      </v-col>
      <v-col cols="4">
        <v-text-field
          solo flat dense outlined required
          :rules="[passwordConfirmationRule]"
          v-model="rePassword"
          type="password"
        ></v-text-field>
      </v-col>
    </v-row>
    <v-row>
      <v-col offset="2" cols="2">
        <v-btn :disabled="!enable">비밀번호 변경</v-btn>
      </v-col>
    </v-row>
  </v-container>

  </div>
</template>

<script>
export default {
  name: 'ChangePassword',
  data: () => ({
    rePassword: '',
    form: {
      password: '',
      newpassword: '',
    }
  }),
  computed: {
    required() {
      return () => !!this.form.password || '비밀번호를 입력해주세요.'
    },
    min_pw() {
      return () => this.form.newpassword.length >= 8 || '비밀번호를 8자 이상 작성해주세요.'
    },
    passwordConfirmationRule() {
      return () => (this.form.newpassword === this.rePassword) || '비밀번호가 일치하지 않습니다.'
    },
    enable() {
      if (this.required() == true && this.min_pw() == true && this.passwordConfirmationRule() == true && this.form.password.length > 0) {
        return true
      } else {
        return false
      }
    }
  }
}
</script>

<style scoped>
  .row {
    margin: -24px;
  }

  >>> .v-text-field__slot {
    margin: 3px;
  }

  >>> .v-messages__message {
    margin-top: 3px;
  }

  .button {
    border-color: #bcbfc6;
    color: #777;
    background-color: #fafbf6;
    background-image: linear-gradient(#fff, #f1f1f1);
    text-align: center;
  }

</style>