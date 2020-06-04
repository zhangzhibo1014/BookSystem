<template>
  <div>
    <el-row :gutter="20" style="margin-top: 40px;">
      <el-col :offset="4" :span="4">
        <el-tag type="info">图书信息列表</el-tag>
        <el-button
          type="primary"
          size="small"
          @click="dialogFormVisible = true"
          style="margin-left: 50px;"
        >新增图书</el-button>
        <el-dialog title="新增图书" :visible.sync="dialogFormVisible" width="30%">
          <el-form :model="form">
            <el-form-item label="书名" :label-width="formLabelWidth">
              <el-input v-model="form.bookName" autocomplete="off" class="ipunt-size"></el-input>
            </el-form-item>
            <el-form-item label="作者" :label-width="formLabelWidth">
              <el-input v-model="form.bookAuthor" autocomplete="off" class="ipunt-size"></el-input>
            </el-form-item>
            <el-form-item label="价格" :label-width="formLabelWidth">
              <el-input v-model="form.bookPrice" autocomplete="off" class="ipunt-size"></el-input>
            </el-form-item>
            <el-form-item label="出版社" :label-width="formLabelWidth">
              <el-input v-model="form.bookAddress" autocomplete="off" class="ipunt-size"></el-input>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="addBook">确 定</el-button>
          </div>
        </el-dialog>
      </el-col>
    </el-row>
    <el-row :gutter="20" type="flex" justify="center">
      <el-col :span="12">
        <el-table :data="nowList" stripe style="width: 100%">
          <el-table-column prop="bookId" label="序号" width="100"></el-table-column>
          <el-table-column prop="bookName" label="书名" width="150"></el-table-column>
          <el-table-column prop="bookAuthor" label="作者" width="150"></el-table-column>
          <el-table-column prop="bookPrice" label="价格" width="150"></el-table-column>
          <el-table-column prop="bookAddress" label="出版社"></el-table-column>
          <el-table-column fixed="right" label="操作">
            <template slot-scope="scope">
              <el-button @click="handleClick(scope.row)" type="text" size="small">编辑</el-button>
              <el-button type="text" size="small" @click="deleteBook(scope.row.bookId)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-col>
    </el-row>
    <el-row :gutter="20" type="flex" justify="center" style="margin-top: 20px;">
      <el-col :offset="7" :span="12">
        <el-pagination
          background
          :page-size="pageSize"
          :current-page="currentPage"
          :total="bookList.length"
          layout="total, prev, pager, next"
          @prev-click="prevInfo"
          @next-click="nextInfo"
          @current-change="currentInfo"
        ></el-pagination>
      </el-col>
    </el-row>
    <el-dialog title="修改图书" :visible.sync="updateBookDialog" width="30%">
      <el-form :model="updatebook">
        <el-form-item label="书名" :label-width="formLabelWidth">
          <el-input v-model="updatebook.bookName" autocomplete="off" class="ipunt-size"></el-input>
        </el-form-item>
        <el-form-item label="作者" :label-width="formLabelWidth">
          <el-input v-model="updatebook.bookAuthor" autocomplete="off" class="ipunt-size"></el-input>
        </el-form-item>
        <el-form-item label="价格" :label-width="formLabelWidth">
          <el-input v-model="updatebook.bookPrice" autocomplete="off" class="ipunt-size"></el-input>
        </el-form-item>
        <el-form-item label="出版社" :label-width="formLabelWidth">
          <el-input v-model="updatebook.bookAddress" autocomplete="off" class="ipunt-size"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="updateBookDialog = false">取 消</el-button>
        <el-button type="primary" @click="updateBook">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<!-- 

