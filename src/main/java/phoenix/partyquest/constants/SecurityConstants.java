package phoenix.partyquest.constants;

/**
 * TODO: jwt_secret key 외부에서 주입 받을 수 있도록 하기
 */
public interface SecurityConstants {
    public static final String JWT_KEY = "jxgEQeXHuPq8VdbyYFNkANdudQ53YUn4";
    public static final String JWT_HEADER = "Authorization";
    public static final String JWT_ISSUER = "Party Quest";
    public static final String JWT_BEARER_PREFIX = "Bearer ";
    public static final String JWT_SUBJECT = "JWT TOKEN";
    public static final Long JWT_ACCESS_LIFE_SPAN = 2 * 60 * 1000L;
    public static final Long JWT_REFRESH_LIFE_SPAN = 24*60 * 60 * 1000L;
    public static final Integer JWT_COOKIE_MAX_AGE = 24 * 60 * 60;
}