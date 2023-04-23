import axios from 'axios'
import { Message } from 'element-ui';
import router from '../router'


//响应拦截器
axios.interceptors.response.use(success=>{   //use 方法穿了两个参数,这俩参数都是函数
    if(success.status && success.status == 200){
        if(success.data.code == 500 || success.data.code == 401 || success.data.code == 403){//操作逻辑错误/未登录/没有权限
            Message.error({message:success.data.message})   //服务器返回的data是json对象,data为{code,message,我们要的数据}
            return;
        }
        else if(success.data.message){
        Message.success({message:success.data.message})
        }
        return success.data;
    }
},error => {           //请求失败

            if(error.response.code == 504 || error.response.code == 404){
                Message.error({message:'服务器被吃了'});//这里是前端自己在玩,因此提示的message信息是前端自己加的,
            }else if(error.response.code == 403){
                Message.error({message:'权限不足，请联系管理员！'});
            }else if(error.response.ocde == 401){
                Message.error({message:'尚未登录，请登录！'});
                router.replace('/Login');
            }else{
                if(error.response.data.message){
                    Message.error({message:error.response.data.message});
                }else{
                    Message.error({message:'未知错误 o(╯□╰)o'});
                }
            }
            return;

    }

    )
