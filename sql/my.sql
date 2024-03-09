CREATE TABLE FilmInfo (
    id INT PRIMARY KEY AUTO_INCREMENT, -- 电影id，主键，自增长
    filmName VARCHAR(255),              -- 电影名
    filmEnName VARCHAR(255),            -- 电影英文名
    filmUrl VARCHAR(255),               -- 电影海报
    filmLanguage VARCHAR(255),          -- 电影语言
    filmType VARCHAR(255),              -- 电影类型
    filmTime VARCHAR(255),              -- 电影时长
    releaseLoc VARCHAR(255),            -- 上映国家
    releaseTime DATETIME,           -- 上映日期
    filmDes VARCHAR(255),                       -- 电影简介
    filmActor VARCHAR(255)                      -- 电影主演
);

CREATE TABLE cinema (
    cinema_id INT PRIMARY KEY AUTO_INCREMENT, -- 影院代码，主键，自增
    cinema_name VARCHAR(255) NOT NULL, -- 影院名称，不允许为空
    cinema_addr VARCHAR(255) NOT NULL, -- 影院地址，不允许为空
    longitude DECIMAL(10, 6) NOT NULL, -- 位置经度，不允许为空
    altitude DECIMAL(10, 6) NOT NULL, -- 位置纬度，不允许为空
    cinema_hall TEXT NOT NULL, -- 影厅，不允许为空，使用TEXT类型以支持更大的文本内容
    cinema_tag VARCHAR(255) NOT NULL -- 影院标签，不允许为空
);

CREATE TABLE CinemaFilm (
    cinemaFilmId INT PRIMARY KEY AUTO_INCREMENT, -- 绑定id
    filmId INT, -- 电影id
    cinemaId INT,-- 影院id
    filmPrice INT,-- 价格
    discountPrice INT,-- 折扣价格
    cinemaHall VARCHAR(255),-- 影厅
    playDate DATE, -- 播放日期
    beginTime TIME,-- 开始时间
    endTime TIME-- 结束时间
);
CREATE TABLE Swiper (
    SwiperId INT PRIMARY KEY AUTO_INCREMENT, -- 轮播图ID，主键自增
    filmImage VARCHAR(255) NOT NULL, -- 电影海报URL，不能为空
    filmName VARCHAR(255) NOT NULL, -- 电影名称，不能为空
    filmId INT NOT NULL, -- 电影ID，不能为空，用于关联电影信息
);
CREATE TABLE Orders (
  -- 订单ID
  order_id INT NOT NULL AUTO_INCREMENT,
  -- 用户名
  user_name VARCHAR(255) NOT NULL,
  -- 关联ID（可能是指电影院与电影的关联ID）
  cinema_film_id INT NOT NULL,
  -- 购票数量
  ticket_num INT NOT NULL,
  -- 影票单价
  ticket_price INT NOT NULL,
  -- 电影名称
  film_name VARCHAR(255) NOT NULL,
  -- 电影ID
  film_id INT NOT NULL,
  -- 选座信息（假设存储单个座位字符，如果是多个座位可能需要重构此字段）
  seat CHAR(1),
  -- 下单日期时间
  order_date DATETIME NOT NULL,
  -- 影院地址
  cinema_addr VARCHAR(255) NOT NULL,
  -- 影院名称
  cinema_name VARCHAR(255) NOT NULL,
  -- 影院ID
  cinema_id INT NOT NULL,
)
CREATE TABLE Seats (
    seat_id INT PRIMARY KEY AUTO_INCREMENT,        -- 座位ID（自增长主键）
    y_coord INT NOT NULL,                         -- Y坐标
    x_coord INT NOT NULL,                         -- X坐标
    seat_code VARCHAR(255) NOT NULL,              -- 座位编码
    status TINYINT NOT NULL,                      -- 座位状态（0通常代表可用，可根据实际情况定义枚举或整数范围）
    row_num TINYINT NOT NULL,                     -- 行号
    column_num TINYINT NOT NULL,                   -- 列号
    cinema_id INT NOT NULL,                       -- 影院ID（如果有多家影院，可添加外键关联）

    -- 若存在Cinemas表，添加外键约束
    FOREIGN KEY (cinema_id) REFERENCES cinema(cinema_id)
) ENGINE=InnoDB;

CREATE TABLE OrderDetails (
    detail_id INT PRIMARY KEY AUTO_INCREMENT,
    order_id INT NOT NULL,                        -- 订单ID
    seat_id INT NOT NULL,                         -- 座位ID

    -- 添加外键约束
    FOREIGN KEY (order_id) REFERENCES orders(order_id),
    FOREIGN KEY (seat_id) REFERENCES seat(seat_id)
) ENGINE=InnoDB;

-- 创建用户表
CREATE TABLE Users (
    -- 用户ID，自增主键
    userId INT AUTO_INCREMENT PRIMARY KEY,
    -- 用户名，不能为空
    userName VARCHAR(255) NOT NULL,
    -- 电话号码
    phone VARCHAR(255),
    -- 密码，通常会被加密存储
    password VARCHAR(255),
    -- 注册时间
    createTime DATE,
    -- 性别，假设仅限于'男'和'女'
    sex VARCHAR(255) DEFAULT NULL COMMENT '性别',
    -- 地址信息
    address VARCHAR(255),
    -- 生日日期
    birthday DATE COMMENT '出生日期'
);

