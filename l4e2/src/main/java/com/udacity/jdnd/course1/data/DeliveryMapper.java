package com.udacity.jdnd.course1.data;

import org.apache.ibatis.annotations.*;

@Mapper
public interface DeliveryMapper {
    @Select("SELECT * FROM Delivery WHERE id = #{id}")
    Delivery findDelivery(Integer id);

    @Insert("INSERT INTO Delivery (orderId, time) VALUES(#{orderId}, #{time})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer insert(Delivery delivery);

    @Delete("DELETE FROM Delivery WHERE id = #{id}")
    void delete(Integer id);
}
