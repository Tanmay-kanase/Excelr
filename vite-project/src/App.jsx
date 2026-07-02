import { useState } from "react";
import Home from "./Home";

const apiUrl = "/api/employees";

function App() {
  const [employees, setEmployees] = useState([]);
  const [formData, setFormData] = useState({
    id: "",
    name: "",
    department: "",
    salary: "",
  });

  const [isEditing, setIsEditing] = useState(false);

  const fetchEmployees = async () => {
    try {
      const response = await fetch(apiUrl);
      const data = await response.json();
      setEmployees(data);
    } catch (error) {
      console.error("Error fetching employees:", error);
    }
  };

  const handleChange = (e) => {
    setFormData((prev) => ({
      ...prev,
      [e.target.name]: e.target.value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const employeeData = {
      name: formData.name,
      department: formData.department,
      salary: Number(formData.salary),
    };

    try {
      if (isEditing) {
        await fetch(`${apiUrl}/${formData.id}`, {
          method: "PUT",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(employeeData),
        });

        alert("Employee updated successfully!");
      } else {
        await fetch(apiUrl, {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(employeeData),
        });

        alert("Employee added successfully!");
      }

      resetForm();
      fetchEmployees();
    } catch (error) {
      console.error("Error saving employee:", error);
      alert("An error occurred while saving the employee.");
    }
  };

  const handleEdit = (employee) => {
    setFormData({
      id: employee.id,
      name: employee.name,
      department: employee.department,
      salary: employee.salary,
    });

    setIsEditing(true);
  };

  const handleDelete = async (id) => {
    const confirmed = window.confirm(
      "Are you sure you want to delete this employee?",
    );

    if (!confirmed) return;

    try {
      await fetch(`${apiUrl}/${id}`, {
        method: "DELETE",
      });

      alert("Employee deleted successfully!");
      fetchEmployees();
    } catch (error) {
      console.error("Error deleting employee:", error);
    }
  };

  const resetForm = () => {
    setFormData({
      id: "",
      name: "",
      department: "",
      salary: "",
    });

    setIsEditing(false);
  };

  return (
    <>
      <Home />
    </>
  );
}

export default App;
