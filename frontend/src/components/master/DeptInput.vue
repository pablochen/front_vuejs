<template>
  <section>
    <h3>부서추가</h3>
    <grid ref="tuiGrid" :data="gridProps.data" :columns="gridProps.columns"/>
    <button v-on:click="setDept">저장</button>
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
    setDept: function() {
      const deptRow = this.$refs.tuiGrid.invoke('getData')[0];

      if(deptRow.code==null || deptRow.name==null){
          alert("모든 값 필요")
          return
        }

      const data = {
        deptCode : deptRow.code,
        deptName : deptRow.name
      }

      const baseURI = 'http://localhost:8080';
      this.$http.post(`${baseURI}/master/setDept`, data)
      .then(res => {
        this.$emit('reRender', 'DeptList')
        this.$emit('reRender', 'DeptInput')
      }).catch(error=>{
        console.log(error)
      });
    }
  },
  created() {
    this.gridProps = {
      columns: [
        { header: '부서코드', name: 'code',        editor: 'text' },
        { header: '부서명',   name: 'name',        editor: 'text' }
      ],
      data: []
    }
  },
  mounted() {
    this.$refs.tuiGrid.invoke('appendRow', [], []);
  }
}
</script>

<style scoped>
button {
  width: 100%;
  height: 50px;
}
</style>
