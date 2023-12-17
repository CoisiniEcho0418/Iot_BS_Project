package com.hwj.bs_backend.mapper;


import com.hwj.bs_backend.param.DeviceAddRequest;
import com.hwj.bs_backend.param.DeviceCountResponse;
import com.hwj.bs_backend.param.DeviceListResponse;
import com.hwj.bs_backend.param.DeviceUpdateRequest;
import org.apache.ibatis.annotations.*;
import com.hwj.bs_backend.pojo.Device;

import java.util.List;
import java.util.Date;

@Mapper
public interface DeviceMapper {

    /**
     * 获取某个设备信息
     *
     * @param deviceId 设备ID
     * @return 返回设备信息
     */
    @Select("SELECT * FROM Device WHERE device_id = #{deviceId}")
    Device getDeviceInfo(@Param("deviceId") String deviceId);

    /**
     * 获取用户设备信息列表
     *
     * @param userId 用户ID
     * @return 返回用户设备信息列表
     */
    @Select("SELECT device_id, device_name, device_type, device_description, registration_time, is_active FROM device WHERE user_id = #{userId}")
    List<DeviceListResponse> getUserDevices(@Param("userId") Integer userId);

    /**
     * 新增设备
     * 使用了 UUID() 生成 唯一的device_id，并将 registration_time 同时赋值给 last_update。
     *
     * @param request 新增设备参数类
     * @return 插入的记录数
     */
    @Insert("INSERT INTO device (device_id, user_id, device_name, device_type, device_description, is_active, registration_time, last_update) " +
            "VALUES (UUID(), #{userId}, #{deviceName}, #{deviceType}, #{deviceDescription}, #{isActive}, #{registrationTime}, #{registrationTime})")
    int insertDevice(DeviceAddRequest request);

    /**
     * 修改设备配置
     *
     * @param request  修改设备配置参数类
     * @param deviceId 设备id
     * @return 修改的记录数
     */
    @Update({
            "<script>",
            "UPDATE device SET ",
            "<if test='request.deviceName != null'>device_name = #{request.deviceName}, </if>",
            "<if test='request.deviceType != null'>device_type = #{request.deviceType}, </if>",
            "<if test='request.deviceDescription != null'>device_description = #{request.deviceDescription}, </if>",
            "<if test='request.lastUpdate != null'>last_update = #{request.lastUpdate}, </if>",
            "<if test='request.isActive != null'>is_active = #{request.isActive} </if>",
            "WHERE device_id = #{deviceId}",
            "</script>"
    })
    int updateDevice(@Param("deviceId") String deviceId, @Param("request") DeviceUpdateRequest request);


    /**
     * 获取最近七天新增设备数量
     *
     * @param userId 用户id
     * @param today  当天的日期
     * @return 最近七天新增的设备列表
     */
    @Select("SELECT DATE(registration_time) AS date, COUNT(*) AS count " +
            "FROM device " +
            "WHERE user_id = #{userId} AND DATE(registration_time) BETWEEN DATE_SUB(#{today}, INTERVAL 6 DAY) AND #{today} " +
            "GROUP BY DATE(registration_time)" +
            "ORDER BY date ASC")
    // 按照日期升序排序
    List<DeviceCountResponse> getNewDevicesCount(@Param("userId") Integer userId, @Param("today") Date today);
}
