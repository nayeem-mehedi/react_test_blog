import React, { Component } from 'react';
import { Card, CardBody, CardTitle, CardText, Col, Input, Button, Row } from 'reactstrap';
import './ArticleCard.css';
import CommentCounter from '../comment/CommentCounter';
import ReactionCounter from '../reaction/ReactionCounter';

import { API_BASE_URL } from '../../constants/serverConstant';
import AuthService from '../../services/auth/AuthService';

import CommentSection from '../comment/CommentSection';

import '../comment/comment.css';

class ArticleDetails extends Component {

    constructor() {
        super();
        this.state = {
            article: "",
            commentBody: "",
            key: Math.random()
        }
        this.Auth = new AuthService();
        this.postComment = this.postComment.bind(this);
        this.handleInput = this.handleInput.bind(this);
    }

    handleInput(e) {
        this.setState(
            {
                [e.target.name]: e.target.value
            }
        )
    }

    postComment() {
        var body = {
            body: this.state.commentBody
        }

        this.Auth.fetch(API_BASE_URL + '/articles/' + this.props.match.params.id + '/comments', {
            method: 'POST',
            body: JSON.stringify(body)
        }).then(res => {
            this.setState({
                commentBody: "",
                key: Math.random()
            },()=>this.forceUpdate());
            return Promise.resolve(res);
        });
    }

    componentWillMount() {
        this.Auth.fetch(API_BASE_URL + '/articles/' + this.props.match.params.id, {
            method: 'GET'
        }).then(res => {
            console.log(res);
            this.setState({
                ...this.state,
                article: res.article
            });
            return Promise.resolve(res);
        })
    }

    render() {
        return (
            <div>
                <h3>
                    <span>{this.state.article.title}</span>
                </h3>
                <Card body className="text-center article-card">
                    <CardBody>
                        <img width="100%" src={this.state.article.coverImage} alt={this.state.article.title} />
                        <CardTitle>
                            <h5>
                                <span>{this.state.article.title}</span>
                            </h5>
                        </CardTitle>
                        <CardText className="text-secondary">
                            {this.state.article.summary}
                            <br/>
                            <br/>
                            <div dangerouslySetInnerHTML={{__html:this.state.article.body}} />;
                        </CardText>
                        <Row>
                            <ReactionCounter count={this.state.article.reactionsNumber} />
                            <CommentCounter count={this.state.article.commentsNumber} />
                        </Row>
                        {this.props.details ? "" : (
                            <Button size="sm" outline color="secondary">Details</Button>
                        )}

                    </CardBody>
                </Card>
                <CommentSection key={this.state.key} articleId={this.props.match.params.id} />
                <div className="comment-card-wrapper">
                    <Card className="comment-card">
                        <Row>
                            <Col sm={2} md={2}>
                            </Col>
                            <Col sm={7} md={8}>
                                <Input type="textarea" name="commentBody" id="commentBody" value={this.state.commentBody} onChange={(e) => this.handleInput(e)} />
                                <Button size="sm" outline color="primary" onClick={() => {
                                    this.postComment();
                                }}>Post</Button>
                            </Col>
                        </Row>
                    </Card>
                </div>
            </div>
        );
    }
}

export default ArticleDetails;