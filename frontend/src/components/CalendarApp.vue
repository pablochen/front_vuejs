
<template>
  <div>
    <CalendarHeader></CalendarHeader>
    <CalendarList :key="calendarListKey"
      v-bind:vacationHistList="vacationHistList"
      ></CalendarList>
    <CalendarFooter></CalendarFooter>
  </div>
</template>

<script>
/* eslint-disable */
import CalendarHeader from './calendar/CalendarHeader.vue'
import CalendarList from './calendar/CalendarList.vue'
import CalendarFooter from './calendar/CalendarFooter.vue'

export default {
  name: 'CalendarApp',
  data() {
    return {
      calendarListKey: 'calendarListKey0',
      vacationHistList: []
    };
  },
  components: {
    'CalendarHeader': CalendarHeader,
    'CalendarList': CalendarList,
    'CalendarFooter': CalendarFooter
  },
  methods: {
    getVacationHistList: function(key) {
      const data = { params:{ userId : 0 } }
      const baseURI = 'http://localhost:8080';

      this.$http.get(`${baseURI}/vacation/getVacationHistList`, data)
      .then((result) => {
        this.vacationHistList = result.data.content
        this.reRender('CalendarList')
      }).catch(error=>{
        console.log(error)
      });
    },
    reRender(compName) {
      switch (compName) {
        case 'CalendarList':
          this.calendarListKey += 1
          break;
        default:
      }
    }
  },
  created() {
    this.getVacationHistList();
  }
}
</script>

<style>
</style>
