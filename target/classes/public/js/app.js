
let socket;

let contactList,sendMessageForm,messagesContainer,messageInput;


let username;
let currentUserName;


window.addEventListener("DOMContentLoaded",function(){
	
	contactList = document.querySelector(".contact-list");
	sendMessageForm =  document.querySelector("form[name=sendMessage]");
	messagesContainer = document.querySelector(".messages");
	messageInput = document.querySelector("input[name=message]");
	
	socket = new WebSocket("ws://localhost:8080/chat");
	
	socket.onopen = onOpen
	socket.onmessage = onMessage
	socket.onclose = onClose
	socket.onError = onError
	
	
	contactList.addEventListener("click",function(event){
		
		var contactNode = event.target;
		
		username = contactNode.getAttribute("data-username");
		
		if(!messagesContainer.firstChild){
			messagesContainer.removeChild(messagesContainer.firstChild)
		}
		
		var url = "/messages/" + username
		
		fetch(url)
		.then((data) => data.json())
		.then((data) => {
			
			data.forEach(function(message){
				
				var messageNode = document.createElement("p");
				
				messageNode.textContent = message.fromUser + " : " +  message.content;
				
				messagesContainer.appendChild(messageNode);
				
			});
			
		})
		
	})
	
	sendMessageForm.addEventListener("submit",function(event){
		
		event.preventDefault();
		
		var message = messageInput.value;
		
		var timestamp = new Date().getTime();
		
		socket.send(JSON.stringify({content : message,
			toUser : username ,
			fromUser : currentUserName,
			timestamp : timestamp}))
			
		var messageNode = document.createElement("p");
		
		messageNode.textContent =  currentUserName + " : " +  message;
		
		messagesContainer.appendChild(messageNode);
		
		
	})
	
	
	fetch("/contacts")
	.then((data) => data.json())
	.then((data) => {
		
		data.forEach(function(contact){
			
			var contactNode = document.createElement("li");
			
			contactNode.textContent = contact.username;
			
			contactNode.setAttribute("data-username",contact.username);
			
			contactList.appendChild(contactNode);
			
		})
		
	})
	
	fetch("/username")
	.then((data) => data.text())
	.then((data) => {
		currentUserName = data;
	})
	
})






function onOpen(event){

    console.log("Socket Opened");

}

function onMessage (event){

    console.log(event.data);
    
    var message = JSON.parse(event.data);
    
    var messageNode = document.createElement("p");
	
	messageNode.textContent = message.fromUser + " : " +  message.content;
	
	messagesContainer.appendChild(messageNode);

}

function onClose (event){

	console.log("Socket Clossed");

}

function onError (event){

	console.log(event);

}



