import React, { Component } from 'react';

import './Review.css';



class Review extends Component {
    render() {
        return (
            <div>
                <blockquote className="review__all">
                    <p className="review__text">" The mentor solves me lots of problem and give me many tips for uni which is very helpful. We keep in touch until now and become very good friend. "</p>
                    <footer className="review__reviewer">— Sarah, student</footer>
                </blockquote>
                <blockquote className="review__all">
                    <p className="review__text">" I really enjoy chatting and sharing my experience with student. "</p>
                    <footer className="review__reviewer">— Sarah, mentor at Student Hub</footer>
                </blockquote>
            </div>
        )
    }
}

export default Review;