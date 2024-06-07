import { NavLink } from "react-router-dom";

const HeaderComponent = () => {
  return (
    <div>
      <footer>
        <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
          <a className="navbar-brand" href="www.google.com">
            Employee Management System
          </a>
          <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
              <li class="nav-item ">
                <NavLink className="nav-link" to="/employees">
                  Employees
                </NavLink>
              </li>
              <li class="nav-item ">
                <NavLink className="nav-link" to="/departments">
                  Departments
                </NavLink>
              </li>
            </ul>
          </div>
        </nav>
      </footer>
    </div>
  );
};
export default HeaderComponent;
