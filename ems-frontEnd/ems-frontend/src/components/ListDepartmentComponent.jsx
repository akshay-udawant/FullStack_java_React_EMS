import { useEffect, useState } from "react";
import {
  deleteDepartment,
  listDepartment,
} from "../services/DepartmentService";
import { useNavigate } from "react-router-dom";
const ListDepartmentComponent = () => {
  const [departments, setDepartments] = useState([]);
  const navigate = useNavigate();
  useEffect(() => {
    getAllDepartment();
  }, []);

  function getAllDepartment() {
    listDepartment()
      .then((response) => {
        console.log(response.data);
        setDepartments(response.data);
      })
      .catch((error) => {
        console.error(error);
      });
  }
  function addDepartment() {
    navigate("/addDepartment");
  }
  function updateDepartment(id) {
    navigate(`/edit-department/${id}`);
  }
  function removeDepartment(id) {
    deleteDepartment(id).then((response) => {
      console.log(response.data);
      getAllDepartment();
    });
  }
  return (
    <div className="container">
      <h1 className="text-center">List Department Component</h1>
      <button className="btn btn-primary mb-2" onClick={addDepartment}>
        Add Department
      </button>
      <table className="table table-striped table-bordered">
        <thead>
          <tr>
            <th>Id</th>
            <th>Department Name</th>
            <th>Department Description</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {departments.map((department) => (
            <tr key={department.id}>
              <td>{department.id}</td>
              <td>{department.departmentName}</td>
              <td>{department.departmentDescription}</td>
              <td>
                <button
                  className="btn btn-info"
                  onClick={() => updateDepartment(department.id)}
                >
                  Update
                </button>
                <button
                  className="btn btn-danger"
                  onClick={() => removeDepartment(department.id)}
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
export default ListDepartmentComponent;
