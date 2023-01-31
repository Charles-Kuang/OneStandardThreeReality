<template>
    <div class="app-container">
    <el-row :gutter="20">
        <el-card class="box-card">
        <div slot="header" class="clearfix">
            <span>个人信息</span>
        </div>
        <div>
            <el-col :span="6" :xs="24">
                <div class="text-center">
                <userAvatar :user="user" />
                </div>
            </el-col>
            <el-col :span="16" :xs="24">
                <ul class="list-group list-group-striped">
                <li class="list-group-item">
                    <svg-icon icon-class="user" />身份:
                    <div class="pull-right" >{{ user.userId }}</div>
                    <div class="pull-right" ><dict-tag :options="dict.type.std_user_type" :value=user.userType /></div>
                
                    
                </li>
                <li class="list-group-item">
                    <svg-icon icon-class="phone" />姓名:
                    <div class="pull-right">{{ user.accountPerson.personName }}</div>
                </li>
                </ul>
            </el-col>
            <el-col :span="2" xs="24" >
                <el-button round="true" size="medium" @click="toSetting">
                    设置
                </el-button>
            </el-col>
        </div>
        </el-card>
    </el-row>
    <el-row :gutter="18" :xs="24">
        <el-form ref="form" :model="task" :rules="rules" label-width="200px">
            <el-form-item>
                <el-button type="warning" size="medium" style="margin-right:100px;" @click.native="toTask(1)">
                    待审核的: <div class="pull-right">{{ taskCount[0] }}</div>
                </el-button>
            
                <el-button type="info" size="medium" style="margin-right:100px;" @click.native="toTask(2)">
                    未通过的: <div class="pull-right">{{ taskCount[1] }}</div>
                </el-button>
            
                <el-button type="danger" size="medium" style="margin-right:100px;" @click.native="toTask(3)">
                    待完善的: <div class="pull-right">{{ taskCount[2] }}</div> 
                </el-button>

                <el-button type="success" size="medium" style="margin-right:100px;" @click.native="toTask(4)">
                    已完成的: <div class="pull-right">{{ taskCount[3] }}</div> 
                </el-button>
            </el-form-item>
        </el-form>
    </el-row>
    <el-row :gutter="20" :xs="24">
        <ul class="list-group list-group-striped">
            <li class="list-group-item" style="margin-bottom:20px;">
                <el-button @click="points">我的积分</el-button>
                <div class="pull-right">{{ user.accountPoints }}</div>
            </li>
            <li class="list-group-item" style="margin-bottom:20px;">
                <el-button @click="exchange">积分兑换</el-button>
            </li>
            <li class="list-group-item" style="margin-bottom:20px;">
                <el-button @click="exchanged">已兑礼品</el-button>
            </li>
        </ul>
    </el-row>
    </div>
  </template>
  
  <script>
  import userAvatar from "./profile/userAvatar";
  import userInfo from "./profile/userInfo";
  import resetPwd from "./profile/resetPwd";
  import { getUserProfile } from "@/api/system/user";
  
  export default {
    name: "Profile",
    components: { userAvatar, userInfo, resetPwd },
    dicts: ['std_user_type'],
    data() {
      return {
        user: {},
        roleGroup: {},
        postGroup: {},
        taskGroup: {},
        activeTab: "userinfo",
        taskCount: [],
      };
    },
    created() {
      this.getUser();
    },
    methods: {
      getUser() {
        getUserProfile().then(response => {
          this.user = response.data;
          this.roleGroup = response.roleGroup;
          this.postGroup = response.postGroup;
          this.taskGroup = response.taskGroup;
          this.taskCount = response.taskCount;
        });
      },
      toTask(status) {
        this.$router.push ({
            path: '/task',
            query: {
                taskStatus: status
            }
        })
      },
      toSetting() {
        this.$router.push ({
            path: '/person/setting'
        })
      }
    }
  };
  </script>
  