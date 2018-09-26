import React from 'react'
import PropTypes from 'prop-types'
import styled from 'styled-components'

const Row = styled.div`
 display: flex;
 flex-direction: row;
 font-size: 22pt;
//  padding-right: 4px;
width: 100%;
`
const RowContent = styled.div`
 display: flex;
 width: 100%;
`

class FileRow extends React.Component {
  render () {
    return (
      <Row style={{ backgroundColor: this.props.background }}>
        <RowContent>
          <i className={this.props.fileImage} />
          <p>{this.props.fileName}</p>
        </RowContent>
      </Row>
    )
  }
}

FileRow.propTypes = {
  background: PropTypes.string.isRequired,
  fileImage: PropTypes.string.isRequired,
  fileName: PropTypes.string.isRequired
}

export default FileRow
