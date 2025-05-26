function toggleEdit(show) {
    document.getElementById("viewMode").style.display = show ? "none" : "block";
    document.getElementById("editForm").style.display = show ? "block" : "none";
}
function showSection(sectionId) {
    // Ẩn tất cả phần nội dung
    document.querySelectorAll('.menu-content').forEach(el => el.style.display = 'none');

    // Hiện phần được chọn
    document.getElementById(sectionId).style.display = 'block';

    // Reset active class
    document.querySelectorAll('.account-menu li').forEach(el => el.classList.remove('active'));
    event.currentTarget.classList.add('active');
}


function openInvoice(data) {
    // Bạn có thể truyền dữ liệu hóa đơn vào đây nếu cần
    document.getElementById("invoiceModal").style.display = "none";
}

function closeInvoice() {
    document.getElementById("invoiceModal").style.display = "none";
}
window.onclick = function (event) {
    const modal = document.getElementById("invoiceModal");
    if (event.target === modal) {
        closeInvoice();
    }
};
    