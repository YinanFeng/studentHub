import React, { Component } from 'react';
import './App.css';

import Review from '../Review/Review';
import TopicList from '../TopicList/TopicList';
// import ChatBox from '../ChatBox/ChatBox';
import Appbar from '../Appbar/Appbar';



class App extends Component {

    constructor(props){
        super(props);
        this.state = {
            profile:null
        };
        this.chooseProfile = this.chooseProfile.bind(this)
    }

    chooseProfile(pro){
        this.setState({
            profile:pro
        })
    }

    render(){
        return(
            <div>
                <Appbar chooseProfile={this.chooseProfile}/>
                {this.state.profile == null?<Review />:<TopicList />}
                {/* <Review /> */}
                {/* <h1 className='app-test'>test</h1> */}
                {/* <ChatBox /> */}
                {/* <ChatBox /> */}
            </div>
        )    
    }
}

export default App;