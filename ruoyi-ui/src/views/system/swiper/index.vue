<template>
    <div class="app-container">
        <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
          <el-form-item label="" prop="filmName">
            <el-input
              v-model="queryParams.filmName"
              placeholder="请输入电影名称"
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
              v-hasPermi="['system:swiper:add']"
            >新增</el-button>
          </el-col>
          <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>
    
        <el-table v-loading="loading" :data="swiperList" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55" align="center" />
          <el-table-column label="轮播图ID" align="center" prop="swiperId" />
          <el-table-column label="电影海报" align="center" prop="filmImage">
            <template slot-scope="scope">
              <img :src="scope.row.filmImage" alt="电影海报" style="width: 100px; height: auto;">
            </template>
          </el-table-column>
          <el-table-column label="电影名称" align="center" prop="filmName" />
          <el-table-column label="电影ID" align="center" prop="filmId" />
          <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="handleUpdate(scope.row)"
                v-hasPermi="['system:swiper:update']"
              >修改</el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
                v-hasPermi="['system:swiper:delete']"
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

      <!-- 修改轮播图 -->
    <el-dialog :title="title" :visible.sync="updateOpen" width="500px" append-to-body>
        <el-form ref="updateForm" :model="updateForm" :rules="updateRules" label-width="80px">
          <el-form-item label="电影海报" prop="filmImage">
            <el-input v-model="updateForm.filmImage" placeholder="请输入修改电影海报的URL" />
          </el-form-item>
          <el-form-item label="电影名称" prop="filmName">
            <el-input v-model="updateForm.filmName" placeholder="请输入电影名称" />
          </el-form-item>
          <el-form-item label="电影Id" prop="filmId">
            <el-input v-model="updateForm.filmId" placeholder="请输入电影Id" />
          </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button type="primary" @click="submitUpdateForm">确 定</el-button>
            <el-button @click="cancelUpdate">取 消</el-button>
          </div>
      </el-dialog>

      <!-- 新增轮播图 -->
      <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
        <el-form ref="addForm" :model="addForm" :rules="addRules" label-width="80px">
          <el-form-item label="电影海报" prop="filmImage">
            <el-input v-model="addForm.filmImage" placeholder="请输入修改电影海报的URL" />
          </el-form-item>
          <el-form-item label="电影名称" prop="filmName">
            <el-input v-model="addForm.filmName" placeholder="请输入电影名称" />
          </el-form-item>
          <el-form-item label="电影Id" prop="filmId">
            <el-input v-model="addForm.filmId" placeholder="请输入电影Id" />
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
import { listSwipers, getSwiper, updateSwiper, delSwiper, addSwiper, querySwiperByFilmName } from '@/api/system/swiper';

export default {
    name: "Swiper",
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
            updateOpen: false,
            // 查询参数
            queryParams: {
                pageNum: 1,
                pageSize: 10,
            },
            updateForm: {},
            updateRules: {
                filmImage: [
                    { required: true, message: '请上传电影海报', trigger: 'blur' }
                ],
                filmName: [
                    { required: true, message: '请输入电影名称', trigger: 'blur' }
                ],
                filmId: [
                    { required: true, message: '请输入电影ID', trigger: 'blur' }
                ]
            },
            addForm: {},
            addRules: {
                filmImage: [
                    { required: true, message: '请上传电影海报', trigger: 'blur' }
                ],
                filmName: [
                    { required: true, message: '请输入电影名称', trigger: 'blur' }
                ],
                filmId: [
                    { required: true, message: '请输入电影ID', trigger: 'blur' }
                ]
            },
        }
    },
    created() {
        this.getList();
    },
    methods: {
        getList() {
            this.loading=true,
            listSwipers().then(response => {
                this.swiperList = response.rows;
                this.total = response.total;
                this.loading = false;
            })
        },
        updateReset() {
            this.updateForm = {
                swiperId: '',
                filmImage: '',
                filmName: '',
                filmId: '',
      };
      this.resetForm("updateForm");
    },
    handleUpdate(row) {
        this.updateReset();
        const swiperId = row.swiperId || this.ids
      getSwiper(swiperId).then(response => {
        const data = response.data;
        // 将时间字符串转换为 Moment 对象并格式化
        this.updateForm = data;
        this.updateOpen = true;
        this.title = "修改关联信息";
      })
    },
    /** 修改影院信息 */
    submitUpdateForm: function() {
        this.$refs["updateForm"].validate(valid => {
        if (valid) {
            if (this.updateForm.swiperId != undefined) {
            updateSwiper(this.updateForm).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.updateOpen = false;
                this.getList();
            });
            }
        }
        });
    },
    cancelUpdate() {
      this.updateOpen = false;
      this.updateReset();
    },
    /** 删除按钮操作 */
    handleDelete(row) {
        const swiperId = row.swiperId || this.ids;
        this.$modal.confirm('是否确认删除影院的编号为"' + swiperId + '"的数据项？').then(function() {
        return delSwiper(swiperId);
        }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
        });
    },
    addReset() {
      this.addForm = {
        filmImage: '',
        filmName: '',
        filmId: ''
      },
      this.resetForm("addForm");
    },
    handleAdd() {
      this.addReset();
      this.open = true;
      this.title = "新增轮播图信息";
    },
    submitAddForm() {
      this.$refs['addForm'].validate(valid => {
        if (valid) {
          addSwiper(this.addForm).then(response => {
            this.$message.success('新增关联关系成功');
            this.open = false; // 关闭新增电影对话框
            this.getList(); // 刷新电影列表
          }).catch(error => {
            this.$message.error('新增关联关系失败');
          });
        } else {
          return false;
        }
      });
    },
    handleQuery() {
      const filmName = this.queryParams.filmName;
      if (filmName === '') {
        this.getList();
      } else {
        querySwiperByFilmName(filmName).then(response => {
            // 检查返回的数据是否是数组类型，并且具有 length 属性
            if (Array.isArray(response.data) && response.data.hasOwnProperty('length')) {
                  this.swiperList = response.data;
                  this.total = this.swiperList.length;
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
    }
  }
}
</script>

