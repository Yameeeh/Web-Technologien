
    document.addEventListener("DOMContentLoaded", function () {
        const dropdown = document.querySelector(".dropdown");
        const dropdownLink = document.querySelector(".dropdown-link");

        dropdownLink.addEventListener("click", function (e) {
            e.preventDefault();
            dropdown.classList.toggle("active");
        });
    });

