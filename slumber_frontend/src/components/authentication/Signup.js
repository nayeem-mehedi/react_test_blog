import React, { Component } from 'react';
import { Card, CardBody, CardTitle, Form, FormGroup, Label, Col, Input, Button, Container } from 'reactstrap';
import { NavLink } from 'react-router-dom';
import AuthService from '../../services/auth/AuthService';
import { API_BASE_URL } from '../../constants/serverConstant';

var validate = require("validate.js");

class Signup extends Component {

    constructor() {
        super();

        this.state = {
            signupDone: false
        }
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
        if (!validate.isEmpty(this.state.username) && !validate.isEmpty(this.state.password) && !validate.isEmpty(this.state.name)) {
            var body = {
                username: this.state.username,
                password: this.state.password,
                name: this.state.name,
                role: 'BLOGGER'
            }
            this.Auth.fetch(`${API_BASE_URL}/signup`, {
                method: 'POST',
                body: JSON.stringify(body)
            }).then(res => {
                console.log(res);
                this.setState({
                    ...this.state,
                    signupDone: true
                });
                return Promise.resolve(res);
            })
        }
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
                            {this.state.signupDone ? (
                                <span>Signup successful. <span><NavLink to="/login">Login</NavLink></span> after sometime.</span>

                            ) : (
                                    <>
                                        <CardTitle>
                                            <h4>
                                                <span>Signup</span>
                                            </h4>
                                        </CardTitle>
                                        <Form>
                                            <FormGroup row>
                                                <Label for="name" sm={3}>Name</Label>
                                                <Col sm={9}>
                                                    <Input type="text" name="name" id="name" placeholder="Type Name" onChange={this.handleChange} />
                                                </Col>
                                            </FormGroup>
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
                                        </Form>
                                        <Button type="submit" size="md" outline color="success" onClick={(e) => {
                                            this.handleFormSubmit(e);
                                        }}>Signup</Button>
                                        <br />
                                        <br />
                                        <NavLink to="/login"><Button size="md" outline color="primary">Login</Button></NavLink>
                                    </>
                                )}
                        </CardBody>
                    </Card>
                </div>
            </>
        );
    }
}

export default Signup;