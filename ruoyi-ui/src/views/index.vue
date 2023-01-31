<template>
    <div class="chart pt-10">
      <div class="white text-center" :style="{'width':'100%','fontSize':'24px'}">
        Vue + Echarts 实现中国地图多级钻取功能
      </div>
      <div id="mapChart" class="mapChart"></div>
      <div class="mapChoose">
        <span v-for="(item,index) in selectedMaps" :key="item.code">
          <span class="title" @click="chooseMap(item,index)">{{item.name == 'guizhou' ? '贵州' : item.name}}</span>
          <span class="icon" v-show="index + 1 != selectedMaps.length">></span>
        </span>
      </div>
    </div>
  </template>
  
  <script>
  import axios from "axios";
  import mapCode from '@/assets/js/mapCode.js';
  import { _debounce } from '@/utils/common.js'
  
  
  export default {
    data(){
      return{
        myChart: null,
        guizhouCode:520000,
        curMapName:'guizhou', // 当前地图名
        selectedMaps:[
          {
            name:'guizhou',
            code:'520000',
          }
        ],
      }
  
    },
    methods:{
      // 绘制地图
      drawMapChart(mapName,mapJSON){
        if (this.myChart != null && this.myChart != "" && this.myChart != undefined) {
            this.myChart.dispose();
        }

        this.myChart = this.$echarts.init(document.getElementById('mapChart'));
        this.$echarts.registerMap(mapName,mapJSON);
        this.myChart.setOption({
            tooltip: {
                formatter:function(params,ticket, callback){
                    let population = 0
                    for(let i=0; i<mapCode.length; i++) {
                        if(mapCode[i].name == params.name) {
                            population = mapCode[i].population;
                            break;
                        }
                    }
                    return params.data.name+'<br />人口'+ ':' + population
                }//数据格式化
            },
            /*dataRange: {
                splitList: [
                    {start: 0, end: 100000, color: '#E0E0E0'},
                    {start: 100000, end: 300000, color: '#F3A482'},
                    {start: 300000, end: 500000, color: '#FCDBC7'},
                    {start: 500000, end: 700000, color: '#92C5DE'},
                    {start: 700000, end: 900000, color: '#5996B7'},
                    {start: 900000, color: '#2066AC'}
                ]
            },*/
            visualMap: {
				min: 0,
				max: 1000000,
				// text: ['100', '0'],
				realtime: false,
				calculable: true,
				inRange: {
					color: ['#A4C8FF', '#71A9FF']
				}
			},
            series: [
                {
                type: 'map',
                map: mapName,
                roam: true,  // 是否开启鼠标缩放和平移漫游
                selectedMode: 'false', // 是否允许选中多个区域
                label:{
                    show:true,
                    color:'#a8e6e1',
                },
                emphasis:{
                    label:{
                    show:true,
                    color:'#ffffff',
                    }
                },
                itemStyle: {
                    normal: {
                    areaColor: "rgba(23, 27, 57,0)",
                    borderColor: "#1dc199",
                    borderWidth: 1
                    }
                },
                data: this.initMapData(mapJSON),
                }
            ]
        });
  
        
  
        this.myChart.on('click', (params) => {
            let map = null;
            for(let i=0; i<mapCode.length; i++) {
                if(mapCode[i].name == params.name) {
                    map = mapCode[i].code;
                    break;
                }
            }
            const files = require.context('/public/json/map/', false, /\.json/);
            const filedata = files.keys();
            let code_exist = false;
            filedata.forEach((item) => {
                if(item === './' + map + '.json') {
                    code_exist = true;
                }
            });
            if(code_exist){
                this.curMapName = params.name;
                this.getMapData(map);
    
                // 为地图标题菜单存入（过滤同一地图多次点击情况）点击地图信息
                let selectedCodes = [];
                this.selectedMaps.forEach( item => selectedCodes.push(item.code));
                if(!selectedCodes.includes(map)){
                this.$set(this.selectedMaps,this.selectedMaps.length,{name: this.curMapName, code: map}); 
                }
                
            }else{
                this.$message({message: '暂无地图数据',type: 'warning',showClose: true});
            }
        });
      },
      initMapData(mapJson) {
        let mapData = [];
        for (let i = 0; i < mapJson.features.length; i++) {
            let population = 0;
            for(let j=0; j<mapCode.length; j++) {
                if(mapJson.features[i].properties.name == mapCode[j].name) {
                    population = mapCode[j].population;
                    break;
                }
            }
            mapData.push({ name: mapJson.features[i].properties.name, value: population});
        }
        return mapData;
      },
      // 地图标题菜单点击
      chooseMap(item,index){
        this.selectedMaps.splice(index + 1); 
        this.getMapData(item.code)
      },
      // 浏览器窗口大小改变时，重新加载图表以自适应
      resizeCharts:_debounce(function(){
        this.$echarts.init(document.getElementById('mapChart')).resize()
      },500),
      // 获取地图数据
      getMapData(map){
        axios.get(`/json/map/${map}.json`).then((res) => {
          if(res.status == 200){
            const mapJSON = res.data;
            this.drawMapChart(this.curMapName,mapJSON);
          } 
        }).catch((err) => {
          this.$message({message: err,type: 'error',showClose: true});
        })
      }
    },
    mounted(){
      this.getMapData(this.guizhouCode);
      window.addEventListener('resize',this.resizeCharts);
    },
    beforeDestroy() {
      window.addEventListener('resize',this.resizeCharts);
    },
  }
  </script>
  
  
  <style lang="scss" scoped>
  .chart{
    background-image: url('../assets/images/bgImg.png');
    background-size: 100% 100%;
    #mapChart{
      width: 100%;
      height: 900px;
    }
    .mapChoose {
      position: absolute;
      left: 20px;
      top: 55px;
      color: #eee;
      .title {
        padding: 5px;
        border-top: 1px solid rgba(132, 219, 233, 0.8);
        border-bottom: 1px solid rgba(147, 235, 248, 0.8);
        cursor: pointer;
      }
      .icon {
        font-family: 'simsun';
        font-size: 25px;
        margin: 0 11px;
      }
    }
  }
  </style>