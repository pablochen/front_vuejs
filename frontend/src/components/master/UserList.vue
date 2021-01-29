<template>
  <section>
    <h3>사원정보</h3>
    <grid ref="tuiGrid" :data="gridProps.data" :columns="gridProps.columns" :rowHeaders="gridProps.rowHeaders"/>
    <button v-on:click="deleteUser">삭제</button>
  </section>
</template>

<script>
/* eslint-disable */
import 'tui-grid/dist/tui-grid.css'
import { Grid } from '@toast-ui/vue-grid'

export default {
  components: {
    'grid': Grid
  },
  methods: {
    getUserList: function() {
      const baseURI = 'http://localhost:8080';
      this.$http.get(`${baseURI}/master/getUserList`)
      .then((result) => {
        this.$refs.tuiGrid.invoke('appendRows', result.data.content);
      })
    },
    deleteUser: function() {
        let keys = this.$refs.tuiGrid.invoke('getCheckedRows')
        let ids = []
        for (let i in keys){ ids.push(keys[i].id)}
        const data = {ids : ids}

        const baseURI = 'http://localhost:8080';
        this.$http.put(`${baseURI}/master/deleteUsers`, data)
        .then((result) => {
          this.$refs.tuiGrid.invoke('removeCheckedRows');
        })
        .catch(error=>{
          console.log(error)
        });

    }
  },
  beforeMount(){
    this.getUserList()
  },
  created() {
    this.gridProps = {
      columns: [
        { header: '사원ID',   name: 'userId' },
        { header: '사번',     name: 'userCode' },
        { header: '사원명',   name: 'userName' },
        { header: '부서코드', name: 'deptCode' },
        { header: '부서명',   name: 'deptName' },
        { header: '직책코드', name: 'positionCode'},
        { header: '직책명',   name: 'positionName'},
        { header: '보유연차', name: 'totalVacCnt' },
        { header: '보유연차', name: 'remainVacCnt' },
        { header: '입사일',   name: 'joinDate' }
      ],
      data: [],
      rowHeaders: ['checkbox']
    }
  }
}

</script>

<style>
button {
  width: 100%;
  height: 50px;
}
</style>
