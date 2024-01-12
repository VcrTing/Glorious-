package com.glorious.framework.module.auth;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.glorious.common.define.datatset.RedisConstants;
import com.glorious.common.define.datatset.RedisTimeConstants;
import com.glorious.framework.model.auth.AuthUser;
import com.glorious.framework.model.auth.AuthUserDto;
import com.glorious.framework.component.tools.RedisTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import com.auth0.jwt.JWT;

@Slf4j
@Component
public class TokenService {

    // 小于 60分钟 就 刷新 TOKEN
    private static final Long EXPIRE_TIME_MINUTE_MIN = 60L;

    // 令牌自定义标识
    private static final String HEADER = "Authorization";
    // 令牌前缀
    public static final String TOKEN_PREFIX = "Bearer ";
    // 令牌秘钥
    private static final String SECRET = "VcrTing";


    final static String KEY_ID = "id";
    final static String KEY_USERNAME = "username";

    @Autowired
    RedisTool redisTool;

    /**
     * 存入 REDIS
     */
    private void saveToCache(AuthUser authUser) {
        // 储存至 redis
        String key = RedisConstants.KEY_AUTH_USER + authUser.getUserId();
        AuthUserDto dto = AuthUserDto.byEntity(authUser);
        redisTool.setObject(key, dto, RedisTimeConstants.AUTH_EXPIRE_MINUET);
    }

    /**
     * 取出 REDIS
     */
    private AuthUser getFromCache(Long uid) {
        log.debug("登录用户的 ID = `{}`", uid);
        // 储存至 redis
        String key = RedisConstants.KEY_AUTH_USER + uid;
        return AuthUserDto.toEntity(redisTool.getObject(key));
    }


    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public AuthUser getLoginUser(HttpServletRequest request) {
        // 获取请求携带的令牌
        String token = getTokenFromRequest(request);
        // 从 redis 里面拿 用户信息
        return (StringUtils.hasText(token)) ? getFromCache(decodeJwt(token)) : null;
    }

    /**
     * 构建 完整 的 用户登录 信息
     *
     * @return 用户信息
     */
    public AuthUser buildAuthUser(AuthUser authUser) {
        authUser.setJwt( genJwt(authUser.getUserId(), authUser.getUsername()) );
        authUser.setLoginTime(System.currentTimeMillis());
        // 存入 redis
        saveToCache(authUser);
        return authUser;
    }

    /**
     * 验证令牌有效期，相差不足20分钟，自动刷新缓存
     *
     * @param authUser 登录信息
     * @return 令牌
     */
    public void continueToken(AuthUser authUser) {
        Long expireTime = authUser.getExpireTime();
        if (expireTime != null) {
            if (expireTime - System.currentTimeMillis() <= EXPIRE_TIME_MINUTE_MIN) { refreshToken(authUser); } }
    }

    /**
     * 刷新令牌有效期
     *
     * @param authUser 登录信息
     */
    public void refreshToken(AuthUser authUser) {
        log.debug("刷新 TOKEN");
        authUser.setLoginTime(System.currentTimeMillis());
        authUser.setExpireTime(authUser.getLoginTime() + RedisTimeConstants.AUTH_EXPIRE_MINUET);
        // 存入 redis
        saveToCache(authUser);
    }

    /**
     * 加密 JWT
     *
     * @params
     * @return
     */
    public static String genJwt(Long id, String username, Integer minute) {
        HashMap<String, Object> map = new HashMap<>();
        return JWT.create().withHeader(map)
                .withClaim(KEY_ID, id)
                .withClaim(KEY_USERNAME, username)
                .withExpiresAt(expireTime(minute))
                .sign(Algorithm.HMAC256(SECRET));
    }
    public static String genJwt(Long id, String username) { return genJwt(id, username, RedisTimeConstants.AUTH_EXPIRE_MINUET); }

    /**
     * 解密
     * @params
     * @return
     */
    public static Long decodeJwt(String jwt) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        Claim claim = verifier.verify(jwt).getClaim(KEY_ID);
        return claim == null ? 0L : claim.asLong();
    }

    /**
     * 获取请求 TOKEN
     *
     * @param request
     * @return token
     */
    private String getTokenFromRequest(HttpServletRequest request) {
        String token = request.getHeader(HEADER);
        if (StringUtils.hasText(token) && token.startsWith(TOKEN_PREFIX)) {
            return token.replace(TOKEN_PREFIX, "");
        }
        return token;
    }

    /**
     * 计算 过期 时间
     *
     * @params
     * @return
     */
    // 过期 时间
    public static Date expireTime(int minute) {
        Calendar ex = Calendar.getInstance();
        ex.add(Calendar.MINUTE, minute);
        return ex.getTime();
    }
}
