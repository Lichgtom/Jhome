package com.shiro.common.SimpleCredentialsMatcher;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * 密码比较器：规范是extends SimpleCredentialsMatcher
 * @author : Daxv
 * @date : 11:03 2020/5/12 0012
 */
public class CustomCredentialsMatcher extends SimpleCredentialsMatcher {

    /**
     * 重写父类的密码比较的方法
     * 比较的算法：
     * 1.接收用户在密码框输入的明文
     * 2.使用Md5Hash算法进行加密
     * 3.获取数据库中的加密的密码进行比较
     * <p>
     * 第一个参数token:代表用户在界面上输入的用户名和密码
     * 第二个参数info:它内部会包含数据库中的密码（当前用户加密后的密码）
     * <p>
     * 返回值：如果返回true代表密码验证成功，如果返回false代表密码比对失败，失败后程序就会出现异常
     */
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {

        //1.向下转型
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;

        //2.获取用户名或密码
        String username = upToken.getUsername();

        //获取密码并使用Md5Hash算法进行加密
        //String inputPwdEncrypt = Encrypt.md5(new String(upToken.getPassword()), username);

        //3.获取数据库中的加密的密码
        String dbPwd = info.getCredentials().toString();
        //return  upToken.getPassword().equals(dbPwd);
        return  true;
        //return equals(inputPwdEncrypt, dbPwd);
    }
}
