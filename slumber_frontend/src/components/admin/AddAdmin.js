import React, { Component } from 'react';
import { Card, CardBody, CardTitle, Form, FormGroup, Label, Col, Input, Button, Container } from 'reactstrap';
import { NavLink } from 'react-router-dom';
import AuthService from '../../services/auth/AuthService';
import { API_BASE_URL } from '../../constants/serverConstant';

var validate = require("validate.js");

class AddAdmin extends Component {

    constructor() {
        super();

        this.state = {
            addAdmin: false
        }
        this.Auth = new AuthService();
        this.handleChange = this.handleChange.bind(this);
        this.handleFormSubmit = this.handleFormSubmit.bind(this);
    }

    componentWillMount() {
        if (!this.Auth.loggedIn())
            this.props.history.replace('/homepage');

        console.log('HERE');
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
                role: 'ADMIN'
            }
            this.Auth.fetch(`${API_BASE_URL}/manage/admins`, {
                method: 'POST',
                body: JSON.stringify(body)
            }).then(res => {
                console.log(res);
                this.setState({
                    ...this.state,
                    addAdmin: true
                });
                return Promise.resolve(res);
            })
        }
    }


    render() {
        console.log('HERE');

        return (
            <div style={{ paddingLeft: "10%", paddingRight: "10%" }}>
                <Card body className="text-center login-card">
                    <CardBody>
                        <br />
                        {this.state.addAdmin ? (
                            <span>Admin created successfully.</span>

                        ) : (
                                <>
                                    <CardTitle>
                                        <h4>
                                            <span>Create Admin</span>
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
                                    }}>Add</Button>
                                </>
                            )}
                    </CardBody>
                </Card>
            </div>
        );
    }
}

export default AddAdmin;