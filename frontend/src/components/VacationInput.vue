<template>
  <section>
    <h3>휴가추가</h3>
    <grid ref="tuiGrid" :data="gridProps.data" :columns="gridProps.columns"/>
    <button v-on:click="setVacation">저장</button>
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
    setVacation: function() {
      const vacationRow = this.$refs.tuiGrid.invoke('getData')[0];

      if(vacationRow.code==null || vacationRow.name==null){
          alert("모든 값 필요")
          return
        }

      const data = {
        vacationCode : vacationRow.code,
        vacationName : vacationRow.name
      }

      const headers = {
        "Content-Type": "application/json"
      }

      const baseURI = 'http://localhost:8080';
      this.$http.post(`${baseURI}/master/setVacation`, data, headers)
      .then(res => {
        this.$emit('reRender', 'VacationList')
        this.$emit('reRender', 'VacationInput')
      })
    }
  },
  created() {
    this.gridProps = {
      columns: [
        { header: '휴가코드', name: 'code', editor: 'text' },
        { header: '휴가명',   name: 'name', editor: 'text' }
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
