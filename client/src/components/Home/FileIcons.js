import React from 'react'
import styled from 'styled-components'

const RowIcons = styled.div`
 display: flex;
 flex-flow: row;
 justify-content: flex-end;
 width: 15%;
 align-items: center;
`

class FileIcons extends React.Component {
  render () {
    return (
      <RowIcons>
        <i
          style={{ marginRight: '25px' }}
          className='fas fa-file-download fa-2x'
        />
        <i
          style={{ marginRight: '25px' }}
          className='far fa-trash-alt fa-2x'
        />
      </RowIcons>
    )
  }
}

export default FileIcons
