
<template>
  <div>
    <VacationHeader></VacationHeader>
    <UserList :key="userListKey"></UserList>
    <VacationInput :key="vacationInputKey"></VacationInput>
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
      vacationInputKey: 'vacationInputKey0'
    };
  },
  components: {
    'VacationHeader': VacationHeader,
    'UserList': UserList,
    'VacationInput': VacationInput,
    'VacationFooter': VacationFooter
  },
  methods: {
  }
}
</script>

<style>
</style>
