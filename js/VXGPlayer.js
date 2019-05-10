import PropTypes from 'prop-types';
import { requireNativeComponent, View } from 'react-native';

var iface = {
  name: 'VXGPlayer',
  propTypes: {
      src: PropTypes.string,
      ...View.propTypes // include the default view properties
  },
};

module.exports = requireNativeComponent('RCTVXGPlayer', iface);