//home.js

//we're interested in the ready event
//we'll listen to this event and then run a block of code
//function() is an anonymous function no name

//CSS Selector Tag Review
//Select by Tag H1P
//Select by Class - Dot Operator .blue
//Select by ID - #mainHeading

//$ is an alias for jQuery
//jQuery uses CSS syntax to work with html elements
$(document).ready(function() {
    //alert("I'm ready!");
    
    //$('H1').hide(); //hide all H1s
    //$('.notOriginal').hide(); //hides all with class notOriginal
    //$('#second').hide();

    $('#third').remove();

    $('#emptyDiv').append('p').text('A new paragraph of text...');

    //$('#newButton').addClass('btn btn-default');

    $('#first').css('color', 'blue');

});

