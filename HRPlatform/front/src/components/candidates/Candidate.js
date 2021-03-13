import React from "react";
import { Table, Button, Form, ButtonGroup } from "react-bootstrap";

import Axios from "../../apis/Axios"

class Candidate extends React.Component {
  constructor(props) {
    super(props);

    let candidate = {
      name: "",
      mail: "",
      date: "",
      number: ""
    };

    let skill = {
      name:"",
      candidateId: -1
    }

    this.state = {
      candidate: candidate,
      candidates: [],
      skill: skill,
      skills: [],
      search: { candidateName: "", skillName: "" },
      pageNum: 0,
      totalPages: 1,
    };
  }

  componentDidMount() {
    this.getData();
  }

  async getData() {
    await this.getSkills();
    await this.getCandidates();
  }

  async getCandidates(page = null) {
    let config = { params: {} };


    if (this.state.search.candidateName != "") {
      config.params.candidateName = this.state.search.candidateName;
    }

    if (this.state.search.skillName != "") {
      config.params.skillName = this.state.search.skillName;
    }

    if (page != null) {
      config.params.pageNum = page;
    } else {
      config.params.pageNum = this.state.pageNum;
    }

    try {
      console.log(config)
      let result = await Axios.get("/candidates", config);
      if (result && result.status === 200) {
        this.setState({
          candidates: result.data,
          totalPages: result.headers["total-pages"],
        });
      }
    } catch (error) {
      alert("Loading failed.");
    }
  }

  async getSkills() {
    try {
      let result = await Axios.get("/skills");
      if (result && result.status === 200) {
        this.setState({
          skills: result.data,
        });
      }
    } catch (error) {
      alert("Loading failed.");
    }
  }

  async doAdd() {
    try {
      await Axios.post("/candidates/", this.state.candidate);

      let candidate = {
        name: "",
        mail: "",
        date: "",
        number: "",
      };

      this.setState({ candidate: candidate });

      this.getCandidates();
    } catch (error) {
      alert("Saving failed.");
    }
  }

  async doAddSkill(){
    try{
      await Axios.post("/skills/", this.state.skill);
    
    let skill = {
      name: "",
      candidateId: -1
    }

    this.setState({skill: skill})

    this.getData();
    }catch (error) {
      alert("Saving failed.");
    }
  }

  async doDelete(candidateId) {
    try {
      await Axios.delete("/candidates/" + candidateId);
      this.getCandidates();
    } catch (error) {
      alert("Deleting failded.");
    }
  }

  async doDeleteSkill(skillId){
    try{
      await Axios.delete("/skills/" + skillId);
      this.getData();
    } catch (error) {
      alert("Deleting failded.");
    }
  }

  addValueInputChange(event) {
    let control = event.target;

    let name = control.name;
    let value = control.value;

    let candidate = this.state.candidate;
    candidate[name] = value;

    this.setState({ candidate: candidate });
  }

  addSkillValueInputChange(event) {
    let control = event.target;

    let name = control.name;
    let value = control.value;

    let skill = this.state.skill;
    skill[name] = value;

    this.setState({ skill: skill });
  }

  searchValueInputChange(event) {
    let control = event.target;

    let name = control.name;
    let value = control.value;

    let search = this.state.search;
    search[name] = value;

    this.setState({ search: search });
  }

  doSearch() {
    this.setState({ totalPages: 1, pageNum: 0 });
    this.getCandidates(0);
  }

  changePage(direction) {
    let page = this.state.pageNum + direction;
    this.getCandidates(page);
    this.setState({ pageNum: page });
  }

