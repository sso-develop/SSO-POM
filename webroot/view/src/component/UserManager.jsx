import React, { Component } from 'react';
import {Link } from 'react-router-dom';
import {Table,message,Divider,Popconfirm,Form,Input,Button,Row,Col,Modal} from 'antd';
import Enum from '../common/Enum.js';
import $ from 'jquery';
const FormItem = Form.Item;
const RequestUrls = Enum.requestUrls
class UserManager extends Component {
	
	constructor(props) {
    	super(props);
    	this.state = {
    		visible:false,
    		formOp:'',
    		searchData:{
    			nickName:''
    		},
    		formData:{
    			id:0,
    			nickName:'',
    			realName:'',
    			operatorName:'',
    			staffNo:'',
    			mobile:''
    		},
    		pager:{ 
    			    dataSource:[],
					total:0,
					pageSize:0,
					loading:false
				}
    	}
    }
	componentWillMount() {
		this.loadListData(this);
	}
  loadListData(){
  	let that = this;
  	Object.assign(this.state.pager,{loading:true})
  	$.post(RequestUrls.sysUser.queryUumsUserInfoByPagerUrl,this.state.searchData,function(data){
  		if(data.success){
  			const pager = data.data
  			that.setState({
  				pager:{
  					dataSource:pager.result,
					total:pager.totalCount,
					pageSize:pager.pageSize,
					loading:false
				}
  			})
  		}else{
  			message.error(data.msg)
  		}
  	});
  }
  handleDelUser(record){
  	let that = this;
  	$.post(RequestUrls.sysUser.deleteUumsUserInfoByIdUrl,{id:record.id},function(res){
  		if(res.success){
  			that.loadListData(that);
  		}else{
  			message.error(res.msg)
  		}
  	});
  }
  addUser(){
  	let that = this;
  	$.post(RequestUrls.sysUser.insertUumsUserinfoUrl,this.state.formData,function(res){
  		if(res.success){
  			that.closeModal(that);
  			that.loadListData(that);
  		}else{
  			message.error(res.msg)
  		}
  	});
  }
  
  editUser(){
  		let that = this;
	  	$.post(RequestUrls.sysUser.updateUumsUserInfoByIdUrl,this.state.formData,function(res){
	  		if(res.success){
	  			that.closeModal(that);
	  			that.loadListData(that);
	  		}else{
	  			message.error(res.msg)
	  		}
	  	});
  }
  
  closeModal(){
  	 this.setState({
	 	visible:false,
	 	formData:{
			id:0,
			nickName:'',
			realName:'',
			operatorName:'',
			staffNo:'',
			mobile:''
		}
	 });
	 this.props.form.resetFields()
  }
   handleCancel(e){
	this.closeModal(this);
  }
  handleModalShow(op,res){
  	this.setState({
	 	visible:true,
	 	formOp:op
	 });
  	if(op==='edit'){
  	  this.setState({
	 	formData:res
	  });
  	}
  }
  
