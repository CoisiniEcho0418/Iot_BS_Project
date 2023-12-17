package com.hwj.bs_backend.controller;

import com.hwj.bs_backend.param.DeviceAddRequest;
import com.hwj.bs_backend.param.DeviceCountResponse;
import com.hwj.bs_backend.param.DeviceListResponse;
import com.hwj.bs_backend.param.DeviceUpdateRequest;
import com.hwj.bs_backend.pojo.Device;
import com.hwj.bs_backend.pojo.Result;
import com.hwj.bs_backend.service.DeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin // 允许跨域
@Slf4j
@RestController
@RequestMapping("device")
public class DeviceController {
    @Autowired
    DeviceService deviceService;

    /**
     * 获取某个设备信息
     *
     * @param deviceId 设备ID
     * @return 返回设备信息
     */
    @GetMapping("/{device_id}")
    // @TokenRequired // 添加 TokenRequired 注解，表示需要 token 鉴权
    public Result<Device> getDeviceInfo(
            @PathVariable("device_id") String deviceId
    ) {
        return deviceService.getDeviceInfo(deviceId);
    }

    /**
     * 获取用户设备列表
     *
     * @param userId 用户的id
     * @return Result 包含设备列表的结果
     */
    @GetMapping("/user/{user_id}")
    // @TokenRequired // 添加 TokenRequired 注解，表示需要 token 鉴权
    public Result<List<DeviceListResponse>> getUserDevices(
            @PathVariable("user_id") Integer userId) {
        return deviceService.getUserDevices(userId);
    }

    /**
     * 新增设备
     *
     * @param request 修改设备配置参数类
     * @return 返回新增设备处理结果
     */
    @PostMapping("/add")
    // @TokenRequired // 添加 TokenRequired 注解，表示需要 token 鉴权
    public Result<String> addDevice(@RequestBody DeviceAddRequest request) {
        System.out.println(request);
        return deviceService.addDevice(request);
    }

    /**
     * 修改设备配置
     *
     * @param request  修改设备配置参数类
     * @param deviceId 设备id
     * @return 返回修改设备配置处理结果
     */
    @PutMapping("/update/{device_id}")
    // @TokenRequired // 添加 TokenRequired 注解，表示需要 token 鉴权
    public Result<String> updateDevice(
            @PathVariable("device_id") String deviceId,
            @RequestBody DeviceUpdateRequest request) {
        return deviceService.updateDevice(deviceId, request);
    }

    /**
     * 获取最近七天新增设备数量
     *
     * @param userId 用户id
     * @param today  当天的日期
     * @return 最近七天新增的设备列表
     */
    @GetMapping("/new-devices-count")
    public Result<List<DeviceCountResponse>> getNewDevicesCount(
            @RequestParam("user_id") Integer userId,
            @RequestParam("today") @DateTimeFormat(pattern = "yyyy-MM-dd") Date today) {
        return deviceService.getNewDevicesCount(userId, today);
    }

}
