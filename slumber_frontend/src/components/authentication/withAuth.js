import React, { Component } from 'react';
import AuthService from '../../services/auth/AuthService';

export default function withAuth(AuthComponent) {
    const Auth = new AuthService('http://localhost:8080');
    return class AuthWrapped extends Component {
        constructor() {
            super();
        }

        componentWillMount() {
            if (!Auth.loggedIn()) {
                this.props.history.replace('/login');;
            }
        }

        render() {
            return (
                <AuthComponent history={this.props.history} />
            )
        }
    }
}
