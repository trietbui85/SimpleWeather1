package com.bmtriet.simpleweather.data.network.service

val sevenDaysForecastResponse401 = """
    {"cod":401, 
    "message": "Invalid API key. Please see https://openweathermap.org/faq#error401 for more info."}
""".trimIndent()

val sevenDaysForecastResponse200 = """
    {
      "city": {
        "id": 1580578,
        "name": "Ho Chi Minh City",
        "coord": {
          "lon": 106.6667,
          "lat": 10.8333
        },
        "country": "VN",
        "population": 0,
        "timezone": 25200
      },
      "cod": "200",
      "message": 16.2518436,
      "cnt": 1,
      "list": [
        {
          "dt": 1666584000,
          "sunrise": 1666564981,
          "sunset": 1666607512,
          "temp": {
            "day": 303.21,
            "min": 297.53,
            "max": 305.36,
            "night": 299.71,
            "eve": 302.8,
            "morn": 297.53
          },
          "feels_like": {
            "day": 307.85,
            "night": 299.71,
            "eve": 308.27,
            "morn": 298.43
          },
          "pressure": 1011,
          "humidity": 68,
          "weather": [
            {
              "id": 501,
              "main": "Rain",
              "description": "moderate rain",
              "icon": "10d"
            }
          ],
          "speed": 3,
          "deg": 160,
          "gust": 5.75,
          "clouds": 100,
          "pop": 0.84,
          "rain": 2.68
        }
      ]
    }
""".trimIndent()
