<template>
  <div>
    <strong style="font-size: 1.5rem;">구매자정보</strong>
      <table class="simple_table" style="margin-top: 1rem; margin-bottom: 3rem;">
        <tbody>
          <tr>
            <th scope="row">이름</th>
            <td>{{name}}</td>
          </tr>
          <tr>
            <th scope="row">이메일</th>
            <td>{{email}}</td>
          </tr>
          <tr>
            <th scope="row">휴대폰 번호</th>
            <td><input type="text" v-model="phonenumber"><button @click="changePhone">저장</button></td>
          </tr>
        </tbody>
      </table>
  </div>
</template>

<script>
import { mapState } from 'vuex'
import axios from 'axios'
export default {
  name: 'BuyerInfo',
  data: () => ({
    phonenumber: null,
  }),
  computed: {
    ...mapState([
      'name',
      'email',
      'phone',
    ])
  },
  created () {
    this.phonenumber = this.phone
  },
  methods: {
    changePhone: function () {
      axios.put('http://i4d106.p.ssafy.io:8080/user/update/phone', {
        email: this.email,
        phone: this.phonenumber
      })
        .then(res => {
          console.log(res)
          console.log(res.data)
          alert('휴대폰 번호가 변경되었습니다.')
          this.$store.dispatch('CHANGEPHONE', this.phonenumber)
        })
    }
  }
}
</script>

<style scoped>
  .simple_table { 
    width: 100%; 
    border-top: 2px solid #969696;
    border-bottom: 2px solid #969696;
    border-collapse: separate; 
    border-spacing: 1px;
  }
  
  .simple_table th { 
    width: 25%;
    padding: 0px 20px; 
    background: #F4F4F4; 
    font-weight: bold; 
    text-align: right; 
    
    border-bottom: 1px solid #ddd;
    border-right: 1px solid #ddd;
  }

  .simple_table td { 
    padding: 10px; 
    border: none; 
    border-bottom: 1px solid #ddd;
    text-align: left; 
  }

  table {
    border-right: 1px solid #ddd;
  }

  input {
    height: 32px;
    width: 150px;
    margin-bottom: -1px;
    padding: 0 0 0 5px;
    border: 1px solid #ddd;
    font-size: 16px;
    color: #333;
  }

  button {
    margin: 0 10px;
    padding: 4px 8px;
    padding-bottom: 3px;
    border: 1px solid #999;
    color: #333;
    border-radius: 1px;
    box-shadow: 0 -2px 0 rgb(0 0 0 / 10%) inset;
    font-size: 14px;
  }
  
</style>