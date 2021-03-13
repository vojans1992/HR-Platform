import React from "react";
import ReactDOM from "react-dom";
import {
  Route,
  Link,
  HashRouter as Router,
  Switch
} from "react-router-dom";
import Home from "./components/Home";
import NotFound from "./components/NotFound";
import { Container, Navbar, Nav } from "react-bootstrap";
import Candidate from "./components/candidates/Candidate"

class App extends React.Component {
  render() {
      return (
        <div>
          <Router>
            <Navbar bg="dark" variant="dark" expand>
              <Navbar.Brand as={Link} to="/">
                HR
              </Navbar.Brand>
              <Nav className="mr-auto">
                <Nav.Link as={Link} to="/components">
                  Candidates
                </Nav.Link>
              </Nav>
            </Navbar>

            <Container style={{marginTop:25}}>
              <Switch>
                <Route exact path="/" component={Home} />
                <Route exact path="/components" component={Candidate} />
                <Route component={NotFound} />
              </Switch>
            </Container>
          </Router>
        </div>
      );
    }
}

ReactDOM.render(<App />, document.querySelector("#root"));
