import React from 'react'
import styled from 'styled-components'
import Popup from 'reactjs-popup'
import { createFolder } from '../../ducks/drive.duck'
import connect from 'react-redux/es/connect/connect'
import PropTypes from 'prop-types'

const Text = styled.a`
`
class CreateButton extends React.Component {
  handleCreateClick = event => {
    this.setState({
      createClicked: !this.state.createClicked
    })
  }
  handleFolderCreation = event => {
    this.props.createFolder(this.state.folderName)
  }
  fileSelected = event => {
    this.setState({
      folderName: event.target.value
    })
  }
  constructor (props) {
    super(props)
    this.state = {
      createClicked: false,
      folderName: ''
    }
  }
  render () {
    if (this.state.createClicked) {
      return <div>create clicked true</div>
    }
    return (
      <Popup
        trigger={<Text onClick={this.handleCreateClick}>Create</Text>}
        position='left'
      >
        <div>
          File Name:
          {' '}
          <input
            type='text'
            name='fname'
            size='6'
            onChange={this.fileSelected}
          />
          <input
            type='submit'
            value='Submit'
            onClick={this.handleFolderCreation}
          />
        </div>
      </Popup>
    )
  }
}

CreateButton.propTypes = {
  createFolder: PropTypes.func.isRequired
}

const mapStateToProps = state => ({})

const mapDispatchToProps = dispatch => ({
  createFolder: name => dispatch(createFolder(name))
})
export default connect(mapStateToProps, mapDispatchToProps)(CreateButton)
