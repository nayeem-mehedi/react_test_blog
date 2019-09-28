import React, { Component } from 'react';
import AuthService from '../../services/auth/AuthService';

class Login extends Component {

    constructor() {
        super();
        this.Auth = new AuthService();
    }

    componentWillMount() {
        this.Auth.logout();
        window.location.replace('/login');
    }

    render() {
        return (
            <>
            </>
        );
    }
}

export default Login;