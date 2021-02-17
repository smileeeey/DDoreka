<template>
  <div class="in-layout mx-auto" style="width: 100%">
    <TopCarousels />
    <TodayItemsList />
    <HotItem />
    <SteadySeller />
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
      @edit="editMessage" ><template v-slot:header><div>🙋 Eureka의 상담원과 채팅중입니다.</div></template></beautiful-chat>
      
  </div>
</template>

<script>
import { mapState } from 'vuex'

import TopCarousels from '@/components/main/TopCarousels.vue'
import TodayItemsList from '@/components/main/TodayItemsList.vue'
import HotItem from '@/components/main/HotItem.vue'
import SteadySeller from '@/components/main/SteadySeller.vue'
import CategoryRecommend from '@/components/main/CategoryRecommend.vue'
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
  created(){
    let tmp = this.userId
    this.fetchMessages();
    let chk = true;
    db.collection('user').get().then(function(querySnapshot) {
      querySnapshot.forEach(function(doc){
        if(doc.data().name == tmp) {
          chk = false
        }
      })
    });
    console.log(chk)
    if(chk){
      db.collection('user').add({
        name: this.userId,
      })
    }       
  },
  methods:{
    deleteCollection() {
      db.collection(this.userId).orderBy('createdAt').onSnapshot((querySnapshot)=>{          
          querySnapshot.forEach(doc =>{
            db.collection(this.userId).doc(doc.id).delete()
          })          
      })
    },
    fetchMessages(){
      db.collection(this.userId).orderBy('createdAt').onSnapshot((querySnapshot)=>{
          let allMessages = [];
          querySnapshot.forEach(doc =>{
            allMessages.push(doc.data())
          })
          this.messageList = allMessages;
      })
    },
    sendMessage (text) {
      /*
      if (text.length > 0) {
        this.newMessagesCount = this.isChatOpen ? this.newMessagesCount : this.newMessagesCount + 1
        this.onMessageWasSent({ author: 'support', type: 'text', data: { text } })
      }
      */

    },
    onMessageWasSent (message) {
      // called when the user sends a message
      db.collection(this.userId).add({
        author:'me',
        createdAt: new Date(),
        type:'text',
        data:message.data
            
        })   
        
      //this.messageList = [ ...this.messageList, message ]
    },
    openChat () {
      // called when the user clicks on the fab button to open the chat
      this.isChatOpen = true
      this.newMessagesCount = 0
    },
    closeChat () {
      // called when the user clicks on the botton to close the chat
      this.isChatOpen = false
    },
    handleScrollToTop () {
      // called when the user scrolls message list to top
      // leverage pagination for loading another page of messages
    },
    handleOnType () {
      console.log('Emit typing event')
    },
    editMessage(message){
      const m = this.messageList.find(m=>m.id === message.id);
      m.isEdited = true;
      m.data.text = message.data.text;
    },
  },
  computed: {
    ...mapState([
      'login',
      'userId',
    ])
  },
  data() {
    return {
      participants: [
        {
          id: 'user1',
          name: 'Eureka',
          imageUrl: 'browserIcon.ico'
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
          bg: '#4e8cff'
        },
        messageList: {
          bg: '#ffffff'
        },
        sentMessage: {
          bg: '#4e8cff',
          text: '#ffffff'
        },
        receivedMessage: {
          bg: '#eaeaea',
          text: '#222222'
        },
        userInput: {
          bg: '#f4f7f9',
          text: '#565867'
        }
      }, // specifies the color scheme for the component
      alwaysScrollToBottom: false, // when set to true always scrolls the chat to the bottom when new events are in (new message, user starts typing...)
      messageStyling: true // enables *bold* /emph/ _underline_ and such (more info at github.com/mattezza/msgdown)
    }
  }
}
</script>

<style scoped>
  >>> .sc-chat-window {
    z-index: 100;
  }
</style>