package com.hwj.bs_backend.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceUpdateRequest {
    @JsonProperty("device_name")
    private String deviceName;

    @JsonProperty("device_type")
    private int deviceType;

    @JsonProperty("device_description")
    private String deviceDescription;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonProperty("last_update")
    private Date lastUpdate;

    @JsonProperty("is_active")
    private Boolean isActive;
}

