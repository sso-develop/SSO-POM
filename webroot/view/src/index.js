import React from 'react';
import ReactDOM from 'react-dom';

import {
  HashRouter as Router,
  Route
} from 'react-router-dom';

import 'antd/dist/antd.css';
import './index.css';

import registerServiceWorker from './registerServiceWorker';
import App from './App';
import AppManager from './component/AppManager.jsx';
import Permission from './component/Permission.jsx';
import UserManager from './component/UserManager.jsx';
import AllotPermission from './component/AllotPermission.jsx';

const pageRoute = ( 
    <Router>
	    <App>
	        <Route exact path="/" component={UserManager} />
	        <Route path="/userManager" component={UserManager} />
	        <Route path="/appManager" component={AppManager} />
	        <Route path="/permission" component={Permission} />
	        <Route path="/allotPermission/:id" component={AllotPermission} />
	        
	    </App>
  </Router>
); 
ReactDOM.render(pageRoute, document.getElementById('root'));
registerServiceWorker();
