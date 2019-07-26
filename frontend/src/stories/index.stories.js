import React from 'react';

import { storiesOf } from '@storybook/react';
import { action } from '@storybook/addon-actions';
import { linkTo } from '@storybook/addon-links';

import { Button, Welcome } from '@storybook/react/demo';

import App from '../components/App';

import Review from '../components/Review/Review';
import Appbar from '../components/Appbar/Appbar';
import ProfileChoose from '../components/ProfileChoose/ProfileChoose';


storiesOf('Welcome', module).add('to Storybook', () => <Welcome showApp={linkTo('Button')} />);

storiesOf('Button', module)
  .add('with text', () => <Button onClick={action('clicked')}>Hello Button</Button>)
  .add('with some emoji', () => (
    <Button onClick={action('clicked')}>
      <span role="img" aria-label="so cool">
        😀 😎 👍 💯
      </span>
    </Button>
  )).add('test app', ()=><App />);

storiesOf('IndexPage', module).add('review', () => <Review />)
                              .add('appBar', ()=> <Appbar />)
                              .add('indexPage', ()=>  <div><Appbar /><Review /></div>)
                              .add('profileChoose', ()=> <ProfileChoose />)

                              ;

