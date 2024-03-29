package psk.practice.mybatis.dao;

import java.util.List;

import org.mybatis.cdi.Mapper;
import psk.practice.mybatis.model.Book;

@Mapper
public interface BookMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Book record);

    Book selectByPrimaryKey(Integer id);

    List<Book> selectAll();

    int updateByPrimaryKey(Book record);
}