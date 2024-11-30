$.ajax({
    url: "/business/data",
    success: function (rankDataList) {
        const labels = rankDataList.map(item => item.name); // name 값을 라벨로 사용
        const data = rankDataList.map(item => parseInt(item.transactionNum, 10)); // transactionNum 값을 숫자로 변환하여 데이터로 사용

        // 막대차트 생성
        const ctx = document.getElementById('myBarChart').getContext('2d');
        const myBarChart = new Chart(ctx, {
            type: 'bar', // 수직 막대 차트
            data: {
                labels: labels, // x축 라벨
                datasets: [{
                    label: '거래 횟수',
                    data: data, // 데이터 값
                    backgroundColor: [
                        '#1D64F2',
                        '#BFF205',
                        '#F25D1D',
                        '#F2A91D',
                        '#8A2BE2'
                    ], // 막대 배경 색상
                    borderColor: [
                        '#1D64F2',
                        '#BFF205',
                        '#F25D1D',
                        '#F2A91D',
                        '#8A2BE2',
                    ], // 막대 테두리 색상
                    borderWidth: 1
                }]
            },
            options: {
                responsive: true, // 반응형 설정
                maintainAspectRatio: false, // div 크기에 맞추기 위해 비율 고정 해제
                scales: {
                    x: {
                        ticks: {
                            font: {
                                size: 12,
                            },
                            maxRotation: 0, // 회전하지 않음
                            minRotation: 0, // 회전하지 않음
                            align: 'center', // 가운데 정렬
                        }
                    },
                    y: {
                        beginAtZero: true // y축 0부터 시작
                    }
                }
            }
        });
    },
    error: function () {
        alert("거래처 순위 조회 실패...")
    }
})


$.ajax({
    url: "/store/data",
    success: function (statusList) {

        const labels = statusList.map(item => item.status);
        const cntData = statusList.map(item => parseInt(item.count, 10));
        
        const ctx = document.getElementById('myPieChart').getContext('2d');

        // 데이터 설정
        const data = {
          labels: labels, // 라벨
          datasets: [{
            data: cntData, // 각 데이터의 값
            backgroundColor: ['#1D64F2', '#BFF205', '#F25D1D','#F2A91D'],
            hoverBackgroundColor: ['#1D64F2', '#BFF205', '#F25D1D','#F2A91D']
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
        alert("가맹점 조회 실패...");
    }
})