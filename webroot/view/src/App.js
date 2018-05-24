import React, { Component } from 'react';
import {withRouter} from 'react-router-dom' ;
import { Layout } from 'antd';
import Side from "./component/layout/Side.jsx";
import Header from "./component/layout/Header.jsx";


const {Content } = Layout;

class App extends Component {
	constructor(props) {
    	super(props);
    	let menus = [{
			title: '用户管理',
            icon: 'user',
            to:'/userManager',
            showSub:true,
            subMenu:[{
            	title: '用户列表',
	            icon: 'lock',
	            to:'/userManager',
	            showSub:false,
	            subMenu:[{
	            	title: '用户权限',
		            icon: 'lock',
		            to:'/allotPermission'
		           }
	            ]
	           }
            ]
    	},{
			title: '应用管理',
            icon: 'android',
            to:'/appManager'
    	},{
    		title: '权限管理',
            icon: 'lock',
            to:'/permission/'
          /*  subMenu:[
            	{
            		title: '权限管理',
		            icon: 'lock',
		            to:'/permission',
            	},{
            		title: '权限',
		            icon: 'lock',
		            to:'/myPermission',
            	}
            ]*/
          
    	}]
    	
    	this.state = {
    		menus:menus,
    		path:this.props.location.pathname
    	}
	}
	componentWillReceiveProps(nextProps){
		this.setState({
    		path:nextProps.location.pathname
    	});
	}
	
  render() {
    return (
        <Layout style={{ minHeight: '100vh' }}>
		      <Side path = {this.state.path} menus ={this.state.menus}/>
		      <Layout>
		      <Header path = {this.state.path} menus ={this.state.menus}/>
		        <Content style={{ margin: '	16px' }}>
		            <div style={{padding:'5px','overflow':'inherit'}}>
		            	{this.props.children}
		            </div>
		        </Content>
		      </Layout>
		    </Layout>
    );
  }
}

export default withRouter(App);
