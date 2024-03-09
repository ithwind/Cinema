package com.ruoyi.common.constant;

/**
 * 缓存的key 常量
 * 
 * @author ruoyi
 */
public class CacheConstants
{
    /**
     * 登录用户 redis key
     */
    public static final String LOGIN_TOKEN_KEY = "login_tokens:";

    /**
     * 验证码 redis key
     */
    public static final String CAPTCHA_CODE_KEY = "captcha_codes:";

    /**
     * 参数管理 cache key
     */
    public static final String SYS_CONFIG_KEY = "sys_config:";

    /**
     * 字典管理 cache key
     */
    public static final String SYS_DICT_KEY = "sys_dict:";

    /**
     * 防重提交 redis key
     */
    public static final String REPEAT_SUBMIT_KEY = "repeat_submit:";

    /**
     * 限流 redis key
     */
    public static final String RATE_LIMIT_KEY = "rate_limit:";

    /**
     * 登录账户密码错误次数 redis key
     */
    public static final String PWD_ERR_CNT_KEY = "pwd_err_cnt:";
    /**
     * 查询电影的常量 Film + filmId
     */
    public static final String FILM = "film:";
    /**
     * 通过电影名称查询的数据
     */
    public static final String FILM_BY_NAME= "filmByName:";
    /**
     * 电影院所有数据
     */
    public static final String CINEMA_ALL = "cinemaAll:";
    /**
     * 通过id查询的电影院数据
     */
    public static final String CINEMA_ID = "cinema:";
    /**
     * 通过名称查询影院
     */
    public static final String CINEMA_BY_NAME = "cinemaByName:";
    /**
     * 通过id查询的关联数据
     */
    public static final String CINEMA_FILM_BY_ID = "cinemaFilmById:";
    /**
     * 通过电影id查询关联信息
     */
    public static final String CINEMA_FILMS_BY_FILM_ID = "cinemaFilmsById:";
    /**
     * 所有的轮播图信息
     */
    public static final String SWIPER_ALL= "swiper_all:";
    /**
     * 通过id查询轮播图
     */
    public static final String SWIPER_ID = "swiperById:";
    /**
     * 通过filmName查询轮播图
     */
    public static final String SWIPER_NAME = "swiperByName";
    /**
     * 全部订单列表key
     */
    public static final String ORDER_ALL = "allOrders:";
    /**
     * 通过用户名查询的订单
     */
    public static final String ORDER_NAME = "orderByUserName:";
    /**
     * 指定电影的票房
     */
    public static final String FILM_BOX_OFFICE_FILM_ID = "boxOfficeByFilmId:";
}
