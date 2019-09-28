import React, { Component } from 'react';
import { Button, Card, Label, Media, Col, Row } from 'reactstrap';
//Gravater
import Avatar from 'react-avatar';
//For Member since
import Moment from 'react-moment';
import 'moment-timezone';

import './Profile.css';

class Profile extends Component {
    render() {
        return (
            <div>
                <h3>Profile</h3>
                <div className="profile-card-wrapper">
                    <Card className="profile-card">
                        <Row>
                            <Col sm={2} md={2}>
                                <div className="profile-avatar">
                                    <Media href={'/bloggers/' + this.props.user.id}>
                                        <Avatar name={this.props.user.name} size="128" />
                                    </Media>
                                </div>
                            </Col>
                            <Col sm={7} md={8}>
                                <Media>
                                    <Media body>
                                        <div className="profile-details">
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
                            {this.props.self ? "" : (<Col sm={3} md={2}>
                                <Button size="sm" outline color="secondary" className="profile-button">Visit Profile</Button>
                            </Col>)}
                        </Row>
                    </Card>
                </div>
            </div>
        );
    }
}

export default Profile;