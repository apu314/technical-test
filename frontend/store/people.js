export const state = () => ({
  list: []
})

export const mutations = {
  setPeople (state, people) {
    state.list = []
    state.list = people
  }

}
