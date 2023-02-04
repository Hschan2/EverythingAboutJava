const input = document.querySelector('.search');
const ul = document.querySelector('.results');

const cities = ['Tokyo', 'Delhi', 'Shanghai', 'Mumbai', 'Sao Paolo', 'Beijing', 'Seoul'];
const foundCities = [];

const genHTML = (city) => {
	const result = document.createElement('li');
	result.classList.add('result');
	result.innerText = city;
	return result;
};

let timeout = null;

input.addEventListener('keyup', (e) => {
	clearTimeout(timeout);
	const query = e.target.value;

	// 검색 타이핑을 중단할 때까지 기다리기
	timeout = setTimeout(() => {
		if (query != '') {
			cities
				.filter((city) => city.toLowerCase().includes(query.toLowerCase()))
				.forEach((city) => {
					if (foundCities.indexOf(city) == -1) {
						foundCities.push(city);
						ul.appendChild(genHTML(city));
					}
				});

			// 옵션 클릭 시
			document.querySelectorAll('.result').forEach((result) => {
				result.addEventListener('click', () => {
					input.value = result.innerText;

					// 결과 비우기
					foundCities.splice(0, foundCities.length);
					ul.innerHTML = '';
				});
			});
		}
	}, 500);

	// 검색창이 비었을 시 초기화
	// Clear results
	if (query === '') {
		foundCities.splice(0, foundCities.length); // Clear foundCities array
		ul.innerHTML = ''; // Clear the ul
	}
});