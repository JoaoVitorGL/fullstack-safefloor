// Load sectors and fill div to show them
function loadSectors() {
    fetch('http://localhost:8080/sector/list', {
        method: 'GET',
    })
    .then(response => response.json())
    .then(sectors => {
        const sectorButtonsContainer = document.querySelector('.sectorButtons');
        const sectorSelect = document.getElementById('sectorSelect');
        
        sectorButtonsContainer.innerHTML = ''; 
        
        sectorSelect.innerHTML = '';
        sectors.forEach(sector => {
            const option = document.createElement('option');
            option.value = sector.id;
            option.textContent = sector.name;
            sectorSelect.appendChild(option);

            const button = document.createElement('button');
            button.classList.add('btn', 'btn-primary', 'm-2');
            button.textContent = sector.name;

            button.onclick = () => {
                window.location.href = `../sectorDetails/sector-details.html?sectorId=${sector.id}`;
            };

            sectorButtonsContainer.appendChild(button);
        });
    })
    .catch(error => console.error('Error trying to load sectors:', error));
}

document.addEventListener('DOMContentLoaded', loadSectors);

// New Sector
document.getElementById('sectorForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const sectorName = document.getElementById('sectorName').value;

    const sectorData = {
        name: sectorName
    };

    fetch("http://localhost:8080/sector/create", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(sectorData)
    })
    .then(response => {
        if (response.status === 201) {
            alert('Sector successfully created!');
            loadSectors();
            $('#modalCreateSector').modal('hide');
        } else {
            alert('Error creating sector');
        }
    })
    .catch(err => console.log('Error creating sector:', err));
});

// New Employee
document.getElementById('employeeForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const employeeName = document.getElementById('employeeName').value;
    const sectorId = document.getElementById('sectorSelect').value;
    const sectorName = document.getElementById('sectorSelect').selectedOptions[0].text;

    const employeeData = {
        name: employeeName,
        sector: {
            id: sectorId,
            name: sectorName
        }
    };

    fetch("http://localhost:8080/employee/create", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(employeeData)
    })
    .then(response => {
        if (response.status === 201) {
            alert('Employee registered successfully!');
            $('#modalRegisterEmployee').modal('hide');
        } else {
            alert('Error registering employee');
        }
    })
    .catch(err => console.log('Error registering employee:', err));
});