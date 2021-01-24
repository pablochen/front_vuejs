<template>
  <section>
    <h3>휴가정보</h3>
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
    getVacationList: function() {
      const baseURI = 'http://localhost:8080';
      this.$http.get(`${baseURI}/master/getVacationList`)
      .then((result) => {
        this.$refs.tuiGrid.invoke('appendRows', result.data.content);
      })
    }
  },
  beforeMount(){
    this.getVacationList()
  },
  created() {
    this.gridProps = {
      columns: [
        { header: '휴가ID',   name: 'id' },
        { header: '휴가코드', name: 'code' },
        { header: '휴가명',   name: 'name' }
      ],
      data: []
    }
  }
}

</script>

<style>
</style>
