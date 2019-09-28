import React, { Component } from 'react';
import Headroom from 'react-headroom';
import { NavLink } from "react-router-dom";
import AuthService from '../../services/auth/AuthService.js';

const Auth = new AuthService();

class Header extends Component {

    render() {
        let navItems = "";

        if (this.props.role == 'BLOGGER') {
            navItems = (
                <>
                    <li><NavLink to="/homepage">Home</NavLink></li>
                    <li><NavLink to="/add-blog">Publish Blog</NavLink></li>
                    <li><NavLink to="/blogs">Blogs</NavLink></li>
                    <li><NavLink to="/bloggers">Bloggers</NavLink></li>
                </>
            );
        } else if (this.props.role == 'ADMIN') {
            navItems = (
                <>
                    <li><NavLink to="/homepage">Home</NavLink></li>
                    <li><NavLink to="/addadmin">Create Admin</NavLink></li>
                    <li><NavLink to="/user-management">User Management</NavLink></li>
                    <li><NavLink to="/maintain-blog">Blogs Maintanance</NavLink></li>
                </>
            );
        } else {
            navItems = (<></>);
        }


        return (
            <Headroom>
                <ul className="header" style={{ display: "flex", flexDirection: "row", justifyContent: "space-between" }}>
                    <div>
                        <li className="company-logo"><img src={process.env.PUBLIC_URL + '/logo.png'} alt="Slumber!" /></li>
                        {navItems}
                    </div>
                    <div className="nav-last">
                        {Auth.loggedIn() ? (
                            <>
                                <span>Welcome, {this, this.props.username}</span>
                                <li><NavLink to="/logout">Logout</NavLink></li>
                            </>
                        ) : ""}
                    </div>
                </ul>
            </Headroom>
        );
    }
}

export default Header;