/* Anpassen der Navbar */
document.addEventListener('DOMContentLoaded', function() {
    const navbar = document.getElementById('navbar');
    const changeHeight = 850; // Höhe in Pixeln, ab der sich die Navbar ändern soll

    window.addEventListener('scroll', function() {
        if (window.scrollY >= changeHeight) {
            header.classList.add('changed');
            navbar.classList.add('changed');
        } else {
            header.classList.remove('changed');
            navbar.classList.remove('changed');
        }
    });
});



const myslide = document.querySelectorAll('.myslider'),
        dot = document.querySelectorAll('.dot');

let counter = 1;
slidefun(counter);

let timer = setInterval(autoslide, 8000);
function autoslide() {
    counter += 1;
    slidefun(counter);
}
function plusSlides(n) {
    counter += n;
    slidefun(counter);
    resetTimer();
}
function currentSlide(n) {
    counter = n;
    slidefun(counter);
    resetTimer();
}
function resetTimer() {
    clearInterval(timer);
    timer = setInterval(autoslide, 8000);
}

function slidefun(n) {
    let i;
    for(i = 0; i<myslide.length;i++) {
        myslide[i].style.display = "none";
    }
    for(i = 0; i<dot.length;i++) {
        dot[i].classList.remove('active');
    }
    if(n > myslide.length) {
        counter = 1;
    }
    if(n < 1) {
        counter = myslide.length;
    }
    myslide[counter - 1].style.display = "block";
    dot[counter - 1].classList.add('active');
}

/* Popup Fenster Stadienspiele */
function togglePopup1() {
    document.getElementById("popup-1").classList.toggle("active");
}
function togglePopup2() {
    document.getElementById("popup-2").classList.toggle("active");
}
function togglePopup3() {
    document.getElementById("popup-3").classList.toggle("active");
}
function togglePopup4() {
    document.getElementById("popup-4").classList.toggle("active");
}
function togglePopup5() {
    document.getElementById("popup-5").classList.toggle("active");
}
function togglePopup6() {
    document.getElementById("popup-6").classList.toggle("active");
}
function togglePopup7() {
    document.getElementById("popup-7").classList.toggle("active");
}
function togglePopup8() {
    document.getElementById("popup-8").classList.toggle("active");
}
function togglePopup9() {
    document.getElementById("popup-9").classList.toggle("active");
}








