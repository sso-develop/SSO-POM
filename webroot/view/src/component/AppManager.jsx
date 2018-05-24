import React, { Component } from 'react';
import { Table,Divider,Modal,Form,Input,InputNumber,Select,Button,message,Row,Col,Popconfirm} from 'antd';
import Enum from '../common/Enum.js';
import {} from '../common/utils.js';
import $ from 'jquery';

const FormItem = Form.Item;
const Option = Select.Option;
const RequestUrls = Enum.requestUrls

class sysApp extends Component {
	constructor(props) {
    	super(props);
    	this.state = {
				dataSource:[],
				visible:false,
				formData:{
					id:0,
					name:'',
					appCode:'',
					sort:'0',
					isEnable:'1'
				},
				searchData:{
					name:'',
					appCode:'',
					pageNumber:1
				},
				formState:'add',
				pager:{
					total:0,
					pageSize:0,
					loading:false
				}
			}
	}
	
	
	componentWillMount() {
		this.loadListData(this);
	}
	
	deleteUser(record){
		let that = this;
		$.post(RequestUrls.sysApp.deleteUumsSysAppByIdUrl, {id:record.id},function(data) {
		  if(!data.success){
		  	message.error(data.msg)
		  	
		  }else{
		  	message.success('删除成功');
		  	that.loadListData(this);
		  }
		});
	}
	
	loadListData(){
		let that = this;
		this.setState({
	      	pager:{
				loading:true
			}
	    });
		 $.post(RequestUrls.sysApp.queryUumsSysAppByPagerUrl, this.state.searchData,function(data) {
		   const d = data.data;
		   that.setState({
		      	dataSource: d.result,
		      	pager:{
					total:d.totalCount,
					pageSize:d.pageSize,
					loading:false
				}
		    });
		});
	}
	
	showModal(op,record){
		this.setState({visible: true,formState:op});
	
		if(op === 'edit'){
			var data = {
				id:record.id,
				name:record.name,
				appCode:record.appCode,
				sort:record.sort,
				isEnable:record.isEnable?'1':'0'
			}
			
			this.setState({visible: true,formData:data});
		}
	}
	
	closeModal(){
		var data = {
					id:'',
					name:'',
					appCode:'',
					sort:0,
					isEnable:'1'
			};
		
		this.setState({
			formData:data,
	        visible: false
	 });
	  this.props.form.resetFields()
	}
	
