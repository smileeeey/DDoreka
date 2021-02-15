<template>
  

    <v-navigation-drawer
      width="260"
      app
    >
      <v-divider class="mb-1" />
      <v-list dense nav>
        <v-list-item>
          <v-img src="@/assets/eureka_logo(blank).png"/>
        </v-list-item>
      </v-list>
      <v-divider class="mb-2" />
      <v-list dense>
        <template v-for="(item, idx) in items">
          <v-list-item
            v-if="!item.children"
            :key="idx"
            :to="item.to"
            @click="currentSelection = item.title"
            :class="currentSelection == item.title ? 'grey': ''"
          >
            <v-list-item-action>
              <v-icon>{{ item.icon }}</v-icon>
            </v-list-item-action>
            <v-list-item-content>
              <v-list-item-title>
                {{ item.title }}
              </v-list-item-title>
            </v-list-item-content>
          </v-list-item>
          <v-list-group
            v-if="item.children"
            :key="item.title"
            v-model="item.model"
            :prepend-icon="item.model ? item.icon : item['icon-alt']"
            append-icon=""
            
          >
            <template v-slot:activator>
              <v-list-item>
                <v-list-item-content>
                  <v-list-item-title>
                    {{ item.title }}
                  </v-list-item-title>
                </v-list-item-content>
              </v-list-item>
            </template>
            <v-list-item
              v-for="(child, i) in item.children"
              :key="i"
              @click="item.model = false;currentSelection = child.title"
              :to="child.to"
              :class="currentSelection == child.title ? 'grey': ''"
            >
              <v-list-item-action v-if="child.icon">
                <v-icon>{{ child.icon }}</v-icon>
              </v-list-item-action>
              <v-list-item-content>
                <v-list-item-title style="text-align: left; margin-left: 1.5rem">
                  {{ child.title }}
                </v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </v-list-group>
          
        </template>
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
        icon: 'mdi-bell',
        title: 'notifications',
        to: '/notification',
      },
      {
        icon: 'mdi-cart',
        title: 'delivery',
        to: '/product/deliver',
      },
      {
        icon: 'mdi-map-marker', 
        'icon-alt': 'mdi-bell',
        title: 'register',
        model: false,
        children: [
          { icon: 'mdi-minus', title: '등록된거', to: '/product/list' },
          { icon: 'mdi-minus', title: '등록할거', to: '/product/register' },
          { icon: 'mdi-minus', title: '수정삭제', to: '/user/profile' },
        ]
      },
      {
        icon: 'mdi-account',
        title: 'profile',
        to: '/user/profile',
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
    background-color: lightgoldenrodyellow !important;
  }

  .v-list-group > .v-list-group__header > .v-list-item {
    padding: 0;
  }

  >>> .v-list-item__title {
    margin-left: 0 !important;
  }
</style>