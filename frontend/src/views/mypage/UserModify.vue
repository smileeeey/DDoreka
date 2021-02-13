<template>
  <div>
    <strong style="font-size: 2rem;">회원정보 수정</strong>
      <table class="simple_table">
        <tbody>
          <tr>
            <th scope="row">아이디(이메일)</th>
            <td><strong>{{email}}</strong></td>
          </tr>
          <tr>
            <th scope="row">이름</th>
            <td><strong>{{name}}</strong></td>
          </tr>
          <tr>
            <th scope="row">휴대폰 번호</th>
            <td><strong v-if="phone">{{phone | phone}}</strong></td>
          </tr>
          <tr>
            <th scope="row">비밀번호변경</th>
            <td>
              <ChangePassword />
            </td>
          </tr>
        </tbody>
      </table>
  </div>
</template>

<script>
import { mapState } from 'vuex'
import ChangePassword from '../../components/accounts/ChangePassword.vue'
export default {
  name: 'UserModify',
  components: {
    ChangePassword,
  },
  computed: {
    ...mapState([
      'name',
      'email',
      'phone'
    ])
  }
}
</script>

<style scoped>
  .simple_table { 
    width: 75%; 
    border-top: 2px solid #969696;
    border-bottom: 2px solid #969696;
    border-collapse: separate; 
    border-spacing: 1px;
  }
  
  .simple_table th { 
    width: 25%;
    padding: 20px; 
    background: #EEF1F8; 
    font-weight: bold; 
    text-align: left; 
    border-bottom: 1px solid #ddd;
    border-right: 1px solid #ddd;
  }

  .simple_table td { 
    padding: 15px; 
    border: none; 
    border-bottom: 1px solid #ddd;
    text-align: left; 
  }

  table {
    border-right: 1px solid #ddd;
  }

  
</style>