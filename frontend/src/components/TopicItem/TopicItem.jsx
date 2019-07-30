import React, { Component } from 'react';
import classname from 'classname';

import './TopicItem.css';

class TopicItem extends Component {

    constructor(props){
        super(props);
        this.state = {
            isChosen : props.isChosen,
            topic: props.topic,
            number: props.number,
            detail: props.detail,
            index: props.index,
            // key: props.key,
        };
        
    }

    componentWillReceiveProps(nextProps){
        console.log('here')
        console.log(nextProps)
        this.setState({
            isChosen : nextProps.isChosen,
        })
        console.log(nextProps.isChosen);
        console.log(this.state.isChosen);
        
    }
 
    

    render() {
        console.log('here render');
        const item = classname('topic-item', { 'topic-item--is-chosen': this.state.isChosen });
        return (
            // <button className={item} onClick={() => onSelect(isAutoplay)}>
            <button className={item} onClick={()=>this.props.chooseFunc(this.state.index)}>
                <span className="topic-item_text-area">
                    <span className="topic-item__topic">Topic: {this.state.topic}</span>
                    <span className="topic-item__detail">{this.state.number} mentor available</span>
                    <span className="topic-item__detail">{this.state.detail}</span>
                </span>
                {/* <span className="topic-item__picture"><Image src='../../asset/pictures/images.jpg' /></span> */}
            </button>
        )
    }
}

export default TopicItem;