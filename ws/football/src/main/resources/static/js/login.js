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

        if (!checkbox.checked) {
            checkboxLabel.classList.add('checkbox-warning');
            return;
        } else {
            checkboxLabel.classList.remove('checkbox-warning');
        }

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

            const responseData = await response.text();
            if (response.ok) {
                alert(responseData);
                window.location.href = '/login';
            } else {
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

document.addEventListener('DOMContentLoaded', (event) => {
    const registerForm = document.getElementById('loginForm');

    registerForm.addEventListener('submit', async (event) => {
        event.preventDefault();

        const formData = new FormData(loginForm);
        const data = Object.fromEntries(formData.entries());

        try {
            const response = await fetch('/api/auth/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            });

            if (response.ok) {
                window.location.href = '/';
            } else {
                const responseData = await response.text();
                alert(responseData);
            }

        } catch (error) {
            console.error('Error:', error);
            alert('Ein Fehler ist aufgetreten: ' + error.message);
        }
    });
});