<template>
    <div class="app-container">
      <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
        <!--<el-form-item label="省" prop="provinceCode">
            <el-select v-model="queryParams.provinceCode" placeholder="请选择省份" @change="queryProvinceSelected(queryParams.provinceCode)">
                <el-option
                v-for="singleProvince in provinceList"
                :key="singleProvince.code"
                :label="singleProvince.name"
                :value="singleProvince.code"/>
            </el-select>
          </el-form-item>
          <el-form-item label="市" prop="cityCode">
          <el-select v-model="queryParams.cityCode" placeholder="请选择市" @change="queryCitySelected(queryParams.cityCode)">
            <el-option
                v-for="singleCity in cityList"
                :key="singleCity.code"
                :label="singleCity.name"
                :value="singleCity.code"/>
            </el-select>
          </el-form-item>
          <el-form-item label="区" prop="districtCode">
          <el-select v-model="queryParams.districtCode" placeholder="请选择区" @change="queryDistrictSelected(queryParams.districtCode)">
            <el-option
                v-for="singleDistrict in districtList"
                :key="singleDistrict.code"
                :label="singleDistrict.name"
                :value="singleDistrict.code"/>
            </el-select>
          </el-form-item>
          <el-form-item label="街道" prop="streetCode">
          <el-select v-model="queryParams.streetCode" placeholder="请选择街道" @change="queryStreetSelected(queryParams.streetCode)">
            <el-option
                v-for="singleStreet in streetList"
                :key="singleStreet.code"
                :label="singleStreet.name"
                :value="singleStreet.code"/>
            </el-select>
          </el-form-item>
          <el-form-item label="居委会" prop="positionCode">
          <el-select v-model="queryParams.positionCode" placeholder="请选择居委会" @change="queryPositionSelected(queryParams.positionCode)">
            <el-option
                v-for="singlePosition in positionList"
                :key="singlePosition.code"
                :label="singlePosition.name"
                :value="singlePosition.code"/>
            </el-select>
          </el-form-item>
        <el-form-item label="单位办公楼产权证号" prop="stdHouseId">
          <el-input
            v-model="queryParams.stdHouseId"
            placeholder="请输入单位办公楼产权证号"
            clearable
            @keyup.enter.native="handleQuery"
            oninput="value=value.replace(/[^\d]/g,'')"
          />
        </el-form-item>-->
        <el-form-item label="营业执照" prop="deptId">
          <el-input
            v-model="queryParams.deptId"
            placeholder="请输入单位营业执照号码"
            clearable
            @keyup.enter.native="handleQuery"
            oninput="value=value.replace(/[^\d]/g,'')"
          />
        </el-form-item>
        <el-form-item label="单位类别" prop="deptTypes">
            <el-select v-model="queryParams.deptTypes" multiple placeholder="请选择单位类别">
              <el-option
                v-for="dict in dict.type.dept_type"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              ></el-option>
            </el-select>
          </el-form-item>
        <el-form-item label="单位性质" prop="deptNature">
          <el-select v-model="queryParams.deptNature" placeholder="请选择单位性质" clearable>
            <el-option
              v-for="dict in dict.type.dept_nature"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="单位名称" prop="deptName">
          <el-input
            v-model="queryParams.deptName"
            placeholder="请输入单位名称"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <!--<el-form-item label="所属派出所" prop="police">
          <el-input
            v-model="queryParams.police"
            placeholder="请输入所属派出所"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>-->
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        </el-form-item>
      </el-form>
  
      <el-row :gutter="10" class="mb8">
        <el-col :span="1.5">
          <el-button
            type="primary"
            plain
            icon="el-icon-plus"
            size="mini"
            @click="handleAdd"
            v-hasPermi="['system:dept:add']"
          >新增</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="success"
            plain
            icon="el-icon-edit"
            size="mini"
            :disabled="single"
            @click="handleUpdate"
            v-hasPermi="['system:dept:edit']"
          >修改</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="danger"
            plain
            icon="el-icon-delete"
            size="mini"
            :disabled="multiple"
            @click="handleDelete"
            v-hasPermi="['system:dept:remove']"
          >删除</el-button>
          
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="warning"
            plain
            icon="el-icon-download"
            size="mini"
            @click="handleExport"
            v-hasPermi="['system:dept:export']"
          >导出</el-button>
        </el-col>
        <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
      </el-row>
  
      <el-table v-loading="loading" :data="deptList" @row-click="getDetail" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="单位营业执照号码" align="center" prop="deptId" />
        <el-table-column label="标准地址" align="center" prop="stdAddress" width="200" :show-overflow-tooltip='true' />
        <el-table-column label="单位办公楼产权证号" align="center" prop="stdHouseId" />
        <el-table-column label="单位性质" align="center" prop="deptNature">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.dept_nature" :value="scope.row.deptNature"/>
          </template>
        </el-table-column>
        <el-table-column label="单位名称" align="center" prop="deptName" />
        <el-table-column label="所属派出所" align="center" prop="police" />
        <el-table-column label="单位类别" align="center" :show-overflow-tooltip='true' >
          <template slot-scope="scope">
            <div v-for=" (deptTypeDetails, index) in scope.row.deptTypeDetails" :key="index">
            {{ dict.type.dept_type[deptTypeDetails.deptType].label }}
            </div>
          </template>
        </el-table-column>
        <el-table-column label="法定代表人证件类别" align="center" prop="legalRepresentativeIdType">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.host_id_type" :value="scope.row.legalRepresentativeIdType"/>
          </template>
        </el-table-column>
        <el-table-column label="法定代表人证件号" align="center" prop="legalRepresentativeId" />
        <el-table-column label="法定代表人姓名" align="center" prop="legalRepresentativeName" />
        <el-table-column label="法定代表人联系方式" align="center" prop="legalRepresentativeContacts" />
        <el-table-column label="单位负责人证件类型" align="center" prop="leaderIdType">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.host_id_type" :value="scope.row.leaderIdType"/>
          </template>
        </el-table-column>
        <el-table-column label="单位负责人证件号" align="center" prop="leaderId" />
        <el-table-column label="单位负责人姓名" align="center" prop="leaderName" />
        <el-table-column label="单位负责人联系方式" align="center" prop="leaderContacts" />
        <el-table-column label="治安负责人证件类别" align="center" prop="securityRepresentativeIdType">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.host_id_type" :value="scope.row.securityRepresentativeIdType"/>
          </template>
        </el-table-column>
        <el-table-column label="治安负责人证件号" align="center" prop="securityRepresentativeId" />
        <el-table-column label="治安负责人姓名" align="center" prop="securityRepresentativeName" />
        <el-table-column label="治安负责人联系方式" align="center" prop="securityRepresentativeContacts" />
        <el-table-column label="消防负责人姓名" align="center" prop="fireControllerName" />
        <el-table-column label="消防负责人联系方式" align="center" prop="fireControllerContacts" />
        <el-table-column label="从业人员人数" align="center" prop="peopleNum" />
        <el-table-column label="操作" align="center" width="150">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click.native.stop="handleEmployee(scope.row)"
              v-hasPermi="['system:dept:edit']"
            >查看该单位员工</el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="involvedCheck(scope.row)"
              v-hasPermi="['system:dept:edit']"
            >查看单位涉案</el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click.native.stop="handleUpdate(scope.row)"
              v-hasPermi="['system:dept:edit']"
            >修改</el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click.native.stop="handleDelete(scope.row)"
              v-hasPermi="['system:dept:remove']"
            >删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      
      <pagination
        v-show="total>0"
        :total="total"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="getList"
      />
  
      <!-- 添加或修改单位对话框 -->
      <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
        <el-form ref="form" :model="form" :rules="rules" label-width="80px">
          <el-form-item label="单位单位营业执照号码" prop="deptId" v-if="mode === 'add'">
            <el-input v-model.number="form.deptId" placeholder="请输入单位营业执照号码" />
          </el-form-item>
          <el-form-item label="单位办公楼产权证号" prop="stdHouseId">
            <el-input v-model.number="form.stdHouseId" placeholder="请输入单位办公楼产权证号" />
          </el-form-item>
          <el-form-item label="单位性质" prop="deptNature">
            <el-select v-model="form.deptNature" placeholder="请选择单位性质">
              <el-option
                v-for="dict in dict.type.dept_nature"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"       
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="单位名称" prop="deptName">
            <el-input v-model="form.deptName" placeholder="请输入单位名称" />
          </el-form-item>
          <el-form-item label="所属派出所" prop="police">
            <el-input v-model="form.police" placeholder="请输入所属派出所" />
          </el-form-item>
          <el-form-item label="单位类别">
            <el-select v-model="form.deptTypes" multiple placeholder="请选择单位类别">
              <el-option
                v-for="dict in dict.type.dept_type"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="法定代表人证件类别" prop="legalRepresentativeIdType">
            <el-select v-model="form.legalRepresentativeIdType" placeholder="请选择法定代表人证件类别">
              <el-option
                v-for="dict in dict.type.host_id_type"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="法定代表人证件号" prop="legalRepresentativeId">
            <el-input v-model="form.legalRepresentativeId" placeholder="请输入法定代表人证件号" />
          </el-form-item>
          <el-form-item label="法定代表人姓名" prop="legalRepresentativeName">
            <el-input v-model="form.legalRepresentativeName" placeholder="请输入法定代表人姓名" />
          </el-form-item>
          <el-form-item label="法定代表人联系方式" prop="legalRepresentativeContacts">
            <el-input v-model="form.legalRepresentativeContacts" placeholder="请输入法定代表人联系方式" />
          </el-form-item>
          <el-form-item label="单位负责人证件类型" prop="leaderIdType">
            <el-select v-model="form.leaderIdType" placeholder="请选择单位负责人证件类型">
              <el-option
                v-for="dict in dict.type.host_id_type"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="单位负责人证件号" prop="leaderId">
            <el-input v-model="form.leaderId" placeholder="请输入单位负责人证件号" />
          </el-form-item>
          <el-form-item label="单位负责人姓名" prop="leaderName">
            <el-input v-model="form.leaderName" placeholder="请输入单位负责人姓名" />
          </el-form-item>
          <el-form-item label="单位负责人联系方式" prop="leaderContacts">
            <el-input v-model="form.leaderContacts" placeholder="请输入单位负责人联系方式" />
          </el-form-item>
          <el-form-item label="治安负责人证件类别" prop="securityRepresentativeIdType">
            <el-select v-model="form.securityRepresentativeIdType" placeholder="请选择治安负责人证件类别">
              <el-option
                v-for="dict in dict.type.host_id_type"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="治安负责人证件号" prop="securityRepresentativeId">
            <el-input v-model="form.securityRepresentativeId" placeholder="请输入治安负责人证件号" />
          </el-form-item>
          <el-form-item label="治安负责人姓名" prop="securityRepresentativeName">
            <el-input v-model="form.securityRepresentativeName" placeholder="请输入治安负责人姓名" />
          </el-form-item>
          <el-form-item label="治安负责人联系方式" prop="securityRepresentativeContacts">
            <el-input v-model="form.securityRepresentativeContacts" placeholder="请输入治安负责人联系方式" />
          </el-form-item>
          <el-form-item label="消防负责人姓名" prop="fireControllerName">
            <el-input v-model="form.fireControllerName" placeholder="请输入消防负责人姓名" />
          </el-form-item>
          <el-form-item label="消防负责人联系方式" prop="fireControllerContacts">
            <el-input v-model="form.fireControllerContacts" placeholder="请输入消防负责人联系方式" />
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </el-dialog>
    </div>
  </template>
  
  <script>
  import { listDept, getDept, delDept, addDept, updateDept } from "@/api/standard/dept";
  import { listRegion } from "@/api/standard/region";

  export default {
    name: "Dept",
    dicts: ['dept_nature', 'host_id_type', 'dept_type'],
    data() {
      return {
        // 遮罩层
        loading: true,
        // 选中数组
        ids: [],
        // 非单个禁用
        single: true,
        // 非多个禁用
        multiple: true,
        // 显示搜索条件
        showSearch: true,
        // 总条数
        total: 0,
        // 单位表格数据
        deptList: [],
        // 行政区划
        provinceList: [],
        cityList: [],
        districtList:[],
        streetList: [],
        positionList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          stdHouseId: null,
          deptNature: null,
          deptName: null,
          police: null,
          legalRepresentativeIdType: null,
          legalRepresentativeId: null,
          legalRepresentativeName: null,
          legalRepresentativeContacts: null,
          leaderIdType: null,
          leaderId: null,
          leaderName: null,
          leaderContacts: null,
          securityRepresentativeIdType: null,
          securityRepresentativeId: null,
          securityRepresentativeName: null,
          securityRepresentativeContacts: null,
          fireControllerName: null,
          fireControllerContacts: null,

          provinceCode: null,
          cityCode: null,
          districtCode: null,
          streetCode: null,
          positionCode: null
        },
        // 添加/修改模式
        mode: 'add',
        // 表单参数
        form: {},
        constrain: {
          level: undefined,
          parentCode: undefined
        },
        // 表单校验
        rules: {
          deptId: [
            { required: true, message: "单位营业执照号码不能为空", triiger: "blur", type: 'number'},
            { pattern: /^[+]?(0|([1-9]\d*))?$/, message: "请输入正确数字", trigger: "blur"}
          ],
          stdHouseId: [
            { required: true, message: "单位办公楼产权号不能为空", triiger: "blur", type: 'number'},
            { pattern: /^[+]?(0|([1-9]\d*))?$/, message: "请输入正确数字", trigger: "blur"}
          ],
          deptNature: [
            { required: true, message: "单位性质不能为空", triiger: "blur" }
          ],
          deptName: [
            { required: true, message: "单位名称不能为空", triiger: "blur" }
          ],
          legalRepresentativeIdType: [
            { required: true, message: "法定代表人证件类型不能为空", triiger: "change" }
          ],
          legalRepresentativeId: [
            { required: true, message: "法定代表人证件号不能为空", triiger: "blur" }
          ],
          legalRepresentativeName: [
            { required: true, message: "法定代表人姓名不能为空", triiger: "blur" }
          ],
          leaderIdType: [
            { required: true, message: "单位负责人证件类型不能为空", triiger: "change" }
          ],
          leaderId: [
            { required: true, message: "单位负责人证件号不能为空", triiger: "blur" }
          ],
          leaderName: [
            { required: true, message: "单位负责人姓名不能为空", triiger: "blur" }
          ],
          securityRepresentativeIdType: [
            { required: true, message: "治安负责人证件类型不能为空", triiger: "change" }
          ],
          securityRepresentativeId: [
            { required: true, message: "治安负责人证件号不能为空", triiger: "blur" }
          ],
          securityRepresentativeName: [
            { required: true, message: "治安负责人姓名不能为空", triiger: "blur" }
          ],
          securityRepresentativeContacts: [
            { required: true, message: "治安负责人联系方式不能为空", triiger: "blur" }
          ]
        }
      };
    },
    created() {
      this.getList();
      this.getProvinceList();
    },
    methods: {
      /** 查询单位列表 */
      getList() {
        this.loading = true;
        listDept(this.queryParams).then(response => {
          this.deptList = response.rows;
          this.total = response.rows.length;
          this.loading = false;
        })
      },
      
      getProvinceList() {
        this.constrain.level = 0;
        listRegion(this.constrain).then(response => {
          this.currentProvinceList = response.data;
          this.provinceList = response.data;
        });
      },
      queryProvinceSelected(value) {
        this.queryParams.provinceIsSelected = true;
        this.queryParams.cityCode = undefined;    
        this.queryParams.cityIsSelected = false;
        this.queryParams.districtCode = undefined;
        this.queryParams.districtIsSelected = false;
        this.queryParams.streetCode = undefined;
        this.queryParams.streetIsSelected = false;
        this.queryParams.positionCode = undefined;
        let constrain = {};
        constrain.parentCode = value;
        listRegion(constrain).then(response => {
          this.cityList = response.data;
        })
        this.$forceUpdate();
      },
      queryCitySelected(value) {
        this.queryParams.cityIsSelected = true;
        this.queryParams.districtCode = undefined;
        this.queryParams.districtIsSelected = false;
        this.queryParams.streetCode = undefined;
        this.queryParams.streetIsSelected = false;
        this.queryParams.positionCode = undefined;
        let constrain = {};
        constrain.parentCode = value;
        listRegion(constrain).then(response => {
          this.districtList = response.data;
        })
        this.$forceUpdate();
      },
      queryDistrictSelected(value) {
        this.queryParams.districtIsSelected = true;
        this.queryParams.streetCode = undefined;
        this.queryParams.streetIsSelected = false;
        this.queryParams.positionCode = undefined;
        let constrain = {};
        constrain.parentCode = value;
        listRegion(constrain).then(response => {
          this.streetList = response.data;
        })
        this.$forceUpdate();
      },
      queryStreetSelected(value) {
        this.queryParams.streetIsSelected = true;
        this.queryParams.positionCode = undefined;
        let constrain = {};
        constrain.parentCode = value;
        listRegion(constrain).then(response => {
          this.positionList = response.data;
        })
        this.$forceUpdate();
      },
      queryPositionSelected(value) {
        this.queryParams.positionIsSelected = true;
        this.$forceUpdate();
      },

      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      
      // 表单重置
      reset() {
        this.form = {
          deptId: null,
          positionId: null,
          stdHouseId: null,
          deptNature: null,
          deptName: null,
          police: null,
          delFlag: null,
          legalRepresentativeIdType: null,
          legalRepresentativeId: null,
          legalRepresentativeName: null,
          legalRepresentativeContacts: null,
          leaderIdType: null,
          leaderId: null,
          leaderName: null,
          leaderContacts: null,
          securityRepresentativeIdType: null,
          securityRepresentativeId: null,
          securityRepresentativeName: null,
          securityRepresentativeContacts: null,
          fireControllerName: null,
          fireControllerContacts: null,

          provinceCode: null,
          cityCode: null,
          districtCode: null,
          streetCode: null,
          positionCode: null,
          deptTypes: [],
          deptTypeDetails: []
        };
        this.cityList = [],
        this.districtList = [],
        this.streetList = [],
        this.positionList = [],
        this.resetForm("form");
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },
      /** 重置按钮操作 */
      resetQuery() {
        this.resetForm("queryForm");
        this.cityList = [],
        this.districtList = [],
        this.streetList = [],
        this.positionList = [],
        this.handleQuery();
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.deptId)
        this.single = selection.length!==1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加单位";
        this.mode = 'add';
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        this.mode = 'edit';
        const deptId = row.deptId || this.ids

        getDept(deptId).then(response => {
          this.form = response.data;
          this.$set(this.form, "deptTypes", response.deptTypes)
          this.open = true;
          this.title = "修改单位";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.mode == 'edit') {
              updateDept(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addDept(this.form).then(response => {
                this.$modal.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              });
            }
          }
        });
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        const deptIds = row.deptId || this.ids;
        this.$modal.confirm('是否确认删除单位编号为"' + deptIds + '"的数据项？').then(function() {
          return delDept(deptIds);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
      },
      /** 导出按钮操作 */
      handleExport() {
        this.download('system/dept/export', {
          ...this.queryParams
        }, `dept_${new Date().getTime()}.xlsx`)
      },
      getDetail(row) {
        const deptId = row.deptId;
        const params = { pageNum: this.queryParams.pageNum };
        this.$tab.openPage("查看ID为[" + deptId + "]的单位的各个类别", '/standard/dept/get-detail/' + deptId, params);
      },
      handleEmployee(row) {
        const deptId = row.deptId;
        const params = { pageNum: this.queryParams.pageNum };
        this.$tab.openPage("查看工作在ID为[" + deptId + "]的单位的人员", '/standard/person/get-dept/' + deptId, params);
      },
      involvedCheck(row) {
        const involvedEntityId = row.deptId;
        const involvedEntityType = '单位';
        const params = { pageNum: this.queryParams.pageNum};
        
        this.$tab.openPage("单位ID为[" + involvedEntityId + "]的涉案记录", '/standard/dept/involvedCheck/' + involvedEntityType + '/' + involvedEntityId, params);
      }
    }
  };
  </script>
  