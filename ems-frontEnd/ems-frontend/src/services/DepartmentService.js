import axios from "axios";
const REST_API_BASE_URL = "http://localhost:8080/api/departments";

export const listDepartment = () => {
  return axios.get(REST_API_BASE_URL);
};

export const addDepartment = (department) => {
  return axios.post(REST_API_BASE_URL, department);
};

export const getDepartmentById = (deparmentId) => {
  return axios.get(REST_API_BASE_URL + "/" + deparmentId);
};
export const updateDepartment = (deparmentId, department) => {
  return axios.put(REST_API_BASE_URL + "/" + deparmentId, department);
};
export const deleteDepartment = (departmentId) => {
  return axios.delete(REST_API_BASE_URL + "/" + departmentId);
};
