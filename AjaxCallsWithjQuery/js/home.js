$(document).ready(function () {

    $('#get-weather-button').click(function (event) {
        function checkZip(value) {
            return (/(^\d{5}$)|(^\d{5}-\d{4}$)/).test(value);
        }
        var zipcode = $('#zip-code').val();
        var unit = $('#units').val();
        var key = '24a09ac35b4faacbc12c8e7b00565cfc';
        var url = 'http://api.openweathermap.org/data/2.5/weather?zip=' + zipcode + '&units=' + unit + '&APPID=' + key;
        var url2 = 'http://api.openweathermap.org/data/2.5/forecast/daily?zip=' + zipcode + '&units=' + unit + '&APPID=' + key;

        $.ajax({
            type: 'GET',
            url: url,
            success: function (weatherData) {


                $('#today').empty();
                $('#today').append('<img src="http://openweathermap.org/img/w/' + weatherData.weather[0].icon + '.png">');
                $('#today').append("Current Condition In " + weatherData.name);
                $('#today').append('<p>' + weatherData.weather[0].main + ': ' +
                    weatherData.weather[0].description + '</p>');
                $('#today').append('<p>Temperature: ' + weatherData.main.temp + 'F </p><p>Humidity: ' +
                    weatherData.main.humidity + '%</p><p>Wind: ' + weatherData.wind.speed + " mph</p>");

            },
            error: function () {
                alert('FAILURE');
            }

        });
    });

    $('#get-weather-button').click(function (event) {
        var zipcode = $('#zip-code').val();
        var unit = $('#units').val();
        var key = '24a09ac35b4faacbc12c8e7b00565cfc';
        var url2 = 'http://api.openweathermap.org/data/2.5/forecast/daily?zip=' + zipcode + '&units=' + unit + '&APPID=' + key;

        $.ajax({
            type: 'GET',
            url: url2,
            success: function (weatherData2) {
                //$.each(weatherData2, function () x, item)
                $('#tomorrow').append("Day " + Date(weatherData2.list[1].dt[1] * 1000));
                $('#tomorrow').append('<img src="http://openweathermap.org/img/w/' + weatherData2.weather[0].icon + '.png">');
            },
            error: function () {
                alert('FAILURE');
            }

        });
    });

    //                $.ajax({
    //                    type: 'GET',
    //                    url: url2,
    //                    success: function (weatherData2) {
    //
    //
    //                        $('#today').empty();
    //                        $('#today').append('<img src="http://openweathermap.org/img/w/' + weatherData.weather[0].icon + '.png">');
    //                        $('#today').append("Current Condition In " + weatherData.name);
    //                        $('#today').append('<p>' + weatherData.weather[0].main + ': ' +
    //                            weatherData.weather[0].description + '</p>');
    //                        $('#today').append('<p>Temperature: ' + weatherData.main.temp + 'F</p><p>Humidity: ' +
    //                            weatherData.main.humidity + '%</p><p>Wind: ' + weatherData.wind.speed + " mph</p>");
    //
    //                    },
    //                    error: function () {
    //                        alert('FAILURE');
    //                    }

})
