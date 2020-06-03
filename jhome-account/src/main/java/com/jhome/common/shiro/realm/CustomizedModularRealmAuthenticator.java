package com.jhome.common.shiro.realm;

import lombok.Getter;
import lombok.Setter;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
/**
 * 多数据源 认证
 * @author : Daxv
 * @date : 11:03 2020/5/12 0012
 */
@Getter
@Setter
public class CustomizedModularRealmAuthenticator extends ModularRealmAuthenticator   {

    /**                                                  doGetAuthenticationInfo
     * 重写doAuthenticate让APP帐号和PC帐号自动使用各自的Realm
     * https://www.jianshu.com/p/6866cdc4462a
     */
    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken) throws AuthenticationException {
        /**
         * 判断getRealms()是否返回为空
         */
        this.assertRealmsConfigured();
        /**
         * jhomeToken
         */
        jhomeToken customizedToken = (jhomeToken) authenticationToken;
        /**
         * 登录设备类型
         */
        String deviceType = customizedToken.getDeviceType();
        /**
         * 所有自定义的Realm
         */
        Collection<Realm> customerRealms = this.getRealms();
        /**
         * 登录设备类型对应的所有自定义Realm
         */
        Collection<Realm> deviceRealms = new ArrayList<>();
        /**
         * 这里所有自定义的Realm的Name必须包含相对应的设备名
         */
        for (Realm realm : customerRealms) {
            if (realm.getName().contains(deviceType))
                deviceRealms.add(realm);
        }
        /**
         * 判断是单Realm还是多Realm
         */
        if (deviceRealms.size() == 1) {
            return doSingleRealmAuthentication(deviceRealms.iterator().next(),
                    customizedToken);
        } else {
            return doMultiRealmAuthentication(deviceRealms, customizedToken);
        }
    }


}