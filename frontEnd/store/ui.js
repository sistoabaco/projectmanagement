import Vue from 'vue'
import { generateUUID, isObject } from '../utils'

export const state = () => ({
  notifications: [],
  contextMenu: {
    position: {},
    visible: false,
  },
})

export const mutations = {
  pushNotification(state, notification) {
    state.notifications.push(notification)
  },
  dismissNotification(state, id) {
    const index = state.notifications.findIndex((n) => n.id === id)
    Vue.delete(state.notifications, index)
  },
}

export const actions = {
  pushNotification({ commit }, notification) {
    notification = {
      id: generateUUID(),
      timeOut: 5000,
      ...notification,
    }
    commit('pushNotification', notification)
  },
  dismissNotification({ commit }, notification) {
    const id = isObject(notification) ? notification.id : notification
    Vue.nextTick(() => {
      commit('dismissNotification', id)
    })
  },
}
