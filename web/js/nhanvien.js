function addEmployee() {
  document.getElementById("addEmployeeRow").style.display = "table-row";
}

function hideAddEmployee() {
  document.getElementById("addEmployeeRow").style.display = "none";
}
function confirmDelete(maNV) {
  var option = confirm("Bạn có chắc muốn xóa nhân viên " + maNV);
  if (option == true) {
    window.location.href = "delete?maNV=" + maNV;
  }
}
function showEditForm(maNV) {
    const editForms = document.querySelectorAll('.edit-form');
    editForms.forEach(form => {
        form.style.display = 'none';
    });
    const editForm = document.querySelector(`#editForm-${maNV}`);
    if (editForm) {
        editForm.style.display = 'table-row';  // Hiển thị form chỉnh sửa
    }
}

function cancelEdit() {
    const editForms = document.querySelectorAll('.edit-form');
    editForms.forEach(form => {
        form.style.display = 'none';  // Ẩn tất cả các form chỉnh sửa
    });
}