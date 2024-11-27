$.ajax({
    url : "/business/data",
    success : function(rankDataList){
        const labels = rankDataList.map(item => item.name); // name 값을 라벨로 사용
        const data = rankDataList.map(item => parseInt(item.transactionNum, 10)); // transactionNum 값을 숫자로 변환하여 데이터로 사용

// 차트 생성
const ctx = document.getElementById('myBarChart').getContext('2d');
const myBarChart = new Chart(ctx, {
    type: 'bar', // 수직 막대 차트
    data: {
        labels: labels, // x축 라벨
        datasets: [{
            label: '거래 횟수',
            data: data, // 데이터 값
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)'
            ], // 막대 배경 색상
            borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
            ], // 막대 테두리 색상
            borderWidth: 1 // 테두리 두께
        }]
    },
    options: {
        responsive: true, // 반응형 설정
        scales: {
            x: {
                ticks: {
                    font: {
                        size: 12, // 글씨 크기
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
    fail : function(){
        alert("거래처 순위 조회 실패...")
    }
})