-->
<script>
import axios from "axios";
export default {
  data() {
    return {
      pageSize: 10,
      currentPage: 1,
      bookList: [],
      nowList: [],
      dialogFormVisible: false,
      updateBookDialog: false,
      form: {
        bookName: "",
        bookAuthor: "",
        bookPrice: "",
        bookAddress: ""
      },
      updatebook: {
        bookId: "",
        bookName: "",
        bookAuthor: "",
        bookPrice: "",
        bookAddress: ""
      },
      formLabelWidth: "120px"
    };
  },
  mounted() {
    this.queryBook();
  },
  methods: {
    handleClick(row) {
      this.updateBookDialog = true;
      this.updatebook.bookId = row.bookId;
      this.updatebook.bookName = row.bookName;
      this.updatebook.bookAuthor = row.bookAuthor;
      this.updatebook.bookPrice = row.bookPrice;
      this.updatebook.bookAddress = row.bookAddress;
    },
    currentInfo(val) {
      this.nowList = [];
      var now = val;
      var start = (now - 1) * 10;
      var end = (this.bookList.length > (now * 10)) ? (now * 10) : this.bookList.length;
      for(var i = start; i < end; i++){
        this.nowList.push(this.bookList[i]);
      }
      // console.log(val);
    },
    nowInfo () {
      this.nowList = [];
      var now = this.currentPage;
      var start = (now - 1) * 10;
      var end = (this.bookList.length > this.pageSize) ? this.pageSize : this.bookList.length;;
      for(var i = start; i < end; i++) {
        this.nowList.push(this.bookList[i])
      }
    },
    prevInfo () {
      this.nowList = [];
      var now = this.currentPage;
      var start = (now - 2) * 10;
      var end = (now - 1) * 10;
      for(var i = start; i < end; i++) {
        this.nowList.push(this.bookList[i])
      }
      this.currentPage -= 1;
    },
    nextInfo () {
      this.nowList = [];
      var now = this.currentPage;
      var start = now * 10;
      var end = (this.bookList.length > (now + 1) * 10) ? (now + 1) * 10 : this.bookList.length;
      for(var i = start; i < end; i++) {
        this.nowList.push(this.bookList[i])
      }
      this.currentPage += 1;
    },
    queryBook() {
      axios
      .post("http://localhost:8080/book/all")
      .then(response => {
        // console.log(response.data.mapResult.data);
        this.bookList = response.data.mapResult.data;
        this.nowInfo();
        this.$store.commit("save_bookInfo", response.data.mapResult.data);
      })
      .catch(error => {
        console.log(error);
      });
    },
    addBook() {
      axios
        .post("http://localhost:8080/book/add", {
          bookName: this.form.bookName,
          bookAuthor: this.form.bookAuthor,
          bookPrice: this.form.bookPrice,
          bookAddress: this.form.bookAddress
        })
        .then(response => {
          this.success("添加");
          console.log(response.data);
        })
        .catch(error => {
          this.fail("添加");
          console.log("error");
        });
      this.dialogFormVisible = false;
    },
    updateBook() {
      console.log(this.updatebook);
      axios
        .post("http://localhost:8080/book/update", {
          bookId: this.updatebook.bookId,
          bookName: this.updatebook.bookName,
          bookAuthor: this.updatebook.bookAuthor,
          bookPrice: this.updatebook.bookPrice,
          bookAddress: this.updatebook.bookAddress
        })
        .then(response => {
          this.success("修改");
          console.log(response.data);
        })
        .catch(error => {
          this.fail("修改");
          console.log("error");
        });
      this.updateBookDialog = false;
    },
    deleteBook(id) {
      this.$confirm('确定删除该数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          axios
      .post('http://localhost:8080/book/delete/'+id)
      .then(response => {
        console.log(response.data);
        this.success("删除");
      })
      .catch(error => {
        console.log(error);
        this.fail("删除");
      })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });          
        });
      
    },
    success(infoMessage) {
      this.$message({
          message: '恭喜你，' + infoMessage + '成功',
          type: 'success'
        });
        window.location.reload();
    },
    fail(infoMessage) {
      this.$message({
          message: '恭喜你，' + infoMessage + '失败',
          type: 'error'
        });
        window.location.reload();
    }
  }
};
</script>

<style>
.ipunt-size {
  width: 80%;
}
</style>
