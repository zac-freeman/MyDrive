import React from 'react'
import connect from 'react-redux/es/connect/connect' // TODO: whats going on here
import styled from 'styled-components'
import { loadChildren, selectItem, uploadContent } from '../../ducks/drive.duck'
import PropTypes from 'prop-types'

import 'whatwg-fetch'

import UploadTable from '../../components/Home/UploadTable.js'

import FileRow from '../../components/Home/FileRow.js'
import CreateButton from '../../components/CreateButton/index.js'

const OuterContainer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  background-color: cornflowerblue;
`

const Container = styled.div`
  height: 100%;
  width: 80%;
  border-radius: 2%;
  max-width: 1200px;
  align-self: center;
  margin: 130px;
  padding: 50px 25px 50px 25px;
  background-color: azure;
`

const Header = styled.div`
  display: flex;
  justify-content: space-evenly;
  font-size: 32pt;
`

const HomeButton = styled.a`
  margin-right: auto;
  background-color: lightblue;
  color: black;
  text-decoration: none;
`

const UserButton = styled.a`
  color: black;
  text-decoration: none;
`

const Sidebar = styled.div`
  font-size: 32pt;
  height: 100%; /* Full-height: remove this if you want "auto" height */
  width: 20%; /* Set the width of the sidebar */
  padding-top: 20px;
  float: left;
`

const SideLink = styled.a`
  display: block;
  padding: 6px 8px 6px 16px;
  text-decoration: none;
  color: black;
  text-decoration: none;
`
const RightContent = styled.div`
  padding-left: 20px;
  float: right;
  height: 100%;
  width: 70%;
  border-left: 5px solid black;
`
const PathContentBar = styled.div`
  display: flex;
  flex-direction: row;
  align-items: center;
  padding-right: 5px;
  margin: 15px 0 15px 0;
  font-size: 2rem;
  width: 100%;
  border: 3px solid black;
`
const Path = styled.p`
  margin: 5px;
  width: 100%;
`
const UploadButton = styled.a`
  margin-left: 20px;

  &:hover {
    color: grey;
  }
`

const LeftContent = styled.div`
  height: 100%;
  
`
const ButtonGroup = styled.div`
  display: flex;
  justify-content: flex-end;
  width: 25%;
`

const Row = styled.div`
  display: flex;
  flex-direction: row;
  font-size: 22pt;
  width: 100%;
`
// const RowContent = styled.div`
//  display: flex;
//  width: 100%;
// `
// const RowIcons = styled.div`
//  display: flex;
//  justify-content: flex-end;
//  width: 15%;
//  align-items: center;
// `

// const FOLDER = 'far fa-folder fa-3x'
// const FILE = 'far fa-file fa-3x'
// const ARCHIVE = 'far fa-file-archive fa-3x'

class Home extends React.Component {
  componentDidMount () {
    this.props.loadChildren()
  }
  constructor (props) {
    super(props)
    this.state = {
      uploadClicked: false
    }
  }

  createFileEntries = () => {
    let getRows = collection =>
      collection.map((current, index) => (
        <FileRow
          key={index}
          fileName={current}
          isSelected={current === this.props.selected}
          handleClick={_ => this.props.selectItem(current)}
        />
      ))
    return getRows(this.props.children)
  }

  handleUploadClick = event => {
    this.setState({
      uploadClicked: !this.state.uploadClicked
    })
  }

  handleUploadSubmit = event => {
    event.preventDefault()
    event.persist()
    let path = 'b'
    // eslint-disable-next-line no-undef
    let data = new FormData()
    data.append('file', event.target[0].files[0])
    this.props.uploadContent(path, data)
  }

  render () {
    return (
      <OuterContainer>
        <Container>
          <Header>
            <HomeButton href='HomeButton'>
              my drive
            </HomeButton>
            <UserButton href='UserButton'>
              account
            </UserButton>
          </Header>
          <LeftContent>
            <Sidebar>
              <SideLink href='DriveButton'>drive</SideLink>
              <SideLink href='TrashButton'>trash</SideLink>
            </Sidebar>
          </LeftContent>
          <RightContent>
            <PathContentBar>
              <Path>MyDrive/</Path>
              {this.state.uploadClicked &&
                <UploadTable handleSubmit={this.handleUploadSubmit} />}
              <ButtonGroup>
                <CreateButton />
                <UploadButton onClick={this.handleUploadClick}>
                  Upload
                </UploadButton>
              </ButtonGroup>
            </PathContentBar>
            <Row />
            {this.props.loadingChildren ? null : this.createFileEntries()}
          </RightContent>
        </Container>
      </OuterContainer>
    )
  }
}

Home.propTypes = {
  selected: PropTypes.string,
  children: PropTypes.array,
  loadChildren: PropTypes.func.isRequired,
  selectItem: PropTypes.func.isRequired,
  uploadContent: PropTypes.func.isRequired,
  loadingChildren: PropTypes.bool
}

const mapStateToProps = state => ({
  selected: state.drive.selected,
  path: state.drive.path,
  children: state.drive.children,
  loadingChildren: state.drive.path.loadingChildren,
  loadingChildrenError: state.drive.loadingChildrenError
})

const mapDispatchToProps = dispatch => ({
  loadChildren: path => dispatch(loadChildren(path)),
  selectItem: itemName => dispatch(selectItem(itemName)),
  uploadContent: (path, data) => dispatch(uploadContent(path, data))
})

export default connect(mapStateToProps, mapDispatchToProps)(Home)
