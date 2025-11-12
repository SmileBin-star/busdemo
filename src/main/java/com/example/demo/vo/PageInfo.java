package com.example.demo.vo;

import lombok.Data;
import java.util.List;

@Data
public class PageInfo<T> {
    private List<T> list; // 数据列表
    private Integer pageNum; // 当前页
    private Integer pageSize; // 每页条数
    private Long total; // 总条数
    private Integer pages; // 总页数
}