  render() {
    return (
      <div>
        <h1>Candidates</h1>

        <Form>
          <Form.Group>
            <Form.Label>Name</Form.Label>
            <Form.Control
              onChange={(event) => this.addValueInputChange(event)}
              name="name"
              value={this.state.candidate.name}
              as="input"
            ></Form.Control>
          </Form.Group>
          <Form.Group>
            <Form.Label>Mail</Form.Label>
            <Form.Control
              onChange={(event) => this.addValueInputChange(event)}
              name="mail"
              value={this.state.candidate.mail}
              as="input"
            ></Form.Control>
          </Form.Group>
          <Form.Group>
            <Form.Label>Date</Form.Label>
            <Form.Control
              onChange={(event) => this.addValueInputChange(event)}
              name="date"
              value={this.state.candidate.date}
              as="input"
            ></Form.Control>
          </Form.Group>
          <Form.Group>
            <Form.Label>Number</Form.Label>
            <Form.Control
              onChange={(event) => this.addValueInputChange(event)}
              name="number"
              value={this.state.candidate.number}
              as="input"
            ></Form.Control>
          </Form.Group>
          <Button variant="primary" onClick={() => this.doAdd()}>
            Add
          </Button>
        </Form>

        <h1>Add skill</h1>

        <Form>
          <Form.Group>
            <Form.Label>Name</Form.Label>
            <Form.Control
              onChange={(event) => this.addSkillValueInputChange(event)}
              name="name"
              value={this.state.skill.name}
              as="input"
            ></Form.Control>
          </Form.Group>
          <Form.Group>
            <Form.Label>Candidate</Form.Label>
            <Form.Control
              onChange={(event) => this.addSkillValueInputChange(event)}
              name="candidateId"
              value={this.state.skill.candidateId}
              as="select"
            >
              <option value={-1}></option>
              {this.state.candidates.map((candidate) => {
                return (
                  <option value={candidate.id} key={candidate.id}>
                    {candidate.name}
                  </option>
                );
              })}
            </Form.Control>
          </Form.Group>
          <Button variant="primary" onClick={() => this.doAddSkill()}>
            Add
          </Button>
        </Form>

        <Form style={{marginTop:35}}>
          <Form.Group>
            <Form.Label>Candidate Name</Form.Label>
            <Form.Control
              value={this.state.search.candidateName}
              name="candidateName"
              as="input"
              onChange={(e) => this.searchValueInputChange(e)}
            ></Form.Control>
          </Form.Group>
          <Form.Group>
            <Form.Label>Skill Name</Form.Label>
            <Form.Control
              onChange={(event) => this.searchValueInputChange(event)}
              name="skillName"
              value={this.state.search.skillName}
              as="input"
            ></Form.Control>
          </Form.Group>
          <Button onClick={() => this.doSearch()}>Search</Button>
        </Form>

        <ButtonGroup style={{ marginTop: 25 }}>
          <Button
            disabled={this.state.pageNum == 0}
            onClick={() => this.changePage(-1)}
          >
            Previous
          </Button>
          <Button
            disabled={this.state.pageNum + 1 == this.state.totalPages}
            onClick={() => this.changePage(1)}
          >
            Next
          </Button>
        </ButtonGroup>

        <Table bordered striped style={{ marginTop: 5 }}>
          <thead className="thead-dark">
            <tr>
              <th>Name</th>
              <th>Mail</th>
              <th>Date</th>
              <th>Number</th>
              <th>Skills</th>
              <th colSpan={2}>Actions</th>
            </tr>
          </thead>
          <tbody>
            {this.state.candidates.map((candidate) => {
              return (
                <tr key={candidate.id}>
                  <td>{candidate.name}</td>
                  <td>{candidate.mail}</td>
                  <td>{candidate.date}</td>
                  <td>{candidate.number}</td>
                  <td>{candidate.skillsDto.map((skill) => {
                    return(
                      <p>
                        {skill.name}
                        <Button
                      variant="danger"
                      onClick={() => this.doDeleteSkill(skill.id)}
                      style={{ marginLeft: 5 }}
                    >
                      Remove skill
                    </Button>
                      </p>
                      
                    )
                  })}
                  </td>
                  <td>
                    <Button
                      variant="danger"
                      onClick={() => this.doDelete(candidate.id)}
                      style={{ marginLeft: 5 }}
                    >
                      Delete
                    </Button>
                  </td>
                </tr>
              );
            })}
          </tbody>
        </Table>
      </div>
    );
  }
}

export default Candidate;
