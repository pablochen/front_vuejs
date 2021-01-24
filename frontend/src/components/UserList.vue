<template>
  <section>
    <h3>사원정보</h3>
    <grid ref="tuiGrid" :data="gridProps.data" :columns="gridProps.columns"/>
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
    }
  },
  beforeMount(){
    this.getUserList()
  },
  created() {
    this.gridProps = {
      columns: [
        { header: '사원ID',   name: 'id' },
        { header: '사번',     name: 'code' },
        { header: '사원명',   name: 'name' },
        { header: '부서코드', name: 'deptCode' },
        { header: '직책코드', name: 'positionCode'},
        { header: '보유연차', name: 'totalVacCnt' },
        { header: '입사일',   name: 'joinDate' }
      ],
      data: []
    }
  }
}

</script>

<style>
</style>
