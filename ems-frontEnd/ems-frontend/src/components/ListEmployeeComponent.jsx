import { useState, useEffect } from "react";
import { deleteEmployee, listEmployees } from "../services/EmployeeService";
import { useNavigate, useParams } from "react-router-dom";
const ListEmployeeComponent = () => {
  const [employees, setEmployee] = useState([]);
  const navigate = useNavigate();

  //   const dummyData = [
  //     {
  //       id: "1",
  //       firstName: "akshay",
  //       lastName: "udawant",
  //       email: "akshay@gmail.com",
  //     },
  //     {
  //       id: "2",
  //       firstName: "rohit",
  //       lastName: "sharma",
  //       email: "rohit@gmail.com",
  //     },
  //     {
  //       id: "3",
  //       firstName: "virat",
  //       lastName: "kohli",
  //       email: "virat@gmail.com",
  //     },
  //     {
  //       id: "4",
  //       firstName: "ram",
  //       lastName: "kumar",
  //       email: "ram@gmail.com",
  //     },
  //   ];
  useEffect(() => {
    getAllEmployees();
  }, []);

  function getAllEmployees() {
    listEmployees()
      .then((response) => {
        setEmployee(response.data);
      })
      .catch((error) => console.error(error));
  }
  function addNewEmployee() {
    navigate("/addEmployee");
  }

  function updateEmployee(id) {
    navigate(`/edit-employee/${id}`);
  }
  function removeEmployee(id) {
    deleteEmployee(id)
      .then((response) => {
        getAllEmployees();
        console.log(response.data);
      })
      .catch((error) => {
        console.error(error);
      });
  }

  return (
    <div className="container">
      <h1>List employee components</h1>
      <button className="btn btn-primary" onClick={addNewEmployee}>
        Add Employee
      </button>
      <table className="table table-striped table-bordered">
        <thead>
          <tr>
            <th>Id</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email Id</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {employees.map((employee) => (
            <tr key={employee.id}>
              <td>{employee.id}</td>
              <td>{employee.firstName}</td>
              <td>{employee.lastName}</td>
              <td>{employee.email}</td>
              <td>
                <button
                  className="btn btn-info"
                  onClick={() => updateEmployee(employee.id)}
                >
                  Update
                </button>
                <button
                  className="btn btn-danger"
                  onClick={() => removeEmployee(employee.id)}
                  style={{ marginLeft: "20px" }}
                >
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};
export default ListEmployeeComponent;
