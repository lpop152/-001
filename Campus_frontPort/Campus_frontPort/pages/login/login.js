// pages/login/login.js
import {Login,getVerificationCode} from '../../utils/api/login'
Page({

  /**
   * 页面的初始数据
   */
  data: {
    telephone:"",//手机号
    verificationCode:""//验证码
  },
  async getVerificationCode(){
    console.log(this.data.telephone)
    const res = await getVerificationCode(this.data.telephone)
  },
  async login(){
    const res = await Login(this.data.telephone,this.data.verificationCode)
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  }
})