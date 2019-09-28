import React, { Component } from 'react';
import { Card, CardBody, CardTitle, Form, FormGroup, Label, Col, Input, Button } from 'reactstrap';
import { NavLink } from 'react-router-dom';

import { Redirect } from 'react-router-dom';
import AuthService from '../../services/auth/AuthService';

import { toast } from 'react-toastify';

import { connect } from "react-redux";
import {LOGIN_USER} from '../../actions/common-action';

class Login extends Component {

    constructor() {
        super();
        this.Auth = new AuthService();
        this.handleChange = this.handleChange.bind(this);
        this.handleFormSubmit = this.handleFormSubmit.bind(this);
    }

    componentWillMount() {
        if (this.Auth.loggedIn())
            this.props.history.replace('/homepage');
    }


    handleChange(e) {
        this.setState(
            {
                [e.target.name]: e.target.value
            }
        )
    }

    handleFormSubmit(e) {
        e.preventDefault();

        this.Auth.login(this.state.username, this.state.password)
            .then(res => {
                this.props.history.replace('/homepage');
                this.forceUpdate();
                this.props.loginUser(res.user);
            })
            .catch(err => {
                toast.error("Login failed. Try again");
            })
    }


    render() {
        return (
            <>
                <div style={{ paddingLeft: "30%", paddingRight: "30%" }}>
                    <Card body className="text-center login-card">
                        <CardBody>
                            <img width="30%" src={process.env.PUBLIC_URL + '/logo.png'} alt="Slumber!" />
                            <br />
                            <br />
                            <CardTitle>
                                <h4>
                                    <span>Login</span>
                                </h4>
                            </CardTitle>
                            <Form>
                                <FormGroup row>
                                    <Label for="username" sm={3}>Username</Label>
                                    <Col sm={9}>
                                        <Input type="text" name="username" id="username" placeholder="Type username" onChange={this.handleChange} />
                                    </Col>
                                </FormGroup>
                                <FormGroup row>
                                    <Label for="password" sm={3}>Password</Label>
                                    <Col sm={9}>
                                        <Input type="password" name="password" id="password" placeholder="Type password" onChange={this.handleChange} />
                                    </Col>
                                </FormGroup>
                                <Button type="submit" size="md" outline color="primary" onClick={(e) => {
                                    this.handleFormSubmit(e);
                                    return <Redirect to='/hompage' />
                                }
                                }>Login</Button>
                            </Form>
                            <br />
                            <NavLink to="/signup"><Button size="md" outline color="success">Signup</Button></NavLink>
                        </CardBody>
                    </Card>
                </div>
            </>
        );
    }
}

const mapStateToProps = (state) => {
    return {
      common: state.commonReducer
    };
  };
  
  const mapDispatchToProps = (dispatch) => {
    return {
      loginUser: (user) => {
        dispatch({
          type: LOGIN_USER,
          payload: {
            user
          }
        });
        return Promise.resolve();
      }
    };
};
  

export default connect(mapStateToProps, mapDispatchToProps)(Login);