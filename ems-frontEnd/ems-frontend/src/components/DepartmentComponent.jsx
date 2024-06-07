import React, { useEffect, useState } from "react";
import {
  addDepartment,
  getDepartmentById,
  updateDepartment,
} from "../services/DepartmentService";
import { useNavigate, useParams } from "react-router-dom";
const DepartmentComponent = () => {
  const [departmentName, setDepartmentName] = useState("");
  const [departmentDescription, setDepartmentDescription] = useState("");
  const navigate = useNavigate();
  const { id } = useParams();

  useEffect(() => {
    getDepartmentById(id).then((response) => {
      setDepartmentName(response.data.departmentName);
      setDepartmentDescription(response.data.departmentDescription);
    });
  }, [id]);

  function saveOrUpdateDepartment(e) {
    e.preventDefault();
    const department = { departmentName, departmentDescription };
    if (id) {
      updateDepartment(id, department)
        .then((response) => {
          console.log(response.data);
          navigate("/departments");
        })
        .catch((error) => {
          console.error(error);
        });
    } else {
      console.log(department);
      addDepartment(department)
        .then((response) => {
          console.log(department);
          navigate("/departments");
        })
        .catch((error) => {
          console.error(error);
        });
    }
  }
  function pageTitle() {
    if (id) {
      return <h2 className="text-center">Update Department</h2>;
    } else {
      return <h2 className="text-center">Add Department</h2>;
    }
  }

  return (
    <div className="container mt-5">
      <div className="row">
        <div className="card col-md-6 offset-md-3">
          {pageTitle()}
          <div className="card-body">
            <form>
              <div className="form-group mb-2">
                <label className="form-label">Department Name :</label>
                <input
                  type="text"
                  name="departmentName"
                  value={departmentName}
                  className="form-control"
                  placeholder="Enter department name"
                  onChange={(e) => setDepartmentName(e.target.value)}
                />
              </div>

              <div className="form-group mb-2">
                <label className="form-label">Department Description :</label>
                <input
                  type="text"
                  name="departmentDescription"
                  value={departmentDescription}
                  className="form-control"
                  placeholder="Enter department description"
                  onChange={(e) => setDepartmentDescription(e.target.value)}
                />
              </div>
              <button
                className="btn btn-primary"
                onClick={(e) => saveOrUpdateDepartment(e)}
              >
                Submit
              </button>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
};

export default DepartmentComponent;
