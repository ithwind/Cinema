<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="" prop="filmId">
        <el-input
          v-model="queryParams.filmId"
          placeholder="请输入电影id"
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
          v-hasPermi="['system:cinema-film:add']"
        >新增</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="cinemaFilmList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="影院电影ID" align="center" prop="cinemaFilmId" />
      <el-table-column label="电影ID" align="center" prop="filmId" />
      <el-table-column label="影院ID" align="center" prop="cinemaId" />
      <el-table-column label="电影价格" align="center" prop="filmPrice" />
      <el-table-column label="折扣价格" align="center" prop="discountPrice" />
      <el-table-column label="影厅" align="center" prop="cinemaHall" />
      <el-table-column label="播放日期" align="center" prop="playDate" />
      <el-table-column label="开始时间" align="center" prop="beginTime" />
      <el-table-column label="结束时间" align="center" prop="endTime" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:cinema-film:update']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:cinema-film:delete']"
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
    <!-- 修改关联关系 -->
    <el-dialog :title="title" :visible.sync="updateOpen" width="500px" append-to-body @close="cancelUpdate">
      <el-form ref="updateForm" :model="updateForm" :rules="updateRules" label-width="80px">
        <el-form-item label="电影票价" prop="filmPrice">
          <el-input v-model="updateForm.filmPrice" placeholder="请输入修改电影票价格" />
        </el-form-item>
        <el-form-item label="折扣票价" prop="discountPrice">
          <el-input v-model="updateForm.discountPrice" placeholder="请输入折扣票价" />
        </el-form-item>
        <el-form-item label="电影厅" prop="cinemaHall">
          <el-input v-model="updateForm.cinemaHall" placeholder="请输入电影厅" />
        </el-form-item>
        <el-form-item label="放映日期" prop="playDate">
          <el-date-picker
            v-model="updateForm.playDate"
            type="date"
            placeholder="请输入放映日期"
            style="width: 100%;"
          />
        </el-form-item>
        <el-form-item label="开始时间" prop="beginTime">
          <el-time-picker 
            v-model="updateForm.beginTime" 
            value-format="HH:mm"
            format="HH:mm"
            placeholder="选择开始时间" 
            class="formtime"
            :is-range="false" 
            :picker-options="{start: '00:00', step: '00:15', end: '23:45'}"
          />
        </el-form-item>
        <el-form-item label="结束时间" prop="endTime">
          <el-time-picker 
          v-model="updateForm.endTime" 
          placeholder="选择结束时间" 
          value-format="HH:mm" 
          format="HH:mm"
          class="formtime"
          :is-range="false" 
          :picker-options="{start: '00:00', step: '00:15', end: '23:45'}"/>
        </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="submitUpdateForm">确认</el-button>
          <el-button @click="cancelUpdate">取 消</el-button>
        </div>
    </el-dialog>

    <!-- 新增关联关系 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="addForm" :model="addForm" :rules="addRules" label-width="80px">
        <el-form-item label="电影ID" prop="filmId">
          <el-input v-model="addForm.filmId"></el-input>
        </el-form-item>
        <el-form-item label="影院ID" prop="cinemaId">
          <el-input v-model="addForm.cinemaId"></el-input>
        </el-form-item>
        <el-form-item label="电影价格" prop="filmPrice">
          <el-input v-model="addForm.filmPrice"></el-input>
        </el-form-item>
        <el-form-item label="折扣价格" prop="discountPrice">
          <el-input v-model="addForm.discountPrice"></el-input>
        </el-form-item>
        <el-form-item label="影厅" prop="cinemaHall">
          <el-input v-model="addForm.cinemaHall"></el-input>
        </el-form-item>
        <el-form-item label="放映日期" prop="playDate">
          <el-date-picker
            v-model="addForm.playDate"
            type="date"
            placeholder="选择日期"
            value-format="yyyy-MM-dd"
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="开始时间" prop="beginTime">
          <el-time-picker
            v-model="addForm.beginTime"
            placeholder="选择时间"
            value-format="HH:mm"
            format="HH:mm"
          ></el-time-picker>
        </el-form-item>
        <el-form-item label="结束时间" prop="endTime">
          <el-time-picker
            v-model="addForm.endTime"
            placeholder="选择时间"
            value-format="HH:mm"
            format="HH:mm"
          ></el-time-picker>
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
  import { listCinemaFilms, getCinemaFilm, updateCinemaFilm, delCinemaFilm, addCinemaFilm, queryCinemaFilmsByfilmId } from "@/api/system/cinema_film";

  export default {
    name: "Cinema-Film",
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
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
        },
        updateForm: {},
        updateRules: {
          filmPrice: [
            { required: true, message: '请输入电影票价格', trigger: 'blur' }
          ],
          discountPrice: [
            { required: true, message: '请输入折扣票价', trigger: 'blur' }
          ],
          cinemaHall: [
            { required: true, message: '请输入电影厅', trigger: 'blur' }
          ],
          playDate: [
            { required: true, message: '请选择放映日期', trigger: 'change' }
          ],
          beginTime: [
            { required: true, message: '请选择开始时间', trigger: 'change' }
          ],
          endTime: [
            { required: true, message: '请选择结束时间', trigger: 'change' }
          ]
        },
        addForm: {},
        addRules: {
          filmId: [
            { required: true, message: '请输入电影ID', trigger: 'blur' }
          ],
          cinemaId: [
            { required: true, message: '请输入影院ID', trigger: 'blur' }
          ],
          filmPrice: [
            { required: true, message: '请输入电影价格', trigger: 'blur' }
          ],
          discountPrice: [
            { required: true, message: '请输入折扣价格', trigger: 'blur' }
          ],
          cinemaHall: [
            { required: true, message: '请输入影厅', trigger: 'blur' }
          ],
          playDate: [
            { required: true, message: '请选择播放日期', trigger: 'change' }
          ],
          beginTime: [
            { required: true, message: '请选择开始时间', trigger: 'change' }
          ],
          endTime: [
            { required: true, message: '请选择结束时间', trigger: 'change' }
          ],
        },
    }
  },
  created() {
    this.getList();
  },
  methods: {
    getList() {
      this.loading = true;
      listCinemaFilms().then(response => {
        this.cinemaFilmList = response.rows;
        this.total = response.total;
        this.loading = false;
        
      })
    },
    updateReset() {
      this.updateForm = {
        cinemaFilmId: undefined,
        filmPrice: undefined,
        discountPrice: undefined,
        cinemaHall: undefined,
        playDate: undefined,
        beginTime: undefined,
        endTime: undefined,
      };
      this.resetForm("updateForm");
    },
    handleUpdate(row) {
      this.updateReset();
      const cinemaFilmId = row.cinemaFilmId || this.ids
      getCinemaFilm(cinemaFilmId).then(response => {
        this.updateOpen = true;
        this.title = "修改关联信息";
        const data = response.data;
        this.updateForm = data;
      });
    },
    Update(row) {
      this.updateReset();
      this.updateOpen = true;
    },
    /** 修改影院信息 */
    submitUpdateForm: function() {
      this.$refs["updateForm"].validate(valid => {
        if (valid) {
          if (this.updateForm.cinemaFilmId != undefined) {
            updateCinemaFilm(this.updateForm).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.updateOpen = false;
              this.getList();
            });
          }
        }
      });
    },

    addReset() {
      this.addForm = {
        cinemaFilmId: '', // This field is auto-generated and not shown in the form
        filmId: '',
        cinemaId: '',
        filmPrice: '',
        discountPrice: '',
        cinemaHall: '',
        playDate: '',
        beginTime: '',
        endTime: '',
      };
      this.resetForm("addForm");
    },
    handleAdd() {
        this.addReset();
        this.open=true;
        this.title="添加关联信息";
    },
    // 新增关联提交
    submitAddForm() {
      this.$refs['addForm'].validate(valid => {
        if (valid) {
          addCinemaFilm(this.addForm).then(response => {
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
    /** 删除按钮操作 */
    handleDelete(row) {
      const cinemaFilmIds = row.cinemaFilmId || this.ids;
      this.$modal.confirm('是否确认删除关联关系的编号为"' + cinemaFilmIds + '"的数据项？').then(function() {
        return delCinemaFilm(cinemaFilmIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    // 取消按钮
    cancelUpdate() {
      this.updateOpen = false;
      this.updateReset();
    },
    handleQuery() {
      const filmId = this.queryParams.filmId;
      if (filmId == "") {
        this.getList();
      } else {
        queryCinemaFilmsByfilmId(filmId).then(response => {
            // 检查返回的数据是否是数组类型，并且具有 length 属性
            if (Array.isArray(response.data) && response.data.hasOwnProperty('length')) {
                  this.cinemaFilmList = response.data;
                  this.total = this.cinemaFilmList.length;
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
  },
};
</script>