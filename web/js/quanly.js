document.addEventListener("DOMContentLoaded", function () {
    const userIcon = document.getElementById('userIcon');
    const userPopup = document.getElementById('userPopup');

    userIcon.addEventListener('click', () => {
        userPopup.style.display = userPopup.style.display === 'block' ? 'none' : 'block';
    });

    window.addEventListener('click', (e) => {
        if (!userIcon.contains(e.target) && !userPopup.contains(e.target)) {
            userPopup.style.display = 'none';
        }
    });
});
