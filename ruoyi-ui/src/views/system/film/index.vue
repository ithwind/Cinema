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
          v-hasPermi="['system:film:add']"
        >新增</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="filmList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="电影id" align="center" prop="filmId" />
      <el-table-column label="电影名称" align="center" prop="filmName" />
      <el-table-column label="电影英文名" align="center" prop="filmEnName" />
      <el-table-column label="电影海报" align="center" prop="filmUrl">
        <template slot-scope="scope">
          <img :src="scope.row.filmUrl" alt="电影海报" style="width: 100px; height: auto;">
        </template>
      </el-table-column>
      <el-table-column label="电影语言" align="center" prop="filmLanguage"/>
      <el-table-column label="电影时长" align="center" prop="filmTime"/>
      <el-table-column label="电影类型" align="center" prop="filmType"/>
      <el-table-column label="上映国家" align="center" prop="releaseLoc"/>
      <el-table-column label="上映日期" align="center" prop="releaseTime">
        <template slot-scope="scope">
          <!-- 使用 formatDate 方法格式化日期，仅显示年月日 -->
          {{ formatDate(scope.row.releaseTime) }}
        </template>
      </el-table-column>
      <el-table-column label="电影描述" align="center" prop="filmDes">
        <template slot-scope="scope">
          {{ scope.row.filmDes | truncate(10) }}
        </template>
      </el-table-column>
      <el-table-column label="主演人员" align="center" prop="filmActor"/>
      <el-table-column label="票房总额(亿元)" align="center" prop="boxOffice"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:film:update']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:film:delete']"
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

    <!--修改电影对话框 -->
    <el-dialog :title="title" :visible.sync="updateOpen" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="电影名称" prop="filmName">
          <el-input v-model="form.filmName" placeholder="请输入电影名称" />
        </el-form-item>
        <el-form-item label="英文名" prop="filmEnName">
          <el-input v-model="form.filmEnName" placeholder="请输入电影英文名" />
        </el-form-item>
        <el-form-item label="电影语言" prop="filmLanguage">
          <el-radio-group v-model="form.filmLanguage">
            <el-radio label="中文">中文</el-radio>
            <el-radio label="英文">英文</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancelUpdate">取 消</el-button>
      </div>
    </el-dialog>

    <!--新增对话框-->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="addForm" :model="addForm" :rules="addRules" label-width="80px">
        <el-form-item label="电影名称" prop="filmName">
          <el-input v-model="addForm.filmName" placeholder="请输入电影名称" />
        </el-form-item>
        <el-form-item label="英文名" prop="filmEnName">
          <el-input v-model="addForm.filmEnName" placeholder="请输入电影英文名" />
        </el-form-item>
        <el-form-item label="电影海报" prop="filmUrl">
          <el-input v-model="addForm.filmUrl" placeholder="请输入电影海报" />
        </el-form-item>
        <el-form-item label="电影语言" prop="filmLanguage">
          <el-radio-group v-model="addForm.filmLanguage">
            <el-radio label="中文">中文</el-radio>
            <el-radio label="英文">英文</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="电影类型" prop="filmType">
          <el-input v-model="addForm.filmType" placeholder="请输入电影类型" />
        </el-form-item>
        <el-form-item label="电影时长" prop="filmTime">
          <el-input v-model="addForm.filmTime" placeholder="请输入电影时长" />
        </el-form-item>
        <el-form-item label="上映国家" prop="releaseLoc">
          <el-input v-model="addForm.releaseLoc" placeholder="请输入上映国家" />
        </el-form-item>
        <el-form-item label="上映日期" prop="releaseTime">
          <el-date-picker
            v-model="addForm.releaseTime"
            type="date"
            placeholder="请输入上映日期"
            style="width: 100%;"
          />
        </el-form-item>
        <el-form-item label="电影简介" prop="filmDes">
          <el-input v-model="addForm.filmDes" placeholder="请输入电影简介" />
        </el-form-item>
        <el-form-item label="主演人员" prop="filmActor">
          <el-input v-model="addForm.filmActor" placeholder="请输入主演人员" />
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
import { listFilm, getFilm, updateFilm, addFilm, delFilm, queryFilmsByFilmName } from "@/api/system/film";
import { DatePicker } from "element-ui";

