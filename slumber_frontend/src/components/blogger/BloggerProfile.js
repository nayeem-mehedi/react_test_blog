import React, { Component } from 'react';
import Profile from '../blogger/Profile';
import ArticleList from '../article/ArticleList'

import { API_BASE_URL } from '../../constants/serverConstant';
import AuthService from '../../services/auth/AuthService';

import { connect } from "react-redux";

class BloggerProfile extends Component {

    constructor(props) {
        super(props);
        this.state = {
            profile: {},
            key1: Math.random(),
            key2: Math.random(),
        }
        this.Auth = new AuthService();
    }

    componentWillMount() {
        if (!this.props.self) {
            this.Auth.fetch(API_BASE_URL + '/bloggers/' + this.props.match.params.id, {
                method: 'GET'
            }).then(res => {
                console.log(res);
                this.setState({
                    profile: res.user,
                    key1: Math.random(),
                    key2: Math.random(),
                });
                return Promise.resolve(res);
            })
        }

    }

    render() {
        return (
            <div>
                <Profile key={this.state.key1} user={this.props.self ? this.props.common.loggedUser : this.state.profile} self={this.props.self} />
                <ArticleList key={this.state.key2} articles={this.props.self ? this.props.common.loggedUser.articles : this.state.profile.articles} />
            </div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        common: state.commonReducer
    };
};

export default connect(mapStateToProps)(BloggerProfile);