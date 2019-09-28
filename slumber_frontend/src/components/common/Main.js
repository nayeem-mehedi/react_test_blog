import React, { Component } from 'react';
import { Route, Switch, withRouter } from "react-router-dom";


import withAuth from '../authentication/withAuth';
import { Container } from 'reactstrap';
import Header from './Header';
import Home from '../views/Home';
import Blogs from '../views/Blogs';
import Bloggers from '../views/Bloggers';
import BloggerProfile from '../blogger/BloggerProfile';

import AddAdmin from '../admin/AddAdmin';
import UserApprovalList from '../admin/UserApprovalList';
import BlogsApprovalList from '../admin/BlogsApprovalList';
import ArticlePublish from '../article/ArticlePublish';

import { connect } from "react-redux";

class Main extends Component {

    render() {
        return (
            <>
                <Header role={this.props.common.loggedUser.role} username={this.props.common.loggedUser.username} />

                <Switch>
                    <div style={{ paddingLeft: "15%", paddingRight: "15%" }}>
                        <Route exact path="/homepage" render={() => {
                            return <Home self={true} />
                        }} />
                        <Route path="/add-blog" component={ArticlePublish} />
                        <Route path="/blogs" component={Blogs} />
                        <Route path="/bloggers" component={Bloggers} />
                        <Route path="/profile/:id" render={(matchProps) => {
                            return <BloggerProfile self={false} {...matchProps} />
                        }} />
                        {/* Admin Routes */}
                        <Route path="/addadmin" component={AddAdmin} />
                        <Route path="/user-management" component={UserApprovalList} />
                        <Route path="/maintain-blog" component={BlogsApprovalList} />
                    </div>
                </Switch>

            </>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        common: state.commonReducer
    };
};

export default withRouter(withAuth(connect(mapStateToProps)(Main)));