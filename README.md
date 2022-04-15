Написал 3 теста:

1) Тест для extention функции (путь -> data.remote.responses.CurrencyResponseKtTest )
	обычный тест с ассертом,

2) Teст для ValueChanger (путь -> domain.ValueChanger) 
	 параметризованный тест, тест на эксепшен (путь ->test.domain.ValueChangerTest)
	в нем два nested тест класса (на каждую функцию). Должны запускаться оба, если запускать из внешнего класса

3) Тест для CurrencyDataRepositoryImpl (путь -> data.repository) 
	использовал mock, тест CurrenciesRepositoryImplTest(путь -> test.data.repository))