  handleModalSubmit(e){
  	e.preventDefault();
  	let that = this;
    this.props.form.validateFields((err, values) => {
      if (!err) {
      	Object.assign(this.state.formData,values)
      	if(this.state.formOp === 'edit'){
      		this.editUser(this);
      	}else{
      		that.addUser(this);
      	}
      }
    });
  }
  handleSearchChange(event){
  	const target = event.target;
  	const value = target.value;
  	const name = target.name;
  	const d = this.state.searchData;
  	d[name] = value
    this.setState({
    	searchData:d
    });
  }
   handleSearchSubmit (e){
  	e.preventDefault();
  	const data = this.state.searchData;
      data.pageNumber = 1;
      this.setState({
      	searchData:data
      })
  	this.loadListData(this);
  }
  render() {
	const columns = [{
	  title: '昵称',
	  dataIndex: 'nickName',
	  key: 'nickName',
	}, {
	  title: '姓名',
	  dataIndex: 'realName',
	  key: 'realName',
	},{
	  title: '帐号',
	  dataIndex: 'operatorName',
	  key: 'operatorName',
	}, {
	  title: '工号',
	  dataIndex: 'staffNo',
	  key: 'staffNo',
	},{
	  title: '手机号码',
	  dataIndex: 'mobile',
	  key: 'mobile',
	},{
	  title: '创建时间',
	  dataIndex: 'createDate',
	  key: 'createDate',
	},{
	  title: '操作',
	  key: 'action',
	  render: (text, record) => {
	  	let id = record.id;
	  	if(record.operatorName === 'admin'){
	  		return <span>
	  			<Link to={{pathname: '/allotPermission/'+id}}> 添加权限</Link>
	  		</span>
	  	}else{
	  		   return (
			  	<span>
			  		<Link to={{pathname: '/allotPermission/'+id}}> 添加权限</Link>
			  		<Divider type="vertical" />
			       <a onClick={this.handleModalShow.bind(this,'edit',record)}>编辑</a>
			       <Divider type="vertical" />
			       <Popconfirm title='是否要删除?' onConfirm = {this.handleDelUser.bind(this,record)} okText="确定" cancelText="取消">
				     <a>删除</a>
				   </Popconfirm>
			    </span>
			  )
	  	}
	  }
	 }];
  	
  	const formItemLayout = {
      labelCol: {
        xs: { span: 24 },
        sm: { span: 5 },
      },
      wrapperCol: {
        xs: { span: 24 },
        sm: { span: 16 },
      },
    };
    const tailFormItemLayout = {
      wrapperCol: {
        xs: {
          span: 24,
          offset: 0,
        },
        sm: {
          span: 16,
          offset: 8,
        },
      },
    };
    const that = this;
    const { getFieldDecorator } = this.props.form;
    return (
       <div>
       		<Form onSubmit={this.handleSearchSubmit.bind(this)}>
       		  <Row gutter={24}>
       		  	<Col span={12}>
       		 	 <FormItem
       		 	 {...formItemLayout}
       		 	 label='昵称'>
       		 	 	<Input name='nickName' onChange = {this.handleSearchChange.bind(this)} value = {this.state.searchData.nickName}/>
       		 	 </FormItem>
       		 	</Col>
       		 	<Col span={12} style={{ textAlign: 'right' }}>
       		 	 <Button type="primary" htmlType="submit">搜索</Button>
			     <Button style={{ marginLeft: 8 }} onClick={this.handleModalShow.bind(this,'add')}>新增</Button>
			    </Col>
       		 </Row>
       		</Form>
       		<Table 
       			bordered 
       			dataSource={this.state.pager.dataSource} 
       			columns={columns} 
       			loading = {this.state.pager.loading}
       			rowKey={record => record.id}
       			pagination={{
       				total:this.state.pager.total,
	       			pageSize:this.state.pager.pageSize,
	       			showTotal: function () {  //设置显示一共几条数据
				            return '共 ' + (that.state.pager.total) + ' 条数据'; 
				        }
       			}}
       		/>
       		 <Modal
	           title={(this.state.formOp==='add'?'添加':'编辑')+'用户'}
	          onCancel = {this.handleCancel.bind(this)}
	          footer={null}
	          visible={this.state.visible}
	        >
		        <div>
		        	<Form
		        		onSubmit={this.handleModalSubmit.bind(this)}
		        	>
				        <FormItem
					           {...formItemLayout}
					          label='用户昵称'
					    >
					       {getFieldDecorator('nickName', {
					       		initialValue:this.state.formData.nickName,
		                        rules: [{required: true, message: '用户昵称不能为空'}],
		                    })(
		                        <Input placeholder='请输入用户昵称'/>
		                    )}
					        
			          	</FormItem>
			          	<FormItem
					           {...formItemLayout}
					          label='用户姓名'
					    >
					       {getFieldDecorator('realName', {
					       		initialValue:this.state.formData.realName,
		                        rules: [{required: true, message: '用户姓名不能为空'}],
		                    })(
		                        <Input placeholder='请输入用户姓名'/>
		                    )}
			          	</FormItem>
			          	<FormItem
					           {...formItemLayout}
					          label='用户帐号'
					    >
					       {getFieldDecorator('operatorName', {
					       		initialValue:this.state.formData.operatorName,
		                        rules: [{required: true, message: '用户帐号不能为空'}],
		                    })(
		                        <Input placeholder='请输入用户帐号'/>
		                    )}
			          	</FormItem>
			          	<FormItem
					           {...formItemLayout}
					          label='用户工号'
					    >
					       {getFieldDecorator('staffNo', {
					       		initialValue:this.state.formData.staffNo,
		                        rules: [{required: true, message: '用户工号不能为空'}],
		                    })(
		                        <Input placeholder='请输入用户工号'/>
		                    )}
			          	</FormItem>
			          	<FormItem
					           {...formItemLayout}
					          label='手机号码'
					    >
					       {getFieldDecorator('mobile', {
					       		initialValue:this.state.formData.mobile,
		                        rules: [{required: true, message: '手机号码不能为空'}],
		                    })(
		                        <Input placeholder='请输入手机号码'/>
		                    )}
			          	</FormItem>
			          	 <FormItem {...tailFormItemLayout}>
				          <Button type="primary" htmlType="submit">确认</Button>
				          <Button style={{ marginLeft: 8 }} onClick = {this.handleCancel.bind(this)}>取消</Button>
				        </FormItem>
		        	</Form>
		        </div>
	        </Modal>
       </div>
    );
  }
}
const UserManagerForm = Form.create()(UserManager);
export default UserManagerForm;