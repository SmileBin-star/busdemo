CREATE TABLE sys_user (
                          user_id INT PRIMARY KEY AUTO_INCREMENT,
                          username VARCHAR(50) NOT NULL UNIQUE,
                          password VARCHAR(100) NOT NULL,
                          role VARCHAR(20) NOT NULL DEFAULT 'admin',
                          create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          status TINYINT NOT NULL DEFAULT 1
);

INSERT INTO sys_user (username, password, role)
VALUES ('admin', 'e10adc3949ba59abbe56e057f20f883e', 'super_admin');

-- 公交车辆表
CREATE TABLE bus_vehicles (
                              vehicle_id INT PRIMARY KEY AUTO_INCREMENT COMMENT '车辆ID',
                              license_plate VARCHAR(20) NOT NULL UNIQUE COMMENT '车牌号',
                              model VARCHAR(50) COMMENT '车型',
                              capacity INT COMMENT '载客量',
                              status TINYINT DEFAULT 1 COMMENT '状态(1:正常,0:维修)',
                              purchase_date DATE COMMENT '购置日期',
                              create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
);

-- 公交时刻表
CREATE TABLE bus_schedules (
                               schedule_id INT PRIMARY KEY AUTO_INCREMENT COMMENT '时刻表ID',
                               route_name VARCHAR(100) NOT NULL COMMENT '线路名称',
                               vehicle_id INT COMMENT '关联车辆ID',
                               departure_time TIME NOT NULL COMMENT '发车时间',
                               arrival_time TIME NOT NULL COMMENT '到达时间',
                               station_count INT COMMENT '站点数量',
                               status TINYINT DEFAULT 1 COMMENT '状态(1:有效,0:无效)',
                               FOREIGN KEY (vehicle_id) REFERENCES bus_vehicles(vehicle_id)
);

-- 公交到站记录表
CREATE TABLE bus_arrival_records (
                                     record_id INT PRIMARY KEY AUTO_INCREMENT COMMENT '记录ID',
                                     schedule_id INT COMMENT '关联时刻表ID',
                                     station_name VARCHAR(50) NOT NULL COMMENT '站点名称',
                                     actual_arrival_time DATETIME COMMENT '实际到站时间',
                                     delay_minutes INT DEFAULT 0 COMMENT '延迟分钟数',
                                     create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                     FOREIGN KEY (schedule_id) REFERENCES bus_schedules(schedule_id)
);