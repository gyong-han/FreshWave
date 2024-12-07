$.ajax({
    url: "/home/data",
    success: function (chartList) {
        const labels = chartList.map(item => item.priority);
        const cntData = chartList.map(item => parseInt(item.count, 10));
        
        const ctx = document.getElementById('myPieChart').getContext('2d');

        // 데이터 설정
        const data = {
          labels: labels, // 라벨
          datasets: [{
            data: cntData, // 각 데이터의 값
            backgroundColor: ['#1D64F2', '#BFF205', '#F25D1D'],
            hoverBackgroundColor: ['#1D64F2', '#BFF205', '#F25D1D']
          }]
        };
    
        // 차트 옵션
        const options = {
          plugins: {
            legend: {
              position: 'top', // 범례 위치
            },
            tooltip: {
              callbacks: {
                label: function(tooltipItem) {
                  const total = tooltipItem.dataset.data.reduce((acc, curr) => acc + curr, 0);
                  const value = tooltipItem.raw;
                  const percentage = ((value / total) * 100).toFixed(1);
                  return `${tooltipItem.label}: ${value} (${percentage}%)`;
                }
              }
            },
            datalabels: {
              formatter: (value, context) => {
                const total = context.chart.data.datasets[0].data.reduce((acc, curr) => acc + curr, 0);
                const percentage = ((value / total) * 100).toFixed(1); // 소수점 1자리 표시
                return `${value} (${percentage}%)`; // 값과 퍼센트 표시
              },
              color: '#000000', // 텍스트 색상
              font: {
                size: 14, // 텍스트 크기
                weight: 'bold' // 텍스트 굵기
              }
            }
          }
        };
    
        // 차트 생성
        const myPieChart = new Chart(ctx, {
          type: 'pie', // 차트 종류: pie 또는 doughnut
          data: data,
          options: options,
          plugins: [ChartDataLabels] // Data Labels 플러그인 추가
        });
    },
    error : function(){
        alert("프로젝트 실패...");
    }
})