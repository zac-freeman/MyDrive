import toPairs from 'lodash/toPairs'
import request from '../utils/request'
import 'whatwg-fetch'

import { API_ROOT } from '../utils/env'

export function fetchChildren (path) {
  return fetchFromDatabase({
    endpoint: 'folders/'
  })
}
export function postFolder (name) {
  return postToDatabase({
    endpoint: `folders/${name}`
  })
}

function fetchFromDatabase ({ endpoint, params }) {
  let url = [API_ROOT, endpoint].join('/')

  if (params) {
    const paramString = toPairs(params).map(param => param.join('=')).join('&')
    url += `?${paramString}`
  }

  const options = {}
  return request(url, options)
}

export function postContent (path, data) {
  // eslint-disable-next-line no-undef
  return fetch(`${API_ROOT}/files/${path}`, {
    method: 'POST',
    body: data
  })
}

function postToDatabase ({ endpoint }) {
  let url = [API_ROOT, endpoint].join('/')

  const options = {
    method: 'POST'
  }

  return request(url, options)
}
