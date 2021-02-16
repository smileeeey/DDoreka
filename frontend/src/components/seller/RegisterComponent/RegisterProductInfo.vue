<template>
  <v-card>
    <v-container>
      <v-row>
        <v-col
          cols="12"
          md="4"
        >
          <span style="font-size: 1.25rem">

          판매자<span style="color: red;"> * </span>
          </span>
          <v-text-field 
            value="권세진"
            disabled
            outlined 
            style="display: inline-block; width: 50%;" 
            class="mx-5"
          ></v-text-field>
        </v-col>
        <v-col
          cols="12"
          md="4"
        >
          <span style="font-size: 1.25rem">

          등록일<span style="color: red;"> * </span>
          </span>
          <v-text-field 
            :value="date"
            disabled
            outlined 
            style="display: inline-block; width: 50%;" 
            class="mx-5"
          ></v-text-field>
        </v-col>
        <v-col
          cols="12"
        >
          <span style="font-size: 1.25rem">

          상품명<span style="color: red;"> * </span>
          </span>
          <v-text-field v-model="productname" outlined style="display: inline-block; width: 50%;" class="mx-5"></v-text-field>
        </v-col>
        <v-col
          cols="12"
        >
          <span style="font-size: 1.25rem">

          옵션<span style="color: red;"> * </span><span style="color: red; font-size: 0.75rem"> 1개 이상</span>
          <a @click="resetOptions"><v-icon class="mx-5" color="primary">mdi-restore</v-icon></a>
          </span>
        </v-col>
        <v-col
          cols="12"
          v-for="(option, idx) in options"
          :key="idx"
        >
          <v-card>
            <v-container>
              <v-row>
                <v-col
                  cols="3"
                > 
                  <v-text-field v-model="option.name" label="옵션" outlined style="display: inline-block"></v-text-field>
                </v-col>

                <v-col
                  cols="3"
                > 
                  <v-text-field v-model="option.stockQuantity" label="재고 수량" outlined style="display: inline-block"></v-text-field>
                </v-col>
                <v-col
                  cols="3"
                > 
                  <v-text-field v-model="option.price" label="원래 가격" outlined style="display: inline-block"></v-text-field>
                </v-col>
                <v-col
                  cols="3"
                > 
                  <v-text-field v-model="option.discountPrice" label="할인된 가격" outlined style="display: inline-block"></v-text-field>
                </v-col>
                <v-col
                  cols="12"
                  class="text-right"
                >
                  <a @click="addOption"><v-icon>mdi-plus</v-icon></a>
                  <a @click="removeOption(idx)"><v-icon>mdi-minus</v-icon></a>
                </v-col>
              </v-row>
            </v-container>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </v-card>
</template>

<script>
export default {
  name: 'RegisterProductInfo',
  data: () => ({
    productname: '',
    options: [
      {
        name: '',
        stockQuantity: '',
        price: '',
        discountPrice: '',
      },
    ],
  }),
  methods: {
    addOption: function () {
      const newOption = {
        name: '',
        stockQuantity: '',
        price: '',
        discountPrice: '',
      }
      this.options.push(newOption)
    },
    removeOption: function (idx) {
      if (this.options.length > 1) this.options.splice(idx, 1)
    },
    resetOptions: function () {
      this.options = [{
        name: '',
        stockQuantity: '',
        price: '',
        discountPrice: '',
      }]
    }
  },
  watch: {
    options: {
      deep: true,
      handler () {
        this.$emit('options', this.options)
      }
    },
    productname() {
      this.$emit('productname', this.productname)
    }
  },
  computed: {
    date () {
      let A = new Date()
      return A.getUTCFullYear() + '년 ' + (A.getUTCMonth()+1) + '월 ' + A.getUTCDate() + '일'
    }
  }
}
</script>

<style scoped>
  >>> .v-text-field__slot > input {
    padding: 0;
  }
</style>