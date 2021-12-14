importScripts('https://www.gstatic.com/Firebasejs/8.7.0/Firebase-app.js')
importScripts('https://www.gstatic.com/Firebasejs/8.7.0/Firebase-messaging.js')

// The object we pass as an argument is the same object we copied into the environment files
Firebase.initializeApp({
    apiKey: "AIzaSyCOnsNn2ybBJvRf9DU8qVG6xOuilQa53DM",
    authDomain: "todonotification-1.firebaseapp.com",
    projectId: "todonotification-1",
    storageBucket: "todonotification-1.appspot.com",
    messagingSenderId: "766900620214",
    appId: "1:766900620214:web:182105a28a6f9b55168f17",
    measurementId: "${config.measurementId}"
  })

const messaging = Firebase.messaging();