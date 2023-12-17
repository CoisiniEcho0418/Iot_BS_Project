package com.hwj.bs_backend.service;

import com.hwj.bs_backend.param.DeviceAddRequest;
import com.hwj.bs_backend.param.DeviceCountResponse;
import com.hwj.bs_backend.param.DeviceListResponse;
import com.hwj.bs_backend.param.DeviceUpdateRequest;
import com.hwj.bs_backend.pojo.Device;
import com.hwj.bs_backend.pojo.Result;

import java.util.Date;
import java.util.List;

public interface DeviceService {

    /**
     * 获取某个设备信息
     *
     * @param deviceId 设备ID
     * @return 返回设备信息
     */
    Result<Device> getDeviceInfo(String deviceId);

    /**
     * 获取用户设备列表
     *
     * @param userId 用户ID
     * @return 返回用户设备信息列表
     */
    Result<List<DeviceListResponse>> getUserDevices(Integer userId);

    /**
     * 新增设备
     *
     * @param request 新增设备参数类
     * @return 返回新增设备处理结果
     */
    Result<String> addDevice(DeviceAddRequest request);

    /**
     * 修改设备配置
     *
     * @param request  修改设备配置参数类
     * @param deviceId 设备id
     * @return 返回修改设备配置处理结果
     */
    Result<String> updateDevice(String deviceId, DeviceUpdateRequest request);

    /**
     * 获取最近七天新增设备数量
     *
     * @param userId 用户id
     * @param today  当天的日期
     * @return 最近七天新增的设备列表
     */
    Result<List<DeviceCountResponse>> getNewDevicesCount(Integer userId, Date today);
}
