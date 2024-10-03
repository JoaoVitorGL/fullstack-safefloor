const params = new URLSearchParams(window.location.search);
const sectorId = params.get('sectorId');

// Load employees and show them in a list
function loadEmployees() {
    fetch(`http://localhost:8080/sector/${sectorId}/employees`, {
        method: 'GET',
    })
    .then(response => response.json())
    .then(employees => {
        const employeeList = document.getElementById('employeeList');
        employeeList.innerHTML = '';

        if (employees.length === 0) {
            employeeList.innerHTML = '<li>There are no employees working in this sector.</li>';
        } else {
            employees.forEach(employee => {
                const li = document.createElement('li');
                li.textContent = `Name: ${employee.name}`;
                employeeList.appendChild(li);
            });
        }
    })
    .catch(error => console.error('Error trying to load employees:', error));
}

// load reports and show them on cards
function loadReports() {
    fetch(`http://localhost:8080/sector/${sectorId}/report`, {
        method: 'GET',
    })
    .then(response => response.json())
    .then(reports => {
        const reportCardsContainer = document.getElementById('reportCards');
        reportCardsContainer.innerHTML = '';

        if (reports.length === 0) {
            reportCardsContainer.innerHTML = '<p>There are no reports available for this sector.</p>';
        } else {
            reports.forEach(report => {
                const card = document.createElement('div');
                card.classList.add('col-md-4', 'report-card');
                card.innerHTML = `
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Problem: ${report.problemDescription}</h5>
                            <p class="card-text">Date: ${report.reportDate}</p>
                            <p class="card-text">Count: ${report.reportCount}</p>
                        </div>
                    </div>
                `;
                reportCardsContainer.appendChild(card);
            });
        }
    })
    .catch(error => console.error('Error trying to load reports:', error));
}

document.querySelector('.back-button').addEventListener('click', function () {
   
    window.location.href = '../index.html';
});

document.addEventListener('DOMContentLoaded', () => {
    loadEmployees();
    loadReports();
});
