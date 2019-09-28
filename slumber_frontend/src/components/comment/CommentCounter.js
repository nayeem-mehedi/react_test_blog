import React, { Component } from 'react';

class CommentCounter extends Component {
    render() {
        return (
            <div style={{ width: "50%" }} className="text-center">
                <small className="text-muted">{this.props.count} Comments</small>
            </div>
        );
    }
}

export default CommentCounter;