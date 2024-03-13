<template>
    <div class="app-container">
      <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
        <el-form-item label="" prop="cinemaName">
          <el-input
            v-model="queryParams.cinemaName"
            placeholder="请输入影院名称"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
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
            v-hasPermi="['system:cinema:add']"
          >新增</el-button>
        </el-col>
        <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
      </el-row>
  
      <el-table v-loading="loading" :data="cinemaList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="影院代码" align="center" prop="cinemaId" />
        <el-table-column label="影院名称" align="center" prop="cinemaName" />
        <el-table-column label="影院地址" align="center" prop="cinemaAddr" />
        <el-table-column label="位置经度" align="center" prop="longitude" />
        <el-table-column label="位置纬度" align="center" prop="altitude" />
        <el-table-column label="影厅" align="center" prop="cinemaHall" >
            <template slot-scope="scope">
                {{ scope.row.cinemaHall | truncate(10) }}
              </template>
        </el-table-column>
        <el-table-column label="影院标签" align="center" prop="cinemaTag" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleUpdate(scope.row)"
              v-hasPermi="['system:cinema:update']"
            >修改</el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
              v-hasPermi="['system:cinema:delete']"
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

    <!--修改电影院对话框 -->
    <el-dialog :title="title" :visible.sync="updateOpen" width="500px" append-to-body>
        <el-form ref="updateForm" :model="updateForm" :rules="updateRules" label-width="80px">
          <el-form-item label="影院名称" prop="cinemaName">
            <el-input v-model="updateForm.cinemaName" placeholder="请输入影院名称" />
          </el-form-item>
          <el-form-item label="影院地址" prop="cinemaAddr">
            <el-input v-model="updateForm.cinemaAddr" placeholder="请输入影院地址" />
          </el-form-item>
          <el-form-item label="影院标签" prop="cinemaTag">
            <el-input v-model="updateForm.cinemaTag" placeholder="请输入影院标签" />
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="submitUpdateForm">确 定</el-button>
          <el-button @click="cancelUpdate">取 消</el-button>
        </div>
      </el-dialog>

      <!-- 新增电影院对话框 -->
      <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
        <el-form ref="addForm" :model="addForm" :rules="addRules" label-width="80px">
          <el-form-item label="影院名称" prop="cinemaName">
            <el-input v-model="addForm.cinemaName" placeholder="请输入影院名称" />
          </el-form-item>
          <el-form-item label="影院地址" prop="cinemaAddr">
            <el-input v-model="addForm.cinemaAddr" placeholder="请输入影院地址" />
          </el-form-item>
          <el-form-item label="经度" prop="longitude">
            <el-input v-model.number="addForm.longitude" placeholder="请输入经度" />
          </el-form-item>
          <el-form-item label="纬度" prop="altitude">
            <el-input v-model.number="addForm.altitude" placeholder="请输入纬度" />
          </el-form-item>
          <el-form-item label="影厅信息" prop="cinemaHall">
            <el-input v-model="addForm.cinemaHall" placeholder="请输入影厅信息" />
          </el-form-item>
          <el-form-item label="影院标签" prop="cinemaTag">
            <el-input v-model="addForm.cinemaTag" placeholder="请输入影院标签" />
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="submitAddForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </el-dialog>
    </div>
  </template>
    
  <script>
  import { listCinema, getCinema, updateCinema, addCinema, delCinema, queryCinemasByCinemaName } from "@/api/system/cinema";
  
  export default {
    name: "Cinema",
    filters: {
    truncate(value, length) {
      if (!value || value.length <= length) {
        return value;
      } else {
        return value.substring(0, length) + '...';
      }
    }
  },
    dicts: ['sys_normal_disable'],
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
        // // 影院表单数据
        // cinemaList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        updateOpen: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
        },
        // 修改表单参数
        updateForm: {},
        // 修改表单校验
        updateRules: {
            cinemaName: [
                { required: true, message: '请输入影院名称', trigger: 'blur' }
            ],
            cinemaAddr: [
                { required: true, message: '请输入影院地址', trigger: 'blur' }
            ],
            cinemaTag: [
                { required: true, message: '请输入影院标签', trigger: 'blur' }
            ]
        },
        addForm: {},
        addRules: {
          cinemaName: [
            { required: true, message: '请输入影院名称', trigger: 'blur' }
          ],
          cinemaAddr: [
            { required: true, message: '请输入影院地址', trigger: 'blur' }
          ],
          longitude: [
            { required: true, message: '请输入经度', trigger: 'blur' }
          ],
          altitude: [
            { required: true, message: '请输入纬度', trigger: 'blur' }
          ],
          cinemaHall: [
            { required: true, message: '请输入影厅信息', trigger: 'blur' }
          ],
          cinemaTag: [
            { required: true, message: '请输入影院标签', trigger: 'blur' }
          ]
        }
      };
    },
    created() {
        this.getList(); // 页面创建时获取电影数据
    },
  methods: {
    getList() {
      this.loading = true;
      listCinema().then(response => {
        this.cinemaList = response.rows;
        this.total = response.total;
        this.loading = false;
      })
    },
    // 表单重置
    updateReset() {
      this.updateForm = {
          cinemaId: '',
          cinemaName: '',
          cinemaAddr: '',
          cinemaTag: ''
        };
        this.resetForm("updateForm");
      },
      // 重置添加表单
      addReset() {
        this.addForm = {
          cinemaName: '',
          cinemaAddr: '',
          longitude: '',
          altitude: '',
          cinemaHall: '',
          cinemaTag: ''
        };
        this.resetForm("addForm")
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.updateReset();
        const cinemaId = row.cinemaId || this.ids
        getCinema(cinemaId).then(response => {
          this.updateForm = response.data;
          this.updateOpen = true;
          this.title = "修改影院信息";
        });
      },
      cancelUpdate() {
        this.updateOpen = false;
        this.updateReset();
      },
      handleAdd() {
        this.addReset();
        this.open=true;
        this.title="添加影院";
      },
      /** 搜索按钮操作 */
      handleQuery() {
        // 获取用户输入的电影名称
        const cinemaName = this.queryParams.cinemaName;
        if (cinemaName == "") {
          this.getList();
        } else {
          // 调用相应的 API 方法来搜索符合条件的电影信息
          queryCinemasByCinemaName(cinemaName)
            .then(response => {
                // 检查返回的数据是否是数组类型，并且具有 length 属性
                if (Array.isArray(response.data) && response.data.hasOwnProperty('length')) {
                      this.cinemaList = response.data;
                      console.log(this.cinemaList);
                      this.total = this.cinemaList.length;
                      this.loading = false; // 关闭loading状态
                    } else {
                      console.error('Error searching cinemas: response data is not in expected format');
                    }
            })
            .catch(error => {
              console.error('Error searching cinemas:', error);
              this.loading = false; // 关闭loading状态
            });
            
        }

      },
      /** 修改影院信息 */
      submitUpdateForm: function() {
        this.$refs["updateForm"].validate(valid => {
          if (valid) {
            if (this.updateForm.cinemaId != undefined) {
              updateCinema(this.updateForm).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.updateOpen = false;
                this.getList();
              });
            }
          }
        });
      },
      // 新增影院
      submitAddForm() {
        this.$refs['addForm'].validate(valid => {
          if (valid) {
            addCinema(this.addForm).then(response => {
              this.$message.success('新增影院成功');
              this.open = false; // 关闭新增电影对话框
              this.getList(); // 刷新电影列表
            }).catch(error => {
              this.$message.error('新增电影失败');
            });
          } else {
            return false;
          }
        });
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        const cinemaIds = row.cinemaId || this.ids;
        this.$modal.confirm('是否确认删除影院的编号为"' + cinemaIds + '"的数据项？').then(function() {
          return delCinema(cinemaIds);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
      },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
  },
  
  };
  </script>
  