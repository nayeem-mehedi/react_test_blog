import {
    LOGIN_USER,
    LOGOUT_USER
} from '../actions/common-action';

const loginState = {
    loggedUser: ""
};

function commonReducer(state = loginState, action) {
    switch (action.type) {
        case LOGIN_USER:
            state = {
                ...state,
                loggedUser: action.payload.user
            };
            break;
        case LOGOUT_USER:
            state = {
                ...state,
                loggedUser: ""
            }
            break;
    }
    return state;
}

export default commonReducer;