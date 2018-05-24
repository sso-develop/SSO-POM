import React, { Component } from 'react';
import { Table,Divider,Form,Input,Select,Modal,Button,Row,Col,message,Popconfirm} from 'antd';
import $ from 'jquery';
import Enum from '../common/Enum.js';
const FormItem = Form.Item;
const Option = Select.Option;
const RequestUrls = Enum.requestUrls

class Permission extends Component {
 constructor(props) {
    	super(props);
    	this.state = {
    			formOp:'',
    			visible:false,
				dataSource:[],
				allApp:[],
				searchData:{
					appId:'',
					name:'',
					code:''
				},
				formData:{
    				id:0,
    				name:'',
    				code:'',
    				appId:'',
    				isEnable:'1',
    				level:'L',
				}
			}
	}
	
	
	componentWillMount() {
		this.loadListData(this);
		this.queryAllApp(this);
		
	}
	queryAllApp(){
		let that = this;
		$.post(RequestUrls.sysApp.queryAllUumsSysAppUrl, {},function(data) {
		  if(data.success){
		  	that.setState({
		  		allApp:data.data
		  	});
		  }
		});
	}
	
	delPermission(record){
		let that = this;
		$.post(RequestUrls.sysPermission.deleteUumsSysPermissionByIdUrl, {id:record.id},function(data) {
		  if(data.success){
		  	that.loadListData(that);
		  }else{
		  	message.error(data.msg)
		  }
		});
	}
	loadListData(){
		let that = this;
		 $.post(RequestUrls.sysPermission.queryUumsSysPermissionByPagerUrl, this.state.searchData,function(data) {
		   that.setState({
		      	dataSource: data.data.result,
		    });
		});
	}
	
	
	closeModal(){
	 	this.setState({
	 		visible:false,
	 		formData:{
    				id:0,
    				name:'',
    				code:'',
    				appId:'',
    				isEnable:'1',
    				level:'L',
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
		
		let d = res;
		d.isEnable = res.isEnable?'1':'0';
  	  this.setState({
	 	formData:d
	  });
  	}
  }
   handleModalSubmit(e){
  	e.preventDefault();
    this.props.form.validateFields((err, values) => {
      if (!err) {
      	let that = this;
      	var params = values;
        params.id=that.state.formData.id;
      	$.post(this.state.formOp==='add'?RequestUrls.sysPermission.insertUumsSysPermissionUrl:RequestUrls.sysPermission.updateUumsSysPermissionByIdUrl, params,function(data) {
		  if(!data.success){
		  	message.error(data.msg)
		  }else{
		  	that.closeModal(that);
		  	that.loadListData(that);
		  }
		});
      	
      	console.log(values)
      }
    });
  }
   
  handleSearchSubmit(e){
  	e.preventDefault();
  	this.loadListData(this);
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
    console.log(this.state.searchData)
  }
 
 handleAppIdChange(currency){
   	Object.assign(this.state.searchData,{appId:currency})
 }
 
  render() {
  	const dataSource = this.state.dataSource;
		const columns = [{
			  title: '权限名称',
			  dataIndex: 'name',
			  key: 'name',
		}, {
			  title: '权限编码',
			  dataIndex: 'code',
			  key: 'code',
			},{
			  title: '应用名称',
			  dataIndex: 'appName',
			  key: 'appName',
			   render: (text, record) => (
			   	
			   	text+'/'+record.appCode
			   )
			}, {
			  title: '是否启用',
			  dataIndex: 'isEnable',
			  key: 'isEnable',
			  render:(text,record) =>(
			  	text?'是':'否'
			  )
			},{
			  title: '风险等级',
			  dataIndex: 'level',
			  key: 'level',
			  render: (text, record) =>{
			  	if(text === 'H'){
			  		return '高风险'
			  	}else if(text === 'M'){
			  		return '中风险'
			  	}else if(text === 'L'){
			  		return '低风险'
			  	}
			  }
			},{
			  title: '创建时间',
			  dataIndex: 'createDate',
			  key: 'createDate',
			},{
				  title: '操作',
				  key: 'action',
				  render: (text, record) => {
				  	if(record.appCode === 'SSO_LOGIN' && record.code === 'SSO_LOGIN_PERMISSION'){
				  		return <span>--</span>
				  	}else{
				  		return (
						  	<span>
						       <a onClick={this.handleModalShow.bind(this,'edit',record)}>编辑</a>
						       <Divider type="vertical" />
						        <Popconfirm title='是否要删除?' okText="确定"  onConfirm={this.delPermission.bind(this,record)} cancelText="取消">
							     <a>删除</a>
							   </Popconfirm>
						    </span>
						  )
				  	}
				  	
				  	
				  }
			}
		];
  	
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
   const { getFieldDecorator } = this.props.form;
    return (
    	<div>
    		 <Form
    		 onSubmit={this.handleSearchSubmit.bind(this)}
    		 >
		        <Row gutter={24}>
		        	<Col span={8}>
			          <FormItem
			          	 	  {...formItemLayout}
		          			label="应用"
			          	 >
			          	<Select
			          		onChange={this.handleAppIdChange.bind(this)}
			          	>
			          	{
			          		this.state.allApp.map(function (app, i) {
		                        return (<Option key={app.id} value={app.id}>{app.name}/{app.appCode}</Option>)
		                   	})
			          	}
				        </Select>
			          </FormItem>
			        </Col>
			        <Col span={8}>
			          <FormItem 
				          {...formItemLayout}
				          label={'权限编码'}
			          >
			            <Input name='code' onChange={this.handleSearchChange.bind(this)} value = {this.state.searchData.code}/>
			          </FormItem>
			        </Col>
			        <Col span={8} style={{ textAlign: 'right' }}>
			            <Button type="primary" htmlType="submit">搜索</Button>
			            <Button style={{ marginLeft: 8 }} onClick={this.handleModalShow.bind(this,'add')}>新增</Button>
		          	</Col>
		        </Row>
		      </Form>
	       	<Table 
	       		bordered
	       		dataSource={dataSource} 
	       		columns={columns} 
	       		rowKey={record => record.id}
	       		/>
	       		
	       	<Modal
	          title={(this.state.formOp==='add'?'新增':'编辑')+'权限'}
	          visible={this.state.visible}
	          footer={null}
	          onCancel={this.handleCancel.bind(this)}
	        >
	         <div>
	         	<Form
	         		onSubmit={this.handleModalSubmit.bind(this)}
	         	>
		         	<FormItem
				           {...formItemLayout}
				          label="权限名称"
				     >
				       {getFieldDecorator('name', {
				       		initialValue:this.state.formData.name,
	                        rules: [{required: true, message: '权限名称不能为空'}],
	                    })(
	                        <Input placeholder='请输入权限名称'/>
	                    )}
				        
		          	 </FormItem>
		          	 <FormItem
				           {...formItemLayout}
				          label="权限编码"
				     >
				       {getFieldDecorator('code', {
				       		initialValue:this.state.formData.code,
	                        rules: [{required: true, message: '权限编码不能为空'}],
	                    })(
	                        <Input placeholder='请输入权限编码'/>
	                    )}
				        
		          	 </FormItem>
			        <FormItem
			          	 	  {...formItemLayout}
		          			label="应用名称"
			          	 >
			        
			        {getFieldDecorator('appId', {
				       		initialValue:this.state.formData.appId,
	                        rules: [{required: true, message: '权限编码不能为空'}],
	                    })(
	                       <Select>
				          	{
				          		this.state.allApp.map(function (app, i) {
			                        return (<Option key={app.id} value={app.id}>{app.name}/{app.appCode}</Option>)
			                   	})
				          	}
					        </Select>
	                    )}
			         </FormItem>
			         <FormItem
	          	 	  {...formItemLayout}
		          			label="是否启用"
			          	 >
			          	 {getFieldDecorator('isEnable', {
					       		initialValue:this.state.formData.isEnable,
		                        rules: [{required: true, message: '应用名称不能为空'}],
		                    })(
			                    <Select >
						          <Option value='1'>是</Option>
						          <Option value='0'>否</Option>
						        </Select>
		                    )}
					        
			         </FormItem>
			         <FormItem
	          	 	  {...formItemLayout}
		          			label="风险等级"
			          >
			          	 {getFieldDecorator('level', {
					       		initialValue:this.state.formData.level,
		                        rules: [{required: true, message: '风险等级不能为空'}],
		                    })(
			                    <Select >
						          <Option value='H'>高风险</Option>
						          <Option value='M'>中风险</Option>
						          <Option value='L'>低风险</Option>
						        </Select>
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

const PermissionForm = Form.create()(Permission);
export default PermissionForm;
