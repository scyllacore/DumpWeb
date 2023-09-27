document.addEventListener("DOMContentLoaded", function () {
    let closeBtn = document.querySelectorAll(".closeBtn");
    let openBtn = document.querySelectorAll(".openBtn");
    popupJS(openBtn, closeBtn);
});


function popupJS(openBtn, closeBtn) {
    let openTarget;

    openBtn.forEach(function (btn) {
        btn.addEventListener("click", function () {
            openTarget = "#" + this.getAttribute("data-popName");
            document.querySelector(openTarget).classList.add('active');
        });
    });

    closeBtn.forEach(function (btn) {
        btn.addEventListener("click", function () {
            document.querySelector(openTarget).classList.remove("active");
        });
    });
}