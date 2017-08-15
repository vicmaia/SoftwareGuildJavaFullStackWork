$(document).ready(function(){

    // wire up the toggleNumbers button
    $('#toggleNumbers').on('click', function() {
       $('h2').toggle('slow');
    });

    // show that you can do more than one thing at a time in an event
    // handler
    $('#centerUp').on('click', function() {
        $('h1').addClass('text-center');
        $('h2').addClass('text-center');
        $('#buttonDiv').addClass('text-center');
    });

    // just another example with styles
    $('#headingsBlue').on('click', function() {
        $('h1').css('color', 'blue');
    });
    //this selects any div to do background color blue
    $('div').hover(
        // in callback
        function() {
            $(this).css('background-color', 'CornflowerBlue');
        },
        //this runs when we're not hovering over a div, it changes the background color back to ''
        // out callback
        function() {
            $(this).css('background-color', '');
        });

    $('h2').hover(
        //in callback
        function() {
            $(this).css('color', 'DarkOrange');
        },
        //out callback
        function() {
            $(this).css('color', '');
        });

    $('#mainHeading').hover(
        // in callback
        function() {
            $(this).css('color', 'red');
        },
        // out callback
        function() {
            $(this).css('color', 'pink');
        }
    )

})
