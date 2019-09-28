import React, { Component } from 'react';
import BloggerProfile from '../blogger/BloggerProfile';

class Home extends Component {
    render() {
        return (
            <BloggerProfile self={this.props.self} />
        );
    }
}

export default Home;