export default {
  components: {
    'el-date-picker': DatePicker
  },
  name: "Film",
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
      // 岗位表格数据
      postList: [],
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
      form: {},
      // 修改表单校验
      rules: {
        filmName: [
          { required: true, message: "电影名称不能为空", trigger: "blur" }
        ],
        filmEnName: [
          { required: true, message: "电影英文名不能为空", trigger: "blur" }
        ],
      },
      addForm: {},
      addRules: {
        filmName: [
          { required: true, message: '请输入电影名称', trigger: 'blur' }
        ],
        filmEnName: [
          { required: true, message: '请输入电影英文名', trigger: 'blur' }
        ],
        filmUrl: [
          { required: true, message: '请输入电影海报', trigger: 'blur' }
        ],
        filmType: [
          { required: true, message: '请输入电影类型', trigger: 'blur' }
        ],
        filmTime: [
          { required: true, message: '请输入电影时长', trigger: 'blur' }
        ],
        releaseLoc: [
          { required: true, message: '请输入上映国家', trigger: 'blur' }
        ],
        releaseTime: [
          { required: true, message: '请输入上映日期', trigger: 'blur' }
        ],
        filmDes: [
          { required: true, message: '请输入电影简介', trigger: 'blur' }
        ],
        filmActor: [
          { required: true, message: '请输入主演人员', trigger: 'blur' }
        ]
      }
    };
  },
  created() {
  this.getList(); // 页面创建时获取电影数据
},
methods: {
  // 自定义方法来格式化日期，仅显示年月日
  formatDate(date) {
    if (!date) return '';
    const options = { year: 'numeric', month: '2-digit', day: '2-digit' };
    return new Date(date).toLocaleDateString('zh-CN', options);
  },
  getList() {
    this.loading = true;
    listFilm().then(response => {
      this.filmList = response.rows;
      this.total = response.total;
      this.loading = false;
    })
  },
    // 表单重置
  reset() {
    this.form = {
      filmId: undefined,
      filmName: undefined,
      filmEnName: undefined,
      filmUrl: undefined,
      filmLanguage: undefined,
      filmTime: undefined,
      filmType: undefined,
    };
    this.resetForm("form");
    },
    // 重置添加表单
    addReset() {
      this.addForm = {
        filmName: '',
        filmEnName: '',
        filmUrl: '',
        filmLanguage: '中文',
        filmType: '',
        filmTime: '',
        releaseLoc: '',
        releaseTime: '',
        filmDes: '',
        filmActor: ''
      };
      this.resetForm("addForm")
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const filmId = row.filmId || this.ids
      getFilm(filmId).then(response => {
        this.form = response.data;
        this.updateOpen = true;
        this.title = "修改电影信息";
      });
    },
    cancelUpdate() {
      this.updateOpen = false;
      this.reset();
    },
    handleAdd() {
      this.addReset();
      this.open=true;
      this.title="添加电影";
    },
    /** 搜索按钮操作 */
    handleQuery() {
      // 获取用户输入的电影名称
      const filmName = this.queryParams.filmName;
      if (filmName == "") {
        this.getList();
      } else {
      // 调用相应的 API 方法来搜索符合条件的电影信息
        queryFilmsByFilmName(filmName)
          .then(response => {
            // 处理获取的电影信息数据
            this.filmList = response.data;
            this.total = response.data.length;
            this.loading = false; // 关闭loading状态
          })
          .catch(error => {
            console.error('Error searching films:', error);
            this.loading = false; // 关闭loading状态
          });
      }
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.filmId != undefined) {
            updateFilm(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.updateOpen = false;
              this.getList();
            });
          }
        }
      });
    },
    // 新增电影
    submitAddForm() {
      this.$refs['addForm'].validate(valid => {
        if (valid) {
          addFilm(this.addForm).then(response => {
            this.$message.success('新增电影成功');
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
      const filmIds = row.filmId || this.ids;
      this.$modal.confirm('是否确认删除电影编号为"' + filmIds + '"的数据项？').then(function() {
        return delFilm(filmIds);
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
