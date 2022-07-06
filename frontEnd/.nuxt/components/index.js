export { default as LayoutSidebar } from '../..\\components\\layout\\Sidebar.vue'
export { default as LayoutTopbar } from '../..\\components\\layout\\Topbar.vue'
export { default as CommonInputsSelectInput } from '../..\\components\\common\\inputs\\SelectInput.vue'
export { default as CommonInputsTextInput } from '../..\\components\\common\\inputs\\TextInput.vue'
export { default as CommonMiscAppButton } from '../..\\components\\common\\misc\\AppButton.vue'
export { default as CommonMiscModal } from '../..\\components\\common\\misc\\Modal.vue'
export { default as CommonMiscPage } from '../..\\components\\common\\misc\\Page.vue'
export { default as CommonMiscTable } from '../..\\components\\common\\misc\\Table.vue'

// nuxt/nuxt.js#8607
function wrapFunctional(options) {
  if (!options || !options.functional) {
    return options
  }

  const propKeys = Array.isArray(options.props) ? options.props : Object.keys(options.props || {})

  return {
    render(h) {
      const attrs = {}
      const props = {}

      for (const key in this.$attrs) {
        if (propKeys.includes(key)) {
          props[key] = this.$attrs[key]
        } else {
          attrs[key] = this.$attrs[key]
        }
      }

      return h(options, {
        on: this.$listeners,
        attrs,
        props,
        scopedSlots: this.$scopedSlots,
      }, this.$slots.default)
    }
  }
}
