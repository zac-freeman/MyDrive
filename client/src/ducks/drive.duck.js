import { fetchChildren, postContent, postFolder } from '../services/api'

export const SELECT_ITEM = 'cooksys/MyDrive/Drive/SELECT_ITEM'
export const CHANGE_PATH = 'cooksys/MyDrive/Drive/CHANGE_PATH'

export const LOAD_CHILDREN_BEGIN = 'cooksys/MyDrive/Drive/LOAD_CHILDREN_BEGIN'
export const LOAD_CHILDREN_DONE = 'cooksys/MyDrive/Drive/LOAD_CHILDREN_DONE'
export const LOAD_CHILDREN_FAILURE =
  'cooksys/MyDrive/Drive/LOAD_CHILDREN_FAILURE'

export const OPEN_UPLOAD_UI = 'cooksys/MyDrive/Drive/OPEN_UPLOAD_UI'
export const UPLOAD_CONTENT_BEGIN = 'cooksys/MyDrive/Drive/UPLOAD_CONTENT_BEGIN'
export const UPLOAD_CONTENT_DONE = 'cooksys/MyDrive/Drive/UPLOAD_CONTENT_DONE'
export const UPLOAD_CONTENT_FAILURE =
  'cooksys/MyDrive/Drive/UPLOAD_CONTENT_FAILURE'
export const CREATE_FOLDER_BEGIN = 'cooksys/MyDrive/Drive/CREATE_FOLDER_BEGIN'
export const CREATE_FOLDER_DONE = 'cooksys/MyDrive/Drive/CREATE_FOLDER_DONE'
export const CREATE_FOLDER_FAILURE =
  'cooksys/MyDrive/Drive/CREATE_FOLDER_FAILURE'

const initialState = {
  selected: '',
  path: ['Drive'],
  children: [],
  loadingChildren: true,
  loadChildrenError: false,

  uploading: false,
  uploadUIOpen: false,
  uploadError: false
}

export default function (state = initialState, action) {
  switch (action.type) {
    case SELECT_ITEM:
      console.log(`${action.payload} selected.`)
      return {
        ...state,
        selected: action.payload
      }
    case CHANGE_PATH:
      console.log(
        `Path changed from ${state.path.join('/')} to ${action.payload.join('/')}`
      )
      return {
        ...state,
        path: action.payload
      }

    case LOAD_CHILDREN_BEGIN:
      console.log('Loading children...')
      return {
        ...state,
        children: initialState.children,
        loadingChildren: true,
        loadChildrenError: false
      }
    case LOAD_CHILDREN_DONE:
      console.log('Loaded children successfully!')
      console.log(action.payload)
      return {
        ...state,
        children: action.payload,
        loadingChildren: false,
        loadChildrenError: false
      }
    case LOAD_CHILDREN_FAILURE:
      console.log('Failed to load children.')
      return {
        ...state,
        children: initialState.children,
        loadingChildren: false,
        loadChildrenError: true
      }

    case OPEN_UPLOAD_UI:
      console.log('Opened upload UI.')
      return {
        ...state,
        uploading: false,
        uploadUIOpen: true,
        uploadError: false
      }
    case UPLOAD_CONTENT_BEGIN:
      console.log('Uploading content...')
      return {
        ...state,
        uploading: true,
        uploadUIOpen: false,
        uploadError: false
      }
    case UPLOAD_CONTENT_DONE:
      console.log('Uploaded Content successfully!')
      return {
        ...state,
        uploading: false,
        uploadUIOpen: false,
        uploadError: false
      }
    case UPLOAD_CONTENT_FAILURE:
      console.log('Failed to upload content.')
      return {
        ...state,
        uploading: false,
        uploadUIOpen: false,
        uploadError: true
      }
    case CREATE_FOLDER_BEGIN:
      console.log('Creating folder')
      return {
        ...state
      }
    case CREATE_FOLDER_DONE:
      console.log('Folder created successfully!')
      return {
        ...state
      }
    case CREATE_FOLDER_FAILURE:
      console.log('Failed to create folder.')
      return {
        ...state
      }
    default:
      return state
  }
}

export const selectItem = itemName => ({
  type: SELECT_ITEM,
  payload: itemName
})

export const changePath = newPath => ({
  type: CHANGE_PATH,
  payload: newPath
})

const loadChildrenBegin = () => ({
  type: LOAD_CHILDREN_BEGIN
})

const loadChildrenDone = children => ({
  type: LOAD_CHILDREN_DONE,
  payload: children
})

const loadChildrenFailure = () => ({
  type: LOAD_CHILDREN_FAILURE
})

export const loadChildren = path => dispatch => {
  dispatch(loadChildrenBegin())
  return fetchChildren(path)
    .then(({ files, folders }) => {
      dispatch(loadChildrenDone(files))
    })
    .catch(err => dispatch(loadChildrenFailure(err)))
}

const uploadContentBegin = () => ({
  type: UPLOAD_CONTENT_BEGIN
})

const uploadContentDone = () => ({
  type: UPLOAD_CONTENT_DONE
})

const uploadContentFailure = () => ({
  type: UPLOAD_CONTENT_FAILURE
})

export const uploadContent = (path, data) => dispatch => {
  dispatch(uploadContentBegin())
  return postContent(path, data)
    .then(() => dispatch(uploadContentDone()))
    .then(() => dispatch(loadChildren(path)))
    .catch(err => dispatch(uploadContentFailure(err)))
}

const createFolderBegin = () => ({
  type: CREATE_FOLDER_BEGIN
})

const createFolderDone = () => ({
  type: CREATE_FOLDER_DONE
})

const createFolderFailure = () => ({
  type: CREATE_FOLDER_FAILURE
})

export const createFolder = name => dispatch => {
  dispatch(createFolderBegin())
  return postFolder(name)
    .then(() => {
      dispatch(createFolderDone())
    })
    .catch(err => dispatch(createFolderFailure(err)))
}
