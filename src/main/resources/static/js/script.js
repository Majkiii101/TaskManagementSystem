// Funkcja pokazująca lub ukrywająca formularz
function togglePopup(id) {
    const popup = document.getElementById(id);
    if (popup.style.display === "flex") {
        popup.style.display = "none"; // Ukryj popup
    } else {
        popup.style.display = "flex"; // Pokaż popup
    }
}


