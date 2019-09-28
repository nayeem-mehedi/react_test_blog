import React from 'react';
import {render} from 'react-dom';

//Toaster
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

import 'bootstrap/dist/css/bootstrap.min.css';
import './index.css';
import App from './App';

import store from './store/store';

toast.configure({
    position: "top-center",
    autoClose: 3000,
    hideProgressBar: true,
    
    closeOnClick: true,
    pauseOnHover: false,
    draggable: false,
});

render(
    <App store={store}/>
    , document.getElementById('root'));
