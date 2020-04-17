package tk.mybatis.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 不能被spring扫描到
 * */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
