const wrapper = document.querySelector('.wrapper');
const loginLink = document.querySelector('.login-link');
const registerLink = document.querySelector('.register-link');

registerLink.addEventListener('click', () => {
    wrapper.classList.add('active');
    console.log('Register link clicked, wrapper class added');
});

loginLink.addEventListener('click', () => {
    wrapper.classList.remove('active');
    console.log('Login link clicked, wrapper class removed');
});

document.addEventListener('DOMContentLoaded', (event) => {
    console.log('DOM fully loaded and parsed');

    const registerForm = document.getElementById('registerForm');
    const checkbox = document.getElementById('checkbox');
    const checkboxLabel = document.getElementById('label');

    registerForm.addEventListener('submit', async (event) => {
        event.preventDefault();

        if (!checkbox.checked) {
            checkboxLabel.classList.add('checkbox-warning');
            console.log('Checkbox not checked, warning added');
            return;
        } else {
            checkboxLabel.classList.remove('checkbox-warning');
            console.log('Checkbox checked, warning removed');
        }

        const formData = new FormData(registerForm);
        const data = Object.fromEntries(formData.entries());
        console.log('Register Form Data:', data);

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
                console.log('Registration successful:', responseData);
                window.location.href = '/login';
            } else {
                alert(responseData);
                console.log('Registration failed:', responseData);
            }

        } catch (error) {
            console.error('Error during registration:', error);
            alert('Ein Fehler ist aufgetreten: ' + error.message);
        }
    });

    function checkCheckbox() {
        if (!checkbox.checked) {
            checkboxLabel.classList.add('checkbox-warning');
            console.log('Checkbox not checked, warning added (button click)');
        } else {
            checkboxLabel.classList.remove('checkbox-warning');
            console.log('Checkbox checked, warning removed (button click)');
        }
    }

    document.getElementById('button').addEventListener('click', checkCheckbox);
});

document.addEventListener('DOMContentLoaded', (event) => {
    console.log('DOM fully loaded and parsed (login form)');

    const loginForm = document.getElementById('loginForm');

    loginForm.addEventListener('submit', async (event) => {
        event.preventDefault();

        const formData = new FormData(loginForm);
        const data = Object.fromEntries(formData.entries());
        console.log('Login Form Data:', data);

        try {
            const response = await fetch('/api/auth/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            });

            if (response.ok) {
                console.log('Login successful');
                window.location.href = '/';
            } else {
                const responseData = await response.text();
                alert(responseData);
                console.log('Login failed:', responseData);
            }

        } catch (error) {
            console.error('Error during login:', error);
            alert('Ein Fehler ist aufgetreten: ' + error.message);
        }
    });
});
