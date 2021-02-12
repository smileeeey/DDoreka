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
                    value="티바 두마리 치킨"
                  >
                  </v-text-field>
                </v-col>

                <v-col
                  cols="12"
                  md="6"
                >
                  <v-text-field
                    label="매장번호"
                    value="01077269318"
                  >
                  </v-text-field>
                </v-col>

                
                <v-col
                  cols="6"
                  md="3"
                >
                  <v-text-field
                    label="우편번호"
                    value="12345"
                  >
                  </v-text-field>
                </v-col>
                <v-col
                  cols="6"
                  md="3"
                >
                  <v-btn style="margin-top: 2rem;">우편번호찾기</v-btn>
                </v-col>
              </v-row>
              
              <v-row style="margin-top: 0;">
                
                <v-col
                  cols="12"
                  md="6"
                >
                  <v-text-field
                    label="주소"
                    value="경상북도 구미시 진평4길 21"
                  >
                  </v-text-field>
                </v-col>

                <v-col
                  cols="12"
                  md="6"
                >
                  <v-text-field
                    label="상세주소"
                    value="IWC 302호"
                  >
                  </v-text-field>
                </v-col>

                <v-col
                  cols="12"
                  class="text-right"
                >
                  <v-btn color="primary" class="mr-0">
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