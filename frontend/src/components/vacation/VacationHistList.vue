<template>
  <section>
    <h3>휴가 내역</h3>
    <grid ref="tuiGrid" :data="gridProps.data" :columns="gridProps.columns" :rowHeaders="gridProps.rowHeaders"/>
    <button v-on:click="deleteVacationHist">취소</button>
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
    getVacationHistList: function(key) {
      const data = {
        params:{
          userCode : key.userCode
        }
      }

      const headers = {
        "Content-Type": "application/json"
      }

      const baseURI = 'http://localhost:8080';
      this.$http.get(`${baseURI}/vacation/getVacationHistList`, data, headers)
      .then((result) => {
        this.$refs.tuiGrid.invoke('clear');
        this.$refs.tuiGrid.invoke('appendRows', result.data.content);
      }).catch(error=>{
        console.log(error)
      });

    },
    deleteVacationHist: function() {
      let keys = this.$refs.tuiGrid.invoke('getCheckedRows')
      console.log(keys)

      let ids = []
      keys.forEach((item, i) => {
        ids.push(item.id)
      });

      const data = {
        ids : ids
      }

      const headers = {
        "Content-Type": "application/json"
      }

      const baseURI = 'http://localhost:8080';
      this.$http.put(`${baseURI}/vacation/deleteVacationHists`, data, headers)
      .then(res => {
        this.$emit('reRender', 'UserList')
        this.$emit('reRender', 'VacationInput')
        this.$emit('reRender', 'VacationHistList')
      })
    }
  },
  created() {
    this.gridProps = {
      columns: [
        { header: '휴가내역ID', name: 'id' },
        { header: '휴가코드',   name: 'vacationCode' },
        { header: '휴가명',     name: 'vacationName' },
        { header: '시작일',     name: 'startDate'},
        { header: '종료일',     name: 'endDate'},
        { header: '사용일수',     name: 'days'},
        { header: '사용',       name: 'useYn' }
      ],
      data: [],
      rowHeaders: ['checkbox']
    }
  },
  beforeMount(){
    this.getVacationHistList()
  }
}

</script>

<style>
button {
  width: 100%;
  height: 50px;
}
</style>
