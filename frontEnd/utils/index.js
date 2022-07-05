export function generateUUID() {
  let dt = new Date().getTime()
  const uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(
    /[xy]/g,
    function (c) {
      const r = (dt + Math.random() * 16) % 16 | 0
      dt = Math.floor(dt / 16)
      return (c === 'x' ? r : (r & 0x3) | 0x8).toString(16)
    }
  )
  return uuid
}

export const isObject = (val) => typeof val === 'object'

export const apiDataToStateObject = (items) => {
  const obj = {}
  Array.isArray(items)
    ? items.forEach((item, index) => {
        if (item.id) {
          obj[item.id] = item
        } else obj[index] = item
      })
    : (obj[items.id] = items)
  return obj
}

export const objectExtract = (path, obj, defaultVal = null) => {
  return path.split('.').reduce((obj, path) => {
    return obj ? obj[path] : defaultVal
  }, obj)
}

export const setStoreItems = (commit, items, resource, key, append = false) => {
  append
    ? commit('APPEND_ITEMS', { items, resource, key }, { root: true })
    : commit('SET_ITEMS', { items, resource, key }, { root: true })
}

export const setStoreItem = (commit, item, resource) => {
  commit('SET_ITEM', { item, resource }, { root: true })
}
export const fetchStoreItemsHelper = (resource, rootConfig) => (
  { dispatch },
  config
) => {
  config = { ...config, ...rootConfig }
  const { key } = config
  return dispatch('fetchItems', { resource, config, key }, { root: true })
}
export const fetchStoreItemHelper = (resource, rootConfig) => (
  { dispatch },
  config
) => {
  config = { ...config, ...rootConfig }
  const { id } = config
  return dispatch('fetchItem', { resource, config, id }, { root: true })
}

export const createStoreItemHelper = (resource, rootConfig) => (
  { dispatch },
  { config, data }
) => {
  config = { ...config, ...rootConfig }
  return dispatch('createItem', { resource, data, config }, { root: true })
}

export const updateStoreItemHelper = (resource, rootConfig) => (
  { dispatch },
  { config, data }
) => {
  config = { ...config, ...rootConfig }
  return dispatch('updateItem', { resource, data, config }, { root: true })
}

export const deleteStoreItemHelper = (resource, rootConfig) => (
  { dispatch },
  { config, data }
) => {
  config = { ...config, ...rootConfig }
  return dispatch('deleteItem', { resource, data, config }, { root: true })
}

export const createActions = (resource, config = {}) => ({
  fetchItems: fetchStoreItemsHelper(resource, config),
  fetchItem: fetchStoreItemHelper(resource, config),
  createItem: createStoreItemHelper(resource, config),
  updateItem: updateStoreItemHelper(resource, config),
  deleteItem: deleteStoreItemHelper(resource, config),
})