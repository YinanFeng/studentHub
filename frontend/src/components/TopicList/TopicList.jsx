import React, { Component } from 'react';
import TopicItem from '../TopicItem/TopicItem';

import './TopicList.css';

class TopicList extends Component {

    constructor(props){
        super(props);

        this.state = {
            listContent : topicListContent,
            socket: null,
            num: [0,0,0,0],
            endChat: props.endChat,
            profile: props.profile,
        }
        this.chooseTopic = this.chooseTopic.bind(this);
        this.initSocket = this.initSocket.bind(this);

    }

    componentWillReceiveProps(nextProps){
        this.setState({
            endChat: nextProps.endChat
        })
    }

    componentWillMount(){
        this.initSocket();
    }

    initSocket(){
        // var res = {};
        var socket = new WebSocket("ws://localhost:8085/ws");
        //add socket.onmessage
        var stuid = this.props.id
        var stuTopic = this.props.topic
        socket.onopen = function(event){
            console.log("open matchCenter");
            let message = {
                userInfo:true,
                stuId:stuid,
                message:"testMessage",
                type:stuTopic,
            };
            var obj1 = JSON.stringify(message);
            //in socket, this means socket??
            //this.sendMessage(obj1);
            socket.send(obj1);
        }

        // socket.onmessage = function(event) {
        //     console.log("here");
        //     console.log(event.data);
        //     console.log(this);
        // };
        socket.onmessage = (event) => {
            console.log("here");
            console.log(event.data);
            console.log(this);
            var newMess = JSON.parse(event.data)
            console.log(newMess['1']);

            var res = this.state.num;
            var num = ["0","1","2","3"];
            for(var i=0;i<4;i++){
                if(newMess[num[i]]!=null){
                    console.log("hereyessss")
                    res[i] = newMess[num[i]]
                }
            }

            this.setState({
                num:res
            })
            console.log(res);

        };

        socket.onclose = function(event){
            console.log("close");
        }
        this.setState({
            socket:socket
        })


    }



    chooseTopic(index){

        let lc = this.state.listContent;
        for(var i=0;i<lc.length;i++){
            lc[i].isChosen = false;
        }
        lc[index].isChosen = true;
        this.setState(
            {
                listContent:lc
            }
        )
        this.props.changeTopic(index);
        //send to backend
    }

    render () {
        console.log("in topic List!!!")
        console.log(this.state.profile)
        console.log("in topic List!!!!!!!!")
         return (
             <div className="topic-list">
                <ul className="topic-list__ul">
                    { this.state.listContent.map(item => (
                    <li key={item.topic}>
                        <TopicItem
                            key={item.index}
                            index={item.index}
                            topic={item.topic}
                            number={this.state.num}
                            detail={item.detail}
                            chooseFunc={this.chooseTopic}
                            isChosen={item.isChosen}
                            endChat={this.state.endChat}
                            profile={this.state.profile}
                    //   isAlbumList={isAlbumList}
                    //   isPlay={isPlay}
                    //   isSelected={item.trackId === activeTrackId}
                    //   onSelect={onSelect}
                    //   {...item}
                        />
                    </li>))}
                </ul>
            </div>
         )
    }



}

export default TopicList;


//change this to a func
const topicListContent = [
{
    index:0,
    topic:'Enrolment',
    number:0,
    detail:'Student can consult question about enrolment including how to use myUNSW and moodle.',
    isChosen:false
    
},
{
    index:1,
    topic:'Accommodation',
    number:5,
    detail:'Student can consult question about accommodation including how to choose and how to apply.',
    isChosen:false
},
{
    index:2,
    topic:'Society and Volunteering',
    number:5,
    detail:'As a new student in uni, it is important to know some information about society and volunteer.',
    isChosen:false
},
{
    index:3,
    topic:'Study and career plan',
    number:5,
    detail:'It is never to late to start plan your future study and career.',
    isChosen:false
}
];
