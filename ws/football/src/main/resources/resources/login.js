const wrapper = document.querySelector('.wrapper');
const loginLink = document.querySelector('.login-link');
const registerLink = document.querySelector('.register-link');

registerLink.addEventListener('click', ()=> {
    wrapper.classList.add('active');
});

loginLink.addEventListener('click', ()=> {
    wrapper.classList.remove('active');
});

// Funktion zum Überprüfen des Zustands der Checkbox und zum Aktivieren/Deaktivieren des Buttons
function checkCheckbox() {
    const checkbox = document.getElementById('checkbox');
    const checkboxLabel = document.getElementById('label');

    if (!checkbox.checked) {
        // Wenn die Checkbox nicht aktiviert ist, ändere die Farbe des Textes
        checkboxLabel.classList.add('checkbox-warning');
    } else {
        // Wenn die Checkbox aktiviert ist, entferne die Warnung
        checkboxLabel.classList.remove('checkbox-warning');
    }
}

// Event-Listener für den Button
document.getElementById('button').addEventListener('click', checkCheckbox);
