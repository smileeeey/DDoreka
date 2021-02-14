<template>
  <v-container
    fluid
  >
    <v-row justify="center">
      <v-col
        cols="12"
        md="8"
      >
        <v-card>
          <v-form>
            <v-container>
              <v-row>
                <v-col
                  cols="12"
                  md="3"
                >
                  <v-text-field
                    label="이름"
                    value="권세진"
                    disabled 
                    style="font-weight: bold;"
                  ></v-text-field>
                </v-col>
                
                <v-col
                  cols="12"
                  md="5"
                >
                  <v-text-field
                    label="이메일"
                    value="sejinkwon@naver.com"
                    disabled 
                    style="font-weight: bold;"
                  ></v-text-field>
                </v-col>
                
                <v-col
                  cols="12"
                  md="4"
                >
                  <v-text-field
                    label="전화번호"
                    value="01077269318"
                    
                  ></v-text-field>
                </v-col>

                
                
                <v-col
                  cols="4"
                  md="6"
                >
                  <v-text-field
                    label="은행"
                    value="신한은행"
                  >
                  </v-text-field>
                </v-col>

                <v-col
                  cols="8"
                  md="6"
                >
                  <v-text-field
                    label="계좌번호"
                    value="110435498410"
                  >
                  </v-text-field>
                </v-col>
                <v-col
                  cols="12"
                  md="6"
                >
                  <v-text-field
                    label="매장 이름"
                    v-model="store.name"
                  >
                  </v-text-field>
                </v-col>

                <v-col
                  cols="12"
                  md="6"
                >
                  <v-text-field
                    label="매장번호"
                    v-model="store.phone"
                  >
                  </v-text-field>
                </v-col>

                
                <v-col
                  cols="6"
                  md="3"
                >
                  <v-text-field
                    id="zipcode"
                    label="우편번호"
                    v-model="store.zipcode"
                  >
                  </v-text-field>
                </v-col>
                <v-col
                  cols="6"
                  md="3"
                >
                  <v-btn style="margin-top: 2rem;" @click="searchAddress">우편번호찾기</v-btn>
                </v-col>
              </v-row>
              
              <v-row style="margin-top: 0;">
                
                <v-col
                  cols="12"
                  md="6"
                >
                  <v-text-field
                    id="main_address"
                    label="주소"
                    v-model="store.main_address"
                  >
                  </v-text-field>
                </v-col>

                <v-col
                  cols="12"
                  md="6"
                >
                  <v-text-field
                    id="sub_address"
                    label="상세주소"
                    v-model="store.sub_address"
                  >
                  </v-text-field>
                </v-col>

                <v-col
                  cols="12"
                  class="text-right"
                >
                  <v-btn color="primary" class="mr-0" @click="saveAddress">
                    업데이트
                  </v-btn>
                </v-col>

              </v-row>
            </v-container>
          </v-form>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  name: 'SellerProfile',
  data: () => ({
    store: {
      name: '티바 두마리치킨',
      phone: '01077269318',
      main_address: '경북 구미시 진평4길 21',
      sub_address: 'IWC 302호',
      zipcode: '12345',
    },
    seller: {
      bank_company: '신한은행',
      bank_account: '110435498410',
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
          var zonecode = data.zonecode;

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
            

          } else {
            // 
          }

          
          document.getElementById('main_address').value = addr;
          document.getElementById('zipcode').value = zonecode;
          var mother = document.getElementById('main_address').parentElement
          var labeltag = mother.firstChild;
          labeltag.innerText = '';
          document.getElementById("sub_address").focus()
          
        },
        
      }).open();
      console.log('start')
    },
    saveAddress: function () {
      this.store.zipcode = document.getElementById('zipcode').value
      this.store.main_address = document.getElementById('main_address').value
      
    }
  },

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