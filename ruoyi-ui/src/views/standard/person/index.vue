<template>
    <div class="app-container">
      <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
        <!--<el-form-item label="户籍省" prop="residenceProvinceCode">
            <el-select v-model="queryParams.residenceProvinceCode" placeholder="请选择省份" @change="queryResidenceProvinceSelected(queryParams.residenceProvinceCode)">
                <el-option
                v-for="singleProvince in residenceProvinceList"
                :key="singleProvince.code"
                :label="singleProvince.name"
                :value="singleProvince.code"/>
            </el-select>
          </el-form-item>
          <el-form-item label="户籍市" prop="residenceCityCode">
          <el-select v-model="queryParams.residenceCityCode" placeholder="请选择市" @change="queryResidenceCitySelected(queryParams.residenceCityCode)">
            <el-option
                v-for="singleCity in residenceCityList"
                :key="singleCity.code"
                :label="singleCity.name"
                :value="singleCity.code"/>
            </el-select>
          </el-form-item>
          <el-form-item label="户籍区" prop="residenceDistrictCode">
          <el-select v-model="queryParams.residenceDistrictCode" placeholder="请选择区" @change="queryResidenceDistrictSelected(queryParams.residenceDistrictCode)">
            <el-option
                v-for="singleDistrict in residenceDistrictList"
                :key="singleDistrict.code"
                :label="singleDistrict.name"
                :value="singleDistrict.code"/>
            </el-select>
          </el-form-item>
          <el-form-item label="户籍街道" prop="residenceStreetCode">
          <el-select v-model="queryParams.residenceStreetCode" placeholder="请选择街道" @change="queryResidenceStreetSelected(queryParams.residenceStreetCode)">
            <el-option
                v-for="singleStreet in residenceStreetList"
                :key="singleStreet.code"
                :label="singleStreet.name"
                :value="singleStreet.code"/>
            </el-select>
          </el-form-item>
          <el-form-item label="户籍居委会" prop="residencePositionCode">
          <el-select v-model="queryParams.residencePositionCode" placeholder="请选择居委会" @change="queryResidencePositionSelected(queryParams.residencePositionCode)">
            <el-option
                v-for="singlePosition in residencePositionList"
                :key="singlePosition.code"
                :label="singlePosition.name"
                :value="singlePosition.code"/>
            </el-select>
          </el-form-item>
          <el-form-item label="户籍房屋产权证号" prop="residenceStdHouseId">
            <el-input
              v-model="queryParams.residenceStdHouseId"
              placeholder="请输入户籍房屋产权证号"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>

          <el-form-item label="现住省" prop="currentProvinceCode">
            <el-select v-model="queryParams.currentProvinceCode" placeholder="请选择省份" @change="queryCurrentProvinceSelected(queryParams.currentProvinceCode)">
                <el-option
                v-for="singleProvince in currentProvinceList"
                :key="singleProvince.code"
                :label="singleProvince.name"
                :value="singleProvince.code"/>
            </el-select>
          </el-form-item>
          <el-form-item label="现住市" prop="currentCityCode">
          <el-select v-model="queryParams.currentCityCode" placeholder="请选择市" @change="queryCurrentCitySelected(queryParams.currentCityCode)">
            <el-option
                v-for="singleCity in currentCityList"
                :key="singleCity.code"
                :label="singleCity.name"
                :value="singleCity.code"/>
            </el-select>
          </el-form-item>
          <el-form-item label="现住区" prop="currentDistrictCode">
          <el-select v-model="queryParams.currentDistrictCode" placeholder="请选择区" @change="queryCurrentDistrictSelected(queryParams.currentDistrictCode)">
            <el-option
                v-for="singleDistrict in currentDistrictList"
                :key="singleDistrict.code"
                :label="singleDistrict.name"
                :value="singleDistrict.code"/>
            </el-select>
          </el-form-item>
          <el-form-item label="现住街道" prop="currentStreetCode">
          <el-select v-model="queryParams.currentStreetCode" placeholder="请选择街道" @change="queryCurrentStreetSelected(queryParams.currentStreetCode)">
            <el-option
                v-for="singleStreet in currentStreetList"
                :key="singleStreet.code"
                :label="singleStreet.name"
                :value="singleStreet.code"/>
            </el-select>
          </el-form-item>
          <el-form-item label="现住居委会" prop="currentPositionCode">
          <el-select v-model="queryParams.currentPositionCode" placeholder="请选择居委会" @change="queryCurrentPositionSelected(queryParams.currentPositionCode)">
            <el-option
                v-for="singlePosition in currentPositionList"
                :key="singlePosition.code"
                :label="singlePosition.name"
                :value="singlePosition.code"/>
            </el-select>
          </el-form-item>
          <el-form-item label="现住房屋产权证号" prop="currentStdHouseId">
            <el-input
              v-model="queryParams.currentStdHouseId"
              placeholder="请输入户籍房屋产权证号"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>-->

        <el-form-item label="证件类型" prop="personIdType">
          <el-select v-model="queryParams.personIdType" placeholder="请选择证件类型" clearable>
            <el-option
              v-for="dict in dict.type.host_id_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="证件号码" prop="personId">
          <el-input
            v-model="queryParams.personId"
            placeholder="请输入证件号码(无证件则填0)"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="个体姓名" prop="personName">
          <el-input
            v-model="queryParams.personName"
            placeholder="请输入个体姓名"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <!--<el-form-item label="个体性别" prop="personGender">
          <el-select v-model="queryParams.personGender" placeholder="请选择个体性别" clearable>
            <el-option
              v-for="dict in dict.type.sys_user_sex"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="个体英文名" prop="personEnglishName">
          <el-input
            v-model="queryParams.personEnglishName"
            placeholder="请输入个体英文名"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="所属派出所" prop="police">
          <el-input
            v-model="queryParams.police"
            placeholder="请输入所属派出所"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="人员类别" prop="personType">
          <el-select v-model="queryParams.personType" placeholder="请选择人员类别" clearable>
            <el-option
              v-for="dict in dict.type.person_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="是否已关联标准地址" prop="relatedToStdPosition">
          <el-select v-model="queryParams.relatedToStdPosition" placeholder="请选择是否已关联标准地址" clearable>
            <el-option
              v-for="dict in dict.type.sys_yes_no"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="民族" prop="nation">
          <el-input
            v-model="queryParams.nation"
            placeholder="请输入民族"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>-->
        <el-form-item label="出生日期">
          <el-date-picker
            v-model="daterangeBirthday"
            style="width: 240px"
            value-format="yyyy-MM-dd"
            type="daterange"
            range-separator="-"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
          ></el-date-picker>
        </el-form-item>
        <!--<el-form-item label="婚姻状况" prop="married">
          <el-select v-model="queryParams.married" placeholder="请选择婚姻状况" clearable>
            <el-option
              v-for="dict in dict.type.married"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="学历" prop="degree">
          <el-select v-model="queryParams.degree" placeholder="请选择学历" clearable>
            <el-option
              v-for="dict in dict.type.degree"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        </el-form-item>-->
      </el-form>
  
      <el-row :gutter="10" class="mb8">
        <el-col :span="1.5">
          <el-button
            type="primary"
            plain
            icon="el-icon-plus"
            size="mini"
            @click="handleAdd"
            v-hasPermi="['system:person:add']"
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
            v-hasPermi="['system:person:edit']"
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
            v-hasPermi="['system:person:remove']"
          >删除</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="warning"
            plain
            icon="el-icon-download"
            size="mini"
            @click="handleExport"
            v-hasPermi="['system:person:export']"
          >导出</el-button>
        </el-col>
        <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
      </el-row>
  
      <el-table v-loading="loading" :data="personList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="证件类型" align="center" prop="personIdType" width="100">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.host_id_type" :value="scope.row.personIdType"/>
          </template>
        </el-table-column>
        <el-table-column label="证件号码" align="center" prop="personId" :show-overflow-tooltip='true' />
        <el-table-column label="个体姓名" align="center" prop="personName" />
        <el-table-column label="个体性别" align="center" prop="personGender">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.sys_user_sex" :value="scope.row.personGender"/>
          </template>
        </el-table-column>
        <el-table-column label="个体英文名" align="center" prop="personEnglishName" />
        <el-table-column label="所属派出所" align="center" prop="police" />
        <el-table-column label="人员类别" align="center" prop="personType">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.person_type" :value="scope.row.personType"/>
          </template>
        </el-table-column>
        <el-table-column label="是否已关联标准地址" align="center" prop="relatedToStdPosition">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.sys_yes_no" :value="scope.row.relatedToStdPosition"/>
          </template>
        </el-table-column>
        <el-table-column label="户籍标准地址" align="center" prop="residenceStdAddress" width="200" :show-overflow-tooltip='true' />
        <el-table-column label="户籍产权证号码" align="center" prop="residenceStdHouseId" />
        <el-table-column label="现住标准地址" align="center" prop="currentStdAddress" width="200" :show-overflow-tooltip='true' />
        <el-table-column label="现住产权证号码" align="center" prop="currentStdHouseId" />
        <el-table-column label="民族" align="center" prop="nation" />
        <el-table-column label="出生日期" align="center" prop="birthday" width="180">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.birthday, '{y}-{m}-{d}') }}</span>
          </template>
        </el-table-column>
        <el-table-column label="职业" align="center" prop="occupation" />
        <el-table-column label="婚姻状况" align="center" prop="married">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.married" :value="scope.row.married"/>
          </template>
        </el-table-column>
        <el-table-column label="学历" align="center" prop="degree">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.degree" :value="scope.row.degree"/>
          </template>
        </el-table-column>
        <el-table-column label="联系方式" align="center" prop="contacts" />
        <el-table-column label="暂住理由" align="center" prop="tempResidenceReason" />
        <el-table-column label="所属单位" align="center" prop="deptName" />
        <el-table-column label="操作" align="center" width="160">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="trackCheck(scope.row)"
              v-hasPermi="['system:person:edit']"
            >查看人口居住轨迹</el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleUpdate(scope.row)"
              v-hasPermi="['system:person:edit']"
            >修改</el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
              v-hasPermi="['system:person:remove']"
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
  
      <!-- 添加或修改个体对话框 -->
      <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
        <el-form ref="form" :model="form" :rules="rules" label-width="80px">
          <el-form-item label="现住房屋产权号" prop="currentStdHouseId">
            <el-input v-model.number="form.currentStdHouseId" placeholder="请输入现住房屋产权号" />
          </el-form-item>
          <el-form-item label="户籍房屋产权号" prop="residenceStdHouseId">
            <el-input v-model="form.residenceStdHouseId" placeholder="请输入户籍房屋产权号" />
          </el-form-item>
          <el-form-item v-if="mode === 'add'" label="证件类型" prop="personIdType">
            <el-select v-model="form.personIdType" placeholder="请选择证件类型">
              <el-option
                v-for="dict in dict.type.host_id_type"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item v-if="mode === 'add'" label="证件号码" prop="personId">
            <el-input v-model="form.personId" placeholder="请输入证件号码(无证件则填0)" />
          </el-form-item>
          <el-form-item label="个体姓名" prop="personName">
            <el-input v-model="form.personName" placeholder="请输入个体姓名" />
          </el-form-item>
          <el-form-item label="个体性别" prop="personGender">
            <el-select v-model="form.personGender" placeholder="请选择个体性别">
              <el-option
                v-for="dict in dict.type.sys_user_sex"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="个体英文名" prop="personEnglishName">
            <el-input v-model="form.personEnglishName" placeholder="请输入个体英文名" />
          </el-form-item>
          <el-form-item label="所属派出所" prop="police">
            <el-input v-model="form.police" placeholder="请输入所属派出所" />
          </el-form-item>
          <el-form-item label="人员类别" prop="personType">
            <el-select v-model="form.personType" placeholder="请选择人员类别">
              <el-option
                v-for="dict in dict.type.person_type"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="民族" prop="nation">
            <el-input v-model="form.nation" placeholder="请输入民族" />
          </el-form-item>
          <el-form-item label="出生日期" prop="birthday">
            <el-date-picker clearable
              v-model="form.birthday"
              type="date"
              value-format="yyyy-MM-dd"
              placeholder="请选择出生日期">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="职业" prop="occupation">
            <el-input v-model="form.occupation" placeholder="请输入职业" />
          </el-form-item>
          <el-form-item label="婚姻状况" prop="married">
            <el-select v-model="form.married" placeholder="请选择婚姻状况">
              <el-option
                v-for="dict in dict.type.married"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="学历" prop="degree">
            <el-select v-model="form.degree" placeholder="请选择学历">
              <el-option
                v-for="dict in dict.type.degree"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="联系方式" prop="contacts">
            <el-input v-model="form.contacts" placeholder="请输入联系方式" />
          </el-form-item>
          <el-form-item label="暂住理由" prop="tempResidenceReason">
            <el-input v-model="form.tempResidenceReason" placeholder="请输入暂住理由" />
          </el-form-item>
          <el-form-item label="所属单位经营执照编号" prop="deptId">
            <el-input v-model="form.deptId" placeholder="请输入所属单位经营执照编号" />
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
  import { listPerson, getPerson, delPerson, addPerson, updatePerson } from "@/api/standard/person";
  import { listRegion, getRegion } from "@/api/standard/region";
  
  export default {
    name: "Person",
    dicts: ['married', 'degree', 'person_type', 'sys_yes_no', 'sys_user_sex', 'host_id_type'],
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
        // 添加或新增
        mode: 'add',
        // 个体表格数据
        personList: [],
        // 行政区划
        residenceProvinceList: [],
        residenceCityList: [],
        residenceDistrictList: [],
        residenceStreetList: [],
        residencePositionList: [],
        currentProvinceList: [],
        currentCityList: [],
        currentDistrictList: [],
        currentStreetList: [],
        currentPositionList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 暂住理由时间范围
        daterangeBirthday: [],
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          personIdType: null,
          personId: null,
          personName: null,
          personGender: null,
          personEnglishName: null,
          police: null,
          personType: null,
          relatedToStdPosition: null,
          nation: null,
          birthday: null,
          married: null,
          degree: null,
          residenceProvinceCode: null,
          residenceCityCode: null,
          residenceDistrictCode: null,
          residenceStreetCode: null,
          residencePositionCode: null,
          residenceStdHouseId: null,

          currentProvinceCode: null,
          currentCityCode: null,
          currentDistrictCode: null,
          currentStreetCode: null,
          currentPositionCode: null,
          currentStdHouseId: null
        },
        // 表单参数
        form: {},
        constrain: {
          level: undefined,
          parentCode: undefined
        },
        // 表单校验
        rules: {
          currentStdHouseId: [
            { required: true, message: "现居房屋产权号不能为空", triiger: "blur", type: "number"},
            { pattern: /^[+]?(0|([1-9]\d*))?$/, message: "请输入正确数字", trigger: "blur"}
          ],
          personIdType: [
            { required: true, message: "证件类型不能为空", trigger: "change" }
          ],
          personId: [
            { required: true, message: "证件号码不能为空", trigger: "blur" }
          ],
          personName: [
            { required: true, message: "个体姓名不能为空", trigger: "blur" }
          ],
          personGender: [
            { required: true, message: "个体性别不能为空", trigger: "change" }
          ],
          birthday: [
            { required: true, message: "出生日期不能为空", trigger: "blur" }
          ],
          deptId: [
            { pattern: /^[+]?(0|([1-9]\d*))?$/, message: "请输入正确数字", trigger: "blur"}
          ],
          nation: [
            { max: 5, message: "名称长度在不超过5个字符", trigger: "blur" }
          ]
        }
      };
    },
    created() {
      this.getList();
      this.getProvinceList();
    },
    methods: {
      /** 查询个体列表 */
      getList() {
        this.loading = true;
        this.queryParams.params = {};
        this.queryParams.residenceStdHouseId = this.$route.params.residenceHouseId;
        this.queryParams.currentStdHouseId = this.$route.params.currentHouseId;
        this.queryParams.deptId = this.$route.params.deptId;
        if (null != this.daterangeBirthday && '' != this.daterangeBirthday) {
          this.queryParams.params["beginBirthday"] = this.daterangeBirthday[0];
          this.queryParams.params["endBirthday"] = this.daterangeBirthday[1];
        }
        listPerson(this.queryParams).then(response => {
          this.personList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      },
      getProvinceList() {
        this.constrain.level = 0;
        listRegion(this.constrain).then(response => {
          this.currentProvinceList = response.data;
          this.residenceProvinceList = response.data;
        });
      },

      queryResidenceProvinceSelected(value) {
        this.queryParams.residenceProvinceIsSelected = true;
        this.queryParams.residenceCityCode = undefined;    
        this.queryParams.residenceCityIsSelected = false;
        this.queryParams.residenceDistrictCode = undefined;
        this.queryParams.residenceDistrictIsSelected = false;
        this.queryParams.residenceStreetCode = undefined;
        this.queryParams.residenceStreetIsSelected = false;
        this.queryParams.residencePositionCode = undefined;
        let constrain = {};
        constrain.parentCode = value;
        listRegion(constrain).then(response => {
          this.residenceCityList = response.data;
        })
        this.$forceUpdate();
      },
      queryCurrentProvinceSelected(value) {
        this.queryParams.currentProvinceIsSelected = true;
        this.queryParams.currentCityCode = undefined;    
        this.queryParams.currentCityIsSelected = false;
        this.queryParams.currentDistrictCode = undefined;
        this.queryParams.currentDistrictIsSelected = false;
        this.queryParams.currentStreetCode = undefined;
        this.queryParams.currentStreetIsSelected = false;
        this.queryParams.currentPositionCode = undefined;
        let constrain = {};
        constrain.parentCode = value;
        listRegion(constrain).then(response => {
          this.currentCityList = response.data;
        })
        this.$forceUpdate();
      },
      queryResidenceCitySelected(value) {
        this.queryParams.residenceCityIsSelected = true;
        this.queryParams.residenceDistrictCode = undefined;
        this.queryParams.residenceDistrictIsSelected = false;
        this.queryParams.residenceStreetCode = undefined;
        this.queryParams.residenceStreetIsSelected = false;
        this.queryParams.residencePositionCode = undefined;
        let constrain = {};
        constrain.parentCode = value;
        listRegion(constrain).then(response => {
          this.residenceDistrictList = response.data;
        })
        this.$forceUpdate();
      },
      queryCurrentCitySelected(value) {
        this.queryParams.currentCityIsSelected = true;
        this.queryParams.currentDistrictCode = undefined;
        this.queryParams.currentDistrictIsSelected = false;
        this.queryParams.currentStreetCode = undefined;
        this.queryParams.currentStreetIsSelected = false;
        this.queryParams.currentPositionCode = undefined;
        let constrain = {};
        constrain.parentCode = value;
        listRegion(constrain).then(response => {
          this.currentDistrictList = response.data;
        })
        this.$forceUpdate();
      },
      queryResidenceDistrictSelected(value) {
        this.queryParams.residenceDistrictIsSelected = true;
        this.queryParams.residenceStreetCode = undefined;
        this.queryParams.residenceStreetIsSelected = false;
        this.queryParams.residencePositionCode = undefined;
        let constrain = {};
        constrain.parentCode = value;
        listRegion(constrain).then(response => {
          this.residenceStreetList = response.data;
        })
        this.$forceUpdate();
      },
      queryCurrentDistrictSelected(value) {
        this.queryParams.currentDistrictIsSelected = true;
        this.queryParams.currentStreetCode = undefined;
        this.queryParams.currentStreetIsSelected = false;
        this.queryParams.currentPositionCode = undefined;
        let constrain = {};
        constrain.parentCode = value;
        listRegion(constrain).then(response => {
          this.currentStreetList = response.data;
        })
        this.$forceUpdate();
      },
      queryResidenceStreetSelected(value) {
        this.queryParams.residenceStreetIsSelected = true;
        this.queryParams.residencePositionCode = undefined;
        let constrain = {};
        constrain.parentCode = value;
        listRegion(constrain).then(response => {
          this.residencePositionList = response.data;
        })
        this.$forceUpdate();
      },
      queryCurrentStreetSelected(value) {
        this.queryParams.currentStreetIsSelected = true;
        this.queryParams.currentPositionCode = undefined;
        let constrain = {};
        constrain.parentCode = value;
        listRegion(constrain).then(response => {
          this.currentPositionList = response.data;
        })
        this.$forceUpdate();
      },
      queryResidencePositionSelected(value) {
        this.queryParams.residencePositionIsSelected = true;
        this.$forceUpdate();
      },
      queryCurrentPositionSelected(value) {
        this.queryParams.currentPositionIsSelected = true;
        this.$forceUpdate();
      },


      // 取消按钮
      cancel() {
        this.open = false;
        this.mode = null;
        this.reset();
      },
      // 表单重置
      reset() {
        this.mode = null;
        this.form = {
          id: null,
          personIdType: null,
          personId: null,
          personName: null,
          personGender: null,
          personEnglishName: null,
          police: null,
          personType: null,
          relatedToStdPosition: null,
          residencePositionId: null,
          residenceStdHouseId: null,
          currentPositionId: null,
          currentStdHouseId: null,
          nation: null,
          birthday: null,
          occupation: null,
          married: null,
          degree: null,
          contacts: null,
          tempResidenceReason: null,

          currentProvinceCode: undefined,
          currentCityCode: undefined,
          currentDistrictCode: undefined,
          currentStreetCode: undefined,
          currentPositionCode: undefined,

          residenceProvinceCode: undefined,
          residenceCityCode: undefined,
          residenceDistrictCode: undefined,
          residenceStreetCode: undefined,
          residencePositionCode: undefined,

          deptId: undefined
        };
        this.currentCityList = [],
        this.currentDistrictList = [],
        this.currentStreetList = [],
        this.currentPositionList = [],

        this.residenceCityList = [],
        this.residenceDistrictList = [],
        this.residenceStreetList = [],
        this.residencePositionList = [],
        this.resetForm("form");
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },
      /** 重置按钮操作 */
      resetQuery() {
        this.daterangeBirthday = [];
        this.resetForm("queryForm");
        this.currentCityList = [],
        this.currentDistrictList = [],
        this.currentStreetList = [],
        this.currentPositionList = [],

        this.residenceCityList = [],
        this.residenceDistrictList = [],
        this.residenceStreetList = [],
        this.residencePositionList = [],
        this.handleQuery();
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.id)
        this.single = selection.length!==1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加个体";
        this.mode = 'add';
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const id = row.id || this.ids
        this.mode = 'edit';
        getPerson(id).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改个体";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.id != null) {
              updatePerson(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addPerson(this.form).then(response => {
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
        const ids = row.id || this.ids;
        this.$modal.confirm('是否确认删除个体编号为"' + ids + '"的数据项？').then(function() {
          return delPerson(ids);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
      },
      /** 导出按钮操作 */
      handleExport() {
        this.download('system/person/export', {
          ...this.queryParams
        }, `person_${new Date().getTime()}.xlsx`)
      },
      trackCheck(row) {
        const personId = row.personId;
        const personIdType = row.personIdType;
        const params = { pageNum: this.queryParams.pageNum};
        console.log(personId, personIdType);
        this.$tab.openPage("个体ID为[" + personId + "]的居住流动记录", '/standard/person/trackCheck/' + personIdType + '/' + personId, params);
      }
    }
  };
  </script>
  