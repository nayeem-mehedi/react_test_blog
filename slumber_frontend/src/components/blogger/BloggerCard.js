import React, { Component } from 'react';
import { Card, Button, Label, Media, Col, Row } from 'reactstrap';
import { NavLink } from "react-router-dom";
//Gravater
import Avatar from 'react-avatar';
//For Member since
import Moment from 'react-moment';
import 'moment-timezone';

import './BloggerCard.css';

class BloggerCard extends Component {
    render() {
        return (
            <div className="blogger-card-wrapper">
                <Card className="blogger-card">
                    <Row>
                        <Col sm={2} md={2}>
                            <div className="blogger-avatar">
                                <Media href={'/bloggers/' + this.props.user.id}>
                                    <Avatar name={this.props.user.name} size="64" round />
                                </Media>
                            </div>
                        </Col>
                        <Col sm={7} md={8}>
                            <Media>
                                <Media body>
                                    <div className="blogger-details">
                                        <Row>
                                            <Label><span>Name:</span></Label>
                                            <Label>{this.props.user.name}</Label>
                                        </Row>
                                        <Row>
                                            <Label><span>Username:</span></Label>
                                            <Label>{this.props.user.username}</Label>
                                        </Row>
                                        <Row>
                                            <small className="text-muted">
                                                <Label><span>Member Since:</span></Label>
                                                <Moment format="DD/MM/YYYY">{this.props.user.created}</Moment>
                                            </small>
                                        </Row>
                                    </div>
                                </Media>
                            </Media>
                        </Col>
                        <Col sm={3} md={2}>
                            <NavLink to={{ pathname: 'profile/' + this.props.user.id }}>
                                <Button size="sm" outline color="primary" className="blogger-button">
                                    Visit Profile
                                </Button>
                            </NavLink>
                        </Col>
                    </Row>
                </Card>
            </div>
        );
    }
}

export default BloggerCard;