import React, { Component } from 'react';
import classname from 'classname';


import './TopicItem.css';
import enrolment from './enrollment.png';
import accommodation from './accommodation.png';
import society from './society.png';
import career from './career.png';

class TopicItem extends Component {

    constructor(props){
        super(props);
        this.state = {
            isChosen: props.isChosen,
            topic: props.topic,
            number: props.number,
            detail: props.detail,
            index: props.index,
            pic:enrolment,
            endChat: props.endChat,
            profile: props.profile,
            // key: props.key,
        };
        
    }

    componentWillReceiveProps(nextProps){
        this.setState({
            isChosen : nextProps.isChosen,
            endChat: nextProps.endChat,
        })
        
    }
 
    componentWillMount(){
        if(this.props.index == 1){
            this.setState({
                pic: accommodation
            })
        }else if(this.props.index == 2){
            this.setState({
                pic: society
            })
        }else if(this.props.index == 3){
            this.setState({
                pic: career
            })
        }
    }

    render() {
        const number = this.state.number;
        console.log("in topic item")
        console.log(this.state.endChat)
        console.log((this.state.isChosen && !this.state.endChat))
        let item = classname('topic-item', { 'topic-item--is-chosen': (this.state.isChosen && !this.state.endChat) });
        
        console.log(item)
        return (
            // <button className={item} onClick={() => onSelect(isAutoplay)}>
 
            <div>
            
            {(this.state.number[this.state.index]==0 && (this.state.isChosen==false || this.state.endChat==true) && this.state.profile != "mentor")?
            <button disabled className="topic-disable-item">
                <span className="topic-item_text-area">
                    <span className="topic-item__topic">Topic: {this.state.topic}</span>
                    <span className="topic-item__detail">Sorry, no mentor available now. Please wait for available mentor. Thank you!</span>
                    <span className="topic-item__detail">{this.state.detail}</span>
                </span>
                <span className="topic-item__picture"><img className="topic-item__image" src={this.state.pic} /></span>
            </button>
            
            :

            <button className={item} onClick={()=>this.props.chooseFunc(this.state.index)}>
                <span className="topic-item_text-area">
                    <span className="topic-item__topic">Topic: {this.state.topic}</span>
                    <span className="topic-item__detail">{this.state.number[this.state.index]} mentor available</span>
                    <span className="topic-item__detail">{this.state.detail}</span>
                </span>
                <span className="topic-item__picture"><img className="topic-item__image" src={this.state.pic} /></span>
            </button>

            }
            </div>
        )
    }
}

export default TopicItem;