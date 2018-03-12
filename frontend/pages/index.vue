<template>
  <v-layout column justify-center align-center>
    <v-flex xs12>
      <div>
        <PeopleDataTable></PeopleDataTable>
      </div>
    </v-flex>
  </v-layout>
</template>

<script>
import PeopleDataTable from '~/components/PeopleDataTable.vue'

export default {
  async fetch ({ store, params, app }) {
    let people = []
    try {
      people = await app.$axios.get('/people').then(res => res.data)
    } catch (error) {
      return console.error(error)
    }
    store.commit('people/setPeople', people)
  },

  data () {
    return {
      title: 'Technical-Test',
      subtitle: 'People',
      meta: [
        { name: 'theme-color', content: '#20c0b1' },
        { hid: 'description', name: 'description', content: 'REST API Spring Nuxtjs' },
        { property: 'og:title', content: 'Technical-Test' },
        { property: 'og:description', content: 'REST API Spring Nuxtjs' },
        { property: 'og:url', content: 'https://apu314.com/' }
      ],
      dialog: false
    }
  },
  components: {
    PeopleDataTable
  },
  methods: {
    showAddPerson () {
      this.dialog = true
    },
    async updateList () {
      let people
      try {
        people = await this.$axios.get('/people').then(res => res.data)
      } catch (error) {
        return console.error(error)
      }
      this.$store.commit('people/setPeople', people)
      this.dialog = false
    }
  }
}
</script>