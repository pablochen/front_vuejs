<template>
  <section>
    <h3>부서정보</h3>
    <grid ref="tuiGrid" :data="gridProps.data" :columns="gridProps.columns"/>
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
    getDeptList: function() {
      const baseURI = 'http://localhost:8080';
      this.$http.get(`${baseURI}/master/getDeptList`)
      .then((result) => {
        this.$refs.tuiGrid.invoke('appendRows', result.data.content)
        this.$emit('reDeptItems', result.data.content)
      })
    }
  },
  beforeMount(){
    this.getDeptList()
  },
  created() {
    this.gridProps = {
      columns: [
        { header: '부서ID',   name: 'id' },
        { header: '부서코드', name: 'code' },
        { header: '부서명',   name: 'name' }
      ],
      data: []
    }
  }
}

</script>

<style>
</style>
