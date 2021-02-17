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
                    :value="seller.name"
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
                    :value="seller.email"
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
                    :value="seller.phone"
                    disabled 
                    style="font-weight: bold;"
                  ></v-text-field>
                </v-col>

                
                <v-col
                  cols="4"
                  md="6"
                >
                  <v-text-field
                    label="은행"
                    :value="seller.bank_company"
                    disabled 
                    style="font-weight: bold;"
                  >
                  </v-text-field>
                </v-col>

                <v-col
                  cols="8"
                  md="6"
                >
                  <v-text-field
                    label="계좌번호"
                    :value="seller.bank_account"
                    disabled 
                    style="font-weight: bold;"
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
import axios from 'axios'
import { mapState } from 'vuex'
export default {
  name: 'SellerProfile',
  data: () => ({
    store: {
      name: '',
      phone: '',
      main_address: '',
      sub_address: '',
      zipcode: '',
    },

  }),
  created() {
    this.store.name = this.sellerstore.name
    this.store.phone = this.sellerstore.phone
    this.store.main_address = this.sellerstore.main_address
    this.store.sub_address = this.sellerstore.sub_address
    this.store.zipcode = this.sellerstore.zipcode
  },
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
          labeltag.classList.add('v-label--active')
          mother = document.getElementById('zipcode').parentElement
          labeltag = mother.firstChild;
          labeltag.classList.add('v-label--active')
          document.getElementById("sub_address").focus()
          
        },
        
      }).open();
    },
    saveAddress: function () {
      this.store.zipcode = document.getElementById('zipcode').value
      this.store.main_address = document.getElementById('main_address').value
      console.log('this is store')
      console.log(this.store)
      console.log(this.seller)
      axios.post('http://i4d106.p.ssafy.io:8088/store/add', {
        'sellerId': this.seller.id,
        'phone': this.store.phone,
        'name': this.store.name,
        'main_address': this.store.main_address,
        'sub_address': this.store.sub_address,
        'zipcode': this.store.zipcode,
      })
        .then(res => {
          console.log('store add')
          console.log(res)
          this.$store.dispatch("SETSELLERSTORE", res.data)
        })
      
    }
  },

  computed: {
    ...mapState([
      'seller',
      'sellerstore',
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
  
</style>