// {"country":"S. Korea","cases":76429,"todayCases":554,"deaths":1378,"todayDeaths":7,"recovered":65478,"active":9573,"critical":270,"casesPerOneMillion":1490,"deathsPerOneMillion":27,"totalTests":5469247,"testsPerOneMillion":106624},

const country = 'S.%20Korea' // 한국 지역 API 가져오기
const url = `https://coronavirus-19-api.herokuapp.com/countries/${country}` // API 설정
const req = new Request(url) // 설정한 API 요청
const res = await req.loadJSON() // JSON으로 되어있는 API 가져오기

let widget = new ListWidget() // widget 선언
widget.backgroundColor = new Color('#ffffff') // new Color('#ffffff') => Color.white()

let titleTxt = widget.addText('코로나 확진 수') // widget에 들어갈 Text 선언
titleTxt.textColor = Color.black() // Text 색 설정

widget.addSpacer(5) // 줄 간격

let Txt1 = widget.addText(`오늘 ${res.todayCases}`)
Txt1.font = Font.systemFont(25) // 폰트 크기 설정
Txt1.textColor = Color.black()

widget.addSpacer(5)

let Txt2 = widget.addText(`전체 ${res.cases}`)
Txt2.textColor = Color.black()

Script.setWidget(widget) // widget 설정
Script.complete() // 완료