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

    termsLink.onclick = function() {
        termsModal.style.display = "block";
    }

    privacyLink.onclick = function() {
        privacyModal.style.display = "block";
    }

    closeTerms.onclick = function() {
        termsModal.style.display = "none";
    }

    closePrivacy.onclick = function() {
        privacyModal.style.display = "none";
    }

    window.onclick = function(event) {
        if (event.target === termsModal) {
            termsModal.style.display = "none";
        }
        if (event.target === privacyModal) {
            privacyModal.style.display = "none";
        }
    }
});
