// Load, show problems and create report
function loadProblems() {
    fetch(`http://localhost:8080/problem/list`, {
        method: 'GET',
    })
    .then(response => response.json())
    .then(problems => {
        const problemButtonsContainer = document.querySelector('.problemButtons');
        problemButtonsContainer.innerHTML = '';

        problems.forEach(problem => {
            const button = document.createElement('button');
            button.classList.add('btn', 'btn-danger', 'm-2');
            button.textContent = problem.description;

            button.onclick = () => {
                const params = new URLSearchParams(window.location.search);
                const employeeId = params.get('employeeId');
                const currentDate = new Date();
                const reportDate = currentDate.toISOString().split('T')[0];

                fetch('http://localhost:8080/report/create', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        date: reportDate,
                        employee: { id: employeeId },
                        problem: { id: problem.id }
                    })
                })
                .then(response => {
                    if (response.ok) {
                        alert('Report created successfully!');
                        problemButtonsContainer.innerHTML = '<p class="alert alert-success">Report created successfully!</p>';

                        setTimeout(() => {
                            window.location.href = '../index.html';
                        }, 1000);
                    } else {
                        alert('Error creating report');
                    }
                })
                .catch(error => console.error('Error creating report:', error));
            };

            problemButtonsContainer.appendChild(button);
        });
    })
    .catch(error => console.error('Error trying to load problems:', error));
}

document.addEventListener('DOMContentLoaded', loadProblems);

document.querySelector('.back-button').addEventListener('click', function () {
   
    window.location.href = '../index.html';
});
