document.addEventListener('DOMContentLoaded', function () {
    // Funktion, um die Benutzerdaten zu laden
    function loadUserData() {
        fetch('/api/profile')
            .then(response => response.json())
            .then(data => {
                document.getElementById('username').value = data.username;
                document.getElementById('email').value = data.email;
                document.getElementById('password').value = data.password;
            })
            .catch(error => console.error('Error:', error));
    }

    // Benutzerdaten beim Laden der Seite abrufen
    loadUserData();

    // Formular-Submit-Event
    document.getElementById('profile-form').addEventListener('submit', function (e) {
        e.preventDefault();

        const userData = {
            username: document.getElementById('username').value,
            email: document.getElementById('email').value,
            password: document.getElementById('password').value
        };

        fetch('/api/profile', {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(userData)
        })
            .then(response => response.json())
            .then(data => {
                alert('Profil aktualisiert!');
            })
            .catch(error => console.error('Error:', error));
    });
});