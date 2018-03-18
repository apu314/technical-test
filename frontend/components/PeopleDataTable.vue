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
              <v-flex xs12 sm6 md6>
                <v-text-field label="First Name" v-model="editedItem.firstName" readonly></v-text-field>
              </v-flex>
              <v-flex xs12 sm6 md6>
                <v-text-field label="Last Name" v-model="editedItem.lastName" readonly></v-text-field>
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
              <v-flex xs12 sm6 md4 v-for="(colour, index) in editedItem.colours">
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
          <td>{{ setfullName(person.item.firstName, person.item.lastName) }}</td>
          <td>{{ person.item.lastName }}</td>
          <td>
            <span v-if="!palindrome(person.item.firstName, person.item.lastName)"><v-icon color="red">clear</v-icon></span>
            <span v-else><v-icon color="green">done</v-icon></span>
            <span :v-model="reverseName(person.item.firstName + person.item.lastName, person.item)">{{ reverseName(person.item.firstName + person.item.lastName, person.item) }}</span>
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
    editedItem: {
      default: function () {
        return {
          id: 0,
          firstName: '',
          lastName: '',
          enabled: '',
          valid: '',
          authorised: '',
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
      editedIndex: -1
    }
  },
  computed: {
    formTitle () {
      return this.editedIndex === -1 ? 'New Person' : 'Edit Person'
    },
    /*
    newPerson () {
      return this.$options.propsData
    },
    */
    people () {
      var list = this.$store.state.people.list

      return list
    }
  },
  methods: {
    closeDialog () {
      this.editedIndex = -1
      this.dialog = false
      console.log(this.editedItem)
    },
    setfullName (firstName, lastName) {
      this.fullName = firstName + lastName
      return this.fullName
    },
    reverseName () {
      var reverse = this.fullName.split('').reverse().join('')

      return reverse
    },
    palindrome (firstName, lastName) {
      var fullName = firstName + lastName
      var reverse = this.reverseName(fullName)
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

      this.editedItem.id = person.id
      this.editedItem.firstName = person.firstName
      this.editedItem.lastName = person.lastName
      this.editedItem.enabled = person.enabled
      this.editedItem.valid = person.valid
      this.editedItem.authorised = person.authorised
      console.log(this.editedItem)
      var editedColours = this.editedItem.colours
      for (var colour in editedColours) {
        for (var pColour in person.colours) {
          if (editedColours[colour].name === person.colours[pColour].name) {
            console.log('Colours match. \n editedColour: ' + editedColours[colour].name + '\n person: ' + person.colours[pColour].name)
            this.editedItem.colours[colour].enabled = true
            console.log(this.editedItem.colours[colour])
            break
          } else {
            console.log('Colours doesn\'t match. \n editedColour: ' + editedColours[colour].name + '\n person: ' + person.colours[pColour].name)
            this.editedItem.colours[colour].enabled = false
            console.log(this.editedItem.colours[colour])
            continue
          }
        }
      }
      // checking colours
      // for (var editColour in this.editedItem.colours) {
      // console.log(this.editedItem.colours)
      // for (var personColour in person.colours) {
      //   if (!editColour.id === person.colours[personColour].id) {
      //     console.log(this.editedItem.colours[editColour.id])
      //   } else {
      //     this.editedItem.colours[editColour].id = person.colours[personColour].id
      //     this.editedItem.colours[editColour.id].name = person.colours[personColour].name
      //     this.editedItem.colours[editColour.id].enabled = person.colours[personColour].enabled
      //   }
      // }
      // }
      // this.editedItem = person
      this.dialog = true
      console.log(this.$options.propsData)
    }
    /*
    createModifyPerson (person) {
      axios.post('/person', {
        person: this.
      })
        .then(function (response) {
            console.log(response);
        })
        .catch(function (error) {
            console.log(error);
        });
    }
    */
  }
}
</script>

<style>

</style>
