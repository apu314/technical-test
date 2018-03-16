<template>
  <div>
    <v-dialog v-model="dialog">
      <v-btn color="primary" dark slot="activator" class="mb-2">New Person</v-btn>
      <v-card>
        <v-card-title>
          <span class="headline">{{ formTitle }}</span>
        </v-card-title>
        <v-card-text>
          <v-container grid-list-md>
            <v-layout wrap>
              <v-flex xs12 sm6 md4>
                <v-text-field label="First Name" v-model="editedItem.firstName" readonly></v-text-field>
              </v-flex>
              <v-flex xs12 sm6 md4>
                <v-text-field label="Last Name" v-model="editedItem.lastName" readonly></v-text-field>
              </v-flex>
              <v-flex xs12 sm6 md4>
                <v-text-field label="Enabled" v-model="editedItem.enabled"></v-text-field>
              </v-flex>
              <v-flex xs12 sm6 md4>
                <v-text-field label="Authorised" v-model="editedItem.authorised"></v-text-field>
              </v-flex>
              <v-flex xs12 sm6 md4 v-for="colour in editedItem.colours" :key="colour.id">

                <v-checkbox
                        :label="colour.name"
                ></v-checkbox>

                <v-text-field label="Colours">{{ colour.name }}</v-text-field>
              </v-flex>
            </v-layout>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" flat @click.native="dialog = false">Cancel</v-btn>
          <v-btn color="blue darken-1" flat @click.native="save">Save</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

    <v-data-table
      :headers="headers"
      :items="people"
      hide-actions
      class="elevation-1"
    >
      <template slot="items" slot-scope="person">
        <tr>
          <td>{{ person.item.id }}</td>
          <td>{{ person.item.firstName }} {{ person.item.lastName }}</td>
          <td>{{ person.item.lastName }}</td>
          <td>
            <span>{{ reverseName(person.item.firstName + person.item.lastName, person.item) }}</span>
            <span v-if="!person.item.palindrome"><v-icon color="red">clear</v-icon></span>
            <span v-else><v-icon color="green">done</v-icon></span>
          </td>
          <td>{{ person.item.enabled }}</td>
          <td>{{ person.item.valid }}</td>
          <td>{{ person.item.authorised }}</td>
          <td>
            <template v-for="(colour, index) in person.item.colours">
              <template>{{ colour.name }}</template>
              <template v-if="index+1 < person.item.colours.length">, </template>
            </template>
          </td>
          <td class="justify-center layout px-0">
            <v-btn icon class="mx-0" @click="editingItem(person.item)">
              <v-icon color="teal">edit</v-icon>
            </v-btn>
            <v-btn icon class="mx-0" @click="">
              <v-icon color="pink">delete</v-icon>
            </v-btn>
          </td>
        </tr>
      </template>
      <template slot="no-data">
        <v-btn color="primary" @click="">Reset</v-btn>
      </template>
    </v-data-table>
  </div>
</template>

<script>
export default {
  data () {
    return {
      dialog: false,
      headers: [
        { text: 'Id', value: 'id', align: 'left' },
        { text: 'Name', value: 'name', sortable: false },
        { text: 'Last Name', value: 'lastName', sortable: false },
        { text: 'Palindrome', sortable: false },
        { text: 'Enabled', value: 'enabled', sortable: false },
        { text: 'Valid', value: 'valid', sortable: false },
        { text: 'Authorised', value: 'authorised', sortable: false },
        { text: 'Colours', value: 'id', sortable: false },
        { text: 'Actions', value: 'id', sortable: false }
      ],
      editedIndex: -1,
      editedItem: [],
      cbxColours: []
    }
  },
  computed: {
    formTitle () {
      return this.editedIndex === -1 ? 'New Person' : 'Edit Person'
    },
    people () {
      var list = this.$store.state.people.list
      for (var i in list) {
        var fullName = list[i].firstName + list[i].lastName
        var reverse = this.reverseName(fullName)
        var fullNameL = fullName.toLowerCase()
        var reverseL = reverse.toLowerCase()
        if (fullNameL === reverseL) {
          this.$set(list[i], 'palindrome', true)
        } else {
          this.$set(list[i], 'palindrome', false)
        }
      }
      return list
    }
  },
  methods: {
    reverseName (fullName) {
      var reverse = fullName.split('').reverse().join('')

      return reverse
    },
    editingItem (person) {
      this.editedIndex = this.people.indexOf(person)
      this.editedItem = person
      this.dialog = true
      console.log(this.editedItem)
    }
  }
}
</script>

<style>

</style>
