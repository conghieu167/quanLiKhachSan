function showEditServiceForm(maDV) {
    // Ẩn tất cả form chỉnh sửa khác nếu cần
    document.querySelectorAll('.edit-form').forEach(form => form.style.display = 'none');
    document.querySelectorAll('.view-mode').forEach(view => view.style.display = 'block');

    // Hiện form chỉnh sửa của dịch vụ cụ thể
    document.getElementById('editServiceForm-' + maDV).style.display = 'block';
    const viewMode = document.getElementById('editServiceForm-' + maDV).previousElementSibling;
    if (viewMode && viewMode.classList.contains('view-mode')) {
        viewMode.style.display = 'none';
    }
}

function cancelEditService(maDV) {
    document.getElementById('editServiceForm-' + maDV).style.display = 'none';
    const viewMode = document.getElementById('editServiceForm-' + maDV).previousElementSibling;
    if (viewMode && viewMode.classList.contains('view-mode')) {
        viewMode.style.display = 'block';
    }
}
