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

-- 公交站点表（bus_stations）
CREATE TABLE bus_stations (
                              station_id INT PRIMARY KEY AUTO_INCREMENT COMMENT '站点ID（PK）',
                              station_name VARCHAR(50) NOT NULL COMMENT '站点名称',
                              latitude DECIMAL(10,6) COMMENT '纬度',
                              longitude DECIMAL(10,6) COMMENT '经度',
                              address VARCHAR(200) COMMENT '站点地址',
                              station_type TINYINT NOT NULL COMMENT '站点类型（1-普通站/2-枢纽站）',
                              status TINYINT NOT NULL DEFAULT 1 COMMENT '状态（0-关闭/1-启用）',
                              create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                              update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='公交站点表';

-- 公交线路表（bus_routes）
CREATE TABLE bus_routes (
                            route_id INT PRIMARY KEY AUTO_INCREMENT COMMENT '线路ID（PK）',
                            route_name VARCHAR(50) NOT NULL COMMENT '线路名称',
                            start_station INT NOT NULL COMMENT '起点站ID（FK -> bus_stations.station_id）',
                            end_station INT NOT NULL COMMENT '终点站ID（FK -> bus_stations.station_id）',
                            total_stations INT NOT NULL COMMENT '总站点数',
                            total_distance DECIMAL(6,2) NOT NULL COMMENT '总里程（公里）',
                            departure_time TIME NOT NULL COMMENT '首班车时间',
                            arrival_time TIME NOT NULL COMMENT '末班车时间',
                            status TINYINT NOT NULL DEFAULT 1 COMMENT '状态（0-停运/1-运营）',
                            create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    -- 外键约束：确保起点站/终点站存在，删除时限制，更新时级联
                            FOREIGN KEY (start_station) REFERENCES bus_stations(station_id) ON DELETE RESTRICT ON UPDATE CASCADE,
                            FOREIGN KEY (end_station) REFERENCES bus_stations(station_id) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='公交线路表';

-- 线路-站点关联表（bus_route_stations）
CREATE TABLE bus_route_stations (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '关联ID（PK）',
    route_id INT NOT NULL COMMENT '线路ID（FK -> bus_routes.route_id）',
    station_id INT NOT NULL COMMENT '站点ID（FK -> bus_stations.station_id）',
    station_order INT NOT NULL COMMENT '站点顺序（从1开始）',
    distance_from_prev DECIMAL(5,2) NOT NULL COMMENT '距前一站距离（公里）',
    estimated_time INT NOT NULL COMMENT '预计通过时间（分钟）',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    FOREIGN KEY (route_id) REFERENCES bus_routes(route_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (station_id) REFERENCES bus_stations(station_id) ON DELETE RESTRICT ON UPDATE CASCADE,
    UNIQUE KEY uk_route_station (route_id, station_id) COMMENT '同一线路下站点唯一',
    UNIQUE KEY uk_route_order (route_id, station_order) COMMENT '同一线路下站点顺序唯一'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='线路-站点关联表';