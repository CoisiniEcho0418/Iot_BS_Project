<template>
  <div class="overview">
    <!-- 第一行 -->
    <div class="first_row">
      <!-- 第一行第一个card -->
      <div class="block1">
        <el-card class="info">
          <el-button type="primary"
                     icon="el-icon-user-solid"
                     circle />
          <h2 class="num-info">112356</h2>
          <p class="desc">总设备数</p>
        </el-card>
        <el-card class="info">
          <el-button type="success"
                     icon="el-icon-s-data"
                     circle />
          <h2 class="num-info">66789</h2>
          <p class="desc">活跃设备数</p>
        </el-card>
        <el-card class="info">
          <el-button type="danger"
                     icon="el-icon-coin"
                     circle />
          <h2 class="num-info">12457</h2>
          <p class="desc">总消息数</p>
        </el-card>
      </div>

      <!-- 第一行第二个card -->
      <div class="block2"
           id="first-chart">
        <!-- 利用Echarts来生成图表 -->
        <!-- <el-card class="e-chart"
                 id="first-chart"></el-card> -->
      </div>
    </div>

    <!-- 第二行 -->
    <div class="second_row">
      <!-- 第二行第一个card -->
      <div class="block3"
           id="second-chart">
      </div>

      <!-- 第二行第二个card -->
      <div class="block4"
           id="third-chart">
      </div>
    </div>
  </div>
</template>

<script>
export default {
  components: {},
  props: {},
  data () {
    return {
    };
  },
  watch: {},
  computed: {},
  methods: {
    // 显示第一个图表信息
    showFirstEcharts () {
      var chartDom = document.getElementById('first-chart');
      var myChart = this.$echarts.init(chartDom);
      var option;

      option = {
        // title: {
        //   text: 'Stacked Line'
        // },
        tooltip: {
          trigger: 'axis'
        },
        legend: {
          data: ['Email', 'Union Ads', 'Video Ads', 'Direct', 'Search Engine']
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        toolbox: {
          feature: {
            saveAsImage: {}
          }
        },
        xAxis: {
          type: 'category',
          boundaryGap: false,
          data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            name: 'Email',
            type: 'line',
            stack: 'Total',
            data: [120, 132, 101, 134, 90, 230, 210]
          },
          {
            name: 'Union Ads',
            type: 'line',
            stack: 'Total',
            data: [220, 182, 191, 234, 290, 330, 310]
          },
          {
            name: 'Video Ads',
            type: 'line',
            stack: 'Total',
            data: [150, 232, 201, 154, 190, 330, 410]
          },
          {
            name: 'Direct',
            type: 'line',
            stack: 'Total',
            data: [320, 332, 301, 334, 390, 330, 320]
          },
          {
            name: 'Search Engine',
            type: 'line',
            stack: 'Total',
            data: [820, 932, 901, 934, 1290, 1330, 1320]
          }
        ]
      };
      myChart.setOption(option);

    },
    // 显示第二个图表
    showSecondEcharts () {
      var chartDom = document.getElementById('second-chart');
      var myChart = this.$echarts.init(chartDom);
      var option;

      option = {
        tooltip: {
          trigger: 'item'
        },
        legend: {
          top: '5%',
          left: 'center'
        },
        series: [
          {
            name: 'Access From',
            type: 'pie',
            radius: ['40%', '70%'],
            avoidLabelOverlap: false,
            itemStyle: {
              borderRadius: 10,
              borderColor: '#fff',
              borderWidth: 2
            },
            label: {
              show: false,
              position: 'center'
            },
            emphasis: {
              label: {
                show: true,
                fontSize: 40,
                fontWeight: 'bold'
              }
            },
            labelLine: {
              show: false
            },
            data: [
              { value: 1048, name: 'Search Engine' },
              { value: 735, name: 'Direct' },
              { value: 580, name: 'Email' },
              { value: 484, name: 'Union Ads' },
              { value: 300, name: 'Video Ads' }
            ]
          }
        ]
      };
      myChart.setOption(option);

    },
    // 显示第三个图表
    showThirdEcharts () {
      var chartDom = document.getElementById('third-chart');
      var myChart = this.$echarts.init(chartDom);
      var option;

      option = {
        legend: {},
        tooltip: {},
        dataset: {
          source: [
            ['product', '2021', '2022', '2023'],
            ['Matcha Latte', 43.3, 85.8, 93.7],
            ['Milk Tea', 83.1, 73.4, 55.1],
            ['Cheese Cocoa', 86.4, 65.2, 82.5],
            ['Walnut Brownie', 72.4, 53.9, 39.1]
          ]
        },
        xAxis: { type: 'category' },
        yAxis: {},
        // Declare several bar series, each will be mapped
        // to a column of dataset.source by default.
        series: [{ type: 'bar' }, { type: 'bar' }, { type: 'bar' }]
      };
      myChart.setOption(option);

    },

  },
  created () {
  },
  mounted () {
    this.showFirstEcharts();
    this.showSecondEcharts();
    this.showThirdEcharts();
  }
};
</script>
<style scoped>
.overview {
  margin-top: 20px;
  display: flex;
  flex-direction: column;
  height: 100%;
}
.first_row {
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 20px 20px;
  height: 300px;
}
.block1 {
  flex: 1;
  display: flex;
  justify-content: space-around; /* 均匀排列每个元素 */
  background: white;
  min-width: 200px;
  height: 100%;
  padding: 20px 20px;
  margin-right: 30px;
  border-radius: 10px;
}
.block2 {
  flex: 1;
  display: flex;
  justify-content: space-around; /* 均匀排列每个元素 */
  background: white;
  min-width: 200px;
  height: 100%;
  width: 100%;
  padding: 20px 20px;
  border-radius: 10px;
}
.info {
  background: white;
  width: 26%;
  height: 70%;
  align-self: center;
  text-align: center;
  box-sizing: border-box;
  display: flex;
  align-items: center;
  justify-content: center;
}
/* .e-chart {
  padding-top: 10px;
  width: 50%;
  height: auto;
  min-width: 500px;
} */
.second_row {
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 20px 20px;
  height: 300px;
  margin-top: 20px;
}
.block3 {
  flex: 1;
  display: flex;
  justify-content: center;
  align-items: center;
  background: white;
  min-width: 200px;
  height: 100%;
  width: 100%;
  padding: 20px 20px;
  border-radius: 10px;
  margin-right: 30px;
}
.block4 {
  flex: 1;
  display: flex;
  justify-content: space-around; /* 均匀排列每个元素 */
  background: white;
  min-width: 200px;
  height: 100%;
  width: 100%;
  padding: 20px 20px;
  border-radius: 10px;
}
</style>