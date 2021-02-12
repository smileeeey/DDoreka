<template>
  <v-container fluid>
    <v-row>
      <v-col
        cols="12"
        lg="4"
      >
        <v-card
          class="mt-4 mx-auto"
          max-width="400"
        >
          <v-sheet
            class="v-sheet--offset mx-auto"
            color="cyan"
            elevation="12"
            max-width="calc(100% - 32px)"
          >
            <v-sparkline
              :labels="labels"
              :value="value"
              color="white"
              line-width="2"
              padding="16"
            ></v-sparkline>
          </v-sheet>

          <v-card-text class="pt-0">
            <div class="title font-weight-light mb-2">
              User Registrations
            </div>
            <div class="subheading font-weight-light grey--text">
              Last Campaign Performance
            </div>
            <v-divider class="my-2"></v-divider>
            <v-icon
              class="mr-2"
              small
            >
              mdi-clock
            </v-icon>
            <span class="caption grey--text font-weight-light">last registration 26 minutes ago</span>
          </v-card-text>
        </v-card>

      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  name: 'Dashboard',
  data: () => ({
    labels: [
      'SUN', 'MON', 'TUE', 'WED', 'THU', 'FRI', 'SAT',
    ],
    value: [
      200, 675, 700, 410, 250, 430, 885,
    ]
  })
}
</script>

<style scoped>
  .v-sheet--offset {
    top: -24px;
    position: relative;
  }
</style>