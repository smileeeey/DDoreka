<template>
  <div>
    <strong style="font-size: 1.5rem;">받는사람정보</strong
    ><button @click="changeAddress">배송지변경</button>
    <table class="simple_table" style="margin-top: 1rem; margin-bottom: 3rem;">
      <tbody>
        <tr>
          <th scope="row">이름</th>
          <td>
            <span id="uniquename" :v-model="name"></span>
          </td>
        </tr>
        <tr>
          <th scope="row">배송주소</th>
          <td>
            <span id="uniqueaddress"></span>
          </td>
        </tr>
        <tr>
          <th scope="row">연락처</th>
          <td>
            <span id="uniquephonenumber">{{ phonenumber | phone }}</span>
          </td>
        </tr>
        <tr>
          <th scope="row">배송 요청사항</th>
          <td>
            <span id="uniquecomment"></span>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {
  name: 'DestinationInfo',
  data() {
    return {
      name: '',
      main_address: '',
      sub_address: '',
      nickname: '',
      phonenumber: '',
      comment: '',
    };
  },
  methods: {
    changeAddress: function() {
      window.open(
        'http://i4d106.p.ssafy.io:8085/popup/address',
        'PopupWin',
        'width=500, height=600'
      );
    },
  },
};
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
  min-height: 50px;
  width: 25%;
  padding: 10px 20px;
  background: #f4f4f4;
  font-weight: bold;
  text-align: right;

  border-bottom: 1px solid #ddd;
  border-right: 1px solid #ddd;
}

.simple_table td {
  padding: 10px;
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
