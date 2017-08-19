$(document).ready(function () {
        loadSnacks();

        var moneyInserted = 0.00;

        updateMoney();

        function updateMoney() {
            formattedMoney = '$' + parseFloat(Math.round(moneyInserted * 100) / 100).toFixed(2);
            $('#amount').text(formattedMoney);
        }

        $('#dollar').on('click', function () {
            moneyInserted += 1.00;
            updateMoney()
        });

        $('#quarter').on('click', function () {
            moneyInserted += .25;
            updateMoney()
        });

        $('#dime').on('click', function () {
            moneyInserted += .10;
            updateMoney()
        });

        $('#nickel').on('click', function () {
            moneyInserted += .05;
            updateMoney()
        });

        $('#buy-button').on('click', function () {
            snack = $('#edit-item-selection').val();
            buySnack(snack);
        });

        function loadSnacks() {
            var mainDiv = $('#items-div');

            $.ajax({
                type: 'GET',
                url: 'http://localhost:8080/items',
                success: function (items) {
                    $.each(items, function (index, item) {
                        var itemID = item.id;
                        var itemName = item.name;
                        var price = '$' + parseFloat(Math.round(item.price * 100) / 100).toFixed(2);
                        var quantity = 'Quantity Left: ' + item.quantity;

                        var newElement = document.createElement('div');
                        newElement.id = 'item-div' + [itemID];
                        newElement.className = "col-md-3 dynamic-items";
                        newElement.innerHTML = '<p class = "item-id">' + itemID + '</p>' + itemName + '<br/>' + price + '<br/><br/>' + quantity;
                        mainDiv.append(newElement);

                    });
                },
                error: function () {
                    $('#errorMessages')
                        .append($('<li>')
                            .attr({class: 'list-group-item list-group-item-danger'})
                            .text('Error calling web service.  Please try again later.'));
                }
            });
        }

        function buySnack(itemToPurchase) {
            // clear errorMessages
            $('#errorMessages').empty();
            // get the contact details from the server and then fill and show the
            // form on success
            $.ajax({
                type: 'GET',
                url: 'http://localhost:8080/money/' + moneyInserted + '/item/' + itemToPurchase,
                success: function (change, status) {
                    if (change.quarters > 0) $('#change').append(' Quarters: ' + change.quarters);
                    if (change.dimes > 0) $('#change').append(' Dimes: ' + change.dimes);
                    if (change.nickels > 0) $('#change').append(' Nickels: ' + change.nickels);
                    if (change.pennies > 0) $('#change').append(' Pennies: ' + change.pennies);
                    $('#message').text(change.message);
                },

                statusCode: {
                    404: function (response) {
                        $('#message').text(response.message);
                    },
                    422: function (response) {
                        $('#message').text(response.message);
                    },
                    400: function (response) {
                        $('#message').text("Please enter an item # to purchase");
                    }
                },
                error: function (response) {
                    $('#message').text(response.responseJSON.message);
                }
            })
        }
    }
);

//statusCode: {
//    //404: function (response) {
//    //    $('#message').text("Please enter an item # to purchase");
//    //},
//    422: function (response) {
//        $('#message').text(response.message);
//    },
//    //400: function (response) {
//    //    $('#message').text("Please enter an item # to purchase");
//    //}
//},