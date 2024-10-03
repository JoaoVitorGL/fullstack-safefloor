const params = new URLSearchParams(window.location.search);
const sectorId = params.get('sectorId');

// Load employees and fill div to show them
function loadEmployees() {
    fetch(`http://localhost:8080/employee/sector/${sectorId}`, {
        method: 'GET',
    })
    .then(response => response.json())
    .then(employees => {
        const employeeButtonsContainer = document.querySelector('.employeeButtons');
        employeeButtonsContainer.innerHTML = ''; 
        
        employees.forEach(employee => {
            const button = document.createElement('button');
            button.classList.add('btn', 'btn-primary', 'm-2');
            button.textContent = employee.name;

            button.onclick = () => {
                window.location.href = `../reportProblem2/report2.html?&employeeId=${employee.id}`;
            };

            employeeButtonsContainer.appendChild(button);
        });
    })
    .catch(error => console.error('Error trying to load employees:', error));
}
document.addEventListener('DOMContentLoaded', loadEmployees);

document.querySelector('.back-button').addEventListener('click', function () {
   
    window.location.href = '../index.html';
});
