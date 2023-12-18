<template>
  <div class="message">
    <el-card>
      <!-- 头部选择栏 -->
      <div class="select_header">
        <div class="tips">
          请选则您要查看的设备:
        </div>
        <el-select v-model="value"
                   placeholder="请选择设备"
                   class="mySelect">
          <el-option v-for="item in deviceList"
                     :key="item.id"
                     :label="item.name"
                     :value="item.name">
          </el-option>
        </el-select>
        <el-button type="primary"
                   style="margin-left: 30px;"
                   @click="selectDevice()">确 定</el-button>
      </div>
      <!-- 数据表 -->
      <el-table :data="messageData"
                style="width: 100%;"
                :row-class-name="tableRowClassName"
                border>
        <el-table-column prop="index"
                         label="序号"
                         width="60">
        </el-table-column>
        <el-table-column prop="message_type"
                         width="140"
                         label="信息类别">
          <template slot-scope="scope">
            <el-tag :type="scope.row.message_type === 0 ? 'success' : 'danger'"
                    disable-transitions>{{scope.row.message_type === 0 ? '正常' : '异常'}}</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="message_content"
                         label="信息内容"
                         show-overflow-tooltip>
        </el-table-column>
        <el-table-column prop="latitude"
                         label="经度">
        </el-table-column>
        <el-table-column prop="longitude"
                         label="纬度"
                         show-overflow-tooltip>
        </el-table-column>
        <el-table-column prop="message_time"
                         label="时间">
        </el-table-column>

      </el-table>
      <!-- 分页 -->
      <el-pagination @current-change="handleCurrentChange"
                     :current-page="current"
                     :page-size="5"
                     layout="total, prev, pager, next, jumper"
                     style="margin-top: 20px;"
                     :total="total">
      </el-pagination>
      <!-- 百度地图 -->
      <baidu-map class="map"
                 :center="map.center"
                 inertial-dragging
                 :scroll-wheel-zoom="true"
                 :zoom="map.zoom"
                 @moving="syncCenterAndZoom"
                 @moveend="syncCenterAndZoom"
                 @zoomend="syncCenterAndZoom">
        <!-- 定位控件   anchor="BMAP_ANCHOR_BOTTOM_RIGHT"代表放在右下角 -->
        <bm-geolocation anchor="BMAP_ANCHOR_BOTTOM_LEFT"
                        :showAddressBar="true"
                        :autoLocation="true"></bm-geolocation>
        <!-- 缩放控件   anchor代表控件停靠位置   anchor="BMAP_ANCHOR_TOP_RIGHT"代表放在右上角-->
        <bm-navigation anchor="BMAP_ANCHOR_TOP_RIGHT"></bm-navigation>
        <!-- 城市列表 -->
        <bm-city-list anchor="BMAP_ANCHOR_TOP_LEFT"></bm-city-list>
        <!-- 缩略图 -->
        <bm-overview-map anchor="BMAP_ANCHOR_BOTTOM_RIGHT"
                         :isOpen="true"></bm-overview-map>
        <!-- 折线 -->
        <bm-polyline :path="polylinePath"
                     stroke-color="blue"
                     :stroke-opacity="0.5"
                     :stroke-weight="2"></bm-polyline>
      </baidu-map>
      <!-- 显示&编辑经纬度和缩放等级 -->
      <div class="map_editor">
        <div class="first_row">
          <div>经 度</div>
          <div>纬 度</div>
          <div>缩 放</div>
        </div>
        <div class="second_row">
          <input v-model.number="map.center.lng"
                 class="my_input">
          <input v-model.number="map.center.lat"
                 class="my_input">
          <input v-model.number="map.zoom"
                 class="my_input">
        </div>
      </div>

    </el-card>

  </div>
</template>

<script>
export default {
  components: {},
  props: {},
  data () {
    return {
      // 地图信息
      map: {
        center: {
          lng: 120.120794,
          lat: 30.259157
        },
        zoom: 10,
      },
      // 可查看的设备的列表  TODO: 之后要改为vuex来赋值
      deviceList: [
        { id: "d0001", name: "智能家居" },
        { id: "d0002", name: "可穿戴设备" }
      ],
      // 设备信息列表
      messageData: [
        {
          index: 1,
          message_type: 0,
          message_content: "device data message",
          latitude: 30.915,
          longitude: 119.804,
          message_time: "2023-10-23 20:12:00"
        },
        {
          index: 2,
          message_type: 1,
          message_content: "device data message",
          latitude: 29.920,
          longitude: 120.405,
          message_time: "2023-10-23 20:13:00"
        },
        {
          index: 3,
          message_type: 1,
          message_content: "device data message",
          latitude: 30.407445,
          longitude: 120.223493,
          message_time: "2023-10-23 22:13:00"
        }
      ],
      // 信息总条数
      total: 3,
      // 当前页
      current: 1,
      // 绘制折线的点的信息
      polylinePath: [
        { lng: 119.804, lat: 30.915 },
        { lng: 120.405, lat: 29.920 },
        { lng: 120.223493, lat: 30.407445 }
      ],

    };
  },
  watch: {},
  computed: {},
  methods: {
    // 选取对应的设备消息
    selectDevice () {

    },
    // 处理分页
    handleCurrentChange () {

    },
    // 移动地图回调处理函数
    syncCenterAndZoom (e) {
      const { lng, lat } = e.target.getCenter()
      this.map.center.lng = lng
      this.map.center.lat = lat
      this.map.zoom = e.target.getZoom()
    }

  },
  created () { },
  mounted () { }
};
</script>
<style scoped>
.message {
  margin: 20px 0;
}
.select_header {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  margin-top: 20px;
  margin-bottom: 30px;
  height: 30px;
}
.tips {
  font-weight: bold;
  letter-spacing: 1px;
  height: 30px;
  line-height: 30px;
  text-align: center;
  margin-right: 20px;
}
.mySelect {
  display: flex;
  align-items: center;
}
.map {
  width: 100%;
  height: 500px;
  margin-top: 20px;
  padding: 10px 10px;
  margin-right: 5px;
  padding: 5px 5px;
}
.map_editor {
  margin-top: 5px;
  padding: 5px 5px;
}
.first_row {
  display: flex;
  justify-content: space-around;
  font-weight: bold;
  font-size: 16px;
  text-align: center;
  align-items: center;
  margin-bottom: 10px;
  border-bottom: 2px solid #ebeef5;
  height: 40px;
  line-height: 40px;
}
.second_row {
  display: flex;
  justify-content: space-around;
  font-weight: bold;
  font-size: 16px;
  height: 40px;
  line-height: 40px;
}
input {
  border: none; /* 移除默认边框 */
  outline: none; /* 取消默认的轮廓线 */
  font-weight: bold;
  font-size: 15px;
  width: 27%;
  border-bottom: 1px solid #dbdbdb;
  border-top: 0px;
  border-left: 0px;
  border-right: 0px;
}
input:focus {
  font-weight: bold;
  font-size: 15px;
  width: 27%;
  border-bottom: 1px solid #03a9f4;
}
input:hover {
  font-weight: bold;
  font-size: 15px;
  width: 27%;
  border-bottom: 1px solid #03a9f4;
  border-top: 0px;
  border-left: 0px;
  border-right: 0px;
}
</style>