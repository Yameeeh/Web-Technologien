const wrapper = document.querySelector('.wrapper');
const loginLink = document.querySelector('.login-link');
const registerLink = document.querySelector('.register-link');

registerLink.addEventListener('click', ()=> {
    wrapper.classList.add('active');
});

loginLink.addEventListener('click', ()=> {
    wrapper.classList.remove('active');
});
/*
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
}*/

// Event-Listener für den Button



document.addEventListener('DOMContentLoaded', (event) => {
    const registerForm = document.getElementById('registerForm');
    const checkbox = document.getElementById('checkbox');
    const checkboxLabel = document.getElementById('label');

    registerForm.addEventListener('submit', async (event) => {
        event.preventDefault();

        // Überprüfen, ob die Checkbox aktiviert ist
        if (!checkbox.checked) {
            checkboxLabel.classList.add('checkbox-warning');
            return;
        } else {
            checkboxLabel.classList.remove('checkbox-warning');
        }

        // Daten aus dem Formular abrufen
        const formData = new FormData(registerForm);
        const data = Object.fromEntries(formData.entries());

        try {
            const response = await fetch('/api/auth/register', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            });

            const responseData = await response.text(); // Antworttext lesen

            if (response.ok) {
                // Registrierung erfolgreich
                alert(responseData);
                window.location.href = '/login';
            } else {
                // Fehler bei der Registrierung
                alert(responseData);
            }
        } catch (error) {
            console.error('Error:', error);
            alert('Ein Fehler ist aufgetreten: ' + error.message);
        }
    });


    function checkCheckbox() {
        if (!checkbox.checked) {
            checkboxLabel.classList.add('checkbox-warning');
        } else {
            checkboxLabel.classList.remove('checkbox-warning');
        }
    }

    document.getElementById('button').addEventListener('click', checkCheckbox);
});