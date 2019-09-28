import React, { Component } from 'react';
import { Card, CardBody, CardTitle, CardText, CardImg, Button, Row, Spinner } from 'reactstrap';
import { NavLink } from "react-router-dom";
import Lazyload from 'react-lazyload';

import './ArticleCard.css';
import CommentCounter from '../comment/CommentCounter';
import ReactionCounter from '../reaction/ReactionCounter';

class ArticleCard extends Component {

    render() {
        console.log(this.props);
        return (
            <div>
                <Card body className="text-center article-card">
                    <Lazyload offset={500} once placeholder={<Spinner width="100px" height="100px" type="grow" color="secondary" />}>
                        <img src={this.props.article.coverImage} alt={this.props.article.title} />
                    </Lazyload>
                    <CardBody>
                        <CardTitle>
                            <h5>
                                <span>{this.props.article.title}</span>
                            </h5>
                        </CardTitle>
                        <CardText className="text-secondary">
                            {this.props.article.summary}
                        </CardText>
                        <Row>
                            <ReactionCounter count={this.props.article.reactionsNumber} />
                            <CommentCounter count={this.props.article.commentsNumber} />
                        </Row>
                        <NavLink to={{ pathname: 'blogs/' + this.props.article.id }}>
                            <Button size="sm" outline color="secondary">
                                Details
                                </Button>
                        </NavLink>
                    </CardBody>
                </Card>
            </div>
        );
    }
}

export default ArticleCard;