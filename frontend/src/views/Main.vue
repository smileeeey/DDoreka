<template>
  <div class="in-layout mx-auto" style="width: 100%">
    <TopCarousels />
    <!--완료-->
    <TodayItemsList />
    <!-- 완료 -->
    <HotItem />
    <!--완료-->
    <SteadySeller />
    <!--완료-->
    <CategoryRecommend />
    <beautiful-chat
      v-if="login"
      :participants="participants"
      :titleImageUrl="titleImageUrl"
      :onMessageWasSent="onMessageWasSent"
      :messageList="messageList"
      :newMessagesCount="newMessagesCount"
      :isOpen="isChatOpen"
      :close="closeChat"
      :icons="icons"
      :open="openChat"
      :showEmoji="false"
      :showFile="false"
      :showEdition="true"
      :showDeletion="true"
      :showTypingIndicator="showTypingIndicator"
      :showLauncher="true"
      :showCloseButton="true"
      :colors="colors"
      :alwaysScrollToBottom="alwaysScrollToBottom"
      :disableUserListToggle="false"
      :messageStyling="messageStyling"
      @onType="handleOnType"
      @edit="editMessage"
      ><template v-slot:header><div>🙋 Eureka의 상담원과 채팅중입니다.</div></template></beautiful-chat
    >
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex';

import TopCarousels from '@/components/main/TopCarousels.vue';
import TodayItemsList from '@/components/main/TodayItemsList.vue';
import HotItem from '@/components/main/HotItem.vue';
import SteadySeller from '@/components/main/SteadySeller.vue';
import CategoryRecommend from '@/components/main/CategoryRecommend.vue';
// @ is an alias to /src

export default {
  name: 'Main',
  components: {
    TopCarousels,
    TodayItemsList,
    HotItem,
    SteadySeller,
    CategoryRecommend,
  },
  created() {
    this.fetchMainInfo();
  },
  methods: {
    ...mapActions('mainStore', ['FETCH_MAIN_INFO']),
    fetchMainInfo() {
      this.FETCH_MAIN_INFO();
    },
  },
  computed: {
    ...mapState(['login', 'userId']),
  },
  data() {
    return {
      participants: [
        {
          id: 'user1',
          name: 'Eureka',
          imageUrl: 'browserIcon.ico',
        },
      ], // the list of all the participant of the conversation. `name` is the user name, `id` is used to establish the author of a message, `imageUrl` is supposed to be the user avatar.
      titleImageUrl: 'https://a.slack-edge.com/66f9/img/avatars-teams/ava_0001-34.png',
      messageList: [
        //{ type: 'text', author: `Eureka`, data: { text: `Say yes!` } },
      ], // the list of the messages to show, can be paginated and adjusted dynamically
      newMessagesCount: 0,
      isChatOpen: false, // to determine whether the chat window should be open or closed
      showTypingIndicator: '', // when set to a value matching the participant.id it shows the typing indicator for the specific user
      colors: {
        header: {
          bg: '#4e8cff',
          text: '#ffffff',
        },
        launcher: {
          bg: '#4e8cff',
        },
        messageList: {
          bg: '#ffffff',
        },
        sentMessage: {
          bg: '#4e8cff',
          text: '#ffffff',
        },
        receivedMessage: {
          bg: '#eaeaea',
          text: '#222222',
        },
        userInput: {
          bg: '#f4f7f9',
          text: '#565867',
        },
      }, // specifies the color scheme for the component
      alwaysScrollToBottom: false, // when set to true always scrolls the chat to the bottom when new events are in (new message, user starts typing...)
      messageStyling: true, // enables *bold* /emph/ _underline_ and such (more info at github.com/mattezza/msgdown)
    };
  },
};
</script>

<style scoped></style>
