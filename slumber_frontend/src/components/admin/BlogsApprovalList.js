import React, { Component } from 'react';
import { Card, Button, Label, Media, Col, Row } from 'reactstrap';
import Moment from 'react-moment';

import AuthService from '../../services/auth/AuthService';
import { API_BASE_URL } from '../../constants/serverConstant';

class BlogsApprovalList extends Component {

    constructor() {
        super();
        this.state = {
            articles: []
        }
        this.Auth = new AuthService();
        this.showBlogsList = this.showBlogsList.bind(this);
        this.handleApprove = this.handleApprove.bind(this);
        this.handleDelete = this.handleDelete.bind(this);
        this.handlePending = this.handlePending.bind(this);
    }

    componentWillMount() {
        this.showBlogsList();
    }

    showBlogsList() {
        this.Auth.fetch(API_BASE_URL + '/manage/articles', {
            method: 'GET'
        }).then(res => {
            console.log(res);
            this.setState({
                articles: res.articles
            }, () => this.forceUpdate());
            return Promise.resolve(res);
        })
    }

    handleApprove(id) {
        var body = {
            id: id,
            approval_state: 2,
        }
        this.Auth.fetch(`${API_BASE_URL}/manage/articles`, {
            method: 'PUT',
            body: JSON.stringify(body)
        }).then(res => {
            console.log(res);
            return Promise.resolve(res);
        }).then(this.showBlogsList());
    }

    handleDelete(id) {
        this.Auth.fetch(`${API_BASE_URL}/manage/articles`+id, {
            method: 'DELETE'
        }).then(res => {
            console.log(res);
            return Promise.resolve(res);
        }).then(this.showBlogsList());
    }

    handlePending(id) {
        var body = {
            id: id,
            approval_state: 1,
        }
        this.Auth.fetch(`${API_BASE_URL}/manage/articles`, {
            method: 'PUT',
            body: JSON.stringify(body)
        }).then(res => {
            console.log(res);
            return Promise.resolve(res);
        }).then(this.showBlogsList());
    }

    render() {
        var blogsApprovalList = this.state.articles.map((article) => {
            return <div>
                <div className="blogger-card-wrapper">
                    <Card className="blogger-card">
                        <Row>
                            <Col sm={3} md={3}>
                                <img width="100%" style={{paddingTop:"15px",overflow:"auto"}} src={article.coverImage} alt="cover-image" />
                            </Col>
                            <Col sm={5} md={5}>
                                <Media>
                                    <Media body>
                                        <div className="blogger-details">
                                            <Row>
                                                <Label><span>Title:</span></Label>
                                                <Label>{article.title}</Label>
                                            </Row>
                                            <Row>
                                                <Label><span>Author:</span></Label>
                                                <Label>{article.user.username}</Label>
                                            </Row>
                                            <Row>
                                                <Label><span>Summary:</span></Label>
                                                <Label>{article.summary}</Label>
                                            </Row>
                                            <Row>
                                                <small className="text-muted">
                                                    <Label><span>Published:</span></Label>
                                                    <Moment format="DD/MM/YYYY HH:mm">{article.created}</Moment>
                                                </small>
                                            </Row>
                                        </div>
                                    </Media>
                                </Media>
                            </Col>
                            <Col sm={2} md={2}>
                                {article.approval_state === 1 ? (
                                    <Button size="sm" outline color="primary" className="blogger-button" onClick={() => this.handleApprove(article.id)}>
                                        Approve
                            </Button>
                                ) : (
                                        <Button size="sm" outline color="primary" className="blogger-button" onClick={() => this.handlePending(article.id)}>
                                            Pending
                                </Button>
                                )}

                            </Col>
                            <Col sm={2} md={2}>
                                    <Button size="sm" outline color="primary" className="blogger-button" onClick={() => this.handleDelete(article.id)}>
                                        Delete
                                </Button>
                            </Col>
                        </Row>
                    </Card>
                </div>
            </div>
        });

        return (
            <div style={{ width: "100%" }}>
                <h3>Blogs Maintanance</h3>
                {blogsApprovalList}
            </div>
        );


    }
}

export default BlogsApprovalList;