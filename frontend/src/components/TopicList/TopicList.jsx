import React, { Component } from 'react';
import TopicItem from '../TopicItem/TopicItem';

import './TopicList.css';

class TopicList extends Component {

    constructor(props){
        super(props);

        this.state = {
             listContent : topicListContent

        }
        this.chooseTopic = this.chooseTopic.bind(this);
    }

    chooseTopic(index){
        console.log('choose');
        console.log(index);
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
        //send to backend
    }

    render () {
         return (
             <div className="topic-list">
                <ul className="topic-list__ul">
                    { this.state.listContent.map(item => (
                    <li key={item.topic}>
                        <TopicItem
                            key={item.index}
                            index={item.index}
                            topic={item.topic}
                            number={item.number}
                            detail={item.detail}
                            chooseFunc={this.chooseTopic}
                            isChosen={item.isChosen}
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
    number:5,
    detail:'student can consult question about xxxxxxx xxxxxx here',
    isChosen:false
    
},
{
    index:1,
    topic:'Accommodation',
    number:5,
    detail:'student can consult question about xxxxxxx xxxxxx here',
    isChosen:false
},
{
    index:2,
    topic:'Society and Volunteering',
    number:5,
    detail:'student can consult question about xxxxxxx xxxxxx here',
    isChosen:false
},
{
    index:3,
    topic:'Study and career plan',
    number:5,
    detail:'student can consult question about xxxxxxx xxxxxx here',
    isChosen:false
}
];
