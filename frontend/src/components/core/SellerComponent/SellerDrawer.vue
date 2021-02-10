<template>
  <v-navigation-drawer
    app
    width="260"
  >
    <!-- <template>
      <v-img />
    </template> -->
    <v-divider class="mb-1" />

    <v-list
      dense
      nav
    >
      <v-list-item>
        <v-img src="@/assets/eureka_logo(blank).png"/>
      </v-list-item>
    </v-list>
    <v-divider class="mb-2" />
    <v-list
      expand
      nav
    >
      <v-list-item
        v-for="(item, idx) in items"
        :key="idx"
        :to="item.to"
        link
      >
        <v-list-item-icon><v-icon>{{item.icon}}</v-icon></v-list-item-icon>

        <v-list-item-content>
          <v-list-item-title>{{item.title}}</v-list-item-title>
        </v-list-item-content>
      </v-list-item>
      
    </v-list>
  </v-navigation-drawer>
</template>

<script>
export default {
  name: 'SellerDrawer',
  data: () => ({
    items: [
      {
        icon: 'mdi-view-dashboard',
        title: 'dashboard',
        to: '/sell',
      },
      {
        icon: 'mdi-account',
        title: 'profile',
        to: '/user/profile',
      },
      {
        icon: 'mdi-map-marker',
        title: 'register',
        to: '/product/register',
      },
      {
        icon: 'mdi-bell',
        title: 'notifications',
        to: '/notification',
      },
    ]
  }),
  computed: {
    computedItems () {
      return this.items.map(this.mapItem)
    }
  },

  methods: {
    mapItem (item) {
      return {
        ...item,
        children: item.children ? item.children.map(this.mapItem) : undefined,
        title: this.$t(item.title),
      }
    }
  }
}
</script>

<style scoped>
  >>> .v-list-item--active {
    background-color: pink !important;
  }
</style>