$(document).ready(function () {
        loadSnacks();

        var moneyInserted = 0.00;
        var formattedMoney = 0.00;

        clearForm();

        function updateMoney() {
            formattedMoney = parseFloat(Math.round(moneyInserted * 100) / 100).toFixed(2);
            $('#amount').text('$' + formattedMoney);
        }

        function clearForm() {
            if (moneyInserted > 0) moneyInserted = 0.00;
            if (moneyInserted == 0) updateMoney();
            $('input[name=selection]').val("");
            if ($('#edit-item-selection').is(':empty')) $('#buy-button').prop('disabled', true);
        }

        function clearChange() {
            $('#change').empty();
        }

        function clearMessage() {
            $('#message-out').empty();
        }

        $('#dollar').on('click', function () {
            clearChange()
            moneyInserted += 1.00;
            updateMoney()
        });

        $('#quarter').on('click', function () {
            clearChange()
            moneyInserted += .25;
            updateMoney()
        });

        $('#dime').on('click', function () {
            clearChange()
            moneyInserted += .10;
            updateMoney()
        });

        $('#nickel').on('click', function () {
            clearChange()
            moneyInserted += .05;
            updateMoney()
        });

        $('#buy-button').on('click', function () {
            $('#message-out').empty();
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
                        var itemName = '<a id="itemDetail' + item.id + '">' + item.name + '</a>';
                        var price = '$' + parseFloat(Math.round(item.price * 100) / 100).toFixed(2);
                        var quantity = 'Quantity Left: ' + item.quantity;

                        var newElement = document.createElement('div');
                        newElement.id = 'item-div' + [itemID];
                        newElement.className = "col-md-3 dynamic-items";
                        newElement.innerHTML = '<p class = "item-id">' + itemID + '</p>' + itemName + '<br/>' + price + '<br/><br/>' + quantity;

                        if (item.quantity > 0) mainDiv.append(newElement);

                        $('#itemDetail' + item.id).on('click', function () {
                            clearMessage();
                            $('#edit-item-selection').val(itemID);
                            $('#buy-button').prop('disabled', false);
                        });

                    });
                },

                error: function (response) {
                    $('#message-out').text(response.responseJSON.message);
                }
            });
        }

        function buySnack(itemToPurchase) {
            $('#change').empty();

            $.ajax({
                type: 'GET',
                url: 'http://localhost:8080/money/' + formattedMoney + '/item/' + itemToPurchase,
                success: function (change, status) {
                    if (change.quarters > 0) $('#change').append(' Quarters: ' + change.quarters);
                    if (change.dimes > 0) $('#change').append(' Dimes: ' + change.dimes);
                    if (change.nickels > 0) $('#change').append(' Nickels: ' + change.nickels);
                    if (change.pennies > 0) $('#change').append(' Pennies: ' + change.pennies);
                    if (change.quarters == 0 && change.dimes == 0 && change.nickels == 0 && change.pennies == 0) {
                        clearForm();
                        $('#message-out').text('Thank you!');
                        $('#items-div div').remove();
                        $('#buy-button').prop('disabled', true);
                        loadSnacks();
                    } else {
                        clearForm();
                        $('#message-out').text('Please take your change');
                        $('#items-div div').remove();
                        $('#buy-button').prop('disabled', true);
                        loadSnacks();
                    }
                },
                error: function (response) {
                    $('#message-out').text(response.responseJSON.message);
                }
            })
        }
    }
);