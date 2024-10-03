// Load sectors and fill div to show them
function loadSectors() {
    fetch('http://localhost:8080/sector/list', {
        method: 'GET',
    })
    .then(response => response.json())
    .then(sectors => {
        const sectorButtonsContainer = document.querySelector('.sectorButtons');
        sectorButtonsContainer.innerHTML = ''; 
        
        sectors.forEach(sector => {
            const button = document.createElement('button');
            button.classList.add('btn', 'btn-primary', 'm-2');
            button.textContent = sector.name;

            button.onclick = () => {
                window.location.href = `../reportProblem1/report1.html?sectorId=${sector.id}`;
            };

            sectorButtonsContainer.appendChild(button);
        });
    })
    .catch(error => console.error('Error trying to load sectors:', error));
}

document.addEventListener('DOMContentLoaded', loadSectors);