<template>
  <section>
    <h3>직책추가</h3>
    <grid ref="tuiGrid" :data="gridProps.data" :columns="gridProps.columns"/>
    <button v-on:click="setPosition">저장</button>
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
    setPosition: function() {
      const positionRow = this.$refs.tuiGrid.invoke('getData')[0];

      if(positionRow.code==null || positionRow.name==null){
          alert("모든 값 필요")
          return
        }

      const data = {
        positionCode : positionRow.code,
        positionName : positionRow.name
      }

      const headers = {
        "Content-Type": "application/json"
      }

      const baseURI = 'http://localhost:8080';
      this.$http.post(`${baseURI}/master/setPosition`, data, headers)
      .then(res => {
        this.$emit('reRender', 'PositionList')
        this.$emit('reRender', 'PositionInput')
      })
    }
  },
  created() {
    this.gridProps = {
      columns: [
        { header: '직책코드', name: 'code',        editor: 'text' },
        { header: '직책명',   name: 'name',        editor: 'text' }
      ],
      data: []
    }
  },
  mounted() {
    this.$refs.tuiGrid.invoke('appendRow', [], []);
  }
}
</script>

<style scoped>
button {
  width: 100%;
  height: 50px;
}
</style>
