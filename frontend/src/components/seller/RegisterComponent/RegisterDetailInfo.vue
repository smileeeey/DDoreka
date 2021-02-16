<template>
  <v-card>
    <v-container>
      <v-row>
        <v-col
          cols="12"
        >
          <span style="font-size: 1.25rem">제품상세표기정보</span>
          <a @click="resetInfo"><v-icon class="mx-5" color="primary">mdi-restore</v-icon></a>
        </v-col>
        <v-col
          cols="12"
          v-for="(info, idx) in detailInfo"
          :key="idx"
        >
          <v-card>
            <v-container>
              <v-row>
                <v-col
                  cols="6"
                > 
                  <v-text-field solo v-model="info.key" label="ex) 제조국" outlined style="display: inline-block; width: 100%;"></v-text-field>
                </v-col>
                <v-col
                  cols="6"
                > 
                  <v-text-field solo v-model="info.value" label="ex) 대한민국" outlined style="display: inline-block; width: 100%;"></v-text-field>
                </v-col>
                <v-col
                  cols="12"
                  class="text-right"
                >
                  <a @click="addInfo"><v-icon>mdi-plus</v-icon></a>
                  <a @click="removeInfo(idx)"><v-icon>mdi-minus</v-icon></a>
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
  name: 'RegisterDetailInfo',
  data: () => ({
    detailInfo: [
      {
        key: null,
        value: null,
      }
    ]
  }),
  methods: {
    addInfo: function () {
      const newInfo = {
        key: null,
        value: null,
      }
      this.detailInfo.push(newInfo)
    },
    removeInfo: function (idx) {
      if (this.detailInfo.length > 1 ) this.detailInfo.splice(idx, 1)
    },
    resetInfo: function () {
      this.detailInfo = [
        {
          key: null,
          value: null,
        }
      ]
    }
  },
  computed: {
    stringInfo() {
      let str = '';
      for (let i=0; i < this.detailInfo.length; i++) {
        str += this.detailInfo[i].key
        str += '&^%'
        str += this.detailInfo[i].value
        if (i == this.detailInfo.length - 1) {
          break;
        }
        str += '&^%'
      }
      return str
    }
  },
  watch: {
    stringInfo() {
      this.$emit('detailInfo', this.stringInfo)
    }
  }
}
</script>

<style>

</style>