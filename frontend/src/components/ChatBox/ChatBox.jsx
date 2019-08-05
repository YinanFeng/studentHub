import React, { Component } from 'react';
import { Chat, addResponseMessage} from 'react-chat-popup';



import logo from './logo.jpeg';

import './ChatBox.css';
 
class ChatBox extends Component {
    // componentDidMount() {
    //     // addResponseMessage("Welcome to this awesome chat!");
    // }
     
    //   handleNewUserMessage = (newMessage) => {
    //     console.log(`New message incomig! ${newMessage}`);
    //     // Now send the message throught the backend API
    //   }

    constructor(props){
        super(props);
        this.state = {
            socket:null
        };
        this.handleNewUserMessage = this.handleNewUserMessage.bind(this);
        this.initSocket = this.initSocket.bind(this);
    }

    componentWillMount(){
        this.initSocket()
    }

    initSocket(){
        var socket = new WebSocket("ws://localhost:8081/ws");
        //add socket.onmessage

        socket.onopen = function(event){
            console.log("open");
            // this.sendMessage();
            let message = {
                userInfo:true,
                stuId:"dsdffe",
                message:"edrfd",
                type:"1"
            };
            var obj1 = JSON.stringify(message);
            //in socket, this means socket??
            //this.sendMessage(obj1);
            socket.send(obj1);
        }

        socket.onclose = function(event){
            console.log("close");
        }
        this.setState({
            socket:socket
        })
 
    }

    sendMessage(obj){
        this.state.socket.send(obj);
    }



    handleNewUserMessage(newMessage){
        console.log('here');
        console.log(newMessage);

        let message = {
            userInfo:false,
            stuId:"dsdffe",
            message:"edrfd",
            type:"1"
        };
        var obj2 = JSON.stringify(message);
        this.sendMessage(obj2);
        addResponseMessage("Welcome to this awesome chat!");
    }
    
    render() {
        return (
            <div className="App">
                <Chat
                    handleNewUserMessage={this.handleNewUserMessage}
                    profileAvatar={logo}
                    title="Student Hub!"
                    subtitle="And my cool subtitle"
                />
            </div>
        );
    }
}
 
export default ChatBox;