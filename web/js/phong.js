/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


function confirmDeleteRoom(maPhong) {
  var option = confirm("Bạn có chắc muốn xóa nhân viên " + maPhong);
  if (option == true) {
    window.location.href = "DeleteRoom?maPhong=" + maPhong;
  }
}
function showEditRoomForm(maPhong) {
  // Ẩn tất cả form trước
  const allForms = document.querySelectorAll('.edit-form');
  allForms.forEach(form => form.style.display = 'none');

  // Hiện form của phòng đang chọn
  const form = document.getElementById(`editRoomForm-${maPhong}`);
  if (form) {
    form.style.display = 'block';
    
    // Ẩn view-mode tương ứng
    const viewMode = form.previousElementSibling;
    if (viewMode && viewMode.classList.contains('view-mode')) {
      viewMode.style.display = 'none';
    }
  }
}

function cancelEditRoom() {
  const allForms = document.querySelectorAll('.edit-form');
  allForms.forEach(form => {
    form.style.display = 'none';

    // Hiện lại view-mode nếu có
    const viewMode = form.previousElementSibling;
    if (viewMode && viewMode.classList.contains('view-mode')) {
      viewMode.style.display = 'block';
    }
  });
}