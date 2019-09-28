import React, { Component } from 'react';
import { Button, Card, Label, Media, Col, Row } from 'reactstrap';
//Gravater
import Avatar from 'react-avatar';
//For Member since
import 'moment-timezone';

import TimeAgo from 'timeago-react';

import './comment.css';

class CommentCard extends Component {
    render() {
        return (
            <div>
                <div className="comment-card-wrapper">
                    <Card className="comment-card">
                        <Row>
                            <Col sm={2} md={2}>
                                <div className="comment-avatar">
                                    <Media href={'/bloggers/' + this.props.comment.comment_user.id}>
                                        <Avatar name={this.props.comment.comment_user.name} size="64" />
                                    </Media>
                                </div>
                            </Col>
                            <Col sm={7} md={8}>
                                <Media>
                                    <Media body>
                                        <div className="comment-details">
                                            <Row>
                                                <Label><span>{this.props.comment.comment_user.name}</span></Label>
                                            </Row>
                                            <Row>
                                                <p>
                                                {this.props.comment.body}
                                                </p>
                                            </Row>
                                            <Row>
                                                <small className="text-muted">
                                                    <TimeAgo datetime={this.props.comment.created} />
                                                </small>
                                            </Row>
                                        </div>
                                    </Media>
                                </Media>
                            </Col>
                        </Row>
                    </Card>
                </div>
            </div>
        );
    }
}

export default CommentCard;