const ctx = document.getElementById('myPieChart').getContext('2d');

// 데이터 및 설정
const data = {
    labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
    datasets: [{
        label: 'Dataset 1',
        data: [10, 20, 30, 15, 25, 5],
        backgroundColor: [
            'rgba(255, 99, 132, 0.5)',  // Red
            'rgba(54, 162, 235, 0.5)',  // Blue
            'rgba(255, 206, 86, 0.5)',  // Yellow
            'rgba(75, 192, 192, 0.5)',  // Green
            'rgba(153, 102, 255, 0.5)', // Purple
            'rgba(255, 159, 64, 0.5)'   // Orange
        ],
        borderColor: [
            'rgba(255, 99, 132, 1)',    // Red border
            'rgba(54, 162, 235, 1)',    // Blue border
            'rgba(255, 206, 86, 1)',    // Yellow border
            'rgba(75, 192, 192, 1)',    // Green border
            'rgba(153, 102, 255, 1)',   // Purple border
            'rgba(255, 159, 64, 1)'     // Orange border
        ],
        borderWidth: 1
    }]
};

// 차트 생성
const config = {
    type: 'pie', // 'doughnut'으로 변경하면 도넛 차트로 표시 가능
    data: data,
    options: {
        responsive: true,
        plugins: {
            legend: {
                position: 'top',
            },
            tooltip: {
                enabled: true
            }
        }
    }
};

const myPieChart = new Chart(ctx, config);