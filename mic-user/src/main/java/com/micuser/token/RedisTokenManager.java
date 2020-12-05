package com.micuser.token;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.micuser.common.RedisUtil;
import com.micuser.entity.User;

@Service
public class RedisTokenManager implements TokenManager {

    @Autowired
    private RedisUtil redisUtil;

//    @Autowired
////    private GlobalConfig globalConfig;

    /**
     * 创建token
     * @param userInfo
     * @return
     */
    public String getToken(User user){
    	
		Date start = new Date();
		long currentTime = System.currentTimeMillis() + 60* 60 * 1000;//一小时有效时间
		Date end = new Date(currentTime);
		String token = "";
		token = JWT.create().withAudience(user.getId()).withIssuedAt(start).withExpiresAt(end)
				.sign(Algorithm.HMAC256(user.getPassword()));    	
        try {
			redisUtil.setexObject(token,user.getUsername(),1000);
			System.out.println(redisUtil.getObject(token));
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("写入token到redis中出错！");
		}
        return token;
    }

    /**
     * 刷新用户 重新设置redis有效时间
     * @param token
     */
    public void refreshUserToken(String token){
        if(redisUtil.keyExist(token)){
            try {
				redisUtil.expire(token, 1000);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }

    /**
     * 用户退出登陆
     * @param token
     */
    public void loginOff(String token){
         redisUtil.delRedisByKey(token);
    }

    /**
     * 获取用户信息
     * @param token
     * @return
     */
    public User getUserInfoByToken(String token){
        if(redisUtil.keyExist(token)){
            try {
				return (User)redisUtil.getObject(token);
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
        return null;
    }
    
    
    
}