// Biểu đồ Tỷ lệ loại phòng
const roomTypeCtx = document.getElementById('tk-roomTypeChart').getContext('2d');
const roomTypeChart = new Chart(roomTypeCtx, {
  type: 'pie',
  data: {
    labels: roomTypeLabels,
    datasets: [{
      data: roomTypeData,
      backgroundColor: ['#5fbdfd', '#000000', '#ffffff'],
      borderColor: '#ccc',
      borderWidth: 1
    }]
  }
});

// Biểu đồ Doanh thu
const revenueCtx = document.getElementById('tk-revenueChart').getContext('2d');
const revenueChart = new Chart(revenueCtx, {
  type: 'line',
  data: {
    labels: revenueLabels, // array ngày
    datasets: [{
      label: 'Doanh thu (VND)',
      data: revenueData,    // array doanh thu
      borderColor: '#5fbdfd',
      backgroundColor: 'rgba(95, 189, 253, 0.1)',
      fill: true,
      tension: 0.3
    }]
  },
  options: {
    scales: {
      y: {
        beginAtZero: true,
        ticks: {
          // Format tiền VND
          callback: function(value) {
            return new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(value);
          }
        }
      }
    }
  }
});

// Biểu đồ Tỷ lệ lấp đầy
const ctxOccupancy = document.getElementById('tk-occupancyChart').getContext('2d');
new Chart(ctxOccupancy, {
  type: 'bar',
  data: {
    labels: occupancyLabels,    // ['Ngày 1', 'Ngày 2', ...]
    datasets: [{
      label: 'Tỷ lệ lấp đầy (%)',
      data: occupancyData,       // [70, 80, 65, ...] - các giá trị 0-100
      backgroundColor: 'rgba(54, 162, 235, 0.7)',
      borderColor: 'rgba(54, 162, 235, 1)',
      borderWidth: 1,
      borderRadius: 5,
      maxBarThickness: 50
    }]
  },
  options: {
    scales: {
      y: {
        beginAtZero: true,
        max: 100,
        ticks: {
          callback: function(value) {
            return value + '%';  // Hiển thị % trên trục y
          }
        }
      }
    },
    plugins: {
      legend: {
        display: true,
        position: 'top'
      },
      tooltip: {
        callbacks: {
          label: function(context) {
            return context.parsed.y + '%';
          }
        }
      }
    }
  }
});

// Lấy biểu đồ bằng ID từ canvas
function downloadChart(chartId, fileName) {
    const canvas = document.getElementById(chartId);
    const url = canvas.toDataURL('image/png');

    const link = document.createElement('a');
    link.href = url;
    link.download = fileName + '.png';
    link.click();
}

// Gắn sự kiện cho các nút tải biểu đồ
document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("tk-downloadRoomType").addEventListener("click", function () {
        downloadChart("tk-roomTypeChart", "bieu_do_loai_phong");
    });

    document.getElementById("tk-downloadRevenue").addEventListener("click", function () {
        downloadChart("tk-revenueChart", "bieu_do_doanh_thu");
    });

    document.getElementById("tk-downloadOccupancy").addEventListener("click", function () {
        downloadChart("tk-occupancyChart", "bieu_do_ty_le_lap_day");
    });
});
