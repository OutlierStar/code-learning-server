package com.example.practicalwork.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.practicalwork.model.Message;
import com.fasterxml.jackson.databind.deser.Deserializers;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MessageMapper extends BaseMapper<Message> {
}
