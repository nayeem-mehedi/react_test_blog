import React, { Component } from 'react';
import { Card, CardBody, CardTitle, Form, FormGroup, Label, Col, Input, Button, Container } from 'reactstrap';
import AuthService from '../../services/auth/AuthService';
import { API_BASE_URL } from '../../constants/serverConstant';

import { EditorState, convertToRaw } from 'draft-js';
import { Editor } from 'react-draft-wysiwyg';
import draftToHtml from 'draftjs-to-html';
import 'react-draft-wysiwyg/dist/react-draft-wysiwyg.css';

var validate = require("validate.js");

class ArticlePublish extends Component {

    constructor() {
        super();

        this.state = {
            addAdmin: false,
            editorState: EditorState.createEmpty(),
        }
        this.Auth = new AuthService();
        this.handleChange = this.handleChange.bind(this);
        this.handleFormSubmit = this.handleFormSubmit.bind(this);
        this.onEditorStateChange = this.onEditorStateChange.bind(this);
    }

    componentWillMount() {
    }

    onEditorStateChange(editorState) {
        this.setState({
            editorState,
        });
    };


    handleChange(e) {
        this.setState(
            {
                [e.target.name]: e.target.value
            }
        )
    }

    handleFormSubmit(e) {
        e.preventDefault();
        var raw = convertToRaw(this.state.editorState.getCurrentContent());
        var value = draftToHtml(raw);

        if (!validate.isEmpty(this.state.title) && !validate.isEmpty(this.state.summary) && !validate.isEmpty(this.state.cover)) {
            var body = {
                title: this.state.title,
                summary: this.state.summary,
                coverImage: this.state.cover,
                body: value
            }
            this.Auth.fetch(`${API_BASE_URL}/articles`, {
                method: 'POST',
                body: JSON.stringify(body)
            }).then(res => {
                return Promise.resolve(res);
            }).then(this.props.history.replace('/homepage'));
        }
    }


    render() {
        return (
            <div style={{ paddingLeft: "10%", paddingRight: "10%" }}>
                <Card body className="text-center login-card">
                    <CardBody>
                        <br />
                        <CardTitle>
                            <h4>
                                <span>Publish a blog</span>
                            </h4>
                        </CardTitle>
                        <Form>
                            <FormGroup row>
                                <Label for="title" sm={3}>Title</Label>
                                <Col sm={9}>
                                    <Input type="text" name="title" id="title" onChange={this.handleChange} />
                                </Col>
                            </FormGroup>
                            <FormGroup row>
                                <Label for="summary" sm={3}>summary</Label>
                                <Col sm={9}>
                                    <Input type="textarea" name="summary" id="summary" value={this.state.summary} onChange={this.handleChange} />
                                </Col>
                            </FormGroup>
                            <FormGroup row>
                                <Label for="cover" sm={3}>Cover image</Label>
                                <Col sm={9}>
                                    <Input type="cover" name="cover" id="cover" onChange={this.handleChange} />
                                </Col>
                            </FormGroup>
                            <FormGroup row>
                                <Label for="summary" sm={3}>Body</Label>
                                <Col sm={9}>
                                    <Editor
                                        editorState={this.state.editorState}
                                        wrapperClassName="demo-wrapper"
                                        editorClassName="demo-editor"
                                        onEditorStateChange={this.onEditorStateChange}
                                    />
                                </Col>
                            </FormGroup>
                        </Form>
                        <Button type="submit" size="md" outline color="success" onClick={(e) => {
                            this.handleFormSubmit(e);
                        }}>Publish</Button>
                    </CardBody>
                </Card>
            </div>
        );
    }
}

export default ArticlePublish;