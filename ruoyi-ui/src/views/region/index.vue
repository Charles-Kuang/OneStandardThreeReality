<template>
    <div class="app-container">
      <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="100px">
        <el-form-item label="行政区划代码" prop="code">
          <el-input
            v-model="queryParams.code"
            placeholder="请输入行政区划代码"
            clearable
            @keyup.enter.native="handleQuery"
            oninput="value=value.replace(/[^\d]/g,'')"
          />
        </el-form-item>
        <el-form-item label="所属公安机关" prop="police">
          <el-input
            v-model="queryParams.police"
            placeholder="请输入所属公安机关"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="行政区名" prop="name">
          <el-input
            v-model="queryParams.name"
            placeholder="请输入行政区名"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">查询</el-button>
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
            v-hasPermi="['system:region:add']"
          >新增</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="warning"
            plain
            icon="el-icon-download"
            size="mini"
            @click="handleExport"
            v-hasPermi="['system:region:export']"
          >导出</el-button>
        </el-col>
        <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
      </el-row>
  
      <el-table ref="multipleTable" v-loading="loading" :data="regionList" row-key="code" :default-expand-all="isExpandAll"  :tree-props="{children: 'children', hasChildren: 'hasChildren'}">
        <el-table-column label="行政区简称" align="center" prop="name" width="200"/>
        <el-table-column label="行政区编码" align="center" prop="code" width="150"/>
        <el-table-column label="父行政区名称" align="center" prop="parentName" :show-overflow-tooltip='true' />
        <el-table-column label="行政区全称" align="center" prop="fullName" width="200" :show-overflow-tooltip='true' />
        <el-table-column label="拼音首字母" align="center" prop="firstLetter" />
        <el-table-column label="所属公安机关" align="center" prop="police" />
        <el-table-column label="中心经度" align="center" prop="lng" width="150"/>
        <el-table-column label="中心纬度" align="center" prop="lat" width="150"/>
        <el-table-column label="城乡分类" align="center" prop="type">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.gs_urban_rural_classification" :value="scope.row.type"/>
          </template>
        </el-table-column>
        <el-table-column label="创建时间" align="center" prop="createTime" width="180">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
          </template>
        </el-table-column>
        <el-table-column label="创建用户名称" align="center" prop="createUser" />
        <el-table-column label="创建用户联系电话" align="center" prop="createUserPhone" :show-overflow-tooltip='true' />
        <el-table-column label="最后更新时间" align="center" prop="lastUpdateTime" width="180">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.lastUpdateTime, '{y}-{m}-{d}') }}</span>
          </template>
        </el-table-column>
        <el-table-column label="最后更新人" align="center" prop="lastUpdateUser" />
        <el-table-column label="最后更新人联系电话" align="center" prop="lastUpdateUserPhone" :show-overflow-tooltip='true' />
        <el-table-column label="入库时间" align="center" prop="auditTime" width="180">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.auditTime) }}</span>
          </template>
        </el-table-column>
        <el-table-column label="入库人" align="center" prop="auditUser" />
        <el-table-column label="入库状态" align="center" width="100" prop="auditState">
          <template slot-scope="scope">
            <el-switch
            v-model="scope.row.auditState"
            :active-value="1"
            :inactive-value="0"
            :disabled = "scope.row.auditState == '1'"
            @change="handleAuditStateChange(scope.row)"
            ></el-switch>
          </template>
        </el-table-column>
        <el-table-column label="开启状态" align="center" width="100" prop="deprecated">
          <template slot-scope="scope">
            <el-switch
              v-model="scope.row.deprecated"
              active-value="0"
              inactive-value="1"
              @change="handleDeprecatedChange(scope.row)"
            ></el-switch>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="left"  width="200">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleModificationHistory(scope.row)"
              v-hasPermi="['system:region:edit']"
            >查看行政区修改痕迹</el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleUpdate(scope.row)"
              v-hasPermi="['system:region:edit']"
            >修改</el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
              v-hasPermi="['system:region:remove']"
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
  
      <!-- 添加或修改行政区对话框 -->
      <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
        <el-form ref="form" :model="form" :rules="rules" label-width="160px">
          <el-form-item v-if="mode === 'add'" label="行政区级别" prop="level">
            <el-select v-model="form.level" placeholder="请选择行政区级别" @change="levelSelected(form.level)">
                <el-option
                v-for="Singlelevel in dict.type.gs_level_list"
                :key="Singlelevel.value"
                :label="Singlelevel.label"
                :value="Singlelevel.value"
                />
            </el-select>
          </el-form-item>
          <el-form-item v-if="form.level > 0" label="省" prop="provinceCode">
            <el-select v-model="form.provinceCode" placeholder="请选择省份" @change="provinceSelected(form.provinceCode)">
                <el-option
                v-for="singleProvince in provinceList"
                :key="singleProvince.code"
                :label="singleProvince.name"
                :value="singleProvince.code"/>
            </el-select>
          </el-form-item>
          <el-form-item v-if="form.level > 1" label="市" prop="cityCode">
          <el-select v-model="form.cityCode" placeholder="请选择市" @change="citySelected(form.cityCode)">
            <el-option
                v-for="singleCity in cityList"
                :key="singleCity.code"
                :label="singleCity.name"
                :value="singleCity.code"/>
            </el-select>
          </el-form-item>
          <el-form-item v-if="form.level > 2" label="区" prop="districtCode">
          <el-select v-model="form.districtCode" placeholder="请选择区" @change="districtSelected(form.districtCode)">
            <el-option
                v-for="singleDistrict in districtList"
                :key="singleDistrict.code"
                :label="singleDistrict.name"
                :value="singleDistrict.code"/>
            </el-select>
          </el-form-item>
          <el-form-item v-if="form.level > 3" label="街道" prop="streetCode">
          <el-select v-model="form.streetCode" placeholder="请选择街道" @change="streetSelected(form.streetCode)">
            <el-option
                v-for="singleStreet in streetList"
                :key="singleStreet.code"
                :label="singleStreet.name"
                :value="singleStreet.code"/>
            </el-select>
          </el-form-item>
          
          <el-form-item label="所属公安机关" prop="police">
            <el-input v-model="form.police" placeholder="请输入所属公安机关" />
          </el-form-item>
          <el-form-item label="行政区编码" prop="code" v-if="mode === 'add'">
            <el-input v-model="form.code" placeholder="请输入行政区编码" />
          </el-form-item>
          <el-form-item label="行政区简称" prop="name">
            <el-input v-model="form.name" placeholder="请输入行政区简称" />
          </el-form-item>
          <el-form-item label="行政区全称" prop="fullName">
            <el-input v-model="form.fullName" placeholder="请输入行政区全称" />
          </el-form-item>
          <el-form-item label="拼音首字母" prop="firstLetter">
            <el-input v-model="form.firstLetter" placeholder="请输入拼音首字母" />
          </el-form-item>
          <el-form-item label="城乡分类" prop="type">
            <el-select v-model="form.type" placeholder="请选择城乡分类">
              <el-option
                v-for="dict in dict.type.gs_urban_rural_classification"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" size="medium" @click="showMap">选 择 位 置</el-button>
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </el-dialog>

      <el-dialog :title="mapTitle" :visible.sync="openMap" width="800px" height="700px" append-to-body>
        <template>
          <div>
              <baidu-map class="map" :center="map.center" :zoom="map.zoom" @ready="handler">
                  <!--缩放-->
                  <bm-navigation anchor="BMAP_ANCHOR_TOP_LEFT"></bm-navigation>
                  <!--定位-->
                  <bm-geolocation anchor="BMAP_ANCHOR_BOTTOM_RIGHT" :showAddressBar="true" :autoLocation="true"></bm-geolocation>
                  <!--点-->
                  <bm-marker :position="map.center" :dragging="map.dragging" animation="BMAP_ANIMATION_DROP">
                      <!--提示信息-->
                      <bm-info-window :show="map.show">当前行政区中心</bm-info-window>
                  </bm-marker>
              </baidu-map>
          </div>
        </template>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="submitMap">确 定</el-button>
          <el-button @click="cancelMap">取 消</el-button>
        </div>
      </el-dialog>
    </div>
  </template>
  
  <script>
  import { listRegion, getRegion, delRegion, addRegion, updateRegion, repealRegion, auditRegion } from "@/api/standard/region";
  
  export default {
    name: "Region",
    dicts: [ 'gs_urban_rural_classification', 'gs_deprecated', 'gs_level_list'],
    data() {
      return {
        needPan: false,
        lastCenter: {lng: 0, lat:0},
        //地图
        map: {
          center: {lng: 106.63,lat: 26.65},
          zoom: 15,
          show: true,
          dragging: true
        },
        mapTitle: null,
        mapObj: undefined,
        // 遮罩层
        loading: true,
        // 选中数组
        ids: [],
        // 展开
        isExpandAll: false,
        // 非单个禁用
        single: true,
        // 非多个禁用
        multiple: true,
        // 显示搜索条件
        showSearch: true,
        // 总条数
        total: 0,
        // 行政区表格数据
        regionList: [],
        // 弹出层标题
        title: "",
        // 添加/修改模式
        mode: 'add',
        // 是否显示弹出层
        open: false,
        openMap: false,
        // 提交状态时间范围
        daterangeCreateTime: [],
        // 提交状态时间范围
        daterangeLastUpdateTime: [],
        // 行政区列表
        provinceList: [],
        cityList: [],
        districtList: [],
        streetList: [],
        neighborhoodList: [],
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          parentName: null,
          police: null,
          name: null,
          fullName: null,
          firstLetter: null,
          type: null,
          hasChild: null,
          createTime: null,
          lastUpdateTime: null,
          auditUser: null,
          auditUserId: null,
          auditUserPhone: null,
          commitStatus: null
        },
        temp: {
          lat: undefined,
          lng: undefined
        },
        // 表单参数
        form: {
            level: undefined,
            provinceIsSelected: false,
            cityIsSelected: false,
            districtIsSelected: false,
            streetIsSelected: false
      },
        // 下拉框查询参数
        constrain: {
            level: undefined,
            parentCode: undefined
        },
        // 表单校验
        rules: {
          level: [
            { required: true, message: "行政区级别不能为空", trigger: "change" }
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
          code: [
            { required: true, message: "行政区编码不能为空", trigger: "blur" },
            { pattern: /^[+]?(0|([1-9]\d*))?$/, message: "请输入正确数字", trigger: "blur"}
          ],
          parentName: [
            { required: true, message: "父行政区名称不能为空", trigger: "blur" }
          ],
          name: [
            { required: true, message: "行政区简称不能为空", trigger: "blur" }
          ],
          fullName: [
            { required: true, message: "行政区全称不能为空", trigger: "blur" }
          ],
          firstLetter: [
            { required: true, message: "拼音首字母不能为空", trigger: "blur" }
          ],
          lat: [
            { required: true, message: "纬度不能为空", trigger: "blur" }
          ],
          lng: [
            { required: true, message: "经度不能为空", trigger: "blur" }
          ]
        }
      };
    },
    created() {
      this.getList();
      this.getProvinceList();
    },
    methods: {
      /** 查询行政区列表 */
      getList() {
        this.loading = true;
        this.queryParams.params = {};
        if (null != this.daterangeCreateTime && '' != this.daterangeCreateTime) {
          this.queryParams.params["beginCreateTime"] = this.daterangeCreateTime[0];
          this.queryParams.params["endCreateTime"] = this.daterangeCreateTime[1];
        }
        listRegion(this.queryParams).then(response => {
          this.regionList = this.handleTree(response.data, "code", "parentCode");
          this.loading = false;
        });
      },
      getProvinceList() {
        this.constrain.level = 0;
        listRegion(this.constrain).then(response => {
            this.provinceList = response.data;
        });
      },
      /** 初始化地图 */
      handler ({BMap, map}) {
        if(this.lastCenter.lat === 0) {
            this.lastCenter.lat = this.map.center.lat;
            this.lastCenter.lng = this.map.center.lng;
            this.needPan = false;
          }
        // 获取地图对象
        this.mapObj = map;
        map.centerAndZoom(this.map.center, this.map.zoom);
        this.mapTitle = "中心选择";

        // 鼠标缩放
        map.enableScrollWheelZoom(true);

        // 点击事件获取经纬度
        map.addEventListener('click', (e) => {    
          alert("所选处经纬度：(" + e.point.lng + ", " + e.point.lat + ")");
          // 先清除其他点
          let length = map.getOverlays().length;
          for(let i=1; i<length; i++) {              
            map.removeOverlay(map.getOverlays()[1]);              
          }
          //再新加点
          let marker = new BMap.Marker(e.point);
          map.addOverlay(marker);
          let label = new BMap.Label("经度：" + String(e.point.lng) + ", 纬度：" + String(e.point.lat), {
            position: e.point,
            offset: new BMap.Size(10, 20)
          });
          map.addOverlay(label);
          this.temp.lat = e.point.lat;
          this.temp.lng = e.point.lng;
          this.map.center.lat = e.point.lat;
          this.map.center.lng = e.point.lng;
          map.centerAndZoom(this.map.center, this.map.zoom);
        });

      },
      levelSelected(value) {
        this.reset();
        this.mode = 'add';
        this.form.level = value;
        this.form.lat = this.map.center.lat;
        this.form.lng = this.map.center.lng;
      },
      provinceSelected(value) {
          if(this.form.level == 1) {
            this.form.parentCode = value;
            this.form.hasChild = 1;
            for(let singleProvince of this.provinceList) {
              if(singleProvince.code == value) {
                this.form.parentName = singleProvince.name;
                this.map.center.lat = singleProvince.lat;
                this.map.center.lng = singleProvince.lng;
                
                this.form.lat = this.map.center.lat;
                this.form.lng = this.map.center.lng;
                break;
              }
            }
          }
          this.form.provinceIsSelected = true;
          this.form.cityCode = undefined;    
          this.form.cityIsSelected = false;
          this.form.districtCode = undefined;
          this.form.districtIsSelected = false;
          this.form.streetCode = undefined;
          this.form.streetIsSelected = false;
          this.form.neighborhoodCode = undefined;
          let constrain = {};
          constrain.parentCode = value;
          listRegion(constrain).then(response => {
            this.cityList = response.data;
          })
          this.$forceUpdate();
      },
      citySelected(value) {
        if(this.form.level == 2) {
            this.form.parentCode = value;
            this.form.hasChild = 1;
            for(let singleCity of this.cityList) {
              if(singleCity.code == value) {
                this.form.parentName = singleCity.name;
                this.map.center.lat = singleCity.lat;
                this.map.center.lng = singleCity.lng;
                this.form.lat = this.map.center.lat;
                this.form.lng = this.map.center.lng;
                break;
              }
            }
          }
          this.form.cityIsSelected = true;
          this.form.districtCode = undefined;
          this.form.districtIsSelected = false;
          this.form.streetCode = undefined;
          this.form.streetIsSelected = false;
          this.form.neighborhoodCode = undefined;
          let constrain = {};
          constrain.parentCode = value;
          listRegion(constrain).then(response => {
            this.districtList = response.data;
          })
          this.$forceUpdate();
      },
      districtSelected(value) {
          if(this.form.level == 3) {
            this.form.parentCode = value;
            this.form.hasChild = 1;
            for(let singleDistrict of this.districtList) {
              if(singleDistrict.code == value) {
                this.form.parentName = singleDistrict.name;
                this.map.center.lat = singleDistrict.lat;
                this.map.center.lng = singleDistrict.lng;
                this.form.lat = this.map.center.lat;
                this.form.lng = this.map.center.lng;
                break;
              }
            }
          }
          this.form.districtIsSelected = true;
          this.form.streetCode = undefined;
          this.form.streetIsSelected = false;
          this.form.neighborhoodCode = undefined;
          let constrain = {};
          constrain.parentCode = value;
          listRegion(constrain).then(response => {
            this.streetList = response.data;
          })
          this.$forceUpdate();
      },
      streetSelected(value) {
        if(this.form.level == 4) {
            this.form.parentCode = value;
            for(let singleStreet of this.streetList) {
              if(singleStreet.code == value) {
                this.form.parentName = singleStreet.name;
                this.map.center.lat = singleStreet.lat;
                this.map.center.lng = singleStreet.lng;
                this.form.lat = this.map.center.lat;
                this.form.lng = this.map.center.lng;
                break;
              }
            }
          }
          this.form.streetIsSelected = true;
          this.form.neighborhoodCode = undefined;
          let constrain = {};
          constrain.parentCode = value;
          listRegion(constrain).then(response => {
            this.form.neighborhoodList = response.data;
          })
          this.$forceUpdate();
      },
      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      cancelMap() {
        this.openMap = false;
      },
      // 表单重置
      reset() {
        this.form = {
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
          level: null,
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
          neighborhood: undefined,
          provinceIsSelected : false,
          cityIsSelected : false,
          districtIsSelected : false,
          streetIsSelected : false,
          oldCode: undefined,
          deprecated: null
        };
        this.resetForm("form");
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },
      /** 重置按钮操作 */
      resetQuery() {
        this.daterangeCreateTime = [];
        this.daterangeLastUpdateTime = [];
        this.resetForm("queryForm");
        this.handleQuery();
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加行政区";
        this.mode = 'add';
      },
      /** 打开地图操作 */
      showMap() {
        this.title = "打开地图";
        this.openMap = true;
        if(this.mapObj) {
          this.mapObj.centerAndZoom(this.map.center, this.map.zoom);
          if(this.lastCenter.lat === 0) {
            this.lastCenter.lat = this.map.center.lat;
            this.lastCenter.lng = this.map.center.lng;
            this.needPan = false;
          }
          else if(this.map.center.lat == this.lastCenter.lat && this.map.center.lng == this.lastCenter.lng) {
            this.needPan = false;
          } else {
            this.lastCenter.lat = this.map.center.lat;
            this.lastCenter.lng = this.map.center.lng;
            this.needPan = true;
          }

          if(this.needPan) {
            this.mapObj.panBy(400, 300);
          }
          let length = this.mapObj.getOverlays().length;
          for(let i=1; i<length; i++) { 
              this.mapObj.removeOverlay(this.mapObj.getOverlays()[1]);              
          }
          
        }
        
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
          this.reset();
          this.mode = 'edit';
          const code = row.code;
          
          getRegion(code).then(response => {
            this.form = response.data;
            this.form.oldCode = this.form.code;
            this.map.center.lat = this.form.lat;
            this.map.center.lng = this.form.lng;
            this.open = true;
            this.title = "修改行政区";
            if(this.form.provinceCode) {
              let constrain1 = {};
              constrain1.parentCode = this.form.provinceCode;
              listRegion(constrain1).then(response => {
                this.cityList = response.data;
              })
            }
            if(this.form.cityCode) {
              let constrain2 = {};
              constrain2.parentCode = this.form.cityCode;
              listRegion(constrain2).then(response => {
                this.districtList = response.data;
              })
            }
            if(this.form.districtCode) {
              let constrain3 = {};
              constrain3.parentCode = this.form.districtCode;
              listRegion(constrain3).then(response => {
                this.streetList = response.data;
              })
            }
          });        
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if(this.form.level == 0) {
          this.form.parentCode = 0;
        } else if(this.form.level == 1) {
          this.form.parentCode = this.form.provinceCode;
        } else if(this.form.level == 2) {
          this.form.parentCode = this.form.cityCode;
        } else if(this.form.level == 3) {
          this.form.parentCode = this.form.districtCode;
        } else if(this.form.level == 4) {
          this.form.parentCode = this.form.streetCode;
        }
          if (valid) {
            if (this.form.regionId != null) {
              updateRegion(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addRegion(this.form).then(response => {
                this.$modal.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              });
            }
            this.reset();
          }
        });
      },
      submitMap() {
        this.openMap = false;
        let length = this.mapObj.getOverlays().length;
        for(let i=1; i<length; i++) { 
            this.mapObj.removeOverlay(this.mapObj.getOverlays()[1]);
          }
        this.form.lat = this.temp.lat;
        this.form.lng = this.temp.lng;
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        const code = row.code;
        this.$modal.confirm('是否确认删除行政区编号为"' + code + '"的数据项？').then(function() {
          return delRegion(code);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
      },
      handleDeprecatedChange(row) {
        let text = row.deprecated === "0" ? "启用" : "作废";
        this.$modal.confirm('确认要"' + text + '""' + row.name + '"行政区吗？').then(function() {
          return repealRegion(row.regionId, row.code, row.deprecated);
        }).then(() => {
          this.$modal.msgSuccess(text + "成功");
        }).catch(function() {
          row.deprecated = row.deprecated === "0" ? "1" : "0";
        });
      },
      handleAuditStateChange(row) {
        if(row.auditState == "1") {
          let text = "入库";
          this.$modal.confirm('确认要"' + text + '""' + row.name + '"行政区吗？(入库后不可更改入库状态)').then(function() {
            return auditRegion(row.code);
          }).then(() => {
            this.$modal.msgSuccess(text + "成功");
          }).catch(function() {
            row.auditState = "0";
          });
        } else {
          alert("不可重复入库！");
          return;
        }
      },
      handleExport() {
        this.download('standard/region/export', {
          ...this.queryParams
        }, `role_${new Date().getTime()}.xlsx`)
      },
      handleModificationHistory(row) {
        const modificationRegionId = row.regionId;
        const code = row.code;
        const params = { pageNum: this.queryParams.pageNum};
        this.$tab.openPage("行政区编码为[" + code + "]的修改痕迹", '/standard/region/modificationHistory/'  + modificationRegionId, params);

      }

    }
  };
  </script>

<style scoped>
.map {
    width: 100%;
    height: 600px;
}
</style>
  