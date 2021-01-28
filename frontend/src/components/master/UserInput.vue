<template>
  <section>
    <h3>사원추가</h3>
    <grid ref="tuiGrid" :data="gridProps.data" :columns="gridProps.columns"/>
    <button v-on:click="setUser">저장</button>
  </section>
</template>

<script>
/* eslint-disable */
import 'tui-grid/dist/tui-grid.css'
import { Grid } from '@toast-ui/vue-grid'

export default {
  props: ['deptItems', 'positionItems'],
  data() {
     return {
       depts: this.deptItems,
       positions: this.positionItems
     }
  },
  components: {
    'grid': Grid
  },
  methods: {
    setUser: function() {
      const userRow = this.$refs.tuiGrid.invoke('getData')[0];

      if(userRow.code==null || userRow.name==null || userRow.deptCode==null ||
          userRow.positionCode==null || userRow.joinDate==null){
          alert("모든 값 필요")
          return
      }

      const data = {
        userCode : userRow.code,
        userName : userRow.name,
        deptCode : userRow.deptCode,
        positionCode : userRow.positionCode,
        joinDate : userRow.joinDate
      }

      const baseURI = 'http://localhost:8080';
      this.$http.post(`${baseURI}/master/setUser`, data)
      .then(res => {
        this.$emit('reRender', 'UserList')
        this.$emit('reRender', 'UserInput')
      }).catch(error=>{
        console.log(error)
      });
    }
  },
  created() {
    let deptItems = [];
    this.depts.forEach((dept)=>{
      deptItems.push({text:dept.name, value:dept.code});
    })

    let positionItems = [];
    this.positions.forEach((position)=>{
      positionItems.push({text:position.name, value:position.code});
    })

    this.gridProps = {
      columns: [
        { header: '사번',     name: 'code',         editor: 'text' },
        { header: '사원명',   name: 'name',         editor: 'text' },
        { header: '부서',     name: 'deptCode',     editor: { type: 'select', options: { listItems: deptItems } },     formatter: 'listItemText'},
        { header: '직책',     name: 'positionCode', editor: { type: 'select', options: { listItems: positionItems } }, formatter: 'listItemText'},
        { header: '입사일',   name: 'joinDate',     editor: 'text' }
      ],
      data: []
    }
  },
  mounted() {
    this.$refs.tuiGrid.invoke('appendRow', [], [])
  }
}
</script>

<style scoped>
button {
  width: 100%;
  height: 50px;
}
</style>
