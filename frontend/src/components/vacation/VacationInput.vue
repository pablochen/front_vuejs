<template>
  <section>
    <h3>휴가 신청</h3>
    <grid ref="tuiGrid" :data="gridProps.data" :columns="gridProps.columns"/>
    <button v-on:click="setUser">저장</button>
  </section>
</template>

<script>
/* eslint-disable */
import 'tui-grid/dist/tui-grid.css'
import { Grid } from '@toast-ui/vue-grid'

export default {
  props: ['vacationItems'],
  data() {
     return {
     }
  },
  components: {
    'grid': Grid
  },
  methods: {
    setUser: function() {
      /*
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

      const headers = {
        "Content-Type": "application/json"
      }

      const baseURI = 'http://localhost:8080';
      this.$http.post(`${baseURI}/master/setUser`, data, headers)
      .then(res => {
        this.$emit('reRender', 'UserList')
        this.$emit('reRender', 'UserInput')
      })
      */
    }
  },
  created() {
    console.log('create')
    console.log(this.vacationItems)

    let vacations = []
    this.vacationItems.forEach((vacation)=>{
      vacations.push({text:vacation.name, value:vacation.code});
    })

    this.gridProps = {
      columns: [
        { header: '사번',     name: 'code'},
        { header: '사원명',   name: 'name'},
        { header: '시작일',   name: 'startDate',    editor: 'text' },
        { header: '종료일',   name: 'endDate',      editor: 'text' },
        { header: '휴가선택', name: 'vacationCode', editor: { type: 'select', options: { listItems: vacations } }}
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
