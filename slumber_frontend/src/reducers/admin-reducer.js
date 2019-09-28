import { ADD_ADMIN } from '../actions/Types';

export default function postReducer(state = [], action) {
  switch (action.type) {
    case ADD_ADMIN:
      return [...state, action.payload];
    default:
      return state;
  }
}