
<template>
  <div>
    <VacationHeader></VacationHeader>
    <UserList :key="userListKey"></UserList>
    <VacationInput :key="vacationInputKey" v-bind:vacationItems="vacationItems"></VacationInput>
    <VacationFooter></VacationFooter>
  </div>
</template>

<script>
/* eslint-disable */
import VacationHeader from './vacation/VacationHeader.vue'
import UserList from './vacation/UserList.vue'
import VacationInput from './vacation/VacationInput.vue'
import VacationFooter from './vacation/VacationFooter.vue'

export default {
  name: 'VacationApp',
  data() {
    return {
      userListKey: 'userListKey0',
      vacationInputKey: 'vacationInputKey0',
      vacationItems: []
    };
  },
  components: {
    'VacationHeader': VacationHeader,
    'UserList': UserList,
    'VacationInput': VacationInput,
    'VacationFooter': VacationFooter
  },
  methods: {
    getVacationList: function() {
      const baseURI = 'http://localhost:8080';
      this.$http.get(`${baseURI}/master/getVacationList`)
      .then((result) => {
        this.vacationItems = result.data.content
        this.reRender('VacationInput')
      })
    },
    reRender(compName) {
      switch (compName) {
        case 'VacationInput':
          this.vacationInputKey += 1
          break;
        default:
      }
    }
  },
  created() {
    this.getVacationList()
  }
}
</script>

<style>
</style>
