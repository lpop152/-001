//导入模块、包提供的类
import WxRequest from 'mina-request'
//对类进行实例化
const instance = new WxRequest({
  baseURL:'http://localhost:8080',
  timeout:15000
})
//添加请求拦截器
instance.interceptors.request=(config)=>{
//添加token
const token = wx.getStorageSync("token")
console.log(token)
if(token!=null){
  config.header['token'] = token
}
return config
}
//添加响应拦截器
instance.interceptors.response=(response)=>{
  //从response对象中结构两个数据
  const{isSuccess,data} =response
console.log(isSuccess)
  if(!isSuccess){
    wx.showToast({
      title: '网络异常请重试！',
      icon:'error'
    })
    return Promise.reject(response)
  }
  //如果isSuccess为true，说明执行成功
  return data
}
//导出实例
export default instance