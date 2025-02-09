import React from 'react';
import PropTypes from 'prop-types';
import { makeStyles } from '@material-ui/core/styles';
import Button from '@material-ui/core/Button';
import Avatar from '@material-ui/core/Avatar';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemAvatar from '@material-ui/core/ListItemAvatar';
import ListItemText from '@material-ui/core/ListItemText';
import DialogTitle from '@material-ui/core/DialogTitle';
import Dialog from '@material-ui/core/Dialog';
import PersonIcon from '@material-ui/icons/Person';
import AddIcon from '@material-ui/icons/Add';
import Typography from '@material-ui/core/Typography';
import { blue } from '@material-ui/core/colors';

const emails = ['username@gmail.com', 'user02@gmail.com'];
const useStyles = makeStyles({
  avatar: {
    backgroundColor: blue[100],
    color: blue[600],
  },
});

function SimpleDialog(props) {
  const classes = useStyles();
  const { onClose, selectedValue, open, changeProfile } = props;

  function handleClose() {
    //   console.log(props)
    // console.log("there")
    // console.log(selectedValue)
    onClose(selectedValue);
  }

  function handleListItemClick(value) {
        // console.log("here")
        // console.log(value)
        changeProfile(value)
    onClose(value);
  }

  return (
    <Dialog onClose={handleClose} aria-labelledby="simple-dialog-title" open={open}>
      <DialogTitle id="simple-dialog-title">Join Student Hub as a ...</DialogTitle>
     
      <List>
        {/* {emails.map(email => (
          <ListItem button onClick={() => handleListItemClick(email)} key={email}>
            <ListItemAvatar>
              <Avatar className={classes.avatar}>
                <PersonIcon />
              </Avatar>
            </ListItemAvatar>
            <ListItemText primary={email} />
          </ListItem>
        ))} */}

        <ListItem button onClick={() => handleListItemClick('student')}>
          <ListItemAvatar>
            <Avatar className={classes.avatar}>
            <PersonIcon />
            </Avatar>
          </ListItemAvatar>
          <ListItemText primary="Lovely Student" />
        </ListItem>

        <ListItem button onClick={() => handleListItemClick('mentor')}>
          <ListItemAvatar>
            <Avatar className={classes.avatar}>
            <PersonIcon />
            </Avatar>
          </ListItemAvatar>
          <ListItemText primary="Awesome Mentor" />
        </ListItem>
      </List>
    </Dialog>
  );
}

SimpleDialog.propTypes = {
  onClose: PropTypes.func.isRequired,
  open: PropTypes.bool.isRequired,
  selectedValue: PropTypes.string.isRequired,
};

export default function SimpleDialogDemo(props) {
  const [open, setOpen] = React.useState(false);
  const [selectedValue, setSelectedValue] = React.useState(emails[1]);

  function handleClickOpen() {
    //   console.log(props)
    setOpen(true);
  }

  const handleClose = value => {
    setOpen(false);
    setSelectedValue(value);
  };

  return (
    <div>
      {/* <Typography variant="subtitle1">Selected: {selectedValue}</Typography> */}
      {/* <br /> */}
      <Button color="inherit" onClick={handleClickOpen}>
        Join Now
      </Button>
      <SimpleDialog selectedValue={selectedValue} open={open} onClose={handleClose}
                    changeProfile={props.changeProfile}
      />
    </div>
  );
}

