document.addEventListener('DOMContentLoaded', (event) => {
    const yearSpan = document.getElementById('year');
    const currentYear = new Date().getFullYear();
    yearSpan.textContent = currentYear;

    const termsLink = document.getElementById('termsLink');
    const privacyLink = document.getElementById('privacyLink');
    const termsModal = document.getElementById('termsModal');
    const privacyModal = document.getElementById('privacyModal');
    const closeTerms = document.getElementById('closeTerms');
    const closePrivacy = document.getElementById('closePrivacy');

    termsLink.onclick = function () {
        termsModal.style.display = "block";
    }

    privacyLink.onclick = function () {
        privacyModal.style.display = "block";
    }

    closeTerms.onclick = function () {
        termsModal.style.display = "none";
    }

    closePrivacy.onclick = function () {
        privacyModal.style.display = "none";
    }

    window.onclick = function (event) {
        if (event.target === termsModal) {
            termsModal.style.display = "none";
        }
        if (event.target === privacyModal) {
            privacyModal.style.display = "none";
        }
    };

    // EmailJS functionality
    (function () {
        document.getElementById('newsletter-form').addEventListener('submit', function (event) {
            event.preventDefault();

            // Get the email input value
            var emailInput = document.querySelector('input[name="user_email"]').value;

            // Send email to backend
            fetch('http://localhost:8080/api/newsletter', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ email: emailInput })
            })
                .then(response => response.json())
                .then(data => {
                    console.log('Backend response:', data);
                })
                .catch((error) => {
                    console.error('Error:', error);
                });

            emailjs.sendForm('service_t168566', 'template_xw7r9tu', this)
                .then(function (response) {
                    console.log('SUCCESS!', response.status, response.text);
                    alert('Vielen Dank für die Anmeldung zum Newsletter!');
                }, function (error) {
                    console.log('FAILED...', error);
                    alert('Es gab ein Problem bei der Anmeldung. Bitte versuchen Sie es später erneut.');
                });
        });
    })();
});
