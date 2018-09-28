import React from 'react'
import PropTypes from 'prop-types'
import styled from 'styled-components'

const Row = styled.div`
 display: flex;
 flex-direction: row;
 font-size: 22pt;
 width: 100%;
`
const RowContent = styled.div`
 display: flex;
 width: 100%;
`
const RowIcons = styled.div`
 display: flex;
 justify-content: flex-end;
 width: 15%;
 align-items: center;
`

const FaIcon = styled.i`
 margin-right: 25px; 
`
class FileRow extends React.Component {
  render () {
    return (
      <Row>
        <RowContent>
          <FaIcon className='far fa-file fa-3x' />
          <p>{this.props.fileName}</p>
        </RowContent>
        <RowIcons>
          <a href={`http://localhost:3000/files/${this.props.fileName}`}>
            <FaIcon className='fas fa-file-download fa-2x' />
          </a>
          <FaIcon className='far fa-trash-alt fa-2x' />
        </RowIcons>
      </Row>
    )
  }
}

FileRow.propTypes = {
  fileName: PropTypes.string.isRequired
}

export default FileRow
