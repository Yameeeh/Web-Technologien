document.addEventListener("DOMContentLoaded", function () {
    const checkbox = document.getElementById("check2");
    const sidebar = document.querySelector(".sidebar");

    const handleResize = () => {
        if (window.innerWidth > 1100) {
            checkbox.checked = false;
            sidebar.style.width = "2500px";
        } else {
            if (!checkbox.checked) {
                sidebar.style.width = "600px";
            }
        }
    };

    checkbox.addEventListener("change", function () {
        if (checkbox.checked) {
            sidebar.style.width = "2500px";
        } else {
            sidebar.style.width = "600px";
        }
    });

    window.addEventListener("resize", handleResize);
    handleResize(); // Initial call to set the correct sidebar state based on the initial window size
});
