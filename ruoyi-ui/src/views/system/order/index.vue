<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="" prop="userName">
        <el-input
          v-model="queryParams.userName"
          placeholder="请输入用户名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
      </el-form-item>
    </el-form>

    <el-table  v-loading="loading" :data="orderList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column align="center" prop="userName" label="用户名" />
      <el-table-column align="center" prop="cinemaFilmId" label="关联ID" />
      <el-table-column align="center" prop="ticketNum" label="购票数量" />
      <el-table-column align="center" prop="ticketPrice" label="影票价格" />
      <el-table-column align="center" prop="filmName" label="电影名称" />
      <el-table-column align="center" prop="filmId" label="电影ID" />
      <el-table-column label="座位信息" align="center">
        <template #default="{ row }">
          <div v-for="(singleSeat, index) in row.seat" :key="index">
            <span>第{{ singleSeat.rowNum + 1 }}排第{{ singleSeat.columnNum + 1}}座</span>
          </div>
        </template>
      </el-table-column>
    <el-table-column prop="orderDate" label="下单时间" align="center">
      <template #default="{ row }">
        <span>{{ formatDateTime(row.orderDate) }}</span>
      </template>
    </el-table-column>
      <el-table-column align="center" prop="cinemaAddr" label="影院地址" />
      <el-table-column align="center" prop="cinemaName" label="影院名称" />
      <el-table-column align="center" prop="cinemaId" label="影院ID" />
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
  </div>
</template>
<script>
import { listOrders, queryOrderByUserName } from "@/api/system/order";
import moment from 'moment';
  export default {
    name: "Order",
    dicts: ['sys_normal_disable'],
    data() {
        return  {
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
            // // 影院表单数据
            // cinemaList: [],
            // 弹出层标题
            title: "",
            // 是否显示弹出层
            open: false,
            // 查询参数
            queryParams: {
                pageNum: 1,
                pageSize: 10,
            },
        }
    },
    created() {
      this.getList();
    },
    methods: {
      formatDateTime(dateStr) {
        return moment(dateStr).format('YYYY-MM-DD HH:mm');
      },
      getList() {
            this.loading=true,
            listOrders().then(response => {
              this.orderList = response.rows.map(item => ({
                ...item.order,
                seat: item.seat
            }));
                this.total = response.total;
                this.loading = false;
            })
        },
        handleQuery() {
          const userName = this.queryParams.userName;
          if (userName == "") {
            this.getList();
          } else {
            queryOrderByUserName(userName).then(response => {
              // 检查response是否存在且data属性是数组类型，同时还要检查data数组是否为空
              if (response && response.hasOwnProperty('data') && Array.isArray(response.data)) {
                if (response.data.length > 0) {
                  this.orderList = response.data.map(item => ({
                    ...item.order,
                    seat: item.seat
                  }));
                  this.total = this.orderList.length;
                } else {
                  alert("没有当前用户信息");
                  this.orderList = []; // 设置为空数组，避免后续引用时报错
                  this.total = 0;
                }
                this.loading = false;
              } else {
                console.error('Error searching Order: Invalid response structure or no data found');
                this.orderList = []; // 设置为空数组，避免后续引用时报错
                this.total = 0;
                this.loading = false;
              }
            })
            .catch(error => {
              console.error('Error searching Order:', error);
              this.orderList = []; // 设置为空数组，避免后续引用时报错
              this.total = 0;
              this.loading = false;
            });
          }
        }
    }
  }
</script>