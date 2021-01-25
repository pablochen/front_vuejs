<template>
  <section>
    <h3>직책정보</h3>
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
    getPositionList: function() {
      const baseURI = 'http://localhost:8080';
      this.$http.get(`${baseURI}/master/getPositionList`)
      .then((result) => {
        this.$refs.tuiGrid.invoke('appendRows', result.data.content);
        this.$emit('rePositionItems', result.data.content)
      })
    }
  },
  beforeMount(){
    this.getPositionList()
  },
  created() {
    this.gridProps = {
      columns: [
        { header: '직책ID',   name: 'id' },
        { header: '직책코드', name: 'code' },
        { header: '직책명',   name: 'name' }
      ],
      data: [],
      bodyHeight: 300
    }
  }
}

</script>

<style>
grid {
  height: 300px;
}
</style>
