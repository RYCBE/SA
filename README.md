# RESTful WebPOS
请参考[spring-petclinic/spring-petclinic-rest](https://github.com/spring-petclinic/spring-petclinic-rest) 将aw03的webpos项目改为REST架构风格的应用系统。具体要求包括：

- 请使用OpenAPI的定义REST接口，参考[api.yml](src/main/resources/api.yml)；
- 有兴趣卷的同学：请将前端改为使用jquery等js框架对restful接口调用获得数据后在前端进行页面内容，可参考[sa-spring/spring-boot-ajax](https://github.com/sa-spring/spring-boot-ajax)；
- 没兴趣卷的同学：可以使用Postman或其他HTTP工具进行交互测试；
- 请编写README.md，详细介绍你所写的RESTfulWebPOS;
- 请着重理解**应用状态**与**资源状态**两个概念，并在README.md中写下你的理解。



## 服务器端（维护资源状态）

| 接口类型 | url           | parameter                                  | 备注                     |
| -------- | ------------- | ------------------------------------------ | ------------------------ |
| GET      | products      |                                            | 获取所有的产品信息       |
| GET      | products/{id} |                                            | 获取id为{id}的产品信息   |
| GET      | tax           |                                            | 获取当前税率             |
| POST     | products      | {"category":str,"name":str,"price":double} | 添加一个商品，id自动生成 |

服务器有且仅有维护目前可以提供的产品的信息的责任，因此本次作业只需要将products的信息在服务器端进行维护。



## 客户端（维护应用状态）

主要工作是需要维护本地的购物车状态。采取的解决方案是用html的localStorage。

### 获取本地缓存的购物车状态（getItem）

```
let oldCart = localStorage.getItem("itemCart")
oldCart = JSON.parse(oldCart)
```

### 更新购物车状态至缓存（setItem）

```
localStorage.setItem("itemCart",newCart)
```

其中oldCart、newCart变量是字符串，但是可以通过JSON.parse函数把其中的信息解析出来使用

由于cart是用户自己的购物车，在restful架构中，不应该交给服务器维护，因此必须使用本地存储的策略。



两端分别维护，并且两端的交互应该是无状态的，需要在每一次请求中包含处理该请求所需的一切信息。

