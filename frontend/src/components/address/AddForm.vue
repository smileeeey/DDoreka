<template>
  <div>
    <v-form>
      <v-container style="width: 100%;">
        <v-row>
          <v-col
            cols="12"
            style="padding-bottom: 0;"
          >
            <v-text-field
              style="margin-top: 1rem;"
              solo
              flat
              dense
              outlined
              prepend-inner-icon="mdi-account-outline"
              label="받는 사람"
              v-model="address.name"
            ></v-text-field> 
            <div id="blueborder">

            <v-text-field
              id="main_address"
              solo
              flat
              dense
              outlined
              required
              prepend-inner-icon="mdi-map-marker-outline"
              append-icon="mdi-magnify"
              label="주소 검색"
              
              @click="searchAddress"
            ></v-text-field> 
            </div>
            <v-text-field
              id="sub_address"
              solo
              flat
              dense
              outlined
              required
              prepend-inner-icon="mdi-map-marker-outline"
              v-model="address.sub_address"
              label="상세 주소"
            ></v-text-field> 
            <div style="border: 1px solid black; display: none;">
              <input type="text" id="sample6_postcode" placeholder="우편번호">
              <input type="text" id="sample6_address" placeholder="주소"><br>
              <input type="text" id="sample6_detailAddress" placeholder="상세주소">
              <input type="text" id="sample6_extraAddress" placeholder="참고항목">
            </div>


            <v-text-field
              solo
              flat
              dense
              outlined
              required
              prepend-inner-icon="mdi-cellphone"
              v-model="address.phonenumber"
              label="휴대폰 번호"
              
            ></v-text-field> 
            <v-text-field
              id="comment"
              solo
              flat
              dense
              outlined
              required
              prepend-inner-icon="mdi-message-processing-outline"
              label="배송 요청사항"
              v-model="address.comment"
            ></v-text-field> 
            <v-text-field
              id="comment"
              solo
              flat
              dense
              outlined
              required
              prepend-inner-icon="mdi-tag-multiple"
              label="별명 (ex : 본가, 자취방)"
              v-model="address.nickname"
            ></v-text-field> 
            
          </v-col>
          <v-col cols="12">
            <v-btn style="background-color: #0275d8; color: white; 
              font-size: 1.5rem; font-weight: bold; 
              width: 100%; height: 150%;"
              @click="saveAddress"
            >
              저장
            </v-btn>
          </v-col>
        </v-row>
      </v-container>
    </v-form>
    
  </div>
</template>



<script>
import axios from 'axios'
import { mapState } from 'vuex'
export default {
  
  name: 'AddForm',
  data: () => ({
    address: {
      name: '',
      main_address: '',
      sub_address: '',
      nickname: '',
      phonenumber: '',
      comment: '',
    }
  }),
  mounted() {
      let recaptchaScript = document.createElement('script')
      recaptchaScript.setAttribute('src', 'https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js')
      document.head.appendChild(recaptchaScript)
    },

  methods: {
    searchAddress: function () {

      new window.daum.Postcode({
        popupName: 'postcodePopup',
        oncomplete: function(data) {
          var addr = '';
          var extraAddr = '';

          if (data.userSelectedType === 'R') {
            addr = data.roadAddress;
          } else {
            addr = data.jibunAddress;
          }

          if (data.userSelectedType === 'R') {
            if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
              extraAddr += data.bname;
            }

            if (data.buildingName !== '' && data.apartment === 'Y') {
              extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            }

            if (extraAddr !== '') {
              extraAddr = ' (' + extraAddr + ')';
            }
            document.getElementById("sample6_extraAddress").value = extraAddr;

          } else {
            document.getElementById("sample6_extraAddress").value = '';
          }

          
          
          document.getElementById('main_address').value = addr;
          var mother = document.getElementById('main_address').parentElement
          var labeltag = mother.firstChild;
          labeltag.innerText = '';
          document.getElementById("sub_address").focus()
          
        },
        
      }).open();
      
    },
    saveAddress: function () {
      this.address.main_address = document.getElementById('main_address').value
      const form = {
            "nickname": this.address.nickname,
            "mainAddress": this.address.main_address,
            "subAddress": this.address.sub_address,
            "zipcode": '',
            "recipientPhone": this.address.phonenumber,
            "recipientName": this.address.name,
            "deliveryMsg": this.address.comment
      }
      axios.post(`http://i4d106.p.ssafy.io:8080/user/address/${this.email}`, form, {})
        .then(res => {
          this.$emit('saveAddress', res.data.data)
        })
      
    }
  },
  computed: {
    ...mapState([
      'email',
    ])
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

  #blueborder >>> fieldset {
    border: 3px solid blue;
  }
  
</style>