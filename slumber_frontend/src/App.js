import React from 'react';
import './App.css';

import { Provider } from 'react-redux';

// import AuthService from '../../services/auth/AuthService';
// import withAuth from '../authentication/withAuth';
// const Auth = new AuthService();

import { Container } from 'reactstrap';

import { BrowserRouter as Router, Route } from "react-router-dom";

import Main from './components/common/Main';
import Login from './components/authentication/Login';
import Logout from './components/authentication/Logout';
import Signup from './components/authentication/Signup';

const Root = ({ store }) => (

  <Provider store={store}>
    <Router>
      <div>
        <Container fluid >
          <div className="content">
            <Route path='/' component={Main} />
            <Route exact path="/login" component={Login} />
            <Route exact path="/signup" component={Signup} />
            <Route exact path="/logout" component={Logout} />
          </div>
        </Container>
      </div>
    </Router>
  </Provider>
)

export default Root;
