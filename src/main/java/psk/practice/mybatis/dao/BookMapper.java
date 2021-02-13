package psk.practice.mybatis.dao;

import java.util.List;
import psk.practice.mybatis.model.Book;

public interface BookMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Book record);

    Book selectByPrimaryKey(Integer id);

    List<Book> selectAll();

    int updateByPrimaryKey(Book record);
}