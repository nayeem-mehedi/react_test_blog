import React, { Component } from 'react';
import BloggerList from '../blogger/BloggerList';

class Bloggers extends Component {
    render() {
        let bloggers_data = require('../../data/bloggers.json');
        console.log(bloggers_data);
        return (
            <div>
                <h3>Active Bloggers</h3>
                <BloggerList bloggers={bloggers_data.users}/>
            </div>
        );
    }
}

export default Bloggers;