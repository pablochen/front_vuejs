
<template>
  <div id="app">
    <MasterHeader></MasterHeader>
    <UserList :key="userListKey"></UserList>
    <UserInput :key="userInputKey" v-on:reRender="reRender"></UserInput>
    <div style="width: 30%; float:left; padding: 15px;">
      <DeptList :key="deptListKey"></DeptList>
      <DeptInput :key="deptInputKey" v-on:reRender="reRender"></DeptInput>
    </div>
    <div style="width: 30%; float:left; padding: 15px;">
      <PositionList :key="positionListKey"></PositionList>
      <PositionInput :key="positionInputKey" v-on:reRender="reRender"></PositionInput>
    </div>
    <div style="width: 30%; float:left; padding: 15px;">
      <VacationList :key="vacationListKey"></VacationList>
      <VacationInput :key="vacationInputKey" v-on:reRender="reRender"></VacationInput>
    </div>
    <MasterFooter></MasterFooter>
  </div>
</template>

<script>
/* eslint-disable */
import MasterHeader from './components/MasterHeader.vue'
import UserList from './components/UserList.vue'
import UserInput from './components/UserInput.vue'
import DeptList from './components/DeptList.vue'
import DeptInput from './components/DeptInput.vue'
import PositionList from './components/PositionList.vue'
import PositionInput from './components/PositionInput.vue'
import VacationList from './components/VacationList.vue'
import VacationInput from './components/VacationInput.vue'
import MasterFooter from './components/MasterFooter.vue'

export default {
  data() {
    return {
      userListKey: 'userListKey0',
      userInputKey: 'userInputKey0',
      deptListKey: 'deptListKey0',
      deptInputKey: 'deptInputKey0',
      positionListKey: 'positionListKey0',
      positionInputKey: 'positionInputKey0',
      vacationListKey: 'vacationListKey0',
      vacationInputKey: 'vacationInputKey0'
    };
  },
  components: {
    'MasterHeader': MasterHeader,
    'UserList': UserList,
    'UserInput': UserInput,
    'DeptList': DeptList,
    'DeptInput': DeptInput,
    'PositionList': PositionList,
    'PositionInput': PositionInput,
    'VacationList': VacationList,
    'VacationInput': VacationInput,
    'MasterFooter': MasterFooter
  },
  methods: {
    reRender(compName) {
      switch (compName) {
        case 'UserList':
          this.userListKey += 1
          break;
        case 'UserInput':
          this.userInputKey += 1
          break;
        case 'DeptList':
          this.deptListKey += 1
          break;
        case 'DeptInput':
          this.deptInputKey += 1
          break;
        case 'PositionList':
          this.positionListKey += 1
          break;
        case 'PositionInput':
          this.positionInputKey += 1
          break;
        case 'VacationList':
          this.vacationListKey += 1
          break;
        case 'VacationInput':
          this.vacationInputKey += 1
          break;
        default:
      }

    }
  }
}
</script>

<style>
</style>
