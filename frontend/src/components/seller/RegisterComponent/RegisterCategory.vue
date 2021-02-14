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
                  <v-list-item-title v-text="item.text"></v-list-item-title>
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
                  <v-list-item-title v-text="item.text"></v-list-item-title>
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
                  <v-list-item-title v-text="item.text"></v-list-item-title>
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
    model: 1,
  }),
}
</script>

<style scoped>
  .border {
    border: 3px dashed red;
  }
</style>