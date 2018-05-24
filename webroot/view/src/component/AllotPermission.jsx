import React, { Component } from 'react';
import { Form,message,Row,Col,Input,Transfer,Select } from 'antd';
import Enum from '../common/Enum.js';
import $ from 'jquery';
const FormItem = Form.Item;
class AllotPermission extends Component {
	constructor(props) {
		super(props);
		this.state = {
			userInfo:{
				id:props.match.params.id
			},
			targetKeys:[],
			mockData:[]
		}
		this.getUser(this);
		this.getPermission(this);
	}
	
	getUser(){
		let that = this;
		$.post(Enum.requestUrls.sysUser.queryUumsUserInfoByIdUrl, {id:this.state.userInfo.id},function(data) {
		  if(!data.success){
		  	message.error(data.msg)
		  }else{
		  	that.setState({userInfo:data.data})
		  }
		});
	}
	
	getPermission(){
		let that = this;
		//拥有权限
		$.post(Enum.requestUrls.sysPermission.queryUumsSysPermissionUrl, {userId:this.state.userInfo.id},function(data) {
		  if(!data.success){
		  	message.error(data.msg)
		  }else{
		 	 const d = data.data.map(function (item){
		  		return item.id
		 	 })
		  	that.setState({ targetKeys: d });
		  }
		});
		//所有权限
		$.post(Enum.requestUrls.sysPermission.queryUumsSysPermissionUrl, {},function(data) {
		  if(!data.success){
		  	message.error(data.msg)
		  }else{
			 const d = data.data.map(function (item){
		  		return {
		  			key: item.id,
				    title: item.name,
				    description: item.des,
				    disabled: false
		  		}
		  	});
		  	that.setState({ mockData: d });
		  }
		});
		
	}
	handleChange(nextTargetKeys, direction, moveKeys) {
		let permissionIds = '';
		let that = this;
		for(var i = 0 ;i < moveKeys.length;i++){
			if(i === moveKeys.length-1 ){
				permissionIds += moveKeys[i];
			}else{
				permissionIds += moveKeys[i]+',';
			}
		}
		if(direction === 'right'){
			$.post(Enum.requestUrls.sysUser.insertUumsUserPermissionRelationUrl, {permissionIds:permissionIds,userId:this.state.userInfo.id},function(data) {
			  if(!data.success){
			  	message.error(data.msg)
			  }else{
				 that.setState({ targetKeys: nextTargetKeys });
			  }
			});
		}else{
			$.post(Enum.requestUrls.sysUser.deleteUumsUserPermissionRelationUrl, {permissionIds:permissionIds,userId:this.state.userInfo.id},function(data) {
			  if(!data.success){
			  	message.error(data.msg)
			  }else{
				 that.setState({ targetKeys: nextTargetKeys });
			  }
			});
		}
	}
	
	render(){
		
		const mockData = [];
		for (let i = 0; i < 20; i++) {
		  mockData.push({
		    key: i.toString(),
		    title: 'content'+i,
		    description: 'description of content',
		    disabled: false
		  });
		}
		return(
			<div className='ant-advanced-search-form' >
			 <Form layout="inline">
				<Row>
					<Col span={6}>
			           <FormItem label='用户帐号'>
			             {this.state.userInfo.operatorName}
			          </FormItem>
			        </Col>
			        <Col span={6}>
			           <FormItem label='用户昵称'>
			             {this.state.userInfo.nickName}
			          </FormItem>
			        </Col>
			        <Col span={6}>
			           <FormItem label='用户姓名'>
			             {this.state.userInfo.realName}
			          </FormItem>
			        </Col>
			        <Col span={6}>
			           <FormItem label='用户姓名'>
			             {this.state.userInfo.mobile}
			          </FormItem>
			        </Col>
				</Row>
				<Row>
					<Col span={6}>
			           <FormItem label='用户工号'>
			             {this.state.userInfo.staffNo}
			          </FormItem>
			        </Col>
			        <Col span={6}>
			           <FormItem label='请选择系统'>
			             <Select
			             	style={{ width: 150 }}
			             >
						    <Select.Option value="jack">Jack</Select.Option>
						    <Select.Option value="lucy">Lucy</Select.Option>
						    <Select.Option value="tom">Tom</Select.Option>
						  </Select>
			          </FormItem>
			        </Col>
				</Row>
			</Form>
			<Transfer
				listStyle={{
		          width: 300,
		          height: 300,
		        }}
		        dataSource={this.state.mockData}
		        targetKeys={this.state.targetKeys}
		        titles={['所有权限', '已有权限']}
		        showSearch
		        render={item => item.title}
		        onChange={this.handleChange.bind(this)}
		        /*targetKeys = {targetKeys}*/
		      />
			</div>
			
		)
	}
}
export default Form.create()(AllotPermission);;