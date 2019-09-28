import React, { Component } from 'react';
import { Card, Button, Label, Media, Col, Row } from 'reactstrap';
import Avatar from 'react-avatar';
import Moment from 'react-moment';

import AuthService from '../../services/auth/AuthService';
import { API_BASE_URL } from '../../constants/serverConstant';

class UserApprovalList extends Component {

    constructor() {
        super();
        this.state = {
            bloggers: []
        }
        this.Auth = new AuthService();
        this.showBloggersList = this.showBloggersList.bind(this);
        this.handleApprove = this.handleApprove.bind(this);
        this.handleDelete = this.handleDelete.bind(this);
        this.handleRestore = this.handleRestore.bind(this);
    }

    componentWillMount() {
        this.showBloggersList();
    }

    showBloggersList() {
        this.Auth.fetch(API_BASE_URL + '/manage/bloggers', {
            method: 'GET'
        }).then(res => {
            console.log(res);
            this.setState({
                bloggers: res.users
            }, () => this.forceUpdate());
            return Promise.resolve(res);
        })
    }

    handleApprove(id) {
        var body = {
            id: id,
            status: 2,
        }
        this.Auth.fetch(`${API_BASE_URL}/manage/bloggers`, {
            method: 'PUT',
            body: JSON.stringify(body)
        }).then(res => {
            console.log(res);
            return Promise.resolve(res);
        }).then(this.showBloggersList());
    }

    handleDelete(id) {
        var body = {
            id: id,
            status: 3,
        }
        this.Auth.fetch(`${API_BASE_URL}/manage/bloggers`, {
            method: 'PUT',
            body: JSON.stringify(body)
        }).then(res => {
            console.log(res);
            return Promise.resolve(res);
        }).then(this.showBloggersList());
    }

    handleRestore(id) {
        var body = {
            id: id,
            status: 1,
        }
        this.Auth.fetch(`${API_BASE_URL}/manage/bloggers`, {
            method: 'PUT',
            body: JSON.stringify(body)
        }).then(res => {
            console.log(res);
            return Promise.resolve(res);
        }).then(this.showBloggersList());
    }

    render() {
        var userApprovalList = this.state.bloggers.map((blogger) => {
            return <div>
                <div className="blogger-card-wrapper">
                    <Card className="blogger-card">
                        <Row>
                            <Col sm={2} md={2}>a
                                <div className="blogger-avatar">
                                    <Media>
                                        <Avatar name={blogger.name} size="64" round />
                                    </Media>
                                </div>
                            </Col>
                            <Col sm={5} md={6}>
                                <Media>
                                    <Media body>
                                        <div className="blogger-details">
                                            <Row>
                                                <Label><span>Name:</span></Label>
                                                <Label>{blogger.name}</Label>
                                            </Row>
                                            <Row>
                                                <Label><span>Username:</span></Label>
                                                <Label>{blogger.username}</Label>
                                            </Row>
                                            <Row>
                                                <small className="text-muted">
                                                    <Label><span>Member Since:</span></Label>
                                                    <Moment format="DD/MM/YYYY">{blogger.created}</Moment>
                                                </small>
                                            </Row>
                                        </div>
                                    </Media>
                                </Media>
                            </Col>
                            <Col sm={2} md={2}>
                                {blogger.status == 1 ? (
                                    <Button size="sm" outline color="primary" className="blogger-button" onClick={() => this.handleApprove(blogger.id)}>
                                        Approve
                            </Button>
                                ) : ""}

                            </Col>
                            <Col sm={2} md={2}>
                                {blogger.status != 3 ? (
                                    <Button size="sm" outline color="primary" className="blogger-button" onClick={() => this.handleDelete(blogger.id)}>
                                        Delete
                                </Button>
                                ) : (
                                        <Button size="sm" outline color="primary" className="blogger-button" onClick={() => this.handleRestore(blogger.id)}>
                                            Restore
                                </Button>
                                    )}
                            </Col>
                        </Row>
                    </Card>
                </div>
            </div>
        });

        return (
            <div style={{ width: "60%" }}>
                <h3>User Management</h3>
                {userApprovalList}
            </div>
        );


    }
}

export default UserApprovalList;