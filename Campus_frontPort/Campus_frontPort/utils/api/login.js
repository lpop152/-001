import http from '../http.js'
export const Login=(telephone,VerificationCode)=>{
  return http.post("/user/login",{telephone:telephone,VerificationCode:VerificationCode})
}
export const getVerificationCode=(telephone)=>{
  return http.get("/user/getVerificationCode",{telephone:telephone})
}
