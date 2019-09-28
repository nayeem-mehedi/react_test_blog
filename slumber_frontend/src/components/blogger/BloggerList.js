import React, { Component } from 'react';
import BloggerCard from '../blogger/BloggerCard';

class BloggerList extends Component {
    render() {

        var bloggerList = this.props.bloggers.map((blogger) => {
            return <div>
                <BloggerCard user={blogger} />
            </div>
        });

        return (
            <div style={{ width: "60%" }}>
                {bloggerList}
            </div>
        );
    }
}

export default BloggerList;