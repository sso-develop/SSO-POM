## SSO单点登录 UUMS统一用户管理平台
###### 作者： 林泽宽（Lambert Lin）
### 功能
* SSO登录验证
* SSO登录权限验证
* 用户管理
  * 用户权限分配
* 应用管理
* 权限管理
 *权限管理
 *权限申请
### 后端
 #### 构建AND部署
* 第一次构建时需要办根目录下pom.xm中modules去掉后再构建（mvn clean install），构建成功后再放开modules执行以下命令.
* mvn clean install -P local tomcat7:run
### 前端
 * NODEJS+ANTD
 * 前端目录  SSO-POM\webroot\view
 * 安装nodejs
 * npm install
 * npm start
 
### 忽略 node_modules 文件夹提交
* git update-index --assume-unchanged node_modules
