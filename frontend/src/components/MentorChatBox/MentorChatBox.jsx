import React, { Component } from 'react';
import { Chat, addResponseMessage} from 'react-chat-popup';



import logo from './logo.jpeg';

// import './ChatBox.css';
 
class  MentorChatBox extends Component {
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
        var stuTpc = ""
        if(this.props.topic == 0){
            stuTpc = "Enrolment"
        }else if(this.props.topic == 1){
            stuTpc = "Accommodation"
        }else if(this.props.topic == 2){
            stuTpc = "Society and Volunteering"
        }else if(this.props.topic == 3){
            stuTpc = "Study and Career plan"
        }
        var msg = `Hi awesome mentor, the topic you have chosen is "${stuTpc}"!`
        addResponseMessage(msg)
        addResponseMessage("Please wait for your lovely student!")
        console.log("heredfgushhb")
        console.log(this.props)
        this.initSocket()
    }

    componentWillUnmount(){
        let msg = {
            userInfo: false,
            mentorId: this.props.id,
            message: "close",
            type: this.props.topic,
            close: true,
        };
        var obj3 = JSON.stringify(msg);
        this.sendMessage(obj3);
        console.log("unmount")
    }

    initSocket(){
        var socket = new WebSocket("ws://localhost:8083/ws");
        //add socket.onmessage
        var mentorid = this.props.id
        var mentorTopic = this.props.topic
        socket.onopen = function(event){
            console.log("open");
            // this.sendMessage();

            let message = {
                userInfo:true,
                mentorId:mentorid,
                message:"testMessage",
                type:mentorTopic,
                close: false,
            };
            var obj1 = JSON.stringify(message);
            //in socket, this means socket??
            //this.sendMessage(obj1);
            socket.send(obj1);
        }

        socket.onmessage = (event) => {
            addResponseMessage(event.data);
            var reg = /student already leave/;
            if(reg.test(event.data) == true){
                this.props.endTheChat()
                //endTheChat
                console.log("hereeeeee yessss lalalallalaal")
            }
            // var ta = document.getElementById('responseText');
            // ta.value = ta.value + '\n' + event.data
        };


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
            userInfo: false,
            mentorId: this.props.id,
            message: newMessage,
            type: this.props.topic,
            close: false,
        };
        var obj2 = JSON.stringify(message);
        this.sendMessage(obj2);
        // addResponseMessage("Welcome to this awesome chat!");
    }
    
    render() {
        return (
            <div className="App">
                <Chat
                    handleNewUserMessage={this.handleNewUserMessage}
                    profileAvatar={logo} 
                    title="Student Hub!"
                    subtitle="And my cool subtitle"
                    fullScreenMode={true}
                    showCloseButton={true}
                />
            </div>
        );
    }
}
 
export default MentorChatBox;