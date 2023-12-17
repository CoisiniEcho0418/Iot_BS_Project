<template>
  <div>
    <el-card>
      <!-- 头部区域 -->
      <div class="user_header">
        <!-- 头像区域 -->
        <el-avatar :size="60"
                   src="../assets//img/avatar.jpg"
                   @error="errorHandler">
          <img src="../assets/img/avatar.jpg" />
        </el-avatar>
        <!-- 用户简略信息区域 -->
        <div class="header_info">
          <!-- 用户名 TODO:后续要改为username -->
          <div style="margin-bottom: 10px;font-weight: bold;font-size: 18px;">{{username}}</div>
          <!-- 邮箱 TODO:后续要改为email -->
          <div style="color: gray;font-size: 14px;">{{ email }}</div>
        </div>
      </div>
      <!-- 用户详情区域 -->
      <el-descriptions direction="vertical"
                       :column="2"
                       style="margin-top: 20px;"
                       border>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-user"></i>
            用户名
          </template>
          {{username}}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-mobile-phone"></i>
            绑定的手机号
          </template>
          {{phone}}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-message"></i>
            绑定的邮箱
          </template>
          {{ email }}
        </el-descriptions-item>
      </el-descriptions>
      <!-- 按钮区域 -->
      <div class="button_block">
        <el-button type="primary"
                   style="letter-spacing: 1px;text-align: center;width: 12%;margin-right: 20px;"
                   @click.native="editUserInfoDialog=true">编辑个人信息</el-button>
        <el-button type="success"
                   style="margin-right: 10px;letter-spacing: 1px;text-align: center;width: 12%;"
                   @click.native="editPasswordDialog=true">修改密码</el-button>
      </div>
      <!-- 编辑个人信息 dialog -->
      <el-dialog title="编辑个人信息"
                 :visible.sync="editUserInfoDialog"
                 width="30%"
                 center
                 :before-close="closeEditUserInfo"
                 :close-on-click-modal="false">
        <el-form ref="editUserInfoForm"
                 :rules="editUserInfoRule"
                 class="demo-form-inline"
                 :model="editUserInfoForm"
                 label-width="90px">
          <el-form-item label="新用户名:"
                        prop="new_username">
            <el-input v-model="editUserInfoForm.new_username"
                      placeholder="请输入新用户名" />
          </el-form-item>
          <el-form-item label="新邮箱:"
                        prop="new_email">
            <el-input v-model="editUserInfoForm.new_email"
                      placeholder="请输入新邮箱" />
          </el-form-item>
          <el-form-item label="新手机号:"
                        prop="new_phone">
            <el-input v-model="editUserInfoForm.new_phone"
                      placeholder="请输入新手机号" />
          </el-form-item>
          <el-form-item class="dialog_button"
                        label-width="0px">
            <el-button type="primary"
                       @click="editUserInfo()">保 存</el-button>
            <el-button style="margin-right: 10px;"
                       @click="closeEditUserInfo()">取 消</el-button>

          </el-form-item>
        </el-form>
      </el-dialog>
      <!-- 修改密码 dialog -->
      <el-dialog title="修改密码"
                 :visible.sync="editPasswordDialog"
                 width="30%"
                 center
                 :before-close="closeEditPassword"
                 :close-on-click-modal="false">
        <el-form ref="editPasswordForm"
                 :rules="editPasswordRule"
                 class="demo-form-inline"
                 :model="editPasswordForm"
                 label-width="90px">
          <el-form-item label="原密码:"
                        prop="oldPassword">
            <el-input v-model="editPasswordForm.oldPassword"
                      placeholder="请输入原密码" />
          </el-form-item>
          <el-form-item label="新密码:"
                        prop="newPassword">
            <el-input v-model="editPasswordForm.newPassword"
                      placeholder="请输入新密码" />
          </el-form-item>
          <el-form-item label="确认密码:"
                        prop="confirmPassword">
            <el-input v-model="editPasswordForm.confirmPassword"
                      placeholder="请确认密码" />
          </el-form-item>
          <el-form-item class="dialog_button"
                        label-width="0px">
            <el-button type="primary"
                       @click="editPassword()">保 存</el-button>
            <el-button style="margin-right: 10px;"
                       @click="closeEditPassword()">取 消</el-button>

          </el-form-item>
        </el-form>
      </el-dialog>

    </el-card>
  </div>
</template>

<script>
export default {
  components: {},
  props: {},
  data () {
    return {
      // 存储全局个人信息 (TODO:后续可能改为Vuex)
      username: "hwj",
      email: "test@zju.edu.cn",
      phone: "188888888888",
      // 编辑个人信息dialog的开关
      editUserInfoDialog: false,
      // 编辑个人信息的表单
      editUserInfoForm: {
        new_username: "",
        new_email: "",
        new_phone: ""
      },
      // 编辑个人信息表单的校验规则  TODO: 邮箱和手机号校验规则待更进
      editUserInfoRule: {

      },
      // 用于控制修改密码dialog的打开与否
      editPasswordDialog: false,
      editPasswordForm: {
        oldPassword: "",
        newPassword: "",
        confirmPassword: "",
      },
      editPasswordRule: {
        oldPassword: [
          { required: true, message: "请输入原密码", trigger: "blur" },
        ],
        newPassword: [
          { required: true, message: "请输入新密码", trigger: "blur" },
        ],
        confirmPassword: [
          { required: true, message: "请确认新密码", trigger: "blur" },
        ],
      },


    };
  },
  watch: {},
  computed: {},
  methods: {
    // 取消编辑个人信息
    closeEditUserInfo () {
      this.editUserInfoDialog = false;
      // resetFields 方法只能重置带有 props 属性的元素
      this.$refs.editUserInfoForm.resetFields();
    },
    // 编辑个人信息处理函数
    editUserInfo () {
      this.$refs.editUserInfoForm.validate(async (valid) => {
        if (!valid) return;
        // 请求接口
        const { data: res } = await this.$axios.post(
          "/user/editUserInfo",
          this.editUserInfoForm
        );
        if (res.success) {
          this.$message.success("密码修改成功，请重新登录！");
        } else {
          return this.$message.error(res.msg);
        }
      });
    },
    // 修改密码
    editPassword () {
      this.$refs.editPasswordForm.validate(async (valid) => {
        if (!valid) return;
        if (this.editPasswordForm.newPassword != this.editPasswordForm.confirmPassword) {
          return this.$message.error("两次密码不正确，请重新输入！");
        }
        // 请求接口
        const { data: res } = await this.$axios.post(
          "/user/updatePassword",
          this.editPasswordForm
        );
        if (res.success) {
          this.$message.success("密码修改成功，请重新登录！");
          sessionStorage.clear();
          this.$router.push("/login");
        } else {
          return this.$message.error(res.msg);
        }
      });
    },
    // 取消关闭密码
    closeEditPassword () {
      this.editPasswordDialog = false;
      // Tips: resetFields 方法只能重置带有 props 属性的元素
      this.$refs.editPasswordForm.resetFields();
    },


  },
  created () { },
  mounted () { }
};
</script>
<style scoped>
.user_header {
  padding: 10px 10px;
  padding-left: 20px;
  display: flex;
  flex-direction: row;
  border-bottom: 2px solid #ebeef5;
}
.header_info {
  margin-left: 15px;
  display: flex;
  flex-direction: column;
}
.button_block {
  display: flex;
  align-items: center;
  justify-content: center;
  margin-top: 30px;
}
.dialog_button {
  margin-top: 30px;
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>