package psk.practice.mybatis.dao;

import java.util.List;

import org.mybatis.cdi.Mapper;
import psk.practice.mybatis.model.Author;

@Mapper
public interface AuthorMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Author record);

    Author selectByPrimaryKey(Integer id);

    List<Author> selectAll();

    int updateByPrimaryKey(Author record);
}