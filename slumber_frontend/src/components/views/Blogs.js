import React, { Component } from 'react';
import ArticleList from '../article/ArticleList';
import ArticleDetails from '../article/ArticleDetails';

import { Route, Switch } from "react-router-dom";
import AuthService from '../../services/auth/AuthService';
import { API_BASE_URL } from '../../constants/serverConstant';

class Blogs extends Component {

    constructor() {
        super();
        this.state = {
            articles: ""
        }
        this.Auth = new AuthService();
    }

    componentWillMount() {
        this.Auth.fetch(API_BASE_URL + '/articles', {
            method: 'GET'
        }).then(res => {
            console.log(res);
            this.setState({
                ...this.state,
                articles: res.articles
            });
            return Promise.resolve(res);
        })
    }

    render() {
        let articles_data = this.state.articles;
        return (
            <div>
                <Switch>
                    <Route exact path="/blogs" render={(props) => {
                        return <ArticleList {...props} articles={articles_data} />
                    }} />
                    <Route path="/blogs/:id" render={(props) => {
                        return <ArticleDetails {...props} details={true} />
                    }} />
                </Switch>
            </div>
        );
    }
}

export default Blogs;