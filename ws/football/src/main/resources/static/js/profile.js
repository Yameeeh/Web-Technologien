document.addEventListener('DOMContentLoaded', function () {
    // Funktion, um die Benutzerdaten zu laden
    function loadUserData() {
        fetch('/api/profile')
            .then(response => response.json())
            .then(data => {
                document.getElementById('username').value = data.username;
                document.getElementById('email').value = data.email;
                // Setze das Profilbild, falls eine fileID vorhanden ist
                if (data.fileID) {
                    document.getElementById('profile-picture').src = `/api/files/${data.fileID}`;
                }
                // Initialisiere die userData Variable mit den geladenen Daten
                userData = {
                    username: data.username,
                    email: data.email,
                    fileID: data.fileID  // Falls fileID vorhanden ist
                };
            })
            .catch(error => console.error('Error:', error));
    }

    // Benutzerdaten beim Laden der Seite abrufen
    loadUserData();

    // Formular-Submit-Event
    document.getElementById('profile-form').addEventListener('submit', function (e) {
        e.preventDefault();

        const formData = {
            username: document.getElementById('username').value,
            email: document.getElementById('email').value,
            password: document.getElementById('password').value,
            fileID: userData.fileID  // Verwende die fileID aus der userData
        };

        fetch('/api/profile', {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        })
            .then(response => response.json())
            .then(data => {
                if (data.message) {
                    alert(data.message);
                } else {
                    alert('Profil aktualisiert!');
                }
            })
            .catch(error => console.error('Error:', error));
    });

    // Profilbild auswählen
    const pictureButton = document.querySelector('.picturebutton');
    pictureButton.addEventListener('click', function () {
        const fileInput = document.createElement('input');
        fileInput.type = 'file';
        fileInput.accept = 'image/*';
        fileInput.onchange = function () {
            const file = fileInput.files[0];
            const formData = new FormData();
            formData.append('profilePicture', file); // Beachte den 'profilePicture' key

            fetch('/api/profile/picture', {
                method: 'POST',
                body: formData, // FormData verwenden, nicht JSON.stringify
            })
                .then(response => response.json())
                .then(data => {
                    // Setze das Profilbild
                    document.getElementById('profile-picture').src = data.profilePictureUrl;
                    // Aktualisiere die fileID im userData Objekt, falls benötigt
                    userData.fileID = data.fileID;
                    alert('Profilbild erfolgreich aktualisiert!');
                })
                .catch(error => {
                    console.error('Error:', error);
                    alert('Fehler beim Hochladen des Profilbilds');
                });
        };
        fileInput.click();
    });

});
