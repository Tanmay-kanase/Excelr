const apiUrl = "/api/employees";

document.addEventListener("DOMContentLoaded", fetchEmployees);

document
  .getElementById("employeeForm")
  .addEventListener("submit", async function (e) {
    e.preventDefault();

    const id = document.getElementById("empId").value;
    const name = document.getElementById("name").value;
    const department = document.getElementById("department").value;
    const salary = document.getElementById("salary").value;

    const employeeData = { name, department, salary };

    try {
      if (id) {
        await fetch(`${apiUrl}/${id}`, {
          method: "PUT",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(employeeData),
        });
        alert("Employee updated successfully!");
      } else {
        await fetch(apiUrl, {
          method: "POST",
          headers: { "Content-Type": "application/json" },
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
  });

async function fetchEmployees() {
  try {
    const response = await fetch(apiUrl);
    const employees = await response.json();

    const tbody = document.getElementById("employeeTableBody");
    tbody.innerHTML = ""; 

    employees.forEach((emp) => {
      const row = document.createElement("tr");
      row.innerHTML = `
                <td>${emp.id}</td>
                <td>${emp.name}</td>
                <td>${emp.department}</td>
                <td>₹${emp.salary.toLocaleString()}</td>
                <td>
                    <button class="action-btn edit-btn" onclick="editEmployee(${emp.id}, '${emp.name}', '${emp.department}', ${emp.salary})">Edit</button>
                    <button class="action-btn delete-btn" onclick="deleteEmployee(${emp.id})">Delete</button>
                </td>
            `;
      tbody.appendChild(row);
    });
  } catch (error) {
    console.error("Error fetching employees:", error);
  }
}

function editEmployee(id, name, department, salary) {
  document.getElementById("formTitle").innerText = "Edit Employee";
  document.getElementById("submitBtn").innerText = "Update Employee";
  document.getElementById("cancelBtn").style.display = "inline-block";

  document.getElementById("empId").value = id;
  document.getElementById("name").value = name;
  document.getElementById("department").value = department;
  document.getElementById("salary").value = salary;
}

async function deleteEmployee(id) {
  if (confirm("Are you sure you want to delete this employee?")) {
    try {
      await fetch(`${apiUrl}/${id}`, { method: "DELETE" });
      alert("Employee deleted successfully!");
      fetchEmployees();
    } catch (error) {
      console.error("Error deleting employee:", error);
    }
  }
}

function resetForm() {
  document.getElementById("employeeForm").reset();
  document.getElementById("empId").value = "";

  document.getElementById("formTitle").innerText = "Add New Employee";
  document.getElementById("submitBtn").innerText = "Save Employee";
  document.getElementById("cancelBtn").style.display = "none";
}
