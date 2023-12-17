<template>
  <div class="config">
    <el-card class="card_container">
      <!-- 查询条件 -->
      <el-row type="flex"
              justify="space-between">
        <el-form ref="searchForm"
                 :model="searchForm"
                 size="small"
                 class="demo-form-inline"
                 inline>
          <el-form-item label="设备id:"
                        prop="device_id">
            <el-input v-model.trim="searchForm.device_id"
                      style="width:170px;"
                      placeholder="请输入设备id" />
          </el-form-item>
          <el-form-item label="设备名称:"
                        prop="device_name">
            <el-input v-model.trim="searchForm.device_name"
                      style="width:170px;"
                      placeholder="请输入设备名称" />
          </el-form-item>
          <el-form-item label="设备类型:"
                        prop="device_type">
            <el-select v-model="searchForm.device_type"
                       style="width:170px;"
                       placeholder="请选择设备类型">
              <el-option v-for="item in deviceTypeList"
                         :key="item.id"
                         :label="item.type"
                         :value="item.id"></el-option>
            </el-select>
          </el-form-item>
          <!-- <el-form-item label="设备状态:"
                        prop="is_active">
            <el-select v-model="searchForm.is_active"
                       style="width:170px;"
                       placeholder="请选择设备状态">
              <el-option v-for="item in deviceStateList"
                         :key="item.id"
                         :label="item.state"
                         :value="item.id"></el-option>
            </el-select>
          </el-form-item> -->
          <el-form-item style="margin-left: 30px;">
            <el-button type="primary"
                       icon="el-icon-search"
                       size="small"
                       style="margin-right: 10px;"
                       @click="handleSearch()">查询
            </el-button>
            <el-button size="small"
                       icon="el-icon-refresh-right"
                       @click="handleClear()">重置</el-button>
          </el-form-item>
        </el-form>
      </el-row>
      <el-row class="rowSpace">
        <el-col :span="4">
          <el-button type="primary"
                     size="small"
                     @click.native="addDeviceDialog = true">新增设备</el-button></el-col>
        <el-col :span="8">
          <div>
            <el-radio-group size="small"
                            @change="changeRadio($event)"
                            v-model="searchForm.is_active">
              <el-radio-button label=null>全部</el-radio-button>
              <el-radio-button label=0>离线</el-radio-button>
              <el-radio-button label=1>活跃</el-radio-button>
            </el-radio-group>
          </div>
        </el-col>
      </el-row>
      <!-- 新增设备dialog -->
      <el-dialog title="新增设备"
                 :visible.sync="addDeviceDialog"
                 width="30%"
                 center
                 @open="openAddDeviceDialog()"
                 :before-close="closeAddDevice"
                 :close-on-click-modal="false">
        <el-form ref="addDeviceForm"
                 :rules="addDeviceRule"
                 class="demo-form-inline"
                 :model="addDeviceForm"
                 label-width="90px">
          <el-form-item label="设备名称:"
                        prop="device_name">
            <el-input v-model="addDeviceForm.device_name"
                      style="width: 280px;"
                      placeholder="请输入设备名称" />
          </el-form-item>
          <el-form-item label="设备描述:"
                        prop="device_description">
            <el-input v-model="addDeviceForm.device_description"
                      style="width: 280px;"
                      placeholder="请输入设备描述" />
          </el-form-item>
          <el-form-item label="设备类型:"
                        prop="device_type">
            <el-select v-model="addDeviceForm.device_type"
                       style="width: 280px;"
                       placeholder="请选择设备类型">
              <el-option v-for="item in deviceTypeList"
                         :key="item.id"
                         :label="item.type"
                         :value="item.id"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="设备状态:"
                        prop="is_active">
            <el-select v-model="addDeviceForm.is_active"
                       style="width: 280px;"
                       placeholder="请选择设备状态">
              <el-option v-for="item in deviceStateList"
                         :key="item.id"
                         :label="item.state"
                         :value="item.id"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="注册时间:"
                        prop="registration_time">
            <el-input v-model="addDeviceForm.registration_time"
                      :disabled="true"
                      style="width: 280px;" />
          </el-form-item>
          <el-form-item class="dialog_button"
                        label-width="0px">
            <el-button type="primary"
                       @click="addDevice()">保 存</el-button>
            <el-button style="margin-right: 10px;"
                       @click="closeAddDevice()">取 消</el-button>

          </el-form-item>
        </el-form>
      </el-dialog>
      <!-- 编辑设备dialog -->
      <el-dialog title="修改设备配置"
                 :visible.sync="editDeviceDialog"
                 width="30%"
                 center
                 @open="openEditDeviceDialog()"
                 :before-close="closeEditDevice"
                 :close-on-click-modal="false">
        <el-form ref="editDeviceForm"
                 :rules="editDeviceRule"
                 class="demo-form-inline"
                 :model="editDeviceForm"
                 label-width="90px">
          <el-form-item label="设备id:"
                        prop="device_id">
            <el-input v-model="editDeviceForm.device_id"
                      :disabled="true"
                      style="width: 280px;" />
          </el-form-item>
          <el-form-item label="设备名称:"
                        prop="device_name">
            <el-input v-model="editDeviceForm.device_name"
                      style="width: 280px;"
                      placeholder="请输入设备名称" />
          </el-form-item>
          <el-form-item label="设备描述:"
                        prop="device_description">
            <el-input v-model="editDeviceForm.device_description"
                      style="width: 280px;"
                      placeholder="请输入设备描述" />
          </el-form-item>
          <el-form-item label="设备类型:"
                        prop="device_type">
            <el-select v-model="editDeviceForm.device_type"
                       style="width: 280px;"
                       placeholder="请选择设备类型">
              <el-option v-for="item in deviceTypeList"
                         :key="item.id"
                         :label="item.type"
                         :value="item.id"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="设备状态:"
                        prop="is_active">
            <el-select v-model="editDeviceForm.is_active"
                       style="width: 280px;"
                       placeholder="请选择设备状态">
              <el-option v-for="item in deviceStateList"
                         :key="item.id"
                         :label="item.state"
                         :value="item.id"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="修改时间:"
                        prop="last_update">
            <el-input v-model="editDeviceForm.last_update"
                      :disabled="true"
                      style="width: 280px;" />
          </el-form-item>
          <el-form-item class="dialog_button"
                        label-width="0px">
            <el-button type="primary"
                       @click="editDevice()">保 存</el-button>
            <el-button style="margin-right: 10px;"
                       @click="closeEditDevice()">取 消</el-button>

          </el-form-item>
        </el-form>
      </el-dialog>
      <!-- 数据表 -->
      <el-table :data="tableData"
                style="width: 100%"
                :row-class-name="tableRowClassName"
                border>
        <el-table-column prop="index"
                         label="序号"
                         width="60">
        </el-table-column>
        <el-table-column prop="device_id"
                         label="设备id">
        </el-table-column>
        <el-table-column prop="device_name"
                         label="设备名称"
                         show-overflow-tooltip>
        </el-table-column>
        <el-table-column prop="device_type"
                         label="设备类型">
          <template slot-scope="scope">
            <div>
              {{
        {
          1: "智能家居",
          2: "生命安全设备",
          3: "车载设备",
          4: "可穿戴设备",
          5: "工业智能网关",
		  6:"其他"
        }[scope.row.device_type]
      }}
            </div>
          </template>
        </el-table-column>
        <el-table-column prop="device_description"
                         label="设备描述"
                         show-overflow-tooltip>
        </el-table-column>
        <el-table-column prop="registration_time"
                         label="创建日期">
        </el-table-column>
        <el-table-column prop="is_active"
                         label="设备状态">
          <template slot-scope="scope">
            <div>
              {{ scope.row.is_active == 0 ? "离线" : "活跃" }}
            </div>
          </template>
        </el-table-column>
        <el-table-column label="操作"
                         width="250">
          <template slot-scope="scope">
            <el-button type="success"
                       size="small"
                       @click="editDeviceDialogHandler(scope.row.device_id) ">编辑</el-button>
            <el-button type="danger"
                       size="small"
                       @click="deleteDevice(scope.row.device_id)">删除</el-button>

            <el-button type="primary"
                       size="small"
                       @click="openDetail(scope.row.device_id)">详情</el-button>
          </template>
        </el-table-column>
      </el-table>
      <!-- 分页 -->
      <el-pagination @size-change="handleSizeChange"
                     @current-change="handleCurrentChange"
                     :current-page="searchForm.current"
                     :page-sizes="[5, 10, 15, 20,25]"
                     :page-size="searchForm.size"
                     layout="total, sizes, prev, pager, next, jumper"
                     :total="total">
      </el-pagination>
    </el-card>
  </div>
