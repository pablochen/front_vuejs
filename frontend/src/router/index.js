import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import MasterApp from '@/components/MasterApp'
import VacationApp from '@/components/VacationApp'

Vue.config.productionTip = false

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/helloWorld',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/masterApp',
      name: 'MasterApp',
      component: MasterApp
    },
    {
      path: '/vacationApp',
      name: 'VacationApp',
      component: VacationApp
    }
  ]
})
