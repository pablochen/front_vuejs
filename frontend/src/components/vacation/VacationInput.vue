<template>
  <section>
    <h3>휴가 신청</h3>
    <grid ref="tuiGrid" :data="gridProps.data" :columns="gridProps.columns" @afterChange="calDays"/>
    <button v-on:click="setVacationHist">저장</button>
  </section>
</template>

<script>
/* eslint-disable */
import 'tui-grid/dist/tui-grid.css'
import { Grid } from '@toast-ui/vue-grid'

export default {
  props: ['vacationItems','selectUserInfo'],
  data() {
     return {
     }
  },
  components: {
    'grid': Grid
  },
  methods: {
    setUser: function(user) {
      // 두번 클릭해야 넘어가는 버그. props 대신 데이터 파라메터로 해결
      this.$refs.tuiGrid.invoke('setValue', 0, 'code', user.userCode)
      this.$refs.tuiGrid.invoke('setValue', 0, 'name', user.userName)
      this.$refs.tuiGrid.invoke('setValue', 0, 'remainVacCnt', user.remainVacCnt)
    },
    setVacationHist: function() {
      const vacaRow = this.$refs.tuiGrid.invoke('getData')[0];

      if(vacaRow.code==null || vacaRow.name==null || vacaRow.remainVacCnt==null ||
        vacaRow.startDate==null || vacaRow.endDate==null || vacaRow.vacationCode==null || vacaRow.days==null){
          alert("모든 값 필요")
          return
      }

      if(Number(vacaRow.remainVacCnt) < Number(vacaRow.days)){
          alert("연차 부족")
          return
      }

      let vacaCode = vacaRow.vacationCode.split(':')[0]

      const data = {
        userCode : vacaRow.code,
        vacationCode : vacaCode,
        startDate : vacaRow.startDate,
        endDate : vacaRow.endDate,
        days : vacaRow.days
      }

      const headers = {
        "Content-Type": "application/json"
      }

      const baseURI = 'http://localhost:8080';
      this.$http.post(`${baseURI}/vacation/setVacationHist`, data, headers)
      .then(res => {
        this.$emit('reRender', 'UserList')
        this.$emit('reRender', 'VacationInput')
        this.$emit('reRender', 'VacationHistList')
      })
    },
    calDays: function() {
      let thisRow = this.$refs.tuiGrid.invoke('getRow', 0)
      if(thisRow.startDate != null && thisRow.startDate != '' &&
         thisRow.endDate != null && thisRow.endDate != '' &&
         thisRow.vacationCode != null && thisRow.vacationCode != ''){
        let dateBetween = this.getDateBetween(thisRow.startDate, thisRow.endDate)
        let days = Number(thisRow.vacationCode.split(':')[1])

        this.$refs.tuiGrid.invoke('setValue', 0, 'days', dateBetween*days, false)
      }
    },
    getDateBetween: function(startDate, endDate){
      /* 공휴일, 휴일 제외 로직 추가하면 좋음 */
      var startObj = new Date(startDate.substr(0,4), startDate.substr(4,2), startDate.substr(6,2))
      var endObj = new Date(endDate.substr(0,4), endDate.substr(4,2), endDate.substr(6,2))
      var betweenDay = (endObj.getTime() - startObj.getTime())/1000/60/60/24 + 1;
      return betweenDay;
    }
  },
  created() {
    let vacations = []
    this.vacationItems.forEach((vacation)=>{
      vacations.push({text:vacation.name, value:vacation.code + ":" + vacation.days});
    })

    this.gridProps = {
      columns: [
        { header: '사번',     name: 'code'},
        { header: '사원명',   name: 'name'},
        { header: '잔여연차', name: 'remainVacCnt'},
        { header: '시작일',   name: 'startDate',    editor: 'text',},
        { header: '종료일',   name: 'endDate',      editor: 'text'},
        /* 그리드 오류 : 휴가선택 셀 포커스 아웃 시, 헤더가 아닌 밸류 세팅 */
        { header: '휴가선택', name: 'vacationCode', editor: { type: 'select', options: { listItems: vacations } }, onAfterChange(ev) { return; /* console.log(ev)} */ } },
        { header: '사용일수', name: 'days'}
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
