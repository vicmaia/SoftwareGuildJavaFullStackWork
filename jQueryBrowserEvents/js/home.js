$(document).ready(function () {
    $('#akronInfoDiv').hide();
    $('#akronWeather').hide();
    $('#minneapolisInfoDiv').hide();
    $('#minneapolisWeather').hide();
    $('#louisvilleInfoDiv').hide();
    $('#louisvilleWeather').hide();

    $('#mainButton').on('click', function() {
        $('#mainInfoDiv').show();
        $('#akronInfoDiv').hide();
        $('#akronWeather').hide();
        $('#minneapolisInfoDiv').hide();
        $('#minneapolisWeather').hide();
        $('#louisvilleInfoDiv').hide();
        $('#louisvilleWeather').hide();
    });

    $('#akronButton').on('click', function() {
        $('#akronInfoDiv').show();
        $('#mainInfoDiv').hide();
        $('#akronWeather').hide();
        $('#minneapolisInfoDiv').hide();
        $('#minneapolisWeather').hide();
        $('#louisvilleInfoDiv').hide();
        $('#louisvilleWeather').hide();
    });

    $('#minneapolisButton').on('click', function() {
        $('#akronInfoDiv').hide();
        $('#mainInfoDiv').hide();
        $('#akronWeather').hide();
        $('#minneapolisInfoDiv').show();
        $('#minneapolisWeather').hide();
        $('#louisvilleInfoDiv').hide();
        $('#louisvilleWeather').hide();
    });

    $('#louisvilleButton').on('click', function() {
        $('#akronInfoDiv').hide();
        $('#mainInfoDiv').hide();
        $('#akronWeather').hide();
        $('#minneapolisInfoDiv').hide();
        $('#minneapolisWeather').hide();
        $('#louisvilleInfoDiv').show();
        $('#louisvilleWeather').hide();
    });

    $('#akronWeatherButton').on('click', function() {
        $('#akronWeather').toggle();
    });

    $('#minneapolisWeatherButton').on('click', function() {
        $('#minneapolisWeather').toggle();
    });

    $('#louisvilleWeatherButton').on('click', function() {
        $('#louisvilleWeather').toggle();
    });
});