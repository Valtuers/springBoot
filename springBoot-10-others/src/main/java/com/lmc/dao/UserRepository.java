package com.lmc.dao;

import com.lmc.bean.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * Elasticsearch搜索框架的dao类
 */
@Component
public interface UserRepository extends ElasticsearchRepository<User,Long> {

}
