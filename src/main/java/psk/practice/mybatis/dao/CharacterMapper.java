package psk.practice.mybatis.dao;

import java.util.List;

import org.mybatis.cdi.Mapper;
import psk.practice.mybatis.model.Character;

@Mapper
public interface CharacterMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Character record);

    int updateByPrimaryKey(Character record);

    Character selectByPrimaryKey(Integer id);

    List<Character> selectAll();

    List<Character> selectCharactersByBook(Integer bookId);
}