<template>
  <section>
    <full-calendar :key="calendarListKey" :events="fcEvents" locale="en"></full-calendar>
  </section>
</template>

<script>
/* eslint-disable */
import FullCalendar from 'vue-fullcalendar'

let calendarEvents = []
let items = []

export default {
  props: ['vacationHistList'],
  data () {
    return {
      calendarListKey : 'calendarListKey0',
      fcEvents : []
    }
  },
  components: {
    'full-calendar': FullCalendar
  },
  methods: {
  },
  created() {
    items = []
    let resCon = this.vacationHistList
    resCon.forEach((vacation, index)=>{
      const titleStr = vacation.userName+'('+vacation.vacationName+')'
      const startStr = vacation.startDate.substr(0,4)+'-'+vacation.startDate.substr(4,2)+'-'+vacation.startDate.substr(6,2)
      const endStr =  vacation.endDate.substr(0,4)+'-'+vacation.endDate.substr(4,2)+'-'+vacation.endDate.substr(6,2)

      if(vacation.useYn == "Y"){
        items.push({
            title: titleStr,
            start: startStr,
            end: endStr
          })
      }
    })

    console.log('비교')
    console.log(items)

    this.fcEvents = items

  }
}

</script>

<style scoped>
button {
  width: 100%;
  height: 50px;
}

.vueconf {
  background-color: #00a65a !important;
}
</style>
