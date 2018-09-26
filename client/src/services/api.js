import toPairs from 'lodash/toPairs'
import request from '../utils/request'

const DATABASE_ROOT = ''

export function fetchChildren (path) {
  return fetchFromDatabase({
    endpoint: 'folders/',
    params: {
      path
    }
  })
}

function fetchFromDatabase ({ endpoint, params }) {
  let url = [DATABASE_ROOT, endpoint].join('/')

  if (params) {
    const paramString = toPairs(params).map(param => param.join('=')).join('&')
    url += `?${paramString}`
  }

  const options = {}
  return request(url, options)
}

export function postContent (path, content) {
  postToDatabase()
}

function postToDatabase ({ endpoint, params }) {}
