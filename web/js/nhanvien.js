function addEmployee() {
  document.getElementById("addEmployeeModal").style.display = "block";
}

function hideAddEmployee() {
  document.getElementById("addEmployeeModal").style.display = "none";
}

// Đóng modal khi nhấn nút X hoặc nút Hủy
document.getElementById("closeModal").onclick = hideAddEmployee;
document.getElementById("cancelAdd").onclick = hideAddEmployee;

// Đóng modal khi click bên ngoài nội dung modal
window.onclick = function(event) {
  const modal = document.getElementById("addEmployeeModal");
  if (event.target === modal) {
    hideAddEmployee();
  }
};
function confirmDelete(maNV) {
  var option = confirm("Bạn có chắc muốn xóa nhân viên " + maNV);
  if (option == true) {
    window.location.href = "delete?maNV=" + maNV;
  }
}
function showEditForm(maNV) {
                // Ẩn tất cả các dòng form sửa trước đó
                document.querySelectorAll('.edit-form').forEach(row => {
                    row.style.display = 'none';
                });
                const editRow = document.getElementById("editForm-" + maNV);

                if (editRow) {
                    editRow.style.display = 'table-row';
                }
            }
function cancelEdit() {
    const editForms = document.querySelectorAll('.edit-form');
    editForms.forEach(form => {
        form.style.display = 'none';  // Ẩn tất cả các form chỉnh sửa
    });
}