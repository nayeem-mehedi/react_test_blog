import React, { Component } from 'react';
import './ReactionCounter.css'

class ReactionCounter extends Component {
    render() {
        return (
            <div style={{ width: "50%" }} className="text-center">
                <span className="react-count-love">♥</span>
                <small className="text-muted">{this.props.count}</small>
            </div>
        );
    }
}

export default ReactionCounter;