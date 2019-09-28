import { createStore, combineReducers, applyMiddleware } from 'redux';
import logger from 'redux-logger';
import thunk from 'redux-thunk';
import commonReducer from '../reducers/common-reducer';

const store = createStore(combineReducers({ commonReducer }), {}, applyMiddleware(logger, thunk));

export default store;