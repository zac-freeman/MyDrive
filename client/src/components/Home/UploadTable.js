import React from 'react'
import styled from 'styled-components'

const UploadTableStyle = styled.div`
 position: absolute;
 top: 20%;
 left: 65%;
 width: 500px;
 font-size: 16pt;
 background-color: white;
 border: 1px solid black;
`

const UploadedFile = styled.td`
  max-width: 300px;
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
`

class UploadTable extends React.Component {
  render () {
    return (
      <form method='POST' encType='multipart/form-data' action='/files/example'>
        <UploadTableStyle>
          <table>
            <tr>
              <td>File to upload:</td>
              <UploadedFile><input type='file' name='file' /></UploadedFile>
            </tr>
            <tr><td /><td><input type='submit' value='Upload' /></td></tr>
          </table>
        </UploadTableStyle>
      </form>
    )
  }
}

export default UploadTable
