package psk.practice.mybatis.dao;

import java.util.List;
import psk.practice.mybatis.model.Character;

public interface CharacterMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Character record);

    Character selectByPrimaryKey(Integer id);

    List<Character> selectAll();

    int updateByPrimaryKey(Character record);
}