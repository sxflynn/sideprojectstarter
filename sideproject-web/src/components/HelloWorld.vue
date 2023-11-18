<script setup>
import { ref } from 'vue'

defineProps({
  msg: String,
})

const count = ref(0)
const messages = ref([]) // Used to store the fetched messages
const errorMessage = ref('') // Used to store any error messages
const userInput = ref('') // Used to store user input

// Function to fetch the message from the server
const fetchAllMessages = async () => {
    try {
        const response = await fetch('http://localhost:8080/all');
        if (!response.ok) {
            throw new Error(`Error: ${response.status}`);
        }
        const data = await response.json();
        messages.value = data;
    } catch (error) {
        errorMessage.value = error.message;
    }
}


const sendMessage = async () => {
  try {
    const helloWorldObj = {
      message: userInput.value,
    };

    const response = await fetch('http://localhost:8080/new', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(helloWorldObj), // Convert to JSON format
    });

    if (!response.ok) {
      throw new Error(`Error: ${response.status}`);
    }

    // Clear the user input after successful submission
    userInput.value = '';

    // Fetch all messages again to update the list
    fetchAllMessages();
  } catch (error) {
    errorMessage.value = error.message;
  }
}

</script>

<template>
  <h1>{{ msg }}</h1>

  <div class="card">
    <button type="button" @click="fetchAllMessages">Fetch All Messages From Postgres</button>
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
