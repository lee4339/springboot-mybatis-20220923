package com.boot.mybatis20220923.repository;

import com.boot.mybatis20220923.domain.News;
import com.boot.mybatis20220923.dto.NewsWriteReqDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NewsRepository {
    public int save(News news);
    public News getNews(int news_id);
}
