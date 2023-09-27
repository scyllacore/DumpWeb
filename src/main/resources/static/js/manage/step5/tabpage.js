const popCheckbox = document.getElementById("showHideCheckbox");
const hiddenPart = document.getElementById("hiddenPart");

popCheckbox.addEventListener("change", function() {
    if (popCheckbox.checked) {
        hiddenPart.style.display = "block";
    } else {
        hiddenPart.style.display = "none";
    }
});