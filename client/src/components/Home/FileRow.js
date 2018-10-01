import React from 'react'
import PropTypes from 'prop-types'
import styled from 'styled-components'

import { API_ROOT } from '../../utils/env'

const Row = styled.div`
 display: flex;
 flex-direction: row;
 font-size: 22pt;
 width: 100%;
`

const SelectedRow = styled(Row)`
  border: 2px solid black;
  border-radius: 5px;
`

const RowContent = styled.div`
 display: flex;
 width: 100%;
`
const RowIcons = styled.div`
 display: flex;
 justify-content: space-around;
 width: 150px;
 align-items: center;
`

const FaIcon = styled.i`
 margin-right: 25px; 
`
class FileRow extends React.Component {
  constructor (props) {
    super(props)
    this.state = { show: false }
  }

  render () {
    /* eslint-disable */
    const row = this.props.isSelected
      ? <SelectedRow
          onMouseEnter={() => this.setState({ show: true })}
          onMouseLeave={() => this.setState({ show: false })}
        >
          <RowContent>
            <FaIcon className="far fa-file fa-3x" />
            <p>{this.props.fileName}</p>
          </RowContent>
          <RowIcons>
            <a href={`${API_ROOT}/files/${this.props.fileName}`}>
              <i className="fas fa-file-download fa-2x" />
            </a>
            <i className="far fa-trash-alt fa-2x" />
          </RowIcons>
        </SelectedRow>
      : <Row
          onMouseEnter={() => this.setState({ show: true })}
          onMouseLeave={() => this.setState({ show: false })}
          onClick={this.props.handleClick}
        >
          <RowContent>
            <FaIcon className="far fa-file fa-3x" />
            <p>{this.props.fileName}</p>
          </RowContent>
          {this.state.show || this.props.isSelected
            ? <RowIcons>
                <a href={`${API_ROOT}/files/${this.props.fileName}`}>
                  <i className="fas fa-file-download fa-2x" />
                </a>
                <i className="far fa-trash-alt fa-2x" />
              </RowIcons>
            : null}
        </Row>
    /* eslint-enable */

    return row
  }
}

FileRow.propTypes = {
  fileName: PropTypes.string.isRequired,
  isSelected: PropTypes.bool.isRequired,
  handleClick: PropTypes.func.isRequired
}

export default FileRow
