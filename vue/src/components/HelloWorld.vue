<script setup>
import { ref } from 'vue'
import { fetchAllMessages, createMessage } from '../service/HelloWorldService'

defineProps({
  msg: String,
})

const count = ref(0)
const messages = ref([]) // Used to store the fetched messages
const errorMessage = ref('') // Used to store any error messages
const userInput = ref('') // Used to store user input

// Function to fetch the message from the server
const showAllMessages = async () => {
    try {
        messages.value = await fetchAllMessages();
    } catch (error) {
        errorMessage.value = `Error: ${error.response.status}`;
    }
}


const sendMessage = async () => {
  try {
    // Creates message in database
    await createMessage(userInput.value);

    // Clear the user input after successful submission
    userInput.value = '';

    // Fetch all messages again to update the list
    showAllMessages();
  } catch (error) {
    errorMessage.value = `Error: ${error.response.status}`;
  }
}

</script>

<template>
  <h1>{{ msg }}</h1>

  <div class="card">
    <button type="button" @click="showAllMessages">Fetch All Messages From Postgres</button>
    <ul v-if="messages.length">
      <li v-for="(message, index) in messages" :key="index">{{ message.message }}</li>
    </ul>
    <p v-if="errorMessage" class="error-message">{{ errorMessage }}</p>
  </div>
<div class="card">
    <input class= "input-field" type="text" v-model="userInput" placeholder="Write 'Hello ....!'" />
  <div>
    <button type="button" @click="sendMessage">Add to Database</button>
  </div>
  </div>
  
</template>

<style scoped>
.read-the-docs {
  color: #888;
}
</style>
