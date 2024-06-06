/*Sidebar*/
document.addEventListener("DOMContentLoaded", function () {
    const checkbox = document.getElementById("check2");
    const sidebar = document.querySelector(".sidebar");

    const handleResize = () => {
        if (window.innerWidth > 1100) {
            checkbox.checked = false;
            sidebar.style.width = "25%";
        } else {
            if (!checkbox.checked) {
                sidebar.style.width = "100px";
            }
        }
    };

    checkbox.addEventListener("change", function () {
        if (checkbox.checked) {
            sidebar.style.width = "40%";
        } else {
            sidebar.style.width = "100px";
        }
    });

    window.addEventListener("resize", handleResize);
    handleResize(); // Initial call to set the correct sidebar state based on the initial window size
});

/*Pup-up für Kommentarpost*/
function togglePopup() {
    document.getElementById("popup-1").classList.toggle("active");
}

/* Image hochladen */
document.getElementById('uploadButton').addEventListener('click', function() {
    document.getElementById('fileInput').click();
});

document.getElementById('fileInput').addEventListener('change', function(event) {
    const file = event.target.files[0];
    if (file) {
        const reader = new FileReader();
        reader.onload = function(e) {
            const img = document.createElement('img');
            img.src = e.target.result;
            
            // Check the image orientation
            img.onload = function() {
                if (img.naturalWidth > img.naturalHeight) {
                    img.classList.add('landscape');
                } else {
                    img.classList.add('portrait');
                }
            };
            
            document.getElementById('imageOutput').appendChild(img);
        };
        reader.readAsDataURL(file);
    }
});

/*document.addEventListener('DOMContentLoaded', function() {
    const uploadButton = document.getElementById('uploadButton');
    const fileInput = document.getElementById('fileInput');
    const imageOutput = document.getElementById('imageOutput');
    const content = document.getElementById('content');
    const userComment = document.getElementById('userComment');

    function adjustTextareaAndContentHeight() {
        if (imageOutput.offsetHeight === 0) {
            userComment.style.height = '';
        } else {
            userComment.style.height = '75px'; // Reset height if imageOutput is not 0
        }
        content.style.height = (content.scrollHeight + imageOutput.offsetHeight) + 'px';
    }});
    */