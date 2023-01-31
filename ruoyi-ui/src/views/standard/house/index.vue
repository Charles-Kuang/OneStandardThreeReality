<template>
    <div class="app-container">
      <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="90px">
        <el-form-item label="房屋产权号" prop="houseId">
          <el-input
            v-model="queryParams.houseId"
            placeholder="请输入房屋产权号"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
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
        <el-form-item label="所属小区名" prop="residentialVillageName">
          <el-input
            v-model="queryParams.residentialVillageName"
            placeholder="请输入所属小区名"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="房屋用途" prop="houseUsage">
          <el-select v-model="queryParams.houseUsage" placeholder="请选择房屋用途" clearable>
            <el-option
              v-for="dict in dict.type.house_usage"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="户主证件类型" prop="hostIdType">
          <el-select v-model="queryParams.hostIdType" placeholder="请选择户主证件类型" clearable>
            <el-option
              v-for="dict in dict.type.host_id_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="户主证件号码" prop="hostId">
          <el-input
            v-model="queryParams.hostId"
            placeholder="请输入户主证件号码"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="房屋名称" prop="houseName">
          <el-input
            v-model="queryParams.houseName"
            placeholder="请输入房屋名称"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="房屋类型" prop="houseType">
          <el-select v-model="queryParams.houseType" placeholder="请选择房屋类型" clearable>
            <el-option
              v-for="dict in dict.type.house_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="房屋安全分类" prop="controlDegree">
          <el-select v-model="queryParams.controlDegree" placeholder="请选择房屋安全分类" clearable>
            <el-option
              v-for="dict in dict.type.control_degree"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="产权性质" prop="propertyRight">
          <el-select v-model="queryParams.propertyRight" placeholder="请选择产权性质" clearable>
            <el-option
              v-for="dict in dict.type.property_right"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="治安责任书签署情况" prop="responsibilitySignature">
          <el-select v-model="queryParams.responsibilitySignature" placeholder="请选择治安责任书签署情况" clearable>
            <el-option
              v-for="dict in dict.type.responsibility_signature"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="托管情况" prop="houseDeposit">
          <el-select v-model="queryParams.houseDeposit" placeholder="请选择托管情况" clearable>
            <el-option
              v-for="dict in dict.type.house_deposit"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
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
            v-hasPermi="['system:house:add']"
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
            v-hasPermi="['system:house:edit']"
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
            v-hasPermi="['system:house:remove']"
          >删除</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="warning"
            plain
            icon="el-icon-download"
            size="mini"
            @click="handleExport"
            v-hasPermi="['system:house:export']"
          >导出</el-button>
        </el-col>
        <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
      </el-row>
  
      <el-table v-loading="loading" :data="houseList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="房屋产权号" align="center" prop="houseId" />
        <el-table-column label="所属小区名" align="center" prop="residentialVillageName" />
        <el-table-column label="门牌号" align="center" prop="doorNumber" />
        <el-table-column label="房间号" align="center" prop="houseNumber" />
        <el-table-column label="流管编号" align="center" prop="controlNumber" />
        <el-table-column label="房屋用途" align="center" prop="houseUsage">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.house_usage" :value="scope.row.houseUsage"/>
          </template>
        </el-table-column>
        <el-table-column label="户主证件类型" align="center" prop="hostIdType" >
          <template slot-scope="scope">
            <dict-tag :options="dict.type.host_id_type" :value="scope.row.hostIdType"/>
          </template>
        </el-table-column>
        <el-table-column label="户主证件号码" align="center" prop="hostId" :show-overflow-tooltip='true' />
        <el-table-column label="标准地址" align="center" prop="standardAddress" width="200" :show-overflow-tooltip='true' />
        <el-table-column label="房屋名称" align="center" prop="houseName" />
        <el-table-column label="房屋类型" align="center" prop="houseType">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.house_type" :value="scope.row.houseType"/>
          </template>
        </el-table-column>
        <el-table-column label="房屋安全分类" align="center" prop="controlDegree">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.control_degree" :value="scope.row.controlDegree"/>
          </template>
        </el-table-column>
        <el-table-column label="产权性质" align="center" prop="propertyRight">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.property_right" :value="scope.row.propertyRight"/>
          </template>
        </el-table-column>
        <el-table-column label="重点关注原因" align="center" prop="controlReason" />
        <el-table-column label="房屋面积" align="center" prop="houseArea" />
        <el-table-column label="治安责任书签署情况" align="center" prop="responsibilitySignature">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.responsibility_signature" :value="scope.row.responsibilitySignature"/>
          </template>
        </el-table-column>
        <el-table-column label="房间数量" align="center" prop="roomQuantity" />
        <el-table-column label="托管情况" align="center" prop="houseDeposit">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.house_deposit" :value="scope.row.houseDeposit"/>
          </template>
        </el-table-column>
        <el-table-column label="备注" align="center" prop="remark" />
        <el-table-column label="操作" align="center" width="180">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleResidencePeople(scope.row)"
              v-hasPermi="['system:house:edit']"
            >查看户籍在此房屋的人员</el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleCurrentPeople(scope.row)"
              v-hasPermi="['system:house:edit']"
            >查看现住在此房屋的人员</el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="dailyCheck(scope.row)"
              v-hasPermi="['system:house:edit']"
            >日常检查记录</el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="involvedCheck(scope.row)"
              v-hasPermi="['system:house:edit']"
            >查看房屋涉案</el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleUpdate(scope.row)"
              v-hasPermi="['system:house:edit']"
            >修改</el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
              v-hasPermi="['system:house:remove']"
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
  
      <!-- 添加或修改门牌对话框 -->
      <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
        <el-form ref="form" :model="form" :rules="rules" label-width="80px">
          <el-form-item label="房屋产权号" prop="houseId" v-if="mode == 'add'">
            <el-input
              v-model="form.houseId"
              placeholder="请输入房屋产权号"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="省" prop="provinceCode">
            <el-select v-model="form.provinceCode" placeholder="请选择省份" @change="provinceSelected(form.provinceCode)">
                <el-option
                v-for="singleProvince in provinceList"
                :key="singleProvince.code"
                :label="singleProvince.name"
                :value="singleProvince.code"/>
            </el-select>
          </el-form-item>
          <el-form-item label="市" prop="cityCode">
          <el-select v-model="form.cityCode" placeholder="请选择市" @change="citySelected(form.cityCode)">
            <el-option
                v-for="singleCity in cityList"
                :key="singleCity.code"
                :label="singleCity.name"
                :value="singleCity.code"/>
            </el-select>
          </el-form-item>
          <el-form-item label="区" prop="districtCode">
          <el-select v-model="form.districtCode" placeholder="请选择区" @change="districtSelected(form.districtCode)">
            <el-option
                v-for="singleDistrict in districtList"
                :key="singleDistrict.code"
                :label="singleDistrict.name"
                :value="singleDistrict.code"/>
            </el-select>
          </el-form-item>
          <el-form-item label="街道" prop="streetCode">
          <el-select v-model="form.streetCode" placeholder="请选择街道" @change="streetSelected(form.streetCode)">
            <el-option
                v-for="singleStreet in streetList"
                :key="singleStreet.code"
                :label="singleStreet.name"
                :value="singleStreet.code"/>
            </el-select>
          </el-form-item>
          <el-form-item label="居委会" prop="positionCode">
          <el-select v-model="form.positionCode" placeholder="请选择居委会" @change="positionSelected(form.positionCode)">
            <el-option
                v-for="singlePosition in positionList"
                :key="singlePosition.code"
                :label="singlePosition.name"
                :value="singlePosition.code"/>
            </el-select>
          </el-form-item>
          <el-form-item label="所属小区名" prop="residentialVillageName">
            <el-input v-model="form.residentialVillageName" placeholder="请输入所属小区名" />
          </el-form-item>
          <el-form-item label="门牌号" prop="doorNumber">
            <el-input v-model="form.doorNumber" placeholder="请输入门牌号" />
          </el-form-item>
          <el-form-item label="房间号" prop="houseNumber">
            <el-input v-model="form.houseNumber" placeholder="请输入房间号" />
          </el-form-item>
          <el-form-item label="流管编号" prop="controlNumber">
            <el-input v-model="form.controlNumber" placeholder="请输入流管编号" />
          </el-form-item>
          <el-form-item label="房屋用途" prop="houseUsage">
            <el-select v-model="form.houseUsage" placeholder="请选择房屋用途">
              <el-option
                v-for="dict in dict.type.house_usage"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="户主证件类型" prop="hostIdType">
            <el-select v-model="form.hostIdType" placeholder="请选择户主证件类型">
              <el-option
                v-for="dict in dict.type.host_id_type"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="户主证件号码" prop="hostId">
            <el-input v-model="form.hostId" placeholder="请输入户主证件号码" />
          </el-form-item>
          <el-form-item label="房屋名称" prop="houseName">
            <el-input v-model="form.houseName" placeholder="请输入房屋名称" />
          </el-form-item>
          <el-form-item label="房屋类型" prop="houseType">
            <el-select v-model="form.houseType" placeholder="请选择房屋类型">
              <el-option
                v-for="dict in dict.type.house_type"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="房屋安全分类" prop="controlDegree">
            <el-select v-model="form.controlDegree" placeholder="请选择房屋安全分类">
              <el-option
                v-for="dict in dict.type.control_degree"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="产权性质" prop="propertyRight">
            <el-select v-model="form.propertyRight" placeholder="请选择产权性质">
              <el-option
                v-for="dict in dict.type.property_right"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="重点关注原因" prop="controlReason">
            <el-input v-model="form.controlReason" placeholder="请输入重点关注原因" />
          </el-form-item>
          <el-form-item label="房屋面积" prop="houseArea">
            <el-input v-model="form.houseArea" placeholder="请输入房屋面积"/>
          </el-form-item>
          <el-form-item label="治安责任书签署情况" prop="responsibilitySignature">
            <el-select v-model="form.responsibilitySignature" placeholder="请选择治安责任书签署情况">
              <el-option
                v-for="dict in dict.type.responsibility_signature"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="房间数量" prop="roomQuantity">
            <el-input v-model="form.roomQuantity" placeholder="请输入房间数量" />
          </el-form-item>
          <el-form-item label="托管情况" prop="houseDeposit">
            <el-select v-model="form.houseDeposit" placeholder="请选择托管情况">
              <el-option
                v-for="dict in dict.type.house_deposit"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="备注" prop="remark">
            <el-input v-model="form.remark" placeholder="请输入备注" />
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
  import { listHouse, getHouse, delHouse, addHouse, updateHouse } from "@/api/standard/house";
  import { listRegion, getRegion } from "@/api/standard/region";

  export default {
    name: "House",
    dicts: ['house_deposit', 'control_degree', 'house_usage', 'responsibility_signature', 'house_type', 'property_right', 'host_id_type'],
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
        // 门牌表格数据
        houseList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 添加或修改
        mode: "add",
        // 行政区列表
        provinceList: [],
        cityList: [],
        districtList: [],
        streetList: [],
        positionList: [],
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          residentialVillageName: null,
          doorNumber: null,
          houseNumber: null,
          controlNumber: null,
          houseUsage: null,
          hostIdType: null,
          hostId: null,
          standardAddress: null,
          houseName: null,
          houseType: null,
          controlDegree: null,
          propertyRight: null,
          houseArea: null,
          responsibilitySignature: null,
          roomQuantity: null,
          houseDeposit: null,
          provinceCode: null,
          cityCode: null,
          districtCode: null,
          streetCode: null,
          positionCode: null

        },
        // 表单参数
        form: {},
        //下拉框查询参数
        constrain: {
          level: undefined,
          parentCode: undefined
        },
        // 表单校验
        rules: {
          houseId: [
            { required: true, message: "房屋产权号不能为空", trigger: "change" },
            { pattern: /^[+]?(0|([1-9]\d*))?$/, message: "请输入正确数字", trigger: "blur"}
          ],
          provinceCode: [
            { required: true, message: "省不能为空", trigger: "change" }
          ],
          cityCode: [
            { required: true, message: "市不能为空", trigger: "change" }
          ],
          districtCode: [
            { required: true, message: "区不能为空", trigger: "change" }
          ],
          streetCode: [
            { required: true, message: "街道不能为空", trigger: "change" }
          ],
          positionCode: [
            { required: true, message: "居委会不能为空", trigger: "change" }
          ],
          residentialVillageName: [
            { required: true, message: "小区名不能为空", trigger: "blur" }
          ],
          doorNumber: [
            { required: true, message: "门牌号不能为空", trigger: "blur" },
            { pattern: /^[+]?(0|([1-9]\d*))?$/, message: "请输入正确数字", trigger: "blur"}
          ],
          houseNumber: [
            { required: true, message: "房间号不能为空", trigger: "blur" },
            { pattern: /^[+]?(0|([1-9]\d*))?$/, message: "请输入正确数字", trigger: "blur"}
          ],
          hostIdType: [
            { required: true, message: "户主证件类型不能为空", trigger: "change" }
          ],
          hostId: [
            { required: true, message: "户主证件号码不能为空（无证件则填'0'）", trigger: "blur" }
          ],
          houseName: [
            { required: true, message: "房间名不能为空", trigger: "blur" }
          ],
          houseArea: [
            { required: true, message: "房间面积不能为空", trigger: "blur" },
            { pattern: /^[+]?(0|([1-9]\d*))?\.?(0|([1-9]\d*))?$/, message: "请输入正确数字", trigger: "blur"}
          ],
          roomQuantity: [
            { pattern: /^[+]?(0|([1-9]\d*))?$/, message: "请输入正确数字", trigger: "blur"}
          ],
          controlNumber: [
            { pattern: /^[+]?(0|([1-9]\d*))?$/, message: "请输入正确数字", trigger: "blur"}
          ]
        }
      };
    },
    created() {
      this.getList();
      this.getProvinceList();
    },
    methods: {
      /** 查询门牌列表 */
      getList() {
        this.loading = true;
        listHouse(this.queryParams).then(response => {
          this.houseList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      },
      getProvinceList() {
        this.constrain.level = 0;
        listRegion(this.constrain).then(response => {
          this.provinceList = response.data;
        });
      },

      provinceSelected(value) {
        this.form.provinceIsSelected = true;
        this.form.cityCode = undefined;    
        this.form.cityIsSelected = false;
        this.form.districtCode = undefined;
        this.form.districtIsSelected = false;
        this.form.streetCode = undefined;
        this.form.streetIsSelected = false;
        this.form.positionCode = undefined;
        let constrain = {};
        constrain.parentCode = value;
        listRegion(constrain).then(response => {
          this.cityList = response.data;
        })
        this.$forceUpdate();
      },
      citySelected(value) {
        this.form.cityIsSelected = true;
        this.form.districtCode = undefined;
        this.form.districtIsSelected = false;
        this.form.streetCode = undefined;
        this.form.streetIsSelected = false;
        this.form.positionCode = undefined;
        let constrain = {};
        constrain.parentCode = value;
        listRegion(constrain).then(response => {
          this.districtList = response.data;
        })
        this.$forceUpdate();
      },
      districtSelected(value) {
        this.form.districtIsSelected = true;
        this.form.streetCode = undefined;
        this.form.streetIsSelected = false;
        this.form.positionCode = undefined;
        let constrain = {};
        constrain.parentCode = value;
        listRegion(constrain).then(response => {
          this.streetList = response.data;
        })
        this.$forceUpdate();
      },
      streetSelected(value) {
        this.form.streetIsSelected = true;
        this.form.positionCode = undefined;
        let constrain = {};
        constrain.parentCode = value;
        listRegion(constrain).then(response => {
          this.positionList = response.data;
        })
        this.$forceUpdate();
      },
      positionSelected(value) {
        this.form.positionIsSelected = true;
        this.$forceUpdate();
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
          houseId: null,
          positionCode: null,
          residentialVillageName: null,
          doorNumber: null,
          houseNumber: null,
          controlNumber: null,
          houseUsage: null,
          hostIdType: null,
          hostId: null,
          standardAddress: null,
          houseName: null,
          houseType: null,
          controlDegree: null,
          propertyRight: null,
          controlReason: null,
          houseArea: null,
          responsibilitySignature: null,
          roomQuantity: null,
          houseDeposit: null,
          remark: null,

          regionId: null,
          parentCode: null,
          parentName: null,
          police: null,
          code: null,
          name: null,
          fullName: null,
          firstLetter: null,
          lng: null,
          lat: null,
          geographicGrid: null,
          geographicEnity: null,
          type: null,
          hasChild: null,
          createTime: null,
          createUserId: null,
          createUser: null,
          createUserPhone: null,
          lastUpdateTime: null,
          lastUpdateUser: null,
          lastUpdateUserId: null,
          lastUpdateUserPhone: null,
          auditUser: null,
          auditUserId: null,
          auditState: null,
          auditUserPhone: null,
          commitStatus: "0", 
          provinceCode: undefined,
          cityCode: undefined,
          districtCode: undefined,
          streetCode: undefined,
          positionCode: undefined,
          provinceIsSelected : false,
          cityIsSelected : false,
          districtIsSelected : false,
          streetIsSelected : false,
          positionIsSelected: false,
          oldCode: undefined,
          deprecated: null
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
        this.ids = selection.map(item => item.houseId)
        this.single = selection.length!==1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加门牌";
        this.mode = "add";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        this.mode = "edit";
        const houseId = row.houseId || this.ids
        getHouse(houseId).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改门牌";
          getRegion(this.form.positionCode).then(response => {
            this.form.provinceCode = response.data.provinceCode;
            let constrain1 = {};
            constrain1.parentCode = this.form.provinceCode;
            listRegion(constrain1).then(response => {
              this.cityList = response.data;
            });

            this.form.cityCode = response.data.cityCode;
            let constrain2 = {};
            constrain2.parentCode = this.form.cityCode;
            listRegion(constrain2).then(response => {
              this.districtList = response.data;
            });

            this.form.districtCode = response.data.districtCode;
            let constrain3 = {};
            constrain3.parentCode = this.form.districtCode;
            listRegion(constrain3).then(response => {
              this.streetList = response.data;
            });

            this.form.streetCode = response.data.streetCode;
            let constrain4 = {};
            constrain4.parentCode = this.form.streetCode;
            listRegion(constrain4).then(response => {
              this.positionList = response.data;
            });
          })
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.mode === "edit") {
              updateHouse(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addHouse(this.form).then(response => {
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
        const houseIds = row.houseId || this.ids;
        this.$modal.confirm('是否确认删除门牌编号为"' + houseIds + '"的数据项？').then(function() {
          return delHouse(houseIds);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
      },
      /** 导出按钮操作 */
      handleExport() {
        this.download('system/house/export', {
          ...this.queryParams
        }, `house_${new Date().getTime()}.xlsx`)
      },
      handleResidencePeople(row) {
        const residenceHouseId = row.houseId;
        const params = { pageNum: this.queryParams.pageNum };
        this.$tab.openPage("查看户籍在房屋ID为[" + residenceHouseId + "]的人员", '/standard/person/get-residence/' + residenceHouseId, params);
      },
      handleCurrentPeople(row) {
        const currentHouseId = row.houseId;
        const params = { pageNum: this.queryParams.pageNum };
        this.$tab.openPage("查看现住在房屋ID为[" + currentHouseId + "]的人员", '/standard/person/get-current/' + currentHouseId, params);
      },
      dailyCheck(row) {
        const houseId = row.houseId;
        const params = { pageNum: this.queryParams.pageNum };
        this.$tab.openPage("房屋ID为[" + houseId + "]的日常检查记录", '/standard/house/dailyCheck/' + houseId, params);
      },
      involvedCheck(row) {
        const involvedEntityId = row.houseId;
        const involvedEntityType = '房屋';
        const params = { pageNum: this.queryParams.pageNum};
        
        this.$tab.openPage("房屋ID为[" + involvedEntityId + "]的涉案记录", '/standard/house/involvedCheck/' + involvedEntityType + '/' + involvedEntityId, params);
      }

    }
  };
  </script>
  