</template>

<script>
export default {
  components: {},
  props: {},
  data () {
    return {
      // 用于控制新增设备dialog的打开与否
      addDeviceDialog: false,
      // 用于控制编辑设备dialog的打开与否
      editDeviceDialog: false,
      // 查询表单的信息
      searchForm: {
        // 当前页
        current: 1,
        // 每页显示的条数
        size: 10,
        device_id: "",
        device_name: "",
        device_type: null,
        is_active: null
      },
      // 设备的数据表信息  1:智能家居 2:生命安全设备 3:车载设备 4:可穿戴设备 5:工业智能网关 6:其他
      tableData: [
        {
          index: 1,
          device_id: 'd0001',
          device_name: '测试1',
          device_type: 1,
          device_description: '一个智能家居设备',
          registration_time: '2023/10/22 12:00:00',
          is_active: 1,
        },
        {
          index: 2,
          device_id: 'd0002',
          device_name: '测试2',
          device_type: 4,
          device_description: '一个可穿戴设备',
          registration_time: '2023/10/22 13:00:00',
          is_active: 0,
        },
      ],
      // 设备总数
      total: 2,
      // 查询模块里的设备类型列表
      deviceTypeList: [
        { id: 1, type: "智能家居" },
        { id: 2, type: "生命安全设备" },
        { id: 3, type: "车载设备" },
        { id: 4, type: "可穿戴设备" },
        { id: 5, type: "工业智能网关" },
        { id: 6, type: "其他" }
      ],
      // 查询模块的设备状态列表
      deviceStateList: [
        { id: 0, state: "离线" },
        { id: 1, state: "活跃" }
      ],
      // 新增设备表单
      addDeviceForm: {
        device_name: "",
        device_type: null,
        device_description: "",
        is_active: null,
        registration_time: ""
      },
      // 新增设备表单校验规则
      addDeviceRule: {
        device_name: [
          { required: true, message: "请输入设备名称", trigger: "blur" },
        ],
        device_type: [
          { required: true, message: "请选择设备类型", trigger: "blur" }
        ],
        device_description: [
          { required: true, message: "请输入设备描述", trigger: "blur" },
        ],
        is_active: [
          { required: true, message: "请选择设备状态", trigger: "blur" },
        ],
      },
      // 编辑设备表单
      editDeviceForm: {
        device_id: "",
        device_name: "",
        device_type: null,
        device_description: "",
        is_active: null,
        last_update: ""
      },
      // 新增设备表单校验规则
      editDeviceRule: {

      },

    };
  },
  watch: {},
  computed: {},
  methods: {
    // 控制数据表某一行的颜色
    tableRowClassName ({ row, rowIndex }) {
      if (rowIndex === 1) {
        return 'warning-row';
      } else if (rowIndex === 3) {
        return 'success-row';
      }
      return '';
    },
    // 切换每页显示条数
    handleSizeChange (val) {
      this.searchForm.size = val;
      this.searchForm.current = 1;
      this.getPageList();
    },
    // 跳转至某一页
    handleCurrentChange (val) {
      this.searchForm.current = val;
      this.getPageList();
    },
    // 路由跳转
    changeView (url, queryParams) {
      console.log(url);
      this.$router.push({
        path: url,
        query: queryParams,
      });
    },
    // 切换tab
    changeRadio (value) {
      this.searchForm.is_active = value;
      this.getPageList();
    },
    // 删除设备
    deleteDevice (device_id) {

    },
    // 查看设备详情
    openDetail (device_id) {

    },
    // 获取设备信息（在初始化时调用）
    async getPageList () {
      // TODO: 请求接口还需要修改
      const result = await this.$axios.get('/device/user/{user_id}', {
        params: this.searchForm,
      });
      if (result.data.success) {
        this.tableData = result.data.data.records;
        this.total = result.data.data.total;
      } else {
        this.$message.error(result.data.message);
      }
    },
    // 查询模块的查询事件
    handleSearch () {
      this.searchForm.current = 1;
      this.getPageList();
    },
    // 查询模块的重置事件
    handleClear () {
      // resetFields()是组件自带的重置函数
      this.$refs["searchForm"].resetFields();
      this.getPageList();
    },
    // 新增设备
    addDevice () {
      this.$refs.addDeviceForm.validate(async (valid) => {
        if (!valid) return;
        // 请求接口  TODO:请求参数还需要加上user_id和注册日期
        const { data: res } = await this.$axios.post(
          "/device/add",
          this.addDeviceForm
        );
        if (res.success) {
          this.$message.success("设备新增成功！");
          alert("新增设备成功！");
        } else {
          return this.$message.error(res.msg);
        }
      });
    },
    // 取消新增设备（重置表单）
    closeAddDevice () {
      this.addDeviceDialog = false;
      // Tips: resetFields 方法只能重置带有 props 属性的元素
      this.$refs.addDeviceForm.resetFields();
    },
    // 新增设备dialog打开的回调函数
    openAddDeviceDialog () {
      this.addDeviceForm.registration_time = this.$moment().format('YYYY-MM-DD HH:mm:ss')
    },
    // 修改设备配置
    editDevice () {
      this.$refs.editDeviceForm.validate(async (valid) => {
        if (!valid) return;
        // 请求接口  TODO:请求参数还需要加上user_id和注册日期
        const { data: res } = await this.$axios.post(
          "/device/add",
          this.editDeviceForm
        );
        if (res.success) {
          this.$message.success("设备新增成功！");
          alert("新增设备成功！");
        } else {
          return this.$message.error(res.msg);
        }
      });
    },
    // 编辑设备dialog打开的回调函数
    openEditDeviceDialog () {
      this.editDeviceForm.last_update = this.$moment().format('YYYY-MM-DD HH:mm:ss')
    },
    // 取消编辑设备（重置表单）
    closeEditDevice () {
      this.editDeviceDialog = false;
      // Tips: resetFields 方法只能重置带有 props 属性的元素
      this.$refs.editDeviceForm.resetFields();
    },
    // 
    editDeviceDialogHandler (id) {
      this.editDeviceDialog = true;
      this.editDeviceForm.device_id = id
      console.log(id);
    }


  },
  created () {
    // TODO:初始化数据列表  
    // this.getPageList();
  },
  mounted () { }
};
</script>
<style >
.config {
  margin: 20px 0;
}

.el-table .warning-row {
  background: oldlace;
}

.el-table .success-row {
  background: #f0f9eb;
}

.el-table {
  margin: 20px 0px;
}

.dialog_button {
  margin-top: 30px;
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>