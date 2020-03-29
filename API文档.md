接口的endpoint：http://116.62.186.144/lostandfound

通用返回对象:{

  	"code": xxx,  状态码（200, 500,404.....）

  	"message": "xxxxx", 描述本次请求的信息

​	  "data": { }  返回数据（多为json对象）

}

#### 功能1：上传丢失物品信息

​	先将图片提交到服务器的到可以访问该资源的url，再将url和其他信息上传到服务器

##### 	接口：

  1. /uploadFile

     描述：上传文件到服务器

     参数：

     ​	表单,  key : "file",  value:文件

     返回：

     ​	成功：{”code“:200,

     ​				"message":"上传失败",

     ​				"data": {"name": "xxx.jpg", 

     ​							"url": "http://localhost:8080/lostandfound/20200326_xxx.jpg"

     ​								}

       				}  xxx为文件名

     ​	失败：{”code“:500,"message":"上传成功","data":null}

  2. 

​		

url前还有一个基础

| url             | 参数                        | 描述 |
| --------------- | --------------------------- | ---- |
| /uploadFile     | 表单, key : file,value:文件 |      |
| /uploadLostItem |                             |      |
|                 |                             |      |

