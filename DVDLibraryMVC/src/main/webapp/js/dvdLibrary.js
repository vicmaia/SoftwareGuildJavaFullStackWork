$(document).ready(function () {

    // Add Button onclick handler
    $('#search-button').click(function (event) {

        $.ajax({
            type: 'POST',
            url: 'search/dvds',
            data: JSON.stringify({
                firstName: $('#search-first-name').val(),
                lastName: $('#search-last-name').val(),
                company: $('#search-company').val(),
                phone: $('#search-phone').val(),
                email: $('#search-email').val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json',
            success: function (data) {
                // clear errorMessages
                $('#errorMessages').empty();
                fillContactTable(data);
            },
            error: function () {
                $('#errorMessages')
                        .append($('<li>')
                                .attr({class: 'list-group-item list-group-item-danger'})
                                .text('Error calling web service.  Please try again later.'));
            }
        });
    });
});

function fillContactTable(data) {
    // we need to clear the previous content so we don't append to it
    clearDvdTable();

    // grab the the tbody element that will hold the rows of contact information
    var contentRows = $('#contentRows');

    $.each(data, function (index, contact) {
        var name = dvd.firstName + ' ' + dvd.lastName;
        var company = dvd.company;
        var phone = dvd.phone;
        var email = dvd.email;

        var row = '<tr>';
        row += '<td>' + name + '</td>';
        row += '<td>' + company + '</td>';
        row += '<td>' + phone + '</td>';
        row += '<td>' + email + '</td>';
        row += '</tr>';
        contentRows.append(row);
    });
}

function clearContactTable() {
    $('#contentRows').empty();
}