   handleOk(e){
	   this.setState({
	      visible: false,
	   });
   }
   handleCancel(e){
	 	this.closeModal(this);
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
  handleSearchChange(event){
  	const target = event.target;
  	const value = target.value;
  	const name = target.name;
  	
  	const data = this.state.searchData;
    data[name] = value;
  	this.setState({
      searchData:data
    });
  }
  handleFormChange (changedFields){
  	
  	const target = changedFields.target;
  	const value = target.value;
  	const name = target.id;
  	var data = this.state.formData;
  	data[name] = value
  	 this.setState({
      formData: data,
    });
  }
  handleSubmit(e){
    e.preventDefault();
    
    this.props.form.validateFields((err, values) => {
      if (!err) {
        let that = this;
        var params = values;
        params.id=that.state.formData.id;
        $.post(this.state.formState==='add'?RequestUrls.sysApp.insertUumsSysAppUrl:RequestUrls.sysApp.updateUumsSysAppByIdUrl, values,function(data) {
		  if(!data.success){
		  	message.error(data.msg)
		  }else{
		  	that.closeModal(that);
		  	that.loadListData(that);
		  }
		});
      }
    });
    
  }
   handleFormIsEnableChange (currency) {
   	let data = this.state.formData;
   	data.isEnable = currency;
   	this.setState({
   		formData:data
   	});
  }
  render() {
  	const dataSource = this.state.dataSource;
		const columns = [{
			  title: '应用名称',
			  dataIndex: 'name',
			  key: 'name',
			}, {
			  title: '应用编码',
			  dataIndex: 'appCode',
			  key: 'appCode',
			}, {
			  title: '是否启用',
			  dataIndex: 'isEnable',
			  key: 'isEnable',
			  render:(text,record) =>(
			  	text?'是':'否'
			  )
			},{
			  title: '排序',
			  dataIndex: 'sort',
			  key: 'sort',
			},{
			  title: '创建时间',
			  dataIndex: 'createDate',
			  key: 'createDate',
			},{
				  title: '操作',
				  key: 'action',
				  render: (text, record) => {
				  	if(record.appCode === 'SSO_LOGIN'){
				  		return <span>--</span>
				  	}else{
				  		return (
						  	<span>
						       <a onClick = {this.showModal.bind(this,'edit',record)}>编辑</a>
						       <Divider type="vertical" />
						       
						        <Popconfirm title='是否要删除?' onConfirm = {this.deleteUser.bind(this,record)} okText="确定" cancelText="取消">
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
 	const that= this;
    return (
    	<div>
    		 <Form onSubmit={this.handleSearchSubmit.bind(this)}>
		        <Row gutter={24}>
		        	<Col span={8}>
			          <FormItem 
				          {...formItemLayout}
				          label={'应用名称'}
			          >
			            <Input name='name' onChange = {this.handleSearchChange.bind(this)} value = {this.state.searchData.name}/>
			          </FormItem>
			        </Col>
			        <Col span={8}>
			          <FormItem 
				          {...formItemLayout}
				          label={'应用编码'}
			          >
			            <Input name='appCode' onChange = {this.handleSearchChange.bind(this)} value = {this.state.searchData.appCode}/>
			          </FormItem>
			        </Col>
			        <Col span={8} style={{ textAlign: 'right' }}>
			            <Button type="primary" htmlType="submit">搜索</Button>
			              <Button style={{ marginLeft: 8 }} onClick={this.showModal.bind(this,'add')}>新增</Button>
		          	</Col>
		        </Row>
		      </Form>
	       	<Table 
	       		bordered 
	       		dataSource={dataSource} 
	       		columns={columns} 
	       		rowKey={record => record.id}
	       		loading = {this.state.pager.loading}
	       		pagination={{
	       				total:this.state.pager.total,
	       				pageSize:this.state.pager.pageSize,
	       				showTotal: function () {  //设置显示一共几条数据
				            return '共 ' + (that.state.pager.total) + ' 条数据'; 
				        },
				        onChange(current) {  //点击改变页数的选项时调用函数，current:将要跳转的页数
					          const data = that.state.searchData;
					          data.pageNumber = current;
					          that.setState({
					          	searchData:data
					          })
					          that.loadListData(that);
					       
					    },  
			     }}
	       		/>
	       	
	       	<Modal
	          title={(this.state.formState==='add'?'添加':'编辑')+'应用'}
	          footer={null}
	          visible={this.state.visible}
	          onOk={this.handleOk.bind(this)}
	          onCancel={this.handleCancel.bind(this)}
	        >
	          <Form 
	          	horizontal = {'true'} 
	          	onChange={this.handleFormChange.bind(this)} 
	          	onSubmit={this.handleSubmit.bind(this)}>
	          	 <FormItem
			           {...formItemLayout}
			          label="应用名称"
			     >
			       {getFieldDecorator('name', {
			       		initialValue:this.state.formData.name,
                        rules: [{required: true, message: '应用名称不能为空'}],
                    })(
                        <Input placeholder='请输入应用名称'/>
                    )}
			        
	          	 </FormItem>
	          	 <FormItem
			           {...formItemLayout}
			          label="应用编码"
			     >
			         {getFieldDecorator('AppCode', {
			         	initialValue:this.state.formData.appCode,
                        rules: [{required: true, message: '应用编码不能为空'}],
                    })(
                        <Input  placeholder='请输入应用编码'/>
                    )}
	          	 </FormItem>
	          	  <FormItem
			           {...formItemLayout}
			          label="排序"
			     >
			         {getFieldDecorator('sort', {
			         	initialValue:this.state.formData.sort,
                        rules: [{required: true, message: '排序不能为空'}],
                    })(
                        <InputNumber min={0} />
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
                        <Select 
			        	onChange={this.handleFormIsEnableChange.bind(this)}
			        >
			          <Option value='1'>是</Option>
			          <Option value='0'>否</Option>
			        </Select>
                    )}
			        
	          	 </FormItem>
	          	 <FormItem {...tailFormItemLayout}>
		          <Button type="primary" htmlType="submit">确认</Button>
		        </FormItem>
	          </Form>
	        </Modal>
	    </div>
    );
  }
}

const AppManagerForm = Form.create()(sysApp);
export default AppManagerForm;
