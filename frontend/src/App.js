import React, { Component } from 'react';
import "bootstrap/dist/css/bootstrap.min.css";
import Reservation from './components/Reservation'


class App extends Component {
  render() {
    return (
      <div>
        <nav className="navbar navbar-expand navbar-dark bg-dark">
          <div className="navbar-brand">
            Restaurant Resrvation
          </div>
        </nav>
        <div>
          <Reservation />
        </div>

      </div>
    )
  };
}

export default App;
