$(document).ready(function () {
        //center H1, H2
        //    $('#centerUp').on('click', function() {
        $('h1').addClass('text-center');
        $('h2').addClass('text-center');

        //Replace the class that is on the element containing the text “Team Up!” with the class page-header
        $('.myBannerHeading').addClass('page-header').removeClass('.myBannerHeading');

        //Change the text of “The Squad” to “Yellow Team”
        $('#yellowHeading').html('Yellow Team');

        //Change the background color for each team list to match the name of the team
        $('#orangeHeading').css('background-color', 'Orange');
        $('#blueHeading').css('background-color', 'Blue');
        $('#redHeading').css('background-color', 'Red');
        $('#yellowHeading').css('background-color', 'Yellow');

        //Add Joseph Banks and Simon Jones to the Yellow Team list
        $('#yellowTeamList').append('<li>Joseph Banks</li>');
        $('#yellowTeamList').append('<li>Simon Jones</li>');

        //Hide the element containing the text “Hide Me!!!”
        $('#oops').hide();

        //Remove the element containing the text “Bogus Contact Info” from the footer
        $('#footerPlaceholder').remove();

        //Add a paragraph element containing your name and email to the footer. The text must be in Courier font and be 24 pixels in height
         $('#footer').append('p').text('Ken Gardiner | Kenneth.Gardiner@libertymutual.com').css("fontSize", "24px").css("font-family","Courier");
});