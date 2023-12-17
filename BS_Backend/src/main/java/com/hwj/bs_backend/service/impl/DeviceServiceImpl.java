package com.hwj.bs_backend.service.impl;

import com.hwj.bs_backend.mapper.DeviceMapper;
import com.hwj.bs_backend.param.DeviceAddRequest;
import com.hwj.bs_backend.param.DeviceCountResponse;
import com.hwj.bs_backend.param.DeviceListResponse;
import com.hwj.bs_backend.param.DeviceUpdateRequest;
import com.hwj.bs_backend.pojo.Device;
import com.hwj.bs_backend.pojo.Result;
import com.hwj.bs_backend.service.DeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    DeviceMapper deviceMapper;

    /**
     * 获取某个设备信息
     *
     * @param deviceId 目标设备的id
     * @return Result 包含设备信息的结果
     */
    @Override
    public Result<Device> getDeviceInfo(String deviceId) {
        try {
            // 调用 Mapper 层查询设备信息
            Device device = deviceMapper.getDeviceInfo(deviceId);

            if (device != null) {
                return Result.success(device);
            } else {
                return Result.error("设备不存在");
            }
        } catch (Exception e) {
            log.error("获取设备信息失败", e);
            return Result.error("获取设备信息失败：" + e.getMessage());
        }
    }

    /**
     * 查询用户设备列表
     *
     * @param userId 用户的id
     * @return Result 包含设备列表的结果
     */
    @Override
    public Result<List<DeviceListResponse>> getUserDevices(Integer userId) {
        try {
            // 调用 Mapper 层查询用户设备列表
            List<DeviceListResponse> devices = deviceMapper.getUserDevices(userId);

            return Result.success(devices);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("获取设备列表失败：" + e.getMessage());
        }
    }

    /**
     * 新增设备
     *
     * @param request 新增设备参数类
     * @return 返回新增设备处理结果
     */
    @Override
    public Result<String> addDevice(DeviceAddRequest request) {
        try {
            int insertRowCount = deviceMapper.insertDevice(request);
            if (insertRowCount != 0) {
                return Result.success("设备添加成功");
            } else {
                return Result.error("新增设备失败：");
            }

        } catch (Exception e) {
            log.error("新增设备失败", e);
            return Result.error("新增设备失败：" + e.getMessage());
        }
    }


    /**
     * 修改设备配置
     *
     * @param request  修改设备配置参数类
     * @param deviceId 设备id
     * @return 返回修改设备配置处理结果
     */
    @Override
    public Result<String> updateDevice(String deviceId, DeviceUpdateRequest request) {
        try {
            // 更新设备信息时，只更新参数不为null的字段
            int updateRowCount = deviceMapper.updateDevice(deviceId, request);
            if (updateRowCount != 0) {
                return Result.success("设备信息更新成功");
            } else {
                return Result.error("设备信息更新失败，设备不存在或更新行数为0");
            }
        } catch (Exception e) {
            log.error("更新设备信息失败", e);
            return Result.error("更新设备信息失败：" + e.getMessage());
        }
    }

    /**
     * 获取最近七天新增设备数量
     *
     * @param userId 用户id
     * @param today  当天的日期
     * @return 最近七天新增的设备列表
     */
    @Override
    public Result<List<DeviceCountResponse>> getNewDevicesCount(Integer userId, Date today) {
        try {
            List<DeviceCountResponse> counts = deviceMapper.getNewDevicesCount(userId, today);
            return Result.success(counts);
        } catch (Exception e) {
            log.error("获取最近七天新增设备数量失败", e);
            return Result.error("获取最近七天新增设备数量失败：" + e.getMessage());
        }
    }
}

