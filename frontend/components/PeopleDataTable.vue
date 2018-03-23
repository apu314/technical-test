<template>
  <div>
    <v-dialog v-model="dialog">
      <v-btn color="primary" dark slot="activator" @click="newPerson()" class="mb-2">New Person</v-btn>
      <v-card>
        <v-card-title>
          <span class="headline">{{ formTitle }}</span>
        </v-card-title>
        <v-card-text>
          <v-container grid-list-md>
            <v-layout wrap>
              <v-flex xs12 sm6 md6>
                <v-text-field label="First Name" v-model="editedItem.firstName" :readonly="readonly"></v-text-field>
              </v-flex>
              <v-flex xs12 sm6 md6>
                <v-text-field label="Last Name" v-model="editedItem.lastName" :readonly="readonly"></v-text-field>
              </v-flex>
              <v-flex xs12 sm6 md6>
                <v-switch
                        label="Enabled"
                        v-model="editedItem.enabled"
                        :input-value="editedItem.enabled"
                        hide-details
                ></v-switch>
              </v-flex>
              <v-flex xs12 sm6 md6>
                <v-switch
                        label="Authorised"
                        v-model="editedItem.authorised"
                        :input-value="editedItem.authorised"
                        hide-details
                ></v-switch>
              </v-flex>
              <v-flex xs12 sm4 md4 v-for="(colour, index) in editedItem.colours" :key="index">
                <v-checkbox
                  row
                  v-model="editedItem.colours[index].enabled"
                  :label="colour.name"
                  :input-value="colour.enabled"
                ></v-checkbox>
              </v-flex>
            </v-layout>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" flat @click.native.stop="closeDialog">Cancel</v-btn>
          <v-btn color="blue darken-1" flat @click.native="createModifyPerson">Save</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

    <v-data-table
        :key="showDataTable"
      :headers="headers"
      :items.sync="people"
      hide-actions
      class="elevation-1"
    >
      <template slot="items" slot-scope="person">
        <tr>
          <td>{{ person.item.id }}</td>
          <td>{{ setFullName(person.item.firstName, person.item.lastName) }}</td>
          <td>{{ person.item.lastName }}</td>
          <td>
            <span v-if="!palindrome(person.item.firstName, person.item.lastName)"><v-icon color="red">clear</v-icon></span>
            <span v-else><v-icon color="green">done</v-icon></span>
            <span :v-model="reverseName(person.item.firstName, person.item.lastName)">{{ reverseName(person.item.firstName, person.item.lastName) }}</span>
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
            <v-btn icon class="mx-0" slot="activator" @click="editingItem(person.item)">
              <v-icon color="teal">edit</v-icon>
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
  props: {
    defaultPerson: {
      default: function () {
        return {
          id: 0,
          firstName: '',
          lastName: '',
          enabled: false,
          valid: false,
          authorised: false,
          colours: [
            {
              id: 1,
              name: 'Blue',
              enabled: false
            },
            {
              id: 2,
              name: 'Red',
              enabled: false
            },
            {
              id: 3,
              name: 'Green',
              enabled: false
            }
          ]
        }
      }
    }
  },
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
      editedItem: Object.assign({}, this.defaultPersonConst()),
      showDataTable: true
    }
  },
  computed: {
    formTitle () {
      return this.editedIndex === -1 ? 'New Person' : 'Edit Person'
    },
    readonly () {
      if (this.editedIndex === -1) {
        // Is Writable by disabling readonly prop
        return false
      } else {
        return true
      }
    },
    people () {
      var list = this.$store.state.people.list

      return list
    }
  },
  methods: {
    defaultPersonConst () {
      const defaultPerson = {
        id: 0,
        firstName: '',
        lastName: '',
        enabled: false,
        valid: false,
        authorised: false,
        colours: [
          {
            id: 1,
            name: 'Blue',
            enabled: false
          },
          {
            id: 2,
            name: 'Red',
            enabled: false
          },
          {
            id: 3,
            name: 'Green',
            enabled: false
          }
        ]
      }
      return defaultPerson
    },
    // To re-render a component.
    rerenderDataTable () {
      this.showDataTable = false
      this.$nextTick(() => {
        // this.updateList()
        this.showDataTable = true
        console.log('re-render start')
        this.$nextTick(() => {
          console.log('re-render end')
        })
      })
    },
    newPerson () {
      this.editedIndex = -1
      this.editedItem = Object.assign({}, this.defaultPersonConst())
      var editedColours = this.editedItem.colours
      for (var colour in editedColours) {
        this.editedItem.colours[colour].enabled = false
      }
      console.log(this.defaultPerson)
    },
    closeDialog () {
      this.editedIndex = -1
      this.editedItem = Object.assign({}, this.defaultPerson)
      this.dialog = false
      console.log(this.editedItem)
    },
    setFullName (firstName, lastName) {
      var fullName = firstName + lastName
      return fullName
    },
    reverseName (firstName, lastName) {
      var fullName = firstName + lastName
      var reverse = fullName.split('').reverse().join('')
      return reverse
    },
    palindrome (firstName, lastName) {
      var fullName = firstName + lastName
      var reverse = this.reverseName(firstName, lastName)
      var fullNameL = fullName.toLowerCase()
      var reverseL = reverse.toLowerCase()
      if (fullNameL === reverseL) {
        return true
      } else {
        return false
      }
    },
    editingItem (person) {
      this.editedIndex = this.people.indexOf(person)

      this.editedItem = Object.assign({}, this.defaultPersonConst())
      this.editedItem.id = person.id
      this.editedItem.firstName = person.firstName
      this.editedItem.lastName = person.lastName
      this.editedItem.enabled = person.enabled
      this.editedItem.valid = person.valid
      this.editedItem.authorised = person.authorised
      var editedColours = this.editedItem.colours
      for (var colour in editedColours) {
        for (var pColour in person.colours) {
          if (editedColours[colour].name === person.colours[pColour].name) {
            // console.log('Colours match. \n editedColour: ' + editedColours[colour].name + '\n person: ' + person.colours[pColour].name)
            this.editedItem.colours[colour].enabled = true
            // console.log(this.editedItem.colours[colour])
            break
          } else {
            // console.log('Colours doesn\'t match. \n editedColour: ' + editedColours[colour].name + '\n person: ' + person.colours[pColour].name)
            this.editedItem.colours[colour].enabled = false
            // console.log(this.editedItem.colours[colour])
            continue
          }
        }
      }
      this.dialog = true
    },
    updateList () {
      let people
      try {
        people = this.$axios.get('/people').then(res => res.data)
      } catch (error) {
        return console.error(error)
      }
      this.$store.commit('people/setPeople', people)
    },
    createModifyPerson () {
      for (var colour in this.editedItem.colours) {
        if (!this.editedItem.colours[colour].enabled) {
          console.log(this.editedItem.colours)
          this.editedItem.colours.splice(colour, 1)
          console.log(this.editedItem.colours)
        }
      }
      var person = this.editedItem
      if (this.editedIndex === -1) {
        this.$axios.post('/person', person)
          .then(function (response) {
            this.updateList()
            console.log(response)
          })
          .catch(function (error) {
            console.log(error)
          })
      } else {
        this.$axios.put('/person', person)
          .then(function (response) {
            this.updateList()
            console.log(response)
          })
          .catch(function (error) {
            console.log(error)
          })
      }
      this.editedItem = this.defaultPersonConst()
      this.rerenderDataTable()
      this.dialog = false
      console.log(this.editedItem)
    }
  }
}
</script>

<style>

</style>
