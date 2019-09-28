import React, { Component } from 'react';
import CommentCard from './CommentCard';

import {API_BASE_URL} from '../../constants/serverConstant';
import AuthService from '../../services/auth/AuthService';

class CommentSection extends Component {

    constructor() {
        super();
        this.state = {
            comments: []
        }
        this.Auth = new AuthService();
    }

    async componentWillMount(){
        await this.Auth.fetch(API_BASE_URL + '/articles/'+this.props.articleId+'/comments', {
            method: 'GET'
        }).then(res => {
            this.setState({
                ...this.state,
                comments: res.comments
            });
            return Promise.resolve(res);
        })
    }

    render() {
        console.log(this.state.comments);
        var commentList = this.state.comments.map((comment) => {
            if(comment !== undefined){
            return <div>
                <CommentCard comment={comment} />
            </div>
            }
        });

        return (
            <div style={{ width: "100%" }}>
                {commentList}
            </div>
        );
    }
}

export default CommentSection;