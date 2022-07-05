import Vue from 'vue'
import Router from 'vue-router'
import { normalizeURL, decode } from 'ufo'
import { interopDefault } from './utils'
import scrollBehavior from './router.scrollBehavior.js'

const _66326168 = () => interopDefault(import('..\\pages\\categories\\index.vue' /* webpackChunkName: "pages/categories/index" */))
const _a48a0038 = () => interopDefault(import('..\\pages\\categories\\index\\create.vue' /* webpackChunkName: "pages/categories/index/create" */))
const _24d8d987 = () => interopDefault(import('..\\pages\\login\\index.vue' /* webpackChunkName: "pages/login/index" */))
const _7ed049f9 = () => interopDefault(import('..\\pages\\partners\\index.vue' /* webpackChunkName: "pages/partners/index" */))
const _157c7e57 = () => interopDefault(import('..\\pages\\project\\index.vue' /* webpackChunkName: "pages/project/index" */))
const _5b0cbdd3 = () => interopDefault(import('..\\pages\\project\\index\\create.vue' /* webpackChunkName: "pages/project/index/create" */))
const _3fbf3188 = () => interopDefault(import('..\\pages\\users\\index.vue' /* webpackChunkName: "pages/users/index" */))
const _5e05b004 = () => interopDefault(import('..\\pages\\users\\index\\create.vue' /* webpackChunkName: "pages/users/index/create" */))
const _4a1117ec = () => interopDefault(import('..\\pages\\users\\index\\_id\\edit.vue' /* webpackChunkName: "pages/users/index/_id/edit" */))
const _43794384 = () => interopDefault(import('..\\pages\\index.vue' /* webpackChunkName: "pages/index" */))

const emptyFn = () => {}

Vue.use(Router)

export const routerOptions = {
  mode: 'history',
  base: '/',
  linkActiveClass: 'nuxt-link-active',
  linkExactActiveClass: 'nuxt-link-exact-active',
  scrollBehavior,

  routes: [{
    path: "/categories",
    component: _66326168,
    name: "categories",
    children: [{
      path: "create",
      component: _a48a0038,
      name: "categories-index-create"
    }]
  }, {
    path: "/login",
    component: _24d8d987,
    name: "login"
  }, {
    path: "/partners",
    component: _7ed049f9,
    name: "partners"
  }, {
    path: "/project",
    component: _157c7e57,
    name: "project",
    children: [{
      path: "create",
      component: _5b0cbdd3,
      name: "project-index-create"
    }]
  }, {
    path: "/users",
    component: _3fbf3188,
    name: "users",
    children: [{
      path: "create",
      component: _5e05b004,
      name: "users-index-create"
    }, {
      path: ":id?/edit",
      component: _4a1117ec,
      name: "users-index-id-edit"
    }]
  }, {
    path: "/",
    component: _43794384,
    name: "index"
  }],

  fallback: false
}

export function createRouter (ssrContext, config) {
  const base = (config._app && config._app.basePath) || routerOptions.base
  const router = new Router({ ...routerOptions, base  })

  // TODO: remove in Nuxt 3
  const originalPush = router.push
  router.push = function push (location, onComplete = emptyFn, onAbort) {
    return originalPush.call(this, location, onComplete, onAbort)
  }

  const resolve = router.resolve.bind(router)
  router.resolve = (to, current, append) => {
    if (typeof to === 'string') {
      to = normalizeURL(to)
    }
    return resolve(to, current, append)
  }

  return router
}
