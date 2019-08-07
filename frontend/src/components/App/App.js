import React, { Component } from 'react';
import './App.css';

import Review from '../Review/Review';
import TopicList from '../TopicList/TopicList';
// import ChatBox from '../ChatBox/ChatBox';
import Appbar from '../Appbar/Appbar';
import ChatBox from '../ChatBox/ChatBox';
import { runInThisContext } from 'vm';



class App extends Component {

    constructor(props){
        super(props);
        this.state = {
            profile:null,
            topic:null,
            id:null,
            update:null

        };
        this.chooseProfile = this.chooseProfile.bind(this)
        this.changeTopic = this.changeTopic.bind(this)
        this.updateChat = this.updateChat.bind(this)
    }

    chooseProfile(pro){
        this.setState({
            profile:pro
        })
        console.log("ttthereeeeeeeeeee111")
    }

    changeTopic(tpc){
        const uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
            var r = Math.random()*16|0, v = c == 'x' ? r : (r&0x3|0x8);
            return v.toString(16);
        });

        this.setState({
            id:uuid,
            topic:tpc,
          }, function() {
            this.updateChat()
          });

        console.log("hereeeeeeeeeee222")

    }

    updateChat(){
        var ud = new Date();
        this.setState({
            update:ud
        })
        console.log("hereeeeeeeeeee333")
    }

    render(){
        console.log(this.state)
        return(
            <div>
                <Appbar chooseProfile={this.chooseProfile}/>
                {this.state.profile == null?<Review />:<TopicList changeTopic={this.changeTopic}/>}
                {(this.state.topic == null || this.state.id == null) ? 
                    null:<ChatBox topic={this.state.topic} id={this.state.id} />}
                {/* <Review /> */}
                {/* <h1 className='app-test'>test</h1> */}
                {/* <ChatBox /> */}
                {/* <ChatBox /> */}
            </div>
        )    
    }
}

export default App;