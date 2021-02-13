package psk.practice.mybatis.dao;

import java.util.List;
import psk.practice.mybatis.model.Character;

public interface CharacterMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.CHARACTER
     *
     * @mbg.generated Sat Feb 13 22:17:58 EET 2021
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.CHARACTER
     *
     * @mbg.generated Sat Feb 13 22:17:58 EET 2021
     */
    int insert(Character record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.CHARACTER
     *
     * @mbg.generated Sat Feb 13 22:17:58 EET 2021
     */
    Character selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.CHARACTER
     *
     * @mbg.generated Sat Feb 13 22:17:58 EET 2021
     */
    List<Character> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.CHARACTER
     *
     * @mbg.generated Sat Feb 13 22:17:58 EET 2021
     */
    int updateByPrimaryKey(Character record);
}