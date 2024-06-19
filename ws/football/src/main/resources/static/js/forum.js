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

function togglePopup() {
    const popup = document.getElementById("popup-1");
    const isActive = popup.classList.contains("active");
    const imageOutput = document.getElementById('imageOutput');
    const userComment = document.getElementById('userComment');
    const uploadButton = document.getElementById('uploadButton');

    if (!isActive) {
        imageOutput.innerHTML = '';
        imageOutput.style.height = '0';
        userComment.value = '';
        photoUploaded = false;
        uploadButton.removeAttribute('data-tooltip');
        uploadButton.disabled = false;
    }

    popup.classList.toggle("active");

    // Laden der Kommentare erneut aufrufen, wenn das Popup geschlossen wird
    if (isActive && currentTopicId !== null) {
        loadComments(currentTopicId);
    }
}

let photoUploaded = false;
const content = document.getElementById('content');
const initialContentHeight = content ? content.clientHeight : 0;
let currentImage = null;

function handleFileChange(event) {
    const file = event.target.files[0];
    if (file) {
        const reader = new FileReader();
        reader.onload = function (e) {
            const img = document.createElement('img');
            img.src = e.target.result;

            if (currentImage) {
                currentImage.src = img.src;
            } else {
                const closeIcon = document.createElement('button');
                closeIcon.innerHTML = '&times;';
                closeIcon.classList.add('close-icon');

                img.onload = function () {
                    if (img.naturalWidth > img.naturalHeight) {
                        img.classList.add('landscape');
                    } else {
                        img.classList.add('portrait');
                    }

                    const imageOutput = document.getElementById('imageOutput');
                    imageOutput.appendChild(img);
                    imageOutput.appendChild(closeIcon);

                    const newHeight = initialContentHeight + img.height;
                    content.style.height = newHeight + 'px';
                    imageOutput.style.height = img.height + 'px';

                    photoUploaded = true;
                    currentImage = img;
                };

                closeIcon.addEventListener('click', function () {
                    const imageOutput = document.getElementById('imageOutput');
                    imageOutput.innerHTML = '';
                    imageOutput.style.height = '0';

                    content.style.height = initialContentHeight + 'px';

                    photoUploaded = false;
                    const uploadButton = document.getElementById('uploadButton');
                    uploadButton.disabled = false;
                    uploadButton.removeAttribute('data-tooltip');

                    document.getElementById('fileInput').addEventListener('change', handleFileChange);
                });
            }
        };
        reader.readAsDataURL(file);
    }
}

document.getElementById('uploadButton').addEventListener('click', function () {
    if (photoUploaded) {
        this.setAttribute('data-tooltip', 'Es kann nur ein Foto hochgeladen werden');
        this.disabled = true;
    } else {
        document.getElementById('fileInput').click();
    }
});

document.getElementById('fileInput').addEventListener('change', handleFileChange);

function checkAuthStatus() {
    fetch('/api/auth/status', {
        method: 'GET',
        credentials: 'include'
    })
        .then(response => response.json())
        .then(data => {
            if (data.authenticated) {
                document.getElementById('auth-status').innerText = `${data.username}`;
            } else {
                document.getElementById('auth-status').innerText = 'Not logged in';
            }
        })
        .catch(error => console.error('Error:', error));
}

async function postComment() {
    const comment = document.getElementById('userComment').value;
    const fileInput = document.getElementById('fileInput');
    const file = fileInput.files[0];

    if (!comment) {
        alert('Bitte geben Sie einen Kommentar ein.');
        return;
    }

    const formData = new FormData();
    formData.append('comment', comment);
    formData.append('image', file);
    formData.append('topicId', currentTopicId);

    try {
        const timestamp = new Date().getTime();
        const url = `http://localhost:8080/api/comments?v=${timestamp}`;

        const response = await fetch(url, {
            method: 'POST',
            body: formData
        });

        if (!response.ok) {
            throw new Error('Network response was not ok ' + response.statusText);
        }

        const result = await response.text();
        console.log('Erfolgreich gepostet:', result);
        alert('Kommentar und Bild wurden erfolgreich gepostet!');

        setTimeout(() => {
            loadComments(currentTopicId);
        }, 1000); // 1 Sekunde Verzögerung

    } catch (error) {
        console.error('Es gab ein Problem mit der Anfrage:', error);
        alert('Es gab ein Problem beim Posten des Kommentars und Bildes.');
    }
}

async function loadComments(topicId) {
    try {
        const response = await fetch(`http://localhost:8080/api/comments/list?topicId=${topicId}`);
        if (!response.ok) {
            throw new Error('Network response was not ok ' + response.statusText);
        }

        const comments = await response.json();
        const commentSection = document.getElementById(`comment-section-${topicId}`);
        commentSection.innerHTML = '';

        comments.reverse();

        comments.forEach(comment => {
            const commentContainer = document.createElement('div');
            commentContainer.classList.add('comment-container');

            const profilePictureSection = document.createElement('div');
            profilePictureSection.classList.add('profile-picture-section');
            const profilePicture = document.createElement('img');
            profilePicture.src = '/assets/user.png';
            profilePicture.alt = 'Profile Picture';
            profilePictureSection.appendChild(profilePicture);

            const commentElement = document.createElement('div');
            commentElement.classList.add('comment');
            const userContainer = document.createElement('div');
            userContainer.classList.add('user');
            const userName = document.createElement('p');
            userName.textContent = comment.username;
            userContainer.appendChild(userName);
            commentElement.appendChild(userContainer);

            const commentText = document.createElement('p');
            commentText.textContent = comment.text;
            commentElement.appendChild(commentText);

            if (comment.fileName) {
                const commentImage = document.createElement('img');
                commentImage.src = `http://localhost:8080/uploads/${comment.fileName}`;
                commentImage.alt = 'Comment Image';
                commentElement.appendChild(commentImage);
            }

            commentContainer.appendChild(profilePictureSection);
            commentContainer.appendChild(commentElement);
            commentSection.appendChild(commentContainer);
        });
    } catch (error) {
        console.error('Es gab ein Problem beim Laden der Kommentare:', error);
    }
}

let currentTopicId = null;

function openTopic(topicId) {
    currentTopicId = topicId;
    const tabs = document.querySelectorAll('.nav-link a');
    const commentSections = document.querySelectorAll('.comment-section');

    tabs.forEach(tab => tab.parentElement.classList.remove('active'));
    commentSections.forEach(section => section.classList.remove('active'));

    document.querySelector(`.nav-link:nth-child(${topicId})`).classList.add('active');
    document.getElementById(`comment-section-${topicId}`).classList.add('active');

    document.getElementById('post-button-container').style.display = 'block';

    loadComments(topicId);
}

function markActiveLink(topicId) {
    const links = document.querySelectorAll('.sidebar li .text');
    links.forEach(link => {
        link.classList.remove('active');
    });

    const activeLink = document.querySelector(`.sidebar li:nth-child(${topicId}) .text`);
    if (activeLink) {
        activeLink.classList.add('active');
    }
}
