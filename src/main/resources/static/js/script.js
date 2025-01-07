// Funkcja pokazująca lub ukrywająca formularz
function toggleForm(formId) {
    const formContainer = document.getElementById(formId);
    if (formContainer.style.display === "none" || formContainer.style.display === "") {
        formContainer.style.display = "block";
    } else {
        formContainer.style.display = "none";
    }
}


