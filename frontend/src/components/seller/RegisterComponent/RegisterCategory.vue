<template>
  <v-container fluid>
    <v-row>
      <v-col
        cols="12"
        md="4"
      >
        <v-card
          class="mx-auto"
          max-width="400"
        >
          <v-list>
            <!-- <v-list-title>대분류</v-list-title> -->
            <v-list-item-group
              v-model="model"
              active-class="border"
              color="indigo"
            >
              <v-list-item
                v-for="(item, i) in items"
                :key="i"
              >
                <v-list-item-icon>
                  <v-icon v-text="item.icon"></v-icon>
                </v-list-item-icon>

                <v-list-item-content>
                  <v-list-item-title v-text="item.name"></v-list-item-title>
                </v-list-item-content>
              </v-list-item>
            </v-list-item-group>
          </v-list>
        </v-card>
      </v-col>
      <v-col
        cols="12"
        md="4"
      >
        <v-card
          class="mx-auto"
          max-width="400"
        >
          <v-list>
            <!-- <v-list-title>대분류</v-list-title> -->
            <v-list-item-group
              v-model="submodel"
              active-class="border"
              color="indigo"
            >
              <v-list-item
                v-for="(item, i) in items[model].subCategory"
                :key="i"
              >
                <v-list-item-icon>
                  <v-icon v-text="item.icon"></v-icon>
                </v-list-item-icon>

                <v-list-item-content>
                  <v-list-item-title v-text="item.name"></v-list-item-title>
                </v-list-item-content>
              </v-list-item>
            </v-list-item-group>
          </v-list>
        </v-card>
      </v-col>
      <v-col
        cols="12"
        md="4"
      >
        <v-card
          class="mx-auto"
          max-width="400"
        >
          <v-list>
            <!-- <v-list-title>대분류</v-list-title> -->
            <v-list-item-group
              v-model="detailmodel"
              active-class="border"
              color="indigo"
            >
              <v-list-item
                v-for="(item, i) in items[model].subCategory[submodel].subCategory"
                :key="i"
              >
                <v-list-item-icon>
                  <v-icon v-text="item.icon"></v-icon>
                </v-list-item-icon>

                <v-list-item-content>
                  <v-list-item-title v-text="item.name"></v-list-item-title>
                </v-list-item-content>
              </v-list-item>
            </v-list-item-group>
          </v-list>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import axios from 'axios'
export default {
  name: 'RegisterCategory',
  data: () => ({
    items: [
      {
        icon: 'mdi-wifi',
        text: 'Wifi',
      },
      {
        icon: 'mdi-bluetooth',
        text: 'Bluetooth',
      },
      {
        icon: 'mdi-chart-donut',
        text: 'Data Usage',
      },
    ],
    model: 0,
    submodel : 0,
    detailmodel: 0,
  }),
  created: function () {
    axios.get(`http://i4d106.p.ssafy.io:8081/category/mainCategory`)
      .then(res => {
        this.items = res.data.data
      })
  },
  watch: {
    model: function () {
      this.$emit('maincode', this.modelCode)
      this.$emit('subcode', this.submodelCode)
      this.$emit('detailcode', this.detailmodelCode)
    },

    submodel: function () {
      this.$emit('maincode', this.modelCode)
      this.$emit('subcode', this.submodelCode)
      this.$emit('detailcode', this.detailmodelCode)
    },

    detailmodel: function () {
      this.$emit('maincode', this.modelCode)
      this.$emit('subcode', this.submodelCode)
      this.$emit('detailcode', this.detailmodelCode)
    },
  },
  computed: {
    modelCode() {
       return this.items[this.model].id
    },
    submodelCode() {
      return this.items[this.model].subCategory[this.submodel].id
    },
    detailmodelCode() {
      return this.items[this.model].subCategory[this.submodel].subCategory[this.detailmodel].id
    }
  }
}
</script>

<style scoped>
  .border {
    border: 2px dashed red;
  }
</style>