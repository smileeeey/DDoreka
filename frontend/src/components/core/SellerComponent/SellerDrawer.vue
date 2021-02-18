<template>
  

    <v-navigation-drawer
      width="260"
      app
    >
      <v-divider class="mb-1" />
      <v-list dense nav>
        <v-list-item @click="gotoDashboard">
          <v-img src="@/assets/eureka_logo(blank).png" />
        </v-list-item>
      </v-list>
      <v-divider class="mb-2" />
      <v-list dense>
        <template v-for="(item, idx) in items">
          <v-list-item
            v-if="!item.children"
            :key="idx"
            :to="item.to"
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
              @click="item.model = false"
              :to="child.to"
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
        icon: 'mdi-bell',
        title: '주문알림',
        to: '/notification',
      },
      {
        icon: 'mdi-cart',
        title: '배송관리',
        to: '/product/deliver',
      },
      {
        icon: 'mdi-note', 
        'icon-alt': 'mdi-note',
        title: '상품관리',
        model: false,
        children: [
          { icon: 'mdi-minus', title: '판매제품', to: '/product/list' },
          { icon: 'mdi-minus', title: '판매등록', to: '/product/register' },
        ]
      },
      {
        icon: 'mdi-account',
        title: '프로필수정',
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
    },
    gotoDashboard() {
      this.$router.push({ name: 'Dashboard' })
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