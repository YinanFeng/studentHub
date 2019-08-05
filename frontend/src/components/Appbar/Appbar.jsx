import React, {Component} from 'react';
import { makeStyles } from '@material-ui/core/styles';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import Button from '@material-ui/core/Button';
import IconButton from '@material-ui/core/IconButton';
import ChatIcon from '@material-ui/icons/Chat';

import ProfileChoose from '../ProfileChoose/ProfileChoose';

const useStyles = makeStyles(theme => ({
  root: {
    flexGrow: 1,
  },
  menuButton: {
    marginRight: theme.spacing(2),
  },
  title: {
    flexGrow: 1,
  },
  chatIcon: {
    marginRight: theme.spacing(),
  },
}));

function ButtonAppBar(props) {
  const classes = useStyles();

  return (
    <div className={classes.root}>
      <AppBar position="static">
        <Toolbar>
          {/* <IconButton edge="start" className={classes.menuButton} color="inherit" aria-label="menu"> */}
            <ChatIcon className={classes.chatIcon} />
          {/* </IconButton> */}
          <Typography variant="h6" className={classes.title}>
            Student HuB
          </Typography>

          {props.profile == null?<ProfileChoose changeProfile={props.changeProfile}/>:          
                                 <Typography>
                                    {props.profile}
                                 </Typography>}
{/*           
         <ProfileChoose changeProfile={props.changeProfile}/> */}
          {/* <Button color="inherit">Join now</Button> */}
        </Toolbar>
      </AppBar>
    </div>
  );
}



class Bar extends Component {

    constructor(props){
        super(props);
        this.state = {
            profile:null
        };
        this.changeProfile = this.changeProfile.bind(this)
    }

    changeProfile(pro){
        this.setState({
            profile:pro
        })
        this.props.chooseProfile(pro)
    }


    render(){
        console.log(this.state.profile)
        return(
            <div>
                <ButtonAppBar changeProfile={this.changeProfile} profile={this.state.profile}/>
            </div>
        )
    }
}

export default Bar;